package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.credit.R;
import com.example.credit.Utils.CreditSharePreferences;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

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
        esp=CreditSharePreferences.getLifeSharedPreferences();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.start_tran_one,R.anim.start_tran_two);
            }
        });
        Animation a= AnimationUtils.loadAnimation(this, R.anim.welcome_tran);
        a.setAnimationListener(listener);
        iv.setAnimation(a);
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
            Intent i=new Intent(WelcomeActivity.this,MainActivity.class);
            startActivity(i);
            finish();
            overridePendingTransition(R.anim.start_tran_one,R.anim.start_tran_two);
        }
    };

}
