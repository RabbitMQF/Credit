package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.CreditSharePreferences;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.URLconstant;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.RequestMethod;

import java.io.File;

public class WelcomeActivity extends Activity {
    @ViewInject(R.id.welcom)
    ImageView iv;
    @ViewInject(R.id.next)
    Button next;

    CreditSharePreferences esp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ViewUtils.inject(this);
        CreditSharePreferences.init(this);
        esp = CreditSharePreferences.getLifeSharedPreferences();

        File destDir = new File(Environment.getExternalStorageDirectory() + "/Credit");
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        initData();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
            }
        });
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Intent i=new Intent(WelcomeActivity.this,MainActivity.class);
//                startActivity(i);
//                overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);
//                finish();
//            }
//        }.start();
        Animation a= AnimationUtils.loadAnimation(this, R.anim.welcome_in);
        a.setAnimationListener(listener);
        iv.setAnimation(a);
    }

    private void initData() {

        GsonUtil NewsRequest=new GsonUtil(URLconstant.NEWSURL, RequestMethod.GET);//新闻数据
        NewsRequest.setConnectTimeout(20000);
        CallServer.getInstance().add(this,NewsRequest, MyhttpCallBack.getInstance(),0x111,true,false,true);
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
            Intent i=new Intent(WelcomeActivity.this,MainActivity.class);
            startActivity(i);
            finish();
            overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);
        }
    };
//    }
}
