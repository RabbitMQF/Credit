package com.WSZW.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.WSZW.Activity.R;
import com.WSZW.Activity.ZWFW_List_Activity;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.Zwfw_Shixiang_List_Item;
import com.WSZW.entity.Zwfw_main_listview_item;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Zwfw_main_listview_adapter extends BaseAdapter {

	private Context context;
	private List<Zwfw_main_listview_item> datas;
	private LayoutInflater inflater;
	private InfoFile_ infofile;

	public Zwfw_main_listview_adapter(Context context,
			List<Zwfw_main_listview_item> datas) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		this.datas = datas;
		this.infofile = new InfoFile_(context);
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

	@SuppressLint("NewApi")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(
					R.layout.fragment_zwfw_main_listview_item, null);
			holder.tv_1 = (TextView) convertView.findViewById(R.id.tv_1);
			holder.tv_2 = (TextView) convertView.findViewById(R.id.tv_2);
			holder.tv_3 = (TextView) convertView.findViewById(R.id.tv_3);
			holder.tv_4 = (TextView) convertView.findViewById(R.id.tv_4);

			holder.iv_1 = (ImageView) convertView.findViewById(R.id.iv_1);
			holder.iv_2 = (ImageView) convertView.findViewById(R.id.iv_2);
			holder.iv_3 = (ImageView) convertView.findViewById(R.id.iv_3);
			holder.iv_4 = (ImageView) convertView.findViewById(R.id.iv_4);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (datas.get(position).getId_1() != null) {
			holder.tv_1.setText(datas.get(position).getName_1());
			setIcon(datas.get(position).getPic_name_1(), holder.iv_1);
		} else {
			holder.tv_1.setVisibility(View.GONE);
			holder.iv_1.setVisibility(View.GONE);
		}
		if (datas.get(position).getId_2() != null) {
			holder.tv_2.setText(datas.get(position).getName_2());
			setIcon(datas.get(position).getPic_name_2(), holder.iv_2);
		} else {
			holder.tv_2.setVisibility(View.GONE);
			holder.iv_2.setVisibility(View.GONE);
		}
		if (datas.get(position).getId_3() != null) {
			holder.tv_3.setText(datas.get(position).getName_3());
			setIcon(datas.get(position).getPic_name_3(), holder.iv_3);
		} else {
			holder.tv_3.setVisibility(View.GONE);
			holder.iv_3.setVisibility(View.GONE);
		}
		if (datas.get(position).getId_4() != null) {
			holder.tv_4.setText(datas.get(position).getName_4());
			setIcon(datas.get(position).getPic_name_4(), holder.iv_4);
		} else {
			holder.tv_4.setVisibility(View.GONE);
			holder.iv_4.setVisibility(View.GONE);
		}

		holder.iv_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Constants.list_zwfw_shixiang = new ArrayList<Zwfw_Shixiang_List_Item>();
				infofile.edit().zhutiID().put(datas.get(position).getId_1())
						.apply();
				infofile.edit().zhutiName()
						.put(datas.get(position).getName_1()).apply();
				context.startActivity(new Intent(context,
						ZWFW_List_Activity.class).addFlags(0));
			}
		});
		holder.iv_2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Constants.list_zwfw_shixiang = new ArrayList<Zwfw_Shixiang_List_Item>();
				infofile.edit().zhutiID().put(datas.get(position).getId_2())
						.apply();
				infofile.edit().zhutiName()
						.put(datas.get(position).getName_2()).apply();
				context.startActivity(new Intent(context,
						ZWFW_List_Activity.class).addFlags(0));
			}
		});
		holder.iv_3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Constants.list_zwfw_shixiang = new ArrayList<Zwfw_Shixiang_List_Item>();
				infofile.edit().zhutiID().put(datas.get(position).getId_3())
						.apply();
				infofile.edit().zhutiName()
						.put(datas.get(position).getName_3()).apply();
				context.startActivity(new Intent(context,
						ZWFW_List_Activity.class).addFlags(0));
			}
		});
		holder.iv_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Constants.list_zwfw_shixiang = new ArrayList<Zwfw_Shixiang_List_Item>();
				infofile.edit().zhutiID().put(datas.get(position).getId_4())
						.apply();
				infofile.edit().zhutiName()
						.put(datas.get(position).getName_4()).apply();
				context.startActivity(new Intent(context,
						ZWFW_List_Activity.class).addFlags(0));
			}
		});

		return convertView;
	}

	class ViewHolder {
		TextView tv_1, tv_2, tv_3, tv_4;
		ImageView iv_1, iv_2, iv_3, iv_4;
		boolean flag;
	}
	
	private void setIcon(String str,ImageView iv){
		if(str.equals("zhuti/2.gif")){
			str = "root_gr_jy.gif";
		}
		if(str.contains("iphone/") || str.contains("default/")){
			str = str.replace("iphone/", "");
			str = str.replace("default/", "");
		}
		InputStream inputStream = null;
		try {
			inputStream = context.getAssets().open(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Drawable backgronud = Drawable.createFromStream(inputStream, str);
		if(backgronud != null){
			iv.setImageDrawable(backgronud);
		}
	}

}
