package com.example.credit.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.credit.Adapters.CC_List_itemAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 专利信息主界面
 */
public class PatentActivity extends BaseActivity {
    @ViewInject(R.id.pa_return)
    ImageView pa_return;
    @ViewInject(R.id.pa_topname)
    TextView pa_topname;

    @ViewInject(R.id.paListView1)
    ListView paListView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patent);
        ViewUtils.inject(this);
        pa_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        List<String> list = Arrays.asList(getResources().getStringArray(R.array.judicial));
        CC_List_itemAdapter adapter = new CC_List_itemAdapter(PatentActivity.this, list, "patent");
        paListView1.setAdapter(adapter);
        paListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(PatentActivity.this, Public_Detail_ctivity.class);
                i.putExtra("position", position);
                startActivity(i);
                overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
            }
        });
    }

    public void init() {
        List<DataManager.patentInfo> list = new ArrayList<>();
        DataManager.patentInfo p1 = new DataManager.patentInfo();
        p1.patentName="标牌(企业征信11315";
        p1.applyNum="";
        p1.applyDate="";
        p1.applyPublishNum="";
        p1.applyPublishDate="";
        p1.inventor="";
        p1.type="";
    }
}
