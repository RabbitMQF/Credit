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
    private List<String> list;

    public AllDetailsAdapter(Context context, List<String> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.activity_c_details_item, null);
            vh = new ViewHolder();
            vh.cdc_name = (TextView) view.findViewById(R.id.cdc_topname);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        if (position % 2 == 0) {//偶数
            vh.cdc_name.setTextColor(Color.parseColor("#B8B8B8"));
        } else {//奇数
            vh.cdc_name.setTextColor(Color.parseColor("#ffffff"));
        }
        return view;
    }

    public class ViewHolder {
        TextView cdc_name;
    }
}
