package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.credit.R;
import com.example.credit.Views.MyListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 预警信息子项公用界面
 */
public class Alert_CActivity extends BaseActivity {
    @ViewInject(R.id.bc_return)
    ImageView bc_return;
    @ViewInject(R.id.bc_topname)
    TextView bc_topname;

    @ViewInject(R.id.alert_title)
    TextView alert_title;

    @ViewInject(R.id.alert_lv)
    ListView alert_lv;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert__content);
        ViewUtils.inject(this);
        Intent i = getIntent();
        position = i.getIntExtra("position", 0);
        bc_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        init();
    }

    public void init() {
        String[] str = getResources().getStringArray(R.array.alert);
        alert_title.setText(str[position]);
    }


}
