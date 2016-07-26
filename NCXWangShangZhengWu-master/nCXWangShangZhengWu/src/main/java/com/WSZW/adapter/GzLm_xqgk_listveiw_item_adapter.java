package com.WSZW.adapter;

import java.util.List;
import java.util.Map;

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

import com.WSZW.Activity.GZLM_XqGk_list_Activity;
import com.WSZW.Activity.GZLM_Content_mainActivity;
import com.WSZW.Activity.GZLM_list_mainActivity;
import com.WSZW.Activity.R;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.GWLM_ZwDt_title;
import com.WSZW.entity.Zwfw_bmfw_listView_item;

public class GzLm_xqgk_listveiw_item_adapter extends BaseAdapter {
	
	private Context context;
	private List<GWLM_ZwDt_title> list;
	private LayoutInflater inflater;
	private InfoFile_ infofile;
	public GzLm_xqgk_listveiw_item_adapter(Context context,List<GWLM_ZwDt_title> list) {
		this.context = context;
		this.list=list;
		infofile=new InfoFile_(context);
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
		return list.size();
	}
	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return position;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewholder;
		if(convertView==null){
			viewholder=new ViewHolder();
			convertView=inflater.inflate(R.layout.gzlm_xqgk_list_item, null);
			
			viewholder.ll=(LinearLayout) convertView.findViewById(R.id.ll);
			viewholder.tv=(TextView) convertView.findViewById(R.id.textview);
			convertView.setTag(viewholder);
		}else {
			viewholder=(ViewHolder) convertView.getTag();
		}
		if(list!= null){
			
			viewholder.tv.setText(list.get(position).getTitle().toString());  
			
		}else{
			viewholder.tv.setVisibility(View.GONE);; 
		}
		
	viewholder.ll.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
//			infofile.chanDocId().get();
			infofile.chanDocId().put(""+list.get(position).getId());
			infofile.gzlm_title().put(list.get(position).getTitle());
			infofile.writeTime().put(list.get(position).getWriteTime().subSequence(0, 10).toString());
			Intent intent=new Intent();
			intent.setClass(context, GZLM_Content_mainActivity.class);
			context.startActivity(intent);
		
		}
	});
			return convertView;
	}
	class ViewHolder{
		public LinearLayout ll;
		TextView tv ;
		
	}
}
