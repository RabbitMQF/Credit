package com.WSZW.Activity;

import java.util.ArrayList;
import java.util.List;

import com.WSZW.data.InfoFile_;
import com.WSZW.util.SQLHelper;

import android.app.Activity;
import android.app.Application;

/**
 * Application全局管理类
 * 
 * @author yuhuihui
 * @date 2014-5-30
 */
public class ActivityApp extends Application {
	public static InfoFile_ infoFile_;
	private List<Activity> activitys = new ArrayList<Activity>();
	private static ActivityApp instance;
	
	private SQLHelper sqlHelper;
	
	public static boolean devMode;// 是否处于开发模式

	public static ActivityApp getIntance() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		infoFile_ = new InfoFile_(instance);
		// 这里注册了防止崩溃后弹窗的机制
		// 错误日志保存在缓存目录下
		// CrashHandler crashHandler = CrashHandler.getInstance();
		// crashHandler.init(getApplicationContext());
	}
	/** 获取数据库Helper */
	public SQLHelper getSQLHelper() {
		if (sqlHelper == null){
			sqlHelper = new SQLHelper(instance);
		}
			return sqlHelper;
	}
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		if (sqlHelper != null)
			sqlHelper.close();
		super.onTerminate();
		//整体摧毁的时候调用这个方法
	}
	/**
	 * 加入Activity
	 * 
	 * @param activity
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public void addActivity(Activity activity) {
		if (!activitys.contains(activity)) {
			activitys.add(activity);
		}
	}

	/**
	 * 删除Activity
	 * 
	 * @param activity
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public void removeActivity(Activity activity) {
		activitys.remove(activity);
	}

	/**
	 * 除了某个Activity，结束掉所有的Activity
	 * 
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public void finishAllExcept(Activity activity) {
		for (Activity $activity : activitys) {
			if ($activity != activity) {
				$activity.finish();
			}
		}
	}

	/**
	 * 除了指定的Activity，结束掉所有的Activity，通过类指定
	 * 
	 * @param clazz
	 *            要保留的Activity
	 * @return 是否有保留的Activity
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public boolean finishAllExcept(Class<? extends Activity> clazz) {
		boolean hasClazz = false;
		for (Activity $activity : activitys) {
			if ($activity.getClass() != clazz) {
				$activity.finish();
			} else {
				hasClazz = true;
			}
		}
		return hasClazz;
	}

	/**
	 * 结束掉所有的Activity
	 * 
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public void finishAll() {
		for (Activity activity : activitys) {
			activity.finish();
		}
	}
}
