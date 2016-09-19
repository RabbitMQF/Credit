package com.example.credit.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.credit.Activitys.SearchFirmActivty;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alucard on 2016-06-03.
 */
public class Citys_Adapter extends BaseAdapter{
    Context context;
    List<DataManager.citys> citysList;
    List<String> list=new ArrayList<>();
    ArrayAdapter adapter;


    public Citys_Adapter(Context context, List<DataManager.citys> citysList) {
        this.context = context;
        this.citysList = citysList;

    }

    @Override
    public int getCount() {
        return citysList.size();
    }

    @Override
    public Object getItem(int position) {
        return citysList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh=null;
        if(convertView==null){
            vh=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.search_select_onelistitem,null);
            vh.tv= (TextView) convertView.findViewById(R.id.item_tv);
            convertView.setTag(vh);
        }else {
          vh= (ViewHolder) convertView.getTag();
        }
        final DataManager.citys temp=citysList.get(position);
        vh.tv.setText(temp.c_name);
        for(DataManager.citycode a:temp.citycode){
            list.add(a.c_name);
        }

        vh.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DataManager.city=list;
               // SearchFirmActivty.handler.sendEmptyMessage(1);
                //Toast.makeText(context,"TTTTTTTT",Toast.LENGTH_SHORT).show();
//                adapter=new ArrayAdapter(context,R.layout.search_select_twolistitem,list);
//                SearchFirmActivty.menu_two.setAdapter(adapter);
//                adapter.notifyDataSetChanged();

            }
        });

        return convertView;
    }
    class ViewHolder{
         TextView tv;
    }
}
