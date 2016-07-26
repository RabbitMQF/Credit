package com.WSZW.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;


import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.GWLM_DpwD_bean;
import com.WSZW.entity.GWLM_ZwDt_title;
import com.WSZW.entity.HandleResult;
import com.WSZW.util.BaseAsyncTask;
import com.WSZW.util.XmlUtil;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * 获取单篇文章接口
 * @author Administrator
 * 
 */
public abstract class getDocumentManagerService {
	private GWLM_ZwDt_title titl;
	private String TAG = "NOY";
	InfoFile_ infofile;

	/**
	 * 	获取单篇文档接口
	 */
	public void getDocuments(final Context paramActivity, final int paramInt,final String chanDocId) {
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result=null;		//从服务其取得数据
			GWLM_DpwD_bean bean;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				
				try {
					result=downloadDB(paramActivity, chanDocId);
//					Log.i(TAG, "获取单篇文档接口---------"+result);
					InputStream   inputStream   =   new   ByteArrayInputStream(result.getBytes());
					bean=XmlUtil.getDpWzContent(result);
					
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
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
				// TODO 自动生成的方法存根
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					if (result != null) {
						handleResult.setiSuccess("success");
						 Constants.gzlm_dpwd_bean=bean;
					}
				} else if (paramInteger == -1) {// 链接服务器失败
					// handleResult.setGetLeftMenuSuccess("fail");
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					return;
			}
				getDocumentManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
			
			};
			loacl.execute(1);

	}
	protected abstract void handlerLoginInfo(Context paramActivity,
			HandleResult handleResult, int paramInt);

}