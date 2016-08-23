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

public class Support_CAdapter extends BaseAdapter {
    private Context context;
    private List<DataManager.supportInfo.DataBean.AdvertisingBean> list;

    public Support_CAdapter(Context context, List<DataManager.supportInfo.DataBean.AdvertisingBean> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.activity_support_item, null);
            vh = new ViewHolder();
            vh.sitem_tv1C=(TextView) view.findViewById(R.id.sitem_tv1C);
            vh.sitem_tv2C=(TextView) view.findViewById(R.id.sitem_tv2C);
            vh.sitem_tv3C=(TextView) view.findViewById(R.id.sitem_tv3C);
            vh.sitem_tv4C=(TextView) view.findViewById(R.id.sitem_tv4C);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.sitem_tv1C.setText(list.get(position).ENJSPAMOUNT+"万元");
        vh.sitem_tv2C.setText(list.get(position).ENJSPCONTENT);
        vh.sitem_tv3C.setText(list.get(position).IMPSPDEPART);
        vh.sitem_tv4C.setText(list.get(position).IMPSPDATE);
        return view;
    }

    public class ViewHolder {
        TextView sitem_tv1C;
        TextView sitem_tv2C;
        TextView sitem_tv3C;
        TextView sitem_tv4C;
    }
}
