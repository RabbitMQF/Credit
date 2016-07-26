package com.WSZW.data;

import java.lang.reflect.Type;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.WSZW.util.CommUtil;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;
import com.googlecode.androidannotations.api.Scope;

@EBean(scope = Scope.Singleton)
public class DataHelper {
	@RootContext
	Context context;
	@Pref
	InfoFile_ infoFile;
	private String centerWs, payWs, memberApi;
	private Handler callbackHandler;
	private boolean isNetworkOk;

	/**
	 * 登录BPN
	 */
	@Background
	public void loginBpn(String username) {
		// Object result = null;
		// PushManager pushManager = PushManager.getInstance();
		// if (isNetworkOk && pushManager != null) {
		// String response = pushManager.login(username);
		// result = JsonUtil.json2Obj(response, Boolean.class);
		// }
		// callback(R.id.data_bpn_login, result);
	}

	@Background
	void bpnMsg(int flag, String methodName, Map<String, Object> params,
			Type type) {
		// Object result = null;
		// PushManager pushManager = PushManager.getInstance();
		// if (isNetworkOk && pushManager != null) {
		// String response = pushManager.getMsg(methodName, params);
		// result = JsonUtil.json2Any(response, type);
		// }
		// callback(flag, result);
	}

	/**
	 * 后台执行WS，并手工转换JSON
	 */
	@Background
	void doWsInBackground(int flag, String wsUrl, String methodName,
			Map<String, Object> params) {
		if (isNetworkOk) {
			// callback(flag, JsonUtil.buildFromJson(flag,
			// XmlUtil.getJson(HttpUtil.doWS(wsUrl, methodName, params))));
		} else {
			callback(flag, null);
		}
	}

	/**
	 * 后台线程执行WebService远程调用任务,采用gson框架解析数据
	 * 
	 * @param flag
	 *            数据请求标识
	 * @param url
	 *            请求的URL
	 * @param params
	 *            请求参数
	 * @author Goven
	 * @date 2013-8-8
	 */
	@Background
	void doWsInBackground(int flag, String wsUrl, String methodName,
			Map<String, Object> params, Class<?> clazz) {
		if (isNetworkOk) {
			// callback(flag,
			// JsonUtil.json2Obj(XmlUtil.getJson(HttpUtil.doWS(wsUrl,
			// methodName, params)), clazz));
		} else {
			callback(flag, null);
		}
	}

	@Background
	void doWsInBackground(int flag, String wsUrl, String methodName,
			Map<String, Object> params, Type type) {
		if (isNetworkOk) {
			// callback(flag,
			// JsonUtil.json2Any(XmlUtil.getJson(HttpUtil.doWS(wsUrl,
			// methodName, params)), type));
		} else {
			callback(flag, null);
		}
	}

	/**
	 * Get请求异步线程
	 * 
	 * @param flag
	 *            数据标识
	 * @param urlPrefix
	 *            url前缀
	 * @param path
	 *            请求路径
	 * @param params
	 *            入参
	 * @author Goven
	 * @date 2013-11-14
	 */
	@Background
	void doHttpGetInBackground(int flag, String urlPrefix, String path,
			Map<String, Object> params, Type type) {
		if (isNetworkOk) {
			// callback(flag,JsonUtil.json2Any(HttpUtil.doGet(urlPrefix, path,
			// params), type));
		} else {
			callback(flag, null);
		}
	}

	/**
	 * 用于get请求获取数据,获取的数据手动json解析
	 * 
	 * @author yuhuihui
	 * @param flag
	 * @param urlPrefix
	 * @param path
	 * @param params
	 * @data 2014-5-30
	 */
	@Background
	void doHttpGetInBackground(int flag, String urlPrefix, String path,
			Map<String, Object> params) {
		if (isNetworkOk) {
			// callback(flag, JsonUtil.buildFromJson(flag,
			// XmlUtil.getJson(HttpUtil.doGet(urlPrefix, path, params))));
		} else {
			callback(flag, null);
		}
	}

	/**
	 * 用于post请求
	 * 
	 * @author yuhuihui
	 * @param flag
	 * @param url
	 * @param params
	 * @data 2014-5-30
	 */
	@Background
	void doHttpPostInBackground(int flag, String url, Map<String, Object> params) {
		if (isNetworkOk) {
			// callback(flag,HttpUtil.doPost(url, params));
		} else {
			callback(flag, null);
		}
	}

	/**
	 * 用于下载文件
	 * 
	 * @author yuhuihui
	 * @param flag
	 * @param url
	 * @param filePath
	 * @data 2014-5-30
	 */
	@Background
	void downLoadInBackground(int flag, String url, String filePath) {
		if (isNetworkOk) {
			// callback(flag, HttpUtil.downLoad(url, filePath));
		} else {
			callback(flag, null);
		}
	}

	/**
	 * UI主线程，处理响应结果
	 * 
	 * @param flag
	 *            数据请求标识
	 * @param result
	 *            返回的结果
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	void callback(int flag, Object result) {
		if (callbackHandler != null) {
			Message msg = Message.obtain();
			msg.what = flag;
			msg.obj = result;
			callbackHandler.sendMessage(msg);
		}
	}

	/**
	 * 设置回调Hanlder，用来通知调用者
	 * 
	 * @param callbackHandler
	 *            回调Hanlder
	 * @author yuhuihui
	 * @date 2014-5-30
	 */
	public void setCallbackHandler(Handler callbackHandler) {
		this.callbackHandler = callbackHandler;
		// infofile中的数据已经在广播接受者中获取访问地址
		// this.centerWs = infoFile.urlCenterWs().get();
		// this.payWs = infoFile.urlPayWs().get();
		// this.memberApi = infoFile.urlMemberApi().get();
		this.isNetworkOk = CommUtil.isNetworkOk(context);
	}
}
