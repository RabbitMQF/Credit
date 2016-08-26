package com.example.credit.Activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Adapters.Commment_ItemlistAdapter;
import com.example.credit.Dialogs.WaitDialog;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.ContainsEmojiEditText;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.MyListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.example.credit.Views.FileUtil.decodeBitmap;

/**
 * 评论列表详情界面
 */
public class CommentListDetailsActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;

    @ViewInject(R.id.commD_img)
    ImageView commD_img;//评论人头像
    @ViewInject(R.id.commD_name)
    TextView commD_name;//评论人名字
    @ViewInject(R.id.commD_time)
    TextView commD_time;//评论时间
    @ViewInject(R.id.commD_cont)
    TextView commD_cont;//评论内容

    @ViewInject(R.id.plD_alreadgood)
    ImageView plD_alreadgood;//好评红icon
    @ViewInject(R.id.plD_good)
    ImageView plD_good;//好评灰icon
    @ViewInject(R.id.plD_good_num)
    TextView plD_good_num;//好评数量

    @ViewInject(R.id.Dalreadynogood)
    ImageView Dalreadynogood;//差评黑icon
    @ViewInject(R.id.Dnogood)
    ImageView Dnogood;//差评灰icon
    @ViewInject(R.id.Dnogood_num)
    TextView Dnogood_num;//差评数量

    @ViewInject(R.id.Dliuyan)
    ImageView Dliuyan;//留言icon
    @ViewInject(R.id.Dliuyan_num)
    TextView Dliuyan_num;//留言数量

    @ViewInject(R.id.commplD_list)
    MyListView commplD_list;//子回复列表

    @ViewInject(R.id.Dhuifu)
    LinearLayout huifu;//回复布局
    @ViewInject(R.id.Dhuifu_con)
    ContainsEmojiEditText huifu_con;//回复内容
    @ViewInject(R.id.Dhuifu_btn)
    TextView huifu_btn;//回复按钮、
    public static WaitDialog wd;
    int position;//下标
    public static Handler handler;
    String deviceId,uid,pid,KeyNo,token;
    int S,N,So,No;
    CreditSharePreferences csp;
    public static List<DataManager.Replay2review> replay2reviewListSS = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list_details);
        ViewUtils.inject(this);
        wd=new WaitDialog(this);
        csp=CreditSharePreferences.getLifeSharedPreferences();
        Intent i=getIntent();
        position=i.getIntExtra("position",0);
        uid=i.getStringExtra("uid");
        pid=i.getStringExtra("pid");
        Build bd = new Build();
        deviceId=bd.MODEL;//设备ID
        KeyNo=CommentListActivity.listpl.get(position).COMMENTID;
        token = SearchFirmActivty.MD5s(KeyNo + deviceId);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String  date =sDateFormat.format(new java.util.Date());
                        DataManager.MyCommentlistr.DataBean.UserreviewBean.Replay2review r2=new DataManager.MyCommentlistr.DataBean.UserreviewBean.Replay2review();
                        r2.CHILDMEMBERNAME=csp.getUSERNAME();
                        r2.REPLAYCOMMENT=huifu_con.getText().toString();
                        r2.REPLAYTIME=date;
                        huifu_con.setText("");
                        CommentListActivity.listpl.get(position).replay2review.add(r2);
                        Dliuyan_num.setText(CommentListActivity.listpl.get(position).replay2review.size()+"");
                        Rinit();
                        break;
                    case 3:
                        wd.dismiss();
                        android.widget.Toast.makeText(CommentListDetailsActivity.this, "点赞失败!", android.widget.Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        wd.dismiss();
                        android.widget.Toast.makeText(CommentListDetailsActivity.this, "差评失败!", android.widget.Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        android.widget.Toast.makeText(CommentListDetailsActivity.this, "回复失败!", android.widget.Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        init();
    }
    public void init(){
        b_topname.setText("详情");
        if(!CommentListActivity.listpl.get(position).ICONPATH.equals("")){
            commD_img.setImageBitmap(decodeBitmap(Environment.getExternalStorageDirectory() + "/Credit/cache/"+((CommentListActivity.listpl.get(position).ICONPATH).substring(CommentListActivity.listpl.get(position).ICONPATH.length() - 20, CommentListActivity.listpl.get(position).ICONPATH.length()-5)).replaceAll("\\/", "_")+".jpg",35,35));
        }else{
            commD_img.setImageResource(R.mipmap.me_icon02);
        }
        commD_name.setText(CommentListActivity.listpl.get(position).MEMBERNAME);
        commD_time.setText(CommentListActivity.listpl.get(position).CREATETIME);
        commD_cont.setText(CommentListActivity.listpl.get(position).CONTENT);

        plD_good_num.setText(CommentListActivity.listpl.get(position).SUCCESSQTY+"");
        Dnogood_num.setText(CommentListActivity.listpl.get(position).FAILEDQTY+"");
        Dliuyan_num.setText(CommentListActivity.listpl.get(position).replay2review.size()+"");

        if(CommentListActivity.listpl.get(position).ISSUCCESS.equals("0")){//是否点赞0=no 1=yes
            plD_good.setVisibility(View.VISIBLE);
            plD_alreadgood.setVisibility(View.GONE);
        }else{
            plD_good.setVisibility(View.GONE);
            plD_alreadgood.setVisibility(View.VISIBLE);
        }
        if(CommentListActivity.listpl.get(position).ISFAILED.equals("0")){//是否吐槽0=no 1=yes
            Dnogood.setVisibility(View.VISIBLE);
            Dalreadynogood.setVisibility(View.GONE);
        }else{
            Dnogood.setVisibility(View.GONE);
            Dalreadynogood.setVisibility(View.VISIBLE);
        }
        /**
         * 好评icon点击事件
         */
        if(plD_alreadgood.getVisibility()==View.VISIBLE){
            plD_alreadgood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    plD_alreadgood.setVisibility(View.GONE);
                    plD_good.setVisibility(View.VISIBLE);
                    plD_good_num.setText(Integer.parseInt(CommentListActivity.listpl.get(position).SUCCESSQTY.trim())-1+"");
                }
            });
            plD_good.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    plD_good.setVisibility(View.GONE);
                    plD_alreadgood.setVisibility(View.VISIBLE);
                    plD_good_num.setText(Integer.parseInt(CommentListActivity.listpl.get(position).SUCCESSQTY.trim())+"");
                }
            });
        }else{
            plD_alreadgood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    plD_alreadgood.setVisibility(View.GONE);
                    plD_good.setVisibility(View.VISIBLE);
                    plD_good_num.setText(Integer.parseInt(CommentListActivity.listpl.get(position).SUCCESSQTY.trim())+"");
                }
            });
            plD_good.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    plD_good.setVisibility(View.GONE);
                    plD_alreadgood.setVisibility(View.VISIBLE);
                    plD_good_num.setText(Integer.parseInt(CommentListActivity.listpl.get(position).SUCCESSQTY.trim())+1+"");
                }
            });
        }

        /**
         * 差评icon点击事件
         */
        if(Dalreadynogood.getVisibility()==View.VISIBLE){
            Dalreadynogood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dalreadynogood.setVisibility(View.GONE);
                    Dnogood.setVisibility(View.VISIBLE);
                    Dnogood_num.setText(Integer.parseInt(CommentListActivity.listpl.get(position).FAILEDQTY.trim())-1+"");
                }
            });
            Dnogood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dnogood.setVisibility(View.GONE);
                    Dalreadynogood.setVisibility(View.VISIBLE);
                    Dnogood_num.setText(Integer.parseInt(CommentListActivity.listpl.get(position).FAILEDQTY.trim())+"");
                }
            });
        }else{
            Dalreadynogood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dalreadynogood.setVisibility(View.GONE);
                    Dnogood.setVisibility(View.VISIBLE);
                    Dnogood_num.setText(Integer.parseInt(CommentListActivity.listpl.get(position).FAILEDQTY.trim())+"");
                }
            });
            Dnogood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dnogood.setVisibility(View.GONE);
                    Dalreadynogood.setVisibility(View.VISIBLE);
                    Dnogood_num.setText(Integer.parseInt(CommentListActivity.listpl.get(position).FAILEDQTY.trim())+1+"");
                }
            });
        }


        Rinit();

        huifu_btn.setOnClickListener(listener);
        b_return.setOnClickListener(listener);
    }
    public void Rinit(){
        if(CommentListActivity.listpl.get(position).replay2review!=null){
            Commment_ItemlistAdapter adapter=new Commment_ItemlistAdapter(CommentListDetailsActivity.this,CommentListActivity.listpl.get(position).replay2review);
            commplD_list.setAdapter(adapter);
        }
    }
    View.OnClickListener listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.b_return://返回键
                    HttpInit();
                    break;
                case R.id.Dhuifu_btn://发送键
                    if(!huifu_con.getText().toString().equals("")){
                        String KeyNo= DataManager.allcountsList.get(0).EnterAddtionID;
                        String token = SearchFirmActivty.MD5s(KeyNo + deviceId);
                        GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.HHOMM, RequestMethod.GET);
                        request14.add("KeyNo",KeyNo);
                        request14.add("token",token);
                        request14.add("deviceId",deviceId);
                        request14.add("content",huifu_con.getText().toString());
                        request14.add("memberPid",uid);//点评其他评论,父评论用户ID
                        request14.add("pid",pid);//点评其他评论,父评论ID
                        request14.add("memberId",csp.getID());
                        CallServer.getInstance().add(CommentListDetailsActivity.this, request14, MyhttpCallBack.getInstance(), 0x205, true, false, true);
                    }else{
                        android.widget.Toast.makeText(CommentListDetailsActivity.this, "回复内容不能为空", android.widget.Toast.LENGTH_SHORT).show();

                    }
                    break;
            }
        }
    };

    public void HttpInit(){
        S=Integer.parseInt(CommentListActivity.listpl.get(position).SUCCESSQTY.trim());//原来点赞数值
        N=Integer.parseInt(plD_good_num.getText().toString());//当前点赞数值
        So=Integer.parseInt(CommentListActivity.listpl.get(position).FAILEDQTY.trim());//原来差评数值
        No=Integer.parseInt(Dnogood_num.getText().toString());//当前差评数值
        if(N!=S){//当当前点赞数值等于原来所赋值的数值，差评数量不变，则只执行点赞请求
//            pd.show();
            GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.ZZOMM, RequestMethod.GET);
            request14.add("KeyNo",KeyNo);
            request14.add("token",token);
            request14.add("deviceId",deviceId);
            request14.add("memberId",csp.getID());
            if(N>S){//当当前点赞数值大于原来所赋值的数值，则为点赞+1
                CommentListActivity.listpl.get(position).ISSUCCESS="1";
                request14.add("opeType","0");
            }else{
                CommentListActivity.listpl.get(position).ISSUCCESS="0";
                request14.add("opeType","1");
            }
            CallServer.getInstance().add(CommentListDetailsActivity.this, request14, MyhttpCallBack.getInstance(), 0x202, true, false, true);
            CommentListActivity.listpl.get(position).SUCCESSQTY=N+"";
        }
        if(No!=So) {//当当前差评数值等于原来所赋值的数值，好评数量不变，则只执行差评请求
            GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.NNOMM, RequestMethod.GET);
            request14.add("KeyNo",KeyNo);
            request14.add("token",token);
            request14.add("deviceId",deviceId);
            request14.add("memberId",csp.getID());
            if(No>So){//当当前差评数值大于原来所赋值的数值，则为差评+
                CommentListActivity.listpl.get(position).ISFAILED="1";
                request14.add("opeType","0");
            }else{
                CommentListActivity.listpl.get(position).ISFAILED="0";
                request14.add("opeType","1");
            }
            CallServer.getInstance().add(CommentListDetailsActivity.this, request14, MyhttpCallBack.getInstance(), 0x203, true, false, true);
            CommentListActivity.listpl.get(position).FAILEDQTY=No+"";
        }
        if(N==S && No==So){
        CommentListDetailsActivity.this.finish();
        }else{
            Intent i=new Intent();
            setResult(0, i);
            finish();
        }
    }
}
