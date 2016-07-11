package com.example.credit.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class UserSetTowActivity extends Activity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;

    @ViewInject(R.id.ust1)
    TextView ust1;
    @ViewInject(R.id.ust1_et)
    EditText ust1_et;
    @ViewInject(R.id.ust1_cc)
    ImageView ust1_cc;

    @ViewInject(R.id.ust2)
    TextView ust2;
    @ViewInject(R.id.ust2_et)
    EditText ust2_et;
    @ViewInject(R.id.ust2_cc)
    ImageView ust2_cc;

    @ViewInject(R.id.ust3)
    TextView ust3;
    @ViewInject(R.id.ust3_et)
    EditText ust3_et;
    @ViewInject(R.id.ust3_cc)
    ImageView ust3_cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_set_tow);
        ViewUtils.inject(this);
        init();
    }

    public void init(){
        b_topname.setText("用户设置");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
