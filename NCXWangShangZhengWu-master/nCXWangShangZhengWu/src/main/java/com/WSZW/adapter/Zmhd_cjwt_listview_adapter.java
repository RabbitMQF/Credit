package com.WSZW.adapter;


import java.util.List;


import com.WSZW.Activity.GZLM_Content_mainActivity;
import com.WSZW.Activity.GZLM_Documents_mainActivity;
import com.WSZW.Activity.R;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.GWLM_ZwDt_title;
import com.WSZW.entity.GzLm_secondColumn_bean;
import com.WSZW.entity.HandleResult;
import com.WSZW.service.getDocumentsManagerService;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Zmhd_cjwt_listview_adapter extends BaseAdapter{

	private InfoFile_ infoFile_;
	private Context context;
	private LayoutInflater inflater;
	List<GzLm_secondColumn_bean> list;
	
	public Zmhd_cjwt_listview_adapter(Context context, List<GzLm_secondColumn_bean> list){
		this.context =context;
		this.list = list;
		this.infoFile_ = new InfoFile_(context);
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		ViewHolder viewHolder;
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView=inflater.inflate(R.layout.gzlm_xqgk_listitem, null);
			
			viewHolder.ll=(LinearLayout) convertView.findViewById(R.id.ll);
			viewHolder.tv=(TextView) convertView.findViewById(R.id.textview);
			convertView.setTag(viewHolder);
		}else {
			viewHolder=(ViewHolder) convertView.getTag();
		}
		if(list!= null){
			
			viewHolder.tv.setText(list.get(position).getName());  
			
		}else{
			viewHolder.tv.setVisibility(View.GONE);
		}
		viewHolder.ll.setOnClickListener(new OnClickListener() {
			final int position1 = position;
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				infoFile_.chanIds().put(list.get(position1).getId());
				infoFile_.gzlm_title().put(list.get(position1).getName());
				Intent intent = new Intent();
				intent.setClass(context, GZLM_Documents_mainActivity.class);
				context.startActivity(intent);
			}
		});
		return convertView;
	}
	
	
	class ViewHolder{
		public LinearLayout ll;
		TextView tv ,time;
		
	}
}
