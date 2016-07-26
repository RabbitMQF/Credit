package com.WSZW.Activity;

import java.util.Set;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GzLm_pictureContent_mainActivity extends BaseActivity implements OnClickListener{
	private WebView webview;
	private ImageView iv_back;
	private LinearLayout ll_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gzlm_picturecontent_mian);
//		Set<String> str = getIntent().getCategories();
		Intent intent=getIntent();
		String url=intent.getStringExtra("url");
		initView();
//		webview.loadDataWithBaseURL(null,infoFile_.zwgk_url().get().toString(),"text/html", "UTF-8",null);
		
		webview.loadUrl(url);

	}  
	      
	
	    private void initView() {
	    	
	    	ll_back = (LinearLayout) findViewById(R.id.ll_back);
	    	webview = (WebView) findViewById(R.id.tv);
	    	//支持javascript
	    	webview.getSettings().setJavaScriptEnabled(true); 
	    	// 设置可以支持缩放 
	    	webview.getSettings().setSupportZoom(true); 
	    	// 设置出现缩放工具 
	    	webview.getSettings().setBuiltInZoomControls(true);
	    	//扩大比例的缩放
	    	webview.getSettings().setUseWideViewPort(true);
	    	//自适应屏幕
	    	webview.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
	    	webview.getSettings().setLoadWithOverviewMode(true);
	    	ll_back.setOnClickListener(this);
//	    	scrollView=(ScrollView) findViewById(R.id.scrollView);
	    

	    	
	    }


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
