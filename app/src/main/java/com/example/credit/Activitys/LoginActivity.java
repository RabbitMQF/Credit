package com.example.credit.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 登录界面
 */
public class LoginActivity extends Activity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;

    @ViewInject(R.id.login_user)
    LinearLayout login_user;//用户名
    @ViewInject(R.id.login_pwd)
    LinearLayout login_pwd;//密码
    @ViewInject(R.id.login)
    LinearLayout login;//登录
    @ViewInject(R.id.regiest)
    LinearLayout regiest;//注册
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewUtils.inject(this);
        init();
    }
    public void init(){
        b_topname.setText("登录");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
