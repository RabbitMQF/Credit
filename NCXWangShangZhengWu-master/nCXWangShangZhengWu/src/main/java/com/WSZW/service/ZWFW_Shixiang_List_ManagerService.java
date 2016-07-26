package com.WSZW.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.widget.Toast;

import com.WSZW.data.Constants;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.Zwfw_Shixiang_List_Item;
import com.WSZW.util.BaseAsyncTask;
import com.WSZW.util.XmlUtil;

public abstract class ZWFW_Shixiang_List_ManagerService {
	public static final String TAG = "ZWFW_Shixiang_List_ManagerService";

	public void get_ZWFW_Shixiang_list(final Context paramActivity,
			final int paramInt, final List<Map<String, Object>> list) {
		final String url = paramActivity.getResources().getString(
				com.WSZW.Activity.R.string.endpoint);
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, false) {
			String result1 = null; // 从服务器获取到的数据
			List<Zwfw_Shixiang_List_Item> resultGroup1 = new ArrayList<Zwfw_Shixiang_List_Item>();// 将服务器获取到的数据转化为此列表

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result1 = downloadDBByMap("ffxx", url, list.get(0));
					resultGroup1 = XmlUtil.get_Zwfw_Shixiang_List(result1);
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result1 == null ||resultGroup1 == null || result1.equals("error")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					Constants.list_zwfw_shixiang = resultGroup1;
					Constants.COUNT_OF_LIST_ZWFW_SHIXIANG = resultGroup1.size();
					handleResult.setiSuccess("success");
				} else if (paramInteger == -1) {// 链接服务器失败
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					handleResult.setiSuccess("fail");
					return;
				}
				ZWFW_Shixiang_List_ManagerService.this.handlerLoginInfo(
						paramActivity, handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}

	public abstract void handlerLoginInfo(Context context,
			HandleResult paramHandleResult, int paramInt);

}
