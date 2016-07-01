package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.RoundImageView;

import java.util.List;

public class MyCommment_listAdapter extends BaseAdapter {
    private Context context;
    private List<DataManager.MyComm.DataBean.CommentListBean> list;
    ViewHolder vh = null;
    public MyCommment_listAdapter(Context context, List<DataManager.MyComm.DataBean.CommentListBean> list) {
        this.context = context;
        this.list = list;
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
            view = LayoutInflater.from(context).inflate(R.layout.activity_my_commentlist_item, null);
            vh = new ViewHolder();
            vh.mycomm_img=(RoundImageView) view.findViewById(R.id.mycomm_img);
            vh.mycomm_time=(TextView) view.findViewById(R.id.mycomm_time);
            vh.mycomm_conn=(TextView) view.findViewById(R.id.mycomm_conn);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.mycomm_time.setText(list.get(position).CREATETIME);
        vh.mycomm_conn.setText(list.get(position).CONTENT);
        return view;
    }

    public class ViewHolder {
        RoundImageView mycomm_img;//用户姓名
        TextView mycomm_time;//评论时间
        TextView mycomm_conn;//评论内容
    }
}
