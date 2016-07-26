package com.WSZW.Activity;

import com.WSZW.entity.HandleResult;
import com.WSZW.service.ZWFW_PJTJ_ManagerService;
import com.WSZW.service.ZWFW_PJYZ_ManagerService;
import com.WSZW.util.StringUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 服务评议验证与提交
 * 
 * @author Administrator
 * 
 */
public class ZWFW_FWPY_MainActivity extends BaseActivity implements OnClickListener {
	private EditText idname, trafficNummber;
	private Button begin, close, back;
	private boolean state = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zwfw_assess_main1);
		
		initVIew();
	}

	/**
	 * 初始化组件
	 */
	private void initVIew() {
		trafficNummber = (EditText) findViewById(R.id.edit_TrafficNummber);
		idname = (EditText) findViewById(R.id.edit_IDCard);
		begin = (Button) findViewById(R.id.button_begin);
		close = (Button) findViewById(R.id.button_close);
		back = (Button) findViewById(R.id.btn_back);
		back.setOnClickListener(this);
		begin.setOnClickListener(this);
		close.setOnClickListener(this);
	}

	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return true;
	};

	ZWFW_PJYZ_ManagerService pjyz_manager = new ZWFW_PJYZ_ManagerService() {

		@Override
		protected void handlerLoginInfo(Context context,
				HandleResult handleResult, int paramInt) {
			if (handleResult.getiSuccess().equals("success")) {
				ZWFW_FWPY_MainActivity.this.startActivity(new Intent(ZWFW_FWPY_MainActivity.this,ZWFW_FWPY_TwoActivity.class));
			}
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_close:
			this.finish();

			break;
		case R.id.btn_back:
			this.finish();
			break;
		case R.id.button_begin:
			if(checkEdit()){
				pjyz_manager.getZWFW_PJYZ_dsell(context, 1, trafficNummber.getText().toString(), idname.getText().toString());
			}else{
				Toast.makeText(ZWFW_FWPY_MainActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
			}
			
			break;
		default:
			break;
		}
	}

	private boolean checkEdit() {

		return StringUtil.isNotBlank(trafficNummber.getText().toString()) && StringUtil.isNotBlank(idname.getText().toString());
	}
}
