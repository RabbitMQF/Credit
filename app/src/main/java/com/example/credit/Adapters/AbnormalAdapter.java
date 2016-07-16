package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;

import java.util.List;

public class AbnormalAdapter extends BaseAdapter {
    private Context context;
    private List<DataManager.abnormalInfo.DataBean.AbNormalBean> list;

    public AbnormalAdapter(Context context, List<DataManager.abnormalInfo.DataBean.AbNormalBean> list) {
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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder vh = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_abnormal_item, null);
            vh = new ViewHolder();
            vh.a_tv1=(TextView) view.findViewById(R.id.a_tv1);
            vh.a_tv2=(TextView) view.findViewById(R.id.a_tv2);
            vh.a_tv3=(TextView) view.findViewById(R.id.a_tv3);
            vh.a_tv4=(TextView) view.findViewById(R.id.a_tv4);
            vh.a_tv5=(TextView) view.findViewById(R.id.a_tv5);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.a_tv1.setText(list.get(position).SPECAUSE_CN);
        vh.a_tv2.setText(list.get(position).ABNTIME);
        vh.a_tv3.setText(list.get(position).REMEXCPRES_CN);
        vh.a_tv4.setText(list.get(position).REMDATE);
        vh.a_tv5.setText(list.get(position).DECORG_CN);
        return view;
    }

    public class ViewHolder {
        TextView a_tv1;
        TextView a_tv2;
        TextView a_tv3;
        TextView a_tv4;
        TextView a_tv5;
    }
}
