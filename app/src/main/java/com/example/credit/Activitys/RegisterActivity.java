package com.example.credit.Activitys;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Adapters.CommmentAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.Toast;
import com.example.credit.Utils.URLconstant;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yolanda.nohttp.RequestMethod;

public class RegisterActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;

    @ViewInject(R.id.Ruser)
    EditText Ruser;//用户名
    @ViewInject(R.id.Ruser_c)
    ImageView Ruser_c; //用户名 消除
    @ViewInject(R.id.Rpwd)
    EditText Rpwd;//密码
    @ViewInject(R.id.Rpwd_c)
    ImageView Rpwd_c; //密码 消除
    @ViewInject(R.id.Rrpwds)
    EditText Rrpwds;//确认密码
    @ViewInject(R.id.Rrpwds_c)
    ImageView Rrpwds_c; //确认密码 消除
    @ViewInject(R.id.regiest_ing)
    LinearLayout regiest_ing;//注册
    public static Handler handler;

    @OnClick({R.id.Ruser_c, R.id.Rpwd_c, R.id.Rrpwds_c})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Ruser_c:
                Ruser.setText("");
                break;
            case R.id.Rpwd_c:
                Rpwd.setText("");
                break;
            case R.id.Rrpwds_c:
                Rrpwds.setText("");
                break;
        }
    }

    AlertDialog.Builder builder;
    AlertDialog dialog;
    public static ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ViewUtils.inject(this);
        initDialog();
        init();

        handler = new Handler() {//注册成功
            @Override
            public void handleMessage(Message msg) {
                pd.dismiss();
                Toast.show("注册成功！");
//                dialog.show();
                finish();
                super.handleMessage(msg);
            }
        };
    }

    private void initDialog() {
        pd = new ProgressDialog(this);
        pd.setMessage("请稍后...");
        pd.setCancelable(false);
        builder = new AlertDialog.Builder(this);
        builder.setTitle("注册成功");
        builder.setMessage("已发送激活邮件至邮箱是否前往激活？");
        builder.setPositiveButton("去激活", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                Uri uri=Uri.parse("http://www.hao123.com/mail");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
    }


    public void init() {
        b_topname.setText("注册");
        b_return.setOnClickListener(listener);
        regiest_ing.setOnClickListener(listener);

        Ruser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 0) {
                    Ruser_c.setVisibility(View.VISIBLE);
                } else {
                    Ruser_c.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    Ruser_c.setVisibility(View.VISIBLE);
                } else {
                    Ruser_c.setVisibility(View.GONE);
                }
            }
        });

        Rpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 0) {
                    Rpwd_c.setVisibility(View.VISIBLE);
                } else {
                    Rpwd_c.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    Rpwd_c.setVisibility(View.VISIBLE);
                } else {
                    Rpwd_c.setVisibility(View.GONE);
                }
            }
        });

        Rrpwds.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 0) {
                    Rrpwds_c.setVisibility(View.VISIBLE);
                } else {
                    Rrpwds_c.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    Rrpwds_c.setVisibility(View.VISIBLE);
                } else {
                    Rrpwds_c.setVisibility(View.GONE);
                }
            }
        });


    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.b_return://返回键
                    finish();
                    break;
                case R.id.regiest_ing://注册按钮
                    if (!UserSetTowActivity.isEmail(Ruser.getText().toString())) {
                        Toast.show("账号邮箱格式不正确...");
                    } else if (Rpwd.getText().length() < 6 ) {
                        Toast.show("密码长度至少6位...");
                    } else if (!Rpwd.getText().toString().equals(Rrpwds.getText().toString()) ) {
                        Toast.show("两次密码输入不一致...");
                    } else {
                        pd.show();
                        GsonUtil regiestRquerst = new GsonUtil(URLconstant.URLINSER + URLconstant.USERSET, RequestMethod.GET);
                        regiestRquerst.add("token", MD5.MD5s(Ruser.getText() + new Build().MODEL));
                        regiestRquerst.add("KeyNo", Ruser.getText().toString());
                        regiestRquerst.add("deviceId", new Build().MODEL);
                        regiestRquerst.add("openType", 0);//0为注册 1为修改
                        regiestRquerst.add("password", Rrpwds.getText().toString());
                        CallServer.getInstance().add(RegisterActivity.this, regiestRquerst, MyhttpCallBack.getInstance(), 0x998, true, false, true);
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
