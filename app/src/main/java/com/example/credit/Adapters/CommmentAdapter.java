package com.example.credit.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Activitys.ToCommentActivity;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.MyListView;
import com.example.credit.Views.RoundImageView;

import java.util.List;

public class CommmentAdapter extends BaseAdapter {
    private Context context;
    private List<DataManager.Userreview> list;
    ViewHolder vh = null;
    int sum=0;
    public CommmentAdapter(Context context, List<DataManager.Userreview> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_comment_list_item, null);
            vh = new ViewHolder();
            vh.comm_img=(RoundImageView) view.findViewById(R.id.comm_img);
            vh.comm_name=(TextView) view.findViewById(R.id.comm_name);
            vh.comm_time=(TextView) view.findViewById(R.id.comm_time);
            vh.comm_cont=(TextView) view.findViewById(R.id.comm_cont);
            vh.pl_alreadgood=(ImageView) view.findViewById(R.id.pl_alreadgood);
            vh.pl_good=(ImageView) view.findViewById(R.id.pl_good);
            vh.pl_good_num=(TextView) view.findViewById(R.id.pl_good_num);
            vh.nogood=(ImageView) view.findViewById(R.id.nogood);
            vh.alreadynogood=(ImageView) view.findViewById(R.id.alreadynogood);
            vh.nogood_num=(TextView) view.findViewById(R.id.nogood_num);
            vh.liuyan=(ImageView) view.findViewById(R.id.liuyan);
            vh.liuyan_num=(TextView) view.findViewById(R.id.liuyan_num);
            vh.huifu=(LinearLayout) view.findViewById(R.id.huifu);
            vh.huifu_con=(EditText) view.findViewById(R.id.huifu_con);
            vh.commpl_list=(MyListView) view.findViewById(R.id.commpl_list);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.comm_name.setText(list.get(position).MEMBERNAME);
        vh.comm_time.setText(list.get(position).CREATETIME);
        vh.comm_cont.setText(list.get(position).CONTENT);
        vh.nogood_num.setText(list.get(position).FAILEDQTY);
        vh.pl_good_num.setText(list.get(position).SUCCESSQTY);

String s=list.get(position).ISSUCCESS;
        String s2=list.get(position).ISFAILED;

        if(list.get(position).ISSUCCESS.equals("0")){//是否点赞
            vh.pl_good.setVisibility(View.VISIBLE);
            vh.pl_alreadgood.setVisibility(View.GONE);
        }else{
            vh.pl_good.setVisibility(View.GONE);
            vh.pl_alreadgood.setVisibility(View.VISIBLE);
        }
        if(list.get(position).ISFAILED.equals("0")){//是否吐槽
            vh.nogood.setVisibility(View.VISIBLE);
            vh.alreadynogood.setVisibility(View.GONE);
        }else{
            vh.nogood.setVisibility(View.GONE);
            vh.alreadynogood.setVisibility(View.VISIBLE);
        }
//        vh.liuyan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(vh.huifu.getVisibility()==View.VISIBLE){
//                    vh.huifu.setVisibility(View.GONE);
//                }else{
//                    vh.huifu.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//        vh.pl_alreadgood.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                vh.pl_alreadgood.setVisibility(View.GONE);
//                vh.pl_good.setVisibility(View.VISIBLE);
//            }
//        });
//        vh.pl_good.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                vh.pl_good.setVisibility(View.GONE);
//                vh.pl_alreadgood.setVisibility(View.VISIBLE);
//            }
//        });
        DataManager.replay2reviewList=list.get(position).replay2review;
        if(DataManager.replay2reviewList!=null){
            vh.liuyan_num.setText(DataManager.replay2reviewList.size()+"");
            Commment_ItemlistAdapter adapter=new Commment_ItemlistAdapter(context,DataManager.replay2reviewList);
            vh.commpl_list.setAdapter(adapter);
        }
//        if(list.get(position).ISFAILED=="0"){//是否差评
//            vh.pl_good.setVisibility(View.VISIBLE);
//            vh.pl_alreadgood.setVisibility(View.GONE);
//        }else{
//            vh.pl_good.setVisibility(View.GONE);
//            vh.pl_alreadgood.setVisibility(View.VISIBLE);
//
//        }
        return view;
    }

    public class ViewHolder {
        RoundImageView comm_img;//用户头像
        TextView comm_name;//用户姓名
        TextView comm_time;//评论时间
        TextView comm_cont;//评论内容
        ImageView pl_alreadgood;//已赞icon
        ImageView pl_good;//未赞icon
        TextView pl_good_num;//已赞size
        ImageView alreadynogood;//差评黑
        ImageView nogood;//差评灰
        TextView nogood_num;//差评size
        ImageView liuyan;//留言
        TextView liuyan_num;//留言size
        LinearLayout huifu;//留言输入框
        EditText huifu_con;//留言内容
        MyListView commpl_list;
    }
}
