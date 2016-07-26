package com.WSZW.service;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.WSZW.entity.HandleResult;
import com.WSZW.entity.ZwFw_CX_BizInfo;
import com.WSZW.util.BaseAsyncTask;
import com.WSZW.util.XmlUtil;

public abstract class ZWFW_PJTJ_ManagerService {
	private String TAG="ZWFW_PJTJ_ManagerService";
	/**
	 * 获取服务评议提交
	 * 
	 * @param paramActivity调用这个方法的Activity
	 * @param paramInt
	 * 						代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list list出入的account
	 */
	public void getZWFW_PJTJ_dsell(final Context paramActivity,final int paramInt,final String str1,final String str2,final String str3,final String str4){
		final String url =paramActivity.getResources().getString(com.WSZW.Activity.R.string.endpoint);
		
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,true){
			
			String result=null; //从服务器获取xml数据
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result=downloadDDBB("doStartEvaluate2",url,str1,str2,str3,str4);
					Log.i(TAG, "==============="+result);
				} catch (Exception e) {
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
				HandleResult handleResult=new HandleResult();
				if(paramInteger==1){//获取返回的信息
					handleResult.setiSuccess("success");
				}else if (paramInteger==-1) {
					Toast.makeText(paramActivity,"连接服务器失败！", Toast.LENGTH_LONG).show();
					handleResult.setiSuccess("fail");
					return;
				}
				ZWFW_PJTJ_ManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	protected abstract void handlerLoginInfo(Context context,
			HandleResult handleResult, int paramInt);
}