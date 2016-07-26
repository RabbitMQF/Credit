package com.WSZW.Activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.WSZW.adapter.Sjdj_deny_bltj_tjcl_ListAdapter;
import com.WSZW.entity.Sjdj_Deny_Msg_bean;
import com.WSZW.util.CommUtil;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.layout_sjsl_sjdj_deny)
public class Sjsl_Sjdj_Deny_Activity extends BaseActivity {
	@ViewById
	RelativeLayout rl_title;
	@ViewById
	TextView tv_name, tvInfo, tvTitle_two_Name;
	@ViewById
	ListView lv_bltj_fuhe, lv_tjcl_fuhe;
	@ViewById
	Button btn_sjdj_next, btn_cancel;
	Sjdj_Deny_Msg_bean deny = new Sjdj_Deny_Msg_bean();
	String str;

	@AfterViews
	void initView() {
		rl_title.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.top));//收件登记Title改网上政务
		tvTitle_two_Name.setText(deny.getName_matter() + "[不予受理]");
		tv_name.setText("尊敬的" + deny.getUsername() + ",");
		tvInfo.setText("您于  " + str + "  申办的    [" + deny.getName_matter()
				+ "]  未能办理，原因如下，特此告知。");
		btn_cancel.setOnClickListener(onClickListener);
		btn_sjdj_next.setOnClickListener(onClickListener);
		lv_bltj_fuhe.setAdapter(new Sjdj_deny_bltj_tjcl_ListAdapter(
				Sjsl_Sjdj_Deny_Activity.this, deny.getList_bltj()));
		lv_tjcl_fuhe.setAdapter(new Sjdj_deny_bltj_tjcl_ListAdapter(
				Sjsl_Sjdj_Deny_Activity.this, deny.getList_tjcl()));
		CommUtil.setListViewHeightBasedOnChildren(lv_bltj_fuhe);
		CommUtil.setListViewHeightBasedOnChildren(lv_tjcl_fuhe);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		str = formatter.format(curDate);
		deny = (Sjdj_Deny_Msg_bean) getIntent().getExtras().getSerializable(
				"value");

	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_sjdj_next:
				// startActivity(new
				// Intent(Sjsl_Sjdj_Deny_Activity.this,Sjsl_Sjdj_shouli_Activity_.class));
				Sjsl_Sjdj_Deny_Activity.this.finish();
				break;
			case R.id.btn_cancel:
				Sjsl_Sjdj_Deny_Activity.this.finish();
				break;
			default:
				break;
			}

		}
	};

}
