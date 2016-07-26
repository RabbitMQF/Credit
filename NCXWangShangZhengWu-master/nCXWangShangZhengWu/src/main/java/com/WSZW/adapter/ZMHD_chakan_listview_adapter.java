package com.WSZW.adapter;

import java.util.List;

import com.WSZW.Activity.R;
import com.WSZW.adapter.ZMHD_ZX_listview_adapter.ViewHolder;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.ZMHD_zx_bean;
import com.WSZW.service.getQueryLetterManagerService;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ZMHD_chakan_listview_adapter extends BaseAdapter{

	private List<ZMHD_zx_bean> datas;
	private LayoutInflater inflater;
	private InfoFile_ infoFile_;
	private Context context;
	
	public ZMHD_chakan_listview_adapter(
			getQueryLetterManagerService getQueryLetterManagerService,
			List<ZMHD_zx_bean> list) {
		// TODO 自动生成的构造函数存根
		
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
		// TODO 自动生成的方法存根
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.zmhd_lxxd_chakan, null);
			holder.tv_1 = (TextView) convertView.findViewById(R.id.tv_1);
			holder.tv_2 = (TextView) convertView.findViewById(R.id.tv_2);
			holder.tv_3 = (TextView) convertView.findViewById(R.id.tv_3);
			holder.tv_4 = (TextView) convertView.findViewById(R.id.tv_4);
			holder.tv_5 = (TextView) convertView.findViewById(R.id.tv_5);
			holder.tv_6 = (TextView) convertView.findViewById(R.id.tv_6);
			holder.tv_7 = (TextView) convertView.findViewById(R.id.tv_7);
			holder.tv_8 = (TextView) convertView.findViewById(R.id.tv_8);
			
			holder.btn = (Button) convertView.findViewById(R.id.btn);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tv_1.setText(datas.get(position).getSearchNo());
		holder.tv_2.setText(datas.get(position).getTitle());
		holder.tv_3.setText(datas.get(position).getContent());
		holder.tv_4.setText(datas.get(position).getLettertypeName());
		holder.tv_5.setText(datas.get(position).getDeliverTime());
		holder.tv_6.setText(datas.get(position).getReplyTimeString());
		holder.tv_7.setText(datas.get(position).getReplyContent());
		
		return convertView;
	}
	class ViewHolder {
		TextView tv_1, tv_2, tv_3,tv_4,tv_5,tv_6,tv_7,tv_8;
		Button btn;
	}
}
