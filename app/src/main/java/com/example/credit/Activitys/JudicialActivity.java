package com.example.credit.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.credit.Adapters.CC_List_itemAdapter;
import com.example.credit.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.Arrays;
import java.util.List;

/**
 *司法信息主界面
 */
public class JudicialActivity extends BaseActivity {
    @ViewInject(R.id.j_return)
    ImageView j_return;
    @ViewInject(R.id.j_topname)
    TextView p_topname;

    @ViewInject(R.id.jListView1)
    ListView jListView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judicial);
        ViewUtils.inject(this);
        j_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        List<String> list = Arrays.asList(getResources().getStringArray(R.array.judicial));
        CC_List_itemAdapter adapter=new CC_List_itemAdapter(JudicialActivity.this,list,"judicial",null);
        jListView1.setAdapter(adapter);
        jListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(JudicialActivity.this,Public_Detail_ctivity.class);
                i.putExtra("position",position);
                startActivity(i);
                overridePendingTransition(R.anim.start_tran_one,R.anim.start_tran_two);
            }
        });
    }
}
