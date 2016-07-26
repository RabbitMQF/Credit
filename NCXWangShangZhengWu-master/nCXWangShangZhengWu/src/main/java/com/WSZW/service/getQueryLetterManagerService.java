package com.WSZW.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import com.WSZW.data.Constants;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.ZMHD_bean;
import com.WSZW.entity.ZMHD_zx_bean;
import com.WSZW.util.BaseAsyncTask;
import com.WSZW.util.XmlUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public abstract class getQueryLetterManagerService {
	private String TAG = "ADA";
	private ZMHD_zx_bean bean;
	private String string, time, title, content,lettertypeName,replyContent,replyTimeString,searchNo,status;
	
	/**
	 * 获取信件列表接口
	 */
	public void getQueryLetter(final Context paramActivity, final int paramInt, final String channelId, final String lettertypeId,final String arg2, final String arg3){
		BaseAsyncTask local = new BaseAsyncTask(paramActivity, false){
			String result = null;
			ZMHD_bean datas;
			List<ZMHD_zx_bean> list = new ArrayList<ZMHD_zx_bean>(); 
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result = downloadDBS(paramActivity,channelId, lettertypeId,arg2, arg3);
					Log.i(TAG, "1222222222222222222222---"+result);
		//			InputStream inputStream = new ByteArrayInputStream(result.getBytes());
					string = XmlUtil.getQueryLetter(result);
					list = parseJsonMulti(string);

				} catch (MalformedURLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (bean == null || bean.equals("error")) {
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
						com.WSZW.data.Constants.zx_item=list;
						Constants.COUNT_OF_LIST_ZMHD = list.size();
					
			//		Constants.COUNT_OF_LIST_ZWFW_SHIXIANG=list.size();
					// handleResult.setList_leftmenu(resultGroup);
					
				} else if (paramInteger == -1) {// 链接服务器失败
					// handleResult.setGetLeftMenuSuccess("fail");
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					return;
			}
				getQueryLetterManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
			
			};
			local.execute(1);
			
	}
	//解析多个数据的Json
    private List<ZMHD_zx_bean> parseJsonMulti(String strResult) { 
    	List<ZMHD_zx_bean> list = new ArrayList<ZMHD_zx_bean>(); 
        try { 
            JSONArray jsonObjs = new JSONObject(strResult).getJSONArray("topics"); 
            for(int i = 0; i < jsonObjs.length() ; i++){ 
                JSONObject jsonObj = ((JSONObject)jsonObjs.opt(i));
         //      .getJSONObject("topics"); 
                time = jsonObj.getString("replyTimeString"); 
                title = jsonObj.getString("title");
                content = jsonObj.getString("content");
                lettertypeName = jsonObj.getString("lettertypeName");
                replyContent = jsonObj.getString("replyContent");
                replyTimeString = jsonObj.getString("deliverTime");
                searchNo = jsonObj.getString("searchNo");
                status = jsonObj.getString("status");
                
                Log.i("asdaaaaaaaaaaaaaaaaaaa", title);
                bean = new ZMHD_zx_bean();
                	 bean.setReplyTimeString(time);
                     bean.setTitle(title);
                     bean.setContent(content);
                     bean.setDeliverTime(replyTimeString);
                     bean.setLettertypeName(lettertypeName);
                     bean.setReplyContent(replyContent);
                     bean.setSearchNo(searchNo);
                     bean.setStatus(status);
                     
                     list.add(bean); 
                     
            //    list.get(i).setDeliverTime(time);
            //    list.get(i).setTitle(title);
            } 
            
        } catch (JSONException e) { 
            System.out.println("Jsons parse error !"); 
            e.printStackTrace(); 
        }
        return list;
    } 
	protected abstract void handlerLoginInfo(Context paramActivity,
			HandleResult handleResult, int paramInt);
			
	}



