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
 * Created by alucard on 2016-05-20.
 */
public class NewClaimListAdapter extends BaseAdapter{
    Context context;
    List<DataManager.MyClaimUtils.DataBean.ClaimlistBean> newsList;
    int st;
    public NewClaimListAdapter(Context context, List<DataManager.MyClaimUtils.DataBean.ClaimlistBean> newsList,int sts) {
        this.context = context;
        this.newsList = newsList;
        st=sts;
    }

    @Override

    public int getCount() {
        if(st==0){
            if(newsList.size()>=3){
                return 3;
            }else{
                return newsList.size();
            }
        }else{
            return newsList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh=null;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.newclaim_list_item, null);
            vh=new ViewHolder();
            vh.newclaim_1= (TextView) convertView.findViewById(R.id.newclaim_1);
            vh.newclaim_2= (TextView) convertView.findViewById(R.id.newclaim_2);
            vh.newclaim_3= (TextView) convertView.findViewById(R.id.newclaim_3);
            convertView.setTag(vh);
        }else {
            vh=(ViewHolder) convertView.getTag();
        }
        vh.newclaim_1.setText(newsList.get(position).ENTERNAME);
        vh.newclaim_2.setText(newsList.get(position).USERNAME);
        vh.newclaim_3.setText(newsList.get(position).CLAIMTIME);
        return convertView;
    }

    public class ViewHolder{
        TextView newclaim_1;
        TextView newclaim_2;
        TextView newclaim_3;
    }
}
