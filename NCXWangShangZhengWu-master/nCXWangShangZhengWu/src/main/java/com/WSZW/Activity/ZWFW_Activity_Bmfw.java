package com.WSZW.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

public class ZWFW_Activity_Bmfw extends BaseActivity {
   private Drawable drawable;
   private ImageView iv;
   private Button bt;
   private ScrollView sv;
   private WebView wv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zwfw_activity_bmfw);
		/*Intent intent=getIntent();
		intent.getIntExtra("id", 0);*/
		iv=(ImageView) findViewById(R.id.ivS);
		bt=(Button) findViewById(R.id.btS);
		sv=(ScrollView) findViewById(R.id.Scroll);
		wv=(WebView) findViewById(R.id.wv);
		String sfbz="<br/><br/><br/><br/><br/><br/><br/><br/><div align=\"center\"><a href=\"http://www.189.cn/jx\">电信收费标准</a><br/><br/><br/><br/><a href=\"http://service.jx.10086.cn/service/resources/indexNew.html\">移动收费标准</a></div>";
	    String xgzn="<br/><br/><br/><br/><br/><br/><br/><br/><div align=\"center\"><a href=\"http://www.189.cn/jx\">电信相关指南</a><br/><br/><br/><br/><a href=\"http://service.jx.10086.cn/service/resources/indexNew.html\">移动相关指南</a></div>";
	    wv.setBackgroundColor(0);
	      wv.setBackgroundResource(R.drawable.zwfw_bg);
	      WebSettings webSettings=wv.getSettings();
		  webSettings.setJavaScriptEnabled(true);//支持js
		  webSettings.setSupportZoom(true); // 支持缩放
		
		
       if(getIntent().getIntExtra("id", -1)==8888){
			sv.setVisibility(View.GONE);
			wv.setVisibility(View.VISIBLE);
			wv.loadData(sfbz, "text/html;charset=utf-8", null);
			
		}else if (getIntent().getIntExtra("id", -1)==0000) {
			sv.setVisibility(View.GONE);
			wv.setVisibility(View.VISIBLE);
			wv.loadData(xgzn, "text/html;charset=utf-8", null);
			
		}else {
			drawable=getResources().getDrawable(getIntent().getIntExtra("id", -1));
			iv.setImageDrawable(drawable);
		}
		
		
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			    finish();
			}
		});
		
	}
	
}
