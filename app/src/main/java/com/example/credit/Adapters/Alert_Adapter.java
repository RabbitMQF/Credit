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
    List<DataManager.AlertInfo.DataBean.date> paperwork_expireList;//"证照到期"
    List<DataManager.AlertInfo.DataBean.date> paperwork_ExpiredList;//证照过期
    List<DataManager.AlertInfo.DataBean.date> correctionList;//责令改正
    List<DataManager.AlertInfo.DataBean.date> loanList;//欠贷信息
    List<DataManager.AlertInfo.DataBean.date> taxesList;//欠税信息
    List<DataManager.AlertInfo.DataBean.date> wagesList;//欠薪信息

    public Alert_Adapter(Context context) {
        this.context = context;
    }

    public void setData(List<DataManager.AlertInfo.DataBean.date> paperwork_expireList, List<DataManager.AlertInfo.DataBean.date> paperwork_ExpiredList, List<DataManager.AlertInfo.DataBean.date> correctionList, List<DataManager.AlertInfo.DataBean.date> loanList, List<DataManager.AlertInfo.DataBean.date> taxesList, List<DataManager.AlertInfo.DataBean.date> wagesList) {
        this.paperwork_expireList = paperwork_expireList;
        this.paperwork_ExpiredList = paperwork_ExpiredList;
        this.correctionList = correctionList;
        this.loanList = loanList;
        this.taxesList = taxesList;
        this.wagesList = wagesList;
    }

    @Override
    public int getCount() {
        if (paperwork_expireList != null) {
            return paperwork_expireList.size();
        }
        if (paperwork_ExpiredList != null) {
            return paperwork_ExpiredList.size();
        }
        if (correctionList != null) {
            return correctionList.size();
        }
        if (loanList != null) {
            return loanList.size();
        }
        if (taxesList != null) {
            return taxesList.size();
        }
        if (wagesList != null) {
            return wagesList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (paperwork_expireList != null) {
            return paperwork_expireList.get(position);
        }
        if (paperwork_ExpiredList != null) {
            return paperwork_ExpiredList.get(position);
        }
        if (correctionList != null) {
            return correctionList.get(position);
        }
        if (loanList != null) {
            return loanList.get(position);
        }
        if (taxesList != null) {
            return taxesList.get(position);
        }
        if (wagesList != null) {
            return wagesList.get(position);
        } else {
            return null;
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
            vh = new ViewHolder();
//            if (paperwork_expireList != null) {
//                convertView = LayoutInflater.from(context).inflate(R.layout.paperwork_expire_item, null);
//                vh.expire_time= (TextView) convertView.findViewById(R.id.expire_time);
//                vh.expire_day= (TextView) convertView.findViewById(R.id.expire_day);
//
//            }
//            if (paperwork_ExpiredList != null) {
//                convertView = LayoutInflater.from(context).inflate(R.layout.paperwork_expired_item, null);
//                vh.Expired_time= (TextView) convertView.findViewById(R.id.Expired_time);
//                vh.Expired_status= (TextView) convertView.findViewById(R.id.Expired_status);
//
//            }
//            if (correctionList != null) {
//                convertView = LayoutInflater.from(context).inflate(R.layout.correction_item, null);
//                vh.correction_content= (TextView) convertView.findViewById(R.id.correction_content);
//
//            }
//            if (loanList != null) {
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
//            }
//            if (taxesList != null) {
//                convertView = LayoutInflater.from(context).inflate(R.layout.taxes_item, null);
//                vh.taxes_time= (TextView) convertView.findViewById(R.id.taxes_time);
//                vh.taxes_money= (TextView) convertView.findViewById(R.id.taxes_money);
//                vh.taxes_office= (TextView) convertView.findViewById(R.id.taxes_office);
//                vh.taxes_status= (TextView) convertView.findViewById(R.id.taxes_status);
//            }
//            if (wagesList != null) {
//                convertView = LayoutInflater.from(context).inflate(R.layout.wages_item, null);
//                vh.wages_time= (TextView) convertView.findViewById(R.id.wages_time);
//                vh.wages_money= (TextView) convertView.findViewById(R.id.wages_money);
//                vh.wages_status= (TextView) convertView.findViewById(R.id.wages_status);
//                vh.wages_source= (TextView) convertView.findViewById(R.id.wages_source);
//            } else {}

        } else {
        vh = (ViewHolder) convertView.getTag();
    }

        if (paperwork_expireList != null) {
            DataManager.AlertInfo.DataBean.date loan=paperwork_expireList.get(position);
            vh.loan_1.setText(loan.ORGAN);
            vh.loan_2.setText(loan.WARNAMOUNT);
            vh.loan_3.setText(loan.WARNSTATUS);
            vh.loan_4.setText(loan.WARNDATE);
            vh.loan_5.setText(loan.WARNCONTENT);
            if(loan.STATE.equals("0")){
                vh.loan_6.setText("有效");
            }else{
                vh.loan_6.setText("无效");
            }
            vh.loan_7.setText(loan.SOURCENAME);
            vh.loan_8.setText(loan.UPDEPARTMENTNAME);
            vh.loan_9.setText(loan.BUSINESSATT);
            vh.loan_10.setText(loan.DATATYPE);
        }
        if (paperwork_ExpiredList != null) {
            DataManager.AlertInfo.DataBean.date loan=paperwork_ExpiredList.get(position);
            vh.loan_1.setText(loan.ORGAN);
            vh.loan_2.setText(loan.WARNAMOUNT);
            vh.loan_3.setText(loan.WARNSTATUS);
            vh.loan_4.setText(loan.WARNDATE);
            vh.loan_5.setText(loan.WARNCONTENT);
            if(loan.STATE.equals("0")){
                vh.loan_6.setText("有效");
            }else{
                vh.loan_6.setText("无效");
            }
            vh.loan_7.setText(loan.SOURCENAME);
            vh.loan_8.setText(loan.UPDEPARTMENTNAME);
            vh.loan_9.setText(loan.BUSINESSATT);
            vh.loan_10.setText(loan.DATATYPE);
        }
        if (correctionList != null) {
            DataManager.AlertInfo.DataBean.date loan=correctionList.get(position);
            vh.loan_1.setText(loan.ORGAN);
            vh.loan_2.setText(loan.WARNAMOUNT);
            vh.loan_3.setText(loan.WARNSTATUS);
            vh.loan_4.setText(loan.WARNDATE);
            vh.loan_5.setText(loan.WARNCONTENT);
            if(loan.STATE.equals("0")){
                vh.loan_6.setText("有效");
            }else{
                vh.loan_6.setText("无效");
            }
            vh.loan_7.setText(loan.SOURCENAME);
            vh.loan_8.setText(loan.UPDEPARTMENTNAME);
            vh.loan_9.setText(loan.BUSINESSATT);
            vh.loan_10.setText(loan.DATATYPE);
        }
        if (loanList != null) {
            DataManager.AlertInfo.DataBean.date loan=loanList.get(position);
            vh.loan_1.setText(loan.ORGAN);
            vh.loan_2.setText(loan.WARNAMOUNT);
            vh.loan_3.setText(loan.WARNSTATUS);
            vh.loan_4.setText(loan.WARNDATE);
            vh.loan_5.setText(loan.WARNCONTENT);
            if(loan.STATE.equals("0")){
                vh.loan_6.setText("有效");
            }else{
                vh.loan_6.setText("无效");
            }
            vh.loan_7.setText(loan.SOURCENAME);
            vh.loan_8.setText(loan.UPDEPARTMENTNAME);
            vh.loan_9.setText(loan.BUSINESSATT);
            vh.loan_10.setText(loan.DATATYPE);
        }
        if (taxesList != null) {
            DataManager.AlertInfo.DataBean.date loan=taxesList.get(position);
            vh.loan_1.setText(loan.ORGAN);
            vh.loan_2.setText(loan.WARNAMOUNT);
            vh.loan_3.setText(loan.WARNSTATUS);
            vh.loan_4.setText(loan.WARNDATE);
            vh.loan_5.setText(loan.WARNCONTENT);
            if(loan.STATE.equals("0")){
                vh.loan_6.setText("有效");
            }else{
                vh.loan_6.setText("无效");
            }
            vh.loan_7.setText(loan.SOURCENAME);
            vh.loan_8.setText(loan.UPDEPARTMENTNAME);
            vh.loan_9.setText(loan.BUSINESSATT);
            vh.loan_10.setText(loan.DATATYPE);
        }
        if (wagesList != null) {
            DataManager.AlertInfo.DataBean.date loan=wagesList.get(position);
            vh.loan_1.setText(loan.ORGAN);
            vh.loan_2.setText(loan.WARNAMOUNT);
            vh.loan_3.setText(loan.WARNSTATUS);
            vh.loan_4.setText(loan.WARNDATE);
            vh.loan_5.setText(loan.WARNCONTENT);
            if(loan.STATE.equals("0")){
                vh.loan_6.setText("有效");
            }else{
                vh.loan_6.setText("无效");
            }
            vh.loan_7.setText(loan.SOURCENAME);
            vh.loan_8.setText(loan.UPDEPARTMENTNAME);
            vh.loan_9.setText(loan.BUSINESSATT);
            vh.loan_10.setText(loan.DATATYPE);
        } else {}
    return convertView;
}

class ViewHolder {
    TextView expire_time, expire_day;
    TextView Expired_time, Expired_status;
    TextView correction_content;
    TextView loan_1, loan_2, loan_3, loan_4,loan_5,loan_6,loan_7,loan_8,loan_9,loan_10;
    TextView taxes_time, taxes_money, taxes_office, taxes_status;
    TextView wages_time, wages_money, wages_status, wages_source;
}

}