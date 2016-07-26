package com.WSZW.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.WSZW.Activity.GZLM_Content_mainActivity;
import com.WSZW.Activity.R;
import com.WSZW.Activity.ZWGK_TwoColumn_mainActivity;
import com.WSZW.Activity.ZWGK_formContent_mainActivity;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.GWLM_ZwDt_title;
import com.WSZW.entity.ZWGK_FirstColumn_bean;
import com.WSZW.entity.Zwfw_bmfw_listView_item;

public class ZWGK_Twocolumn_listveiw_adapter extends BaseAdapter {
	
	private Context context;
	private List<ZWGK_FirstColumn_bean> datas;
	private LayoutInflater inflater;
	private InfoFile_ infofile;
	
	public ZWGK_Twocolumn_listveiw_adapter(Context context,List<ZWGK_FirstColumn_bean> datas) {
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
	public View getView( final int position, View convertView, ViewGroup parent) {
		ViewHolder viewholder;
		if(convertView==null){
			viewholder=new ViewHolder();
			convertView=inflater.inflate(R.layout.zwgk_xzxgk_content_listitem, null);
			viewholder.ll=(LinearLayout) convertView.findViewById(R.id.ll);
			viewholder.tv=(TextView) convertView.findViewById(R.id.textview);
			viewholder.time=(TextView) convertView.findViewById(R.id.tv_time);
			convertView.setTag(viewholder);
		}else {
			viewholder=(ViewHolder) convertView.getTag();
		}
		if(datas.get(position).getName() != null){
			viewholder.tv.setText(datas.get(position).getName());  
			
		}
		
	viewholder.ll.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent=new Intent();
			infofile.id().put(datas.get(position).getId());
			System.out.println("====子菜单Adapter====="+datas.get(position).getId());
			intent.setClass(context, ZWGK_formContent_mainActivity.class);
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
