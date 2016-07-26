package com.WSZW.adapter;


import java.util.List;















import com.WSZW.Activity.GZLM_Content_mainActivity;
import com.WSZW.Activity.GZLM_Documents_mainActivity;
import com.WSZW.Activity.GZLM_Fragment_shgles;
import com.WSZW.Activity.GZLM_XqGk_list_Activity;
import com.WSZW.Activity.GZLM_Xxgk_list_Activity;
import com.WSZW.Activity.GZLM_shglactivity;
import com.WSZW.Activity.R;
import com.WSZW.adapter.GzLm_zcfg_listveiw_adapter.ViewHolder;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.GWLM_ZwDt_title;
import com.WSZW.entity.GzLm_secondColumn_bean;
import com.WSZW.entity.HandleResult;
import com.WSZW.service.ChannelsByParentIdManagerService;
import com.WSZW.service.getDocumentsManagerService;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Gzlm_shgl_listview_adapter extends BaseAdapter{

	private InfoFile_ infoFile_;
	private Context context;
	private LayoutInflater inflater;
	List<GzLm_secondColumn_bean> list;
	private Gzlm_shgl_listview_adapter adapter;
	private ListView lv;
	
	public Gzlm_shgl_listview_adapter(Context context, List<GzLm_secondColumn_bean> list){
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
			Intent intent = new Intent();
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				if(list.get(position1).getId().equals("793")){
					infoFile_.chanIds().put(list.get(position1).getId());
					infoFile_.gzlm_title().put(list.get(position1).getName());
					intent.setClass(context, GZLM_shglactivity.class);
					context.startActivity(intent);
				}
				else{
					infoFile_.chanIds().put(list.get(position1).getId());
					infoFile_.gzlm_title().put(list.get(position1).getName());
					intent.setClass(context, GZLM_Documents_mainActivity.class);
					context.startActivity(intent);
				}
			}
		});
		return convertView;
	}
	
	getDocumentsManagerService getdocuments=new getDocumentsManagerService() {

		private List<GWLM_ZwDt_title> title_list;

		@Override
		protected void handlerLoginInfo(Context paramActivity,
				HandleResult handleResult, int paramInt) {
				if (handleResult.getiSuccess().equals("success")) {
						title_list=Constants.gzlm_item;
						infoFile_.chanDocId().put(""+title_list.get(0).getId());
						infoFile_.chanDocId().get();
						Intent intent=new Intent();
						intent.setClass(context, GZLM_Content_mainActivity.class);
						context.startActivity(intent);
					}
				}
	};
	
	class ViewHolder{
		public LinearLayout ll;
		TextView tv ,time;
		
	}
}
