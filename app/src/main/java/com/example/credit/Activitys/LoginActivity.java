package com.example.credit.Activitys;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;

    @ViewInject(R.id.login_user)
    EditText login_user;//用户名
    @ViewInject(R.id.login_pwd)
    EditText login_pwd;//密码
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

    public void init() {
        b_topname.setText("登录");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                if (login_user.getText().length() == 0 || login_pwd.getText().length() == 0) {
                    Toast.show("账号密码不能为空...");
                } else if (login_pwd.getText().length() < 6) {
                    Toast.show("密码长度至少6位...");

                } else {
                    Toast.show("通过判定");
                    GsonUtil LoginRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.USERLOGIN, RequestMethod.GET);
                    LoginRequest.add("token", MD5.MD5s(login_user.getText() + new Build().MODEL));
                    LoginRequest.add("KeyNo", login_user.getText().toString());
                    LoginRequest.add("deviceId", new Build().MODEL);
                    LoginRequest.add("password", login_pwd.getText().toString());
                    CallServer.getInstance().add(this,LoginRequest, MyhttpCallBack.getInstance(),0x999,true,false,true);
                }
                break;
            default:
                break;
        }
    }
}
