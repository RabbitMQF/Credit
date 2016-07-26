package com.WSZW.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Sjdj_matter_bean implements Serializable{
	public String name; // 姓名
	public String id_num; // 身份证号
	public String telephone_num; // 手机号码
	public String email; // 电子邮件
	public String organizationId;//办事窗口ID
	
	public String ifAcceptForegrounf; // 是否前台受理
	public String ifSendMsg; // 是否发送短信回执

	public String matter_name; // 事项名称
	public String matter_id; // 事项ID
	public String matter_org; // 所属机构
	public String matter_orgid; // 所属机构ID
	public String matter_divisionCode; // 行政区划

	public String chushen_address_first; // 选择的初审地点第一栏
	public String chushen_address_second; // 选择的初审地点第二栏
	public String fafang_address_first; // 选择的发放地点第一栏
	public String fafang_address_second; // 选择的发放地点第二栏

	public String code_csdd; // 审核机构ID
	public String code_ffdd; // 发放机构ID
	public String name_csdd; // 审核机构名称
	public String name_ffdd; // 发放机构名称
	
	public String getName_csdd() {
		return name_csdd;
	}

	public void setName_csdd(String name_csdd) {
		this.name_csdd = name_csdd;
	}

	public String getName_ffdd() {
		return name_ffdd;
	}

	public void setName_ffdd(String name_ffdd) {
		this.name_ffdd = name_ffdd;
	}

	public List<Map<String, Object>> list_chushen_address;// 初审地点列表
	public List<Map<String, Object>> list_fafang_address;// 发放地点列表

	public String getIfAcceptForegrounf() {
		return ifAcceptForegrounf;
	}

	public void setIfAcceptForegrounf(String ifAcceptForegrounf) {
		this.ifAcceptForegrounf = ifAcceptForegrounf;
	}

	public List<Map<String, Object>> getList_chushen_address() {
		return list_chushen_address;
	}

	public void setList_chushen_address(
			List<Map<String, Object>> list_chushen_address) {
		this.list_chushen_address = list_chushen_address;
	}

	public List<Map<String, Object>> getList_fafang_address() {
		return list_fafang_address;
	}

	public void setList_fafang_address(
			List<Map<String, Object>> list_fafang_address) {
		this.list_fafang_address = list_fafang_address;
	}

	public boolean send_meg; // 是否发送短信回执

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId_num() {
		return id_num;
	}

	public void setId_num(String id_num) {
		this.id_num = id_num;
	}

	public String getTelephone_num() {
		return telephone_num;
	}

	public void setTelephone_num(String telephone_num) {
		this.telephone_num = telephone_num;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isSend_meg() {
		return send_meg;
	}

	public void setSend_meg(boolean send_meg) {
		this.send_meg = send_meg;
	}

	public String getMatter_name() {
		return matter_name;
	}

	public void setMatter_name(String matter_name) {
		this.matter_name = matter_name;
	}

	public String getMatter_org() {
		return matter_org;
	}

	public void setMatter_org(String matter_org) {
		this.matter_org = matter_org;
	}

	public String getMatter_orgid() {
		return matter_orgid;
	}

	public void setMatter_orgid(String matter_orgid) {
		this.matter_orgid = matter_orgid;
	}

	public String getMatter_divisionCode() {
		return matter_divisionCode;
	}

	public void setMatter_divisionCode(String matter_divisionCode) {
		this.matter_divisionCode = matter_divisionCode;
	}

	public String getChushen_address_first() {
		return chushen_address_first;
	}

	public void setChushen_address_first(String chushen_address_first) {
		this.chushen_address_first = chushen_address_first;
	}

	public String getChushen_address_second() {
		return chushen_address_second;
	}

	public void setChushen_address_second(String chushen_address_second) {
		this.chushen_address_second = chushen_address_second;
	}

	public String getFafang_address_first() {
		return fafang_address_first;
	}

	public void setFafang_address_first(String fafang_address_first) {
		this.fafang_address_first = fafang_address_first;
	}

	public String getFafang_address_second() {
		return fafang_address_second;
	}

	public void setFafang_address_second(String fafang_address_second) {
		this.fafang_address_second = fafang_address_second;
	}

	public String getMatter_id() {
		return matter_id;
	}

	public void setMatter_id(String matter_id) {
		this.matter_id = matter_id;
	}

	public String getIfSendMsg() {
		return ifSendMsg;
	}

	public void setIfSendMsg(String ifSendMsg) {
		this.ifSendMsg = ifSendMsg;
	}

	public String getCode_csdd() {
		return code_csdd;
	}

	public void setCode_csdd(String code_csdd) {
		this.code_csdd = code_csdd;
	}

	public String getCode_ffdd() {
		return code_ffdd;
	}

	public void setCode_ffdd(String code_ffdd) {
		this.code_ffdd = code_ffdd;
	}
}
