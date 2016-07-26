package com.WSZW.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.GWLM_ZwDt_title;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.ZWGK_FirstColumn_bean;
import com.WSZW.entity.ZWGK_formcontent_bean;
import com.WSZW.util.BaseAsyncTask;
import com.WSZW.util.XmlUtil;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * 获取信息公开文档列表数据接口
 * @author Administrator
 * 
 */
public abstract class getGovInfoDocumentsManagerService {
	private GWLM_ZwDt_title titl;
	private String TAG = "NOY";
	InfoFile_ infofile;

	/**
	 * 	获取信息公开文档列表数据接口
	 */
	public void getGovInfoDocuments(final Context paramActivity, final int paramInt,final String organId, final String  Id,final String pageNum,final String  pageSize) {
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result=null;		//从服务其取得数据
			List<ZWGK_formcontent_bean> list=null;
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				
				try {
					result=downloadDDB(paramActivity,organId, Id, pageNum, pageSize);
					Log.i(TAG, "获取信息公开文档列表数据接口---------"+result);
					InputStream   inputStream   =   new   ByteArrayInputStream(result.getBytes());
					list=XmlUtil.getGovInfo(result);
//					Log.i(TAG, "其中一条数据一条数据"+list.get(0).getTitle().toString());
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
					if (result != null) {
						handleResult.setiSuccess("success");
					Constants.list_formcontent_bean=list;
					Constants.COUNT_OF_LIST_ZWFW_SHIXIANG=list.size();
					}
				} else if (paramInteger == -1) {// 链接服务器失败
					// handleResult.setGetLeftMenuSuccess("fail");
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					return;
			}
				getGovInfoDocumentsManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
			
			};
			loacl.execute(1);

	}
	protected abstract void handlerLoginInfo(Context paramActivity,
			HandleResult handleResult, int paramInt);

}