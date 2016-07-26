package com.WSZW.Activity;

import java.util.Set;

import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.GWLM_DpwD_bean;
import com.WSZW.entity.HandleResult;
import com.WSZW.service.getDocumentManagerService;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GzLm_pictureContent_Activity2 extends BaseActivity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gzlm_picturecontent_mian2);
		Intent intent=getIntent();
		initView();
		initData();
	
	}  
	      
	
	    private void initData() {
		
	}
	    private void initView() {
	    	ll_back=(LinearLayout) findViewById(R.id.ll_back);
	    	ll_back.setOnClickListener(this);
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
