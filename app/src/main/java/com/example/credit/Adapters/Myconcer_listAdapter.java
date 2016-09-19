package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Views.RoundImageView;

import java.util.List;

public class Myconcer_listAdapter extends BaseAdapter {
    private Context context;
    private List<DataManager.FavotiteList.DataBean.AttentionListBean> list;
    ViewHolder vh = null;
    public Myconcer_listAdapter(Context context, List<DataManager.FavotiteList.DataBean.AttentionListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_myconcern_item, null);
            vh = new ViewHolder();
            vh.mycon_name=(TextView) view.findViewById(R.id.mycon_name);
            vh.mycon_Faname=(TextView) view.findViewById(R.id.mycon_Faname);
            vh.mycon_zcmoney=(TextView) view.findViewById(R.id.mycon_zcmoney);
            vh.mycon_zcdate=(TextView) view.findViewById(R.id.mycon_zcdate);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        DataManager.FavotiteList.DataBean.AttentionListBean ab=list.get(position);
        vh.mycon_name.setText(ab.ENTERNAME+"");
        vh.mycon_Faname.setText(ab.NAME+"");
        vh.mycon_zcmoney.setText(ab.REGNORE+"");
        vh.mycon_zcdate.setText(ab.ATTENTIONTIME+"");
        return view;
    }

    public class ViewHolder {
        TextView mycon_name;//公司名称
        TextView mycon_Faname;//法人代表
        TextView mycon_zcmoney;//注册资金
        TextView mycon_zcdate;//成立日期
    }
}
