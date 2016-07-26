package com.WSZW.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.WSZW.adapter.Bdlr_Dlrbd_Luru_Form_Adapter;
import com.WSZW.adapter.Bdlr_Dlrbd_Luru_Form_Adapter.MyCallBack;
import com.WSZW.data.Constants;
import com.WSZW.data.InfoFile_;
import com.WSZW.entity.HandleResult;
import com.WSZW.service.BdlrManagerService;
import com.WSZW.util.StringUtil;
import com.WSZW.view.FormFillView;
import com.WSZW.entity.Sjdj_matter_bean;




import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;



@EActivity(R.layout.layout_zxsb_bdlr)
public class ZWFW_ZXSB_bdlr_Activity extends BaseActivity implements OnClickListener {

	@Pref
	InfoFile_ infoFile_;
	@ViewById
	RelativeLayout rl_title;// 标题栏
	@ViewById
	TextView tvTitle_two_Name;// 二级标题的显示文本
	@ViewById
	LinearLayout ll_form;
	@ViewById
	Button btn_list_2, btn_list_3;
	List<Map<String, Object>> list;
	Bdlr_Dlrbd_Luru_Form_Adapter adapter;

	String shenqingrenName = "";
	String shenqingrenID = "";
	String shixiangID = "";
	
	public String name = null; // 姓名
	public String id_num = null; // 身份证号
	public String telephone_num= ""; // 手机号码
	public String email= ""; // 电子邮件
	public String organizationId = null;//办事窗口ID
	public String resultCode = null;
	public String isSendMsg = "false";
	public StringBuffer bizArchivesAttachmentIds = null;
	@AfterViews
	void initView() {
		rl_title.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.tt_dllbd));
		tvTitle_two_Name.setText(infoFile_.shixiang_name().get());
		btn_list_2.setOnClickListener(onclick);
		btn_list_3.setOnClickListener(onclick);

		if (infoFile_.shixiang_name().get() != null) {
			shenqingrenName = infoFile_.shengqingrenname().get();
		}
		if (infoFile_.serviceSubjectId().get() != null) {
			shenqingrenID = infoFile_.shengqingrenCardId().get();
		}
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		List<Map<String, Object>> paramlist = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serviceSubjectName",
				StringUtil.replaceSpace(infoFile_.shixiang_name().get()));
		paramlist.add(map);
		blBdlrManagerService.getServiceSubjectForm(this, 1, paramlist);
		Intent intent = getIntent();
		name = intent.getStringExtra("name");
		id_num = intent.getStringExtra("id_num");
		telephone_num = intent.getStringExtra("telephone_num");
		email = intent.getStringExtra("email");
		organizationId = intent.getStringExtra("organizationId");
		resultCode = intent.getStringExtra("resultCode");
		isSendMsg = intent.getStringExtra("isSendMsg");
		shixiangID = infoFile_.shixiang_id().get();
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		default:
			break;
		}
	}

	MyCallBack callBack=new MyCallBack() {
		
		@Override
		public void callback(String s, String key, int position) {
			// TODO Auto-generated method stub
			list.get(position).put(key, s);
		}
	};

	private BdlrManagerService blBdlrManagerService = new BdlrManagerService() {

		@Override
		public void handlerLoginInfo(Context context,
				HandleResult paramHandleResult, int paramInt) {
			switch (paramInt) {
			case 1:
				if (paramHandleResult.getGetBdlr_getServiceSubjectFormSuccess()
						.equals("success")) {
					list = new ArrayList<Map<String, Object>>();
					list = paramHandleResult.getList_bdlr_form();
					// adapter = new Bdlr_Dlrbd_Luru_Form_Adapter(context, list,
					// callBack);
					// lv_base.setAdapter(adapter);
					FormFillView fillView = new FormFillView(context, list,
							callBack, shenqingrenName, shenqingrenID);
					ll_form.addView(fillView.getView());
					// CommUtil.setListViewHeightBasedOnChildren(lv_base);
				}

				break;
			case 2:
				if (paramHandleResult.getGetSubmitServiceSubjectFormSuccess()
						.equals("success")) {
					String str = "";
					if (paramHandleResult.getSubmitBdlrFormContent().contains(
							"更新成功")) {
						str = paramHandleResult.getSubmitBdlrFormContent()
								.replace("更新成功:", "");
					}
					Toast.makeText(ZWFW_ZXSB_bdlr_Activity.this, "录入成功！",
							Toast.LENGTH_LONG).show();
/*					Intent intent = new Intent();
					intent.setClass(ZWFW_ZXSB_bdlr_Activity.this,
							Base_DetailActivity_.class);
					infoFile_.edit().bizArchivesId().put(str).apply();
					infoFile_.edit().bdlr_ywlsh()
							.put(infoFile_.baselist_yewuliushuihao().get())
							.apply();
					Constants.title = context.getResources().getString(
							R.string.yilurubiaodan);
					ZWFW_ZXSB_bdlr_Activity.this.startActivity(intent);*/
					ZWFW_ZXSB_bdlr_Activity.this.finish();
				}
				break;
			default:
				break;
			}
		}
	};

	public View.OnClickListener onclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_list_2:
				ZWFW_ZXSB_bdlr_Activity.this
						.startActivity(new Intent(ZWFW_ZXSB_bdlr_Activity.this,
								ZWFW_Zxsb_Activity_.class));
				ZWFW_ZXSB_bdlr_Activity.this.finish();
				break;
			case R.id.btn_list_3:
				getPicture_ID_List();
				
				if(name == null || id_num == null || organizationId == null){
					Toast.makeText(context, "请返回重新输入用户信息！", Toast.LENGTH_SHORT).show();
					return;
				}
				StringBuffer str = new StringBuffer();
				String str_ywlsh = infoFile_.baselist_yewuliushuihao().get();
/*				str.append("<?xml version='1.0' encoding='utf-8'?>"
						+ "<case><ZsoftInfo><Account><![CDATA["
						+ infoFile_.infoUsername().get().replace(" ", "")
						+ "]]></Account><Ywlsh><![CDATA["
						+ str_ywlsh.replace(" ", "")
						+ "]]></Ywlsh><Ywbd><![CDATA[{");*/
				if(list == null || list.size() == 0){
					Toast.makeText(context, "获取表单失败，请重新获取！", Toast.LENGTH_SHORT).show();
					return;
				}
				for (int i = 0; i < list.size(); i++) {
					// str.append(list.get(i).get("name")+":");
					if (list.get(i).get("nonEmpty").equals("false")) {
						if (list.get(i).get("name").toString().contains("身份证号")) {
							// EditText edt = (EditText) lv_base.getChildAt(i)
							// .findViewById(R.id.edt_job);
							String s = (String) list.get(i).get("value");
							if (!StringUtil.IDCardValidate(s).equals("")) {
								Toast.makeText(ZWFW_ZXSB_bdlr_Activity.this,
										StringUtil.IDCardValidate(s),
										Toast.LENGTH_LONG).show();
								return;
							}
						}
						if (list.get(i).get("controlType").toString()
								.equals("DATEFIELD")) {
							// TextView tv = (TextView) lv_base.getChildAt(i)
							// .findViewById(R.id.tv_search_2);
							String s = (String) list.get(i).get("value");
							if (StringUtil.isBlank(s)) {
								Toast.makeText(ZWFW_ZXSB_bdlr_Activity.this,
										list.get(i).get("name") + "不能为空！",
										Toast.LENGTH_LONG).show();
								return;
							} else {
								if (i == 0) {
									str.append(list.get(i).get("columnName")
											+ "=" + s);
								} else {
									str.append(","
											+ list.get(i).get("columnName")
											+ "=" + s);
								}
							}
						} else if (list.get(i).get("controlType").toString()
								.equals("TEXTBOX")) {
							// EditText edt = (EditText) lv_base.getChildAt(i)
							// .findViewById(R.id.edt_job);
							String s = (String) list.get(i).get("value");
							if (StringUtil.isBlank(s)) {
								Toast.makeText(ZWFW_ZXSB_bdlr_Activity.this,
										list.get(i).get("name") + "不能为空！",
										Toast.LENGTH_LONG).show();
								return;
							} else {
								if (i == 0) {
									str.append(list.get(i).get("columnName")
											+ "=" + s);
								} else {
									str.append(","
											+ list.get(i).get("columnName")
											+ "=" + s);
								}
							}
						} else if (list.get(i).get("controlType")
								.equals("ADDRESS")) {
							// TextView tv = (TextView) lv_base.getChildAt(i)
							// .findViewById(R.id.tv_search_2);
							String s = (String) list.get(i).get("codeBText");
							if (StringUtil.isBlank(s)) {
								Toast.makeText(ZWFW_ZXSB_bdlr_Activity.this,
										list.get(i).get("name") + "不能为空！",
										Toast.LENGTH_LONG).show();
								return;
							} else {
								if (i == 0) {
									str.append(list.get(i).get("columnName")
											+ "=" + list.get(i).get("code"));
								} else {
									str.append(","
											+ list.get(i).get("columnName")
											+ "=" + list.get(i).get("code"));
								}
							}
						} else if (list.get(i).get("controlType")
								.equals("DROPDOWN_LIST")) {
							// TextView tv = (TextView) lv_base.getChildAt(i)
							// .findViewById(R.id.tv_search_1);
							String s = (String) list.get(i).get("value");
							if (StringUtil.isBlank(s) || s.equals("---请选择---")) {
								Toast.makeText(ZWFW_ZXSB_bdlr_Activity.this,
										list.get(i).get("name") + "不能为空！",
										Toast.LENGTH_LONG).show();
								return;
							} else {
								if (i == 0) {
									str.append(list.get(i).get("columnName")
											+ "=" + s);
								} else {
									str.append(","
											+ list.get(i).get("columnName")
											+ "=" + s);
								}

							}
							Log.e("1111111111" + "ccc" + i, s);
						}
					} else {
						if (list.get(i).get("controlType").equals("DATEFIELD")) {
							// TextView tv = (TextView) lv_base.getChildAt(i)
							// .findViewById(R.id.tv_search_2);
							String s = (String) list.get(i).get("value");
							if (i == 0) {
								str.append(list.get(i).get("columnName") + "="
										+ s);
							} else {
								str.append("," + list.get(i).get("columnName")
										+ "=" + s);
							}
						} else if (list.get(i).get("controlType")
								.equals("TEXTBOX")) {
							// EditText edt = (EditText) lv_base.getChildAt(i)
							// .findViewById(R.id.edt_job);
							String s = (String) list.get(i).get("value");
							if (i == 0) {
								str.append(list.get(i).get("columnName") + "="
										+ s);
							} else {
								str.append("," + list.get(i).get("columnName")
										+ "=" + s);
							}
						} else if (list.get(i).get("controlType")
								.equals("ADDRESS")) {
							// TextView tv = (TextView) lv_base.getChildAt(i)
							// .findViewById(R.id.tv_search_2);
							if (i == 0) {
								str.append(list.get(i).get("columnName") + "="
										+ list.get(i).get("code"));
							} else {
								str.append("," + list.get(i).get("columnName")
										+ "=" + list.get(i).get("code"));
							}
						} else if (list.get(i).get("controlType")
								.equals("DROPDOWN_LIST")) {
							// TextView tv = (TextView) lv_base.getChildAt(i)
							// .findViewById(R.id.tv_search_1);
							String s = (String) list.get(i).get("value");
							if (i == 0) {
								str.append(list.get(i).get("columnName") + "="
										+ s);
							} else {
								str.append("," + list.get(i).get("columnName")
										+ "=" + s);
							}
						}
					}

				}
				getPicture_ID_List();
//				str.append("}]]>" + "</Ywbd></ZsoftInfo></case>");
				List<Map<String, Object>> paramlist = new ArrayList<Map<String, Object>>();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("fillForm", str.toString());
				map.put("serviceSubjectId", shixiangID);
				map.put("userName", name);
				map.put("identityNumber", id_num);
				map.put("serviceTargetPhone", telephone_num);
				map.put("serviceTargetEmail", email);
				map.put("organizationId", organizationId);
				map.put("resultCode", resultCode);
				map.put("isSendMsg", isSendMsg);
				/*if(bizArchivesAttachmentIds == null){
					map.put("bizArchivesAttachmentIds", "175153d8-2887-4f0e-9cc5-d8adb636c2d2");
				}else{*/
					map.put("bizArchivesAttachmentIds", bizArchivesAttachmentIds.toString());
			/*	}*/
				
				paramlist.add(map);
				blBdlrManagerService.SubmitServiceSubjectForm(
						ZWFW_ZXSB_bdlr_Activity.this, 2, paramlist);
				break;
			default:
				break;
			}
		}
	};
	@SuppressWarnings("unchecked")
	protected void getPicture_ID_List() {
		bizArchivesAttachmentIds = new StringBuffer();
//		if(Constants.list_tjcl_for_save_uploaded_pictures.size()!=0 && Constants.list_tjcl_for_save_uploaded_pictures.get(0).get("list_pictures_id") != null){
//			for(int i=0;i<((List<String>)(Constants.list_tjcl_for_save_uploaded_pictures.get(i).get("list_pictures_id"))).size();i++){
//				if(i== 0){
//					bizArchivesAttachmentIds .append(((List<String>)(Constants.list_tjcl_for_save_uploaded_pictures.get(i).get("list_pictures_id"))).get(0));
//				}else{
//					bizArchivesAttachmentIds.append(","+((List<String>)(Constants.list_tjcl_for_save_uploaded_pictures.get(i).get("list_pictures_id"))).get(i));
//				}
//			}
//		}
		Boolean bool =true;
		Object c;
		List<Map<String, Object>> list_tjcl_for_save_uploaded_picturess = new ArrayList<Map<String, Object>>();
		list_tjcl_for_save_uploaded_picturess = Constants.list_tjcl_for_save_uploaded_pictures;
		if(Constants.list_tjcl_for_save_uploaded_pictures != null && Constants.list_tjcl_for_save_uploaded_pictures.size()!=0){
			
			
			for(int i=0;i<Constants.list_tjcl_for_save_uploaded_pictures.size();i++){		
				if(Constants.list_tjcl_for_save_uploaded_pictures.get(i) != null && 
						((List<String>)(Constants.list_tjcl_for_save_uploaded_pictures.get(i).get("list_pictures_id"))).size() != 0){
					for(int j=0;j<((List<String>)(Constants.list_tjcl_for_save_uploaded_pictures.get(i).get("list_pictures_id"))).size();j++){
						if(i == 0 && bool){
							
							bizArchivesAttachmentIds .append(((List<String>)(Constants.list_tjcl_for_save_uploaded_pictures.get(0).get("list_pictures_id"))).get(0));
							bool=false;
							Log.e("111===>>>", bizArchivesAttachmentIds.toString());
						}else{
							bizArchivesAttachmentIds .append(","+((List<String>)(Constants.list_tjcl_for_save_uploaded_pictures.get(i).get("list_pictures_id"))).get(j));
							Log.e("111===>>>", bizArchivesAttachmentIds.toString());
						}
					}
					
				}
				/*//c =Constants.list_tjcl_for_save_uploaded_pictures.get(i).values();
				Iterator it=Constants.list_tjcl_for_save_uploaded_pictures.get(i).values().iterator();//((List<Map<String, Object>>) c)
				while(it.hasNext()
						
						(Constants.list_tjcl_for_save_uploaded_pictures.get(i).getValue()!= null && 
						((List<String>)(Constants.list_tjcl_for_save_uploaded_pictures.get(i).get("list_pictures_id"))).size() != 0)){
					for(int j=0;j<((List<String>)(Constants.list_tjcl_for_save_uploaded_pictures.get(i).get("list_pictures_id"))).size();j++){
						if(i==0&&bool){
							
							bizArchivesAttachmentIds .append(it.next().toString()((List<String>)(Constants.list_tjcl_for_save_uploaded_pictures.get(0).get("list_pictures_id"))).get(0));
							bool=false;
							Log.e("111===>>>", bizArchivesAttachmentIds.toString());
						}else{
							bizArchivesAttachmentIds.append(","+it.next().toString()((List<String>)(Constants.list_tjcl_for_save_uploaded_pictures.get(i).get("list_pictures_id"))).get(j));
							Log.e("222===>>>", bizArchivesAttachmentIds.toString());
						}
					}
				}*/
			}
		}
/*		if(list_tjcl_for_save_uploaded_picturess != null && list_tjcl_for_save_uploaded_picturess.size() != 0){
			
		for(int a = 0;a<list_tjcl_for_save_uploaded_picturess.size();a++){
			if(list_tjcl_for_save_uploaded_picturess.get(a) != null && list_tjcl_for_save_uploaded_picturess.get(a).size() != 0){
				if(list_tjcl_for_save_uploaded_picturess.get(a) != null && list_tjcl_for_save_uploaded_picturess.get(a).size() != 0){
					
				for(int b=0;b<list_tjcl_for_save_uploaded_picturess.get(a).size() ;b++){
					if(a == 0 && b == 0){
						bizArchivesAttachmentIds.append((((List<String>))(list_tjcl_for_save_uploaded_picturess.get(a).get("list_pictures_id")).get)
					}

				}
				}
			}
		}
		}		*/
	}
	
	
	
	
	
	

}
