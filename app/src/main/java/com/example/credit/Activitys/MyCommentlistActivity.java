package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Adapters.CommmentAdapter;
import com.example.credit.Adapters.MyCommment_listAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.MyListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MyCommentlistActivity extends Activity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topY)
    TextView b_topY;
    @ViewInject(R.id.Mycomm_list)
    MyListView Mycomm_list;//评论列表
    MyCommment_listAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_commentlist);
        ViewUtils.inject(this);
        init();
    }
    public void init(){
        b_topY.setVisibility(View.VISIBLE);//显示右上角控件“发表”
        b_topname.setText("我的评论");
        b_return.setOnClickListener(listener);
        b_topY.setOnClickListener(listener);
        adapter=new MyCommment_listAdapter(MyCommentlistActivity.this, DataManager.MyComms.data.commentList);
        Mycomm_list.setAdapter(adapter);
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
