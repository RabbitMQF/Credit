package com.WSZW.Activity;

import com.WSZW.data.InfoFile_;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.ZWFW_ShiXiang_Detai_xzcf_bean;
import com.WSZW.service.ZWFW_Shixiang_Detail_ManagerService;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ZWFW_Detail_xzcf_Activity extends BaseActivity implements OnClickListener{
	InfoFile_ infofile;
	ZWFW_ShiXiang_Detai_xzcf_bean bean;
	TextView tv_title,tv_1,tv_3,tv_4,tv_5,tv_6,tv_7,tv_8;
	Button btn_back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zwfw_shixiang_xzcf_detail_main);
		infofile = new InfoFile_(this);
		initViews();
		detail_manager.get_ZWFW_Shixiang_xzcf_Detail(context, 1, infofile.shixiang_id().get().toString() != null ? infofile.shixiang_id().get() : "null");
	}

	
	private ZWFW_Shixiang_Detail_ManagerService detail_manager = new ZWFW_Shixiang_Detail_ManagerService() {
		
		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
			if(paramHandleResult.getiSuccess().equals("success")){
				bean = new ZWFW_ShiXiang_Detai_xzcf_bean();
				bean = paramHandleResult.getBean_xzcf();
				if(bean != null){
					initDatas();
				}
			}
		}
	};
	
	private void initDatas() {
		tv_title.setText(infofile.shixiang_name().get() != null ? infofile.shixiang_name().get() : "");
		tv_3.setText(bean.getPolicyBasis() != null && !bean.getPolicyBasis().equals("null")? bean.getPolicyBasis() : "无");
		tv_1.setText(bean.getServiceCode() != null && !bean.getServiceCode().equals("null")? bean.getServiceCode() : "无");
		tv_4.setText(bean.getXzcfType() != null && !bean.getXzcfType().equals("null")? bean.getXzcfType() : "无");
		tv_5.setText(bean.getProcessingConditions() != null && !bean.getProcessingConditions().equals("null")? bean.getProcessingConditions() : "无");
		tv_6.setText(bean.getChargeBasisAndStandard() != null && !bean.getChargeBasisAndStandard().equals("null")? bean.getChargeBasisAndStandard() : "无");
		tv_7.setText(bean.getLegalTerm() != null && !bean.getLegalTerm().equals("null")? bean.getLegalTerm() : "无");
		tv_8.setText(bean.getComplaintsHotline() != null && !bean.getComplaintsHotline().equals("null")? bean.getComplaintsHotline() : "无");
	}
	
	private void initViews() {
		tv_title = (TextView)findViewById(R.id.tv_title);
		tv_1 = (TextView)findViewById(R.id.tv_1);
		tv_3 = (TextView)findViewById(R.id.tv_3);
		tv_4 = (TextView)findViewById(R.id.tv_4);
		tv_5 = (TextView)findViewById(R.id.tv_5);
		tv_6 = (TextView)findViewById(R.id.tv_6);
		tv_7 = (TextView)findViewById(R.id.tv_7);
		tv_8 = (TextView)findViewById(R.id.tv_8);
		btn_back = (Button)findViewById(R.id.btn_back);
		
		btn_back.setOnClickListener(this);
		
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			this.finish();
			break;
		default:
			break;
		}
	}
	
	
}
