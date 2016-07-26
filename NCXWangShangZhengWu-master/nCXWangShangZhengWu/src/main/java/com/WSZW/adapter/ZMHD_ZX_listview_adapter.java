package com.WSZW.adapter;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.WSZW.Activity.R;
import com.WSZW.Activity.ZMHD_ClickChaKan_activity;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.ZMHD_zx_bean;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ZMHD_ZX_listview_adapter extends BaseAdapter{

	private List<ZMHD_zx_bean> datas;
	private LayoutInflater inflater;
	private InfoFile_ infoFile_;
	private Context context;
	
	public ZMHD_ZX_listview_adapter(Context context, List<ZMHD_zx_bean> list){
		inflater = LayoutInflater.from(context);
		this.datas = list;
		this.infoFile_ = new InfoFile_(context);
		this.context = context;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.zmhd_list_item, null);
			holder.tv_1 = (TextView) convertView.findViewById(R.id.tv_list_item_1);
			holder.tv_2 = (TextView) convertView.findViewById(R.id.tv_list_item_2);
			holder.tv_3 = (TextView) convertView.findViewById(R.id.tv_list_item_3);
			
			holder.btn = (Button) convertView.findViewById(R.id.btn_list_1);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
			holder.tv_1.setText(datas.get(position).getTitle());
			holder.tv_2.setText(datas.get(position).getStatus().equals("2") ? "已处理" : "未处理");
			holder.tv_3.setText(datas.get(position).getReplyTimeString());
		
		
		
		
		holder.btn.setOnClickListener(new OnClickListener() {
			Intent intent = new Intent();
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				intent.setClass(context, ZMHD_ClickChaKan_activity.class);
				infoFile_.title().put(datas.get(position).getTitle());
				infoFile_.searchNo().put(datas.get(position).getSearchNo());
				infoFile_.content().put(datas.get(position).getContent());
				infoFile_.lettertypeName().put(datas.get(position).getLettertypeName());
				infoFile_.deliverTime().put(datas.get(position).getDeliverTime());
				infoFile_.replyTimeString().put(datas.get(position).getReplyTimeString());
				infoFile_.replyContent().put(datas.get(position).getReplyContent());
				infoFile_.score().put(datas.get(position).getScore());
				context.startActivity(intent);
			}
		});
		return convertView;
	}

	class ViewHolder {
		TextView tv_1, tv_2, tv_3;
		Button btn;
	}
}
