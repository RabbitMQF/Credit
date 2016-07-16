package com.example.credit.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.credit.Adapters.CommmentAdapter;
import com.example.credit.Adapters.Commment_ItemlistAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.MyListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
    EditText huifu_con;//回复内容
    @ViewInject(R.id.Dhuifu_btn)
    TextView huifu_btn;//回复按钮
    public static ProgressDialog pd;
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
        csp=CreditSharePreferences.getLifeSharedPreferences();
        Intent i=getIntent();
        position=i.getIntExtra("position",0);
        uid=i.getStringExtra("uid");
        pid=i.getStringExtra("pid");
        Build bd = new Build();
        deviceId=bd.MODEL;//设备ID
        KeyNo=DataManager.UserreviewList.get(position).COMMENTID;
        token = SearchFirmActivty.MD5s(KeyNo + deviceId);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 0:
//                        nogoodhttp(0x2031);
                        break;
                    case 1:
                        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                        String  date =sDateFormat.format(new java.util.Date());
                        DataManager.Replay2review r2=new DataManager.Replay2review();
                        r2.CHILDMEMBERNAME=DataManager.UserreviewList.get(position).MEMBERNAME;
                        r2.REPLAYCOMMENT=huifu_con.getText().toString();
                        r2.REPLAYTIME=date;
                        huifu_con.setText("");
                        DataManager.replay2reviewList.add(r2);
                        Dliuyan_num.setText(DataManager.UserreviewList.get(position).replay2review.size()+"");
                        Rinit();
                        break;
                    case 2:
                        /**
                         * 重新查一遍评论
                         */
                        GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.COMM, RequestMethod.GET);
                        request14.add("deviceId", (new Build()).MODEL);
                        request14.add("token", SearchFirmActivty.MD5s(DataManager.BaseinfoList.get(0).EnterAddtionID + (new Build()).MODEL));
                        request14.add("KeyNo", DataManager.BaseinfoList.get(0).EnterAddtionID);
                        request14.add("memberId", csp.getID());
                        CallServer.getInstance().add(CommentListDetailsActivity.this, request14, MyhttpCallBack.getInstance(), 0x20111, true, false, true);
                        break;
                    case 3:
                        pd.dismiss();
                        android.widget.Toast.makeText(CommentListDetailsActivity.this, "点赞失败!", android.widget.Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        pd.dismiss();
                        android.widget.Toast.makeText(CommentListDetailsActivity.this, "差评失败!", android.widget.Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        android.widget.Toast.makeText(CommentListDetailsActivity.this, "回复失败!", android.widget.Toast.LENGTH_SHORT).show();
                        break;
                    case 21://评论
                        CommentListActivity.RUserreviewList= DataManager.UserreviewList;
                        finish();
//                        Intent i21=new Intent(CommentListDetailsActivity.this,CommentListActivity.class);
//                        startActivity(i21);
                        break;
                }
            }
        };
        init();
    }
    public void init(){
        b_topname.setText("详情");

        commD_name.setText(DataManager.UserreviewList.get(position).MEMBERNAME);
        commD_time.setText(DataManager.UserreviewList.get(position).CREATETIME);
        commD_cont.setText(DataManager.UserreviewList.get(position).CONTENT);

        plD_good_num.setText(DataManager.UserreviewList.get(position).SUCCESSQTY+"");
        Dnogood_num.setText(DataManager.UserreviewList.get(position).FAILEDQTY+"");
        Dliuyan_num.setText(DataManager.UserreviewList.get(position).replay2review.size()+"");

        if(DataManager.UserreviewList.get(position).ISSUCCESS.equals("0")){//是否点赞
            plD_good.setVisibility(View.VISIBLE);
            plD_alreadgood.setVisibility(View.GONE);
        }else{
            plD_good.setVisibility(View.GONE);
            plD_alreadgood.setVisibility(View.VISIBLE);
        }
        if(DataManager.UserreviewList.get(position).ISFAILED.equals("0")){//是否吐槽
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
                    plD_good_num.setText(Integer.parseInt(DataManager.UserreviewList.get(position).SUCCESSQTY.trim())-1+"");
                }
            });
            plD_good.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    plD_good.setVisibility(View.GONE);
                    plD_alreadgood.setVisibility(View.VISIBLE);
                    plD_good_num.setText(Integer.parseInt(DataManager.UserreviewList.get(position).SUCCESSQTY.trim())+"");
                }
            });
        }else{
            plD_alreadgood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    plD_alreadgood.setVisibility(View.GONE);
                    plD_good.setVisibility(View.VISIBLE);
                    plD_good_num.setText(Integer.parseInt(DataManager.UserreviewList.get(position).SUCCESSQTY.trim())+"");
                }
            });
            plD_good.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    plD_good.setVisibility(View.GONE);
                    plD_alreadgood.setVisibility(View.VISIBLE);
                    plD_good_num.setText(Integer.parseInt(DataManager.UserreviewList.get(position).SUCCESSQTY.trim())+1+"");
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
                    Dnogood_num.setText(Integer.parseInt(DataManager.UserreviewList.get(position).FAILEDQTY.trim())-1+"");
                }
            });
            Dnogood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dnogood.setVisibility(View.GONE);
                    Dalreadynogood.setVisibility(View.VISIBLE);
                    Dnogood_num.setText(Integer.parseInt(DataManager.UserreviewList.get(position).FAILEDQTY.trim())+"");
                }
            });
        }else{
            Dalreadynogood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dalreadynogood.setVisibility(View.GONE);
                    Dnogood.setVisibility(View.VISIBLE);
                    Dnogood_num.setText(Integer.parseInt(DataManager.UserreviewList.get(position).FAILEDQTY.trim())+"");
                }
            });
            Dnogood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dnogood.setVisibility(View.GONE);
                    Dalreadynogood.setVisibility(View.VISIBLE);
                    Dnogood_num.setText(Integer.parseInt(DataManager.UserreviewList.get(position).FAILEDQTY.trim())+1+"");
                }
            });
        }


        DataManager.replay2reviewList=DataManager.UserreviewList.get(position).replay2review;//子回复list
        Rinit();

        huifu_btn.setOnClickListener(listener);
        b_return.setOnClickListener(listener);
    }
    public void Rinit(){
        if(DataManager.replay2reviewList!=null){
            Commment_ItemlistAdapter adapter=new Commment_ItemlistAdapter(CommentListDetailsActivity.this,DataManager.replay2reviewList);
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
                        String KeyNo= DataManager.BaseinfoList.get(0).EnterAddtionID;
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
        pd=new ProgressDialog(CommentListDetailsActivity.this);
        pd.setMessage("正在加载中...");
        pd.setCancelable(false);
        S=Integer.parseInt(DataManager.UserreviewList.get(position).SUCCESSQTY.trim());//原来点赞数值
        N=Integer.parseInt(plD_good_num.getText().toString());//当前点赞数值
        So=Integer.parseInt(DataManager.UserreviewList.get(position).FAILEDQTY.trim());//原来差评数值
        No=Integer.parseInt(Dnogood_num.getText().toString());//当前差评数值
        if(N!=S){//当当前点赞数值等于原来所赋值的数值，差评数量不变，则只执行点赞请求
            pd.show();
            GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.ZZOMM, RequestMethod.GET);
            request14.add("KeyNo",KeyNo);
            request14.add("token",token);
            request14.add("deviceId",deviceId);
            request14.add("memberId",csp.getID());
            if(N>S){//当当前点赞数值大于原来所赋值的数值，则为点赞+1
                request14.add("opeType","0");
            }else{
                request14.add("opeType","1");
            }
            CallServer.getInstance().add(CommentListDetailsActivity.this, request14, MyhttpCallBack.getInstance(), 0x202, true, false, true);
        }
        if(No!=So) {//当当前差评数值等于原来所赋值的数值，好评数量不变，则只执行差评请求
            pd.show();
            GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.NNOMM, RequestMethod.GET);
            request14.add("KeyNo",KeyNo);
            request14.add("token",token);
            request14.add("deviceId",deviceId);
            request14.add("memberId",csp.getID());
            if(No>So){//当当前差评数值大于原来所赋值的数值，则为差评+1
                request14.add("opeType","0");
            }else{
                request14.add("opeType","1");
            }
            CallServer.getInstance().add(CommentListDetailsActivity.this, request14, MyhttpCallBack.getInstance(), 0x203, true, false, true);
        }
        if(N==S && No==So){
            CommentListDetailsActivity.this.finish();
        }else{
            CommentListDetailsActivity.handler.sendEmptyMessage(2);
        }
    }
}
