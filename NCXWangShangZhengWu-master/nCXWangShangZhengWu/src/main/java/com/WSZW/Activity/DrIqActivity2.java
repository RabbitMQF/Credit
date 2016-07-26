package com.WSZW.Activity;

import java.util.List;

import com.WSZW.adapter.ZwFw_cx_listview_adapter;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.ZwFw_CX_BizInfo;
import com.WSZW.entity.ZwFw_CX_item_bean;
import com.WSZW.service.ZWFW_CX_ManagerService;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DrIqActivity2 extends BaseActivity implements OnClickListener {
	private TextView tv1_v,tv2_v,tv3_v,tv4_v,tv5_v,tv6_v,tv7_v,tv8_v;
	private ZwFw_CX_BizInfo bean;
	private ListView lv;
	private ZwFw_cx_listview_adapter adapter;
	Button btn_back;
	InfoFile_ infoFile_;
	String str1,str2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zwfw_query_main02);
		infoFile_ = new InfoFile_(this);
		initView();
		str1 = infoFile_.edittext_1_zwfw_search().get();
		str2 = infoFile_.edittext_2_zwfw_search().get();
	//	cx_manager.getZWFW_CX_driq(context, 1,"360121100001-141111-00003" ,"362330198810185057");
		cx_manager.getZWFW_CX_driq(context, 1,str2 != null ?str2 :"",str1 != null ?str1 : "") ;

	}
	
	private ZWFW_CX_ManagerService cx_manager=new ZWFW_CX_ManagerService() {
	@Override
	protected void handlerLoginInfo(Context paramActivity, HandleResult handleResult, int paramInt) {
		if(handleResult.getiSuccess().equals("success")){

		bean=new ZwFw_CX_BizInfo();
		bean= handleResult.getBean_cx();
		if(bean.getObjectName()==null){
			Toast.makeText(DrIqActivity2.this, "暂无此事项，查询失败！", Toast.LENGTH_SHORT).show();
			DrIqActivity2.this.finish();
		}
			initDatas();
		
		}
	}
};

	private void initDatas() {
		tv1_v.setText(bean.getTrafficName()!= null && !bean.getTrafficName().toString().equals("null")? bean.getTrafficName():"-");
		tv2_v.setText(bean.getObjectName()!= null &&!bean.getObjectName().toString().equals("null")? bean.getObjectName():"-");
		tv3_v.setText(bean.getWorkName()!= null &&!bean.getWorkName().toString().equals("null")? bean.getWorkName():"-");
		tv4_v.setText(bean.getBidDateName()!= null &&!bean.getBidDateName().toString().equals("null")? bean.getBidDateName():"-");
		tv5_v.setText(bean.getInstitutionName()!= null &&!bean.getInstitutionName().toString().equals("null")? bean.getInstitutionName():"-");
		tv6_v.setText(bean.getEndDateName()!= null &&!bean.getEndDateName().toString().equals("null")? bean.getEndDateName():"-");
		tv7_v.setText(bean.getDepartmentName()!= null &&!bean.getDepartmentName().toString().equals("null")? bean.getDepartmentName():"-");
		tv8_v.setText(bean.getSiteName()!= null &&!bean.getSiteName().toString().equals("null")? bean.getSiteName():"-");
		if(bean.getList() != null ){
			adapter=new ZwFw_cx_listview_adapter(this, bean.getList());
			lv.setAdapter(adapter);
		}
	}

		private void initView() {
		
			tv1_v=(TextView) findViewById(R.id.tv1_value);
			tv2_v=(TextView) findViewById(R.id.tv2_value);
			tv3_v=(TextView)findViewById(R.id.tv3_value);
			tv4_v=(TextView) findViewById(R.id.tv4_value);
			tv5_v=(TextView)findViewById(R.id.tv5_value);
			tv6_v=(TextView) findViewById(R.id.tv6_value);
			tv7_v=(TextView)findViewById(R.id.tv7_value);
			tv8_v=(TextView) findViewById(R.id.tv8_value);
			btn_back = (Button)findViewById(R.id.btn_back);
			btn_back.setOnClickListener(this);
			lv = (ListView) findViewById(R.id.lv_query);
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
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
				this.finish();
		}
		return true;
	}
	
	
}
