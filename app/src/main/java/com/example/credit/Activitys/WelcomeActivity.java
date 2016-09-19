package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MD5;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.URLconstant;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.io.File;

public class WelcomeActivity extends BaseActivity {
    @ViewInject(R.id.welcom)
    ImageView iv;
    @ViewInject(R.id.next)
    Button next;

    CreditSharePreferences esp;
    public  static Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ViewUtils.inject(this);
        CreditSharePreferences.init(this);
        esp = CreditSharePreferences.getLifeSharedPreferences();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
            }
        });
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        GsonUtil hOTClaimRequest=new GsonUtil(URLconstant.URLINSER + URLconstant.HOTSPOT, RequestMethod.GET);//最新认领
                        hOTClaimRequest.add("token", MD5.MD5s("" + new Build().MODEL));
                        hOTClaimRequest.add("KeyNo", "");
                        hOTClaimRequest.add("deviceId",(new Build()).MODEL);
                        CallServer.getInstance().add(WelcomeActivity.this,hOTClaimRequest, MyhttpCallBack.getInstance(),0x114,true,false,true);

                        GsonUtil NewClaimRequest=new GsonUtil(URLconstant.URLINSER + URLconstant.NEWCLAIM, RequestMethod.GET);//最新认领
                        CallServer.getInstance().add(WelcomeActivity.this,NewClaimRequest, MyhttpCallBack.getInstance(),0x113,true,false,true);

                        GsonUtil NewsRequest=new GsonUtil(URLconstant.URLINSER+URLconstant.NEWSURL, RequestMethod.GET);//新闻数据
                        NewsRequest.setConnectTimeout(10000);
                        NewsRequest.setReadTimeout(10000);
                        NewsRequest.add("token", MD5.MD5s("" + new Build().MODEL));
                        NewsRequest.add("KeyNo","");
                        NewsRequest.add("deviceId",(new Build()).MODEL);

                        NewsRequest.add("pageIndex",1);
                        NewsRequest.add("pageSize",5);
                        CallServer.getInstance().add(WelcomeActivity.this,NewsRequest, MyhttpCallBack.getInstance(),0x111,true,false,true);
                        break;
                    case 1:
                        GsonUtil LUNboimgRequest=new GsonUtil(URLconstant.URLINSER + URLconstant.LUNBOIMH, RequestMethod.GET);//获取轮播图
                        LUNboimgRequest.add("token", MD5.MD5s("" + new Build().MODEL));
                        LUNboimgRequest.add("KeyNo","");
                        LUNboimgRequest.add("deviceId",(new Build()).MODEL);
                        CallServer.getInstance().add(WelcomeActivity.this,LUNboimgRequest, MyhttpCallBack.getInstance(),0x112,true,false,true);
                        break;
                    case 10:
                        startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                        finish();
                        overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);
                        break;
                }
            }
        };
        Animation a= AnimationUtils.loadAnimation(this, R.anim.welcome_in);
        a.setAnimationListener(listener);
        iv.setAnimation(a);
        initDataHttp();
    }

    private void initDataHttp() {
        GsonUtil NewClaimRequest=new GsonUtil(URLconstant.URLINSER + URLconstant.NEWAPP, RequestMethod.GET);//获取最新版本
        CallServer.getInstance().add(WelcomeActivity.this,NewClaimRequest, MyhttpCallBack.getInstance(),0x110,true,false,true);
    }
    Animation.AnimationListener listener=new Animation.AnimationListener() {

        @Override
        public void onAnimationStart(Animation arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationRepeat(Animation arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationEnd(Animation arg0) {
            // TODO Auto-generated method stub
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        }
    };
//    }
}
