package com.WSZW.Activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class ZWGKActivity extends BaseActivity {
	
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentList;
	private TextView view1, view2, view3;
	
	FragmentManager fm;
	private int currIndex;// 当前页卡编号
	private int bmpW;// 横线图片宽度
	private int offset;// 图片移动的偏移量
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zwgk_main);
		
		fm=getSupportFragmentManager();
		
		initTextView();
		InitViewPager();
		
	}

	private void initTextView() {
		view1 = (TextView) findViewById(R.id.tv_guid1);
		view2 = (TextView) findViewById(R.id.tv_guid2);
		view3 = (TextView) findViewById(R.id.tv_guid3);
		view1.setOnClickListener(new txListener(0));
		view2.setOnClickListener(new txListener(1));
		view3.setOnClickListener(new txListener(2));
	}
	void setViewsBackgroundNull() {
		view1.setBackgroundDrawable(null);
		view2.setBackgroundDrawable(null);
		view3.setBackgroundDrawable(null);
	}
	/*
	 * 初始化ViewPager
	 */
	public void InitViewPager() {
		mPager = (ViewPager) findViewById(R.id.viewpager);
		fragmentList = new ArrayList<Fragment>();
		Fragment_ZwGk_Main_1 fragment_1 = Fragment_ZwGk_Main_1
				.newInstance("this is first fragment");
		Fragment_ZwGk_Main_2 fragment_2 = new Fragment_ZwGk_Main_2();
		Fragment_ZwGk_Main_3 fragment_3 = new Fragment_ZwGk_Main_3();
				;
		
		fragmentList.add(fragment_1);
		fragmentList.add(fragment_2);
		fragmentList.add(fragment_3);
		// 给ViewPager设置适配器
		mPager.setAdapter(new MyFragmentPagerAdapter(fm, fragmentList));
		mPager.setCurrentItem(0);// 设置当前显示标签页为第一页
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());// 页面变化时的监听器

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
			v.setBackgroundDrawable(ZWGKActivity.this.getResources()
					.getDrawable(R.drawable.text_view_border));
		}

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
			int i = currIndex + 1;
			setViewsBackgroundNull();
			setSelectedViewBackground(arg0);

			// Toast.makeText(ZWFWActivity.this, "您选择了第"+i+"个页卡",
			// Toast.LENGTH_SHORT).show();
		}

		private void setViewsBackgroundNull() {
			view1.setBackgroundDrawable(null);
			view2.setBackgroundDrawable(null);
			view3.setBackgroundDrawable(null);
			
		}

		@SuppressWarnings("deprecation")
		private void setSelectedViewBackground(int arg) {
			if (arg == 0) {
			//	Constants.SHIXIANG_TYPE = Constants.SHIXIANG_GEREN_QIYE;
				view1.setBackgroundDrawable(ZWGKActivity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 1) {
				//Constants.SHIXIANG_TYPE = Constants.SHIXIANG_GEREN_QIYE;
				view2.setBackgroundDrawable(ZWGKActivity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 2) {
			//	Constants.SHIXIANG_TYPE = Constants.SHIXIANG_XINGZHENGZHIQUAN;
				view3.setBackgroundDrawable(ZWGKActivity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			} 
			// Toast.makeText(ZWFWActivity.this, Constants.SHIXIANG_TYPE+"",
			// Toast.LENGTH_SHORT).show();
		}
	}
}
