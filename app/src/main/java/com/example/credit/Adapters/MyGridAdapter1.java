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
public class MyGridAdapter1 extends BaseAdapter {
	private Context mContext;
	private String[] arrays1;
	private String[] arrays2;
	private int[] imgs;

	public MyGridAdapter1(Context context, String[] array1, String[] array2 , int[] imgs1) {
		super();
		this.mContext = context;
		arrays1=array1;
		arrays2=array2;
		imgs=imgs1;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(arrays1.length%4==0){
			return arrays1.length;
		}else{
			return (4-arrays1.length%4)+arrays1.length;
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
					R.layout.grid_item1, parent, false);
		}
		if(arrays1.length%4!=0){
			if((arrays1.length-1)<position){
				return convertView;
			}
		}
		TextView tv1 = BaseViewHolder.get(convertView, R.id.tv_item1);
		TextView tv2 = BaseViewHolder.get(convertView, R.id.tv_item2);
		ImageView iv = BaseViewHolder.get(convertView, R.id.iv_item);

		ImageView logo = BaseViewHolder.get(convertView, R.id.logo);

		iv.setBackgroundResource(imgs[position]);
		tv1.setText(arrays1[position]);
//		tv2.setText(arrays2[position]);
		return convertView;
	}

}
