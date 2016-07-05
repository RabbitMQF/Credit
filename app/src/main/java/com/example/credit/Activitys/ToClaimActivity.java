package com.example.credit.Activitys;

import android.app.Activity;
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

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

/**
 * 企业认领界面
 */
public class ToClaimActivity extends Activity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;

    @ViewInject(R.id.claim_emils)
    EditText claim_emils;//邮箱
    @ViewInject(R.id.claim_phone)
    EditText claim_phone;//手机号
    @ViewInject(R.id.claim_details)
    EditText claim_details;//描述详情
    @ViewInject(R.id.claim_details_num)
    TextView claim_details_num;//描述详情字数
    @ViewInject(R.id.claim_fj)
    EditText claim_fj;//附件
    @ViewInject(R.id.claim_btn)
    LinearLayout claim_btn;//提交
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_claim);
        ViewUtils.inject(this);
        init();
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };

    }
    public void init(){
        b_topname.setText("企业认领");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one,R.anim.finish_tran_two);
            }
        });

        claim_details.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    claim_details_num.setText("您还可以输入300个字");
                }else{
                    int num=300-s.length();
                    claim_details_num.setText("您还可以输入"+num+"个字");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==300){
                    android.widget.Toast.makeText(ToClaimActivity.this, "您输入的字数已经达到200了", android.widget.Toast.LENGTH_SHORT).show();
                }
            }
        });

        claim_btn.setOnClickListener(listener);
    }
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.claim_btn:
                    if(claim_emils.getText().equals("")){
                        Toast.show("邮箱地址不能为空!");
                    }else if(claim_phone.getText().equals("")){
                        Toast.show("手机号码不能为空!");
                    }else if(claim_details.getText().equals("")){
                        Toast.show("描述详情不能为空!");
                    }else{
                        GsonUtil request14 = new GsonUtil(URLconstant.URLINSER + URLconstant.CLAIMURL, RequestMethod.GET);
                        request14.add("deviceId",(new Build()).MODEL);
                        request14.add("token",SearchFirmActivty.MD5s(DataManager.BaseinfoList.get(0).EnterAddtionID + (new Build()).MODEL));
                        request14.add("KeyNo",DataManager.BaseinfoList.get(0).EnterAddtionID);
                        request14.add("memberId","86D9D7F53FCA45DD93E2D83DFCA0CB42");
                        request14.add("email",claim_emils.getText().toString());
                        request14.add("description",claim_details.getText().toString());
                        request14.add("telphone",claim_phone.getText().toString());
                        request14.add("openType","0");//0为添加，1为修改
                        CallServer.getInstance().add(ToClaimActivity.this, request14, MyhttpCallBack.getInstance(), 0x301, true, false, true);

                    }
                    break;
            }
        }
    };
}
