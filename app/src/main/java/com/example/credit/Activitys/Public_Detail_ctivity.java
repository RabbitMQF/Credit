package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.credit.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 *著作权详情界面
 */
public class Public_Detail_ctivity extends BaseActivity {
    @ViewInject(R.id.cd_return)
    ImageView cd_return;
    @ViewInject(R.id.cd_topname)
    TextView cd_topname;

    @ViewInject(R.id.cdListView1)
    ListView cdListView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_details);
        ViewUtils.inject(this);
        Intent i = getIntent();
        cd_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
    }
}
