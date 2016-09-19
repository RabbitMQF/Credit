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

public class FeiZhiAdapter extends BaseAdapter {
    private Context context;
    private List<DataManager.GSXX.DataBean.AnnualReports> list;

    public FeiZhiAdapter(Context context, List<DataManager.GSXX.DataBean.AnnualReports> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.activity_c_details_fenzhijigou, null);
            vh = new ViewHolder();
            vh.hitem_tv1C=(TextView) view.findViewById(R.id.fz_tv1C);
            vh.hitem_tv2C=(TextView) view.findViewById(R.id.fz_tv2C);
            vh.hitem_tv3C=(TextView) view.findViewById(R.id.fz_tv3C);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.hitem_tv1C.setText(list.get(position).PRIPID);
        vh.hitem_tv2C.setText(list.get(position).BRNAME);
        vh.hitem_tv3C.setText(list.get(position).REGIDATE);
        return view;
    }

    public class ViewHolder {
        TextView hitem_tv1C;
        TextView hitem_tv2C;
        TextView hitem_tv3C;
    }
}
