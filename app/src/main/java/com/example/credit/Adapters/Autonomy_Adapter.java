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
    List<DataManager.report> reportList;
    List<DataManager.funded> fundedList;
    List<DataManager.stock> stockList;
    List<DataManager.permit> permitList;

    public Autonomy_Adapter(Context context) {
        this.context = context;
    }

    public void setData(List<DataManager.report> reportList, List<DataManager.funded> fundedList, List<DataManager.stock> stockList, List<DataManager.permit> permitList) {
        this.reportList = reportList;
        this.fundedList = fundedList;
        this.stockList = stockList;
        this.permitList = permitList;
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
        } else {
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
            if (reportList != null) {

                DataManager.report report = reportList.get(position);
                vh.report_year.setText(report.name);
                vh.report_date.setText(report.time);
            }
            if (fundedList != null) {
                DataManager.funded funded = fundedList.get(position);
                vh.partenr.setText(funded.shareholdersName);
                vh.money.setText(funded.subscribedNums);
                vh.true_money.setText(funded.actuallyPaidNums);
                vh.subscribed_type.setText(funded.subscribedWay);
                vh.subscribed.setText(funded.subscribedNum);
                vh.subscribed_time.setText(funded.subscribedDate);
                vh.paid_type.setText(funded.actuallyPaidWay);
                vh.paid.setText(funded.actuallyPaidNum);
                vh.paid_time.setText(funded.actuallyPaidDate);
                vh.funded_public_time.setText(funded.publishDate);
            }
            if (stockList != null) {
                DataManager.stock stock = stockList.get(position);
                vh.stock_time.setText(stock.changeDate);
                vh.AfterContent.setText(stock.afterChange);
                vh.BeforeContent.setText(stock.beforeChange);
            }
            if (permitList != null) {
                DataManager.permit permit = permitList.get(position);
                vh.permit_name.setText(permit.licenseName);
                vh.permit_no.setText(permit.licenseNum);
                vh.permit_date.setText(permit.invalidDate);
                vh.permit_office.setText(permit.licenseDepart);
                vh.permit_time.setText(permit.sendDate);
                vh.permit_content.setText(permit.licensedContent);

            } else {
            }


            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
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
