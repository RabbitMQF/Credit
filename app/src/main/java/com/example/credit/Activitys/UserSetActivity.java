package com.example.credit.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.credit.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class UserSetActivity extends Activity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_set);
        ViewUtils.inject(this);
    }

    public void init(){
        b_topname.setText("用户设置");
    }
}
