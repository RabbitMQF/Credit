package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.credit.Entitys.search;
import com.example.credit.R;

import java.util.List;

public class SearchListAdapter extends BaseAdapter {
	private Context context;
	private  List<search> list;
	public SearchListAdapter(Context context,List<search> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return  0;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder vh=null;
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.search_result_list_item, null);
			vh=new ViewHolder();
			vh.firm_name=(TextView) view.findViewById(R.id.firm_name);
			vh.corporate=(TextView) view.findViewById(R.id.corporate);
			vh.funds=(TextView) view.findViewById(R.id.funds);
			vh.status=(TextView) view.findViewById(R.id.status);
			vh.time=(TextView) view.findViewById(R.id.date);
			view.setTag(vh);
		}else{
			vh=(ViewHolder) view.getTag();
		}
		search c=list.get(position);
		vh.firm_name.setText(c.Gname);
		vh.corporate.setText(c.UserName);
		vh.funds.setText(c.money);
		vh.status.setText(c.state);
		vh.time.setText(c.time);
		return view;
	}
	public class ViewHolder{
		TextView firm_name;
		TextView corporate;
		TextView funds;
		TextView status;
		TextView time;
	}
}
