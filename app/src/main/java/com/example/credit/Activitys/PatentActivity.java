package com.example.credit.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.credit.Adapters.CC_List_itemAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Utils.PullToRefreshView;
import com.example.credit.Views.MyListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 专利信息主界面
 */
public class PatentActivity extends BaseActivity {
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topname)
    TextView b_topname;

    @ViewInject(R.id.pa_sc)
    ScrollView pa_sc;
    @ViewInject(R.id.paListView1)
    MyListView paListView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patent);
        ViewUtils.inject(this);

        pa_sc.smoothScrollTo(0,20);
        Intent i=getIntent();
        String Tname=i.getStringExtra("Tname");
        b_topname.setText(Tname);
        //b_topname.setText("专利信息");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        List<String> list = new ArrayList<>();
        List<String> listurl = new ArrayList<>();
        for(DataManager.PatentInfo.DataBean.PatentInfoBean p:DataManager.PatentInfoS.data.patentInfo){
            list.add(p.PATENTNAME);
            listurl.add(p.ABSTRACTGRAPH);
        }
        CC_List_itemAdapter adapter = new CC_List_itemAdapter(PatentActivity.this, list, "patent",listurl);
        paListView1.setAdapter(adapter);
        /**
         * 详情页改H5点击事件写adapter
         */
//        paListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent i = new Intent(PatentActivity.this, Public_Detail_ctivity.class);
//                i.putExtra("position",position);
//                i.putExtra("state","patent");
//                startActivity(i);
//                overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
//            }
//        });
    }
}
