package com.WSZW.Activity;

import com.WSZW.data.Constants;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.ZwFw_Fwpy_BizInfo;
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
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
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
public class ZWFW_FWPY_TwoActivity extends BaseActivity implements OnClickListener, android.widget.RadioGroup.OnCheckedChangeListener {
	Button btn_1,btn_2,btn_back;;
	TextView tv_1,tv_2,tv_3,tv_4,tv_5,tv_6,tv_7,tv_8,tv_9,tv_10;
	EditText ed_1;
	RadioButton rb_1,rb_2,rb_3,rb_4,rb_5;
	RadioGroup rg;
	ZwFw_Fwpy_BizInfo info = new ZwFw_Fwpy_BizInfo();
	String str_level = "1";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zwfw_fwpy_detailes);
		info = Constants.bean_fwpy;
		if(info != null){
			initVIew();
		}else{
			Toast.makeText(context, "该业务已评价，谢谢！", Toast.LENGTH_SHORT).show();
			this.finish();
		}
	}

	private void initDatas() {
		tv_1.setText(info.getShixiang_name().toString() != null ? info.getShixiang_name().toString() : "-");
		tv_2.setText(info.getFwsxbjzt().toString() != null ? info.getFwsxbjzt().toString() : "-");
		tv_3.setText(info.getSllsh().toString() != null ? info.getSllsh().toString() : "-");
		tv_4.setText(info.getBanshiren().toString() != null ? info.getBanshiren().toString() : "-");
		tv_5.setText(info.getBanlijigou().toString() != null ? info.getBanlijigou().toString() : "-");
		tv_6.setText(info.getZhuguanbumen().toString() != null ? info.getZhuguanbumen().toString() : "-");
		tv_7.setText(info.getShixiang_name().toString() != null ? info.getShixiang_name().toString() : "-");
		tv_8.setText(info.getShenban_date().toString() != null ? info.getShenban_date().toString() : "-");
		tv_9.setText(info.getBanjie_date().toString() != null ? info.getBanjie_date().toString() : "-");
		tv_10.setText(info.getBanlididiab().toString() != null ? info.getBanlididiab().toString() : "-");
	}

	/**
	 * 初始化组件
	 */
	private void initVIew() {
		btn_1 = (Button) findViewById(R.id.tijiao);
		btn_2 = (Button) findViewById(R.id.close);
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
		ed_1 = (EditText) findViewById(R.id.et_1);
		rb_1 = (RadioButton) findViewById(R.id.rb_1);
		rb_2 = (RadioButton) findViewById(R.id.rb_2);
		rb_3 = (RadioButton) findViewById(R.id.rb_3);
		rb_4 = (RadioButton) findViewById(R.id.rb_4);
		rb_5 = (RadioButton) findViewById(R.id.rb_5);
		rg = (RadioGroup) findViewById(R.id.rg);
		btn_1.setOnClickListener(this);
		btn_2.setOnClickListener(this);
		btn_back.setOnClickListener(this);
		rg.setOnCheckedChangeListener(this);
		initDatas();
	}

	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return true;
	};

	private ZWFW_PJTJ_ManagerService pjtj = new ZWFW_PJTJ_ManagerService() {
		
		@Override
		protected void handlerLoginInfo(Context context, HandleResult handleResult,
				int paramInt) {
			if(handleResult.getiSuccess().toString().trim().equals("success")){
				info.setPy_content(ed_1.getText().toString().trim()!= null? ed_1.getText().toString().trim():"-");
				info.setStr_level(str_level);
				Constants.bean_fwpy = info;
				ZWFW_FWPY_TwoActivity.this.startActivity(new Intent(ZWFW_FWPY_TwoActivity.this, ZWFW_FWPY_ThreeActivity.class));
			}else{
				Toast.makeText(context, "评议失败！", Toast.LENGTH_SHORT).show();
			}
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tijiao:
			pjtj.getZWFW_PJTJ_dsell(context, 1, str_level, ed_1.getText().toString() != null ? ed_1.getText().toString():"",
					info.getSllsh().toString() != null ? info.getSllsh().toString() : "",
							info.getBanshiren().toString() != null ? info.getBanshiren().toString() : "");
			break;
		case R.id.close:ZWFW_FWPY_TwoActivity.this.finish();
		break;
		case R.id.btn_back:ZWFW_FWPY_TwoActivity.this.finish();break;
		default:
			break;
		}
	}


	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.rb_1:
			str_level = "1";
			Toast.makeText(context, str_level, Toast.LENGTH_SHORT).show();
			break;
		case R.id.rb_2:
			str_level = "2";
			Toast.makeText(context, str_level, Toast.LENGTH_SHORT).show();
			break;
		case R.id.rb_3:
			str_level = "3";
			Toast.makeText(context, str_level, Toast.LENGTH_SHORT).show();
			break;
		case R.id.rb_4:
			str_level = "4";
			Toast.makeText(context, str_level, Toast.LENGTH_SHORT).show();
			break;
		case R.id.rb_5:
			str_level = "5";
			Toast.makeText(context, str_level, Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
		
	}

}
