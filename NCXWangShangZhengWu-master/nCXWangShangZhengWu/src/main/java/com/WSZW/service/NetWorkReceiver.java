package com.WSZW.service;

import java.net.InetAddress;
import java.util.Properties;

import com.WSZW.Activity.ActivityApp;
import com.WSZW.Activity.LoadingActivity;
import com.WSZW.Activity.R;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.util.CommUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * 对网络连接进行判断，根据用户的SIM卡进行判断用户的网络运营商
 * 
 * @author rjh
 * @data 2014-5-30
 */
public class NetWorkReceiver extends BroadcastReceiver {
	private Context context;
	private static NetWorkReceiver instance;
	private Properties props;
	private ConnectivityManager connectivityManager;

	/**
	 * 初始化广播发送者的单例模式
	 * 
	 * @param context
	 * @return
	 */
	public static NetWorkReceiver getInstance(Context context) {
		if (instance == null) {
			synchronized (NetWorkReceiver.class) {
				instance = new NetWorkReceiver(context);
			}
		}
		return instance;
	}

	private NetWorkReceiver(Context context) {
		this.context = context;
		ActivityApp.devMode = Boolean.valueOf(context.getResources().getString(
				R.string.devMode));
		this.props = loadConfig(context.getResources().getString(
				R.string.config_file_name));// 从String中导入服务器地址
		this.connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	/**
	 * 加载配置文件
	 * 
	 * @param fileName
	 *            配置文件名
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public Properties loadConfig(String fileName) {
		Properties props = new Properties();
		try {
			int id = context.getResources().getIdentifier(fileName, "raw",
					context.getPackageName());
			props.load(context.getResources().openRawResource(id));
		} catch (Exception e) {
			Log.e("NetworkReceiver", "Could not find the properties file.", e);
		}
		return props;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
		if (activeInfo != null && activeInfo.isConnected()) {
			// 获取网络运营商类型
			ServerType simType = selectSIM();
			boolean isWifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
			// 如果处于WIFI连接状态，就要选择好用的服务器，否则就根据具体的SIM卡类型来选择相应的服务器
			if (isWifiConnected) {
				selectServer();
			} else {
				initServer(simType);
			}
		} else {
			CommUtil.showNetworkDialog(context);// 显示网络没有连接
			Constants.SERVER_TYPE = Constants.SERVER_NULL;
		}
	}

	/**
	 * 初始化配置（跟连接服务器有关）
	 * 
	 * @param serverType
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public void initServer(ServerType serverType) {
		String serverIp;
		if (serverType == ServerType.yiDong) {
			serverIp = props.getProperty("ip_yidong", null);
			Constants.SERVER_TYPE = Constants.SERVER_YIDONG;
		} else if (serverType == ServerType.lianTong) {
			serverIp = props.getProperty("ip_liantong", null);
			Constants.SERVER_TYPE = Constants.SERVER_LIANTONG;
		} else {
			serverIp = props.getProperty("ip_dianxin", null);
			Constants.SERVER_TYPE = Constants.SERVER_DIANXIN;
		}

		// 从资源文件获取服务器地址
		String centerWs = props.getProperty("url_center_ws", null);
		InfoFile_ infoFile = new InfoFile_(context);
		// 将服务器地址保存在本地
		infoFile.edit().urlCenterWs().put(String.format(centerWs, serverIp))
				.apply();

		// 开启后台推送服务
		// ServiceManager serviceManager = ServiceManager.getInstance();
		// if (serviceManager == null) {
		// ServiceManager.getInstance(context).startService();
		// } else {
		// serviceManager.restartService();
		// }
	}

	/**
	 * 实现选择服务器的功能，需要开启异步线程
	 * 
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public void selectServer() {
		new Thread() {
			public void run() {
				ServerType serverType = null;
				String serverIps[] = { props.getProperty("ip_yidong", null),
						props.getProperty("ip_liantong", null),
						props.getProperty("ip_dianxin", null) };
				int timeOut = 3000; // 定义超时
				while (timeOut < 10000) {
					if (tryConnect(serverIps[2], timeOut)) {
						serverType = ServerType.dianXin;
						break;
					} else if (tryConnect(serverIps[0], timeOut)) {
						serverType = ServerType.yiDong;
						break;
					} else if (tryConnect(serverIps[1], timeOut)) {
						serverType = ServerType.lianTong;
						break;
					}
					timeOut += 2000;
				}
				;
				if (serverType != null) {
					Message msg = callbackHandler.obtainMessage();
					msg.obj = serverType;
					callbackHandler.sendMessage(msg);
				}
			}
		}.start();
	}

	/**
	 * 测试服务器是否成功连接上
	 * 
	 * @param serverIp
	 *            服务器IP
	 * @param timeOut
	 *            超时
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	private boolean tryConnect(String serverIp, int timeOut) {
		try {
			return InetAddress.getByName(serverIp).isReachable(timeOut);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 开启线程初始化服务
	 */
	private Handler callbackHandler = new Handler() {
		public void handleMessage(Message msg) {
			initServer((ServerType) msg.obj);
		};
	};

	/**
	 * 判断SIM卡
	 * 
	 * @return 需要连接的服务器类型
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public ServerType selectSIM() {
		TelephonyManager telManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String operator = telManager.getSimOperator();
		if ("46000".equals(operator) || "46002".equals(operator)) {
			// 此卡属于(中国移动)
			return ServerType.yiDong;
		} else if ("46001".equals(operator)) {
			// 此卡属于(中国联通)
			return ServerType.lianTong;
		} else {
			// 此卡属于(中国电信)
			return ServerType.dianXin;
		}
	}

	/**
	 * 用于选择服务器
	 * 
	 * @author yuhuihui
	 * @data 2014-5-30
	 */
	public enum ServerType {
		dianXin, yiDong, lianTong
	}

}
