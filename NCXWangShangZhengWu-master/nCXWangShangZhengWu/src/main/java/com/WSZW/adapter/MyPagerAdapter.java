package com.WSZW.adapter;

import java.lang.reflect.Field;
import java.util.List;






import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

public class MyPagerAdapter extends PagerAdapter{
	private List<View> mList;
	Context context;
	public MyPagerAdapter(Context context,List<View> list) {
		this.mList = list;
		this.context = context;
	}
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view==object;
	}
	public Object instantiateItem( View view, int position) {
       ((ViewPager) view).addView(mList.get(position)); 
		return mList.get(position);
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mList.get(position));
	}

}
