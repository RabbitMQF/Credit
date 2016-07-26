package com.WSZW.Activity;


import com.WSZW.data.InfoFile_;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ZWGK_Form_content_mainActivity extends BaseActivity implements OnClickListener{
	private WebView webview;
	private InfoFile_ infofile;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zwgk_form_content_main);
		initView();
		System.out.println("++++++++++++++++++"+infofile.zwgk_url().get().toString());
		webview.loadUrl(infofile.zwgk_url().get().toString());
//		webview.loadUrl(infofile.prefixURL().get().toString()+infofile.zwgk_url().get().toString());
	}  
	

	    private void initView() {
	    	infofile=new InfoFile_(context);
//	    	tv = (WebView) findViewById(R.id.tv);  
//	    	tv.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动  
	    	
	    	iv_back = (ImageView) findViewById(R.id.iv_back);
	    	ll_back = (LinearLayout) findViewById(R.id.ll_back);
	    	webview = (WebView) findViewById(R.id.tv);
	    	//支持javascript
	    	webview.getSettings().setJavaScriptEnabled(true); 
	    	// 设置可以支持缩放 
//	    	webview.getSettings().setSupportZoom(true); 
	    	// 设置出现缩放工具 
	    	webview.getSettings().setBuiltInZoomControls(true);
//	    	//扩大比例的缩放
	    	webview.getSettings().setUseWideViewPort(true);   //添加会使图片与文字的显示错位
	    	//设置加载进来的页面自适应手机屏幕  
	        webview.getSettings().setUseWideViewPort(true);  
	        webview.getSettings().setLoadWithOverviewMode(true); 
	    	ll_back.setOnClickListener(this);
//	    	scrollView=(ScrollView) findViewById(R.id.scrollView);
	    	

	    	
	    }
	
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
