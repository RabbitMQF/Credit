package com.example.credit.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.credit.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 *守合同重信用界面
 */
public class ObeyedActivity extends BaseActivity {
    @ViewInject(R.id.o_return)
    ImageView o_return;
    @ViewInject(R.id.o_topname)
    TextView o_topname;

    @ViewInject(R.id.oListView1)
    ListView oListView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obeyed);
        ViewUtils.inject(this);
        o_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
    }
}
