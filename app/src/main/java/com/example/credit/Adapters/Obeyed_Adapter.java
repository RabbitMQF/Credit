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
public class Obeyed_Adapter extends BaseAdapter {
    Context context;
    List<DataManager.obeyedInfo> advertisementList;

    public Obeyed_Adapter(Context context, List<DataManager.obeyedInfo> advertisementList) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.activity_obeyed_item,null);
            vh.puc_tv1= (TextView) convertView.findViewById(R.id.puc_tv1);
            vh.puc_tv2= (TextView) convertView.findViewById(R.id.puc_tv2);
            vh.puc_tv3= (TextView) convertView.findViewById(R.id.puc_tv3);
            vh.puc_tv4= (TextView) convertView.findViewById(R.id.puc_tv4);
            vh.puc_tv5= (TextView) convertView.findViewById(R.id.puc_tv5);
            vh.puc_tv6= (TextView) convertView.findViewById(R.id.puc_tv6);
            vh.puc_tv7= (TextView) convertView.findViewById(R.id.puc_tv7);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
            DataManager.obeyedInfo temp=advertisementList.get(position);
            vh.puc_tv1.setText(temp.ENTNAME);
            vh.puc_tv2.setText(temp.REGNO);
            vh.puc_tv3.setText(temp.UNISCID);
            vh.puc_tv4.setText(temp.CONTENT);
            vh.puc_tv5.setText(temp.IDENTIFYDATE);
            vh.puc_tv6.setText(temp.IDENTIFYORGANS);
            if(temp.STATE.equals("1")){//，1表示有效，2表示无效
                vh.puc_tv7.setText("有效");
            }else{
                vh.puc_tv7.setText("无效");
            }
        }

        return convertView;
    }

    class ViewHolder {
        TextView puc_tv1;
        TextView puc_tv2;
        TextView puc_tv3;
        TextView puc_tv4;
        TextView puc_tv5;
        TextView puc_tv6;
        TextView puc_tv7;

    }

}
