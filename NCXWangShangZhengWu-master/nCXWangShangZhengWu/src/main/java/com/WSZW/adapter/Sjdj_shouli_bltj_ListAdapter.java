package com.WSZW.adapter;

import java.util.List;

import com.WSZW.Activity.R;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;



/**
 * 通知提醒列表的数据Adapter
 * 
 * @author Goven
 * @date 2012-12-27
 */
public class Sjdj_shouli_bltj_ListAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context = null;
	private List<String> datas;

	public Sjdj_shouli_bltj_ListAdapter(Context context, List<String> datas) {
		this.datas = datas;
		inflater = LayoutInflater.from(context);
		this.context = context;

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
			convertView = inflater.inflate(R.layout.sjdj_shouli_bltj_list_item,
					null);
			holder.tvText_name = (TextView) convertView
					.findViewById(R.id.tv_sjdj_shouli_bltj_item);
			holder.cb = (CheckBox) convertView
					.findViewById(R.id.cb_sjdj_shouli_bltj);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvText_name.setText(datas.get(position));
		holder.tvText_name.setMovementMethod(ScrollingMovementMethod.getInstance()); 
//		holder.tvText_name.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(context, (String) datas.get(position), Toast.LENGTH_SHORT).show();
//			}
//		});
		
		
		return convertView;
	}

	class ViewHolder {
		TextView tvText_name;
		CheckBox cb;
		boolean flag;
	}

	public List<String> getHisInspectRecords() {
		return datas;
	}

	public void setNotifications(List<String> datas) {
		this.datas = datas;
	}

}
