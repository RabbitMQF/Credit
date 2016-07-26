package com.WSZW.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import com.WSZW.data.Constants;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.Zwfw_bmfw_listView_item;
import com.WSZW.util.BaseAsyncTask;
import com.WSZW.util.StringUtil;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * 获取政务服务主题服务
 * 
 * @author Administrator
 * 
 */
public abstract class ZWFW_BM_ManagerService {
	private String TAG = "ZWFW_BM_ManagerService";
	

	/**
	 * 获取部门服务
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list传入的account
	 */
	public void getZWFW_bmfw(final Context paramActivity, final int paramInt,final List<Map<String, Object>> list) {
		final String url = paramActivity.getResources().getString(
				com.WSZW.Activity.R.string.bmpoint);
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, false) {
			
			String result=null;		//从服务其取得数据
			List<String> resultGroup = new ArrayList<String>(); //将取得数据转化为列表
			List<Zwfw_bmfw_listView_item> list_1 = new ArrayList<Zwfw_bmfw_listView_item>(); 
			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// TODO 自动生成的方法存根
				try {
					result=downloadDB("bmfw", url,null);		
					resultGroup = StringUtil.stringToNormalList(result);
					
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null || resultGroup == null || result.equals("error")) {
					return Integer.valueOf(-1);
				}
				initListDatas();
				return Integer.valueOf(1);
			}
			private void initListDatas() {
				for (int i = 0; i < resultGroup.size(); i++) {
					Zwfw_bmfw_listView_item bmfw= new Zwfw_bmfw_listView_item();
					String[] imgs = resultGroup.get(i).split("=");
					bmfw.setName(imgs[0]);
					bmfw.setId(imgs[1]);
					list_1.add(bmfw);
				}
			
				Constants.Zwfw_bmfw_listview_item=list_1;
			}
			@Override
			protected void onPostExecute(Integer paramInteger) {
				// TODO 自动生成的方法存根
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
//					 handleResult.setGetLeftMenuSuccess("success");
//					 handleResult.setList_leftmenu(resultGroup);
				} else if (paramInteger == -1) {// 链接服务器失败
					// handleResult.setGetLeftMenuSuccess("fail");
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					return;
			}
				ZWFW_BM_ManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
			
			};
			loacl.execute(1);

	}
	protected abstract void handlerLoginInfo(Context paramActivity,
			HandleResult handleResult, int paramInt);
}