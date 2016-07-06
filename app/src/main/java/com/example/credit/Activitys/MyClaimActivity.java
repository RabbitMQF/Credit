package com.example.credit.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.credit.Adapters.MyClaim_listAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 我的认领界面
 */
public class MyClaimActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;

    @ViewInject(R.id.Myclaim_list)
    ListView Myclaim_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_claim);
        ViewUtils.inject(this);
        init();
    }
    public void init(){
        b_topname.setText("我的认领");
        b_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        MyClaim_listAdapter adapter=new MyClaim_listAdapter(MyClaimActivity.this, DataManager.MyClaimUtilsModel.data.Claimlist);
        Myclaim_list.setAdapter(adapter);
    }
}
