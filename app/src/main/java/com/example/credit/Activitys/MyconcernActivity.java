package com.example.credit.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.credit.Adapters.MyCommment_listAdapter;
import com.example.credit.Adapters.Myconcer_listAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 我的关注界面
 */
public class MyconcernActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;

    @ViewInject(R.id.mycconn)
    ListView mycconn;//评论列表
    Myconcer_listAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myconcern);
        ViewUtils.inject(this);
        init();
    }
    public void init(){
        b_topname.setText("我的关注");
        b_return.setOnClickListener(listener);
        adapter=new Myconcer_listAdapter(MyconcernActivity.this, DataManager.FavotiteListS.data.AttentionList);
        mycconn.setAdapter(adapter);
    }
    View.OnClickListener listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.b_return://返回键
                    finish();
                    break;
            }
        }
    };
}
