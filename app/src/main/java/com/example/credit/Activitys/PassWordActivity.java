package com.example.credit.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Dialogs.WaitDialog;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.CreditSharePreferences;
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
public class PassWordActivity extends BaseActivity implements View.OnClickListener {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;

    @ViewInject(R.id.R_user)
    TextView R_user;//用户名
    @ViewInject(R.id.R_pwd1)
    EditText R_pwd1;//原密码
    @ViewInject(R.id.R_pwd2)
    EditText R_pwd2;//新密码1
    @ViewInject(R.id.R_pwd3)
    EditText R_pwd3;//新密码2
    @ViewInject(R.id.RR)
    LinearLayout RR;//确认修改
    @ViewInject(R.id.pwd_c1)
    ImageView pwd_c;//密码清除按钮
    @ViewInject(R.id.pwd_c2)
    ImageView pwd_c2;//密码清除按钮
    @ViewInject(R.id.pwd_c3)
    ImageView pwd_c3;//密码清除按钮

    public static Handler handler;

    CreditSharePreferences csp;
    WaitDialog wd;
    AlertDialog.Builder builder;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        ViewUtils.inject(this);
        csp=CreditSharePreferences.getLifeSharedPreferences();
        wd=new WaitDialog(this);
        init();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        wd.dismiss();
                        csp.putLoginStatus(false);
//                        dialog.show();
                        Toast.show("修改密码成功，请重新登录！");
                        Intent i=new Intent(PassWordActivity.this,MainActivity.class);
                        startActivity(i);
//                        finish();
                        break;
                    case 2:
                        wd.dismiss();
                        Toast.show("修改密码失败！");
                        break;
                    case 3:
                        wd.dismiss();
                        Toast.show("原始密码错误！");
                        break;
                }
            }
        };
    }

    public void init() {
        builder = new AlertDialog.Builder(PassWordActivity.this);
        builder.setTitle("是否登录");
        builder.setMessage("浏览企业详情，请先登录账号。");
        builder.setPositiveButton("去登陆", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                startActivity(new Intent(PassWordActivity.this,LoginActivity.class));
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                finish();
//                startActivity(new Intent(SearchFirmActivty.this, MainActivity.class));
            }
        });
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失

        b_topname.setText("修改密码");
        R_user.setText(csp.getUSERNAME());
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RR.setOnClickListener(this);
        pwd_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                R_pwd1.setText("");
            }
        });
        pwd_c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                R_pwd2.setText("");
            }
        });
        pwd_c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                R_pwd3.setText("");
            }
        });
        R_pwd1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 0) {
                    pwd_c.setVisibility(View.VISIBLE);
                } else {
                    pwd_c.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    pwd_c.setVisibility(View.VISIBLE);
                } else {
                    pwd_c.setVisibility(View.GONE);
                }
            }
        });
        R_pwd2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 0) {
                    pwd_c2.setVisibility(View.VISIBLE);
                } else {
                    pwd_c2.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    pwd_c2.setVisibility(View.VISIBLE);
                } else {
                    pwd_c2.setVisibility(View.GONE);
                }
            }
        });
        R_pwd3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 0) {
                    pwd_c3.setVisibility(View.VISIBLE);
                } else {
                    pwd_c3.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    pwd_c3.setVisibility(View.VISIBLE);
                } else {
                    pwd_c3.setVisibility(View.GONE);
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.RR:
                if (R_pwd1.getText().length() == 0) {
                    Toast.show("原密码不能为空...");

                } else if (R_pwd2.getText().length() == 0 || R_pwd3.getText().length() == 0) {
                    Toast.show("新密码不能为空...");

                } else if (R_pwd2.getText().length() < 6 || R_pwd3.getText().length() < 6) {
                    Toast.show("新密码长度至少6位...");

                } else if (!R_pwd2.getText().toString().equals(R_pwd3.getText().toString())) {
                    Toast.show("两次新密码输入不一致...");
                } else {
                    wd.show();
                    GsonUtil LoginRequest = new GsonUtil(URLconstant.URLINSER + URLconstant.RPASSWORD, RequestMethod.GET);
                    LoginRequest.add("token", MD5.MD5s(csp.getID() + new Build().MODEL));
                    LoginRequest.add("KeyNo", csp.getID());
                    LoginRequest.add("deviceId", new Build().MODEL);
                    LoginRequest.add("username", csp.getUSERNAME());
                    LoginRequest.add("oldPassword",  R_pwd1.getText().toString());
                    LoginRequest.add("newPassword",  R_pwd3.getText().toString());
                    CallServer.getInstance().add(this, LoginRequest, MyhttpCallBack.getInstance(), 0x501, true, false, true);
                }
                break;
            default:
                break;
        }
    }
}
