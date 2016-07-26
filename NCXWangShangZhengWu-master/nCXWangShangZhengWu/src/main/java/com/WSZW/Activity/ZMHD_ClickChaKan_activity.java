package com.WSZW.Activity;

import java.util.List;

import com.WSZW.adapter.ZMHD_ZX_listview_adapter;
import com.WSZW.adapter.ZMHD_chakan_listview_adapter;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.ZMHD_zx_bean;
import com.WSZW.service.getQueryLetterManagerService;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ZMHD_ClickChaKan_activity extends BaseActivity{
	
	private InfoFile_ infoFile_;
	private TextView tv_1,tv_2,tv_3,tv_4,tv_5,tv_6,tv_7,tv_8;
	private Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zmhd_lxxd_chakan);
		initView();
		infoFile_ = new InfoFile_(context);
		tv_1.setText(infoFile_.searchNo().get().toString());
		tv_2.setText(infoFile_.title().get().toString());
		tv_3.setText(infoFile_.content().get().toString());
		tv_4.setText(infoFile_.lettertypeName().get().toString());
		tv_5.setText(infoFile_.deliverTime().get().toString());
		tv_6.setText(infoFile_.replyTimeString().get().toString());
		tv_7.setText(infoFile_.replyContent().get().toString());
		tv_8.setText(infoFile_.score().get().toString().equals("0") ? "不满意" : "满意");
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				context.finish();
			}
		});
	}
	
	private void initView(){
		tv_1 = (TextView) findViewById(R.id.tv_1);
		tv_2 = (TextView) findViewById(R.id.tv_2);
		tv_3 = (TextView) findViewById(R.id.tv_3);
		tv_4 = (TextView) findViewById(R.id.tv_4);
		tv_5 = (TextView) findViewById(R.id.tv_5);
		tv_6 = (TextView) findViewById(R.id.tv_6);
		tv_7 = (TextView) findViewById(R.id.tv_7);
		tv_8 = (TextView) findViewById(R.id.tv_8);
		
		button = (Button) findViewById(R.id.btn);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return true;
	}
}
