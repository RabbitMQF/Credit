package com.WSZW.adapter;

import java.util.List;

import com.WSZW.Activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * PopupWindow中listView的适配器 内容为单行
 * 
 * @author x.y
 * @data 2013-5-21
 */
public class PopupWindowSingleAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<String> names;

	public PopupWindowSingleAdapter(Context context, List<String> names) {
		this.names = names;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return names.size();
	}

	@Override
	public Object getItem(int position) {
		return names.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_single_popup_window,
					null);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvName.setText((String) (names.get(position)));
		return convertView;
	}

	class ViewHolder {
		TextView tvName;
	}

}
