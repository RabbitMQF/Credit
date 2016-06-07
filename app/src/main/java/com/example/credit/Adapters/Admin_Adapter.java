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


/**
 * Created by alucard on 2016-05-25.
 */
public class Admin_Adapter extends BaseAdapter {
    Context context;
    List<DataManager.administraton> adminList;

    public Admin_Adapter(Context context, List<DataManager.administraton> adminList) {
        this.context = context;
        this.adminList = adminList;
    }

    @Override
    public int getCount() {
        return adminList.size();
    }

    @Override
    public Object getItem(int position) {
        return adminList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.admin_list_item, null);
            vh = new ViewHolder();
            vh.title = (TextView) convertView.findViewById(R.id.title);
            vh.aname = (TextView) convertView.findViewById(R.id.aname);
            vh.ano = (TextView) convertView.findViewById(R.id.ano);
            vh.adate = (TextView) convertView.findViewById(R.id.adate);
            vh.aoffice = (TextView) convertView.findViewById(R.id.aoffice);
            vh.atime = (TextView) convertView.findViewById(R.id.atime);
            vh.apripid = (TextView) convertView.findViewById(R.id.apripid);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        DataManager.administraton temp_admin = adminList.get(position);
        vh.title.setText("行政许可信息");
        vh.aname.setText(temp_admin.LICNAME);
        vh.ano.setText(temp_admin.LICNO);
        vh.atime.setText(temp_admin.VALFROM);
        vh.aoffice.setText(temp_admin.LICANTH);
        vh.adate.setText(temp_admin.VALTO);
        vh.apripid.setText(temp_admin.PRIPID);

        return convertView;
    }

    class ViewHolder {
        TextView title;
        TextView aname;
        TextView ano;
        TextView adate;
        TextView aoffice;
        TextView atime;
        TextView apripid;

    }

}
