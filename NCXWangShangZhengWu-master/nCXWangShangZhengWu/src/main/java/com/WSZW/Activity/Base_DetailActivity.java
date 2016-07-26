package com.WSZW.Activity;
/*
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.WSZW.data.Constants;
import com.WSZW.data.DataHelper;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.Detail_Ffjl_bean;
import com.WSZW.entity.Detail_Sjdj_bean;
import com.WSZW.entity.Detail_Wxts_bean;
import com.WSZW.entity.HandleResult;
import com.WSZW.service.BaseDetail_ManagerService;
import com.WSZW.util.StringUtil;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

*//**
 * 滚动标题栏公共类
 * 
 * @author durenchong
 * @date 2014-06-16
 *//*
@EActivity(R.layout.layout_datail_bar)
public class Base_DetailActivity extends BaseActivity {

	@Bean
	DataHelper dataHelper;
	@Pref
	InfoFile_ infoFile_;
	@ViewById
	RadioGroup radiogroup;
	@ViewById
	RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10;
	@ViewById
	FrameLayout frameL_title;
	@ViewById
	Button btn_FanHui, btn_ShouLi, btn_BuShouLi, btn_fafangjieguo,
			btn_duanxintongzhi, detail_btn_kaishi, detail_btn_buzhengshouli,
			detail_btn_llbd, btn_chuliyewu, btn_zantingyewu,
			btn_buzhengtongzhi, btn_tuihuiyewu;
	@ViewById
	RelativeLayout rl_title;
	@ViewById
	LinearLayout ll_detail_btn_1, ll_detail_btn_2, ll_detail_btn_3,
			ll_detail_btn_4, ll_detail_btn_5, ll_detail_btn_6;

	@ViewById
	TextView tv_jgjf_item_1;

	private Detail_jbxxFragment_ detail_jbxxFragment_;// 基本信息
	private Detail_wsbdFragment_ detail_wsbdFragment_;// 网上表单
	private Detail_fjckFragment_ detail_fjckFragment_;// 附件查看
	private Detail_sjdjFargment_ detail_sjdjFargment_;// 收件登记
	private Detail_ywbdFragment_ detail_ywbdFragment_;// 业务表单
	private Detail_ffjlFragment_ detail_ffjlFragment_;// 发放记录
	private Detail_dzclFragment_ detail_dzclFragment_;// 电子材料
	private Detail_bzxxFragment_ detail_bzxxFragment_;// 补正信息
	private Detail_ztxxFragment_ detail_ztxxFragment_;// 暂停信息
	private Detail_wxtsFragment_ detail_wxtsFragment_;// 温馨提示

	private FragmentTransaction oFragmentTransaction;// 碎片管理者

	Detail_Sjdj_bean detail_Sjdj_bean;
	Detail_Sjdj_bean detail_Sjdj_bean_ysl;
	Detail_Wxts_bean detail_Wxts_bean;
	Detail_Ffjl_bean detail_Ffjl_bean;
	List<Map<String, Object>> list_jbxx = new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> list_ztxx = new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> list_bzxx = new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> list_ywbd = new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> list_fjck = new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> list_wsbd = new ArrayList<Map<String, Object>>();

	private BaseDetail_ManagerService baseDetail_ManagerService = new BaseDetail_ManagerService() {

		@Override
		public void handlerBaseDetailInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
			switch (paramInt) {
			case 1:
				if (paramHandleResult.getGetBaseDetailSuccess().equals(
						"success")) {
					if (paramHandleResult.getResultGroup_tab() != null) {
						for (int i = 0; i < paramHandleResult
								.getResultGroup_tab().size(); i++) {
							if (!Constants.title.equals(context.getResources()
									.getString(R.string.yushouliyewu))) {
								initRadioGroup(paramHandleResult, i);
							}
						}
					}
					if (paramHandleResult.getResultGroup_jbxx() != null) {
						list_jbxx = paramHandleResult.getResultGroup_jbxx();
						initdata();
					}
					if (paramHandleResult.getResultGroup_sjdj_dzcl() != null) {
						detail_Sjdj_bean = new Detail_Sjdj_bean();
						detail_Sjdj_bean = paramHandleResult
								.getResultGroup_sjdj_dzcl();
						infoFile_.edit().serviceTargetPhone()
						.put(detail_Sjdj_bean.getServiceTargetPhone())
						.apply();
					}
					if (paramHandleResult.getResultGroup_ztxx() != null) {
						list_ztxx = paramHandleResult.getResultGroup_ztxx();
						int a = list_ztxx.size() - 1;
						if (list_ztxx != null && list_ztxx.size() > 0) {
							for (Map<String, Object> map : (List<Map<String, Object>>) list_ztxx
									.get(a).get("list_item_list")) {
								if (map.get("name").equals("暂停开始时间")) {
									infoFile_.edit().ZTYWkaishishijian()
											.put((String) map.get("value"))
											.apply();
								}
								if (map.get("name").equals("暂停原因")) {
									infoFile_.edit().ZTYWyuanyin()
											.put((String) map.get("value"))
											.apply();
								}
							}
						}
					}
					if (paramHandleResult.getResultGroup_bzxx() != null) {
						list_bzxx = paramHandleResult.getResultGroup_bzxx();
						int a = list_bzxx.size() - 1;
						if (list_bzxx != null && list_bzxx.size() > 0) {
							for (Map<String, Object> map : (List<Map<String, Object>>) list_bzxx
									.get(0).get("list_item_list")) {
								if (map.get("name").equals("补正申请人电话")) {
									infoFile_.edit().bz_sqr_phone()
											.put((String) map.get("value"))
											.apply();
								}
								if (map.get("name").equals("补正短信")) {
									infoFile_.edit().bz_msg()
											.put((String) map.get("value"))
											.apply();
								}
								if (map.get("name").equals("补正受理操作员")) {
									infoFile_.edit().bz_slczy()
											.put((String) map.get("value"))
											.apply();
								}
								if (map.get("name").equals("补正原因")) {
									infoFile_.edit().bz_yy()
											.put((String) map.get("value"))
											.apply();
								}
							}
						}
					}
					if (paramHandleResult.getResultGroup_wxts() != null) {
						detail_Wxts_bean = new Detail_Wxts_bean();
						detail_Wxts_bean = paramHandleResult
								.getResultGroup_wxts();
					}
					if (paramHandleResult.getResultGroup_ffjl() != null) {
						detail_Ffjl_bean = new Detail_Ffjl_bean();
						detail_Ffjl_bean = paramHandleResult
								.getResultGroup_ffjl();
					}
					if (paramHandleResult.getResultGroup_ywbd() != null) {
						list_ywbd = paramHandleResult.getResultGroup_ywbd();
					}
					if (paramHandleResult.getResultGroup_fjck() != null) {
						list_fjck = paramHandleResult.getResultGroup_fjck();
					}
				}
				break;
			case 2:
				if (paramHandleResult.getGetBaseDetailSuccess().equals(
						"success")) {
					if (paramHandleResult.getResultGroup_jbxx_ysl() != null) {
						detail_Sjdj_bean_ysl = new Detail_Sjdj_bean();
						detail_Sjdj_bean_ysl = paramHandleResult
								.getResultGroup_jbxx_ysl();
						initdata();
					}
					if (paramHandleResult.getResultGroup_fjck() != null) {
						list_fjck = paramHandleResult.getResultGroup_fjck();
					}
					if (paramHandleResult.getResultGroup_wsbd() != null) {
						list_wsbd = paramHandleResult.getResultGroup_wsbd();
					}
				}
				break;
			default:
				break;
			}
		}

	};

	public void initRadioGroup(HandleResult paramHandleResult, int i) {
		if (paramHandleResult.getResultGroup_tab().get(i).get("name")
				.equals("基本信息")) {
			if (paramHandleResult.getResultGroup_tab().get(i).get("isShow")
					.equals("true")) {
				rb1.setVisibility(View.VISIBLE);
			} else {
				rb1.setVisibility(View.GONE);
			}
		}
		if (paramHandleResult.getResultGroup_tab().get(i).get("name")
				.equals("收件登记")) {
			if (paramHandleResult.getResultGroup_tab().get(i).get("isShow")
					.equals("true")) {
				rb4.setVisibility(View.VISIBLE);
			} else {
				rb4.setVisibility(View.GONE);
			}
		}
		if (paramHandleResult.getResultGroup_tab().get(i).get("name")
				.equals("业务表单")) {
			if (paramHandleResult.getResultGroup_tab().get(i).get("isShow")
					.equals("true")) {
				rb5.setVisibility(View.VISIBLE);
			} else {
				rb5.setVisibility(View.GONE);
			}
		}
		if (paramHandleResult.getResultGroup_tab().get(i).get("name")
				.equals("附件")) {
			if (paramHandleResult.getResultGroup_tab().get(i).get("isShow")
					.equals("true")) {
				rb3.setVisibility(View.VISIBLE);
			} else {
				rb3.setVisibility(View.GONE);
			}
		}
		if (paramHandleResult.getResultGroup_tab().get(i).get("name")
				.equals("电子材料")) {
			if (paramHandleResult.getResultGroup_tab().get(i).get("isShow")
					.equals("true")) {
				rb7.setVisibility(View.VISIBLE);
			} else {
				rb7.setVisibility(View.GONE);
			}
		}
		if (paramHandleResult.getResultGroup_tab().get(i).get("name")
				.equals("暂停信息")) {
			if (paramHandleResult.getResultGroup_tab().get(i).get("isShow")
					.equals("true")) {
				rb9.setVisibility(View.VISIBLE);
			} else {
				rb9.setVisibility(View.GONE);
			}
		}
		if (paramHandleResult.getResultGroup_tab().get(i).get("name")
				.equals("补正信息")) {
			if (paramHandleResult.getResultGroup_tab().get(i).get("isShow")
					.equals("true")) {
				rb8.setVisibility(View.VISIBLE);
			} else {
				rb8.setVisibility(View.GONE);
			}
		}
		if (paramHandleResult.getResultGroup_tab().get(i).get("name")
				.equals("发放记录")) {
			if (paramHandleResult.getResultGroup_tab().get(i).get("isShow")
					.equals("true")) {
				rb6.setVisibility(View.VISIBLE);
			} else {
				rb6.setVisibility(View.GONE);
			}
		}
		if (paramHandleResult.getResultGroup_tab().get(i).get("name")
				.equals("温馨提示")) {
			if (paramHandleResult.getResultGroup_tab().get(i).get("isShow")
					.equals("true")) {
				rb10.setVisibility(View.VISIBLE);
			} else {
				rb10.setVisibility(View.GONE);
			}
		}
		if (paramHandleResult.getResultGroup_tab().get(i).get("name")
				.equals("录入表单")) {
			ll_detail_btn_2.setVisibility(View.VISIBLE);
		}
		if (paramHandleResult.getResultGroup_tab().get(i).get("name")
				.equals("处理业务")) {
			ll_detail_btn_3.setVisibility(View.VISIBLE);
			btn_chuliyewu.setVisibility(View.VISIBLE);
		}
		if (paramHandleResult.getResultGroup_tab().get(i).get("name")
				.equals("暂停业务")) {
			ll_detail_btn_3.setVisibility(View.VISIBLE);
			btn_zantingyewu.setVisibility(View.VISIBLE);
		}
		if (paramHandleResult.getResultGroup_tab().get(i).get("name")
				.equals("补正通知")) {
			ll_detail_btn_3.setVisibility(View.VISIBLE);
			btn_buzhengtongzhi.setVisibility(View.VISIBLE);
		}
		if (paramHandleResult.getResultGroup_tab().get(i).get("name")
				.equals("退回业务")) {
			ll_detail_btn_3.setVisibility(View.VISIBLE);
			btn_tuihuiyewu.setVisibility(View.VISIBLE);
		}
		if (paramHandleResult.getResultGroup_tab().get(i).get("name")
				.equals("发放结果")) {
			ll_detail_btn_4.setVisibility(View.VISIBLE);
			btn_fafangjieguo.setVisibility(View.VISIBLE);
		}
		if (paramHandleResult.getResultGroup_tab().get(i).get("name")
				.equals("短信通知")) {
			ll_detail_btn_4.setVisibility(View.VISIBLE);
			btn_duanxintongzhi.setVisibility(View.VISIBLE);
		}
		if (paramHandleResult.getResultGroup_tab().get(i).get("name")
				.equals("开始业务")) {
			ll_detail_btn_5.setVisibility(View.VISIBLE);
			detail_btn_kaishi.setVisibility(View.VISIBLE);
		}
		if (paramHandleResult.getResultGroup_tab().get(i).get("name")
				.equals("补正受理")) {
			ll_detail_btn_6.setVisibility(View.VISIBLE);
			detail_btn_buzhengshouli.setVisibility(View.VISIBLE);
		}
	}

	@AfterViews
	void initView() {
		initTitle();
		rb1.setChecked(true);
		rb1.setTextColor(this.getResources().getColor(R.color.text_blue));
		radiogroup.setOnCheckedChangeListener(onCheckedChangeListener);
		btn_FanHui.setOnClickListener(onClickListener);
		btn_ShouLi.setOnClickListener(onClickListener);
		btn_BuShouLi.setOnClickListener(onClickListener);
		btn_fafangjieguo.setOnClickListener(onClickListener);
		btn_duanxintongzhi.setOnClickListener(onClickListener);
		detail_btn_kaishi.setOnClickListener(onClickListener);
		detail_btn_buzhengshouli.setOnClickListener(onClickListener);
		detail_btn_llbd.setOnClickListener(onClickListener);
		btn_chuliyewu.setOnClickListener(onClickListener);
		btn_zantingyewu.setOnClickListener(onClickListener);
		btn_buzhengtongzhi.setOnClickListener(onClickListener);
		btn_tuihuiyewu.setOnClickListener(onClickListener);
	}

	// 当第一次进入的时候默认开启的是基本信息
	@SuppressLint("ResourceAsColor")
	public void initdata() {
		if (Constants.title.equals(Base_DetailActivity.this.getResources()
				.getString(R.string.yushouliyewu))) {
			initDataYsl();
		} else {
			oFragmentTransaction = getSupportFragmentManager()
					.beginTransaction();// 碎片管理者开始处理
			if (detail_jbxxFragment_ == null) {
				detail_jbxxFragment_ = new Detail_jbxxFragment_();
				Bundle bundle = new Bundle();
				bundle.putSerializable("list", (Serializable) list_jbxx);
				detail_jbxxFragment_.setArguments(bundle);
			}
			oFragmentTransaction.replace(R.id.frameL_title,
					detail_jbxxFragment_);
			oFragmentTransaction.commit();
		}

	}

	private void initDataYsl() {
		oFragmentTransaction = getSupportFragmentManager().beginTransaction();// 碎片管理者开始处理
		if (detail_sjdjFargment_ == null) {
			detail_sjdjFargment_ = new Detail_sjdjFargment_();
			Bundle bundle = new Bundle();
			bundle.putSerializable("Detail_Sjdj_bean", detail_Sjdj_bean_ysl);
			detail_sjdjFargment_.setArguments(bundle);
		}
		oFragmentTransaction.replace(R.id.frameL_title, detail_sjdjFargment_);
		oFragmentTransaction.commit();

	}

	public OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {

		@SuppressLint("ResourceAsColor")
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			oFragmentTransaction = getSupportFragmentManager()
					.beginTransaction();
			initRadioButtonTextColor();
			hideFragment(oFragmentTransaction);
			switch (checkedId) {
			case R.id.rb1:
				rb1.setTextColor(Base_DetailActivity.this.getResources()
						.getColor(R.color.text_blue));
				if (Constants.title.equals(Base_DetailActivity.this
						.getResources().getString(R.string.yushouliyewu))) {
					if (detail_sjdjFargment_ == null) {
						detail_sjdjFargment_ = new Detail_sjdjFargment_();
						Bundle bundle = new Bundle();
						bundle.putSerializable("Detail_Sjdj_bean",
								detail_Sjdj_bean_ysl);
						detail_sjdjFargment_.setArguments(bundle);
						oFragmentTransaction.add(R.id.frameL_title,
								detail_sjdjFargment_);
					}
					oFragmentTransaction.show(detail_sjdjFargment_).commit();

					// oFragmentTransaction.replace(R.id.frameL_title,
					// detail_sjdjFargment_);
					// oFragmentTransaction.commit();
				} else {
					if (detail_jbxxFragment_ == null) {
						detail_jbxxFragment_ = new Detail_jbxxFragment_();
						Bundle bundle = new Bundle();
						bundle.putSerializable("list", (Serializable) list_jbxx);
						detail_jbxxFragment_.setArguments(bundle);
						oFragmentTransaction.add(R.id.frameL_title,
								detail_jbxxFragment_);
					}
					oFragmentTransaction.show(detail_jbxxFragment_).commit();
					// oFragmentTransaction.replace(R.id.frameL_title,
					// detail_jbxxFragment_);
					// oFragmentTransaction.commit();
				}

				break;
			case R.id.rb2:
				rb2.setTextColor(Base_DetailActivity.this.getResources()
						.getColor(R.color.text_blue));
				if (detail_wsbdFragment_ == null) {
					detail_wsbdFragment_ = new Detail_wsbdFragment_();
					Bundle bundle = new Bundle();
					bundle.putSerializable("list", (Serializable) list_wsbd);
					detail_wsbdFragment_.setArguments(bundle);
					oFragmentTransaction.add(R.id.frameL_title,
							detail_wsbdFragment_);
				}
				oFragmentTransaction.show(detail_wsbdFragment_).commit();
				// oFragmentTransaction.replace(R.id.frameL_title,
				// detail_wsbdFragment_);
				// oFragmentTransaction.commit();
				break;
			case R.id.rb3:
				rb3.setTextColor(Base_DetailActivity.this.getResources()
						.getColor(R.color.text_blue));
				if (detail_fjckFragment_ == null) {
					detail_fjckFragment_ = new Detail_fjckFragment_();
					Bundle bundle = new Bundle();
					bundle.putSerializable("list", (Serializable) list_fjck);
					detail_fjckFragment_.setArguments(bundle);
					oFragmentTransaction.add(R.id.frameL_title,
							detail_fjckFragment_);
				}
				oFragmentTransaction.show(detail_fjckFragment_).commit();
				// oFragmentTransaction.replace(R.id.frameL_title,
				// detail_fjckFragment_);
				// oFragmentTransaction.commit();
				break;
			case R.id.rb4:
				rb4.setTextColor(Base_DetailActivity.this.getResources()
						.getColor(R.color.text_blue));
				if (detail_sjdjFargment_ == null) {
					detail_sjdjFargment_ = new Detail_sjdjFargment_();
					Bundle bundle = new Bundle();
					bundle.putSerializable("Detail_Sjdj_bean", detail_Sjdj_bean);
					detail_sjdjFargment_.setArguments(bundle);
					oFragmentTransaction.add(R.id.frameL_title,
							detail_sjdjFargment_);
				}
				oFragmentTransaction.show(detail_sjdjFargment_).commit();
				// oFragmentTransaction.replace(R.id.frameL_title,
				// detail_sjdjFargment_);
				// oFragmentTransaction.commit();
				break;
			case R.id.rb5:
				rb5.setTextColor(Base_DetailActivity.this.getResources()
						.getColor(R.color.text_blue));
				if (detail_ywbdFragment_ == null) {
					detail_ywbdFragment_ = new Detail_ywbdFragment_();
					Bundle bundle = new Bundle();
					bundle.putSerializable("list", (Serializable) list_ywbd);
					detail_ywbdFragment_.setArguments(bundle);
					oFragmentTransaction.add(R.id.frameL_title,
							detail_ywbdFragment_);
				}
				oFragmentTransaction.show(detail_ywbdFragment_).commit();
				// oFragmentTransaction.replace(R.id.frameL_title,
				// detail_ywbdFragment_);
				// oFragmentTransaction.commit();
				break;
			case R.id.rb6:
				rb6.setTextColor(Base_DetailActivity.this.getResources()
						.getColor(R.color.text_blue));
				if (detail_ffjlFragment_ == null) {
					detail_ffjlFragment_ = new Detail_ffjlFragment_();
					Bundle bundle = new Bundle();
					bundle.putSerializable("Detail_Ffjl_bean", detail_Ffjl_bean);
					detail_ffjlFragment_.setArguments(bundle);
					oFragmentTransaction.add(R.id.frameL_title,
							detail_ffjlFragment_);
				}
				oFragmentTransaction.show(detail_ffjlFragment_).commit();
				// oFragmentTransaction.replace(R.id.frameL_title,
				// detail_ffjlFragment_);
				// oFragmentTransaction.commit();
				break;
			case R.id.rb7:
				rb7.setTextColor(Base_DetailActivity.this.getResources()
						.getColor(R.color.text_blue));
				if (detail_dzclFragment_ == null) {
					detail_dzclFragment_ = new Detail_dzclFragment_();
					Bundle bundle = new Bundle();
					bundle.putSerializable("Detail_Sjdj_bean", detail_Sjdj_bean);
					detail_dzclFragment_.setArguments(bundle);
					oFragmentTransaction.add(R.id.frameL_title,
							detail_dzclFragment_);
				}
				oFragmentTransaction.show(detail_dzclFragment_).commit();
				// oFragmentTransaction.replace(R.id.frameL_title,
				// detail_dzclFragment_);
				// oFragmentTransaction.commit();
				break;
			case R.id.rb8:
				rb8.setTextColor(Base_DetailActivity.this.getResources()
						.getColor(R.color.text_blue));
				if (detail_bzxxFragment_ == null) {
					detail_bzxxFragment_ = new Detail_bzxxFragment_();
					Bundle bundle = new Bundle();
					bundle.putSerializable("list", (Serializable) list_bzxx);
					detail_bzxxFragment_.setArguments(bundle);
					oFragmentTransaction.add(R.id.frameL_title,
							detail_bzxxFragment_);
				}
				oFragmentTransaction.show(detail_bzxxFragment_).commit();
				// oFragmentTransaction.replace(R.id.frameL_title,
				// detail_bzxxFragment_);
				// oFragmentTransaction.commit();
				break;
			case R.id.rb9:
				rb9.setTextColor(Base_DetailActivity.this.getResources()
						.getColor(R.color.text_blue));
				if (detail_ztxxFragment_ == null) {
					detail_ztxxFragment_ = new Detail_ztxxFragment_();
					Bundle bundle = new Bundle();
					bundle.putSerializable("list", (Serializable) list_ztxx);
					detail_ztxxFragment_.setArguments(bundle);
					oFragmentTransaction.add(R.id.frameL_title,
							detail_ztxxFragment_);
				}
				oFragmentTransaction.show(detail_ztxxFragment_).commit();
				// oFragmentTransaction.replace(R.id.frameL_title,
				// detail_ztxxFragment_);
				// oFragmentTransaction.commit();
				break;

			case R.id.rb10:
				rb10.setTextColor(Base_DetailActivity.this.getResources()
						.getColor(R.color.text_blue));
				if (detail_wxtsFragment_ == null) {
					detail_wxtsFragment_ = new Detail_wxtsFragment_();
					Bundle bundle = new Bundle();
					bundle.putSerializable("Detail_Wxts_bean", detail_Wxts_bean);
					detail_wxtsFragment_.setArguments(bundle);
					oFragmentTransaction.add(R.id.frameL_title,
							detail_wxtsFragment_);
				}
				oFragmentTransaction.show(detail_wxtsFragment_).commit();
				// oFragmentTransaction.replace(R.id.frameL_title,
				// detail_wxtsFragment_);
				// oFragmentTransaction.commit();
				break;
			default:
				break;
			}

		}
	};

	private OnClickListener onClickListener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_FanHui:
				Base_DetailActivity.this.finish();
				break;
			case R.id.btn_ShouLi:
				Constants.fromYsl = true;
				Intent intent_shouli = new Intent();
				intent_shouli.setClass(Base_DetailActivity.this,
						Sjsl_Sjdj_shouli_Activity_.class);
				Base_DetailActivity.this.startActivity(intent_shouli);
				Base_DetailActivity.this.finish();
				break;
			case R.id.btn_BuShouLi:
				Intent intent_ = new Intent();
				intent_.setClass(Base_DetailActivity.this,
						Wssq_Yslyw_Deny_Activity_.class);
				Base_DetailActivity.this.startActivity(intent_);
				Base_DetailActivity.this.finish();
				break;
			case R.id.btn_fafangjieguo:
				Intent intent_ffjg = new Intent();
				intent_ffjg.setClass(Base_DetailActivity.this,
						Jgff_Dfflb_ff_Activity_.class);
				Base_DetailActivity.this.startActivity(intent_ffjg);
				Base_DetailActivity.this.finish();
				break;
			case R.id.btn_duanxintongzhi:
				Intent intent_dxtz = new Intent();
				intent_dxtz.setClass(Base_DetailActivity.this,
						Jgff_Dfflb_ff_dxtz_Activity_.class);
				Base_DetailActivity.this.startActivity(intent_dxtz);
				Base_DetailActivity.this.finish();
				break;
			case R.id.detail_btn_kaishi:
				Intent intent = new Intent();
				intent.setClass(Base_DetailActivity.this,
						Spcl_Dsp_ksyw_Activity_.class);
				Base_DetailActivity.this.startActivity(intent);
				Base_DetailActivity.this.finish();
				break;
			case R.id.detail_btn_buzhengshouli:
				Intent intent_1 = new Intent();
				intent_1.setClass(Base_DetailActivity.this,
						Spcl_Dsp_bzsl_Activity_.class);
				Base_DetailActivity.this.startActivity(intent_1);
				Base_DetailActivity.this.finish();
				break;
			case R.id.detail_btn_llbd:
				Intent intent_2 = new Intent();
				intent_2.setClass(Base_DetailActivity.this,
						Bdlr_lrbd_luru_Activity_.class);
				Base_DetailActivity.this.startActivity(intent_2);
				Base_DetailActivity.this.finish();
				break;
			case R.id.btn_chuliyewu:
				Intent intent_3 = new Intent();
				intent_3.setClass(Base_DetailActivity.this,
						Spcl_Dsp_sp_Activity_.class);
				Base_DetailActivity.this.startActivity(intent_3);
				Base_DetailActivity.this.finish();
				break;
			case R.id.btn_zantingyewu:
				Intent intent_4 = new Intent();
				intent_4.setClass(Base_DetailActivity.this,
						Spcl_Dsp_ztyw_Activity_.class);
				Base_DetailActivity.this.startActivity(intent_4);
				Base_DetailActivity.this.finish();
				break;
			case R.id.btn_buzhengtongzhi:
				Intent intent_5 = new Intent();
				intent_5.setClass(Base_DetailActivity.this,
						Spcl_Dsp_bztz_Activity_.class);
				Base_DetailActivity.this.startActivity(intent_5);
				Base_DetailActivity.this.finish();
				break;
			case R.id.btn_tuihuiyewu:
				Intent intent_6 = new Intent();
				intent_6.setClass(Base_DetailActivity.this,
						Spcl_Dsp_thyw_Activity_.class);
				Base_DetailActivity.this.startActivity(intent_6);
				Base_DetailActivity.this.finish();
				break;
			default:
				break;
			}
		}
	};

	public void initRadioButtonTextColor() {
		rb1.setTextColor(getResources().getColor(R.color.black));
		rb2.setTextColor(getResources().getColor(R.color.black));
		rb3.setTextColor(getResources().getColor(R.color.black));
		rb4.setTextColor(getResources().getColor(R.color.black));
		rb5.setTextColor(getResources().getColor(R.color.black));
		rb6.setTextColor(getResources().getColor(R.color.black));
		rb7.setTextColor(getResources().getColor(R.color.black));
		rb8.setTextColor(getResources().getColor(R.color.black));
		rb9.setTextColor(getResources().getColor(R.color.black));
		rb10.setTextColor(getResources().getColor(R.color.black));
	}

	public void initTitle() {
		rb2.setVisibility(View.GONE);
		if (Constants.title.equals(context.getResources().getString(
				R.string.yushouliyewu))) {
			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_yuslyw));
			rb1.setVisibility(View.VISIBLE);
			rb2.setVisibility(View.VISIBLE);
			rb3.setVisibility(View.VISIBLE);
			rb4.setVisibility(View.GONE);
			rb5.setVisibility(View.GONE);
			rb6.setVisibility(View.GONE);
			rb7.setVisibility(View.GONE);
			rb8.setVisibility(View.GONE);
			rb9.setVisibility(View.GONE);
			rb10.setVisibility(View.GONE);
			ll_detail_btn_1.setVisibility(View.VISIBLE);
		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.shoujiandengji))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_sjdj));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yishouliyewu))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_yslyw));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.buyushouliyewu))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_byslyw));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.dailurubiaodan))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_dllbd));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yilurubiaodan))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_yllbd));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.daishenpi))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_dsp));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yishenpi))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_ysp));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yituihui))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_yth));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.daifafangliebiao))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_dfflb));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yifafangliebiao))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_yfflb));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.daoqiweibanjieliebiao))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_dqwbjlb));

		}
		if (Constants.title.equals(context.getResources().getString(
				R.string.yewudanganchaxun))) {

			rl_title.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.tt_ywdacx));

		}
	}

	private void hideFragment(FragmentTransaction fragmentTransaction) {
		if (detail_jbxxFragment_ != null) {
			fragmentTransaction.hide(detail_jbxxFragment_);
		}
		if (detail_wsbdFragment_ != null) {
			fragmentTransaction.hide(detail_wsbdFragment_);
		}
		if (detail_fjckFragment_ != null) {
			fragmentTransaction.hide(detail_fjckFragment_);
		}
		if (detail_sjdjFargment_ != null) {
			fragmentTransaction.hide(detail_sjdjFargment_);
		}
		if (detail_ywbdFragment_ != null) {
			fragmentTransaction.hide(detail_ywbdFragment_);
		}
		if (detail_ffjlFragment_ != null) {
			fragmentTransaction.hide(detail_ffjlFragment_);
		}
		if (detail_dzclFragment_ != null) {
			fragmentTransaction.hide(detail_dzclFragment_);
		}
		if (detail_bzxxFragment_ != null) {
			fragmentTransaction.hide(detail_bzxxFragment_);
		}
		if (detail_ztxxFragment_ != null) {
			fragmentTransaction.hide(detail_ztxxFragment_);
		}
		if (detail_wxtsFragment_ != null) {
			fragmentTransaction.hide(detail_wxtsFragment_);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> oMap = new HashMap<String, Object>();
		oMap.put("account", Constants.account);
		String id = infoFile_.bizArchivesId().get();
		oMap.put("bizArchivesId", id);
		oMap.put("serialNumber", infoFile_.baselist_yewuliushuihao().get());
		oMap.put("serviceSubjectName",
				StringUtil.replaceSpace(infoFile_.serviceSubjectName().get()));
		if (Constants.title.equals(context.getResources().getString(
				R.string.yushouliyewu))
				&& infoFile_.yslyw_shengqingrenCardId().get() != null) {
			oMap.put("serviceTargetId", infoFile_.yslyw_shengqingrenCardId()
					.get());
			list.add(oMap);
			baseDetail_ManagerService.getShowDetails(this, 2, list);
		} else {
			list.add(oMap);

			baseDetail_ManagerService.getShowDetails(this, 1, list);
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO 自动生成的方法存根
		// 注释掉下面的代码,作用：
		// （如果）页面被系统销毁时，不再保存状态，直接重新创建了activity，使fragment不重叠
		// super.onSaveInstanceState(outState);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// exitBy2Click();
			Intent intent = new Intent();
			intent.setClass(this, Base_ListActivity_.class);
			startActivity(intent);
			finish();
		}
		return false;
	}

}*/
