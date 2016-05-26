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
 * 行政处罚主界面
 */
public class PunishActivity extends BaseActivity {
    @ViewInject(R.id.pu_return)
    ImageView pu_return;
    @ViewInject(R.id.pu_topname)
    TextView pu_topname;

    @ViewInject(R.id.puListView1)
    ListView puListView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punish);
        ViewUtils.inject(this);
        pu_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
            }
        });
        List<String> list = Arrays.asList(getResources().getStringArray(R.array.pledge));
        CC_List_itemAdapter adapter = new CC_List_itemAdapter(PunishActivity.this, list,"punish");
        puListView1.setAdapter(adapter);
        puListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(PunishActivity.this, Public_Detail_ctivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.start_tran_one, R.anim.start_tran_two);
            }
        });
    }
}
