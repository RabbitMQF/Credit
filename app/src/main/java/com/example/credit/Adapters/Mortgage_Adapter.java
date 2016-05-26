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
public class Mortgage_Adapter extends BaseAdapter {
    Context context;
    List<DataManager.mortgageRE> listRE;//不动产数据源
    List<DataManager.mortgageMP> listMP;//动产数据源

    public Mortgage_Adapter(Context context, List<DataManager.mortgageRE> listRE, List<DataManager.mortgageMP> listMP) {
        this.context = context;
        this.listRE = listRE;
        this.listMP = listMP;
    }

    @Override
    public int getCount() {
        if (listRE != null) {
            return listRE.size();
        } else {
            return listMP.size();
        }

    }

    @Override
    public Object getItem(int position) {
        if (listRE != null) {
            return listRE.get(position);
        } else {
            return listMP.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.mortgage_list_item, null);
            vh.ctv= (TextView) convertView.findViewById(R.id.title);
            vh.no = (TextView) convertView.findViewById(R.id.no);
            vh.register_date = (TextView) convertView.findViewById(R.id.register_date);
            vh.public_date = (TextView) convertView.findViewById(R.id.public_date);
            vh.debts = (TextView) convertView.findViewById(R.id.debts);
            vh.office = (TextView) convertView.findViewById(R.id.office);
            vh.detail = (TextView) convertView.findViewById(R.id.detail);
            vh.detail1 = (TextView) convertView.findViewById(R.id.detail1);
            vh.collateral = (TextView) convertView.findViewById(R.id.Collateral);
            vh.collateral1 = (TextView) convertView.findViewById(R.id.Collateral1);
            vh.valuation = (TextView) convertView.findViewById(R.id.Valuation);
            vh.valuation1 = (TextView) convertView.findViewById(R.id.Valuation1);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        DataManager.mortgageMP tempMP = new DataManager.mortgageMP();
        DataManager.mortgageRE tempRE = new DataManager.mortgageRE();
        if(listRE!=null){
        tempRE = listRE.get(position);}else {
            tempMP = listMP.get(position);
        }
        if (listMP != null) {
            vh.no.setText(tempMP.no);
            vh.register_date.setText(tempMP.register_date);
            vh.public_date.setText(tempMP.public_date);
            vh.debts.setText(tempMP.debts);
            vh.office.setText(tempMP.office);
            vh.detail.setText(tempMP.detail);
        } else {
            vh.ctv.setText("不动产抵押信息");
            vh.no.setText(tempRE.no);
            vh.register_date.setText(tempRE.register_date);
            vh.public_date.setText(tempRE.public_date);
            vh.debts.setText(tempRE.debts);
            vh.office.setText(tempRE.office);
            vh.detail.setVisibility(View.GONE);
            vh.detail1.setVisibility(View.GONE);
            vh.collateral.setText(tempRE.collateral);
            vh.valuation.setText(tempRE.valuation);
            vh.collateral.setVisibility(View.VISIBLE);
            vh.collateral1.setVisibility(View.VISIBLE);
            vh.valuation.setVisibility(View.VISIBLE);
            vh.valuation1.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    class ViewHolder {
        TextView ctv;
        TextView no;
        TextView register_date;
        TextView public_date;
        TextView debts;
        TextView office;
        TextView detail1;
        TextView detail;
        TextView collateral1;
        TextView collateral;
        TextView valuation1;
        TextView valuation;
    }
}
