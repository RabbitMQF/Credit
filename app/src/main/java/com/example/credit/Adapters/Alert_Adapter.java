package com.example.credit.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;

import java.util.List;

/**
 * Created by alucard on 2016-05-30.
 */
public class Alert_Adapter extends BaseAdapter {
    Context context;
    List<DataManager.paperwork_expire> paperwork_expireList;
    List<DataManager.paperwork_Expired> paperwork_ExpiredList;
    List<DataManager.correction> correctionList;
    List<DataManager.loan> loanList;
    List<DataManager.Taxes> taxesList;
    List<DataManager.Wages> wagesList;


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
        }else {
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
        }else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        return null;
    }

    class ViewHolder{

    }

}
