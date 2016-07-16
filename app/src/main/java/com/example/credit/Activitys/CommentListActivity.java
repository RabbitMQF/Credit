package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.credit.Adapters.CommmentAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.URLconstant;
import com.example.credit.Views.MyListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Decoder.BASE64Decoder;

/**
 * 评论列表界面
 */
public class CommentListActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topY)
    TextView b_topY;
    @ViewInject(R.id.Ccomm_list)
    MyListView Ccomm_list;//评论列表
    CommmentAdapter adapter;
    public static Handler handler;
    CreditSharePreferences csp;
    int type;
    public static List<DataManager.Userreview> RUserreviewList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        ViewUtils.inject(this);
        Intent i=getIntent();
        type=i.getIntExtra("type",0);
        csp=CreditSharePreferences.getLifeSharedPreferences();
        init();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        RUserreviewList=DataManager.UserreviewList;
                        Rit();
                        break;
                }
            }
        };
    }
    public void init(){
        b_topY.setVisibility(View.VISIBLE);//显示右上角控件“发表”
        b_topname.setText("评论");
        b_return.setOnClickListener(listener);
        b_topY.setOnClickListener(listener);
        Rit();
        Ccomm_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(CommentListActivity.this,CommentListDetailsActivity.class);
                i.putExtra("uid",RUserreviewList.get(position).MEMBERID);
                i.putExtra("pid",RUserreviewList.get(position).COMMENTID);
                i.putExtra("position",position);
                startActivity(i);
            }
        });
    }

    View.OnClickListener listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.b_return://返回键
                    finish();
                    break;
                case R.id.b_topY://跳转发表评论界面
                    Intent i=new Intent(CommentListActivity.this,ToCommentActivity.class);
                    startActivity(i);
                    break;
            }
        }
    };

    @Override
    protected void onRestart() {
        Rit();
        super.onRestart();
    }

//    @Override
//    protected void onResume() {
//        Rit();
//        super.onResume();
//    }

    public  void Rit(){

        File file = new File(Environment.getExternalStorageDirectory() + "/Credit/cache/"+RUserreviewList.get(0).COMMENTID+".jpg");
        if (!file.exists()) {//获取本地图片路径是否存在
            for(int i=0;i<RUserreviewList.size();i++){
                try {
                    BASE64Decoder decode = new BASE64Decoder();
                    byte[] b = decode.decodeBuffer(RUserreviewList.get(i).ICONPATH);
                    System.out.println(new String(b));
                    StringBuilder str = new StringBuilder();//不建议用String
                    for (byte bs : b) {
                        str.append(Integer.toBinaryString(bs));//转换为二进制
                    }
                    //把字节数组的图片写到另一个地方
                    File apple = new File(Environment.getExternalStorageDirectory() + "/Credit/cache/"+RUserreviewList.get(i).COMMENTID+".jpg");
                    FileOutputStream fos = new FileOutputStream(apple);
                    fos.write(b);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            adapter=new CommmentAdapter(CommentListActivity.this,RUserreviewList);
            Ccomm_list.setAdapter(adapter);
        }else{
            adapter=new CommmentAdapter(CommentListActivity.this,RUserreviewList);
            Ccomm_list.setAdapter(adapter);
        }
        if(type==0){
            CompanyDetailsActivity.waitDialog.dismiss();
        }else{
            CommentListDetailsActivity.pd.dismiss();
        }
    }
    public void intiow(){
        /**
         * 重新查一遍评论
         */
        GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.COMM, RequestMethod.GET);
        request14.add("deviceId", (new Build()).MODEL);
        request14.add("token", SearchFirmActivty.MD5s(DataManager.BaseinfoList.get(0).EnterAddtionID + (new Build()).MODEL));
        request14.add("KeyNo", DataManager.BaseinfoList.get(0).EnterAddtionID);
        request14.add("memberId", csp.getID());
        CallServer.getInstance().add(CommentListActivity.this, request14, MyhttpCallBack.getInstance(), 0x20111, true, false, true);
    }
}
