package com.WSZW.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xmlpull.v1.XmlPullParserException;

import com.WSZW.data.Constants;
import com.WSZW.entity.HandleResult;
import com.WSZW.entity.Zwfw_main_listview_item;
import com.WSZW.entity.Zwfw_zhuti_bean;
import com.WSZW.util.BaseAsyncTask;
import com.WSZW.util.StringUtil;

import android.content.Context;
import android.widget.Toast;

/**
 * 获取政务服务主题服务
 * 
 * @author Administrator
 * 
 */
public abstract class ZWFW_Zhuti_ManagerService {
	public static final String TAG = "ZWFW_Zhuti_ManagerService";

	/**
	 * 获取个人办事，企业办事主题
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list传入的account
	 */
	public void getZWFW_zhuti(final Context paramActivity, final int paramInt,
			final List<Map<String, Object>> list) {
		final String url = paramActivity.getResources().getString(
				com.WSZW.Activity.R.string.endpoint);
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, false) {
			String result1 = null; // 从服务器获取到的数据
			String result2 = null;
			String result3 = null;
			String result4 = null;
			String result5 = null;
			String result6 = null;
			List<String> resultGroup1 = new ArrayList<String>();// 将服务器获取到的数据转化为此列表
			List<String> resultGroup2 = new ArrayList<String>();
			List<String> resultGroup3 = new ArrayList<String>();
			List<String> resultGroup4 = new ArrayList<String>();
			List<String> resultGroup5 = new ArrayList<String>();
			List<String> resultGroup6 = new ArrayList<String>();
			List<Zwfw_zhuti_bean> list1 = new ArrayList<Zwfw_zhuti_bean>();
			List<Zwfw_zhuti_bean> list2 = new ArrayList<Zwfw_zhuti_bean>();
			List<Zwfw_zhuti_bean> list3 = new ArrayList<Zwfw_zhuti_bean>();
			List<Zwfw_zhuti_bean> list4 = new ArrayList<Zwfw_zhuti_bean>();
			List<Zwfw_zhuti_bean> list5 = new ArrayList<Zwfw_zhuti_bean>();
			List<Zwfw_zhuti_bean> list6 = new ArrayList<Zwfw_zhuti_bean>();

			List<Zwfw_main_listview_item> list_1 = new ArrayList<Zwfw_main_listview_item>();
			List<Zwfw_main_listview_item> list_2 = new ArrayList<Zwfw_main_listview_item>();
			List<Zwfw_main_listview_item> list_3 = new ArrayList<Zwfw_main_listview_item>();
			List<Zwfw_main_listview_item> list_4 = new ArrayList<Zwfw_main_listview_item>();
			List<Zwfw_main_listview_item> list_5 = new ArrayList<Zwfw_main_listview_item>();
			List<Zwfw_main_listview_item> list_6 = new ArrayList<Zwfw_main_listview_item>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result1 = downloadDB("grbs", url,
							(String) list.get(0).get("code1"));
					result2 = downloadDB("grbs", url,
							(String) list.get(0).get("code2"));
					result3 = downloadDB("grbs", url,
							(String) list.get(0).get("code3"));
					result4 = downloadDB("grbs", url,
							(String) list.get(0).get("code4"));
					result5 = downloadDB("grbs", url,
							(String) list.get(0).get("code5"));
					result6 = downloadDB("grbs", url,
							(String) list.get(0).get("code6"));
					resultGroup1 = StringUtil.stringToNormalList(result1);
					resultGroup2 = StringUtil.stringToNormalList(result2);
					resultGroup3 = StringUtil.stringToNormalList(result3);
					resultGroup4 = StringUtil.stringToNormalList(result4);
					resultGroup5 = StringUtil.stringToNormalList(result5);
					resultGroup6 = StringUtil.stringToNormalList(result6);
					
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result1 == null || resultGroup1 == null || result1.equals("error")
						|| result2 == null || resultGroup2 == null || result2.equals("error")
						|| result3 == null ||resultGroup3 == null || result3.equals("error")
						|| result4 == null ||resultGroup4 == null || result4.equals("error")
						|| result5 == null ||resultGroup5 == null || result5.equals("error")
						|| result6 == null ||resultGroup6 == null || result6.equals("error")) {
					
					return Integer.valueOf(-1);
				}
				
				return Integer.valueOf(1);
			}

			private void initListDatas() {
				for (int i = 0; i < resultGroup1.size(); i++) {
					Zwfw_zhuti_bean zhuti = new Zwfw_zhuti_bean();
					String[] imgs = resultGroup1.get(i).split("=");
					zhuti.setName(imgs[0]);
					zhuti.setPic_name(imgs[1]);
					zhuti.setId(imgs[2]);
					list1.add(zhuti);
				}
				for (int i = 0; i < resultGroup2.size(); i++) {
					Zwfw_zhuti_bean zhuti = new Zwfw_zhuti_bean();
					String[] imgs = resultGroup2.get(i).split("=");
					zhuti.setName(imgs[0]);
					zhuti.setPic_name(imgs[1]);
					zhuti.setId(imgs[2]);
					list2.add(zhuti);
				}
				for (int i = 0; i < resultGroup3.size(); i++) {
					Zwfw_zhuti_bean zhuti = new Zwfw_zhuti_bean();
					String[] imgs = resultGroup3.get(i).split("=");
					zhuti.setName(imgs[0]);
					zhuti.setPic_name(imgs[1]);
					zhuti.setId(imgs[2]);
					list3.add(zhuti);
				}
				for (int i = 0; i < resultGroup4.size(); i++) {
					Zwfw_zhuti_bean zhuti = new Zwfw_zhuti_bean();
					String[] imgs = resultGroup4.get(i).split("=");
					zhuti.setName(imgs[0]);
					zhuti.setPic_name(imgs[1]);
					zhuti.setId(imgs[2]);
					list4.add(zhuti);
				}
				for (int i = 0; i < resultGroup5.size(); i++) {
					Zwfw_zhuti_bean zhuti = new Zwfw_zhuti_bean();
					String[] imgs = resultGroup5.get(i).split("=");
					zhuti.setName(imgs[0]);
					zhuti.setPic_name(imgs[1]);
					zhuti.setId(imgs[2]);
					list5.add(zhuti);
				}
				for (int i = 0; i < resultGroup6.size(); i++) {
					Zwfw_zhuti_bean zhuti = new Zwfw_zhuti_bean();
					String[] imgs = resultGroup6.get(i).split("=");
					zhuti.setName(imgs[0]);
					zhuti.setPic_name(imgs[1]);
					zhuti.setId(imgs[2]);
					list6.add(zhuti);
				}
				initConstangsList1();
				initConstangsList2();
				initConstangsList3();
				initConstangsList4();
				initConstangsList5();
				initConstangsList6();
			}

			private void initConstangsList1() {
				int m = list1.size();
				int a = 0;
				while (a < m) {
					Zwfw_main_listview_item listview_item = new Zwfw_main_listview_item();
					if (a + 3 < m) {
						listview_item.setId_4(list1.get(a + 3).getId());
						listview_item.setName_4(list1.get(a + 3).getName());
						listview_item.setPic_name_4(list1.get(a + 3)
								.getPic_name());
					}

					if (a + 2 < m) {
						listview_item.setId_3(list1.get(a + 2).getId());
						listview_item.setName_3(list1.get(a + 2).getName());
						listview_item.setPic_name_3(list1.get(a + 2)
								.getPic_name());
					}
					if (a + 1 < m) {
						listview_item.setId_2(list1.get(a + 1).getId());
						listview_item.setName_2(list1.get(a + 1).getName());
						listview_item.setPic_name_2(list1.get(a + 1)
								.getPic_name());
					}
					listview_item.setId_1(list1.get(a).getId());
					listview_item.setName_1(list1.get(a).getName());
					listview_item.setPic_name_1(list1.get(a).getPic_name());
					list_1.add(listview_item);
					a = a + 4;
				}
				Constants.list_zwfw_zhuti_gr_ztfl = list_1;
				// for(int i=list_1.size()-1;i>=0;i--){
				// Constants.list_zwfw_zhuti_gr_ztfl.add(list_1.get(i));
				// }
			}

			private void initConstangsList2() {
				int m = list2.size();
				int a = 0;
				while (a < m) {
					Zwfw_main_listview_item listview_item = new Zwfw_main_listview_item();
					if (a + 3 < m) {
						listview_item.setId_4(list2.get(a + 3).getId());
						listview_item.setName_4(list2.get(a + 3).getName());
						listview_item.setPic_name_4(list2.get(a + 3)
								.getPic_name());
					}

					if (a + 2 < m) {
						listview_item.setId_3(list2.get(a + 2).getId());
						listview_item.setName_3(list2.get(a + 2).getName());
						listview_item.setPic_name_3(list2.get(a + 2)
								.getPic_name());
					}
					if (a + 1 < m) {
						listview_item.setId_2(list2.get(a + 1).getId());
						listview_item.setName_2(list2.get(a + 1).getName());
						listview_item.setPic_name_2(list2.get(a + 1)
								.getPic_name());
					}
					listview_item.setId_1(list2.get(a).getId());
					listview_item.setName_1(list2.get(a).getName());
					listview_item.setPic_name_1(list2.get(a).getPic_name());
					list_2.add(listview_item);
					a = a + 4;
				}
				Constants.list_zwfw_zhuti_gr_rssj = list_2;
				// for(int i=list_1.size()-1;i>=0;i--){
				// Constants.list_zwfw_zhuti_gr_ztfl.add(list_1.get(i));
				// }
			}

			private void initConstangsList3() {
				int m = list3.size();
				int a = 0;
				while (a < m) {
					Zwfw_main_listview_item listview_item = new Zwfw_main_listview_item();
					if (a + 3 < m) {
						listview_item.setId_4(list3.get(a + 3).getId());
						listview_item.setName_4(list3.get(a + 3).getName());
						listview_item.setPic_name_4(list3.get(a + 3)
								.getPic_name());
					}

					if (a + 2 < m) {
						listview_item.setId_3(list3.get(a + 2).getId());
						listview_item.setName_3(list3.get(a + 2).getName());
						listview_item.setPic_name_3(list3.get(a + 2)
								.getPic_name());
					}
					if (a + 1 < m) {
						listview_item.setId_2(list3.get(a + 1).getId());
						listview_item.setName_2(list3.get(a + 1).getName());
						listview_item.setPic_name_2(list3.get(a + 1)
								.getPic_name());
					}
					listview_item.setId_1(list3.get(a).getId());
					listview_item.setName_1(list3.get(a).getName());
					listview_item.setPic_name_1(list3.get(a).getPic_name());
					list_3.add(listview_item);
					a = a + 4;
				}
				Constants.list_zwfw_zhuti_gr_tdqt = list_3;
				// for(int i=list_1.size()-1;i>=0;i--){
				// Constants.list_zwfw_zhuti_gr_ztfl.add(list_1.get(i));
				// }
			}

			private void initConstangsList4() {
				int m = list4.size();
				int a = 0;
				while (a < m) {
					Zwfw_main_listview_item listview_item = new Zwfw_main_listview_item();
					if (a + 3 < m) {
						listview_item.setId_4(list4.get(a + 3).getId());
						listview_item.setName_4(list4.get(a + 3).getName());
						listview_item.setPic_name_4(list4.get(a + 3)
								.getPic_name());
					}

					if (a + 2 < m) {
						listview_item.setId_3(list4.get(a + 2).getId());
						listview_item.setName_3(list4.get(a + 2).getName());
						listview_item.setPic_name_3(list4.get(a + 2)
								.getPic_name());
					}
					if (a + 1 < m) {
						listview_item.setId_2(list4.get(a + 1).getId());
						listview_item.setName_2(list4.get(a + 1).getName());
						listview_item.setPic_name_2(list4.get(a + 1)
								.getPic_name());
					}
					listview_item.setId_1(list4.get(a).getId());
					listview_item.setName_1(list4.get(a).getName());
					listview_item.setPic_name_1(list4.get(a).getPic_name());
					list_4.add(listview_item);
					a = a + 4;
				}
				Constants.list_zwfw_zhuti_qy_ztfl = list_4;
				// for(int i=list_1.size()-1;i>=0;i--){
				// Constants.list_zwfw_zhuti_gr_ztfl.add(list_1.get(i));
				// }
			}

			private void initConstangsList5() {
				int m = list5.size();
				int a = 0;
				while (a < m) {
					Zwfw_main_listview_item listview_item = new Zwfw_main_listview_item();
					if (a + 3 < m) {
						listview_item.setId_4(list5.get(a + 3).getId());
						listview_item.setName_4(list5.get(a + 3).getName());
						listview_item.setPic_name_4(list5.get(a + 3)
								.getPic_name());
					}

					if (a + 2 < m) {
						listview_item.setId_3(list5.get(a + 2).getId());
						listview_item.setName_3(list5.get(a + 2).getName());
						listview_item.setPic_name_3(list5.get(a + 2)
								.getPic_name());
					}
					if (a + 1 < m) {
						listview_item.setId_2(list5.get(a + 1).getId());
						listview_item.setName_2(list5.get(a + 1).getName());
						listview_item.setPic_name_2(list5.get(a + 1)
								.getPic_name());
					}
					listview_item.setId_1(list5.get(a).getId());
					listview_item.setName_1(list5.get(a).getName());
					listview_item.setPic_name_1(list5.get(a).getPic_name());
					list_5.add(listview_item);
					a = a + 4;
				}
				Constants.list_zwfw_zhuti_qy_jyhd = list_5;
				// for(int i=list_1.size()-1;i>=0;i--){
				// Constants.list_zwfw_zhuti_gr_ztfl.add(list_1.get(i));
				// }
			}

			private void initConstangsList6() {
				int m = list6.size();
				int a = 0;
				while (a < m) {
					Zwfw_main_listview_item listview_item = new Zwfw_main_listview_item();
					if (a + 3 < m) {
						listview_item.setId_4(list6.get(a + 3).getId());
						listview_item.setName_4(list6.get(a + 3).getName());
						listview_item.setPic_name_4(list6.get(a + 3)
								.getPic_name());
					}

					if (a + 2 < m) {
						listview_item.setId_3(list6.get(a + 2).getId());
						listview_item.setName_3(list6.get(a + 2).getName());
						listview_item.setPic_name_3(list6.get(a + 2)
								.getPic_name());
					}
					if (a + 1 < m) {
						listview_item.setId_2(list6.get(a + 1).getId());
						listview_item.setName_2(list6.get(a + 1).getName());
						listview_item.setPic_name_2(list6.get(a + 1)
								.getPic_name());
					}
					listview_item.setId_1(list6.get(a).getId());
					listview_item.setName_1(list6.get(a).getName());
					listview_item.setPic_name_1(list6.get(a).getPic_name());
					list_6.add(listview_item);
					a = a + 4;
				}
				Constants.list_zwfw_zhuti_qy_tddx = list_6;
				// for(int i=list_1.size()-1;i>=0;i--){
				// Constants.list_zwfw_zhuti_gr_ztfl.add(list_1.get(i));
				// }
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					initListDatas();
					handleResult.setiSuccess("success");
				} else if (paramInteger == -1) {// 链接服务器失败
					handleResult.setiSuccess("fail");
					Toast.makeText(paramActivity, "链接服务器失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				ZWFW_Zhuti_ManagerService.this.handlerLoginInfo(paramActivity,
						handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	public abstract void handlerLoginInfo(Context context,
			HandleResult paramHandleResult, int paramInt);
}
