package com.WSZW.Activity;


import java.util.ArrayList;

import com.WSZW.Activity.ZWFWActivity.MyFragmentPagerAdapter;
import com.WSZW.Activity.ZWFWActivity.MyOnPageChangeListener;
import com.WSZW.Activity.ZWFWActivity.txListener;
import com.WSZW.data.Constants;
import com.WSZW.view.SampleListFragment;
import com.ZWPT.sliding.SlidingFragmentActivity;
import com.ZWPT.sliding.SlidingMenu;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class ZMHDActivity extends SlidingFragmentActivity implements OnClickListener{
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentList;
	private TextView view1, view2, view3, view4, view5, view6, view7; //
	private int currIndex;// 当前页卡编号
	private int bmpW;// 横线图片宽度
	private int offset;// 图片移动的偏移量

	
	FragmentManager fm;
	
	Button btn_menu;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zmhd_main);
		fm = getSupportFragmentManager();
		initSlidingMenu(savedInstanceState);
		InitTextView();
		InitViewPager();
	}
	
	SlidingMenu sm;
	/**
	 * 初始化滑动菜单
	 */
	protected void initSlidingMenu(Bundle savedInstanceState) {
		// 设置滑动菜单的视图
		setBehindContentView(R.layout.sliding_menu_frame);
	//	if (Constants.list_leftmenu.size() > 0) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.menu_frame, new SampleListFragment(this))
					.commit();
	//	}
		// 实例化滑动菜单对象
		sm = getSlidingMenu();
		// 设置滑动阴影的宽度
		sm.setShadowWidthRes(R.dimen.shadow_width);
		// 设置滑动阴影的图像资源
		sm.setShadowDrawable(R.drawable.shadow);
		// 设置滑动菜单视图的宽度
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// 设置渐入渐出效果的值
		sm.setFadeDegree(0.35f);
		// 设置触摸屏幕的模式
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

	}
	
	
	/*
	 * 初始化标签名
	 */
	public void InitTextView() {
		view1 = (TextView) findViewById(R.id.tv_zx);
		view2 = (TextView) findViewById(R.id.tv_ts);
		view3 = (TextView) findViewById(R.id.tv_jy);
		view4 = (TextView) findViewById(R.id.tv_qz);
		view5 = (TextView) findViewById(R.id.tv_qt); 
		view6 = (TextView) findViewById(R.id.tv_ly);
		view7 = (TextView) findViewById(R.id.tv_xw);
		btn_menu = (Button) findViewById(R.id.btn_menu);
		view1.setOnClickListener(new txListener(0));
		view2.setOnClickListener(new txListener(1));
		view3.setOnClickListener(new txListener(2));
		view4.setOnClickListener(new txListener(3));
		view5.setOnClickListener(new txListener(4)); 
		view6.setOnClickListener(new txListener(5));
		view7.setOnClickListener(new txListener(6));
		btn_menu.setOnClickListener(this);
	}

	@SuppressWarnings("deprecation")
	void setViewsBackgroundNull() {
		view1.setBackgroundDrawable(null);
		view2.setBackgroundDrawable(null);
		view3.setBackgroundDrawable(null);
		view4.setBackgroundDrawable(null);
		view5.setBackgroundDrawable(null);
		view6.setBackgroundDrawable(null);
		view7.setBackgroundDrawable(null);
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
			v.setBackgroundDrawable(ZMHDActivity.this.getResources()
					.getDrawable(R.drawable.text_view_border));
		}
	}

	/*
	 * 初始化ViewPager
	 */
	public void InitViewPager() {
		mPager = (ViewPager) findViewById(R.id.viewpager);
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
		ZMHD_fragment_ly fragment_6 = ZMHD_fragment_ly
				.newInstance("this is sixth fragment");
		ZMHD_Fragment_xw fragment_7 = ZMHD_Fragment_xw
				.newInstance("this is first fragment");

		fragmentList.add(fragment_1);
		fragmentList.add(fragment_2);
		fragmentList.add(fragment_3);
		fragmentList.add(fragment_4);
		fragmentList.add(fragment_5);
		fragmentList.add(fragment_6);
		fragmentList.add(fragment_7);

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
				view1.setBackgroundDrawable(ZMHDActivity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 1) {
				view2.setBackgroundDrawable(ZMHDActivity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 2) {
				view3.setBackgroundDrawable(ZMHDActivity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 3) {
				view4.setBackgroundDrawable(ZMHDActivity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 4) {
				view5.setBackgroundDrawable(ZMHDActivity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 5) {
				view6.setBackgroundDrawable(ZMHDActivity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
				
			}else if (arg == 6) {
				view7.setBackgroundDrawable(ZMHDActivity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private boolean isExit = false;

	/**
	 * 双击退出
	 */
	public void exitBy2Click() {

		if (!isExit) {
			isExit = true; // 准备退出
			Toast.makeText(this, "再按一次退出程序", 2000).show();

			new Thread(new Runnable() {

				@Override
				public void run() {
					SystemClock.sleep(2000);
					isExit = false; // 取消退出
				}
			}).start();
		} 

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click();

		}
		return false;
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_menu:
			if(sm.isMenuShowing()){
				sm.toggle();
			}else{
				sm.showMenu(true);
			}
			break;

		default:
			break;
		}
		
	}

}





