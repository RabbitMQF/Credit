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
 * 商标信息主界面
 */
public class TrademarkActivity extends BaseActivity {
    @ViewInject(R.id.t_return)
    ImageView t_return;
    @ViewInject(R.id.t_topname)
    TextView t_topname;

    @ViewInject(R.id.tListView1)
    ListView tListView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trademark);
        ViewUtils.inject(this);
        t_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        List<String> list = new ArrayList<>();
        List<String> listurl = new ArrayList<>();//商标URL
        for(DataManager.trademarkInfo p:DataManager.trademarkInfoList){
            list.add(p.registeredName);
            listurl.add(p.iconUrl);
        }
        CC_List_itemAdapter adapter = new CC_List_itemAdapter(TrademarkActivity.this, list,"trademark",listurl);
        tListView1.setAdapter(adapter);
        tListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(TrademarkActivity.this, Public_Detail_ctivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
            }
        });
    }
}
