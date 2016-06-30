package com.example.credit.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.credit.Adapters.CommmentAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.MyListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论列表界面
 */
public class CommentListActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;
    @ViewInject(R.id.b_topY)
    TextView b_topY;
    @ViewInject(R.id.Ccomm_list)
    MyListView Ccomm_list;//评论列表
    CommmentAdapter adapter;

    public static List<DataManager.Userreview> RUserreviewList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        ViewUtils.inject(this);
        init();
    }
    public void init(){
        b_topY.setVisibility(View.VISIBLE);//显示右上角控件“发表”
        b_topname.setText("评论");
        b_return.setOnClickListener(listener);
        b_topY.setOnClickListener(listener);
        Rit();
        
        Ccomm_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(CommentListActivity.this,CommentListDetailsActivity.class);
                i.putExtra("uid",RUserreviewList.get(position).MEMBERID);
                i.putExtra("pid",RUserreviewList.get(position).COMMENTID);
                i.putExtra("position",position);
                startActivity(i);
            }
        });
    }

    View.OnClickListener listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.b_return://返回键
                    finish();
                    break;
                case R.id.b_topY://跳转发表评论界面
                    Intent i=new Intent(CommentListActivity.this,ToCommentActivity.class);
                    startActivity(i);
                    break;
            }
        }
    };

    @Override
    protected void onRestart() {
        Rit();
        super.onRestart();
    }

    public  void Rit(){
        adapter=new CommmentAdapter(CommentListActivity.this,RUserreviewList);
        Ccomm_list.setAdapter(adapter);
    }
}
