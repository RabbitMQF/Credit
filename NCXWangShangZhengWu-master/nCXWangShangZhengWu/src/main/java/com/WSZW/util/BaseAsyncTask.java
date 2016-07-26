package com.WSZW.util;

import android.os.AsyncTask;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import org.ksoap2.SoapFault;
import org.xmlpull.v1.XmlPullParserException;
import com.WSZW.view.LoadingDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

public class BaseAsyncTask extends AsyncTask<Integer, Integer, Integer> {
	private static final int SHOW_BAR = 4097;
	public static final int UPDATE_FAIL = -1;
	public static final int UPDATE_SUCCEED = 1;
	private Handler asyncHandler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case SHOW_BAR:
				if (LD != null && context != null) {
					// if (((BaseActivity) context).isShowDialog()) {
					LD.show();
					// ((BaseActivity) context).setOnCloseProgressDialog(LD);
					// } else {
					// LD.cancel();
					// }
				}
				break;
			}
		}
	};
	private Context context;
	private LoadingDialog LD;
	protected String resultGroup;
	private boolean showBar = false;
	private int showTime = 1000;
	public static String str_url;

	public BaseAsyncTask(Context paramContext) {
		this.context = paramContext;

	}

	public BaseAsyncTask(Context paramContext, boolean paramBoolean) {
		this.context = paramContext;
		this.showBar = paramBoolean;
	}

	private void closeBar() {
		if (this.LD != null) {
			this.LD.dismiss();
			this.LD = null;
			this.context = null;
		}
	}

	public static String downloadDB(Context context, String methodName,
			String endPoint, String str) throws XmlPullParserException,
			IOException {
		String result = HttpUtil.getXmlData(context, methodName, endPoint, str);

		return result;

	}
	
	/**
	 * 在线申办后添加的方法
	 * 
	 * @param 
	 * 
	 */
	
	public String downloadDB2(String methodName, String endPoint,
			List<Map<String, Object>> paramList) throws XmlPullParserException,
			IOException {
		return downloadDB(this.context, methodName, endPoint, paramList);
	}
	
	/**
	 * 在线申办后添加的方法
	 * 
	 * @param 
	 * 
	 */
	public static String downloadDB(Context context, String methodName,
			String endPoint, List<Map<String, Object>> paramList)
			throws XmlPullParserException, IOException {
		String result = HttpUtil.getXmlData(context, methodName, endPoint,
				paramList);
		// List<Map<String, Object>> list = XmlUtil.XmlParse(result);
		// if ((result == null) ) {
		// return null;
		// }
		// for(int i=0;i<list.size();i++){
		// Log.e("rjh-------------------->>>>>>>>", list.get(i).toString());
		// }

		return result;

	}
	

	public static String downloadDBByMap(Context context, String methodName,
			String endPoint, Map<String, Object> map)
			throws XmlPullParserException, IOException {
		String result = HttpUtil.getXmlDataByMap(context, methodName, endPoint,
				map);

		return result;

	}
	public static String downloadDBB(Context context, String methodName,
			String endPoint, String str1,String str2) throws XmlPullParserException,
			IOException {
		String result = HttpUtil.getXmlData(context, methodName, endPoint, str1,str2);
		
		return result;
		
	}
	public static String downloadDDBB(Context context, String methodName,
			String endPoint, String str1,String str2,String str3,String str4) throws XmlPullParserException,
			IOException {
		String result = HttpUtil.getXmlData2(context, methodName, endPoint, str1,str2,str3,str4);
		
		return result;
		
	}

	private void setDownLoadProgress() {
		this.LD = new LoadingDialog(context, "", false);
		this.LD.setTitle("正在交互数据");
		this.LD.setMessage("请稍候...");
		this.LD.setCanceledOnTouchOutside(false);
		this.LD.setOnCancelListener(new DialogInterface.OnCancelListener() {
			public void onCancel(DialogInterface paramDialogInterface) {

			}
		});
		// this.LD.setProgressStyle(0);
		this.asyncHandler.sendEmptyMessageDelayed(SHOW_BAR, this.showTime);
	}

	protected Integer doInBackground(Integer[] paramArrayOfInteger) {
		return 1;
	}

	public String downloadDB(String methodName, String endPoint, String str)
			throws XmlPullParserException, IOException {
		return downloadDB(this.context, methodName, endPoint, str);
	}
	public String downloadDBB(String methodName, String endPoint, String str1,String str2)
			throws XmlPullParserException, IOException {
		return downloadDBB(this.context, methodName, endPoint, str1,str2);
	}
	public String downloadDDBB(String methodName, String endPoint, String str1,String str2,String str3,String str4)
			throws XmlPullParserException, IOException {
		return downloadDDBB(this.context, methodName, endPoint, str1,str2,str3,str4);
	}

	public String downloadDBByMap(String methodName, String endPoint,
			Map<String, Object> map) throws XmlPullParserException, IOException {
		return downloadDBByMap(this.context, methodName, endPoint, map);
	}
	/**
	 * 获取文档列表接口
	 */
	public String downloadDB( Context context,String chanIds,String pageSize,String pageIndex,String statusIds) throws MalformedURLException, IOException{
		String result=HttpUtil.getXML(context, chanIds, pageSize, pageIndex, statusIds);
		return result;
	}
	/**
	 * 获取文档列表接口
	 */
	public String downloadDBForFlash( Context context,String chanIds,String pageSize,String pageIndex,String statusIds) throws MalformedURLException, IOException{
		String result=HttpUtil.getXMLForFlash(context, chanIds, pageSize, pageIndex, statusIds);
		return result;
	}
	/**
	 * 获取单篇文章接口
	 */
	public String downloadDB( Context context,String chanDocId) throws MalformedURLException, IOException{
		String result=HttpUtil.getDocumentXML(context, chanDocId);
		return result;
	}
	
	/**
	 * 获取子栏目列表接口
	 */
	public String downloadDBB( Context context,String methodName,String endPoint,long arg0,long arg1,int arg2) throws MalformedURLException, IOException{
		String result=HttpUtil.getChannelsByParentIdXML(context, methodName, endPoint, arg0, arg1, arg2);
		return result;
	}
	/**
	 *  	获取信息公开目录一级分类对象集合接口
	 */
	public String downloadDBB(Context context ,String arg0) throws MalformedURLException, IOException{
		String result=HttpUtil.getGovInfoTypesByParent(context,arg0);
		return result;
	}
	/**
	 *  	获取信息公开目录二级级分类对象集合接口
	 */
	public String downloadDBB(Context context ,String arg0,String arg2) throws MalformedURLException, IOException{
		String result=HttpUtil.getGovInfoTypesByParentTwo(context,arg0,arg2);
		return result;
	}
	/**
	 *  	获取信息公开文档列表数据接口
	 */
	public String downloadDDB(Context context ,String organId,String Id,String pageNum,String pageSize) throws MalformedURLException, IOException{
		String result=HttpUtil.getGovInfoDocuments(context, organId, Id, pageNum, pageSize);
		return result;
	}
	/**
	 * 信件列表
	 */
	public String downloadDBS(Context context,String channelId, String lettertypeId,String arg2, String arg3) throws MalformedURLException, IOException{
		String result = HttpUtil.getqueryLetter(context, channelId, lettertypeId,arg2, arg3);
		return result;
	}
	/**
	 * 县长信箱
	 */
	public String downloadDB(Context context,String content,String email,String lettertypeId,String lettertypeName,
			String metaJson,String phone,String title,String writerName) throws MalformedURLException, IOException{
		String result = HttpUtil.saveLetter(context, content, email, lettertypeId, lettertypeName, metaJson, phone, title, writerName);
			return result;
	}
	
	protected void onPostExecute(Integer paramInteger) {
		super.onPostExecute(paramInteger);
		if (this.showBar)
			closeBar();
	}

	protected void onPreExecute() {
		super.onPreExecute();
		if (this.showBar) {
			setDownLoadProgress();
		}
	}
	public int getShowTime() {
		return this.showTime;
	}

	public boolean isShowBar() {
		return this.showBar;
	}

	public void setShowBar(boolean paramBoolean) {
		this.showBar = paramBoolean;
	}

	public void setShowTime(int paramInt) {
		this.showTime = paramInt;
	}
}
