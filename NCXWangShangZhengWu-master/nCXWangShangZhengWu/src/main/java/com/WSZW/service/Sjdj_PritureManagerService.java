package com.WSZW.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.WSZW.entity.HandleResult;
import com.WSZW.util.BaseAsyncTask;

import android.content.Context;
import android.widget.Toast;

public abstract class Sjdj_PritureManagerService {
	public static final String TAG = "Sjdj_PritureManagerService";

	/**
	 * 收件登记图片管理
	 * 
	 * @param paramActivity
	 *            上下文对象
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list
	 *            包含用户id、事项id、提交材料姓名、申请人姓名、申请人身份证号、提交材料类型、提交材料的id、二进制图片流
	 */
	public void upLoadPritureInfo(final Context paramActivity,
			final int paramInt, final List<Map<String, Object>> list) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl";
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;
			List<String> resultGroup = new ArrayList<String>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result = downloadDB2("onlineAcceptImage", url, list);
					resultGroup.add(result);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (result == null ||resultGroup == null || result.equals("error")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					if (resultGroup.get(0) != null) {
						handleResult.setUploadPritureSuccess("success");
					} else {
						handleResult.setUploadPritureSuccess("fail");
					}
					handleResult.setPriture_list(resultGroup);
				} else if (paramInteger == -1) {// 链接服务器失败

					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setUploadPritureSuccess("fail");
				}
				Sjdj_PritureManagerService.this.handlerUpLoadPritureInfo(
						paramActivity, handleResult, paramInt);
				super.onPostExecute(paramInteger);
			}
		};
		loacl.execute(1);
	}

	/**
	 * 通过图片id删除图片
	 * 
	 * @param paramActivity
	 * @param paramInt
	 * @param list
	 */
	public void deletePritureInfo(final Context paramActivity,
			final int paramInt, final List<Map<String, Object>> list) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl";
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;
			String resultGroup = new String();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result = downloadDB2("deleteCertificateImage", url, list);
					resultGroup = result;
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (result == null ||resultGroup == null || result.equals("error")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					if (resultGroup != null) {
						handleResult.setDeletePritureSuccess("success");
					} else {
						handleResult.setDeletePritureSuccess("fail");
					}
					handleResult.setGetDeletePritureInfo(resultGroup);
				} else if (paramInteger == -1) {// 链接服务器失败

					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				Sjdj_PritureManagerService.this.handlerUpLoadPritureInfo(
						paramActivity, handleResult, paramInt);
				super.onPostExecute(paramInteger);
			}

		};
		loacl.execute(1);
	}

	public abstract void handlerUpLoadPritureInfo(Context context,
			HandleResult paramHandleResult, int paramInt);
}
