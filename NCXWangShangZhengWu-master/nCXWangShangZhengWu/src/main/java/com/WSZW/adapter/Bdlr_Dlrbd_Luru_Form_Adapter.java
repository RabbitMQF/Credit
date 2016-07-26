package com.WSZW.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.WSZW.Activity.R;
import com.WSZW.util.BdlrAddressHandle;
import com.WSZW.util.DatePickDialog;
import com.WSZW.util.StringUtil;
import com.WSZW.view.MyPopupWindow;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable.Callback;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Bdlr_Dlrbd_Luru_Form_Adapter extends BaseAdapter {

	public interface MyCallBack {
		public void callback(String s, String key, int position);
	}

	private LayoutInflater inflater;
	private Context context = null;
	private List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();

	MyCallBack callBack;
	private String str_shenqingrenName = null;
	private String str_shenqingrenID = null;

	private Bdlr_Dlrbd_Luru_Form_Adapter(Context context,
			List<Map<String, Object>> datas, String str_name, String str_id) {
		this.datas = datas;
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.str_shenqingrenName = str_name;
		this.str_shenqingrenID = str_id;
	}

	public Bdlr_Dlrbd_Luru_Form_Adapter(Context context,
			List<Map<String, Object>> datas, MyCallBack callback) {
		this.datas.clear();
		this.datas.addAll(datas);
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.callBack = callback;

	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.bdlr_luru_item, null);
			viewHolder.edt_bdlr_shurukuang = (EditText) convertView
					.findViewById(R.id.edt_bdlr_shurukuang);
			viewHolder.iv_bdlr_shurukuang = (ImageView) convertView
					.findViewById(R.id.iv_bdlr_shurukuang);
			viewHolder.iv_bdlr_springs_chioce_kuang = (ImageView) convertView
					.findViewById(R.id.iv_bdlr_springs_chioce_kuang);
			viewHolder.iv_bdlr_time_chioce_kuang = (ImageView) convertView
					.findViewById(R.id.iv_bdlr_time_chioce_kuang);
			viewHolder.iv_more_chioce_kuang = (ImageView) convertView
					.findViewById(R.id.iv_more_chioce_kuang);
			viewHolder.ll_bdlr_shurukuang = (LinearLayout) convertView
					.findViewById(R.id.ll_bdlr_shurukuang);
			viewHolder.ll_bdlr_springs_chioce_kuang = (LinearLayout) convertView
					.findViewById(R.id.ll_bdlr_springs_chioce_kuang);
			viewHolder.ll_bdlr_springs_chioce_kuang_fenlei = (LinearLayout) convertView
					.findViewById(R.id.ll_bdlr_springs_chioce_kuang_fenlei);
			viewHolder.ll_bdlr_time_chioce_kuang = (LinearLayout) convertView
					.findViewById(R.id.ll_bdlr_time_chioce_kuang);
			viewHolder.ll_bdlr_time_chioce_kuang_shenqingriqi = (LinearLayout) convertView
					.findViewById(R.id.ll_bdlr_time_chioce_kuang_shenqingriqi);
			viewHolder.ll_more_chioce_kuang = (LinearLayout) convertView
					.findViewById(R.id.ll_more_chioce_kuang);
			viewHolder.ll_more_chioce_kuang_address = (LinearLayout) convertView
					.findViewById(R.id.ll_more_chioce_kuang_address);
			viewHolder.ll_more_chioce_kuang_address2 = (LinearLayout) convertView
					.findViewById(R.id.ll_more_chioce_kuang_address2);
			viewHolder.tv_bdlr_shurukuang = (TextView) convertView
					.findViewById(R.id.tv_bdlr_shurukuang);
			viewHolder.tv_bdlr_springs_chioce_kuang = (TextView) convertView
					.findViewById(R.id.tv_bdlr_springs_chioce_kuang);
			viewHolder.tv_bdlr_springs_chioce_kuang_fenlei = (TextView) convertView
					.findViewById(R.id.tv_bdlr_springs_chioce_kuang_fenlei);
			viewHolder.tv_bdlr_time_chioce_kuang = (TextView) convertView
					.findViewById(R.id.tv_bdlr_time_chioce_kuang);
			viewHolder.tv_bdlr_time_chioce_kuang_shenqingriqi = (TextView) convertView
					.findViewById(R.id.tv_bdlr_time_chioce_kuang_shenqingriqi);
			viewHolder.tv_more_chioce_kuang = (TextView) convertView
					.findViewById(R.id.tv_more_chioce_kuang);
			viewHolder.tv_more_chioce_kuang_address = (TextView) convertView
					.findViewById(R.id.tv_more_chioce_kuang_address);
			viewHolder.tv_more_chioce_kuang_address2 = (TextView) convertView
					.findViewById(R.id.tv_more_chioce_kuang_address2);

			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			// viewHolder.reset();
		}
		if (datas.get(position).get("controlType").equals("TEXTBOX")) {

			viewHolder.ll_bdlr_shurukuang.setVisibility(View.VISIBLE);
			viewHolder.ll_more_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_springs_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_time_chioce_kuang.setVisibility(View.GONE);

			viewHolder.tv_bdlr_shurukuang.setText((String) datas.get(position)
					.get("name"));
			viewHolder.edt_bdlr_shurukuang.setFocusable(true);
			viewHolder.edt_bdlr_shurukuang.setFocusableInTouchMode(true);
			final EditText edt_bdlr_shurukuang = viewHolder.edt_bdlr_shurukuang;
			try {
				edt_bdlr_shurukuang.setText(datas.get(position).get("value")
						.toString());
				Log.e("", "inset position : " + position + " content : "
						+ datas.get(position).get("value").toString());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			viewHolder.edt_bdlr_shurukuang
					.setOnFocusChangeListener(new OnFocusChangeListener() {

						@Override
						public void onFocusChange(View arg0, boolean arg1) {
							// TODO 自动生成的方法存根
							if (!arg1) {
								Log.e("", "aaa " + position);
								// datas.get(position).put("value",
								// edt_bdlr_shurukuang.getText().toString());
								callBack.callback(edt_bdlr_shurukuang.getText()
										.toString(), "value", position);
							} else {
								Log.e("", "bbb " + position);
							}
						}
					});
			if (datas.get(position).get("name").toString().equals("申请人")
					|| datas.get(position).get("name").toString().equals("姓名")
					|| datas.get(position).get("name").toString()
							.equals("申请人姓名")) {
				edt_bdlr_shurukuang.setText(str_shenqingrenName);
			}
			if (datas.get(position).get("name").toString().contains("身份证号")) {
				edt_bdlr_shurukuang.setText(str_shenqingrenID);
			}
			if (datas.get(position).get("nonEmpty").equals("false")) {
				viewHolder.iv_bdlr_shurukuang.setVisibility(View.VISIBLE);
			}

		} else if (datas.get(position).get("controlType").equals("ADDRESS")) {

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
				Log.e("", "" + (String) datas.get(position).get("codeAText"));
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
									// datas.get(position).put("codeAText",
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
				Log.e("", "" + (String) datas.get(position).get("codeBText"));
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
									// datas.get(position).put("code",
									// list1.get(position).get("code"));
									// datas.get(position).put(
									// "codeBText",
									// (String) list1.get(position).get(
									// "name"));
									callBack.callback((String) list1.get(p)
											.get("code"), "code", position);
									callBack.callback((String) list1.get(p)
											.get("name"), "codeBText", position);
									// Toast.makeText(context,
									// (String)list.get(position).get("code"),
									// Toast.LENGTH_LONG).show();
								}
							};
						}
					});

			if (datas.get(position).get("nonEmpty").equals("false")) {

				viewHolder.iv_more_chioce_kuang.setVisibility(View.VISIBLE);
			}

		} else if (datas.get(position).get("controlType")
				.equals("DROPDOWN_LIST")) {

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
				Log.e("", "" + (String) datas.get(position).get("value"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			viewHolder.ll_bdlr_springs_chioce_kuang
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							List<String> list = new ArrayList<String>();
							String str = (String) datas.get(position).get(
									"inputControlValueScope");
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
									// datas.get(position).put("value",
									// list1.get(position));
									callBack.callback(list1.get(p), "value",
											position);
								}
							};
						}
					});

			if (datas.get(position).get("nonEmpty").equals("false")) {
				viewHolder.iv_bdlr_springs_chioce_kuang
						.setVisibility(View.VISIBLE);
			}

		} else if (datas.get(position).get("controlType").equals("DATEFIELD")) {

			viewHolder.ll_bdlr_shurukuang.setVisibility(View.GONE);
			viewHolder.ll_more_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_springs_chioce_kuang.setVisibility(View.GONE);
			viewHolder.ll_bdlr_time_chioce_kuang.setVisibility(View.VISIBLE);

			viewHolder.tv_bdlr_time_chioce_kuang.setText((String) datas.get(
					position).get("name"));
			try {
				viewHolder.tv_bdlr_time_chioce_kuang_shenqingriqi
						.setText((String) datas.get(position).get("value"));
				Log.e("", "" + (String) datas.get(position).get("value"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			final TextView tv_bdlr_time_chioce_kuang_shenqingriqi = viewHolder.tv_bdlr_time_chioce_kuang_shenqingriqi;
			final android.content.DialogInterface.OnClickListener clickListener = new android.content.DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO 自动生成的方法存根
					// datas.get(position).put(
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

			if (datas.get(position).get("nonEmpty").equals("false")) {
				viewHolder.iv_bdlr_time_chioce_kuang
						.setVisibility(View.VISIBLE);
			}

		}
		return convertView;
	}

	class ViewHolder {
		LinearLayout ll_bdlr_shurukuang, ll_more_chioce_kuang,
				ll_more_chioce_kuang_address, ll_more_chioce_kuang_address2,
				ll_bdlr_springs_chioce_kuang,
				ll_bdlr_springs_chioce_kuang_fenlei, ll_bdlr_time_chioce_kuang,
				ll_bdlr_time_chioce_kuang_shenqingriqi;
		TextView tv_bdlr_shurukuang, tv_more_chioce_kuang,
				tv_more_chioce_kuang_address, tv_more_chioce_kuang_address2,
				tv_bdlr_springs_chioce_kuang,
				tv_bdlr_springs_chioce_kuang_fenlei, tv_bdlr_time_chioce_kuang,
				tv_bdlr_time_chioce_kuang_shenqingriqi;
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

	public List<Map<String, Object>> getHisInspectRecords() {
		return datas;
	}

	public void setNotifications(List<Map<String, Object>> datas) {
		this.datas = datas;
	}
}
