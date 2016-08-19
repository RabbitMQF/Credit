package com.example.credit.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.credit.Adapters.CC_List_itemAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.MyListView;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alucard on 2016-08-19.
 */

public class YearReportList extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.year_lv)
    MyListView YR_LV;

    List<String> year, year_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.year_repor_list);
        com.lidroid.xutils.ViewUtils.inject(this);
        findViewById(R.id.b_return).setOnClickListener(this);
        ((TextView) findViewById(R.id.b_topname)).setText("企业年报");
        List<String> list = new ArrayList<>();
        for(DataManager.report p:DataManager.reportList){
            list.add(p.ANCHEYEAR);
        }
        CC_List_itemAdapter adapter = new CC_List_itemAdapter(YearReportList.this, list, "企业年报", null);
        YR_LV.setAdapter(adapter);
        YR_LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(YearReportList.this,WebAutonomy.class).putExtra("KeyNo",DataManager.reportList.get(position).ANCHEID));
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_return:
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
                break;

            default:
                break;
        }
    }
}
