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
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.GWLM_ZwDt_title;
import com.WSZW.entity.Zwfw_bmfw_listView_item;

public class GzLm_title_listveiw_adapter extends BaseAdapter {
	
	private Context context;
	private List<GWLM_ZwDt_title> datas;
	private LayoutInflater inflater;
	private InfoFile_ infofile;
	
	public GzLm_title_listveiw_adapter(Context context,List<GWLM_ZwDt_title> datas) {
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
	public View getView( int position, View convertView, ViewGroup parent) {
		ViewHolder viewholder;
		if(convertView==null){
			viewholder=new ViewHolder();
			convertView=inflater.inflate(R.layout.fragment_gzlm_main_listitem, null);
			viewholder.ll=(LinearLayout) convertView.findViewById(R.id.ll);
			viewholder.tv=(TextView) convertView.findViewById(R.id.textview);
			viewholder.time=(TextView) convertView.findViewById(R.id.tv_time);
			convertView.setTag(viewholder);
		}else {
			viewholder=(ViewHolder) convertView.getTag();
		}
		if(datas.get(position).getTitle() != null){
			viewholder.tv.setText(datas.get(position).getTitle());  
			viewholder.time.setText(datas.get(position).getWriteTime().subSequence(0, 10));  
		}else{
			viewholder.tv.setVisibility(View.GONE);
		}
		final int position1 = position;	
	viewholder.ll.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			infofile.gzlm_title().put(datas.get(position1).getTitle());
			infofile.chanDocId().put(datas.get(position1).getId());
			infofile.writeTime().put(datas.get(position1).getWriteTime().subSequence(0, 10).toString());
			Intent intent=new Intent();
			intent.setClass(context, GZLM_Content_mainActivity.class);
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
