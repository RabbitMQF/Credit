package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Dialogs.WaitDialog;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.ContainsEmojiEditText;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.URLconstant;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

/**
 * 提交评论界面
 */
public class ToCommentActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;

    public static WaitDialog wd;
    @ViewInject(R.id.To_details)
    ContainsEmojiEditText To_details;
    @ViewInject(R.id.To_details_num)
    TextView To_details_num;
    @ViewInject(R.id.To_btn)
    LinearLayout To_btn;
    String deviceId;
    CreditSharePreferences csp;
    public static Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_comment);
        ViewUtils.inject(this);
        wd=new WaitDialog(this);
        csp=CreditSharePreferences.getLifeSharedPreferences();
        Build bd = new Build();
        deviceId=bd.MODEL;//设备ID
        init();

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        Intent i=new Intent();
                        setResult(1, i);
                        finish();
                        break;
                    case 2:
                        android.widget.Toast.makeText(ToCommentActivity.this, "发表评论失败!", android.widget.Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }
    public void init(){
        b_topname.setText("我要评论");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        To_details.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    To_details_num.setText("您还可以输入300个字");
                }else{
                    int num=300-s.length();
                    To_details_num.setText("您还可以输入"+num+"个字");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==300){
                    android.widget.Toast.makeText(ToCommentActivity.this, "您输入的字数已经达到300了", android.widget.Toast.LENGTH_SHORT).show();
                }
            }
        });

        To_btn.setOnClickListener(listener);
    }
    View.OnClickListener listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.To_btn:
                    if(!To_details.getText().toString().equals("")){
                        wd.show();
                        String KeyNo= DataManager.allcountsList.get(0).EnterAddtionID;
                        String token = SearchFirmActivty.MD5s(KeyNo + deviceId);
                        GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.HHOMM, RequestMethod.GET);
                        request14.add("KeyNo",KeyNo);
                        request14.add("token",token);
                        request14.add("deviceId",deviceId);
                        request14.add("content",To_details.getText().toString());
                        request14.add("memberId",csp.getID());
                        CallServer.getInstance().add(ToCommentActivity.this, request14, MyhttpCallBack.getInstance(), 0x204, true, false, true);
                    }else{
                        android.widget.Toast.makeText(ToCommentActivity.this, "发表内容不能为空", android.widget.Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

        }
    };
}
