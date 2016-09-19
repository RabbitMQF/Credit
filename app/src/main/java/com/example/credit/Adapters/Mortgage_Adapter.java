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
 * Created by alucard on 2016-05-25.
 */
public class Mortgage_Adapter extends BaseAdapter {
    Context context;
    List<DataManager.Mychattel.DataBean.ChattelBean> listMP;//动产数据源
    List<DataManager.MyrealEstate.DataBean.realEstateBean> listRE;//不动产数据源

    public Mortgage_Adapter(Context context, List<DataManager.MyrealEstate.DataBean.realEstateBean> listRE, List<DataManager.Mychattel.DataBean.ChattelBean> listMP) {
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
            vh.ctv= (TextView) convertView.findViewById(R.id.title2);
            vh.mort_id = (TextView) convertView.findViewById(R.id.mort_id);
            vh.mort_no = (TextView) convertView.findViewById(R.id.mort_no);
            vh.mort_time = (TextView) convertView.findViewById(R.id.mort_time);
            vh.mort_date = (TextView) convertView.findViewById(R.id.mort_date);
            vh.mort_office = (TextView) convertView.findViewById(R.id.mort_office);
            vh.mort_val = (TextView) convertView.findViewById(R.id.mort_Val);
            vh.mort_detail=(TextView) convertView.findViewById(R.id.mort_detail);
            vh.ll_detail= (LinearLayout) convertView.findViewById(R.id.ll_detail);
            vh.ll_val= (LinearLayout) convertView.findViewById(R.id.ll_val);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        DataManager.Mychattel.DataBean.ChattelBean tempMP = new DataManager.Mychattel.DataBean.ChattelBean();
        DataManager.MyrealEstate.DataBean.realEstateBean tempRE = new DataManager.MyrealEstate.DataBean.realEstateBean();
        if(listRE!=null){
        tempRE = listRE.get(position);}else {
            tempMP = listMP.get(position);
        }
        if (listMP != null) {
            if(position==0){
                vh.ctv.setVisibility(View.VISIBLE);
                vh.ctv.setText("动产抵押信息");
            }

            vh.mort_id.setText(tempMP.MORREG_ID);
            vh.mort_no.setText(tempMP.MORREGCNO);
            vh.mort_time.setText(tempMP.REGIDATE);
            vh.mort_date.setText(tempMP.PUBLICDATE);
            vh.mort_office.setText(tempMP.REGORG_CN);
            vh.mort_val.setText(tempMP.PRICLASECAM);
            vh.ll_val.setVisibility(View.VISIBLE);
            vh.ll_detail.setVisibility(View.GONE);
        } else {
            if(position==0){
                vh.ctv.setVisibility(View.VISIBLE);
                vh.ctv.setText("不动产抵押信息");
            }
            vh.mort_id.setText(tempRE.C_INFOID);
            vh.mort_no.setText(tempRE.C_DYDJZH);
            vh.mort_time.setText(tempRE.D_DJRQ);
            vh.mort_date.setText(tempRE.D_SQRQ);
            vh.mort_office.setText(tempRE.C_DJJG);
            vh.mort_detail.setText(tempRE.C_DBFW);
            vh.ll_val.setVisibility(View.GONE);
            vh.ll_detail.setVisibility(View.VISIBLE);


        }
        return convertView;
    }

    class ViewHolder {
        TextView ctv;
        TextView mort_id,mort_no,mort_time,mort_date,mort_office,mort_val;
        TextView mort_detail;
        LinearLayout ll_detail,ll_val;


    }
}
