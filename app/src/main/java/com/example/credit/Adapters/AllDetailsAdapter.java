package com.example.credit.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.credit.R;

import java.util.List;

public class AllDetailsAdapter extends BaseAdapter {
    private Context context;
    private List<String> list1;
    private List<String> list2;

    public AllDetailsAdapter(Context context, List<String> list1,List<String> list2) {
        this.context = context;
        this.list1 = list1;
        this.list2 = list2;
    }

    @Override
    public int getCount() {
        return list1.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.activity_c_details_item, null);
            vh = new ViewHolder();
            vh.cdc_tv1 = (TextView) view.findViewById(R.id.cdc_tv1);
            vh.cdc_tv2 = (TextView) view.findViewById(R.id.cdc_tv2);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.cdc_tv1.setText(list2.get(position));
        vh.cdc_tv2.setText(list1.get(position));
        return view;
    }

    public class ViewHolder {
        TextView cdc_tv1;
        TextView cdc_tv2;
    }
}
