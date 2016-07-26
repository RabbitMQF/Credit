package com.WSZW.Activity;

import java.util.ArrayList;

import com.WSZW.adapter.GzlmFragmentPagerAdapter;
import com.WSZW.entity.ChannelItem;
import com.WSZW.entity.ChannelManage;
import com.WSZW.util.BaseTools;
import com.WSZW.view.ColumnHorizontalScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
public class GZLMActivity extends FragmentActivity {
	public static String TAG = "GZLMActivity";
	
	
	/** 自定义HorizontalScrollView */
	private ColumnHorizontalScrollView mColumnHorizontalScrollView;
	LinearLayout mRadioGroup_content;
	LinearLayout ll_more_columns;
	RelativeLayout rl_column;
	private ViewPager mViewPager;
	private ImageView button_more_columns;
	/** 用户选择的新闻分类列表 */
	private ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();
	/** 当前选中的栏目 */
	private int columnSelectIndex = 0;
	/** 左阴影部分 */
	public ImageView shade_left;
	/** 右阴影部分 */
	public ImageView shade_right;
	/** 屏幕宽度 */
	private int mScreenWidth = 0;
	/** Item宽度 */
	private int mItemWidth = 0;
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	private GZLM_Fragment_zwdt fragment_1;

	private GZLM_Fragment_xzdt fragment_2;
	private GZLM_Fragment_bmdt fragment_3;
	private GZLM_Fragment_mtjj fragment_6;
	private GZLM_Fragment_gsgg fragment_7;
	private GZLM_Fragment_bmtz fragment_8;
	private GZLM_Fragment_xqgk fragment_9;
	private GZLM_Fragment_rsxx fragment_10;
//	private GZLM_Fragment_xxgk fragment_11;
	private GZLM_Fragment_ldzc fragment_13;
	private GZLM_Fragment_zcfg fragment_14;
	private GZLM_Fragment_shgl fragment_15;
	private GZLM_Fragment_czxx fragment_16;
	private GZLM_Fragment_yjgl fragment_17;
	private GZLM_Fragment_zdxm fragment_18;
	private GZLM_Fragment_tjxx fragment_19;
	private GZLM_Fragment_ghjh fragment_20;

	
	/** 请求CODE */
	public final static int CHANNELREQUEST = 1;
	/** 调整返回的RESULTCODE */
	public final static int CHANNELRESULT = 0;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gzlm_main);
		mScreenWidth = BaseTools.getWindowsWidth(this);
		mItemWidth = mScreenWidth / 5;// 一个Item宽度为屏幕的1/7
		initView();
	
	}

	private void initView() {
		findViewById(R.id.btn_menu).setVisibility(View.GONE);;
		mColumnHorizontalScrollView = (ColumnHorizontalScrollView) findViewById(R.id.mColumnHorizontalScrollView);
		mRadioGroup_content = (LinearLayout) findViewById(R.id.mRadioGroup_content);
		ll_more_columns = (LinearLayout) findViewById(R.id.ll_more_columns);
		rl_column = (RelativeLayout) findViewById(R.id.rl_column);
		button_more_columns = (ImageView) findViewById(R.id.button_more_columns);
		mViewPager = (ViewPager) findViewById(R.id.mViewPager);
		shade_left = (ImageView) findViewById(R.id.shade_left);
		shade_right = (ImageView) findViewById(R.id.shade_right);

		button_more_columns.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent_channel = new Intent(getApplicationContext(),ChannelActivity.class);
				
				startActivity(intent_channel);
				finish();
				// overridePendingTransition(R.anim.slide_in_right,
				// R.anim.slide_out_left);
			}
		});
		setChangelView();
	}


	/**
	 * 当栏目项发生变化时候调用
	 * */
	private void setChangelView() {
		initColumnData();
		initTabColumn();
		initFragment();
	}

	/**
	 * 初始化Fragment项
	 */
	private void initFragment() {
		fragment_1 = new GZLM_Fragment_zwdt();
		fragment_2 =new GZLM_Fragment_xzdt();
		fragment_3 = new GZLM_Fragment_bmdt();
		fragment_6 = new GZLM_Fragment_mtjj();
		fragment_7 = new GZLM_Fragment_gsgg();
		fragment_8 = new GZLM_Fragment_bmtz();
		fragment_9 = new GZLM_Fragment_xqgk();
		fragment_10 = new GZLM_Fragment_rsxx();
//		fragment_11 = new GZLM_Fragment_xxgk();
		fragment_13 = new GZLM_Fragment_ldzc();
		fragment_14 = new GZLM_Fragment_zcfg();
		fragment_15 = new GZLM_Fragment_shgl();
		fragment_16 = new GZLM_Fragment_czxx();
		fragment_17 = new GZLM_Fragment_yjgl();
		fragment_18 = new GZLM_Fragment_zdxm();
		fragment_19 = new GZLM_Fragment_tjxx();
		fragment_20 = new GZLM_Fragment_ghjh();

		fragments.clear();// 清空

		int count = userChannelList.size();
		for (int i = 0; i < count; i++) {
			Bundle data = new Bundle();
			data.putString("text", userChannelList.get(i).getName());
			data.putInt("id", userChannelList.get(i).getId());
	
			if (userChannelList.get(i).getName().equals("政务动态")) {
				fragments.add(fragment_1);
			}
			if (userChannelList.get(i).getName().equals("乡镇动态")) {
				fragments.add(fragment_2);
			}
			if (userChannelList.get(i).getName().equals("部门动态")) {
				fragments.add(fragment_3);
			}
			if (userChannelList.get(i).getName().equals("媒体聚焦")) {
				fragments.add(fragment_6);
			}
			if (userChannelList.get(i).getName().equals("公告公示")) {
				fragments.add(fragment_7);
			}
			if (userChannelList.get(i).getName().equals("便民通知")) {
				fragments.add(fragment_8);
			}
			if (userChannelList.get(i).getName().equals("县情概括")) {
				fragments.add(fragment_9);
			}
			if (userChannelList.get(i).getName().equals("人事信息")) {
				fragments.add(fragment_10);
			}
//			if (userChannelList.get(i).getName().equals("信息公开")) {
//				fragments.add(fragment_11);
//			}
			if (userChannelList.get(i).getName().equals("领导之窗")) {
				fragments.add(fragment_13);
			}
			if (userChannelList.get(i).getName().equals("政策法规")) {
				fragments.add(fragment_14);
			}
			if (userChannelList.get(i).getName().equals("社会管理")) {
				fragments.add(fragment_15);
			}
			if (userChannelList.get(i).getName().equals("财政信息")) {
				fragments.add(fragment_16);
			}
			if (userChannelList.get(i).getName().equals("应急管理")) {
				fragments.add(fragment_17);
			}
			if (userChannelList.get(i).getName().equals("重大项目")) {
				fragments.add(fragment_18);
			}
			if (userChannelList.get(i).getName().equals("统计信息")) {
				fragments.add(fragment_19);
			}
			if (userChannelList.get(i).getName().equals("规划计划")) {
				fragments.add(fragment_20);
			}
			/*if (userChannelList.get(i).getName().equals("政府文件")) {
				fragments.add(fragment_21);
			}*/

		GzlmFragmentPagerAdapter mAdapetr = new GzlmFragmentPagerAdapter(
				getSupportFragmentManager(), fragments);
		 mViewPager.setOffscreenPageLimit(6);//设置缓存view 的个数（实际有7个，缓存5个+正在显示的1个）
		mViewPager.setAdapter(mAdapetr); 
		mViewPager.setOnPageChangeListener(pageListener);
		}
	}


	/** 获取Column栏目 数据 */

	private void initColumnData() {
		userChannelList =((ArrayList<ChannelItem>) ChannelManage.getManage(ActivityApp.getIntance().getSQLHelper()).getUserChannel());
	}

	/**
	 * 初始化Column栏目项
	 * */
	private void initTabColumn() {
		mRadioGroup_content.removeAllViews();
		int count = userChannelList.size();
		mColumnHorizontalScrollView.setParam(this, mScreenWidth,
				mRadioGroup_content, shade_left, shade_right, ll_more_columns,
				rl_column);
		for (int i = 0; i < count; i++) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					mItemWidth, LayoutParams.WRAP_CONTENT);
			params.leftMargin = 1;
			params.rightMargin = 1;
		
			TextView columnTextView = new TextView(this);
			columnTextView.setTextAppearance(this,
					R.style.top_category_scroll_view_item_text);
			// localTextView.setBackground(getResources().getDrawable(R.drawable.top_category_scroll_text_view_bg));
			columnTextView.setBackgroundResource(R.drawable.radio_buttong_bg);
			columnTextView.setGravity(Gravity.CENTER);
			columnTextView.setPadding(5, 5, 5, 5);
			columnTextView.setId(i);
			columnTextView.setText(userChannelList.get(i).getName());
			columnTextView.setTextColor(getResources().getColorStateList(
					R.color.top_category_scroll_text_color_day));//字体颜色
			if (columnSelectIndex == i) {
				columnTextView.setSelected(true);
			}
			columnTextView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
						View localView = mRadioGroup_content.getChildAt(i);
						if (localView != v)
							localView.setSelected(false);
						else {
							localView.setSelected(true);
							mViewPager.setCurrentItem(i);
						}
					}
//					Toast.makeText(getApplicationContext(),
//							userChannelList.get(v.getId()).getName(),
//							Toast.LENGTH_SHORT).show();
				}
			});
			mRadioGroup_content.addView(columnTextView, i, params);
		}
	}

	/**
	 * 选择的Column里面的Tab
	 * */
	private void selectTab(int tab_postion) {
		columnSelectIndex = tab_postion;
		for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
			View checkView = mRadioGroup_content.getChildAt(tab_postion);
			int k = checkView.getMeasuredWidth();
			int l = checkView.getLeft();
			int i2 = l + k / 2 - mScreenWidth / 2;
			// rg_nav_content.getParent()).smoothScrollTo(i2, 0);
			mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
			// mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
			// mItemWidth , 0);
		}
		// 判断是否选中
		for (int j = 0; j < mRadioGroup_content.getChildCount(); j++) {
			View checkView = mRadioGroup_content.getChildAt(j);
			boolean ischeck;
			if (j == tab_postion) {
				ischeck = true;
			} else {
				ischeck = false;
			}
			checkView.setSelected(ischeck);
		}
	}

	/**
	 * ViewPager切换监听方法
	 * */
	public OnPageChangeListener pageListener = new OnPageChangeListener() {

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
			mViewPager.setCurrentItem(position);
			selectTab(position);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	
	
	
	private long mExitTime;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
				if ((System.currentTimeMillis() - mExitTime) > 2000) {
					Toast.makeText(this, "在按一次退出",
							Toast.LENGTH_SHORT).show();
					mExitTime = System.currentTimeMillis();
				} else {
					finish();
				}
				return true;
			}
		
		//拦截MENU按钮点击事件，让他无任何操作
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			return true;
	}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	protected void onResume() {
		setChangelView();
		super.onResume();
	}
	@Override
	protected void onPause() {
	
		super.onPause();
	}
	@Override
	protected void onStart() {
	
		super.onStart();
	}

}
