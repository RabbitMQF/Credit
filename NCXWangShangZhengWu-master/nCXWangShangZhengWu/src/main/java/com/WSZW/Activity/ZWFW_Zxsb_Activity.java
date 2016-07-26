package com.WSZW.Activity;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.WSZW.adapter.Sjdj_shouli_bltj_ListAdapter;
import com.WSZW.adapter.Sjdj_shouli_tjcl_ListAdapter;
import com.WSZW.adapter.Sjdj_shouli_tjcl_check_dialog_ListAdapter;
import com.WSZW.data.Constants;
import com.WSZW.data.DataHelper;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.Sjdj_Deny_Msg_bean;
import com.WSZW.entity.Sjdj_matter_bean;
import com.WSZW.entity.Sjdj_shouli_detail_bean;
import com.WSZW.entity.ZWFW_zxsb_detail_bean;
import com.WSZW.service.BaseDetail_ManagerService;
import com.WSZW.service.Zwfw_zxsbManagerService;
import com.WSZW.util.CommUtil;
import com.WSZW.util.StringUtil;
import com.WSZW.view.MyPopupWindow;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 在线申办界面，包括一级列表界面和二级列表界面
 * 
 * @author Administrator
 * 
 */

@NoTitle
@EActivity(R.layout.layout_zxsb_shouli)
public class ZWFW_Zxsb_Activity extends BaseActivity {
	@Bean
	DataHelper dataHelper;

	@Pref
	InfoFile_ infoFile_;
	String IfAcceptQTSL = "false"; // 收件登记的事项是否为前台受理

	@ViewById
	LinearLayout ll_detail_jbxx_shff, ll_sjdj_csdd_first, ll_sjdj_csdd_second,
			ll_sjdj_ffdd_first, ll_sjdj_ffdd_second;
	@ViewById
	RelativeLayout rl_title;
	// 以下布局分别为：提取材料模块里面的材料列表布局，提取材料里面的选择条件布局,联系方式布局
	@ViewById
	LinearLayout ll_sjdj_tjcl_all, ll_sjdj_tjcl_checkbox, sjsl_lxfs;
	@ViewById
	ListView lv_sjdj_shouli_bltj, lv_sjdj_shouli_tjcl; // 显示办理条件列表的listview和显示提交材料的listview,提交材料弹出dialog里面的listview
	// 以下控件分别为：受理地点textview，初审地点第一栏textview，初审地点第二栏textview，发放地点第一栏textview，发放地点第二栏textview，
	@ViewById
	TextView tvTitle_two_Name, tv_detail_jbxx_shoulididian, tv_sjdj_csdd_first,
			tv_sjdj_csdd_second, tv_sjdj_ffdd_first, tv_sjdj_ffdd_second;
	@ViewById
	Button btn_check_tiaojian, btn_list_1, btn_list_2, btn_check_all;
	@ViewById
	EditText edt_search_1, edt_search_2, edt_search_3, edt_search_4;

	@ViewById
	CheckBox cb_login;
	
	List<String> list_chushen_address_first; // 用来存放初审地点第一栏的地点列表
	List<String> list_chushen_address_second; // 用来存放初审地点第二栏的地点列表
	List<String> list_chushen_address_second_id; // 用来存放初审地点第二栏的id列表
	List<String> list_fafang_address_first; // 用来存放发放地点第一栏的地点列表
	List<String> list_fafang_address_second; // 用来存放发放地点第二栏的地点列表
	List<String> list_fafang_address_second_id; // 用来存放发放地点第二栏的id列表
	List<Map<String, Object>> list_bltj; // 跳转入不予受理界面后办理条件是否符合列表
	List<Map<String, Object>> list_tjcl; // 跳转入不予受理界面后办理材料是否符合列表
	Sjdj_matter_bean matter = new Sjdj_matter_bean(); // 收件登记事项实体类
	Sjdj_Deny_Msg_bean deny_msg = new Sjdj_Deny_Msg_bean();// ????
	ZWFW_zxsb_detail_bean detail_bltj_tjcl = new ZWFW_zxsb_detail_bean();// 收件登记中办理条件和提交材料的实体类
	ListView lv_sjdj_shouli_tjcl_checkbox;
	AlertDialog dialog;

	String str_resultCode = null;// 收件登记选择框

	boolean all_checked = false;// 是否全选

	boolean goto_luru = false;// 区别点击的按钮是第二个还是第三个

	boolean have_luru = true;// 点击第三个按钮后，区别该受理的事件是否需要录入表单

	boolean act_fafang = false;// 收件登记提交后是否调直接发放结果接口

	List<Map<String, Object>> list_tjcl_for_save_uploaded_pictures = null;

	/**
	 * 跳转到表单录入需要传递的参数
	 */
	public String name; // 姓名
	public String id_num; // 身份证号
	public String telephone_num; // 手机号码
	public String email; // 电子邮件
	public String organizationId;//办事窗口ID
	public String isSendMsg;
	public StringBuffer resultCode;
	
	@AfterViews
	void initView() {
		/*
		 * rl_title.setBackgroundDrawable(getResources().getDrawable(
		 * R.drawable.tt_sjdj));
		 */
		ll_sjdj_csdd_first.setOnClickListener(onClickListener);
		ll_sjdj_csdd_second.setOnClickListener(onClickListener);
		ll_sjdj_ffdd_first.setOnClickListener(onClickListener);
		ll_sjdj_ffdd_second.setOnClickListener(onClickListener);
		btn_check_tiaojian.setOnClickListener(onClickListener);
		btn_check_all.setOnClickListener(onClickListener);
		btn_list_1.setOnClickListener(onClickListener);
		btn_list_2.setOnClickListener(onClickListener);
		// btn_list_3.setOnClickListener(onClickListener);
		tvTitle_two_Name.setText(matter.matter_name);
		if (Constants.fromYsl) {
			edt_search_1.setText(infoFile_.shengqingrenname().get());
			edt_search_2.setText(infoFile_.shengqingrenCardId().get());
		} else {
			edt_search_1.setHint("请输入姓名");
			edt_search_2.setHint("请输入身份证号");
		}
		Constants.list_tjcl_for_save_uploaded_pictures = null;
		// sjsl_lxfs.setVisibility(View.GONE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.layout_zxsb_shouli);
		matter.matter_id = infoFile_.shixiang_id().get();
		matter.matter_name = infoFile_.shixiang_name().get();
		deny_msg.setName_matter(matter.matter_name);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("account", Constants.account);
		map.put("serviceSubjectId", infoFile_.shixiang_id().get());
		list.add(map);
		/*
		 * mZwfw_zxsbManagerService.getAcceptInfo2(ZWFW_Zxsb_Activity.this, 1,
		 * list);
		 */
		mZwfw_zxsbManagerService.getSjdj_bltj_tjcl(ZWFW_Zxsb_Activity.this, 4,
				list);

	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ib_sjdj_shouli_tjcl_dzcl:
				Map<String, Object> maps = (Map<String, Object>) v.getTag();
				infoFile_.edit().postinfoname().put((String) maps.get("name"))
						.apply();// 提交材料名
				infoFile_.edit().postinfoid().put((String) maps.get("id"))
						.apply();// 提交材料id
				infoFile_.edit().postinfotype()
						.put((String) maps.get("identityType")).apply();// 提交材料类型
				// 弹出对话框，选择拍照或者从文件中取
				// popRelationshipSelect();
				String result = StringUtil.IDCardValidate(edt_search_2
						.getText().toString());
				if (edt_search_1.getText().toString().length() < 1
						|| edt_search_1.getText().toString().equals("")
						|| edt_search_2.getText().toString().length() < 1
						|| edt_search_2.getText().toString().equals("")) {
					showToastShort("申请人姓名及申请人身份证号不能为空");
				} else if (result.trim().length() < 1 || result.equals("")) {
					infoFile_.edit().shengqingrenname()
							.put(edt_search_1.getText().toString()).apply();
					infoFile_.edit().shengqingrenCardId()
							.put(edt_search_2.getText().toString()).apply();
					Intent intent = new Intent(context,
							PritureShowActivity_.class);
					intent.putExtra("postinfoname", infoFile_.postinfoname()
							.get());
					intent.putExtra("index", (Integer) maps.get("index"));
					startActivity(intent);
				} else {
					showToastShort(result);
				}

				break;
			case R.id.ll_sjdj_csdd_first:
				if (list_chushen_address_first != null) {
					showPopuWindow(tv_sjdj_csdd_first, ll_sjdj_csdd_first,
							list_chushen_address_first);
				}
				break;
			case R.id.ll_sjdj_csdd_second:
				if (list_chushen_address_second != null) {
					showPopuWindow(tv_sjdj_csdd_second, ll_sjdj_csdd_second,
							list_chushen_address_second);
				} else {
					list_chushen_address_second = new ArrayList<String>();
					list_chushen_address_second.add("请选择受理地点");
					showPopuWindow(tv_sjdj_csdd_second, ll_sjdj_csdd_second,
							list_chushen_address_second);
				}

				break;
			/*
			 * case R.id.ll_sjdj_ffdd_first: if(list_fafang_address_first !=
			 * null){ showPopuWindow(tv_sjdj_ffdd_first, ll_sjdj_ffdd_first,
			 * list_fafang_address_first); } break; case
			 * R.id.ll_sjdj_ffdd_second: if (list_fafang_address_second != null)
			 * { showPopuWindow(tv_sjdj_ffdd_second, ll_sjdj_ffdd_second,
			 * list_fafang_address_second); } else { list_fafang_address_second
			 * = new ArrayList<String>();
			 * list_fafang_address_second.add("请选择发放地点");
			 * showPopuWindow(tv_sjdj_ffdd_second, ll_sjdj_ffdd_second,
			 * list_fafang_address_second); } break;
			 */
			case R.id.btn_check_tiaojian:
				Constants.list_middle = new ArrayList<Map<String, Object>>();
				Constants.list_tjcl = new ArrayList<Map<String, Object>>();
				showTJCL_Dialog();
				break;
			case R.id.btn_list_1:
				finish();
				break;
			case R.id.btn_list_2:
				if(checkIfSuitableToApply()){
					name = edt_search_1.getText().toString();
					id_num = edt_search_2.getText().toString();
					telephone_num = edt_search_3.getText().toString();
					email = edt_search_4.getText().toString();
					organizationId = matter.getCode_csdd();
					infoFile_.edit().shengqingrenname().put(name).apply();
					infoFile_.edit().shengqingrenCardId().put(id_num).apply();
					startActivity(new Intent(context, ZWFW_ZXSB_bdlr_Activity_.class).putExtra("name", name).putExtra("id_num", id_num)
							.putExtra("telephone_num",telephone_num).putExtra("email", email).putExtra("organizationId", organizationId)
							.putExtra("isSendMsg", cb_login.isChecked()+"").putExtra("resultCode", resultCode+""));
					finish();
				}
				break;
				/*
				 * if (checkIfSuitableToApply()) {
				 * deny_msg.setUsername(edt_search_1.getText().toString());
				 * boolean bltj_all_checked = checkBltj(); boolean
				 * tjcl_all_checked = checkTjcl();
				 * infoFile_.edit().shengqingrenname()
				 * .put(edt_search_1.getText().toString()).apply();
				 * infoFile_.edit().shengqingrenCardId()
				 * .put(edt_search_2.getText().toString()).apply(); if
				 * (bltj_all_checked && tjcl_all_checked) { List<Map<String,
				 * Object>> paramlist = new ArrayList<Map<String, Object>>();
				 * Map<String, Object> map = new HashMap<String, Object>(); if
				 * (Constants.fromYsl) { map.put("bizArchivesId",
				 * infoFile_.bizArchivesId() .get()); } else {
				 * map.put("bizArchivesId", ""); } map.put("serviceSubjectId",
				 * matter.getMatter_id()); map.put("accountId",
				 * infoFile_.infoUserId().get()); map.put("userName",
				 * matter.getName()); map.put("identityNumber",
				 * matter.getId_num()); map.put("handleLocation",
				 * matter.getCode_csdd()); map.put("handleLocationName",
				 * matter.getName_csdd()); map.put("grantLocation",
				 * matter.getCode_ffdd()); map.put("grantLocationName",
				 * matter.getName_ffdd()); if (str_resultCode != null) {
				 * map.put("resultCode", str_resultCode); } else {
				 * map.put("resultCode", ""); } map.put("serviceTargetPhone",
				 * edt_search_3.getText().toString());
				 * map.put("serviceTargetEmail",
				 * edt_search_4.getText().toString()); paramlist.add(map);
				 * goto_luru = false;
				 * mZwfw_zxsbManagerService.getSJDJ_Submit(context, 5,
				 * paramlist); } else { if (lv_sjdj_shouli_tjcl.getAdapter() !=
				 * null) { Intent intent1 = new Intent(); Bundle bundle = new
				 * Bundle(); bundle.putSerializable("value", deny_msg);
				 * intent1.putExtras(bundle);
				 * intent1.setClass(ZWFW_Zxsb_Activity.this,
				 * Sjsl_Sjdj_Deny_Activity_.class); startActivity(intent1); }
				 * else { Toast.makeText(ZWFW_Zxsb_Activity.this, "请选择提交材料！",
				 * Toast.LENGTH_LONG).show(); }
				 * 
				 * }
				 * 
				 * } break;
				 */
				/*
				 * case R.id.btn_list_3: if (checkIfSuitableToApply()) { boolean
				 * bltj_all_checked = checkBltj(); boolean tjcl_all_checked =
				 * checkTjcl(); if (bltj_all_checked && tjcl_all_checked) {
				 * List<Map<String, Object>> paramlist = new
				 * ArrayList<Map<String, Object>>(); Map<String, Object> map =
				 * new HashMap<String, Object>(); if (Constants.fromYsl) {
				 * map.put("bizArchivesId", infoFile_.bizArchivesId() .get()); }
				 * else { map.put("bizArchivesId", ""); }
				 * map.put("serviceSubjectId", matter.getMatter_id());
				 * map.put("accountId", infoFile_.infoUserId().get());
				 * map.put("userName", matter.getName());
				 * map.put("identityNumber", matter.getId_num());
				 * map.put("handleLocation", matter.getCode_csdd());
				 * map.put("handleLocationName", matter.getName_csdd());
				 * map.put("grantLocation", matter.getCode_ffdd());
				 * map.put("grantLocationName", matter.getName_ffdd()); if
				 * (str_resultCode != null) { map.put("resultCode",
				 * str_resultCode); } else { map.put("resultCode", ""); }
				 * if(!edt_search_3.getText().toString().equals("")){
				 * map.put("serviceTargetPhone",
				 * edt_search_3.getText().toString()); }
				 * if(!edt_search_4.getText().toString().equals("")){
				 * map.put("serviceTargetEmail",
				 * edt_search_4.getText().toString()); } paramlist.add(map);
				 * goto_luru = true;
				 * mSjdj_ShouLiManagerService.getSJDJ_Submit(context, 5,
				 * paramlist);
				 * 
				 * } else { if (lv_sjdj_shouli_tjcl.getAdapter() != null) {
				 * Intent intent1 = new Intent(); Bundle bundle = new Bundle();
				 * bundle.putSerializable("value", deny_msg);
				 * intent1.putExtras(bundle);
				 * intent1.setClass(Sjsl_Sjdj_shouli_Activity.this,
				 * Sjsl_Sjdj_Deny_Activity_.class); startActivity(intent1); }
				 * else { Toast.makeText(Sjsl_Sjdj_shouli_Activity.this,
				 * "请选择提交材料！", Toast.LENGTH_LONG).show(); } } }
				 * 
				 * break;
				 */
			case R.id.btn_check_all:
				if (all_checked) {
					btn_check_all
							.setBackgroundResource(R.drawable.btn_check_on_1);
					un_selectAll();
					all_checked = false;
				} else {
					btn_check_all
							.setBackgroundResource(R.drawable.btn_check_on);
					selectAll();
					all_checked = true;
				}
				break;
			default:
				break;
			}
		}
	};

	private void un_selectAll() {
		if (lv_sjdj_shouli_tjcl.getAdapter() != null) {
		for (int i = 0; i < lv_sjdj_shouli_tjcl.getAdapter().getCount(); i++) {
			CheckBox cb_shouqu = (CheckBox) lv_sjdj_shouli_tjcl.getChildAt(i)
					.findViewById(R.id.cb_sjdj_shouli_tjcj_cb_shouqu);
			CheckBox cb_caiji = (CheckBox) lv_sjdj_shouli_tjcl.getChildAt(i)
					.findViewById(R.id.cb_sjdj_shouli_tjcj_cb_caiji);
			cb_shouqu.setChecked(false);
			cb_caiji.setChecked(false);
		}
		}
	}
		

	private void selectAll() {
		if (lv_sjdj_shouli_tjcl.getAdapter() != null) {
		for (int i = 0; i < lv_sjdj_shouli_tjcl.getAdapter().getCount(); i++) {
			CheckBox cb_shouqu = (CheckBox) lv_sjdj_shouli_tjcl.getChildAt(i)
					.findViewById(R.id.cb_sjdj_shouli_tjcj_cb_shouqu);
			CheckBox cb_caiji = (CheckBox) lv_sjdj_shouli_tjcl.getChildAt(i)
					.findViewById(R.id.cb_sjdj_shouli_tjcj_cb_caiji);
			cb_shouqu.setChecked(true);
			cb_caiji.setChecked(true);
		}
		}
	}

	private boolean checkBltj() {
		boolean flag = true;
		if (lv_sjdj_shouli_bltj != null) {
			list_bltj = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < lv_sjdj_shouli_bltj.getAdapter().getCount(); i++) {
				CheckBox cb = (CheckBox) lv_sjdj_shouli_bltj.getChildAt(i)
						.findViewById(R.id.cb_sjdj_shouli_bltj);
				Map<String, Object> map = new HashMap<String, Object>();
				if (!cb.isChecked()) {
					map.put("name",
							detail_bltj_tjcl.getList_bltj().get(i).get("name"));
					map.put("ifChecked", false);
					list_bltj.add(map);
					flag = false;
				} else {
					map.put("name",
							detail_bltj_tjcl.getList_bltj().get(i).get("name"));
					map.put("ifChecked", true);
					list_bltj.add(map);
				}
			}
			deny_msg.setList_bltj(list_bltj);
		}
		if (flag) {
			return true;
		}
		return false;
	}

	protected boolean checkTjcl() {
		boolean flag = true;
		if (lv_sjdj_shouli_tjcl.getAdapter() != null) {
			list_tjcl = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < lv_sjdj_shouli_tjcl.getAdapter().getCount(); i++) {
				CheckBox cb_shouqu = (CheckBox) lv_sjdj_shouli_tjcl.getChildAt(
						i).findViewById(R.id.cb_sjdj_shouli_tjcj_cb_shouqu);
				CheckBox cb_caiji = (CheckBox) lv_sjdj_shouli_tjcl
						.getChildAt(i).findViewById(
								R.id.cb_sjdj_shouli_tjcj_cb_caiji);
				TextView tv_name = (TextView) lv_sjdj_shouli_tjcl.getChildAt(i)
						.findViewById(R.id.sjdj_shouli_tjcl_tjcl);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", tv_name.getText().toString());
				if (cb_shouqu.getVisibility() == View.INVISIBLE) {
					if (cb_caiji.isChecked()) {
						map.put("ifChecked", true);
						list_tjcl.add(map);
					} else {
						map.put("ifChecked", false);
						list_tjcl.add(map);
						flag = false;
					}
				} else {
					if (!cb_shouqu.isChecked() && !cb_caiji.isChecked()) {
						map.put("ifChecked", false);
						list_tjcl.add(map);
						flag = false;
					} else {
						map.put("ifChecked", true);
						list_tjcl.add(map);
					}
				}
			}
			deny_msg.setList_tjcl(list_tjcl);
		}
		if (flag) {
			return true;
		}
		return false;
	}

	private boolean checkIfSuitableToApply() {
		if (edt_search_1.getText().toString().equals("")
				|| edt_search_2.getText().toString().equals("")) {
			Toast.makeText(ZWFW_Zxsb_Activity.this, "姓名或者身份证号不能为空！",
					Toast.LENGTH_LONG).show();
			return false;
		}
		matter.setName(edt_search_1.getText().toString());
		matter.setId_num(edt_search_2.getText().toString());
		if (!StringUtil.IDCardValidate(edt_search_2.getText().toString())
				.equals("")) {
			Toast.makeText(
					ZWFW_Zxsb_Activity.this,
					StringUtil
							.IDCardValidate(edt_search_2.getText().toString()),
					Toast.LENGTH_LONG).show();
			return false;
		}
		if (tv_sjdj_csdd_first.getText().toString().equals("请选择发放地点")
				|| tv_sjdj_csdd_second.getText().toString().equals("请选择发放地点")
				|| tv_sjdj_ffdd_first.getText().toString().equals("请选择发放地点")
				|| tv_sjdj_ffdd_second.getText().toString().equals("请选择发放地点")) {
			Toast.makeText(ZWFW_Zxsb_Activity.this, "请选择初审地点和发放地点！",
					Toast.LENGTH_LONG).show();
			return false;
		}
		if (!edt_search_3.getText().toString().equals("")
				&& !StringUtil.isMobile(edt_search_3.getText().toString())) {
			Toast.makeText(ZWFW_Zxsb_Activity.this, "请正确填写手机号码！",
					Toast.LENGTH_LONG).show();
			return false;
		}
		if (!edt_search_4.getText().toString().equals("")
				&& !StringUtil.isEmail(edt_search_4.getText().toString())) {
			Toast.makeText(ZWFW_Zxsb_Activity.this, "请正确填写邮箱地址！",
					Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}

	/*
	 * 弹出提交材料选择框
	 */

	public void showTJCL_Dialog() {
		dialog = new AlertDialog.Builder(this).create();
		dialog.show();
		Window window = dialog.getWindow();
		window.setContentView(R.layout.sjdj_shouli_tjcl_checkbox_dialog);
		LayoutInflater inflater = LayoutInflater.from(context);

		View convertView = inflater.inflate(
				R.layout.sjdj_shouli_tjcl_checkbox_dialog, null);
		lv_sjdj_shouli_tjcl_checkbox = (ListView) window
				.findViewById(R.id.lv_sjdj_shouli_tjcl_checkbox);
		Button btn_ensure = (Button) window.findViewById(R.id.btnEnsure);
		Button btn_cancle = (Button) window.findViewById(R.id.btn_cancel);
		btn_ensure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ll_sjdj_tjcl_all.setVisibility(View.VISIBLE);
				 resultCode = new StringBuffer();
				if (Constants.list_middle.size() == 1) {
					resultCode.append(Constants.list_middle.get(0).get("checked_id"));
				} else {
					for (Integer i = 0; i < Constants.list_middle.size(); i++) {

						resultCode.append(Constants.list_middle.get(i).get(
								"checked_id")
								+ "|");

					}
				}

				str_resultCode = resultCode.toString();
				List<Map<String, Object>> list_canshu = new ArrayList<Map<String, Object>>();
				Map<String, Object> map_canshu = new HashMap<String, Object>();
				map_canshu.put("serviceSubjectId", infoFile_.shixiang_id()
						.get());/* infoFile_.serviceSubjectId().get() */
				map_canshu.put("resultCode", resultCode.toString());
				list_canshu.add(map_canshu);
				mZwfw_zxsbManagerService
						.getAcceptInfo2(context, 6, list_canshu);
				dialog.cancel();
			}
		});
		btn_cancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.cancel();

			}
		});
		for (int i = 0; i < detail_bltj_tjcl.getList_tjcl().size(); i++) {
			Map<String, Object> map_middle = new HashMap<String, Object>();
			map_middle.put("rg_id", i);
			map_middle.put("list_tjcl", new ArrayList<Map<String, Object>>());
			Constants.list_middle.add(map_middle);
		}
		lv_sjdj_shouli_tjcl_checkbox
				.setAdapter(new Sjdj_shouli_tjcl_check_dialog_ListAdapter(
						ZWFW_Zxsb_Activity.this, detail_bltj_tjcl
								.getList_tjcl()));
	}

	/*
	 * 弹下拉列表方法方法
	 */
	public void showPopuWindow(final TextView tv, final View view,
			final List<String> list) {
		new MyPopupWindow(ZWFW_Zxsb_Activity.this, list, view,
				MyPopupWindow.BOTTOM) {
			@SuppressWarnings("unchecked")
			@Override
			protected void doNext(int position) {
				tv.setText(list.get(position));

				if (view.getId() == R.id.ll_sjdj_csdd_first) {
					// matter.setCode_csdd((String)
					// matter.list_chushen_address.get(position).get("code"));
					list_chushen_address_second = new ArrayList<String>();
					list_chushen_address_second_id = new ArrayList<String>();
					for (Map<String, Object> map : matter.list_chushen_address) {
						if (map.get("firstname").equals(list.get(position))) {
							tv_sjdj_csdd_second.setText("请选择初审地点");
							tv_sjdj_csdd_second
									.setText(((String) (((List<Map<String, Object>>) (map
											.get("my_second_list"))).get(0)
											.get("secondname"))));
							matter.setCode_csdd((String) (((List<Map<String, Object>>) (map
									.get("my_second_list"))).get(0).get("code")));
							matter.setName_csdd((String) (((List<Map<String, Object>>) (map
									.get("my_second_list"))).get(0)
									.get("secondname")));
							for (Map<String, Object> map_second : (List<Map<String, Object>>) map
									.get("my_second_list")) {
								list_chushen_address_second
										.add((String) map_second
												.get("secondname"));
								list_chushen_address_second_id
										.add((String) map_second.get("code"));

								/*
								 * if (map_second.get("ifdefaultsecondvalue")
								 * .equals("true")) { tv_sjdj_csdd_second
								 * .setText((String) map_second
								 * .get("secondname"));
								 * matter.setCode_csdd((String) map_second
								 * .get("code")); matter.setName_csdd((String)
								 * map_second .get("secondname")); //
								 * Toast.makeText(context, (String) //
								 * map_second.get("code"), //
								 * Toast.LENGTH_LONG).show(); }
								 */
							}
						}
					}
				}
				if (view.getId() == R.id.ll_sjdj_csdd_second) {
					matter.setCode_csdd(list_chushen_address_second_id
							.get(position));
					matter.setName_csdd(list_chushen_address_second
							.get(position));
					// Toast.makeText(context,
					// list_chushen_address_second_id.get(position),
					// Toast.LENGTH_LONG).show();
				}
				if (view.getId() == R.id.ll_sjdj_ffdd_first) {
					// matter.setCode_ffdd((String)
					// matter.list_fafang_address.get(position).get("code"));
					list_fafang_address_second = new ArrayList<String>();
					list_fafang_address_second_id = new ArrayList<String>();
					for (Map<String, Object> map : matter.list_fafang_address) {
						if (map.get("firstname").equals(list.get(position))) {
							tv_sjdj_ffdd_second.setText("请选择发放地点");
							for (Map<String, Object> map_second : (List<Map<String, Object>>) map
									.get("my_second_list")) {
								list_fafang_address_second
										.add((String) map_second
												.get("secondname"));
								list_fafang_address_second_id
										.add((String) map_second.get("code"));
								if (map_second.get("ifdefaultsecondvalue")
										.equals("true")) {
									tv_sjdj_ffdd_second
											.setText((String) map_second
													.get("secondname"));
									matter.setCode_ffdd((String) map_second
											.get("code"));
									matter.setName_ffdd((String) map_second
											.get("secondname"));
									// Toast.makeText(context, (String)
									// map_second.get("code"),
									// Toast.LENGTH_LONG).show();
								}
							}
						}
					}
				}
				if (view.getId() == R.id.ll_sjdj_ffdd_second) {
					matter.setCode_ffdd(list_fafang_address_second_id
							.get(position));
					matter.setName_ffdd(list_fafang_address_second
							.get(position));
					// Toast.makeText(context,
					// list_fafang_address_second_id.get(position),
					// Toast.LENGTH_LONG).show();
				}
			}
		};
	}

	/**
	 * 从服务器中获取审核发放中的各地点数据
	 */
	private Zwfw_zxsbManagerService mZwfw_zxsbManagerService = new Zwfw_zxsbManagerService() {
		@SuppressWarnings("unchecked")
		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
			switch (paramInt) {
			case 1:
				if (paramHandleResult.getGetIfAcceptForeground_ResultSuccess()
						.equals("success")) {
					for (int i = 0; i < paramHandleResult
							.getList_if_accept_foreground().size(); i++) {
						IfAcceptQTSL = (String) paramHandleResult
								.getList_if_accept_foreground().get(i)
								.get("booleanAcceptQtsl");// 收件登记事项是否前台受理
						matter.ifAcceptForegrounf = IfAcceptQTSL;
						infoFile_
								.edit()
								.infoUserId()
								.put((String) paramHandleResult
										.getList_if_accept_foreground().get(i)
										.get("accountId")).apply();
						if (IfAcceptQTSL.equals("false")) {// 若不在前台受理，则显示审核发放栏
							matter.matter_org = (String) paramHandleResult
									.getList_if_accept_foreground().get(i)
									.get("org");// 所属机构
							// matter.matter_name = (String)
							// paramHandleResult.getList_if_accept_foreground().get(i).get("name");//事项名称
							matter.matter_orgid = (String) paramHandleResult
									.getList_if_accept_foreground().get(i)
									.get("orgId");// 所属机构ID
							matter.matter_divisionCode = (String) paramHandleResult
									.getList_if_accept_foreground().get(i)
									.get("divisionCode");// 行政区划
							tv_detail_jbxx_shoulididian
									.setText((String) paramHandleResult
											.getList_if_accept_foreground()
											.get(i).get("org"));// 受理地点初始化
							List<Map<String, Object>> list_csdd = new ArrayList<Map<String, Object>>();// 获取初审和发放地点所需要传的参数
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("serviceSubjectId", matter.matter_id);
							list_csdd.add(map);
							mZwfw_zxsbManagerService.getSjdj_CSDD(
									ZWFW_Zxsb_Activity.this, 2, list_csdd);// 从服务器获取初审地点数据
							mZwfw_zxsbManagerService.getSjdj_FFDD(
									ZWFW_Zxsb_Activity.this, 3, list_csdd);// 从服务器获取发放地点数据
						} else {
							ll_detail_jbxx_shff.setVisibility(View.GONE);// 若在前台受理，则隐藏审核发放栏
						}
						if (((String) (paramHandleResult
								.getList_if_accept_foreground().get(i)
								.get("nextBizNode")))
								.equals("street_formInput")) {

						} else if (((String) (paramHandleResult
								.getList_if_accept_foreground().get(i)
								.get("nextBizNode")))
								.equals("street_resultDeliver")) {
							act_fafang = true;
							have_luru = false;
							// /btn_list_3.setText("完成并发放结果");
						} else {
							have_luru = false;
							// /btn_list_3.setText("完成并进入下一步");
							// btn_list_3.setVisibility(View.GONE);
						}
					}
				}
				break;
			case 2:
				if (paramHandleResult.getGetChushen_Address_ResultSuccess()
						.equals("success")) {// 从服务器中获取初审地点成功
					matter.list_chushen_address = paramHandleResult
							.getList_chushen_address(); // 获取初审地点总列表
					list_chushen_address_first = new ArrayList<String>(); // 初始化初审地点第一栏列表
					list_chushen_address_second = new ArrayList<String>(); // 初始化初审地点第二栏列表
					list_chushen_address_second_id = new ArrayList<String>(); // 初始化初审地点第二栏id列表

					tv_sjdj_csdd_first.setText("请选择受理地点");
					tv_sjdj_csdd_second.setText("请选择初审地点");
					/*
					 * tv_sjdj_csdd_first .setText((String)
					 * matter.list_chushen_address .get(0).get("firstname"));//
					 * 若为默认显示值，则初始化初审地点第一栏 matter.setCode_csdd((String)
					 * matter.list_chushen_address .get(0).get("code"));
					 * tv_sjdj_csdd_second .setText((CharSequence)
					 * ((List<Map<String, Object>>) matter.list_chushen_address
					 * .get(0).get("my_second_list")).get(0).get(
					 * "secondname"));// 若为默认显示值，则初始化初审地点第二栏
					 * matter.setCode_csdd((String) ((List<Map<String, Object>>)
					 * matter.list_chushen_address
					 * .get(0).get("my_second_list")).get(0).get("code"));
					 */
					for (Map<String, Object> map : matter.list_chushen_address) { // 遍历初审地点总列表
						list_chushen_address_first.add((String) map
								.get("firstname")); // 初审地点第一栏添加项
						for (Map<String, Object> map_second_id : (List<Map<String, Object>>) map
								.get("my_second_list")) {// 遍历列表
							list_chushen_address_second_id
									.add((String) map_second_id.get("code"));
						}
						for (Map<String, Object> map_second : (List<Map<String, Object>>) map
								.get("my_second_list")) {// 遍历列表
							list_chushen_address_second.add((String) map_second
									.get("secondname"));// 初审地点第二栏添加项
						}
					}
				}
				break;
			case 3:
				if (paramHandleResult.getGetfafang_Address_ResultSuccess()
						.equals("success")) {
					matter.list_fafang_address = paramHandleResult
							.getList_fafang_address();
					list_fafang_address_first = new ArrayList<String>();
					list_fafang_address_second = new ArrayList<String>();
					list_fafang_address_second_id = new ArrayList<String>();
					tv_sjdj_ffdd_first.setText("请选择发放地点");
					tv_sjdj_ffdd_second.setText("请选择发放地点");
					for (Map<String, Object> map : matter.list_fafang_address) {
						list_fafang_address_first.add((String) map
								.get("firstname"));
						for (Map<String, Object> map_second_id : (List<Map<String, Object>>) map
								.get("my_second_list")) {
							list_fafang_address_second_id
									.add((String) map_second_id.get("code"));
						}

						if (map.get("ifdefaultfirstvalue").equals("true")) {
							tv_sjdj_ffdd_first.setText((String) map
									.get("firstname"));
							// matter.setCode_ffdd((String)map.get("code"));
							for (Map<String, Object> map_second : (List<Map<String, Object>>) map
									.get("my_second_list")) {
								list_fafang_address_second
										.add((String) map_second
												.get("secondname"));
								if (map_second.get("ifdefaultsecondvalue")
										.equals("true")) {
									tv_sjdj_ffdd_second
											.setText((String) map_second
													.get("secondname"));
									matter.setCode_ffdd((String) map_second
											.get("code"));
								}

							}
						}
					}
				}
				break;
			case 4:
				if (paramHandleResult.getGetSjdj_bltj_tjcl_ResultSuccess()
						.equals("success")) {

					List<Map<String, Object>> list_csdd = new ArrayList<Map<String, Object>>();// 获取初审和发放地点所需要传的参数
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("serviceSubjectId", matter.matter_id);
					list_csdd.add(map1);
					mZwfw_zxsbManagerService.getSjdj_CSDD(
							ZWFW_Zxsb_Activity.this, 2, list_csdd);// 从服务器获取初审地点数据
	/*				mZwfw_zxsbManagerService.getSjdj_FFDD(
							ZWFW_Zxsb_Activity.this, 3, list_csdd);// 从服务器获取发放地点数据
*/
					detail_bltj_tjcl = paramHandleResult.getZxsb_detail();
					List<String> list_bltj = new ArrayList<String>();
					for (Map<String, Object> map : detail_bltj_tjcl
							.getList_bltj()) {
						list_bltj.add((String) map.get("name"));
					}
					lv_sjdj_shouli_bltj
							.setAdapter(new Sjdj_shouli_bltj_ListAdapter(
									ZWFW_Zxsb_Activity.this, list_bltj));
					CommUtil.setListViewHeightBasedOnChildren(lv_sjdj_shouli_bltj);
					if (detail_bltj_tjcl.ifHaveCheck) {
						ll_sjdj_tjcl_all.setVisibility(View.GONE);
					} else {
						ll_sjdj_tjcl_checkbox.setVisibility(View.GONE);
						lv_sjdj_shouli_tjcl
								.setAdapter(new Sjdj_shouli_tjcl_ListAdapter(
										ZWFW_Zxsb_Activity.this,
										detail_bltj_tjcl
												.getList_tjcl_no_checkbox(),
										onClickListener));

						CommUtil.setListViewHeightBasedOnChildren(lv_sjdj_shouli_tjcl);
					}
				}
				break;
			/*
			 * case 5: if
			 * (paramHandleResult.getGetSjsj_AcceptSubmitSuccess().equals(
			 * "success")) { List<String> list_content = StringUtil
			 * .stringToNormalList(paramHandleResult
			 * .getSjsj_AcceptSubmitContent()); String str =
			 * list_content.get(0).replace("受理成功:", "");
			 * infoFile_.edit().bizArchivesId().put(list_content.get(1))
			 * .apply();
			 * infoFile_.edit().baselist_yewuliushuihao().put(str).apply();
			 * infoFile_.edit().shengqingrenname()
			 * .put(edt_search_1.getText().toString()).apply();
			 * infoFile_.edit().shengqingrenCardId()
			 * .put(edt_search_2.getText().toString()).apply();
			 * Toast.makeText(ZWFW_Zxsb_Activity.this, "受理成功！",
			 * Toast.LENGTH_LONG).show(); Intent intent = new Intent(context,
			 * Bdlr_lrbd_luru_Activity_.class);
			 * infoFile_.edit().serviceSubjectName()
			 * .put(matter.matter_name).apply();
			 * infoFile_.edit().serviceSubjectId().put(matter.matter_id)
			 * .apply(); //
			 * infoFile_.edit().shengqingrenname().put(edt_search_1.
			 * getText().toString()).apply(); //
			 * infoFile_.edit().shengqingrenCardId
			 * ().put(edt_search_2.getText().toString()).apply();
			 * infoFile_.edit().dfflb_bjzt().put("成功").apply();
			 * 
			 * if (goto_luru) { if (have_luru) { context.startActivity(intent);
			 * Constants.title = context.getResources().getString(
			 * R.string.dailurubiaodan);
			 * 
			 * ZWFW_Zxsb_Activity.this.finish(); } else if (act_fafang) {
			 * List<Map<String, Object>> list_1 = new ArrayList<Map<String,
			 * Object>>(); Map<String, Object> oMap = new HashMap<String,
			 * Object>(); oMap.put("bizArchivesId", infoFile_.bizArchivesId()
			 * .get()); oMap.put("userAccountId", infoFile_.infoUserId()
			 * .get()); oMap.put("imageStream", ""); list_1.add(oMap);
			 * baseDetail_ManagerService.doSendReceipt( ZWFW_Zxsb_Activity.this,
			 * 7, list_1); } else { Constants.title =
			 * context.getResources().getString( R.string.yishouliyewu);
			 * ZWFW_Zxsb_Activity.this.finish(); ZWFW_Zxsb_Activity.this
			 * .startActivity(new Intent( ZWFW_Zxsb_Activity.this,
			 * Base_DetailActivity_.class)); }
			 * 
			 * } else {
			 * 
			 * ZWFW_Zxsb_Activity.this.finish(); }
			 * 
			 * } else { Toast.makeText(ZWFW_Zxsb_Activity.this, "受理失败！",
			 * Toast.LENGTH_LONG).show(); } break;
			 */
			case 6:
				if (paramHandleResult.getSjdj_getTjclSuccess()
						.equals("success")) {
					Constants.list_tjcl_for_save_uploaded_pictures = new ArrayList<Map<String, Object>>();
					// ll_sjdj_tjcl_checkbox.setVisibility(View.GONE);
					lv_sjdj_shouli_tjcl
							.setAdapter(new Sjdj_shouli_tjcl_ListAdapter(
									ZWFW_Zxsb_Activity.this, paramHandleResult
											.getSjdj_tjcl_no_checkbox(),
									onClickListener));

					CommUtil.setListViewHeightBasedOnChildren(lv_sjdj_shouli_tjcl);
				}
				break;
			default:
				break;
			}
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();

		}
		return false;
	}

	/*
	 * private BaseDetail_ManagerService baseDetail_ManagerService = new
	 * BaseDetail_ManagerService() {
	 * 
	 * @Override public void handlerBaseDetailInfo(Context context, HandleResult
	 * paramHandleResult, int paramInt) { if
	 * (paramHandleResult.getJfjgSuccess().equals("success")) {
	 * Toast.makeText(ZWFW_Zxsb_Activity.this, "办理成功！",
	 * Toast.LENGTH_LONG).show(); Constants.title = (context.getResources()
	 * .getString(R.string.yushouliyewu));
	 * ZWFW_Zxsb_Activity.this.finishOthers();
	 * ZWFW_Zxsb_Activity.this.startActivity(new Intent(
	 * ZWFW_Zxsb_Activity.this, Base_DetailActivity_.class));
	 * 
	 * }
	 * 
	 * }
	 * 
	 * };
	 */

}
