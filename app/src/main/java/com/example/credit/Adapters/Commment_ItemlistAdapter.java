package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.RoundImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Commment_ItemlistAdapter extends BaseAdapter {
    private Context context;
    private List<DataManager.MyCommentlistr.DataBean.UserreviewBean.Replay2review> list;
    ViewHolder vh = null;
    public Commment_ItemlistAdapter(Context context, List<DataManager.MyCommentlistr.DataBean.UserreviewBean.Replay2review> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.activity_comment_list_item_item, null);
            vh = new ViewHolder();
            vh.pli_name=(TextView) view.findViewById(R.id.pli_name);
            vh.pli_time=(TextView) view.findViewById(R.id.pli_time);
            vh.pli_content=(TextView) view.findViewById(R.id.pli_content);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.pli_name.setText(list.get(position).CHILDMEMBERNAME);
        vh.pli_time.setText(list.get(position).REPLAYTIME);
        vh.pli_content.setText(list.get(position).REPLAYCOMMENT);
        return view;
    }

    public class ViewHolder {
        TextView pli_name;//用户姓名
        TextView pli_time;//评论时间
        TextView pli_content;//评论内容
    }
}
