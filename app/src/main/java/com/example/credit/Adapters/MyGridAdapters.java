package com.example.credit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.credit.R;
import com.example.credit.Views.BaseViewHolder;

/**
 * @Description:gridview的Adapter
 * @author http://blog.csdn.net/finddreams
 */
public class MyGridAdapters extends BaseAdapter {
	private Context mContext;
	private int[] imgs;

	public MyGridAdapters(Context context,  int[] imgs1) {
		super();
		this.mContext = context;
		imgs=imgs1;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(imgs.length%4==0){
			return imgs.length;
		}else{
			return (4-imgs.length%4)+imgs.length;
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.grid_items, parent, false);
		}
		if(imgs.length%4!=0){
			if((imgs.length-1)<position){
				return convertView;
			}
		}
		ImageView iv = BaseViewHolder.get(convertView, R.id.iv_items);

		iv.setBackgroundResource(imgs[position]);
		return convertView;
	}

}
