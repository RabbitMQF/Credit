package com.WSZW.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import com.WSZW.Activity.R;
import com.WSZW.data.Constants;
import com.WSZW.entity.Detail_Ffjl_bean;
import com.WSZW.entity.Detail_Sjdj_bean;
import com.WSZW.entity.Detail_Wxts_bean;
import com.WSZW.entity.HandleResult;
import com.WSZW.util.BaseAsyncTask;
import com.WSZW.util.XmlUtil;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public abstract class BaseDetail_ManagerService {
	public static final String TAG = "BaseDetail_ManagerService";

	/**
	 * 获取服务端版本信息
	 * 
	 * @param parmasActivity
	 *            操作的activity对象
	 * @param parmasInt
	 *            指定回调的操作
	 * @param oList
	 *            入参
	 */
	public void getShowDetails(final Context parmasActivity,
			final int parmasInt, final List<Map<String, Object>> oList) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl";// 调用的接口地址

		BaseAsyncTask loacl = new BaseAsyncTask(parmasActivity, true) {
			String result_tab = "";
			String result_jbxx = "";
			String result_jbxx_ysl = "";
			String result_ywbd = "";
			String result_sjdj_dzcl = "";
			String result_bzxx = "";
			String result_ztxx = "";
			String result_ffjl = "";
			String result_wxts = "";
			String result_wsbd = "";
			String result_fjck = "";
			boolean flag = false;
			boolean down_jbxx = false, down_jbxx_ysl = false,
					down_ywbd = false, down_sjdj_dzcl = false,
					down_bzxx = false, down_ztxx = false, down_wxts = false,
					down_ffjl = false, down_fjck = false;
			// List<Map<String, Object>> resultGroup=new
			// ArrayList<Map<String,Object>>();
			List<Map<String, Object>> resultGroup_tab = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultGroup_jbxx = new ArrayList<Map<String, Object>>();
			Detail_Sjdj_bean resultGroup_jbxx_ysl = new Detail_Sjdj_bean();
			List<Map<String, Object>> resultGroup_ywbd = new ArrayList<Map<String, Object>>();
			Detail_Sjdj_bean resultGroup_sjdj_dzcl = new Detail_Sjdj_bean();
			List<Map<String, Object>> resultGroup_bzxx = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultGroup_ztxx = new ArrayList<Map<String, Object>>();
			Detail_Ffjl_bean resultGroup_ffjl = new Detail_Ffjl_bean();
			Detail_Wxts_bean resultGroup_wxts = new Detail_Wxts_bean();
			List<Map<String, Object>> resultGroup_wsbd = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> resultGroup_fjck = new ArrayList<Map<String, Object>>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				try {
					if (Constants.title.equals(parmasActivity.getResources()
							.getString(R.string.yushouliyewu))) {
						result_jbxx_ysl = downloadDB2(
								"getYslReceiptByBizArchivesId", url, oList); // 基本信息
						result_wsbd = downloadDB2("getBizArchivesOnlineForm",
								url, oList);
						result_fjck = downloadDB2(
								"getBizAttachmentByBizArchivesId", url, oList);
						down_jbxx_ysl = true;
					} else {
						result_tab = downloadDB2("getShowTabs", url, oList);// 第一个参数接口的方法名，第二个参数接口地址，第三个参数入参信息
						if (!result_tab.equals("error")) {
							flag = true;
							resultGroup_tab = XmlUtil
									.getSubject_Detail_TAB(result_tab);
						}
					}
					if (resultGroup_tab != null) {

						for (Map<String, Object> map : resultGroup_tab) {
							if (map.get("name").equals("基本信息")) {
								if (map.get("isShow").equals("true")) {
									down_jbxx = true;
								}
							} else if (map.get("name").equals("收件登记")) {
								if (map.get("isShow").equals("true")) {
									down_sjdj_dzcl = true;
								}
							} else if (map.get("name").equals("业务表单")) {
								if (map.get("isShow").equals("true")) {
									down_ywbd = true;
								}
							} else if (map.get("name").equals("电子材料")) {
								if (map.get("isShow").equals("true")) {
									down_sjdj_dzcl = true;
								}
							} else if (map.get("name").equals("暂停信息")) {
								if (map.get("isShow").equals("true")) {
									down_ztxx = true;
								}
							} else if (map.get("name").equals("补正信息")) {
								if (map.get("isShow").equals("true")) {
									down_bzxx = true;
								}
							} else if (map.get("name").equals("发放记录")) {
								if (map.get("isShow").equals("true")) {
									down_ffjl = true;
								}
							} else if (map.get("name").equals("温馨提示")) {
								if (map.get("isShow").equals("true")) {
									down_wxts = true;
								}
							} else if (map.get("name").equals("附件")) {
								if (map.get("isShow").equals("true")) {
									down_fjck = true;
								}
							}
						}
					}
					if (down_jbxx) {
						result_jbxx = downloadDB2("getBasicInfo", url, oList); // 基本信息
						Log.e("downloadDB", "result_jbxx");
					}
					if (down_ywbd) {
						result_ywbd = downloadDB2("getBizArchivesForm", url,
								oList); // 业务表单
						Log.e("downloadDB", "result_ywbd");
					}
					if (down_sjdj_dzcl) {
						result_sjdj_dzcl = downloadDB2("getBizReceiptInfo", url,
								oList);// 收件登记和电子材料
						Log.e("downloadDB", "result_sjdj_dzcl");
					}
					if (down_bzxx) {
						result_bzxx = downloadDB2("getCorrectInfo", url, oList);
						Log.e("downloadDB", "result_bzxx");
					}
					if (down_ztxx) {
						result_ztxx = downloadDB2("getPauseReasonInfo", url,
								oList);
						Log.e("downloadDB", "result_ztxx");
					}
					if (down_ffjl) {
						result_ffjl = downloadDB2("getDeliveryRecordInfo", url,
								oList);// 发放记录
						Log.e("downloadDB", "result_ffjl");
					}
					if (down_wxts) {
						result_wxts = downloadDB2("getTipInfo", url, oList); // 温馨提示
						Log.e("downloadDB", "result_wxts");
					}
					if (down_fjck) {
						result_fjck = downloadDB2(
								"getBizAttachmentByBizArchivesId", url, oList);
						Log.e("downloadDB", "result_fjck");
					}

					if (!result_jbxx.equals("error") && down_jbxx) {
						flag = true;
						resultGroup_jbxx = XmlUtil
								.getSubject_Detail_JBXX(result_jbxx);
					}
					if (!result_sjdj_dzcl.equals("error") && down_sjdj_dzcl) {
						flag = true;
						resultGroup_sjdj_dzcl = XmlUtil
								.getSubject_Detail_SJDJ_DZCL(result_sjdj_dzcl);
					}
					if (!result_ztxx.equals("error") && down_ztxx) {
						flag = true;
						resultGroup_ztxx = XmlUtil
								.getSubject_Detail_ZTXX(result_ztxx);
					}
					if (!result_bzxx.equals("error") && down_bzxx) {
						flag = true;
						resultGroup_bzxx = XmlUtil
								.getSubject_Detail_BZXX(result_bzxx);
					}
					if (!result_ffjl.equals("error") && down_ffjl) {
						flag = true;
						resultGroup_ffjl = XmlUtil
								.getSubject_Detail_FFJL(result_ffjl);
					}
					if (!result_wxts.equals("error") && down_wxts) {
						flag = true;
						resultGroup_wxts = XmlUtil
								.getSubject_Detail_WXTS(result_wxts);
					}
					if (!result_ywbd.equals("error") && down_ywbd) {
						flag = true;
						resultGroup_ywbd = XmlUtil
								.getSubject_Detail_YWBD(result_ywbd);
					}
					if (!result_fjck.equals("error")
							&& !result_jbxx_ysl.equals("error")
							&& down_jbxx_ysl) {
						flag = true;
						resultGroup_fjck = XmlUtil
								.getSubject_Detail_FJCK(result_fjck);
						resultGroup_jbxx_ysl = XmlUtil
								.getSubject_Detail_JBXX_YSL(result_jbxx_ysl);
						resultGroup_wsbd = XmlUtil
								.getDetail_WSBD_ServiceSubjecrFormXmlParse(result_wsbd);
					}
					if (!result_fjck.equals("error")) {
						flag = true;
						resultGroup_fjck = XmlUtil
								.getSubject_Detail_FJCK(result_fjck);
					}
					// resultGroup=null;//传入result，如果是xml的格式进行pull解析，如果是json进行json解析
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (!flag) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					handleResult.setGetBaseDetailSuccess("success");
					if (resultGroup_tab != null) {
						handleResult.setResultGroup_tab(resultGroup_tab);
					}
					if (resultGroup_jbxx != null) {
						handleResult.setResultGroup_jbxx(resultGroup_jbxx);
					}
					if (resultGroup_sjdj_dzcl != null) {
						handleResult
								.setResultGroup_sjdj_dzcl(resultGroup_sjdj_dzcl);
					}
					if (resultGroup_ztxx != null) {
						handleResult.setResultGroup_ztxx(resultGroup_ztxx);
					}
					if (resultGroup_bzxx != null) {
						handleResult.setResultGroup_bzxx(resultGroup_bzxx);
					}
					if (resultGroup_ffjl != null) {
						handleResult.setResultGroup_ffjl(resultGroup_ffjl);
					}
					if (resultGroup_wxts != null) {
						handleResult.setResultGroup_wxts(resultGroup_wxts);
					}
					if (resultGroup_ywbd != null) {
						handleResult.setResultGroup_ywbd(resultGroup_ywbd);
					}
					if (resultGroup_fjck != null) {
						handleResult.setResultGroup_fjck(resultGroup_fjck);
					}
					if (resultGroup_jbxx_ysl != null) {
						handleResult
								.setResultGroup_jbxx_ysl(resultGroup_jbxx_ysl);
					}
					if (resultGroup_wsbd != null) {
						handleResult.setResultGroup_wsbd(resultGroup_wsbd);
					}
				} else if (paramInteger == -1) {// 链接服务器失败

					Toast.makeText(parmasActivity, "连接服务器失败！",
							Toast.LENGTH_LONG).show();
					return;
				}
				BaseDetail_ManagerService.this.handlerBaseDetailInfo(
						parmasActivity, handleResult, parmasInt);
				super.onPostExecute(paramInteger);
			}
		};
		loacl.execute(1);
	}

	/**
	 * 暂停业务
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list传入的account
	 */
	public void doPauseBizArchives(final Context paramActivity,
			final int paramInt, final List<Map<String, Object>> list) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl";
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;
			List<Map<String, Object>> resultGroup = new ArrayList<Map<String, Object>>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB2("doPauseBizArchives", url, list);
					// resultGroup =
					// XmlUtil.getServiceSubjecrFormXmlParse(result);
				} catch (XmlPullParserException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null || result.equals("error")
						|| !result.equals("success")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					handleResult.setZtxxSuccess("success");
				} else if (paramInteger == -1) {// 链接服务器失败
					handleResult.setZtxxSuccess("fail");
					Toast.makeText(paramActivity, "暂停业务失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				BaseDetail_ManagerService.this.handlerBaseDetailInfo(
						paramActivity, handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	/**
	 * 开始业务
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list传入的account
	 */
	public void doStartBizArchives(final Context paramActivity,
			final int paramInt, final List<Map<String, Object>> list) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl";
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;
			List<Map<String, Object>> resultGroup = new ArrayList<Map<String, Object>>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB2("doStartBizArchives", url, list);
					// resultGroup =
					// XmlUtil.getServiceSubjecrFormXmlParse(result);
				} catch (XmlPullParserException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null || result.equals("error")
						|| !result.equals("success")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					handleResult.setKsywSuccess("success");
				} else if (paramInteger == -1) {// 链接服务器失败
					handleResult.setKsywSuccess("fail");
					Toast.makeText(paramActivity, "开始业务失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				BaseDetail_ManagerService.this.handlerBaseDetailInfo(
						paramActivity, handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	/**
	 * 补正业务开始
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list传入的account
	 */
	public void doCorrectSendNote(final Context paramActivity,
			final int paramInt, final List<Map<String, Object>> list) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl";
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;
			List<Map<String, Object>> resultGroup = new ArrayList<Map<String, Object>>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB2("doCorrectSendNote", url, list);
					// resultGroup =
					// XmlUtil.getServiceSubjecrFormXmlParse(result);
				} catch (XmlPullParserException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null || result.equals("error")
						|| !result.equals("success")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					handleResult.setBztzSuccess("success");
				} else if (paramInteger == -1) {// 链接服务器失败
					handleResult.setBztzSuccess("fail");
					Toast.makeText(paramActivity, "补正通知失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				BaseDetail_ManagerService.this.handlerBaseDetailInfo(
						paramActivity, handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	/**
	 * 补正业务结束
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list传入的account
	 */
	public void doCorrectAccept(final Context paramActivity,
			final int paramInt, final List<Map<String, Object>> list) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl";
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;
			List<Map<String, Object>> resultGroup = new ArrayList<Map<String, Object>>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB2("doCorrectAccept", url, list);
					// resultGroup =
					// XmlUtil.getServiceSubjecrFormXmlParse(result);
				} catch (XmlPullParserException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null || result.equals("error")
						|| !result.equals("success")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					handleResult.setBzjsSuccess("success");
				} else if (paramInteger == -1) {// 链接服务器失败
					handleResult.setBzjsSuccess("fail");
					Toast.makeText(paramActivity, "补正业务结束失败！",
							Toast.LENGTH_LONG).show();
					return;
				}
				BaseDetail_ManagerService.this.handlerBaseDetailInfo(
						paramActivity, handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	/**
	 * 退回业务
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list传入的account
	 */
	public void doBizArchivesWithDrawal(final Context paramActivity,
			final int paramInt, final List<Map<String, Object>> list) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl";
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;
			List<Map<String, Object>> resultGroup = new ArrayList<Map<String, Object>>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB2("doBizArchivesWithDrawal", url, list);
					// resultGroup =
					// XmlUtil.getServiceSubjecrFormXmlParse(result);
				} catch (XmlPullParserException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null || result.equals("error")
						|| !result.equals("success")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					handleResult.setThywSuccess("success");
				} else if (paramInteger == -1) {// 链接服务器失败
					handleResult.setThywSuccess("fail");
					Toast.makeText(paramActivity, "退回业务失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				BaseDetail_ManagerService.this.handlerBaseDetailInfo(
						paramActivity, handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	/**
	 * 处理业务
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list传入的account
	 */
	public void doBizArchives(final Context paramActivity, final int paramInt,
			final List<Map<String, Object>> list) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl";
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;
			List<Map<String, Object>> resultGroup = new ArrayList<Map<String, Object>>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB2("doBizArchives", url, list);
					// resultGroup =
					// XmlUtil.getServiceSubjecrFormXmlParse(result);
				} catch (XmlPullParserException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null || result.equals("error")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					if (result.equals("success") || result.equals("succeed")) {
						handleResult.setClywSuccess("success");
					} else {
						handleResult.setClywSuccess("fail");
						Toast.makeText(paramActivity, "处理业务失败！",
								Toast.LENGTH_LONG).show();
					}

				} else if (paramInteger == -1) {// 链接服务器失败
					handleResult.setClywSuccess("fail");
					Toast.makeText(paramActivity, "连接服务器失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				BaseDetail_ManagerService.this.handlerBaseDetailInfo(
						paramActivity, handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	/**
	 * 结果发放
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list传入的account
	 */
	public void doSendReceipt(final Context paramActivity, final int paramInt,
			final List<Map<String, Object>> list) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl";
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;
			List<Map<String, Object>> resultGroup = new ArrayList<Map<String, Object>>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB2("doSendReceipt", url, list);
					// resultGroup =
					// XmlUtil.getServiceSubjecrFormXmlParse(result);
				} catch (XmlPullParserException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null || result.equals("error")
						|| !result.equals("success")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					handleResult.setJfjgSuccess("success");
				} else if (paramInteger == -1) {// 链接服务器失败
					handleResult.setJfjgSuccess("fail");
					Toast.makeText(paramActivity, "交付失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				BaseDetail_ManagerService.this.handlerBaseDetailInfo(
						paramActivity, handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	/**
	 * 结果发放
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list传入的account
	 */
	public void doSendSM(final Context paramActivity, final int paramInt,
			final List<Map<String, Object>> list) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl";
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;
			List<Map<String, Object>> resultGroup = new ArrayList<Map<String, Object>>();

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB2("doSendSM", url, list);
					// resultGroup =
					// XmlUtil.getServiceSubjecrFormXmlParse(result);
				} catch (XmlPullParserException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null || result.equals("error")
						|| !result.equals("success")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					handleResult.setFsdxSuccess("success");
				} else if (paramInteger == -1) {// 链接服务器失败
					handleResult.setFsdxSuccess("fail");
					Toast.makeText(paramActivity, "发送失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				BaseDetail_ManagerService.this.handlerBaseDetailInfo(
						paramActivity, handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	/**
	 * 不予受理提交
	 * 
	 * @param paramActivity调用这个方法的activity
	 * @param paramInt
	 *            代表调用的是哪个功能，当调用下一个方法时，就知道做怎样的反应
	 * @param list传入的account
	 */
	public void noAccept(final Context paramActivity, final int paramInt,
			final List<Map<String, Object>> list) {
		final String url ="http://117.40.244.135:8082/Service/PortalSrv?wsdl";
		BaseAsyncTask loacl = new BaseAsyncTask(paramActivity, true) {
			String result = null;

			@Override
			protected Integer doInBackground(Integer[] paramArrayOfInteger) {
				// 调用方法，到服务器上验证，第一个参数是要调用的服务器的方法，
				// 第二个参数是要传的参数，获取返回值
				try {
					result = downloadDB2("noAccept", url, list);
					// resultGroup =
					// XmlUtil.getServiceSubjecrFormXmlParse(result);
				} catch (XmlPullParserException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				// 判断返回值，为空或者字符串“servicefail”返回-1.否则返回1
				if (result == null || result.equals("error")
						|| !result.equals("succeed")) {
					return Integer.valueOf(-1);
				}
				return Integer.valueOf(1);
			}

			@Override
			protected void onPostExecute(Integer paramInteger) {
				super.onPostExecute(paramInteger);
				HandleResult handleResult = new HandleResult();
				if (paramInteger == 1) {// 获取到返回的信息
					handleResult.setDenySuccess("success");
				} else if (paramInteger == -1) {// 链接服务器失败
					handleResult.setDenySuccess("fail");
					Toast.makeText(paramActivity, "提交失败！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				BaseDetail_ManagerService.this.handlerBaseDetailInfo(
						paramActivity, handleResult, paramInt);
			}

		};
		loacl.execute(1);
	}

	public abstract void handlerBaseDetailInfo(Context context,
			HandleResult paramHandleResult, int paramInt);
}
