package com.WSZW.Activity;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ZWFWActivity extends BaseActivity {

	private ViewPager mPager;
	private ArrayList<Fragment> fragmentList;
	private ImageView image;
	private TextView view1, view2, view3, view4, view5, view6; //
	private int currIndex;// 当前页卡编号
	private int bmpW;// 横线图片宽度
	private int offset;// 图片移动的偏移量

	private RelativeLayout rl_title;
	
	FragmentManager fm;
	
	Button btn_menu;
	ImageButton ib_1,ib_2,ib_3,ib_4;
	private PopupWindow mPopupwinow = null;
	private View llyPopView;// PopupWindow显示的View

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zwfw_main);
		fm = getSupportFragmentManager();

		// getDatasFromURL();

		InitTextView();
		InitMenu();
		InitViewPager();

	}

	private void InitMenu() {
		btn_menu = (Button) findViewById(R.id.btn_menu);
		
		btn_menu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mPopupwinow == null) {
					mPopupwinow = new PopupWindow(llyPopView,
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT, true);
					mPopupwinow.setBackgroundDrawable(new ColorDrawable(
							0x00000000));
				}
				mPopupwinow.showAsDropDown(rl_title, 0, 0);
			}
		});
		// PopupWindow 控件
		llyPopView = LayoutInflater.from(ZWFWActivity.this).inflate(
				R.layout.zwfw_menu, null);	
		ib_1 = (ImageButton)llyPopView.findViewById(R.id.ib_1);
		ib_2 = (ImageButton)llyPopView.findViewById(R.id.ib_2);
		ib_3 = (ImageButton)llyPopView.findViewById(R.id.ib_3);
		ib_4 = (ImageButton)llyPopView.findViewById(R.id.ib_4);
		ib_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intents=new Intent(ZWFWActivity.this,ZWFW_FWPY_MainActivity.class);
				startActivity(intents);
				
			}
		});
		ib_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intents=new Intent(ZWFWActivity.this,DrIqActivity.class);
				startActivity(intents);
				
			}
		});
		
		
	}

	/*
	 * 初始化标签名
	 */
	public void InitTextView() {
		view1 = (TextView) findViewById(R.id.tv_guid1);
		view2 = (TextView) findViewById(R.id.tv_guid2);
		view3 = (TextView) findViewById(R.id.tv_guid3);
		view4 = (TextView) findViewById(R.id.tv_guid4);
		view5 = (TextView) findViewById(R.id.tv_guid5); //
		view6 = (TextView) findViewById(R.id.tv_guid6);
		rl_title = (RelativeLayout) findViewById(R.id.rl_title);
		view1.setOnClickListener(new txListener(0));
		view2.setOnClickListener(new txListener(1));
		view3.setOnClickListener(new txListener(2));
		view4.setOnClickListener(new txListener(3));
		view5.setOnClickListener(new txListener(4)); //
		view6.setOnClickListener(new txListener(5));
	}

	@SuppressWarnings("deprecation")
	void setViewsBackgroundNull() {
		view1.setBackgroundDrawable(null);
		view2.setBackgroundDrawable(null);
		view3.setBackgroundDrawable(null);
		view4.setBackgroundDrawable(null);
		view5.setBackgroundDrawable(null);
		view6.setBackgroundDrawable(null);
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
			v.setBackgroundDrawable(ZWFWActivity.this.getResources()
					.getDrawable(R.drawable.text_view_border));
		}
	}

	/*
	 * 初始化ViewPager
	 */
	public void InitViewPager() {
		mPager = (ViewPager) findViewById(R.id.viewpager);
		fragmentList = new ArrayList<Fragment>();
		Fragment_Zwfu_Main_1 fragment_1 = Fragment_Zwfu_Main_1
				.newInstance("this is first fragment");
		Fragment_Zwfu_Main_2 fragment_2 = Fragment_Zwfu_Main_2
				.newInstance("this is second fragment");
		Fragment_Zwfu_Main_3 fragment_3 = Fragment_Zwfu_Main_3
				.newInstance("this is three fragment");
		Fragment_Zwfu_Main_4 fragment_4 = Fragment_Zwfu_Main_4
				.newInstance("this is four fragment");
		Fragment_Zwfu_Main_5 fragment_5 = Fragment_Zwfu_Main_5
				.newInstance("this is five fragment");
		Fragment_Zwfu_Main_6 fragment_6 = Fragment_Zwfu_Main_6
				.newInstance("this is sixth fragment");

		fragmentList.add(fragment_1);
		fragmentList.add(fragment_2);
		fragmentList.add(fragment_3);
		fragmentList.add(fragment_4);
		fragmentList.add(fragment_5);
		fragmentList.add(fragment_6);

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
			int i = currIndex + 1;
			setViewsBackgroundNull();
			setSelectedViewBackground(arg0);

			// Toast.makeText(ZWFWActivity.this, "您选择了第"+i+"个页卡",
			// Toast.LENGTH_SHORT).show();
		}

		@SuppressWarnings("deprecation")
		private void setSelectedViewBackground(int arg) {
			if (arg == 0) {
			//	Constants.SHIXIANG_TYPE = Constants.SHIXIANG_GEREN_QIYE;
				view1.setBackgroundDrawable(ZWFWActivity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 1) {
				//Constants.SHIXIANG_TYPE = Constants.SHIXIANG_GEREN_QIYE;
				view2.setBackgroundDrawable(ZWFWActivity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 2) {
			//	Constants.SHIXIANG_TYPE = Constants.SHIXIANG_XINGZHENGZHIQUAN;
				view3.setBackgroundDrawable(ZWFWActivity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 3) {
			//	Constants.SHIXIANG_TYPE = Constants.SHIXIANG_BUMEN;
				view4.setBackgroundDrawable(ZWFWActivity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 4) {
				view5.setBackgroundDrawable(ZWFWActivity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 5) {
				//Constants.SHIXIANG_TYPE = Constants.SHIXIANG_XINGZHENGCHUFA;
				view6.setBackgroundDrawable(ZWFWActivity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			}
			// Toast.makeText(ZWFWActivity.this, Constants.SHIXIANG_TYPE+"",
			// Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
