package com.WSZW.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.WSZW.Activity.R;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.Zwfw_bmfw_listView_item;

public class ZwFw_bmfw_listveiw_adapter extends BaseAdapter {
	
	private Context context;
	private List<Zwfw_bmfw_listView_item> datas;
	private LayoutInflater inflater;
	private InfoFile_ infofile;
	
	public ZwFw_bmfw_listveiw_adapter(Context context,List<Zwfw_bmfw_listView_item> datas) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		this.datas = datas;
		this.infofile=new InfoFile_(context);
	}
	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return datas.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		return datas.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewholder;
		if(convertView==null){
			viewholder=new ViewHolder();
			convertView=inflater.inflate(R.layout.fragment_zwfw_main_4_listitem, null);
			viewholder.tv=(TextView) convertView.findViewById(R.id.textview);
			convertView.setTag(viewholder);
		}else {
			viewholder=(ViewHolder) convertView.getTag();
		}
		if(datas.get(position).getId() != null){
			viewholder.tv.setText(datas.get(position).getName());  
		}else{
			viewholder.tv.setVisibility(View.GONE);
		}
			return convertView;
	}
	class ViewHolder{
		TextView tv ;
		
	}
}
