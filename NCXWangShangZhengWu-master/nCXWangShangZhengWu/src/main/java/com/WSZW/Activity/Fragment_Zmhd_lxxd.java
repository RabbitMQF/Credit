package com.WSZW.Activity;

import java.util.ArrayList;

import com.WSZW.Activity.ZMHDActivity.MyFragmentPagerAdapter;
import com.WSZW.Activity.ZMHDActivity.MyOnPageChangeListener;
import com.WSZW.Activity.ZMHDActivity.txListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class Fragment_Zmhd_lxxd extends Fragment {
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentList;
	private TextView view1, view2, view3, view4, view5/*, view6*/; //
	private int currIndex;// 当前页卡编号
	private int bmpW;// 横线图片宽度
	private int offset;// 图片移动的偏移量
	View view;
	
	FragmentManager fm;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.e("111", "111");
		 view = inflater.inflate(R.layout.fragment_zmhd_lxxd, container,
				false);
		 fm = getActivity().getSupportFragmentManager();
			InitTextView();
			InitViewPager();
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.e("222", "222");
	}
	/*
	 * 初始化标签名
	 */
	public void InitTextView() {
		view1 = (TextView) view.findViewById(R.id.tv_zx);
		view2 = (TextView) view.findViewById(R.id.tv_ts);
		view3 = (TextView) view.findViewById(R.id.tv_jy);
		view4 = (TextView) view.findViewById(R.id.tv_qz);
		view5 = (TextView) view.findViewById(R.id.tv_qt); 
		/*view6 = (TextView) view.findViewById(R.id.tv_ly);*/
		
		view1.setOnClickListener(new txListener(0));
		view2.setOnClickListener(new txListener(1));
		view3.setOnClickListener(new txListener(2));
		view4.setOnClickListener(new txListener(3));
		view5.setOnClickListener(new txListener(4)); 
		/*view6.setOnClickListener(new txListener(5));*/
	}

	@SuppressWarnings("deprecation")
	void setViewsBackgroundNull() {
		view1.setBackgroundDrawable(null);
		view2.setBackgroundDrawable(null);
		view3.setBackgroundDrawable(null);
		view4.setBackgroundDrawable(null);
		view5.setBackgroundDrawable(null);
		/*view6.setBackgroundDrawable(null);*/
	}

	public class txListener implements View.OnClickListener {
		private int index = 0;

		public txListener(int i) {
			index = i;
		}

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
			
			setViewsBackgroundNull();
			v.setBackgroundDrawable(Fragment_Zmhd_lxxd.this.getActivity().getResources()
					.getDrawable(R.drawable.text_view_border));
		}
	}

	/*
	 * 初始化ViewPager
	 */
	public void InitViewPager() {
		mPager = (ViewPager) view.findViewById(R.id.viewpager);
		fragmentList = new ArrayList<Fragment>();
		ZMHD_Fragment_zx fragment_1 = ZMHD_Fragment_zx
				.newInstance("this is first fragment");
		ZMHD_Fragment_ts fragment_2 = ZMHD_Fragment_ts
				.newInstance("this is second fragment");
		ZMHD_fragment_jy fragment_3 = ZMHD_fragment_jy
				.newInstance("this is four fragment");
		ZMHD_fragment_qz fragment_4 = ZMHD_fragment_qz
				.newInstance("this is three fragment");
		ZMHD_fragment_qt fragment_5 = ZMHD_fragment_qt
				.newInstance("this is five fragment");
		/*ZMHD_fragment_ly fragment_6 = ZMHD_fragment_ly
				.newInstance("this is sixth fragment");*/

		fragmentList.add(fragment_1);
		fragmentList.add(fragment_2);
		fragmentList.add(fragment_3);
		fragmentList.add(fragment_4);
		fragmentList.add(fragment_5);
		/*fragmentList.add(fragment_6);*/

		// 给ViewPager设置适配器
		mPager.setAdapter(new MyFragmentPagerAdapter(fm, fragmentList));
		mPager.setCurrentItem(0);// 设置当前显示标签页为第一页
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());// 页面变化时的监听器

	}

	public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
		ArrayList<Fragment> list;

		public MyFragmentPagerAdapter(FragmentManager fm,
				ArrayList<Fragment> list) {
			super(fm);
			this.list = list;

		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Fragment getItem(int arg0) {
			return list.get(arg0);
		}
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {
		private int one = offset * 2 + bmpW;// 两个相邻页面的偏移量

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = new TranslateAnimation(currIndex * one, arg0
					* one, 0, 0);// 平移动画
			currIndex = arg0;
			animation.setFillAfter(true);// 动画终止时停留在最后一帧，不然会回到没有执行前的状态
			animation.setDuration(200);// 动画持续时间0.2秒
			// image.startAnimation(animation);//是用ImageView来显示动画的
			//int i = currIndex + 1;
			setViewsBackgroundNull();
			setSelectedViewBackground(arg0);

			// Toast.makeText(ZWFWActivity.this, "您选择了第"+i+"个页卡",
			// Toast.LENGTH_SHORT).show();
		}

		@SuppressWarnings("deprecation")
		private void setSelectedViewBackground(int arg) {
			if (arg == 0) {
				view1.setBackgroundDrawable(Fragment_Zmhd_lxxd.this.getActivity().getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 1) {
				view2.setBackgroundDrawable(Fragment_Zmhd_lxxd.this.getActivity().getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 2) {
				view3.setBackgroundDrawable(Fragment_Zmhd_lxxd.this.getActivity().getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 3) {
				view4.setBackgroundDrawable(Fragment_Zmhd_lxxd.this.getActivity().getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 4) {
				view5.setBackgroundDrawable(Fragment_Zmhd_lxxd.this.getActivity().getResources()
						.getDrawable(R.drawable.text_view_border));
			} /*else if (arg == 5) {
				view6.setBackgroundDrawable(Fragment_Zmhd_lxxd.this.getActivity().getResources()
						.getDrawable(R.drawable.text_view_border));
			}*/
		}
	}
	
}
