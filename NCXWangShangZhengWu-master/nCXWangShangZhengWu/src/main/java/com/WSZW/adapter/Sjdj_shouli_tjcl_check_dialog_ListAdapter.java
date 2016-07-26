package com.WSZW.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.WSZW.Activity.R;
import com.WSZW.data.Constants;
import com.WSZW.entity.blcl_checkbox;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 通知提醒列表的数据Adapter
 * 
 * @author Goven
 * @date 2012-12-27
 */
public class Sjdj_shouli_tjcl_check_dialog_ListAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private Context context = null;
	private List<blcl_checkbox> datas;
	int sizeofrb = 0;

	public Sjdj_shouli_tjcl_check_dialog_ListAdapter(Context context,
			List<blcl_checkbox> datas) {
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
		// if (convertView == null) {
		holder = new ViewHolder();

		convertView = inflater.inflate(
				R.layout.sjdj_shouli_tjcl_dialog_list_item, null);
		holder.tvText_name = (TextView) convertView
				.findViewById(R.id.tv_sjdj_shouli_bltj_item);
		holder.rg = (RadioGroup) convertView
				.findViewById(R.id.rg_sjdj_tjcl_dialog_rg);

		convertView.setTag(holder);

		// } else {
		//
		// holder = (ViewHolder) convertView.getTag();
		// }
		holder.tvText_name.setText(datas.get(position).getBlcl_first_name());
		sizeofrb = datas.get(position).getBlcl_second_name().size();
		for (Integer i = 0; i < sizeofrb; i++) {
			RadioButton rb = new RadioButton(context);
			rb.setId(i);
			// rb.setButtonDrawable(context.getResources().getDrawable(R.drawable.radiobutton_style));
			rb.setTextColor(context.getResources().getColor(R.color.black));
			rb.setText((String) datas.get(position).getBlcl_second_name()
					.get(i).get("second_name"));
			holder.rg.addView(rb, LinearLayout.LayoutParams.FILL_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
		}

		holder.rg.check(0);
		for (Integer i = 0; i < Constants.list_middle.size(); i++) {
			if ((Integer) Constants.list_middle.get(i).get("rg_id") == position) {
				// Constants.list_middle.get(i)
				// .put("list_tjcl",
				// datas.get(position)
				// .getBlcl_second_name()
				// .get(0)
				// .get("second_blcl_list"));
				Constants.list_middle.get(i).put("checked_id", 1);
			}
		}
		holder.rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				for (Integer i = 0; i < Constants.list_middle.size(); i++) {
					if ((Integer) Constants.list_middle.get(i).get("rg_id") == position) {
						// Constants.list_middle.get(i)
						// .put("list_tjcl",
						// datas.get(position)
						// .getBlcl_second_name()
						// .get(checkedId)
						// .get("second_blcl_list"));
						Constants.list_middle.get(i).put("checked_id",
								checkedId + 1);
					}
				}
			}
		});

		return convertView;
	}

	class ViewHolder {
		TextView tvText_name;
		RadioGroup rg;
		boolean flag;
	}

	public List<blcl_checkbox> getHisInspectRecords() {
		return datas;
	}

	public void setNotifications(List<blcl_checkbox> datas) {
		this.datas = datas;
	}

}
