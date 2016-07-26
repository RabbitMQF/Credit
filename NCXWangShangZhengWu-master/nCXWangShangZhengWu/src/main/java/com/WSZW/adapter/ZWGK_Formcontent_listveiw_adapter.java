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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.WSZW.Activity.GZLM_Content_mainActivity;
import com.WSZW.Activity.R;
import com.WSZW.Activity.ZWGK_Form_content_mainActivity;
import com.WSZW.Activity.ZWGK_TwoColumn_mainActivity;
import com.WSZW.Activity.ZWGK_formContent_mainActivity;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.GWLM_ZwDt_title;
import com.WSZW.entity.ZWGK_FirstColumn_bean;
import com.WSZW.entity.ZWGK_formcontent_bean;
import com.WSZW.entity.Zwfw_bmfw_listView_item;

public class ZWGK_Formcontent_listveiw_adapter extends BaseAdapter {
	
	private Context context;
	private List<ZWGK_formcontent_bean> datas;
	private LayoutInflater inflater;
	private InfoFile_ infofile;
	
	public ZWGK_Formcontent_listveiw_adapter(Context context,List<ZWGK_formcontent_bean> datas) {
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
			convertView=inflater.inflate(R.layout.zwgk_formcontent_item, null);
			viewholder.tv1=(TextView) convertView.findViewById(R.id.tv_list_item_1);
			viewholder.tv2=(TextView) convertView.findViewById(R.id.tv_list_item_2);
			viewholder.tv3=(TextView) convertView.findViewById(R.id.tv_list_item_3);
			viewholder.tv4=(TextView) convertView.findViewById(R.id.tv_list_item_4);
			viewholder.btn=(Button) convertView.findViewById(R.id.btn_list_1);
			convertView.setTag(viewholder);
		}else {
			viewholder=(ViewHolder) convertView.getTag();
		}
		if(datas!= null){
			viewholder.tv1.setText(datas.get(position).getIndexId());  
			viewholder.tv2.setText(datas.get(position).getTitle());  
			viewholder.tv3.setText("主动公开");  
			viewholder.tv4.setText(datas.get(position).getPubDocTime().subSequence(0, 10));  
			
		}
//		
	viewholder.btn.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent=new Intent();
			infofile.zwgk_url().put(datas.get(position).getUrl().toString());
			intent.setClass(context, ZWGK_Form_content_mainActivity.class);
			context.startActivity(intent);
		}
	});
			return convertView;
	}
	class ViewHolder{
		Button btn;
		TextView tv1,tv2,tv3,tv4;
		
	}
}
