package com.WSZW.service;

import java.util.ArrayList;
import java.util.List;

import com.WSZW.data.Constants;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.ZwFw_CX_BizInfo;
import com.WSZW.entity.ZwFw_CX_item_bean;
import com.WSZW.util.BaseAsyncTask;
import com.WSZW.util.StringUtil;
import com.WSZW.util.XmlUtil;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public abstract class ZWFW_CX_ManagerService {
		private String TAG="ZWFW_CX_ManagerService";
	
	/**
	 * 获取结果查询
	 * 
	 * @param paramActivity调用这个方法的Activity
	 * @param paramInt
	 * 						代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list list出入的account
	 */
	public void getZWFW_CX_driq(final Context paramActivity,final int paramInt,final String str1,final String str2){
		final String url =paramActivity.getResources().getString(com.WSZW.Activity.R.string.endpoint);
		
		BaseAsyncTask loacl =new BaseAsyncTask(paramActivity,false){
			
			String result=null; //从服务器获取xml数据
			ZwFw_CX_BizInfo resultGroup=new ZwFw_CX_BizInfo();
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// TODO 自动生成的方法存根
//				String str1="360121100001-141104-00001";//业务流水号
//				String str2="362330198810185057";			//身份证号
				try {
					result=downloadDBB("doResultInquire",url,str1,str2);
					Log.i(TAG, "***********************"+result);
					resultGroup=XmlUtil.get_Zwfw_CX_BizInfo(result);
			//	Constants.list_cx_item= resultGroup.getList();
//					Constants.list_zwfw_cx_bizinfo=resultGroup;
				} catch (Exception e) {
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null ||resultGroup == null || result.equals("error")) {
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
					handleResult.setBean_cx(resultGroup);
					handleResult.setiSuccess("success");
				}else if (paramInteger==-1) {
					Toast.makeText(paramActivity,"连接服务器失败！", Toast.LENGTH_LONG).show();
					handleResult.setiSuccess("fail");
					return;
				}
				ZWFW_CX_ManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		loacl.execute(1);
	}
	protected abstract void handlerLoginInfo(Context context,
			HandleResult handleResult, int paramInt);
}
