package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;

import java.util.List;


/**
 * Created by alucard on 2016-05-25.
 */
public class Admin_Adapter extends BaseAdapter {
    Context context;
    List<DataManager.administraton> adminList;//行政许可信息
    List<DataManager.admin_other> otherList;//空其它信息数据

    public Admin_Adapter(Context context, List<DataManager.administraton> adminList,List<DataManager.admin_other> otherList) {
        this.context = context;
        this.adminList = adminList;
        this.otherList=otherList;//空其它信息数据
    }

    @Override
    public int getCount() {
        if(adminList!=null){
            return adminList.size();
        }else {
            return otherList.size();
        }
    }

    @Override
    public Object getItem(int position) {

        if(adminList!=null){
            return adminList.get(position);
        }else {
            return otherList.get(position);
        }


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

            vh.apripid = (TextView) convertView.findViewById(R.id.apripid);
            vh.aname = (TextView) convertView.findViewById(R.id.aname);
            vh.ano = (TextView) convertView.findViewById(R.id.ano);
            vh.adate = (TextView) convertView.findViewById(R.id.adate);
            vh.aoffice = (TextView) convertView.findViewById(R.id.aoffice);
            vh.atime = (TextView) convertView.findViewById(R.id.atime);

            vh.apripid_tit = (TextView) convertView.findViewById(R.id.apripid_tit);
            vh.aname_tit = (TextView) convertView.findViewById(R.id.aname_tit);
            vh.ano_tit = (TextView) convertView.findViewById(R.id.ano_tit);
            vh.atime_tit = (TextView) convertView.findViewById(R.id.atime_tit);
            vh.aoffice_tit = (TextView) convertView.findViewById(R.id.aoffice_tit);
            vh.contetns1 = (TextView) convertView.findViewById(R.id.contetns1);

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        if(adminList!=null) {
            DataManager.administraton temp_admin = adminList.get(position);
            vh.apripid.setText(temp_admin.PRIPID);
            vh.aname.setText(temp_admin.LICNAME);
            vh.ano.setText(temp_admin.LICNO);
            vh.atime.setText(temp_admin.VALFROM);
            vh.aoffice.setText(temp_admin.LICANTH);
            vh.adate.setText(temp_admin.VALTO);
        }

        if(otherList!=null) {
            DataManager.admin_other temp_admin = otherList.get(position);
            vh.apripid_tit.setText("许可文件名称");
            vh.aname_tit.setText("许可证号");
            vh.ano_tit.setText("有效期");
            vh.atime_tit.setText("详情");
            vh.aoffice_tit.setText("登记机关");
            vh.contetns1.setText("发证日期");

            vh.apripid.setText(temp_admin.LICNAME_CN);
            vh.aname.setText(temp_admin.LICNO);
            vh.ano.setText(temp_admin.VALFROM+"至"+temp_admin.VALTO);
            vh.atime.setText(temp_admin.LICITEM);
            vh.aoffice.setText(temp_admin.LICANTH);
            vh.adate.setText("");

        }
        return convertView;
    }

    class ViewHolder {
        TextView aname;
        TextView ano;
        TextView adate;
        TextView aoffice;
        TextView atime;
        TextView apripid;

        TextView apripid_tit;
        TextView aname_tit;
        TextView ano_tit;
        TextView atime_tit;
        TextView aoffice_tit;
        TextView contetns1;

    }

}
