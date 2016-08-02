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
 *著作权主界面
 */
public class CopyrightActivity extends BaseActivity {
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topname)
    TextView b_topname;

    @ViewInject(R.id.ccListView1)
    ListView ccListView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copyright);
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
        final List<String> list1 = new ArrayList<>();
        final List<String> list2 = new ArrayList<>();
        if(DataManager.copyrightInfoeList!=null && DataManager.copyrightInfoeList.size()>0){
            for(DataManager.copyrightInfo p:DataManager.copyrightInfoeList){
//                if(p.WORKNAME!=null){
//                    if(!p.WORKCLASS.equals("")){
//                        list1.add("【"+p.WORKCLASS+"】"+p.WORKNAME);
//                    }else{
//                        list1.add("【其他】"+p.WORKNAME);
//                    }
//                }
//                if(p.SOFTWARENAME!=null){
//                    list1.add(p.SOFTWARENAME);//软件
//                }
                if(p.WORKNAME!=null){//其他
                    list1.add(p.WORKNAME);
                }
                if(p.SOFTWARENAME!=null){//软件
                    list1.add(p.SOFTWARENAME);
                }
                if(p.REGISTERDATA!=null){//时间
                    list2.add(p.REGISTERDATA);
                }else{
                    list2.add("暂无日期");
                }
            }
        }
        CC_List_itemAdapter adapter = new CC_List_itemAdapter(CopyrightActivity.this, list1,"copyright",list2);
        ccListView1.setAdapter(adapter);
        ccListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(CopyrightActivity.this, Public_Detail_ctivity.class);
                i.putExtra("position",position);
                i.putExtra("type",DataManager.copyrightInfoeList.get(position).WORKCLASS);
                i.putExtra("state","copyright");
                startActivity(i);
                overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
            }
        });
    }
}
