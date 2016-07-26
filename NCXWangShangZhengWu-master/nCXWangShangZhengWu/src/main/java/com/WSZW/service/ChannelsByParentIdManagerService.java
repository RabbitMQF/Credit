package com.WSZW.service;

import java.io.IOException;
import java.net.MalformedURLException;






import java.util.List;

import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.GWLM_DpwD_bean;
import com.WSZW.entity.GWLM_ZwDt_title;
import com.WSZW.entity.GzLm_secondColumn_bean;
import com.WSZW.entity.HandleResult;
import com.WSZW.util.BaseAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * 获取子栏目列表接口
 * @author Administrator
 * 
 */
public abstract class ChannelsByParentIdManagerService {
	private GWLM_ZwDt_title titl;
	private String TAG = "NOY";
	InfoFile_ infofile;

	/**
	 * 	获取子栏目列表接口
	 */
	public void getChannels(final Context paramActivity, final int paramInt,final String methodName,final String endPoint,final long arg0,final long arg1,final int arg2) {
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result=null;		//从服务其取得数据
			GzLm_secondColumn_bean bean;
			List<GzLm_secondColumn_bean> list;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=downloadDBB(paramActivity,"getChannelsByParentId", endPoint, arg0, arg1, arg2);
					Log.i(TAG, "获取子栏目列表接口---------"+result);
					 Gson gson = new Gson();
				     if(result!="error"){  
			            list = gson.fromJson(result,
			                    new TypeToken<List<GzLm_secondColumn_bean>>() {
			                    }.getType());
					
				     }
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} 
//				catch (XmlPullParserException e) {
//					e.printStackTrace();
//				}
				
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null || result.equals("error")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}
			@Override
			protected void onPostExecute(Integer paramInteger) {
				// TODO 自动生成的方法存根
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					if (result != null) {
						handleResult.setiSuccess("success");
						 Constants.gzlm_second_bean=list;
						 
					}
				} else if (paramInteger == -1) {// 链接服务器失败
					// handleResult.setGetLeftMenuSuccess("fail");
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					return;
			}
				ChannelsByParentIdManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
			
			};
			loacl.execute(1);

	}
	protected abstract void handlerLoginInfo(Context paramActivity,
			HandleResult handleResult, int paramInt);

}