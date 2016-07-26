package com.WSZW.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.WSZW.Activity.R;
import com.WSZW.adapter.Bdlr_Dlrbd_Luru_Form_Adapter.MyCallBack;
import com.WSZW.util.BdlrAddressHandle;
import com.WSZW.util.BdlrAddressHandleC2A;
import com.WSZW.util.DatePickDialog;
import com.WSZW.util.StringUtil;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



public class FormFillView {

	private LinearLayout view;
	private Context context = null;
	private List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
	private MyCallBack callBack;
	private boolean editable = true;
	private String str_shenqingrenName = null;
	private String str_shenqingrenID = null;

	public FormFillView(Context context, List<Map<String, Object>> datas,
			MyCallBack callback, String str_name, String str_id) {
		this.datas = datas;
		this.context = context;
		this.callBack = callback;
		this.str_shenqingrenName = str_name;
		this.str_shenqingrenID = str_id;
		initView();
	}

	public FormFillView(Context context, List<Map<String, Object>> datas,
			MyCallBack callback, boolean editable) {
		this.datas = datas;
		this.context = context;
		this.callBack = callback;
		this.editable = editable;
		initView();
	}

	public View getView() {
		return view;
	}

	private void initView() {
		view = new LinearLayout(context);
		view.setOrientation(LinearLayout.VERTICAL);
		for (int i = 0; i < datas.size(); i++) {
			View v = null;
			if (editable) {
				v = initItemView(datas.get(i), i);
			} else {
				v = initItemView2(datas.get(i), i);
			}
			view.addView(v);
		}
	}

	private View initItemView(final Map<String, Object> map, final int position) {
		View item = getItemView();
		ViewHolder viewHolder = (ViewHolder) item.getTag();
		if (map.get("controlType").equals("TEXTBOX")) {

			viewHolder.ll_bdlr_shurukuang.setVisibility(View.VISIBLE);
			viewHolder.ll_more_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_springs_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_time_chioce_kuang.setVisibility(View.GONE);

			viewHolder.tv_bdlr_shurukuang.setText((String) map.get("name"));
			viewHolder.edt_bdlr_shurukuang.setFocusable(true);
			viewHolder.edt_bdlr_shurukuang.setFocusableInTouchMode(true);
			final EditText edt_bdlr_shurukuang = viewHolder.edt_bdlr_shurukuang;
			try {
				edt_bdlr_shurukuang.setText(map.get("value").toString());
				Log.e("", "inset position : " + position + " content : "
						+ map.get("value").toString());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			viewHolder.edt_bdlr_shurukuang
					.addTextChangedListener(new TextWatcher() {

						@Override
						public void onTextChanged(CharSequence arg0, int arg1,
								int arg2, int arg3) {
							// TODO 自动生成的方法存根

						}

						@Override
						public void beforeTextChanged(CharSequence arg0,
								int arg1, int arg2, int arg3) {
							// TODO 自动生成的方法存根

						}

						@Override
						public void afterTextChanged(Editable arg0) {
							// TODO 自动生成的方法存根
							callBack.callback(edt_bdlr_shurukuang.getText()
									.toString(), "value", position);
						}
					});

			// viewHolder.edt_bdlr_shurukuang
			// .setOnFocusChangeListener(new OnFocusChangeListener() {
			//
			// @Override
			// public void onFocusChange(View arg0, boolean arg1) {
			// // TODO 自动生成的方法存根
			// if (!arg1) {
			// Log.e("", "aaa " + position);
			// // map.put("value",
			// // edt_bdlr_shurukuang.getText().toString());
			// callBack.callback(edt_bdlr_shurukuang.getText()
			// .toString(), "value", position);
			// } else {
			// Log.e("", "bbb " + position);
			// }
			// }
			// });
			if (map.get("name").toString().equals("申请人")
					|| map.get("name").toString().equals("姓名")
					|| map.get("name").toString().equals("申请人姓名")) {
				callBack.callback(str_shenqingrenName, "value", position);
				viewHolder.edt_bdlr_shurukuang.setText(str_shenqingrenName);
			}
			if (map.get("name").toString().equals("身份证号")
					|| map.get("name").toString().equals("申请人身份证号码")) {
				callBack.callback(str_shenqingrenID, "value", position);
				viewHolder.edt_bdlr_shurukuang.setText(str_shenqingrenID);
			}
			if (map.get("nonEmpty").equals("false")) {
				viewHolder.iv_bdlr_shurukuang.setVisibility(View.VISIBLE);
			}

		} else if (map.get("controlType").equals("ADDRESS")) {

			viewHolder.ll_bdlr_shurukuang.setVisibility(View.GONE);
			viewHolder.ll_more_chioce_kuang.setVisibility(View.VISIBLE);
			viewHolder.ll_bdlr_springs_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_time_chioce_kuang.setVisibility(View.GONE);

			viewHolder.tv_more_chioce_kuang.setText((String) datas
					.get(position).get("name"));

			final LinearLayout ll_more_chioce_kuang_address = viewHolder.ll_more_chioce_kuang_address;
			final LinearLayout ll_more_chioce_kuang_address2 = viewHolder.ll_more_chioce_kuang_address2;
			final TextView tv_more_chioce_kuang_address = viewHolder.tv_more_chioce_kuang_address;
			final TextView tv_more_chioce_kuang_address2 = viewHolder.tv_more_chioce_kuang_address2;
			try {
				tv_more_chioce_kuang_address.setText((String) datas.get(
						position).get("codeAText"));
				Log.e("", "" + (String) map.get("codeAText"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			viewHolder.ll_more_chioce_kuang_address
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							final List<String> list = new ArrayList<String>();
							list.add("南昌县");
							list.add("莲塘镇");
							list.add("向塘镇");
							list.add("三江镇");
							list.add("塘南镇");
							list.add("幽兰镇");
							list.add("蒋巷镇");
							list.add("武阳镇");
							list.add("冈上镇");
							list.add("广福镇");
							list.add("泾口乡");
							list.add("南新乡");
							list.add("塔城乡");
							list.add("黄马乡");
							list.add("富山乡");
							list.add("东新乡");
							list.add("八一乡");
							list.add("小蓝经济开发区");
							list.add("银三角管委会");
							new MyPopupWindow(context, list,
									ll_more_chioce_kuang_address,
									MyPopupWindow.BOTTOM) {
								protected void doNext(int p) {
									tv_more_chioce_kuang_address.setText(list
											.get(p));
									// map.put("codeAText",
									// list.get(position));
									callBack.callback(list.get(p), "codeAText",
											position);
								};
							};

						}
					});
			try {
				tv_more_chioce_kuang_address2.setText((String) datas.get(
						position).get("codeBText"));
				Log.e("", "" + (String) map.get("codeBText"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			viewHolder.ll_more_chioce_kuang_address2
					.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							final List<Map<String, Object>> list1 = BdlrAddressHandle
									.getAddressList(tv_more_chioce_kuang_address
											.getText().toString());
							new MyPopupWindow(context, list1,
									ll_more_chioce_kuang_address2,
									MyPopupWindow.BOTTOM, 0) {
								@Override
								protected void doNext(int p) {
									tv_more_chioce_kuang_address2
											.setText((String) list1.get(p).get(
													"name"));
									// map.put("code",
									// list1.get(position).get("code"));
									// map.put(
									// "codeBText",
									// (String) list1.get(position).get(
									// "name"));
									callBack.callback((String) list1.get(p)
											.get("code"), "code", position);
									callBack.callback((String) list1.get(p)
											.get("name"), "codeBText", position);
									map.put("code",
											(String) list1.get(p).get("code"));
									// Toast.makeText(context,
									// (String)list.get(position).get("code"),
									// Toast.LENGTH_LONG).show();
								}
							};
						}
					});

			if (map.get("nonEmpty").equals("false")) {

				viewHolder.iv_more_chioce_kuang.setVisibility(View.VISIBLE);
			}

		} else if (map.get("controlType").equals("DROPDOWN_LIST")) {

			viewHolder.ll_bdlr_shurukuang.setVisibility(View.GONE);
			viewHolder.ll_more_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_springs_chioce_kuang.setVisibility(View.VISIBLE);
			viewHolder.ll_bdlr_time_chioce_kuang.setVisibility(View.GONE);

			viewHolder.tv_bdlr_springs_chioce_kuang.setText((String) datas.get(
					position).get("name"));
			final LinearLayout ll_bdlr_springs_chioce_kuang_fenlei = viewHolder.ll_bdlr_springs_chioce_kuang_fenlei;
			final TextView tv_bdlr_springs_chioce_kuang_fenlei = viewHolder.tv_bdlr_springs_chioce_kuang_fenlei;
			try {
				tv_bdlr_springs_chioce_kuang_fenlei.setText((String) datas.get(
						position).get("value"));
				Log.e("", "" + (String) map.get("value"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			viewHolder.ll_bdlr_springs_chioce_kuang
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							List<String> list = new ArrayList<String>();
							String str = (String) map
									.get("inputControlValueScope");
							if (str.equals("null")) {

							} else {
								list = StringUtil.stringToNormalList(str);
							}
							final List<String> list1 = list;
							new MyPopupWindow(context, list,
									ll_bdlr_springs_chioce_kuang_fenlei,
									MyPopupWindow.BOTTOM) {
								@Override
								protected void doNext(int p) {
									tv_bdlr_springs_chioce_kuang_fenlei
											.setText(list1.get(p));
									// map.put("value",
									// list1.get(position));
									callBack.callback(list1.get(p), "value",
											position);
								}
							};
						}
					});

			if (map.get("nonEmpty").equals("false")) {
				viewHolder.iv_bdlr_springs_chioce_kuang
						.setVisibility(View.VISIBLE);
			}

		} else if (map.get("controlType").equals("DATEFIELD")) {

			viewHolder.ll_bdlr_shurukuang.setVisibility(View.GONE);
			viewHolder.ll_more_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_springs_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_time_chioce_kuang.setVisibility(View.VISIBLE);

			viewHolder.tv_bdlr_time_chioce_kuang.setText((String) datas.get(
					position).get("name"));
			try {
				viewHolder.tv_bdlr_time_chioce_kuang_shenqingriqi
						.setText((String) map.get("value"));
				Log.e("", "" + (String) map.get("value"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			final TextView tv_bdlr_time_chioce_kuang_shenqingriqi = viewHolder.tv_bdlr_time_chioce_kuang_shenqingriqi;
			final android.content.DialogInterface.OnClickListener clickListener = new android.content.DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO 自动生成的方法存根
					// map.put(
					// "value",
					// tv_bdlr_time_chioce_kuang_shenqingriqi.getText()
					// .toString());
					callBack.callback(tv_bdlr_time_chioce_kuang_shenqingriqi
							.getText().toString(), "value", position);
				}
			};

			viewHolder.ll_bdlr_time_chioce_kuang_shenqingriqi
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {

							DatePickDialog.showDateCheckDialog(
									(Activity) context,
									tv_bdlr_time_chioce_kuang_shenqingriqi,
									false, clickListener);
						}
					});

			if (map.get("nonEmpty").equals("false")) {
				viewHolder.iv_bdlr_time_chioce_kuang
						.setVisibility(View.VISIBLE);
			}

		}
		return item;
	}

	private View initItemView2(final Map<String, Object> map, final int position) {
		View item = getItemView();
		ViewHolder viewHolder = (ViewHolder) item.getTag();
		if (map.get("controlType").equals("TEXTBOX")) {

			viewHolder.ll_bdlr_shurukuang.setVisibility(View.VISIBLE);
			viewHolder.ll_more_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_springs_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_time_chioce_kuang.setVisibility(View.GONE);

			viewHolder.tv_bdlr_shurukuang.setText((String) map.get("name"));
			viewHolder.edt_bdlr_shurukuang.setFocusable(true);
			viewHolder.edt_bdlr_shurukuang.setFocusableInTouchMode(true);
			final EditText edt_bdlr_shurukuang = viewHolder.edt_bdlr_shurukuang;
			try {
				String text = map.get("value").toString();
				if (text == null || text.equals("") || text.equals("null")) {
					text = "-";
				}
				edt_bdlr_shurukuang.setText(text);
				Log.e("", "inset position : " + position + " content : "
						+ map.get("value").toString());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			if (map.get("nonEmpty").equals("false")) {
				viewHolder.iv_bdlr_shurukuang.setVisibility(View.VISIBLE);
			}

		} else if (map.get("controlType").equals("ADDRESS")) {

			viewHolder.ll_bdlr_shurukuang.setVisibility(View.GONE);
			viewHolder.ll_more_chioce_kuang.setVisibility(View.VISIBLE);
			viewHolder.ll_bdlr_springs_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_time_chioce_kuang.setVisibility(View.GONE);

			viewHolder.tv_more_chioce_kuang.setText((String) datas
					.get(position).get("name"));

			final LinearLayout ll_more_chioce_kuang_address = viewHolder.ll_more_chioce_kuang_address;
			final LinearLayout ll_more_chioce_kuang_address2 = viewHolder.ll_more_chioce_kuang_address2;
			final TextView tv_more_chioce_kuang_address = viewHolder.tv_more_chioce_kuang_address;
			final TextView tv_more_chioce_kuang_address2 = viewHolder.tv_more_chioce_kuang_address2;
			// viewHolder.ll_addr_gone.setVisibility(View.GONE);
			try {
				String code = (String) datas.get(position).get("value");
				String text = "江西省南昌市南昌县";
				if (code == null || code.equals("") || code.equals("null")) {
					text = "-";
				}
				tv_more_chioce_kuang_address.setText(text);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			try {
				String code = (String) datas.get(position).get("value");
				String name = BdlrAddressHandleC2A.getAddressName(code);
				if (code == null || code.equals("") || code.equals("null")) {
					name = "-";
				}
				tv_more_chioce_kuang_address2.setText(name);
				Log.e("", "address name : " + name + ", code : " + code);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			if (map.get("nonEmpty").equals("false")) {

				viewHolder.iv_more_chioce_kuang.setVisibility(View.VISIBLE);
			}

		} else if (map.get("controlType").equals("DROPDOWN_LIST")) {

			viewHolder.ll_bdlr_shurukuang.setVisibility(View.GONE);
			viewHolder.ll_more_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_springs_chioce_kuang.setVisibility(View.VISIBLE);
			viewHolder.ll_bdlr_time_chioce_kuang.setVisibility(View.GONE);

			viewHolder.tv_bdlr_springs_chioce_kuang.setText((String) datas.get(
					position).get("name"));
			final LinearLayout ll_bdlr_springs_chioce_kuang_fenlei = viewHolder.ll_bdlr_springs_chioce_kuang_fenlei;
			final TextView tv_bdlr_springs_chioce_kuang_fenlei = viewHolder.tv_bdlr_springs_chioce_kuang_fenlei;
			try {
				String text = (String) datas.get(position).get("value");
				if (text == null || text.equals("") || text.equals("null")) {
					text = "-";
				}
				tv_bdlr_springs_chioce_kuang_fenlei.setText(text);
				Log.e("", "" + (String) map.get("value"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			if (map.get("nonEmpty").equals("false")) {
				viewHolder.iv_bdlr_springs_chioce_kuang
						.setVisibility(View.VISIBLE);
			}

		} else if (map.get("controlType").equals("DATEFIELD")) {

			viewHolder.ll_bdlr_shurukuang.setVisibility(View.GONE);
			viewHolder.ll_more_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_springs_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_time_chioce_kuang.setVisibility(View.VISIBLE);

			viewHolder.tv_bdlr_time_chioce_kuang.setText((String) datas.get(
					position).get("name"));
			try {
				String text = (String) map.get("value");
				if (text == null || text.equals("") || text.equals("null")) {
					text = "-";
				}
				viewHolder.tv_bdlr_time_chioce_kuang_shenqingriqi.setText(text);
				Log.e("", "" + (String) map.get("value"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			final TextView tv_bdlr_time_chioce_kuang_shenqingriqi = viewHolder.tv_bdlr_time_chioce_kuang_shenqingriqi;
			final android.content.DialogInterface.OnClickListener clickListener = new android.content.DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO 自动生成的方法存根
					// map.put(
					// "value",
					// tv_bdlr_time_chioce_kuang_shenqingriqi.getText()
					// .toString());
				}
			};

			if (map.get("nonEmpty").equals("false")) {
				viewHolder.iv_bdlr_time_chioce_kuang
						.setVisibility(View.VISIBLE);
			}

		}
		return item;
	}

	private View getItemView() {
		ViewHolder viewHolder = new ViewHolder();
		View view = LayoutInflater.from(context).inflate(
				R.layout.bdlr_luru_item, null);
		viewHolder.edt_bdlr_shurukuang = (EditText) view
				.findViewById(R.id.edt_bdlr_shurukuang);
		if (!editable) {
			viewHolder.edt_bdlr_shurukuang.setEnabled(false);
		}
		viewHolder.iv_bdlr_shurukuang = (ImageView) view
				.findViewById(R.id.iv_bdlr_shurukuang);
		viewHolder.iv_bdlr_springs_chioce_kuang = (ImageView) view
				.findViewById(R.id.iv_bdlr_springs_chioce_kuang);
		viewHolder.iv_bdlr_time_chioce_kuang = (ImageView) view
				.findViewById(R.id.iv_bdlr_time_chioce_kuang);
		viewHolder.iv_more_chioce_kuang = (ImageView) view
				.findViewById(R.id.iv_more_chioce_kuang);
		viewHolder.ll_bdlr_shurukuang = (LinearLayout) view
				.findViewById(R.id.ll_bdlr_shurukuang);
		viewHolder.ll_bdlr_springs_chioce_kuang = (LinearLayout) view
				.findViewById(R.id.ll_bdlr_springs_chioce_kuang);
		viewHolder.ll_bdlr_springs_chioce_kuang_fenlei = (LinearLayout) view
				.findViewById(R.id.ll_bdlr_springs_chioce_kuang_fenlei);
		viewHolder.ll_bdlr_time_chioce_kuang = (LinearLayout) view
				.findViewById(R.id.ll_bdlr_time_chioce_kuang);
		viewHolder.ll_bdlr_time_chioce_kuang_shenqingriqi = (LinearLayout) view
				.findViewById(R.id.ll_bdlr_time_chioce_kuang_shenqingriqi);
		viewHolder.ll_more_chioce_kuang = (LinearLayout) view
				.findViewById(R.id.ll_more_chioce_kuang);
		viewHolder.ll_more_chioce_kuang_address = (LinearLayout) view
				.findViewById(R.id.ll_more_chioce_kuang_address);
		viewHolder.ll_more_chioce_kuang_address2 = (LinearLayout) view
				.findViewById(R.id.ll_more_chioce_kuang_address2);
		viewHolder.ll_addr_gone = (LinearLayout) view
				.findViewById(R.id.ll_addr_gone);
		viewHolder.tv_bdlr_shurukuang = (TextView) view
				.findViewById(R.id.tv_bdlr_shurukuang);
		viewHolder.tv_bdlr_springs_chioce_kuang = (TextView) view
				.findViewById(R.id.tv_bdlr_springs_chioce_kuang);
		viewHolder.tv_bdlr_springs_chioce_kuang_fenlei = (TextView) view
				.findViewById(R.id.tv_bdlr_springs_chioce_kuang_fenlei);
		viewHolder.tv_bdlr_time_chioce_kuang = (TextView) view
				.findViewById(R.id.tv_bdlr_time_chioce_kuang);
		viewHolder.tv_bdlr_time_chioce_kuang_shenqingriqi = (TextView) view
				.findViewById(R.id.tv_bdlr_time_chioce_kuang_shenqingriqi);
		viewHolder.tv_more_chioce_kuang = (TextView) view
				.findViewById(R.id.tv_more_chioce_kuang);
		viewHolder.tv_more_chioce_kuang_address = (TextView) view
				.findViewById(R.id.tv_more_chioce_kuang_address);
		viewHolder.tv_more_chioce_kuang_address2 = (TextView) view
				.findViewById(R.id.tv_more_chioce_kuang_address2);
		view.setTag(viewHolder);
		return view;
	}
}

class ViewHolder {
	LinearLayout ll_bdlr_shurukuang, ll_more_chioce_kuang,
			ll_more_chioce_kuang_address, ll_more_chioce_kuang_address2,
			ll_bdlr_springs_chioce_kuang, ll_bdlr_springs_chioce_kuang_fenlei,
			ll_bdlr_time_chioce_kuang, ll_bdlr_time_chioce_kuang_shenqingriqi,
			ll_addr_gone;
	TextView tv_bdlr_shurukuang, tv_more_chioce_kuang,
			tv_more_chioce_kuang_address, tv_more_chioce_kuang_address2,
			tv_bdlr_springs_chioce_kuang, tv_bdlr_springs_chioce_kuang_fenlei,
			tv_bdlr_time_chioce_kuang, tv_bdlr_time_chioce_kuang_shenqingriqi;
	ImageView iv_bdlr_shurukuang, iv_more_chioce_kuang,
			iv_bdlr_springs_chioce_kuang, iv_bdlr_time_chioce_kuang;
	EditText edt_bdlr_shurukuang;

	public void reset() {
		tv_bdlr_shurukuang.setText("");
		tv_more_chioce_kuang.setText("");
		tv_more_chioce_kuang_address.setText("");
		tv_more_chioce_kuang_address2.setText("");
		tv_bdlr_springs_chioce_kuang.setText("");
		tv_bdlr_springs_chioce_kuang_fenlei.setText("");
		tv_bdlr_time_chioce_kuang.setText("");
		tv_bdlr_time_chioce_kuang_shenqingriqi.setText("");
		edt_bdlr_shurukuang.setText("");
	}
}
