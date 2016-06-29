package com.example.credit.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.credit.Adapters.CommmentAdapter;
import com.example.credit.Adapters.Commment_ItemlistAdapter;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.MyListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论列表详情界面
 */
public class CommentListDetailsActivity extends BaseActivity {
    @ViewInject(R.id.b_topname)
    TextView b_topname;
    @ViewInject(R.id.b_return)
    LinearLayout b_return;

    @ViewInject(R.id.commD_img)
    ImageView commD_img;//评论人头像
    @ViewInject(R.id.commD_name)
    TextView commD_name;//评论人名字
    @ViewInject(R.id.commD_time)
    TextView commD_time;//评论时间
    @ViewInject(R.id.commD_cont)
    TextView commD_cont;//评论内容

    @ViewInject(R.id.plD_alreadgood)
    ImageView plD_alreadgood;//好评红icon
    @ViewInject(R.id.plD_good)
    ImageView plD_good;//好评灰icon
    @ViewInject(R.id.plD_good_num)
    TextView plD_good_num;//好评数量

    @ViewInject(R.id.Dalreadynogood)
    ImageView Dalreadynogood;//差评黑icon
    @ViewInject(R.id.Dnogood)
    ImageView Dnogood;//差评灰icon
    @ViewInject(R.id.Dnogood_num)
    TextView Dnogood_num;//差评数量

    @ViewInject(R.id.Dliuyan)
    ImageView Dliuyan;//留言icon
    @ViewInject(R.id.Dliuyan_num)
    TextView Dliuyan_num;//留言数量

    @ViewInject(R.id.commplD_list)
    MyListView commplD_list;//子回复列表

    @ViewInject(R.id.Dhuifu)
    LinearLayout huifu;//回复布局
    @ViewInject(R.id.Dhuifu_con)
    EditText huifu_con;//回复内容
    @ViewInject(R.id.Dhuifu_btn)
    TextView huifu_btn;//回复按钮

    int position;//下标
    public static List<DataManager.Userreview> UserreviewListItem= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list_details);
        ViewUtils.inject(this);
        Intent i=getIntent();
        position=i.getIntExtra("position",0);
        init();
    }
    public void init(){
        b_topname.setText("详情");

        commD_name.setText(DataManager.UserreviewList.get(position).MEMBERNAME);
        commD_time.setText(DataManager.UserreviewList.get(position).CREATETIME);
        commD_cont.setText(DataManager.UserreviewList.get(position).CONTENT);

        plD_good_num.setText(DataManager.UserreviewList.get(position).SUCCESSQTY+"");
        Dnogood_num.setText(DataManager.UserreviewList.get(position).FAILEDQTY+"");
        Dliuyan_num.setText(DataManager.UserreviewList.get(position).replay2review.size()+"");

        if(DataManager.UserreviewList.get(position).ISSUCCESS.equals("0")){//是否点赞
            plD_good.setVisibility(View.VISIBLE);
            plD_alreadgood.setVisibility(View.GONE);
        }else{
            plD_good.setVisibility(View.GONE);
            plD_alreadgood.setVisibility(View.VISIBLE);
        }
        if(DataManager.UserreviewList.get(position).ISFAILED.equals("0")){//是否吐槽
            Dnogood.setVisibility(View.VISIBLE);
            Dalreadynogood.setVisibility(View.GONE);
        }else{
            Dnogood.setVisibility(View.GONE);
            Dalreadynogood.setVisibility(View.VISIBLE);
        }
     /**
       * 好评icon点击事件
       */
        if(plD_alreadgood.getVisibility()==View.VISIBLE){
            plD_alreadgood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    plD_alreadgood.setVisibility(View.GONE);
                    plD_good.setVisibility(View.VISIBLE);
                    plD_good_num.setText(Integer.parseInt(DataManager.UserreviewList.get(position).SUCCESSQTY.trim())-1+"");
                }
            });
            plD_good.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    plD_good.setVisibility(View.GONE);
                    plD_alreadgood.setVisibility(View.VISIBLE);
                    plD_good_num.setText(Integer.parseInt(DataManager.UserreviewList.get(position).SUCCESSQTY.trim())+"");
                }
            });
        }else{
            plD_alreadgood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    plD_alreadgood.setVisibility(View.GONE);
                    plD_good.setVisibility(View.VISIBLE);
                    plD_good_num.setText(Integer.parseInt(DataManager.UserreviewList.get(position).SUCCESSQTY.trim())+"");
                }
            });
            plD_good.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    plD_good.setVisibility(View.GONE);
                    plD_alreadgood.setVisibility(View.VISIBLE);
                    plD_good_num.setText(Integer.parseInt(DataManager.UserreviewList.get(position).SUCCESSQTY.trim())+1+"");
                }
            });
        }

        /**
         * 差评icon点击事件
         */
        if(Dalreadynogood.getVisibility()==View.VISIBLE){
            Dalreadynogood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dalreadynogood.setVisibility(View.GONE);
                    Dnogood.setVisibility(View.VISIBLE);
                    Dnogood_num.setText(Integer.parseInt(DataManager.UserreviewList.get(position).FAILEDQTY.trim())-1+"");
                }
            });
            Dnogood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dnogood.setVisibility(View.GONE);
                    Dalreadynogood.setVisibility(View.VISIBLE);
                    Dnogood_num.setText(Integer.parseInt(DataManager.UserreviewList.get(position).FAILEDQTY.trim())+"");
                }
            });
        }else{
            Dalreadynogood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dalreadynogood.setVisibility(View.GONE);
                    Dnogood.setVisibility(View.VISIBLE);
                    Dnogood_num.setText(Integer.parseInt(DataManager.UserreviewList.get(position).FAILEDQTY.trim())+"");
                }
            });
            Dnogood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dnogood.setVisibility(View.GONE);
                    Dalreadynogood.setVisibility(View.VISIBLE);
                    Dnogood_num.setText(Integer.parseInt(DataManager.UserreviewList.get(position).FAILEDQTY.trim())+1+"");
                }
            });
        }


        DataManager.replay2reviewList=DataManager.UserreviewList.get(position).replay2review;//子回复list
        if(DataManager.replay2reviewList!=null){
            Commment_ItemlistAdapter adapter=new Commment_ItemlistAdapter(CommentListDetailsActivity.this,DataManager.replay2reviewList);
            commplD_list.setAdapter(adapter);
        }

        huifu_btn.setOnClickListener(listener);
        b_return.setOnClickListener(listener);
    }

    View.OnClickListener listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.b_return://返回键
                    finish();
                    break;
                case R.id.Dhuifu_btn://发送键
                    break;
            }
        }
    };
}
