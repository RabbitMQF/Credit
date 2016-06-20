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
 *著作权主界面
 */
public class CopyrightActivity extends BaseActivity {
    @ViewInject(R.id.b_return)
    ImageView b_return;
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
        final List<String> list = new ArrayList<>();
        if(DataManager.copyrightInfoeList!=null && DataManager.copyrightInfoeList.size()>0){
            for(DataManager.copyrightInfo p:DataManager.copyrightInfoeList){
                if(p.WORKNAME!=null){
                    if(!p.WORKCLASS.equals("")){
                        list.add("【"+p.WORKCLASS+"】"+p.WORKNAME);
                    }else{
                        list.add("【其他】"+p.WORKNAME);
                    }

                }
                if(p.SOFTWARENAME!=null){
                    list.add(p.SOFTWARENAME);//软件
                }
            }
        }
        CC_List_itemAdapter adapter = new CC_List_itemAdapter(CopyrightActivity.this, list,"copyright",null);
        ccListView1.setAdapter(adapter);
        ccListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(CopyrightActivity.this, Public_Detail_ctivity.class);
                i.putExtra("position",position);
                i.putExtra("type",list.get(position));
                i.putExtra("state","copyright");
                startActivity(i);
                overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
            }
        });
    }
}
