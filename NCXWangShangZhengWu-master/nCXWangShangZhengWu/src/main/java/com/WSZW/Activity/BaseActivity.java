package com.WSZW.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * 基础Activity，所有的Activity都要继承此类， 定义了一些公共的方法和方便的操作，以后还可以扩展
 * 
 * @date 2014-5-30
 * @author yuhuihui
 */
public class BaseActivity extends FragmentActivity {
	protected static ActivityApp app;

	protected BaseActivity context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (app == null) {
			app = (ActivityApp) getApplicationContext();
		}
		addActivity();
		context = this;
	}

	@Override
	protected void onResume() {
		// overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		app.removeActivity(this);
	}

	/**
	 * 在应用的Activity管理器里面添加当前Activity
	 * 
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	protected void addActivity() {
		app.addActivity(this);
	}

	/**
	 * 结束所有的Activity
	 * 
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	protected void finishAllActivity() {
		app.finishAll();
	}

	protected void finishOthers() {
		app.finishAllExcept(this);
	}

	/**
	 * 除指定activity外，其他的activity全部结束
	 * 
	 * @author yuhuihui
	 * @date 2014-5-30
	 * @param clazz
	 */
	protected void finishOthers(Class<? extends Activity> clazz) {
		if (!app.finishAllExcept(clazz)) {
			// startActivity(new Intent(context, MainActivity_.class));
		}
	}

	/**
	 * 长时间的Toast
	 * 
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public void showToastLong(String... values) {
		if (values == null || values.length == 0) {
			Toast.makeText(context, "网络不给力!", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(context, values[0], Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 短时间的Toast
	 * 
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public void showToastShort(String... values) {
		if (values == null || values.length == 0) {
			Toast.makeText(context, "网络不给力!", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(context, values[0], Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 弹出仅带有确定按钮的对话框
	 * 
	 * @param title
	 *            标题
	 * @param info
	 *            内容
	 * @param icon
	 *            图标
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public void showOKDialog(String title, String info, int icon) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setIcon(icon).setTitle(title).setMessage(info)
				.setPositiveButton("确定", null).show();
	}

	//
	// /**
	// * 初始化滑动菜单
	// */
	// protected void initSlidingMenu(Bundle savedInstanceState) {
	// // 设置滑动菜单的视图
	// setBehindContentView(R.layout.sliding_menu_frame);
	// if(Constants.list_leftmenu.size()>0){
	// getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame,
	// new SampleListFragment()).commit();
	// }
	// // 实例化滑动菜单对象
	// sm = getSlidingMenu();
	// // 设置滑动阴影的宽度
	// sm.setShadowWidthRes(R.dimen.shadow_width);
	// // 设置滑动阴影的图像资源
	// sm.setShadowDrawable(R.drawable.shadow);
	// // 设置滑动菜单视图的宽度
	// sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
	// // 设置渐入渐出效果的值
	// sm.setFadeDegree(0.35f);
	// // 设置触摸屏幕的模式
	// sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
	//
	// }

	// SlidingMenu sm;
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
		} else {
			app.finishAll();
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click();

		}
		return false;
	}

}
