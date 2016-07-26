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
import android.widget.ListView;
import android.widget.TextView;

import com.WSZW.Activity.GZLM_Documents_mainActivity;
import com.WSZW.Activity.GZLM_XqGk_list_Activity;
import com.WSZW.Activity.GZLM_Content_mainActivity;
import com.WSZW.Activity.GZLM_list_mainActivity;
import com.WSZW.Activity.R;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.GWLM_ZwDt_title;
import com.WSZW.entity.GzLm_secondColumn_bean;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.Zwfw_bmfw_listView_item;
import com.WSZW.service.getDocumentsManagerService;

public class GzLm_zcfg_listveiw_adapter extends BaseAdapter {
	
	private Context context;
	private LayoutInflater inflater;
	private InfoFile_ infofile;
	List<GzLm_secondColumn_bean> list;
	public GzLm_zcfg_listveiw_adapter(Context context,List<GzLm_secondColumn_bean> list) {
		this.context = context;
		this.list=list;
		this.infofile=new InfoFile_(context);
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
			convertView=inflater.inflate(R.layout.gzlm_xqgk_listitem, null);
			
			viewholder.ll=(LinearLayout) convertView.findViewById(R.id.ll);
			viewholder.tv=(TextView) convertView.findViewById(R.id.textview);
			convertView.setTag(viewholder);
		}else {
			viewholder=(ViewHolder) convertView.getTag();
		}
		if(list!= null){
			
			viewholder.tv.setText(list.get(position).getName());  
			
		}else{
			viewholder.tv.setVisibility(View.GONE);
		}
		
	viewholder.ll.setOnClickListener(new OnClickListener() {
	final int position1=position;	
		@Override
		public void onClick(View v) {
		
					infofile.chanIds().put(list.get(position1).getId());
					Intent intent=new Intent();
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
