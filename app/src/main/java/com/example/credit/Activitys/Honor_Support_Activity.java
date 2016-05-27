package com.example.credit.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.credit.Adapters.Honor_CAdapter;
import com.example.credit.Adapters.Support_CAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.MyListView;
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

    @ViewInject(R.id.hs_sc)
    ScrollView hs_sc;

    @ViewInject(R.id.hListView1)
    MyListView hListView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honor_support);
        ViewUtils.inject(this);
        hs_sc.smoothScrollTo(0,20);
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
                Honor_CAdapter hcadapter1=new Honor_CAdapter(Honor_Support_Activity.this, DataManager.honorInfoList);
                hListView1.setAdapter(hcadapter1);
                break;
            case 2:
                sh_tit.setText("企业扶持");
               Support_CAdapter hcadapter2=new Support_CAdapter(Honor_Support_Activity.this, DataManager.supportInfoList);
                hListView1.setAdapter(hcadapter2);
                break;
        }


    }

}
