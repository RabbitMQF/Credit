package com.WSZW.service;

import com.WSZW.data.InfoFile_;
import com.WSZW.entity.HandleResult;
import com.WSZW.util.BaseAsyncTask;
import com.WSZW.util.XmlUtil;

import android.content.Context;
import android.util.Log;

public abstract class getSaveLetterManagerService {
	InfoFile_ infoFile_;
	
	public void getSaveLetter(final Context paramActivity, final int paramInt, final String content, final String email,
			final String lettertypeId, final String lettertypeName,final String metaJson, final String phone,
			final String title, final String writerName){
		
		BaseAsyncTask local = new BaseAsyncTask(paramActivity, false){
			String result = null;
			String str = null;
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					result = downloadDB(paramActivity, content, email, lettertypeId, lettertypeName, metaJson, phone,
							title, writerName);
					str = XmlUtil.getResult(result);
					Log.i("22222222222222222222222222", str);
				} catch (Exception e) {
					// TODO: handle exception
				}
				return Integer.valueOf(1);
				
			}
			@Override
			protected void onPostExecute(Integer paramInteger) {
				// TODO 自动生成的方法存根
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if(paramInteger == 1){
					handleResult.setiSuccess("success");
					infoFile_ = new InfoFile_(paramActivity);
					infoFile_.result().put(str);
					
				}else if(paramInteger == -1){
					return;
				}
				getSaveLetterManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}
		};
		local.execute(1);
	}

	protected abstract void handlerLoginInfo(Context paramActivity,
			HandleResult handleResult, int paramInt);
}
