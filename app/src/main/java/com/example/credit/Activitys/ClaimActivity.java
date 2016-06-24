package com.example.credit.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 企业认领界面
 */
public class ClaimActivity extends Activity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;

    @ViewInject(R.id.ec_emils)
    TextView ec_emils;//邮箱
    @ViewInject(R.id.ec_phone)
    TextView ec_phone;//手机号
    @ViewInject(R.id.ec_details)
    TextView ec_details;//描述详情
    @ViewInject(R.id.ec_details_num)
    TextView ec_details_num;//描述详情字数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_claim);
        ViewUtils.inject(this);
        init();

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

        ec_details.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    ec_details_num.setText("您还可以输入300个字");
                }else{
                    int num=300-s.length();
                    ec_details_num.setText("您还可以输入"+num+"个字");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==300){
                    android.widget.Toast.makeText(ClaimActivity.this, "您输入的字数已经达到200了", android.widget.Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
