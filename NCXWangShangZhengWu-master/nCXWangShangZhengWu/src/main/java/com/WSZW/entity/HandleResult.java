package com.WSZW.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;






public class HandleResult {
	private String iSuccess;
	private ZWFW_ShiXiang_Detail_bean bean;
	private ZWFW_ShiXiang_Detai_xzcf_bean bean_xzcf;
	private ZwFw_CX_BizInfo bean_cx;
	private ZwFw_Fwpy_BizInfo bean_fwpy_1;
	
	private ZWFW_zxsb_detail_bean zxsb_detail; // 收件登记办理条件和提交材料
	private String GetBdlr_getServiceSubjectFormSuccess; // 判断事项表单是否获取成功
	private String GetSubmitServiceSubjectFormSuccess; // 判断事项表单是否提交成功
	private String uploadPritureSuccess;// 图片上传是否成功
	private String deletePritureSuccess;// 图片删除是否成功
	private String sjdj_getTjclSuccess;// 图片删除是否成功
	private List<Map<String, Object>> list_bdlr_form; // 收件登记发放地点列表
	private Sjdj_shouli_detail_bean sjdj_detail; // 收件登记办理条件和提交材料
	private String SubmitBdlrFormContent; // 表单录入提交成功后获得的返回值
	private String Sjsj_AcceptSubmitContent; // 收件登记提交成功后获得的返回值
	private String GetSjsj_AcceptSubmitSuccess; // 判断收件登记是否提交成功
	private String GetIfAcceptForeground_ResultSuccess; // 判断收件登记事项是否前台受理
	private String GetChushen_Address_ResultSuccess; // 判断收件登记是否获取初审地点成功
	private String Getfafang_Address_ResultSuccess; // 判断收件登记是否获取发放地点成功
	private String GetSjdj_bltj_tjcl_ResultSuccess; // 判断收件登记是否获取办理条件和提交的材料成功
	private List<Map<String, Object>> list_if_accept_foreground; // 前台受理地点列表
	private List<Map<String, Object>> list_chushen_address; // 收件登记初审地点列表
	private List<Map<String, Object>> list_fafang_address; // 收件登记发放地点列表
	
	private String GetBaseDetailSuccess;// 判断是否获取已发放列表信息
	
	private List<String> priture_list = new ArrayList<String>();// 获取图片上传后的返回值
	private String getDeletePritureInfo;// 获取图片删除后的返回值
	
	private List<Map<String, Object>> sjdj_tjcl_no_checkbox = new ArrayList<Map<String, Object>>();
	private Detail_Sjdj_bean resultGroup_sjdj_dzcl = new Detail_Sjdj_bean();
	
	private List<Map<String, Object>> resultGroup_tab = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> resultGroup_jbxx = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> resultGroup_ztxx = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> resultGroup_bzxx = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> resultGroup_ywbd = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> resultGroup_fjck = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> resultGroup_wsbd = new ArrayList<Map<String, Object>>();
	private Detail_Sjdj_bean resultGroup_jbxx_ysl = new Detail_Sjdj_bean();
	private Detail_Ffjl_bean resultGroup_ffjl = new Detail_Ffjl_bean();
	private Detail_Wxts_bean resultGroup_wxts = new Detail_Wxts_bean();
	private String ZtxxSuccess;// 判断是否暂停成功
	private String KsywSuccess;// 判断是否开始成功
	private String BztzSuccess;// 判断是否补正通知成功
	private String BzjsSuccess;// 判断是否补正业务结束
	private String ThywSuccess;// 判断是否退回成功
	private String ClywSuccess;// 判断是否处理成功
	private String JfjgSuccess;// 判断是否交付结果成功
	private String FsdxSuccess;// 判断是否发送短信成功
	private String denySuccess;// 判断是否发送短信成功
	
	
	public ZWFW_ShiXiang_Detail_bean getBean() {
		return bean;
	}

	public void setBean(ZWFW_ShiXiang_Detail_bean bean) {
		this.bean = bean;
	}

	public String getiSuccess() {
		return iSuccess;
	}

	public void setiSuccess(String iSuccess) {
		this.iSuccess = iSuccess;
	}

	public ZWFW_ShiXiang_Detai_xzcf_bean getBean_xzcf() {
		return bean_xzcf;
	}

	public void setBean_xzcf(ZWFW_ShiXiang_Detai_xzcf_bean bean_xzcf) {
		this.bean_xzcf = bean_xzcf;
	}

	public ZwFw_CX_BizInfo getBean_cx() {
		return bean_cx;
	}

	public void setBean_cx(ZwFw_CX_BizInfo bean_cx) {
		this.bean_cx = bean_cx;
	}

	public ZwFw_Fwpy_BizInfo getBean_fwpy_1() {
		return bean_fwpy_1;
	}

	public void setBean_fwpy_1(ZwFw_Fwpy_BizInfo bean_fwpy_1) {
		this.bean_fwpy_1 = bean_fwpy_1;
	}
	public String getGetIfAcceptForeground_ResultSuccess() {
		return GetIfAcceptForeground_ResultSuccess;
	}

	public void setGetIfAcceptForeground_ResultSuccess(
			String getIfAcceptForeground_ResultSuccess) {
		GetIfAcceptForeground_ResultSuccess = getIfAcceptForeground_ResultSuccess;
	}
	public void setList_if_accept_foreground(
			List<Map<String, Object>> list_if_accept_foreground) {
		this.list_if_accept_foreground = list_if_accept_foreground;
	}
	public List<Map<String, Object>> getList_if_accept_foreground() {
		return list_if_accept_foreground;
	}

	public String getGetChushen_Address_ResultSuccess() {
		return GetChushen_Address_ResultSuccess;
	}

	public void setGetChushen_Address_ResultSuccess(
			String getChushen_Address_ResultSuccess) {
		GetChushen_Address_ResultSuccess = getChushen_Address_ResultSuccess;
	}

	public List<Map<String, Object>> getList_chushen_address() {
		return list_chushen_address;
	}

	public void setList_chushen_address(List<Map<String, Object>> list_chushen_address) {
		this.list_chushen_address = list_chushen_address;
	}

	public String getGetfafang_Address_ResultSuccess() {
		return Getfafang_Address_ResultSuccess;
	}

	public void setGetfafang_Address_ResultSuccess(
			String getfafang_Address_ResultSuccess) {
		Getfafang_Address_ResultSuccess = getfafang_Address_ResultSuccess;
	}

	public List<Map<String, Object>> getList_fafang_address() {
		return list_fafang_address;
	}

	public void setList_fafang_address(List<Map<String, Object>> list_fafang_address) {
		this.list_fafang_address = list_fafang_address;
	}

	public String getGetSjdj_bltj_tjcl_ResultSuccess() {
		return GetSjdj_bltj_tjcl_ResultSuccess;
	}

	public void setGetSjdj_bltj_tjcl_ResultSuccess(
			String getSjdj_bltj_tjcl_ResultSuccess) {
		GetSjdj_bltj_tjcl_ResultSuccess = getSjdj_bltj_tjcl_ResultSuccess;
	}

	public ZWFW_zxsb_detail_bean getZxsb_detail() {
		return zxsb_detail;
	}

	public void setZxsb_detail(ZWFW_zxsb_detail_bean zxsb_detail) {
		this.zxsb_detail = zxsb_detail;
	}

	public String getSjdj_getTjclSuccess() {
		return sjdj_getTjclSuccess;
	}

	public void setSjdj_getTjclSuccess(String sjdj_getTjclSuccess) {
		this.sjdj_getTjclSuccess = sjdj_getTjclSuccess;
	}

	public List<Map<String, Object>> getSjdj_tjcl_no_checkbox() {
		return sjdj_tjcl_no_checkbox;
	}

	public void setSjdj_tjcl_no_checkbox(List<Map<String, Object>> sjdj_tjcl_no_checkbox) {
		this.sjdj_tjcl_no_checkbox = sjdj_tjcl_no_checkbox;
	}

	public String getGetSjsj_AcceptSubmitSuccess() {
		return GetSjsj_AcceptSubmitSuccess;
	}

	public void setGetSjsj_AcceptSubmitSuccess(
			String getSjsj_AcceptSubmitSuccess) {
		GetSjsj_AcceptSubmitSuccess = getSjsj_AcceptSubmitSuccess;
	}

	public String getSjsj_AcceptSubmitContent() {
		return Sjsj_AcceptSubmitContent;
	}

	public void setSjsj_AcceptSubmitContent(String sjsj_AcceptSubmitContent) {
		Sjsj_AcceptSubmitContent = sjsj_AcceptSubmitContent;
	}

	public Sjdj_shouli_detail_bean getSjdj_detail() {
		return sjdj_detail;
	}

	public void setSjdj_detail(Sjdj_shouli_detail_bean sjdj_detail) {
		this.sjdj_detail = sjdj_detail;
	}

	public String getGetDeletePritureInfo() {
		return getDeletePritureInfo;
	}

	public void setGetDeletePritureInfo(String getDeletePritureInfo) {
		this.getDeletePritureInfo = getDeletePritureInfo;
	}

	public List<String> getPriture_list() {
		return priture_list;
	}

	public void setPriture_list(List<String> priture_list) {
		this.priture_list = priture_list;
	}

	public String getUploadPritureSuccess() {
		return uploadPritureSuccess;
	}

	public void setUploadPritureSuccess(String uploadPritureSuccess) {
		this.uploadPritureSuccess = uploadPritureSuccess;
	}

	public String getDeletePritureSuccess() {
		return deletePritureSuccess;
	}

	public void setDeletePritureSuccess(String deletePritureSuccess) {
		this.deletePritureSuccess = deletePritureSuccess;
	}

	public List<Map<String, Object>> getResultGroup_tab() {
		return resultGroup_tab;
	}

	public void setResultGroup_tab(List<Map<String, Object>> resultGroup_tab) {
		this.resultGroup_tab = resultGroup_tab;
	}

	public String getGetBaseDetailSuccess() {
		return GetBaseDetailSuccess;
	}

	public void setGetBaseDetailSuccess(String getBaseDetailSuccess) {
		GetBaseDetailSuccess = getBaseDetailSuccess;
	}

	public List<Map<String, Object>> getResultGroup_jbxx() {
		return resultGroup_jbxx;
	}

	public void setResultGroup_jbxx(List<Map<String, Object>> resultGroup_jbxx) {
		this.resultGroup_jbxx = resultGroup_jbxx;
	}

	public Detail_Sjdj_bean getResultGroup_sjdj_dzcl() {
		return resultGroup_sjdj_dzcl;
	}

	public void setResultGroup_sjdj_dzcl(Detail_Sjdj_bean resultGroup_sjdj_dzcl) {
		this.resultGroup_sjdj_dzcl = resultGroup_sjdj_dzcl;
	}

	public List<Map<String, Object>> getResultGroup_ztxx() {
		return resultGroup_ztxx;
	}

	public void setResultGroup_ztxx(List<Map<String, Object>> resultGroup_ztxx) {
		this.resultGroup_ztxx = resultGroup_ztxx;
	}

	public List<Map<String, Object>> getResultGroup_bzxx() {
		return resultGroup_bzxx;
	}

	public void setResultGroup_bzxx(List<Map<String, Object>> resultGroup_bzxx) {
		this.resultGroup_bzxx = resultGroup_bzxx;
	}

	public Detail_Ffjl_bean getResultGroup_ffjl() {
		return resultGroup_ffjl;
	}

	public void setResultGroup_ffjl(Detail_Ffjl_bean resultGroup_ffjl) {
		this.resultGroup_ffjl = resultGroup_ffjl;
	}

	public Detail_Wxts_bean getResultGroup_wxts() {
		return resultGroup_wxts;
	}

	public void setResultGroup_wxts(Detail_Wxts_bean resultGroup_wxts) {
		this.resultGroup_wxts = resultGroup_wxts;
	}

	public List<Map<String, Object>> getResultGroup_ywbd() {
		return resultGroup_ywbd;
	}

	public void setResultGroup_ywbd(List<Map<String, Object>> resultGroup_ywbd) {
		this.resultGroup_ywbd = resultGroup_ywbd;
	}

	public List<Map<String, Object>> getResultGroup_fjck() {
		return resultGroup_fjck;
	}

	public void setResultGroup_fjck(List<Map<String, Object>> resultGroup_fjck) {
		this.resultGroup_fjck = resultGroup_fjck;
	}

	public Detail_Sjdj_bean getResultGroup_jbxx_ysl() {
		return resultGroup_jbxx_ysl;
	}

	public void setResultGroup_jbxx_ysl(Detail_Sjdj_bean resultGroup_jbxx_ysl) {
		this.resultGroup_jbxx_ysl = resultGroup_jbxx_ysl;
	}

	public List<Map<String, Object>> getResultGroup_wsbd() {
		return resultGroup_wsbd;
	}

	public void setResultGroup_wsbd(List<Map<String, Object>> resultGroup_wsbd) {
		this.resultGroup_wsbd = resultGroup_wsbd;
	}

	public String getZtxxSuccess() {
		return ZtxxSuccess;
	}

	public void setZtxxSuccess(String ztxxSuccess) {
		ZtxxSuccess = ztxxSuccess;
	}

	public String getKsywSuccess() {
		return KsywSuccess;
	}

	public void setKsywSuccess(String ksywSuccess) {
		KsywSuccess = ksywSuccess;
	}

	public String getBztzSuccess() {
		return BztzSuccess;
	}

	public void setBztzSuccess(String bztzSuccess) {
		BztzSuccess = bztzSuccess;
	}

	public String getBzjsSuccess() {
		return BzjsSuccess;
	}

	public void setBzjsSuccess(String bzjsSuccess) {
		BzjsSuccess = bzjsSuccess;
	}

	public String getThywSuccess() {
		return ThywSuccess;
	}

	public void setThywSuccess(String thywSuccess) {
		ThywSuccess = thywSuccess;
	}

	public String getClywSuccess() {
		return ClywSuccess;
	}

	public void setClywSuccess(String clywSuccess) {
		ClywSuccess = clywSuccess;
	}

	public String getJfjgSuccess() {
		return JfjgSuccess;
	}

	public void setJfjgSuccess(String jfjgSuccess) {
		JfjgSuccess = jfjgSuccess;
	}

	public String getFsdxSuccess() {
		return FsdxSuccess;
	}

	public void setFsdxSuccess(String fsdxSuccess) {
		FsdxSuccess = fsdxSuccess;
	}

	public String getDenySuccess() {
		return denySuccess;
	}

	public void setDenySuccess(String denySuccess) {
		this.denySuccess = denySuccess;
	}

	public String getGetBdlr_getServiceSubjectFormSuccess() {
		return GetBdlr_getServiceSubjectFormSuccess;
	}

	public void setGetBdlr_getServiceSubjectFormSuccess(
			String getBdlr_getServiceSubjectFormSuccess) {
		GetBdlr_getServiceSubjectFormSuccess = getBdlr_getServiceSubjectFormSuccess;
	}

	public List<Map<String, Object>> getList_bdlr_form() {
		return list_bdlr_form;
	}

	public void setList_bdlr_form(List<Map<String, Object>> list_bdlr_form) {
		this.list_bdlr_form = list_bdlr_form;
	}

	public String getGetSubmitServiceSubjectFormSuccess() {
		return GetSubmitServiceSubjectFormSuccess;
	}

	public void setGetSubmitServiceSubjectFormSuccess(
			String getSubmitServiceSubjectFormSuccess) {
		GetSubmitServiceSubjectFormSuccess = getSubmitServiceSubjectFormSuccess;
	}

	public String getSubmitBdlrFormContent() {
		return SubmitBdlrFormContent;
	}

	public void setSubmitBdlrFormContent(String submitBdlrFormContent) {
		SubmitBdlrFormContent = submitBdlrFormContent;
	}
	
}
