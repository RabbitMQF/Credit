package com.WSZW.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.WSZW.Activity.R.color;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.ZWFW_ShiXiang_Detail_bean;
import com.WSZW.service.ZWFW_Shixiang_Detail_ManagerService;
import com.WSZW.util.StringUtil;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class ZWFW_Detail_Activity extends BaseActivity implements OnClickListener{
	InfoFile_ infofile;
	ZWFW_ShiXiang_Detail_bean bean;
	TextView tv_title,tv_1,tv_2,tv_3,tv_5,tv_6,tv_7,tv_8,tv_9;
	TextView tv_guide1,tv_guide2,tv_guide3,tv_guide4,tv_guide5,tv_guide6,tv_guide7;
	Button btn_back,btn_apply,btn_next;
	LinearLayout tv_4;
	ViewPager viewpager;
	ScrollView scroll1;
	LinearLayout ll2;
	
	private ArrayList<Fragment> fragmentList;
	private int currIndex;// 当前页卡编号
	private int bmpW;// 横线图片宽度
	private int offset;// 图片移动的偏移量
	List<Map<String,Object>> list_blyj = null;
	
	FragmentManager fm;
	boolean flag_next = false;
	
	int type = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zwfw_shixiang_detail_main);
		infofile = new InfoFile_(this);
		fm = getSupportFragmentManager();
		initViews();
		detail_manager.get_ZWFW_Shixiang_Detail(context, 1, infofile.shixiang_id().get().toString() != null ? infofile.shixiang_id().get() : "null");
	}

	
	private ZWFW_Shixiang_Detail_ManagerService detail_manager = new ZWFW_Shixiang_Detail_ManagerService() {
		
		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
			if(paramHandleResult.getiSuccess().equals("success")){
				bean = new ZWFW_ShiXiang_Detail_bean();
				bean = paramHandleResult.getBean();
				if(bean != null){
					initDatas();
					InitViewPager();
				}
			}
		}
	};
	
	private void handleDatasBLYJ(String str,LinearLayout tv){
		list_blyj = new ArrayList<Map<String,Object>>();
		if(!str.equals("")){
			str = str.replace("\"", "");
			List<String> list = new ArrayList<String>();
			list = StringUtil.stringToNormalListBy(str);
			if(list.size() != 0){
				for(int i=0;i<list.size();i++){
					if(!list.get(i).equals("") || !list.get(i).contains("http")){
						List<String> list_1 = new ArrayList<String>();
						list_1 = StringUtil.stringToNormalList(list.get(i));
						Map<String,Object> map = new HashMap<String, Object>();
						if(list_1.size() == 2){
							map.put("url", list_1.get(0));
							map.put("name", list_1.get(1));
							list_blyj.add(map);
						}
					}
				}
			}
		}
		if(list_blyj.size() != 0){
			for( int i=0;i<list_blyj.size();i++){
				TextView tvs = new TextView(this);
				tvs.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				tvs.setTextColor(getResources().getColor(color.blue_2));
				tvs.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
				tvs.setTextSize(16);
				tvs.setText((String) list_blyj.get(i).get("name"));
				final int j = i;
				tvs.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						 Intent intent = new Intent();        
				            intent.setAction("android.intent.action.VIEW");    
				            Uri content_url = Uri.parse((String) list_blyj.get(j).get("url"));   
				            intent.setData(content_url);  
				            startActivity(intent);
						//Toast.makeText(ZWFW_Detail_Activity.this, (CharSequence) list_blyj.get(j).get("url"), Toast.LENGTH_SHORT).show();
					}
				});
				tv_4.addView(tvs);
			}
		}else{
			TextView tvs = new TextView(this);
			tvs.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			tvs.setTextColor(getResources().getColor(color.blue_2));
			tvs.setTextSize(16);
			tvs.setText("无");
			tv_4.addView(tvs);
		}
	}
	
	private void initDatas() {
		tv_title.setText(infofile.shixiang_name().get() != null ? infofile.shixiang_name().get() : "");
		tv_1.setText(bean.getServiceCode() != null && !bean.getServiceCode().equals("null")? bean.getServiceCode() : "无");
		tv_2.setText(bean.getServiceObject() != null && !bean.getServiceObject().equals("null")? bean.getServiceObject() : "无");
		tv_3.setText(bean.getProcessingConditions() != null && !bean.getProcessingConditions().equals("null")? bean.getProcessingConditions() : "无");
		handleDatasBLYJ(bean.getPolicyBasis() != null && !bean.getPolicyBasis().equals("null")? bean.getPolicyBasis() : "无",tv_4);
	//	tv_4.setText(bean.getPolicyBasis() != null && !bean.getPolicyBasis().equals("null")? bean.getPolicyBasis() : "无");
		tv_5.setText(bean.getTerm() != null && !bean.getTerm().equals("null")? bean.getTerm() : "无");
		tv_6.setText(bean.getBlsj() != null && !bean.getBlsj().equals("null")? bean.getBlsj() : "无");
		tv_7.setText(bean.getFaq() != null && !bean.getFaq().equals("null")? bean.getFaq() : "无");
		tv_8.setText(bean.getTel() != null && !bean.getTel().equals("null")? bean.getTel() : "无");
		tv_9.setText(bean.getComplaintsHotline() != null && !bean.getComplaintsHotline().equals("null")? bean.getComplaintsHotline() : "无");
	}
	
	private void initViews() {
		tv_title = (TextView)findViewById(R.id.tv_title);
		tv_1 = (TextView)findViewById(R.id.tv_1);
		tv_2 = (TextView)findViewById(R.id.tv_2);
		tv_3 = (TextView)findViewById(R.id.tv_3);
		tv_4 = (LinearLayout)findViewById(R.id.tv_4);
		tv_5 = (TextView)findViewById(R.id.tv_5);
		tv_6 = (TextView)findViewById(R.id.tv_6);
		tv_7 = (TextView)findViewById(R.id.tv_7);
		tv_8 = (TextView)findViewById(R.id.tv_8);
		tv_9 = (TextView)findViewById(R.id.tv_9);
		tv_guide1 = (TextView)findViewById(R.id.tv_guid1);
		tv_guide2 = (TextView)findViewById(R.id.tv_guid2);
		tv_guide3 = (TextView)findViewById(R.id.tv_guid3);
		tv_guide4 = (TextView)findViewById(R.id.tv_guid4);
		tv_guide5 = (TextView)findViewById(R.id.tv_guid5);
		tv_guide6 = (TextView)findViewById(R.id.tv_guid6);
		tv_guide7 = (TextView)findViewById(R.id.tv_guid7);
		btn_back = (Button)findViewById(R.id.btn_back);
		btn_apply = (Button)findViewById(R.id.btn_apply);
		btn_next = (Button)findViewById(R.id.btn_next);
		viewpager = (ViewPager)findViewById(R.id.viewpager);
		scroll1 = (ScrollView)findViewById(R.id.scroll1);
		ll2 = (LinearLayout)findViewById(R.id.detail_2);
		
		btn_back.setOnClickListener(this);
		btn_apply.setOnClickListener(this);
		btn_next.setOnClickListener(this);
		
		tv_guide1.setOnClickListener(new txListener(0));
		tv_guide2.setOnClickListener(new txListener(1));
		tv_guide3.setOnClickListener(new txListener(2));
		tv_guide4.setOnClickListener(new txListener(3));
		tv_guide5.setOnClickListener(new txListener(4)); //
		tv_guide6.setOnClickListener(new txListener(5));
		tv_guide7.setOnClickListener(new txListener(6));
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_apply:
			break;
		case R.id.btn_back:
			this.finish();
			break;
		case R.id.btn_next:
			if(flag_next){
				btn_next.setText("下一页");
				scroll1.setVisibility(View.VISIBLE);
				ll2.setVisibility(View.GONE);
				flag_next = false;
			}else{
				btn_next.setText("上一页");
				flag_next = true;
				scroll1.setVisibility(View.GONE);
				ll2.setVisibility(View.VISIBLE);
			}
			break;

		default:
			break;
		}
	}

	
	
	public class txListener implements View.OnClickListener {
		private int index = 0;

		public txListener(int i) {
			index = i;
		}

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {
			viewpager.setCurrentItem(index);
			setViewsBackgroundNull();
			v.setBackgroundDrawable(ZWFW_Detail_Activity.this.getResources()
					.getDrawable(R.drawable.text_view_border));
		}
	}
	
	
	@SuppressWarnings("deprecation")
	void setViewsBackgroundNull() {
		tv_guide1.setBackgroundDrawable(null);
		tv_guide2.setBackgroundDrawable(null);
		tv_guide3.setBackgroundDrawable(null);
		tv_guide4.setBackgroundDrawable(null);
		tv_guide5.setBackgroundDrawable(null);
		tv_guide6.setBackgroundDrawable(null);
		tv_guide7.setBackgroundDrawable(null);
	}
	
	
	/*
	 * 初始化ViewPager
	 */
	public void InitViewPager() {
		fragmentList = new ArrayList<Fragment>();
		Fragment_Zwfw_Detail_1 fragment_1 = Fragment_Zwfw_Detail_1
				.newInstance(bean.getSubmittedMaterials()!= null ? bean.getSubmittedMaterials() : "");
		Fragment_Zwfw_Detail_2 fragment_2 = Fragment_Zwfw_Detail_2
				.newInstance(bean.getWindowWorkflow()!= null ? bean.getWindowWorkflow() : "");
		Fragment_Zwfw_Detail_2 fragment_3 = Fragment_Zwfw_Detail_2
				.newInstance(bean.getPowerWorkflow()!= null ? bean.getPowerWorkflow() : "");
		Fragment_Zwfw_Detail_1 fragment_4 = Fragment_Zwfw_Detail_1
				.newInstance(bean.getServiceSubjectPost()!= null ? bean.getServiceSubjectPost() : "");
		Fragment_Zwfw_Detail_1 fragment_5 = Fragment_Zwfw_Detail_1
				.newInstance(bean.getChargeBasisAndStandard()!= null ? bean.getChargeBasisAndStandard() : "");
		Fragment_Zwfw_Detail_1 fragment_6 = Fragment_Zwfw_Detail_1
				.newInstance(bean.getServiceWindow()!= null ? bean.getServiceWindow() : "");
		Fragment_Zwfw_Detail_1 fragment_7 = Fragment_Zwfw_Detail_1
				.newInstance(bean.getForm()!= null ? bean.getForm() : "");

		fragmentList.add(fragment_1);
		fragmentList.add(fragment_2);
		fragmentList.add(fragment_3);
		fragmentList.add(fragment_4);
		fragmentList.add(fragment_5);
		fragmentList.add(fragment_6);
	//	fragmentList.add(fragment_7);

		// 给ViewPager设置适配器
		viewpager.setAdapter(new MyFragmentPagerAdapter(fm, fragmentList));
		viewpager.setCurrentItem(0);// 设置当前显示标签页为第一页
		viewpager.setOnPageChangeListener(new MyOnPageChangeListener());// 页面变化时的监听器

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
				tv_guide1.setBackgroundDrawable(ZWFW_Detail_Activity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 1) {
				tv_guide2.setBackgroundDrawable(ZWFW_Detail_Activity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 2) {
				tv_guide3.setBackgroundDrawable(ZWFW_Detail_Activity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 3) {
				tv_guide4.setBackgroundDrawable(ZWFW_Detail_Activity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 4) {
				tv_guide5.setBackgroundDrawable(ZWFW_Detail_Activity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 5) {
				tv_guide6.setBackgroundDrawable(ZWFW_Detail_Activity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			} else if (arg == 6) {
				tv_guide7.setBackgroundDrawable(ZWFW_Detail_Activity.this.getResources()
						.getDrawable(R.drawable.text_view_border));
			}
			// Toast.makeText(ZWFWActivity.this, Constants.SHIXIANG_TYPE+"",
			// Toast.LENGTH_SHORT).show();
		}
	}

	
	
}
