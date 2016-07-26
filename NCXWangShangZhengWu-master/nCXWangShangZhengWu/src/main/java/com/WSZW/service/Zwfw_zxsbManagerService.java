package com.WSZW.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import com.WSZW.entity.HandleResult;
import com.WSZW.entity.ZWFW_zxsb_detail_bean;
import com.WSZW.util.BaseAsyncTask;
import com.WSZW.util.XmlUtil;

import android.content.Context;
import android.widget.Toast;

/**
 * 在线申办数据源管理类
 * 
 * @author Administrator
 * 
 */
public abstract class Zwfw_zxsbManagerService {
	public static final String TAG = "Zwfw_zxsbManagerService";
	/**
	 * 获取收件登记事项是否前台受理
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * 
	 */
	public void getIfAcceptForeground(final Context paramActivity,
			final int paramInt, final List<Map<String, Object>> list) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl"; /*paramActivity.getResources().getString(
				com.ZWPT.activity.R.string.endpoint);*/
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;
			List<Map<String, Object>> resultGroup = new ArrayList<Map<String, Object>>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB2("getAcceptQtsl", url, list);
					resultGroup = XmlUtil.getAcceptQtslXmlParse(result);
				} catch (XmlPullParserException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null ||resultGroup == null || result.equals("error")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					handleResult
							.setGetIfAcceptForeground_ResultSuccess("success");
					handleResult.setList_if_accept_foreground(resultGroup);
				} else if (paramInteger == -1) {// 链接服务器失败
					handleResult.setGetIfAcceptForeground_ResultSuccess("fail");
					Toast.makeText(paramActivity, "获取数据失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				Zwfw_zxsbManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	/**
	 * 获取初审地点
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list传入的account
	 */
	public void getSjdj_CSDD(final Context paramActivity, final int paramInt,
			final List<Map<String, Object>> list) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl"; /*paramActivity.getResources().getString(
				com.ZWPT.activity.R.string.endpoint);*/
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;
			List<Map<String, Object>> resultGroup = new ArrayList<Map<String, Object>>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB2("getAcceptBsck", url, list);
					resultGroup = XmlUtil.getAddressCsddXmlParse(result);
				} catch (XmlPullParserException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null || resultGroup == null || result.equals("error")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					handleResult.setGetChushen_Address_ResultSuccess("success");
					handleResult.setList_chushen_address(resultGroup);
				} else if (paramInteger == -1) {// 链接服务器失败
					handleResult.setGetChushen_Address_ResultSuccess("fail");
					Toast.makeText(paramActivity, "获取初审地点失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				Zwfw_zxsbManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	/**
	 * 获取发放地点
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list传入的account
	 */
	public void getSjdj_FFDD(final Context paramActivity, final int paramInt,
			final List<Map<String, Object>> list) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl"; /*paramActivity.getResources().getString(
				com.ZWPT.activity.R.string.endpoint);*/
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;
			List<Map<String, Object>> resultGroup = new ArrayList<Map<String, Object>>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB2("getAcceptFfdd", url, list);
					resultGroup = XmlUtil.getAddressFfddXmlParse(result);
				} catch (XmlPullParserException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null ||resultGroup == null || result.equals("error")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					handleResult.setGetfafang_Address_ResultSuccess("success");
					handleResult.setList_fafang_address(resultGroup);
				} else if (paramInteger == -1) {// 链接服务器失败
					handleResult.setGetfafang_Address_ResultSuccess("fail");
					Toast.makeText(paramActivity, "获取发放地点失败！",
							Toast.LENGTH_LONG).show();
					return;
				}
				Zwfw_zxsbManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	/**
	 * 获取办理条件和提交材料列表
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list传入的account
	 */
	public void getSjdj_bltj_tjcl(final Context paramActivity,
			final int paramInt, final List<Map<String, Object>> list) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl"; /*paramActivity.getResources().getString(
				com.ZWPT.activity.R.string.endpoint);*/
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;
			ZWFW_zxsb_detail_bean resultGroup = new ZWFW_zxsb_detail_bean();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB2("getAcceptInfo", url, list);
					if (result.contains("<南昌市燃气管理条例>")) {
						result = result.replace("<南昌市燃气管理条例>", " 南昌市燃气管理条例 ");
					}
					resultGroup = XmlUtil.getSjdj_detailXmlParse(result);
				} catch (XmlPullParserException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null ||resultGroup == null || result.equals("error")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					handleResult.setGetSjdj_bltj_tjcl_ResultSuccess("success");
					handleResult.setZxsb_detail(resultGroup);
				} else if (paramInteger == -1) {// 链接服务器失败
					handleResult.setGetSjdj_bltj_tjcl_ResultSuccess("fail");
					Toast.makeText(paramActivity, "获取数据失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				Zwfw_zxsbManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	/**
	 * 获取提交材料列表
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list传入的account
	 */
	public void getAcceptInfo2(final Context paramActivity, final int paramInt,
			final List<Map<String, Object>> list) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl";/* paramActivity.getResources().getString(
				com.ZWPT.activity.R.string.endpoint);*/
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;
			List<Map<String, Object>> resultGroup = new ArrayList<Map<String, Object>>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB2("getAcceptInfo2", url, list);
					resultGroup = XmlUtil.getSjdj_detail_tjcl_XmlParse(result);
				} catch (XmlPullParserException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null ||resultGroup == null || result.equals("error")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					handleResult.setSjdj_getTjclSuccess("success");
					handleResult.setSjdj_tjcl_no_checkbox(resultGroup);
				} else if (paramInteger == -1) {// 链接服务器失败
					handleResult.setSjdj_getTjclSuccess("fail");
					Toast.makeText(paramActivity, "获取数据失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				Zwfw_zxsbManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	/**
	 * 获取办理条件和提交材料列表
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list传入的account
	 */
	public void getSJDJ_Submit(final Context paramActivity, final int paramInt,
			final List<Map<String, Object>> list) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl"; /*paramActivity.getResources().getString(
				com.ZWPT.activity.R.string.endpoint);*/
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;

			// Sjdj_shouli_detail_bean resultGroup = new
			// Sjdj_shouli_detail_bean();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB2("getAcceptSubmit", url, list);
					// resultGroup = XmlUtil.getSjdj_detailXmlParse(result);
				} catch (XmlPullParserException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null || result.equals("error")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}
			
			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					if (result.contains("成功")) {
						handleResult.setGetSjsj_AcceptSubmitSuccess("success");
						handleResult.setSjsj_AcceptSubmitContent(result);
					} else {
						handleResult.setGetSjsj_AcceptSubmitSuccess("fail");
						handleResult.setSjsj_AcceptSubmitContent(result);
					}
				} else if (paramInteger == -1) {// 链接服务器失败
					handleResult.setGetSjsj_AcceptSubmitSuccess("fail");
					Toast.makeText(paramActivity, "获取数据失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				Zwfw_zxsbManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	public abstract void handlerLoginInfo(Context context,
			HandleResult paramHandleResult, int paramInt);
}
