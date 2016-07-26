package com.WSZW.Activity;


import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.GWLM_DpwD_bean;
import com.WSZW.entity.HandleResult;
import com.WSZW.service.getDocumentManagerService;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GZLM_Content_mainActivity extends BaseActivity implements OnClickListener{
	private WebView webview;
	private TextView tv,tv_fabutime,tv_author;
	private InfoFile_ infofile;
	private String chanDocId,fabutime;
	private String datas;
	private GWLM_DpwD_bean bean;
	private TextView tv_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gzlm_content_main);
		initView();
		initData();
	
	}  
	      
	
	    private void initData() {
	    	String title=infofile.gzlm_title().get().toString();
	    	tv_title.setText(title);
			chanDocId=infofile.chanDocId().get().toString();
			fabutime=infofile.writeTime().get().toString();
			System.out.println("++++++++++"+fabutime);
			System.out.println("chanDocId++++++++++"+chanDocId);
			tv_fabutime.setText(fabutime);
			getdocument.getDocuments(this, 1, chanDocId);
		
	}
	    private void initView() {
	    	infofile=new InfoFile_(context);
//	    	tv = (WebView) findViewById(R.id.tv);  
//	    	tv.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动  
	    	
	    	iv_back = (ImageView) findViewById(R.id.iv_back);
	    	ll_back = (LinearLayout) findViewById(R.id.ll_back);
	    	tv_title=(TextView) findViewById(R.id.tv_title);
	    	tv_fabutime=(TextView) findViewById(R.id.tv_fabutime);
	    	tv_author=(TextView) findViewById(R.id.tv_author);
	    	webview = (WebView) findViewById(R.id.tv);
	    	//支持javascript
	    	webview.getSettings().setJavaScriptEnabled(true); 
	    	// 设置可以支持缩放 
	    	webview.getSettings().setSupportZoom(true); 
	    	// 设置出现缩放工具 
	    	webview.getSettings().setBuiltInZoomControls(true);
//	    	//扩大比例的缩放
//	    	webview.getSettings().setUseWideViewPort(true);
	    	//设置加载进来的页面自适应手机屏幕  
//	        webview.getSettings().setUseWideViewPort(true);  
//	        webview.getSettings().setLoadWithOverviewMode(true); 
	    	ll_back.setOnClickListener(this);
//	    	scrollView=(ScrollView) findViewById(R.id.scrollView);
	    

	    	
	    }
	    getDocumentManagerService getdocument=new  getDocumentManagerService() {
	    	private String tag="ys";
			private String data;
	    	
	    	@Override
	    	protected void handlerLoginInfo(Context paramActivity,
	    			HandleResult handleResult, int paramInt) {
	    		if (handleResult.getiSuccess() != null && handleResult.getiSuccess().equals("success")) {
	    			bean=Constants.gzlm_dpwd_bean;
	    			//作者
	    			tv_author.setText(bean.getAuthor());
	    			//查看次数
	    			
	    			datas=bean.getDocContent().toString();
	    			data=datas;
	    			data = data.replace("/uploadfiles", "http://www.ncx.gov.cn/uploadfiles");
	    			data = data.replace("/UploadFiles", "http://www.ncx.gov.cn/uploadfiles");
	    			data =data.replaceAll("<img.*src/>", "<img src");
	    			data =data.replaceAll("<IMG.*src/>", "<img src");
	    			data =data.replaceAll("jpg\".*/>", "jpg\"/>");
	    			data =data.replaceAll("JPG\".*/>", "JPG\"/>");
	    			data =data.replaceAll("png\".*/>", "png\"/>");
	    			data =data.replaceAll("PNG\".*/>", "PNG\"/>");
	    			data =data.replaceAll("jpg\".*/>", "jpg\"style=\"width: 320px; height: 200px\"/>");
	    			data =data.replaceAll("JPG\".*/>", "JPG\"style=\"width: 320px; height: 200px\"/>");
	    			data =data.replaceAll("png\".*/>", "png\"style=\"width: 320px; height: 200px\"/>");
	    			data =data.replaceAll("PNG\".*/>", "PNG\"style=\"width: 320px; height: 200px\"/>");
	    			
//	    			data =data.replaceAll("style=\"width:.*px\"", "style=\"width: 350px; height: 250px\"");
//					data = data.replace(".jpg\" />", ".jpg\" style=\"width: 350px; height: 250px\"/>");
//					data = data.replace(".JPG\" />", ".jpg\" style=\"width: 350px; height: 250px\"/>");
//					data =data.replaceAll("style=\"height:.*/>", "style=\"width: 350px; height: 250px\"/>");
//					data =data.replaceAll("style=\"width:.*/>", "style=\"width: 350px; height: 250px\"/>");
//					data = data.replace("width: 600px", "width: 350px");
//					data = data.replace("height: 450px",
//							"height: 250px");
					System.out.println(data);
	    			if(datas!=null){
//	    				tv.setText(Html.fromHtml(datas));
	    				webview.loadDataWithBaseURL(null,data,"text/html", "UTF-8",null);
	    			}else {
//	    				tv.set("没有内容");
	    			}
	    		}
	    	}
	    };
		private ImageView iv_back;
		private LinearLayout ll_back;


	    @Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				this.finish();
			}
			return true;
		}


		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ll_back:
				this.finish();
				break;

			default:
				break;
			}
			
		}

}
