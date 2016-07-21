package com.example.credit.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topname)
    TextView b_topname;

    @ViewInject(R.id.tListView1)
    ListView tListView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trademark);
        ViewUtils.inject(this);

        Intent i=getIntent();
        String Tname=i.getStringExtra("Tname");
        b_topname.setText(Tname);
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        List<String> list = new ArrayList<>();
        List<String> listurl = new ArrayList<>();//商标URL
        for(DataManager.trademarkInfo p:DataManager.trademarkInfoList){
            list.add(p.BRANDNAME);
            if(!p.BRANDIMG.equals("") && p.BRANDIMG!=null){
                listurl.add(p.BRANDIMG);
            }
        }
        CC_List_itemAdapter adapter = new CC_List_itemAdapter(TrademarkActivity.this, list,"trademark",listurl);
        tListView1.setAdapter(adapter);
        tListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(TrademarkActivity.this, Public_Detail_ctivity.class);
                i.putExtra("position", position);
                i.putExtra("state", "trademark");
                startActivity(i);
                overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
            }
        });
    }
}
