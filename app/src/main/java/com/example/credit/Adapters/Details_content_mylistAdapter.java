package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.MyListView;

import java.util.List;

public class Details_content_mylistAdapter extends BaseAdapter {
    private Context context;
    private List<DataManager.ChangeTime> list;

    public Details_content_mylistAdapter(Context context, List<DataManager.ChangeTime> list) {
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
            view = LayoutInflater.from(context).inflate(R.layout.activity_details_content_myitems, null);
            vh = new ViewHolder();
            vh.GSmylist_time = (TextView) view.findViewById(R.id.GSmylist_time);
            vh.GSmylist_item1 = (MyListView) view.findViewById(R.id.GSmylist_item1);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        DataManager.ChangeTime cr = list.get(position);
        vh.GSmylist_time.setText(cr.ALTDATE);
        List<DataManager.ChangeData> clist=cr.changedata;
        Details_content_mylist_ItemAdapter adapter1 = new Details_content_mylist_ItemAdapter(context, clist);
        vh.GSmylist_item1.setAdapter(adapter1);
        return view;
    }

    public class ViewHolder {
        TextView GSmylist_time;
        MyListView GSmylist_item1;
    }
}
