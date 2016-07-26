package com.WSZW.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CommUtil {

	/**
	 * 更改权限
	 * 
	 * @param permission权限
	 * @param path文件路径
	 * @author rjh
	 * @date 2014-5-30
	 */
	public static void chmod(String permission, String path) {
		try {
			String command = "chmod " + permission + " " + path;
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(command);
		} catch (IOException e) {
			Log.e("CommUtil (chmod) --> ", e.toString());
		}
	}

	/**
	 * 打印默认info级别的日志
	 * 
	 * @param tag
	 *            标签
	 * @param info
	 *            信息
	 * @author rjh
	 * @date 2014-5-30
	 */
	public static void log(String tag, String info) {
		Log.i(tag, info);
	}

	/**
	 * 打印debug级别的日志
	 * 
	 * @param tag
	 *            标签
	 * @param info
	 *            信息
	 * @author rjh
	 * @date 2014-5-30
	 */
	public static void logD(String tag, String info) {
		Log.d(tag, info);
	}

	/**
	 * 打印error级别的日志
	 * 
	 * @param tag
	 *            标签
	 * @param info
	 *            信息
	 * @author rjh
	 * @date 2014-5-30
	 */
	public static void logE(String tag, String info) {
		Log.e(tag, info);
	}

	/**
	 * 判断网络是否连接
	 * 
	 * @author rjh
	 * @param context
	 * @return
	 */
	public static boolean isNetworkOk(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
		if (activeInfo == null) {
			return false;
		} else {
			return activeInfo.isConnected();
		}
	}

	/**
	 * @param 获取
	 *            listView 的绝对高度
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {

		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(
					MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
					MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			// listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}

	/**
	 * 网络配置提醒
	 * 
	 * @author rjh
	 * @date 2014-5-30
	 */
	public static void showNetworkDialog(Context context) {
		// AlertDialog dialog = new AlertDialog(context);
		// dialog.setTitle("当前无可用网络").setMessage("是否跳转到网络设置界面？").setEnsureText("确定").setCancelText("取消").setOnClickListener(new
		// AlertDialog.OnClickAdapter() {
		// @Override
		// public void onEnsureClick(AlertDialog dialog) {
		// startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));//
		// 跳转到网络配置界面
		// finish();
		// }
		//
		// @Override
		// public void onCancelClick(AlertDialog dialog) {
		// finish();
		// }
		// }).cancelable(false).show();
		Toast.makeText(context, "当前无可用网络", Toast.LENGTH_SHORT).show();
	}

	
	/**
	* 从服务器取图片
	*http://bbs.3gstdy.com
	* @param url
	* @return
	*/
	@SuppressWarnings("unused")
	public static Bitmap getHttpBitmap(String url) {
	     URL myFileUrl = null;
	     Bitmap bitmap = null;
	     try {
	          myFileUrl = new URL(url);
	          if(myFileUrl == null){
	        	  return null;
	          }
	     } catch (MalformedURLException e) {
	          e.printStackTrace();
	     }
	     try {
	          HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
	          conn.setConnectTimeout(0);
	          conn.setDoInput(true);
	          conn.connect();
	          InputStream is = conn.getInputStream();
	          bitmap = BitmapFactory.decodeStream(is);
	          is.close();
	     } catch (IOException e) {
	          e.printStackTrace();
	     }
	     return bitmap;
	}
	
	
	 public static Date stringToDate(String dateString) {  
	        ParsePosition position = new ParsePosition(0);  
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        Date dateValue = simpleDateFormat.parse(dateString, position);  
	        return dateValue;  
	    }  
}
