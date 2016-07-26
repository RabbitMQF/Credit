package com.WSZW.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import com.WSZW.entity.HandleResult;
import com.WSZW.util.BaseAsyncTask;
import com.WSZW.util.XmlUtil;


import android.content.Context;
import android.widget.Toast;

public abstract class BdlrManagerService {
	public static final String TAG = "LeftMenuManagerService";

	/**
	 * 获取表单录入表格详情
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list传入的account
	 */
	public void getServiceSubjectForm(final Context paramActivity,
			final int paramInt, final List<Map<String, Object>> list) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl";/*paramActivity.getResources().getString(
				com.ZWPT.activity.R.string.endpoint);*/
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;
			List<Map<String, Object>> resultGroup = new ArrayList<Map<String, Object>>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB2("getServiceSubjectForm", url, list);
					resultGroup = XmlUtil.getServiceSubjecrFormXmlParse(result);
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
							.setGetBdlr_getServiceSubjectFormSuccess("success");
					handleResult.setList_bdlr_form(resultGroup);
				} else if (paramInteger == -1) {// 链接服务器失败
					handleResult
							.setGetBdlr_getServiceSubjectFormSuccess("fail");
					Toast.makeText(paramActivity, "获取表单失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				BdlrManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	public void SubmitServiceSubjectForm(final Context paramActivity,
			final int paramInt, final List<Map<String, Object>> list) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl"; /*paramActivity.getResources().getString(
				com.ZWPT.activity.R.string.endpoint);*/
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;

			// List<Map<String, Object>> resultGroup = new
			// ArrayList<Map<String,Object>>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB2("onlineAcceptSubmit", url, list);
					// resultGroup =
					// XmlUtil.getServiceSubjecrFormXmlParse(result);
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
						handleResult
								.setGetSubmitServiceSubjectFormSuccess("success");
						handleResult.setSubmitBdlrFormContent(result);
					} else {
						handleResult
								.setGetSubmitServiceSubjectFormSuccess("fail");
						Toast.makeText(paramActivity, "提交表单失败！",
								Toast.LENGTH_LONG).show();
					}

				} else if (paramInteger == -1) {// 链接服务器失败
					handleResult.setGetSubmitServiceSubjectFormSuccess("fail");
					Toast.makeText(paramActivity, "提交表单失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				BdlrManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	public abstract void handlerLoginInfo(Context context,
			HandleResult paramHandleResult, int paramInt);
}
