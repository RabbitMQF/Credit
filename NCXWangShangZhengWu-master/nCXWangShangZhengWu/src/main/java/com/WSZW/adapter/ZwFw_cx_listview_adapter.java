package com.WSZW.adapter;

import java.util.List;

import com.WSZW.Activity.R;
import com.WSZW.adapter.ZwFw_bmfw_listveiw_adapter.ViewHolder;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.ZwFw_CX_BizInfo;
import com.WSZW.entity.ZwFw_CX_item_bean;
import com.WSZW.entity.Zwfw_bmfw_listView_item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ZwFw_cx_listview_adapter  extends BaseAdapter{

	
	private Context context;
	private List<ZwFw_CX_item_bean> datas;
	private LayoutInflater inflater;
	private InfoFile_ infofile;
	public ZwFw_cx_listview_adapter(Context context, List<ZwFw_CX_item_bean> datas) {
		inflater = LayoutInflater.from(context);
		this.datas =datas ;
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
			convertView=inflater.inflate(R.layout.zwfw_query_main02_item, null);
			viewholder.tv1=(TextView) convertView.findViewById(R.id.tv_time);
			viewholder.tv2=(TextView) convertView.findViewById(R.id.tv_jigou);
			viewholder.tv3=(TextView) convertView.findViewById(R.id.tv_huanjie);
			viewholder.tv4=(TextView) convertView.findViewById(R.id.tv_zhuangtai);
			convertView.setTag(viewholder);
		}else {
			viewholder=(ViewHolder) convertView.getTag();
		}
		
			viewholder.tv1.setText(datas.get(position).getDate());  
			viewholder.tv2.setText(datas.get(position).getDepatement());  
			viewholder.tv3.setText(datas.get(position).getHj());  
			viewholder.tv4.setText(datas.get(position).getZt());  
			
			
			return convertView;
	}
	class ViewHolder{
		TextView tv1,tv2,tv3,tv4 ;
		
	}
}
