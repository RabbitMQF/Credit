package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.credit.Entitys.changeResult;
import com.example.credit.R;

import java.util.List;
public class Alert_Honor_CAdapter extends BaseAdapter {
    private Context context;
    private List<changeResult> list;

    public Alert_Honor_CAdapter(Context context, List<changeResult> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.activity_honor_support_item, null);
            vh = new ViewHolder();
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        return view;
    }

    public class ViewHolder {
    }
}
