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
    List<DataManager.date> paperwork_expireList;
    List<DataManager.date> paperwork_ExpiredList;
    List<DataManager.date> correctionList;
    List<DataManager.date> loanList;
    List<DataManager.date> taxesList;
    List<DataManager.date> wagesList;

    public Alert_Adapter(Context context) {
        this.context = context;
    }

    public void setData(List<DataManager.date> paperwork_expireList, List<DataManager.date> paperwork_ExpiredList, List<DataManager.date> correctionList, List<DataManager.date> loanList, List<DataManager.date> taxesList, List<DataManager.date> wagesList) {
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
            if (paperwork_expireList != null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.paperwork_expire_item, null);
                vh.expire_time= (TextView) convertView.findViewById(R.id.expire_time);
                vh.expire_day= (TextView) convertView.findViewById(R.id.expire_day);

            }
            if (paperwork_ExpiredList != null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.paperwork_expired_item, null);
                vh.Expired_time= (TextView) convertView.findViewById(R.id.Expired_time);
                vh.Expired_status= (TextView) convertView.findViewById(R.id.Expired_status);

            }
            if (correctionList != null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.correction_item, null);
                vh.correction_content= (TextView) convertView.findViewById(R.id.correction_content);

            }
            if (loanList != null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.loan_item, null);
                vh.loan_time= (TextView) convertView.findViewById(R.id.loan_time);
                vh.loan_money= (TextView) convertView.findViewById(R.id.loan_money);
                vh.loan_bank= (TextView) convertView.findViewById(R.id.loan_bank);
                vh.loan_status= (TextView) convertView.findViewById(R.id.loan_status);
            }
            if (taxesList != null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.taxes_item, null);
                vh.taxes_time= (TextView) convertView.findViewById(R.id.taxes_time);
                vh.taxes_money= (TextView) convertView.findViewById(R.id.taxes_money);
                vh.taxes_office= (TextView) convertView.findViewById(R.id.taxes_office);
                vh.taxes_status= (TextView) convertView.findViewById(R.id.taxes_status);
            }
            if (wagesList != null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.wages_item, null);
                vh.wages_time= (TextView) convertView.findViewById(R.id.wages_time);
                vh.wages_money= (TextView) convertView.findViewById(R.id.wages_money);
                vh.wages_status= (TextView) convertView.findViewById(R.id.wages_status);
                vh.wages_source= (TextView) convertView.findViewById(R.id.wages_source);
            } else {}

        } else {
        vh = (ViewHolder) convertView.getTag();
    }

//        if (paperwork_expireList != null) {
//           DataManager.paperwork_expire pe=DataManager.paperwork_expireList.get(position);
//            vh.expire_time.setText(pe.deadline);
//            vh.expire_day.setText(pe.daysRemaining);
//        }
//        if (paperwork_ExpiredList != null) {
//            DataManager.paperwork_Expired ped=DataManager.paperwork_ExpiredList.get(position);
//            vh.Expired_time.setText(ped.deadline);
//            vh.Expired_status.setText(ped.state);
//        }
//        if (correctionList != null) {
//            DataManager.correction cor=DataManager.correctionList.get(position);
//            vh.correction_content.setText(cor.centent);
//        }
//        if (loanList != null) {
//            DataManager.loan loan=DataManager.loanList.get(position);
//            vh.loan_time.setText(loan.time);
//            vh.loan_money.setText(loan.money);
//            vh.loan_bank.setText(loan.bank);
//            vh.loan_status.setText(loan.status);
//        }
//        if (taxesList != null) {
//            DataManager.Taxes taxes=DataManager.taxesList.get(position);
//            vh.taxes_time.setText(taxes.time);
//            vh.taxes_money.setText(taxes.money);
//            vh.taxes_office.setText(taxes.organ);
//            vh.taxes_status.setText(taxes.status);
//
//        }
//        if (wagesList != null) {
//            DataManager.Wages wages=DataManager.wagesList.get(position);
//            vh.wages_time.setText(wages.time);
//            vh.wages_money.setText(wages.money);
//            vh.wages_status.setText(wages.status);
//            vh.wages_source.setText(wages.source);
//        } else {}
    return convertView;
}

class ViewHolder {
    TextView expire_time, expire_day;
    TextView Expired_time, Expired_status;
    TextView correction_content;
    TextView loan_time, loan_money, loan_bank, loan_status;
    TextView taxes_time, taxes_money, taxes_office, taxes_status;
    TextView wages_time, wages_money, wages_status, wages_source;
}

}