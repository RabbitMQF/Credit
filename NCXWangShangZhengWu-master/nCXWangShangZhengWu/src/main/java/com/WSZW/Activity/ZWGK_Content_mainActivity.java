package com.WSZW.Activity;

import java.util.List;

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
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ZWGK_Content_mainActivity extends BaseActivity implements OnClickListener{
	private WebView webview;
	private ImageView iv_back;
	private LinearLayout ll_back;
	private InfoFile_ infoFile_;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zwgk_content_main);
		infoFile_ = new InfoFile_(context);
		initView();
//		webview.loadDataWithBaseURL(null,infoFile_.zwgk_url().get().toString(),"text/html", "UTF-8",null);
		webview.loadUrl(infoFile_.zwgk_url().get().toString());

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
