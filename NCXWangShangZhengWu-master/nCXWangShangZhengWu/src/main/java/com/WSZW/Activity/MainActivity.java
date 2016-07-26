package com.WSZW.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.WSZW.data.Constants;
import com.WSZW.entity.HandleResult;
import com.WSZW.service.ZWFW_BM_ManagerService;
import com.WSZW.service.ZWFW_CX_ManagerService;
import com.WSZW.service.ZWFW_PJTJ_ManagerService;
import com.WSZW.service.ZWFW_PJYZ_ManagerService;
import com.WSZW.service.ZWFW_XZ_ManagerService;
import com.WSZW.service.ZWFW_Zhuti_ManagerService;
import com.umeng.update.UmengUpdateAgent;

import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainActivity extends BaseActivity {
	private TabHost myTabHost;
	private String[] tags = new String[] { "Tab_new", "Tab_priture",
			"Tab_video", "Tab_box" };
	private int[] tImg = new int[] { R.drawable.bg_checkbox_icon_menu_0,
			R.drawable.bg_checkbox_icon_menu_1,
			R.drawable.bg_checkbox_icon_menu_2,
			R.drawable.bg_checkbox_icon_menu_3 };
	private ImageView mImage;
	private View mView;
	private Intent[] intents = new Intent[4];
	private Intent intent1, intent2, intent3, intent4;
	private LocalActivityManager mActivityManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		UmengUpdateAgent.setUpdateOnlyWifi(false);
		UmengUpdateAgent.update(this);
		myTabHost = (TabHost) this.findViewById(R.id.myTabHost);
		mActivityManager = new LocalActivityManager(MainActivity.this, false);
		mActivityManager.dispatchCreate(savedInstanceState);
		myTabHost.setup(mActivityManager);
		init();
		for (int i = 0; i < intents.length; i++) {
			mView = getLayoutInflater().inflate(R.layout.tab_item, null);
			mImage = (ImageView) mView.findViewById(R.id.mImg);
			
			// mText = (TextView) mView.findViewById(R.id.mTxt);
			
			mImage.setImageResource(tImg[i]);
			
			
			// mText.setText(titileStrings[i]);
			TabSpec tabSpec = myTabHost.newTabSpec(tags[i]);
			tabSpec.setIndicator(mView).setContent(intents[i]);
			myTabHost.addTab(tabSpec);
			myTabHost.setOnTabChangedListener(onTabChangeListener);
		}

		getDatasFromURL();
	}

	/*
	 * 进入政务服务专栏，首先判断是否已经获取过主题列表，如果没有获取过则从服务器获取
	 */
	private void getDatasFromURL() {
		if (Constants.list_zwfw_zhuti_gr_rssj.size() == 0
				|| Constants.list_zwfw_zhuti_gr_tdqt.size() == 0
				|| Constants.list_zwfw_zhuti_gr_ztfl.size() == 0
				|| Constants.list_zwfw_zhuti_qy_jyhd.size() == 0
				|| Constants.list_zwfw_zhuti_qy_tddx.size() == 0
				|| Constants.list_zwfw_zhuti_qy_ztfl.size() == 0
				|| Constants.list_zwfw_bmfw_xzcf_name.size() == 0 
				|| Constants.Zwfw_bmfw_listview_item.size() == 0
				|| Constants.Zwfw_xzcf_listview_item.size() == 0){
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code1", "root_gr_ztfl");
			map.put("code2", "root_gr_sjfl");
			map.put("code3", "root_gr_qtfl");
			map.put("code4", "root_qy_ztfl");
			map.put("code5", "root_qy_sjfl");
			map.put("code6", "root_qy_qtfl");
			list.add(map);
			managerService.getZWFW_zhuti(context, 1, list);
			bm_manager.getZWFW_bmfw(context, 1, null);
			xz_manager.getZWFW_xzcf(context, 1, null);
		}
	}

	private ZWFW_Zhuti_ManagerService managerService = new ZWFW_Zhuti_ManagerService() {

		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
		}
	};

	private ZWFW_BM_ManagerService bm_manager = new ZWFW_BM_ManagerService() {

		@Override
		protected void handlerLoginInfo(Context paramActivity,
				HandleResult handleResult, int paramInt) {
			
		}
	};
	private ZWFW_XZ_ManagerService xz_manager=new ZWFW_XZ_ManagerService() {
		
		@Override
		protected void handlerLoginInfo(Context paramActivity,
				HandleResult handleResult, int paramInt) {
			
		}
	};
	private ZWFW_PJYZ_ManagerService pj_manager=new ZWFW_PJYZ_ManagerService() {
		
		@Override
		protected void handlerLoginInfo(Context context, HandleResult handleResult,
				int paramInt) {
					
		}
	};
	private ZWFW_PJTJ_ManagerService pjtj_manager=new ZWFW_PJTJ_ManagerService() {
		
		@Override
		protected void handlerLoginInfo(Context context, HandleResult handleResult,
				int paramInt) {
			
		}
	};
	private ZWFW_CX_ManagerService cx_manager=new ZWFW_CX_ManagerService() {
		
		@Override
		protected void handlerLoginInfo(Context paramActivity,
				HandleResult handleResult, int paramInt) {
			
		}
	};
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setContentView(R.layout.activity_main);
		myTabHost = (TabHost) this.findViewById(R.id.myTabHost);
		mActivityManager = new LocalActivityManager(MainActivity.this, false);
		mActivityManager.dispatchCreate(intent.getExtras());
		myTabHost.setup(mActivityManager);
		init();
		for (int i = 0; i < intents.length; i++) {
			mView = getLayoutInflater().inflate(R.layout.tab_item, null);
			mImage = (ImageView) mView.findViewById(R.id.mImg);
			// mText = (TextView) mView.findViewById(R.id.mTxt);

			mImage.setImageResource(tImg[i]);
			// mText.setText(titileStrings[i]);
			TabSpec tabSpec = myTabHost.newTabSpec(tags[i]);
			tabSpec.setIndicator(mView).setContent(intents[i]);
			myTabHost.addTab(tabSpec);
			myTabHost.setOnTabChangedListener(onTabChangeListener);
		}
	}

	private OnTabChangeListener onTabChangeListener = new OnTabChangeListener() {

		@Override
		public void onTabChanged(String tabId) {
			// Log.e("tabID****************",tabId+"-----------------------");
			for (int i = 0; i < tags.length; i++) {
				if (tags[i] == tabId) {
					// Intent intent = new Intent();
					switch (i) {
					case 0:
						// intent.setAction("com.dichuang.jxwhcyw.PriService");
						// intent.putExtra("send", 2);// 碎片广播
						break;
					case 1:
						// intent.setAction("com.dichuang.jxwhcyw.ReService");
						// intent.putExtra("send", 1);// 图说新闻的广播
						break;
					case 3:
						// intent.setAction("com.dichuang.BoxReceiver");
						// intent.putExtra("send", 3);
					}
					// sendBroadcast(intent);
				}
			}

		}
	};

	public void init() {
		intent1 = new Intent(MainActivity.this, GZLMActivity.class);
		intent2 = new Intent(MainActivity.this, ZWGKActivity.class);
		intent3 = new Intent(MainActivity.this, ZWFWActivity.class);
		intent4 = new Intent(MainActivity.this, ZMHDActivity.class);
		intents[0] = intent1;
		intents[1] = intent2;
		intents[2] = intent3;
		intents[3] = intent4;
	}

	// @SuppressWarnings("deprecation")
	// public class MainActivity extends BaseActivity {
	// /** Called when the activity is first created. */
	// private TabHost tabHost;
	// private Context context;
	// private RadioButton main_tab_myExam;
	// protected static ActivityApp app;
	//
	// @Override
	// public void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	// setContentView(R.layout.activity_main);
	// if(app == null){
	// app = (ActivityApp) this.getApplicationContext();
	// }
	// context=this;
	//
	// main_tab_myExam=(RadioButton) findViewById(R.id.main_tab_myExam);
	//
	// tabHost=this.getTabHost();
	// TabHost.TabSpec spec;
	// Intent intent;
	// intent=new Intent().setClass(this, GZLMActivity_.class);
	// spec=tabHost.newTabSpec("公众栏目").setIndicator("公众栏目").setContent(intent);
	// tabHost.addTab(spec);
	//
	// intent=new Intent().setClass(this,ZWGKActivity_.class);
	// spec=tabHost.newTabSpec("政务公开").setIndicator("政务公开").setContent(intent);
	// tabHost.addTab(spec);
	//
	// intent=new Intent().setClass(this, ZWFWActivity.class);
	// spec=tabHost.newTabSpec("政务服务").setIndicator("政务服务").setContent(intent);
	// tabHost.addTab(spec);
	//
	//
	// intent=new Intent().setClass(this, ZMHDActivity_.class);
	// spec=tabHost.newTabSpec("政民互动").setIndicator("政民互动").setContent(intent);
	// tabHost.addTab(spec);
	// tabHost.setCurrentTab(1);
	//
	// RadioGroup radioGroup=(RadioGroup)
	// this.findViewById(R.id.main_tab_group);
	// radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	//
	// @Override
	// public void onCheckedChanged(RadioGroup group, int checkedId) {
	// // TODO Auto-generated method stub
	// switch (checkedId) {
	// case R.id.main_tab_addExam://添加考试
	// // tabHost.setCurrentTab(0);
	// // tabHost.setCurrentTabByTag("公众栏目");
	// break;
	// case R.id.main_tab_myExam://我的考试
	// // tabHost.setCurrentTab(1);
	// // tabHost.setCurrentTabByTag("政务公开");
	// break;
	// case R.id.main_tab_message://我的通知
	// // tabHost.setCurrentTab(2);
	// // tabHost.setCurrentTabByTag("政务服务");
	// break;
	// case R.id.main_tab_settings://设置
	// // tabHost.setCurrentTab(3);
	// // tabHost.setCurrentTabByTag("政民互动");
	// break;
	// default:
	// //tabHost.setCurrentTabByTag("我的考试");
	// break;
	// }
	// }
	// });
	// }
	//
	// public static void changeTo(int id){
	//
	// // tabHost.setCurrentTabByTag("我的考试");
	// // Animation slideLeftIn = AnimationUtils.loadAnimation(context,
	// R.anim.slide_left_in);
	// // tabHost.getCurrentView().startAnimation(slideLeftIn);
	// // main_tab_myExam.setChecked(true);
	// }
	//
	//
	// private boolean isExit = false;
	//
	// /**
	// * 双击退出
	// */
	// public void exitBy2Click() {
	//
	// if (!isExit) {
	// isExit = true; // 准备退出
	// Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
	//
	// new Thread(new Runnable() {
	//
	// @Override
	// public void run() {
	// SystemClock.sleep(2000);
	// isExit = false; // 取消退出
	// }
	// }).start();
	// } else {
	// app.finishAll();
	// }
	//
	//
	// }
	@Override
	protected void onResume() {
		// TODO 自动生成的方法存根
//		setChangelView();
		super.onResume();
	}
	@Override
	protected void onPause() {
	
		// TODO 自动生成的方法存根
		super.onPause();
	}
	@Override
	protected void onStart() {
	
		// TODO 自动生成的方法存根
		super.onStart();
	}
}