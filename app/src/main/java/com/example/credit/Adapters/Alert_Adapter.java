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
 * Created by alucard on 2016-05-30.
 */
public class Alert_Adapter extends BaseAdapter {
    Context context;
    List<DataManager.AlertInfo.DataBean.date> list;
    String str;

    public Alert_Adapter(Context context) {
        this.context = context;
    }

    public void setData(List<DataManager.AlertInfo.DataBean.date> list, String str) {
        this.list = list;
        this.str = str;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }


    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.loan_item, null);
            vh.loan_1= (TextView) convertView.findViewById(R.id.loan_1);
            vh.loan_2= (TextView) convertView.findViewById(R.id.loan_2);
            vh.loan_3= (TextView) convertView.findViewById(R.id.loan_3);
            vh.loan_4= (TextView) convertView.findViewById(R.id.loan_4);
            vh.loan_5= (TextView) convertView.findViewById(R.id.loan_5);
            vh.loan_6= (TextView) convertView.findViewById(R.id.loan_6);
            vh.loan_7= (TextView) convertView.findViewById(R.id.loan_7);
            vh.loan_8= (TextView) convertView.findViewById(R.id.loan_8);
            vh.loan_9= (TextView) convertView.findViewById(R.id.loan_9);
            vh.loan_10= (TextView) convertView.findViewById(R.id.loan_10);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        switch (str){
            case "证照到期":
                vh.loan_1.setText(list.get(position).ORGAN);
                vh.loan_2.setText(list.get(position).WARNAMOUNT+" 万元");
                vh.loan_3.setText(list.get(position).WARNSTATUS);
                vh.loan_4.setText(list.get(position).WARNDATE);
                vh.loan_5.setText(list.get(position).WARNCONTENT);
                if(list.get(position).STATE.equals("0")){
                    vh.loan_6.setText("有效");
                }else{
                    vh.loan_6.setText("无效");
                }
                vh.loan_7.setText(list.get(position).SOURCENAME);
                vh.loan_8.setText(list.get(position).UPDEPARTMENTNAME);
                vh.loan_9.setText(list.get(position).BUSINESSATT);
                if(list.get(position).DATATYPE.equals("2")){
                    vh.loan_10.setText("欠贷信息");
                }else if(list.get(position).DATATYPE.equals("4")){
                    vh.loan_10.setText("欠税信息");
                }else if(list.get(position).DATATYPE.equals("5")){
                    vh.loan_10.setText("欠薪信息");
                }else if(list.get(position).DATATYPE.equals("7")){
                    vh.loan_10.setText("责令改正");
                }else if(list.get(position).DATATYPE.equals("8")){
                    vh.loan_10.setText("证照到期");
                }else if(list.get(position).DATATYPE.equals("9")){
                    vh.loan_10.setText("证照过期");
                }
                break;
            case "证照过期":
                vh.loan_1.setText(list.get(position).ORGAN);
                vh.loan_2.setText(list.get(position).WARNAMOUNT+" 万元");
                vh.loan_3.setText(list.get(position).WARNSTATUS);
                vh.loan_4.setText(list.get(position).WARNDATE);
                vh.loan_5.setText(list.get(position).WARNCONTENT);
                if(list.get(position).STATE.equals("0")){
                    vh.loan_6.setText("有效");
                }else{
                    vh.loan_6.setText("无效");
                }
                vh.loan_7.setText(list.get(position).SOURCENAME);
                vh.loan_8.setText(list.get(position).UPDEPARTMENTNAME);
                vh.loan_9.setText(list.get(position).BUSINESSATT);
                if(list.get(position).DATATYPE.equals("2")){
                    vh.loan_10.setText("欠贷信息");
                }else if(list.get(position).DATATYPE.equals("4")){
                    vh.loan_10.setText("欠税信息");
                }else if(list.get(position).DATATYPE.equals("5")){
                    vh.loan_10.setText("欠薪信息");
                }else if(list.get(position).DATATYPE.equals("7")){
                    vh.loan_10.setText("责令改正");
                }else if(list.get(position).DATATYPE.equals("8")){
                    vh.loan_10.setText("证照到期");
                }else if(list.get(position).DATATYPE.equals("9")){
                    vh.loan_10.setText("证照过期");
                }
                break;
            case "责令改正":
                vh.loan_1.setText(list.get(position).ORGAN+"");
                vh.loan_2.setText(list.get(position).WARNAMOUNT+" 万元");
                vh.loan_3.setText(list.get(position).WARNSTATUS+"");
                vh.loan_4.setText(list.get(position).WARNDATE+"");
                vh.loan_5.setText(list.get(position).WARNCONTENT+"");
                if(list.get(position).STATE.equals("0")){
                    vh.loan_6.setText("有效");
                }else{
                    vh.loan_6.setText("无效");
                }
                vh.loan_7.setText(list.get(position).SOURCENAME);
                vh.loan_8.setText(list.get(position).UPDEPARTMENTNAME);
                vh.loan_9.setText(list.get(position).BUSINESSATT);
                if(list.get(position).DATATYPE.equals("2")){
                    vh.loan_10.setText("欠贷信息");
                }else if(list.get(position).DATATYPE.equals("4")){
                    vh.loan_10.setText("欠税信息");
                }else if(list.get(position).DATATYPE.equals("5")){
                    vh.loan_10.setText("欠薪信息");
                }else if(list.get(position).DATATYPE.equals("7")){
                    vh.loan_10.setText("责令改正");
                }else if(list.get(position).DATATYPE.equals("8")){
                    vh.loan_10.setText("证照到期");
                }else if(list.get(position).DATATYPE.equals("9")){
                    vh.loan_10.setText("证照过期");
                }
                break;
            case "欠贷信息":
                vh.loan_1.setText(list.get(position).ORGAN);
                vh.loan_2.setText(list.get(position).WARNAMOUNT+" 万元");
                vh.loan_3.setText(list.get(position).WARNSTATUS);
                vh.loan_4.setText(list.get(position).WARNDATE);
                vh.loan_5.setText(list.get(position).WARNCONTENT);
                if(list.get(position).STATE.equals("0")){
                    vh.loan_6.setText("有效");
                }else{
                    vh.loan_6.setText("无效");
                }
                vh.loan_7.setText(list.get(position).SOURCENAME);
                vh.loan_8.setText(list.get(position).UPDEPARTMENTNAME);
                vh.loan_9.setText(list.get(position).BUSINESSATT);
                if(list.get(position).DATATYPE.equals("2")){
                    vh.loan_10.setText("欠贷信息");
                }else if(list.get(position).DATATYPE.equals("4")){
                    vh.loan_10.setText("欠税信息");
                }else if(list.get(position).DATATYPE.equals("5")){
                    vh.loan_10.setText("欠薪信息");
                }else if(list.get(position).DATATYPE.equals("7")){
                    vh.loan_10.setText("责令改正");
                }else if(list.get(position).DATATYPE.equals("8")){
                    vh.loan_10.setText("证照到期");
                }else if(list.get(position).DATATYPE.equals("9")){
                    vh.loan_10.setText("证照过期");
                }
                break;
            case "欠税信息":
                vh.loan_1.setText(list.get(position).ORGAN);
                vh.loan_2.setText(list.get(position).WARNAMOUNT+" 万元");
                vh.loan_3.setText(list.get(position).WARNSTATUS);
                vh.loan_4.setText(list.get(position).WARNDATE);
                vh.loan_5.setText(list.get(position).WARNCONTENT);
                if(list.get(position).STATE.equals("0")){
                    vh.loan_6.setText("有效");
                }else{
                    vh.loan_6.setText("无效");
                }
                vh.loan_7.setText(list.get(position).SOURCENAME);
                vh.loan_8.setText(list.get(position).UPDEPARTMENTNAME);
                vh.loan_9.setText(list.get(position).BUSINESSATT);
                if(list.get(position).DATATYPE.equals("2")){
                    vh.loan_10.setText("欠贷信息");
                }else if(list.get(position).DATATYPE.equals("4")){
                    vh.loan_10.setText("欠税信息");
                }else if(list.get(position).DATATYPE.equals("5")){
                    vh.loan_10.setText("欠薪信息");
                }else if(list.get(position).DATATYPE.equals("7")){
                    vh.loan_10.setText("责令改正");
                }else if(list.get(position).DATATYPE.equals("8")){
                    vh.loan_10.setText("证照到期");
                }else if(list.get(position).DATATYPE.equals("9")){
                    vh.loan_10.setText("证照过期");
                }
                break;
            case "欠薪信息":
                vh.loan_1.setText(list.get(position).ORGAN);
                vh.loan_2.setText(list.get(position).WARNAMOUNT + " 万元");
                vh.loan_3.setText(list.get(position).WARNSTATUS);
                vh.loan_4.setText(list.get(position).WARNDATE);
                vh.loan_5.setText(list.get(position).WARNCONTENT);
                if (list.get(position).STATE.equals("0")) {
                    vh.loan_6.setText("有效");
                } else {
                    vh.loan_6.setText("无效");
                }
                vh.loan_7.setText(list.get(position).SOURCENAME);
                vh.loan_8.setText(list.get(position).UPDEPARTMENTNAME);
                vh.loan_9.setText(list.get(position).BUSINESSATT);
                if (list.get(position).DATATYPE.equals("2")) {
                    vh.loan_10.setText("欠贷信息");
                } else if (list.get(position).DATATYPE.equals("4")) {
                    vh.loan_10.setText("欠税信息");
                } else if (list.get(position).DATATYPE.equals("5")) {
                    vh.loan_10.setText("欠薪信息");
                } else if (list.get(position).DATATYPE.equals("7")) {
                    vh.loan_10.setText("责令改正");
                } else if (list.get(position).DATATYPE.equals("8")) {
                    vh.loan_10.setText("证照到期");
                } else if (list.get(position).DATATYPE.equals("9")) {
                    vh.loan_10.setText("证照过期");
                }
                break;
        }
        return convertView;
    }

    public class ViewHolder {
        TextView loan_1;
        TextView loan_2;
        TextView loan_3;
        TextView loan_4;
        TextView loan_5;
        TextView loan_6;
        TextView loan_7;
        TextView loan_8;
        TextView loan_9;
        TextView loan_10;
    }
}