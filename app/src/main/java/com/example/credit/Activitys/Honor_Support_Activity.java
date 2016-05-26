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
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
/**
 *荣誉信息和企业扶持界面
 */
public class Honor_Support_Activity extends BaseActivity {
    @ViewInject(R.id.h_return)
    ImageView h_return;
    @ViewInject(R.id.h_topname)
    TextView h_topname;
    @ViewInject(R.id.sh_tit)
    TextView sh_tit;

    @ViewInject(R.id.hListView1)
    ListView hListView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honor_support);
        ViewUtils.inject(this);
        h_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        Intent i=getIntent();
        int st=i.getIntExtra("st",0);
        switch (st){
            case 1:
                sh_tit.setText("荣誉信息");
                break;
            case 2:
                sh_tit.setText("企业扶持");
                break;
        }
//        Alert_Honor_CAdapter hcadapter=new Alert_Honor_CAdapter();
//        hListView1.setAdapter(hcadapter);

    }

}
