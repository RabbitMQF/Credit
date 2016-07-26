package com.WSZW.adapter;

import java.util.List;

import com.WSZW.Activity.R;
import com.WSZW.Activity.ZWFW_Detail_Activity;
import com.WSZW.Activity.ZWFW_Detail_xzcf_Activity;
import com.WSZW.Activity.ZWFW_Zxsb_Activity;
import com.WSZW.Activity.ZWFW_Zxsb_Activity_;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.Zwfw_Shixiang_List_Item;
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
import android.widget.Toast;

public class Zwfw_ShiXiang_List_adapter extends BaseAdapter {

	private List<Zwfw_Shixiang_List_Item> datas;
	private LayoutInflater inflater;
	private InfoFile_ infofile;
	private Context context;

	public Zwfw_ShiXiang_List_adapter(Context context,
			List<Zwfw_Shixiang_List_Item> datas) {
		inflater = LayoutInflater.from(context);
		this.datas = datas;
		this.infofile = new InfoFile_(context);
		this.context = context;
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int arg0) {
		return datas.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.zwfw_shixiang_list_item,
					null);
			holder.tv_1 = (TextView) convertView
					.findViewById(R.id.tv_list_item_1);
			holder.tv_2 = (TextView) convertView
					.findViewById(R.id.tv_list_item_2);
			holder.tv_3 = (TextView) convertView
					.findViewById(R.id.tv_list_item_3);
			holder.tv_4 = (TextView) convertView
					.findViewById(R.id.tv_list_item_4);

			holder.btn = (Button) convertView.findViewById(R.id.btn_list_1);
			holder.btn_detail = (Button) convertView.findViewById(R.id.detail);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv_1.setText(datas.get(position).getName());
		holder.tv_2.setText(datas.get(position).getCode());
		holder.tv_3.setText(datas.get(position).getLeibie());
		holder.tv_4.setText(datas.get(position).getBumen());

		if(Constants.TYPE_IF_XZCF == 0){
			holder.btn.setVisibility(View.VISIBLE);
		}else{
			holder.btn.setVisibility(View.GONE);
		}
		holder.btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				infofile.edit().shixiang_id().put(datas.get(position).getId()).apply();
				infofile.edit().shixiang_name().put(datas.get(position).getName()).apply();
				context.startActivity(new Intent(context, ZWFW_Zxsb_Activity_.class));
			}
		});
		
		holder.btn_detail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(Constants.TYPE_IF_XZCF == 0){
					infofile.edit().shixiang_id().put(datas.get(position).getId()).apply();
					infofile.edit().shixiang_name().put(datas.get(position).getName()).apply();
					context.startActivity(new Intent(context, ZWFW_Detail_Activity.class));
				}else{
					infofile.edit().shixiang_id().put(datas.get(position).getId()).apply();
					infofile.edit().shixiang_name().put(datas.get(position).getName()).apply();
					context.startActivity(new Intent(context, ZWFW_Detail_xzcf_Activity.class));
				}

			}
		});

		return convertView;
	}

	class ViewHolder {
		TextView tv_1, tv_2, tv_3, tv_4;
		Button btn,btn_detail;
		boolean flag;
	}

}
