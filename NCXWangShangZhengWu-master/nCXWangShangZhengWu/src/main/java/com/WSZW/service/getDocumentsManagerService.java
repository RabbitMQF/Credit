package com.WSZW.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import com.WSZW.data.Constants;
import com.WSZW.entity.GWLM_ZwDt_title;
import com.WSZW.entity.HandleResult;
import com.WSZW.util.BaseAsyncTask;
import com.WSZW.util.HttpUtil;
import com.WSZW.util.XmlUtil;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * 	获取文档列表接口
 * @author Administrator
 * 
 */
public abstract class getDocumentsManagerService {
	private GWLM_ZwDt_title titl;
	private String TAG = "IOS";

	/**3.1.	获取文档列表接口
	 * 
	 */
	public void getDocuments(final Context paramActivity, final int paramInt,final String chanIds, final String pageSize, final String pageIndex,final String  statusIds) {
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, false) {
			String result=null;		//从服务其取得数据
			private List<GWLM_ZwDt_title> list;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				
				try {
//					result = HttpUtil.getXML();
					result=downloadDB(paramActivity, chanIds, pageSize, pageIndex, statusIds);
				Log.i(TAG, "=========获取文档列表接口============"+result);
					InputStream   inputStream   =   new   ByteArrayInputStream(result.getBytes());
					list=XmlUtil.getTitle(result); 

				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
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
				// TODO 自动生成的方法存根
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					
					handleResult.setiSuccess("success");
					com.WSZW.data.Constants.gzlm_item=list;
					Constants.COUNT_OF_LIST_ZWFW_SHIXIANG=list.size();
					// handleResult.setList_leftmenu(resultGroup);
				} else if (paramInteger == -1) {// 链接服务器失败
					// handleResult.setGetLeftMenuSuccess("fail");
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					return;
			}
				getDocumentsManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
			
			};
			loacl.execute(1);

	}
	/**3.1.	获取文档列表接口
	 * 
	 */
	public void getDocumentsForFlash(final Context paramActivity, final int paramInt,final String chanIds, final String pageSize, final String pageIndex,final String  statusIds) {
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, false) {
			String result=null;		//从服务其取得数据
			private List<GWLM_ZwDt_title> list;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				
				try {
//					result = HttpUtil.getXML();
					result=downloadDBForFlash(paramActivity, chanIds, pageSize, pageIndex, statusIds);
				Log.i(TAG, "=========获取文档列表接口============"+result);
					InputStream   inputStream   =   new   ByteArrayInputStream(result.getBytes());
					list=XmlUtil.getTitle(result); 

				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (XmlPullParserException e) {
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
				// TODO 自动生成的方法存根
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					
					handleResult.setiSuccess("success");
					com.WSZW.data.Constants.gzlm_item=list;
					Constants.COUNT_OF_LIST_ZWFW_SHIXIANG=list.size();
					// handleResult.setList_leftmenu(resultGroup);
				} else if (paramInteger == -1) {// 链接服务器失败
					// handleResult.setGetLeftMenuSuccess("fail");
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					return;
			}
				getDocumentsManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
			
			};
			loacl.execute(1);

	}
	protected abstract void handlerLoginInfo(Context paramActivity,
			HandleResult handleResult, int paramInt);

}