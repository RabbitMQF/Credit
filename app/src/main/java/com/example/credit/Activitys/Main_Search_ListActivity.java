package com.example.credit.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.credit.Adapters.CC_List_itemAdapter;
import com.example.credit.Adapters.Main_CC_List_itemAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面的【专利】，【商标】搜索结果显示界面
 */
public class Main_Search_ListActivity extends BaseActivity {
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topname)
    TextView b_topname;

    @ViewInject(R.id.tListView1)
    ListView tListView1;
    @ViewInject(R.id.tvtit)
    TextView tvtit;
    Main_CC_List_itemAdapter adapter;
    String type="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trademark);
        ViewUtils.inject(this);

        Intent i=getIntent();
        String Tname=i.getStringExtra("Tname");
        b_topname.setText(Tname);
        tvtit.setVisibility(View.GONE);
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        List<String> list = new ArrayList<>();
        List<String> listurl = new ArrayList<>();//商标URL
        if(Tname.equals("商标搜索结果")){
            for(DataManager.sb_search.DataBean.TrademarkBean p:DataManager.sb_searchS.data.trademark){
                list.add(p.BRANDNAME);
                if(!p.BRANDIMG.equals("") && p.BRANDIMG!=null){
                    listurl.add(p.BRANDIMG);
                }
            }
            adapter = new Main_CC_List_itemAdapter(Main_Search_ListActivity.this, list,"trademark",listurl);
            type="trademark";
        }else if(Tname.equals("专利搜索结果")){
            for(DataManager.zl_search.DataBean.PatentInfoBean p:DataManager.zl_searchS.data.patentInfo){
                list.add(p.PATENTNAME);
            }
            adapter= new Main_CC_List_itemAdapter(Main_Search_ListActivity.this, list, "patent",null);
            type="patent";
        }
        tListView1.setAdapter(adapter);
        tListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Main_Search_ListActivity.this, Main_Public_Detail_ctivity.class);
                i.putExtra("position", position);
                i.putExtra("state", type);
                startActivity(i);
                overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
            }
        });
    }
}
