package com.WSZW.adapter;

import java.util.List;
import java.util.Map;

import com.WSZW.Activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;



/**
 * 通知提醒列表的数据Adapter
 * 
 * @author Goven
 * @date 2012-12-27
 */
public class Sjdj_deny_bltj_tjcl_ListAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context = null;
	private List<Map<String, Object>> datas;

	public Sjdj_deny_bltj_tjcl_ListAdapter(Context context,
			List<Map<String, Object>> datas) {
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
			convertView = inflater.inflate(
					R.layout.sjdj_deny_bltj_tjcl_list_item, null);
			holder.tvText_name = (TextView) convertView
					.findViewById(R.id.tv_name);
			holder.iv = (ImageView) convertView.findViewById(R.id.iv_sjdj);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvText_name.setText((String) datas.get(position).get("name"));
		if ((Boolean) datas.get(position).get("ifChecked")) {
			holder.iv.setBackgroundDrawable(context.getResources().getDrawable(
					R.drawable.icon_have));
		} else {
			holder.iv.setBackgroundDrawable(context.getResources().getDrawable(
					R.drawable.have_no));
		}

		return convertView;
	}

	class ViewHolder {
		TextView tvText_name;
		ImageView iv;
		boolean flag;
	}

	public List<Map<String, Object>> getHisInspectRecords() {
		return datas;
	}

	public void setNotifications(List<Map<String, Object>> datas) {
		this.datas = datas;
	}

}
