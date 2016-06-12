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
 * Created by alucard on 2016/5/29.
 */
public class Autonomy_Adapter extends BaseAdapter {
    Context context;
    List<DataManager.report> reportList;//企业年报
    List<DataManager.funded> fundedList;//股东出资
    List<DataManager.stock> stockList;//股权变更
    List<DataManager.permit> permitList;//行政许可信
    List<DataManager.lore> loreList;//知识产权
    List<DataManager.punish> punishList;//行政处罚

    public Autonomy_Adapter(Context context) {
        this.context = context;
    }

    public void setData(List<DataManager.report> reportList, List<DataManager.funded> fundedList, List<DataManager.stock> stockList, List<DataManager.permit> permitList,List<DataManager.lore> loreList,List<DataManager.punish> punishList) {
        this.reportList = reportList;
        this.fundedList = fundedList;
        this.stockList = stockList;
        this.permitList = permitList;
        this.loreList=loreList;
        this.punishList=punishList;
    }

    @Override
    public int getCount() {
        if (reportList != null) {
            return reportList.size();
        }
        if (fundedList != null) {
            return fundedList.size();
        }
        if (stockList != null) {
            return stockList.size();
        }
        if (permitList != null) {
            return permitList.size();
        }
        if(loreList!=null){
            return loreList.size();
        }
        if (punishList!=null){
            return punishList.size();
        }
        else {
            return 0;
        }

    }

    @Override
    public Object getItem(int position) {
        if (reportList != null) {
            return reportList.get(position);
        }
        if (fundedList != null) {
            return fundedList.get(position);
        }
        if (stockList != null) {
            return stockList.get(position);
        }
        if (permitList != null) {
            return permitList.get(position);
        }
        if(loreList!=null){
            return loreList.get(position);
        }
        if (punishList!=null){
            return punishList.get(position);
        }
        else {
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
            if (reportList != null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.activity_c_list_item, null);
                convertView.findViewById(R.id.report_item).setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.detail).setVisibility(View.GONE);
                convertView.findViewById(R.id.time).setVisibility(View.VISIBLE);
                vh.report_year = (TextView) convertView.findViewById(R.id.report_year);
                vh.report_date = (TextView) convertView.findViewById(R.id.date);

            }
            if (fundedList != null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.autonomy_funded_item, null);
                vh.partenr = (TextView) convertView.findViewById(R.id.partenr);
                vh.money = (TextView) convertView.findViewById(R.id.money);
                vh.true_money = (TextView) convertView.findViewById(R.id.true_money);
                vh.subscribed_type = (TextView) convertView.findViewById(R.id.subscribed_type);
                vh.subscribed = (TextView) convertView.findViewById(R.id.subscribed);
                vh.subscribed_time = (TextView) convertView.findViewById(R.id.subscribed_time);
                vh.paid_type = (TextView) convertView.findViewById(R.id.paid_type);
                vh.paid = (TextView) convertView.findViewById(R.id.paid);
                vh.paid_time = (TextView) convertView.findViewById(R.id.paid_time);
                vh.funded_public_time = (TextView) convertView.findViewById(R.id.funded_public_time);

            }
            if (stockList != null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.autonomy_stock_item, null);
                vh.stock_time = (TextView) convertView.findViewById(R.id.stock_time);
                vh.BeforeContent = (TextView) convertView.findViewById(R.id.BeforeContent);
                vh.AfterContent = (TextView) convertView.findViewById(R.id.AfterContent);
            }
            if (permitList != null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.autonomy_permit_item, null);
                vh.permit_name = (TextView) convertView.findViewById(R.id.permit_name);
                vh.permit_no = (TextView) convertView.findViewById(R.id.permit_no);
                vh.permit_date = (TextView) convertView.findViewById(R.id.permit_date);
                vh.permit_office = (TextView) convertView.findViewById(R.id.permit_office);
                vh.permit_time = (TextView) convertView.findViewById(R.id.permit_time);
                vh.permit_content = (TextView) convertView.findViewById(R.id.permit_content);
            } else {
            }
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        if (reportList != null) {

            DataManager.report report = reportList.get(position);
            vh.report_year.setText(report.ANCHEYEAR);
            vh.report_date.setText(report.ANCHEDATE);
        }
        if (fundedList != null) {
            DataManager.funded funded = fundedList.get(position);
            vh.partenr.setText(funded.INVNAME);
            vh.money.setText(funded.LISUBCONAM);
            vh.true_money.setText(funded.LIACCONAM);
            vh.subscribed_type.setText(funded.SUBCONFORM);
            vh.subscribed.setText(funded.LISUBCONAM);
            vh.subscribed_time.setText(funded.SUBCONDATE);
            vh.paid_type.setText(funded.ACCONFORM);
            vh.paid.setText(funded.LIACCONAM);
            vh.paid_time.setText(funded.ACCONDATE);
            vh.funded_public_time.setText(funded.SUBCONDATE);
        }
        if (stockList != null) {
            DataManager.stock stock = stockList.get(position);
            vh.stock_time.setText(stock.ALTDATE);
            vh.AfterContent.setText(stock.TRANSAMAFT);
            vh.BeforeContent.setText(stock.TRANSAMPR);
        }
        if (permitList != null) {
            DataManager.permit permit = permitList.get(position);
            vh.permit_name.setText(permit.LICNAME_CN);
            vh.permit_no.setText(permit.LICNO);
            vh.permit_date.setText(permit.invalidDate);
            vh.permit_office.setText(permit.LICANTH);
            vh.permit_time.setText(permit.PUBLICDATE);
            vh.permit_content.setText(permit.LICITEM);

        } else {
        }

        return convertView;
    }

    class ViewHolder {
        TextView report_year, report_date;
        TextView partenr, money, true_money, subscribed_type, subscribed, subscribed_time, paid_type, paid, paid_time, funded_public_time;
        TextView stock_time, BeforeContent, AfterContent;
        TextView permit_name, permit_no, permit_date, permit_office, permit_time, permit_content;
    }

}
