package com.WSZW.Activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.WSZW.data.Constants;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.ZwFw_Fwpy_BizInfo;
import com.WSZW.service.ZWFW_PJTJ_ManagerService;
import com.WSZW.service.ZWFW_PJYZ_ManagerService;
import com.WSZW.util.StringUtil;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 服务评议验证与提交
 * 
 * @author Administrator
 * 
 */
public class ZWFW_FWPY_ThreeActivity extends BaseActivity implements
		OnClickListener {
	TextView tv_1, tv_2, tv_3, tv_4, tv_5, tv_6, tv_7, tv_8, tv_9, tv_10,
			tv_11, tv_12, tv_13;
	ImageView iv_1,iv_2,iv_3,iv_4,iv_5;
	Button btn_back;
	ZwFw_Fwpy_BizInfo info = new ZwFw_Fwpy_BizInfo();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zwfw_fwpy_detail);
		info = Constants.bean_fwpy;
		initVIew();
		initDatas();
	}

	@SuppressWarnings("deprecation")
	private void initDatas() {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss ");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		tv_1.setText(info.getShixiang_name().toString() != null ? info
				.getShixiang_name().toString() : "-");
		tv_2.setText(info.getFwsxbjzt().toString() != null ? info.getFwsxbjzt()
				.toString() : "-");
		tv_3.setText(info.getSllsh().toString() != null ? info.getSllsh()
				.toString() : "-");
		tv_4.setText(info.getBanshiren().toString() != null ? info
				.getBanshiren().toString() : "-");
		tv_5.setText(info.getBanlijigou().toString() != null ? info
				.getBanlijigou().toString() : "-");
		tv_6.setText(info.getZhuguanbumen().toString() != null ? info
				.getZhuguanbumen().toString() : "-");
		tv_7.setText(info.getShixiang_name().toString() != null ? info
				.getShixiang_name().toString() : "-");
		tv_8.setText(info.getShenban_date().toString() != null ? info
				.getShenban_date().toString() : "-");
		tv_9.setText(info.getBanjie_date().toString() != null ? info
				.getBanjie_date().toString() : "-");
		tv_10.setText(info.getBanlididiab().toString() != null ? info
				.getBanlididiab().toString() : "-");
		tv_12.setText(info.getPy_content().toString() != null ? info
				.getPy_content().toString() : "-");
		tv_13.setText(str);
		if (info.getStr_level().toString().equals("1")) {
			iv_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_x));
			iv_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_xs));
			iv_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_xs));
			iv_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_xs));
			iv_5.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_xs));
		}
		if (info.getStr_level().toString().equals("2")) {
			iv_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_x));
			iv_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_x));
			iv_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_xs));
			iv_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_xs));
			iv_5.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_xs));
		}
		if (info.getStr_level().toString().equals("3")) {
			iv_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_x));
			iv_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_x));
			iv_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_x));
			iv_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_xs));
			iv_5.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_xs));
		}
		if (info.getStr_level().toString().equals("4")) {
			iv_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_x));
			iv_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_x));
			iv_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_x));
			iv_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_x));
			iv_5.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_xs));
		}
		if (info.getStr_level().toString().equals("5")) {
			iv_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_x));
			iv_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_x));
			iv_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_x));
			iv_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_x));
			iv_5.setBackgroundDrawable(getResources().getDrawable(R.drawable.dl_x));
		}
	}

	/**
	 * 初始化组件
	 */
	private void initVIew() {
		btn_back = (Button) findViewById(R.id.btn_back);
		tv_1 = (TextView) findViewById(R.id.tv_item_1);
		tv_2 = (TextView) findViewById(R.id.tv_item_2);
		tv_3 = (TextView) findViewById(R.id.tv_item_3);
		tv_4 = (TextView) findViewById(R.id.tv_item_4);
		tv_5 = (TextView) findViewById(R.id.tv_item_5);
		tv_6 = (TextView) findViewById(R.id.tv_item_6);
		tv_7 = (TextView) findViewById(R.id.tv_item_7);
		tv_8 = (TextView) findViewById(R.id.tv_item_8);
		tv_9 = (TextView) findViewById(R.id.tv_item_9);
		tv_10 = (TextView) findViewById(R.id.tv_item_10);
		tv_11 = (TextView) findViewById(R.id.tv_item_11);
		tv_12 = (TextView) findViewById(R.id.tv_item_12);
		tv_13 = (TextView) findViewById(R.id.tv_item_13);
		iv_1 = (ImageView) findViewById(R.id.iv_1);
		iv_2 = (ImageView) findViewById(R.id.iv_2);
		iv_3 = (ImageView) findViewById(R.id.iv_3);
		iv_4 = (ImageView) findViewById(R.id.iv_4);
		iv_5 = (ImageView) findViewById(R.id.iv_5);
		btn_back.setOnClickListener(this);
	}

	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return true;
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			ZWFW_FWPY_ThreeActivity.this.finish();
			break;
		default:
			break;
		}
	}

}
