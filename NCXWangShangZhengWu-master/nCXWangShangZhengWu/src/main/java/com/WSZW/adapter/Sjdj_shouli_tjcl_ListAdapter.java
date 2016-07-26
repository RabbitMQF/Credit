package com.WSZW.adapter;

import java.util.List;
import java.util.Map;

import com.WSZW.Activity.R;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 通知提醒列表的数据Adapter
 * 
 * @author Goven
 * @date 2012-12-27
 */
public class Sjdj_shouli_tjcl_ListAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context = null;
	private List<Map<String, Object>> datas;
	private OnClickListener onClickListener;

	public Sjdj_shouli_tjcl_ListAdapter(Context context,
			List<Map<String, Object>> datas, OnClickListener onClickListener) {
		this.datas = datas;
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.onClickListener = onClickListener;

	}

	public int getCount() {
		return datas.size();
	}

	public Object getItem(int position) {
		return datas.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.sjdj_shouli_tjcl_list_item,
					null);
			holder.tvText_name = (TextView) convertView
					.findViewById(R.id.sjdj_shouli_tjcl_tjcl);
			holder.tvText_yscl = (TextView) convertView
					.findViewById(R.id.sjdj_shouli_tjcl_yscl);
			holder.ib = (ImageButton) convertView
					.findViewById(R.id.ib_sjdj_shouli_tjcl_dzcl);
			holder.cb_shouqu = (CheckBox) convertView
					.findViewById(R.id.cb_sjdj_shouli_tjcj_cb_shouqu);
			holder.cb_caiji = (CheckBox) convertView
					.findViewById(R.id.cb_sjdj_shouli_tjcj_cb_caiji);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvText_name.setText((String) datas.get(position).get("name"));
		holder.tvText_yscl.setText((String) datas.get(position).get("yscl"));
		datas.get(position).put("index", position);
		holder.ib.setOnClickListener(onClickListener);
		holder.ib.setTag(datas.get(position));
		holder.tvText_name.setMovementMethod(ScrollingMovementMethod.getInstance()); 
//		holder.tvText_name.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(context, (String) datas.get(position).get("name"), Toast.LENGTH_SHORT).show();
//			}
//		});
//		
		if (datas.get(position).get("yscl").equals("不收取")) {
			holder.cb_shouqu.setVisibility(View.INVISIBLE);
		}
		// holder.ib.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		//
		// }
		// });
		return convertView;
	}

	class ViewHolder {
		TextView tvText_name;
		TextView tvText_yscl;
		ImageButton ib;
		CheckBox cb_shouqu;
		CheckBox cb_caiji;
		boolean flag;
	}

	public List<Map<String, Object>> getHisInspectRecords() {
		return datas;
	}

	public void setNotifications(List<Map<String, Object>> datas) {
		this.datas = datas;
	}

}
