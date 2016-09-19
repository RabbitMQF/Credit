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
public class Advertisement_Adapter extends BaseAdapter {
    Context context;
    List<DataManager.advertisementInfo> advertisementList;

    public Advertisement_Adapter(Context context, List<DataManager.advertisementInfo> advertisementList) {
        this.context = context;
        this.advertisementList = advertisementList;
    }

    @Override
    public int getCount() {
        return advertisementList.size();
    }

    @Override
    public Object getItem(int position) {
        return advertisementList.get(position);
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
            convertView= LayoutInflater.from(context).inflate(R.layout.advertisement_list_item,null);
            vh.title= (TextView) convertView.findViewById(R.id.title);
            vh.level= (TextView) convertView.findViewById(R.id.level);
            vh.type= (TextView) convertView.findViewById(R.id.type);
            vh.dates= (TextView) convertView.findViewById(R.id.dates);
            vh.times= (TextView) convertView.findViewById(R.id.times);
            vh.office= (TextView) convertView.findViewById(R.id.office);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
            DataManager.advertisementInfo temp=advertisementList.get(position);
            vh.level.setText(temp.C_LEVEL);
            vh.type.setText(temp.CATEGORY);
            vh.dates.setText(temp.VALFORM +"至"+ temp.VALTO);
            vh.times.setText(temp.IDENTIFYDATE);
            vh.office.setText(temp.IDENTIFYORGANS);
        }

        return convertView;
    }

    class ViewHolder {
        TextView title;
        TextView level;
        TextView type;
        TextView dates;
        TextView times;
        TextView office;

    }

}
