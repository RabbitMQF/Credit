package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.credit.Entitys.DataManager;
import com.example.credit.R;

import java.util.List;

public class Details_content_mylist_ItemAdapter extends BaseAdapter {
	private Context context;
	private  List<DataManager.ChangeData> list;
	public Details_content_mylist_ItemAdapter(Context context, List<DataManager.ChangeData> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return  0;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder vh=null;
		if(view==null){
			view=LayoutInflater.from(context).inflate(R.layout.activity_details_content_myitems_item, null);
			vh=new ViewHolder();
			vh.mylist_items_title=(TextView) view.findViewById(R.id.mylist_items_title);
			vh.BeforeContent=(TextView) view.findViewById(R.id.BeforeContent);//变更后
			vh.AfterContent=(TextView) view.findViewById(R.id.AfterContent);//变更前
			view.setTag(vh);
		}else{
			vh=(ViewHolder) view.getTag();
		}
		DataManager.ChangeData cc=list.get(position);
		vh.mylist_items_title.setText(cc.ALTITEM_CN);
		vh.BeforeContent.setText(cc.ALTAF);
		vh.AfterContent.setText(cc.ALTBE);
		return view;
	}
	public class ViewHolder{
		TextView mylist_items_title;
		TextView BeforeContent;
		TextView AfterContent;
	}
}
