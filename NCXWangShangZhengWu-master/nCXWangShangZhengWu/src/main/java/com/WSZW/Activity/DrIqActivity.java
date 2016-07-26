package com.WSZW.Activity;

import java.util.List;

import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.ZwFw_CX_BizInfo;
import com.WSZW.entity.ZwFw_CX_item_bean;
import com.WSZW.entity.Zwfw_Shixiang_List_Item;
import com.WSZW.service.ZWFW_CX_ManagerService;
import com.WSZW.util.StringUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DrIqActivity extends BaseActivity implements OnClickListener {
	private static String TAG = "DrlqActivity";
	private String tv1_v, tv2_v, tv3_v, tv4_v, tv5_v, tv6_v, tv7_v, tv8_v;
	private EditText id, number, sc;
	private List<ZwFw_CX_item_bean> datas;
	private String identityNumber, serialNumber;
	private ZwFw_CX_BizInfo bean;
	private Button clean, query, back;
	private String[] str;
	InfoFile_ infoFile_;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zwfw_query_main);
		infoFile_ = new InfoFile_(this);
		initView();
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
		case R.id.btn_back:
			this.finish();
			break;
		case R.id.button_clean:
			id.setText("");
			number.setText("");
			break;
		case R.id.button_query:
			if (checkEdit()) {
				infoFile_.edit().edittext_1_zwfw_search()
						.put(id.getText().toString()).apply();
				infoFile_.edit().edittext_2_zwfw_search()
						.put(number.getText().toString()).apply();
				Intent intent = new Intent();
				intent.setClass(DrIqActivity.this, DrIqActivity2.class);
				startActivity(intent);
			}
			break;
		default:
			break;
		}
	}

	private boolean checkEdit() {
		if (!StringUtil.IDCardValidate(id.getText().toString()).equals("")) {
			Toast.makeText(this,
					StringUtil.IDCardValidate(id.getText().toString()),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (number.getText().toString().trim().equals("")) {
			Toast.makeText(this, "业务流水号不能为空", Toast.LENGTH_SHORT).show();
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 初始化组件
	 */
	private void initView() {
		back = (Button) findViewById(R.id.btn_back);
		id = (EditText) findViewById(R.id.edit_IDCard);
		number = (EditText) findViewById(R.id.edit_TrafficNummber);
		clean = (Button) findViewById(R.id.button_clean);
		query = (Button) findViewById(R.id.button_query);
		clean.setOnClickListener(this);
		query.setOnClickListener(this);
		back.setOnClickListener(this);
	}
}
