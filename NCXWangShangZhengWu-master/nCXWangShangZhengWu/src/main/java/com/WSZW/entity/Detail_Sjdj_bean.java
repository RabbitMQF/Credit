package com.WSZW.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Detail_Sjdj_bean implements Serializable {
	private String serialNumber;
	private String serviceTargetName;
	private String applyDate;
	private String serviceOrgName;
	private String handleDate;
	private String serialNumber_ysl;
	private String serviceTargetPhone;
	private String serviceTargetEmail;
	private String applyDate_ysl;
	private String operatorName;

	List<String> list_bltj = new ArrayList<String>();
	List<Map<String, Object>> list_blcl = new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> list_blfztj = new ArrayList<Map<String, Object>>();

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getServiceTargetName() {
		return serviceTargetName;
	}

	public void setServiceTargetName(String serviceTargetName) {
		this.serviceTargetName = serviceTargetName;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		if (applyDate.contains(".0")) {
			applyDate = applyDate.replace(".0", "");
		}
		this.applyDate = applyDate;
	}

	public String getServiceOrgName() {
		return serviceOrgName;
	}

	public void setServiceOrgName(String serviceOrgName) {
		this.serviceOrgName = serviceOrgName;
	}

	public String getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(String handleDate) {
		if (handleDate.contains(".0")) {
			handleDate = handleDate.replace(".0", "");
		}
		this.handleDate = handleDate;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public List<String> getList_bltj() {
		return list_bltj;
	}

	public void setList_bltj(List<String> list_bltj2) {
		this.list_bltj = list_bltj2;
	}

	public List<Map<String, Object>> getList_blcl() {
		return list_blcl;
	}

	public void setList_blcl(List<Map<String, Object>> list_blcl) {
		this.list_blcl = list_blcl;
	}

	public List<Map<String, Object>> getList_blfztj() {
		return list_blfztj;
	}

	public String getServiceTargetPhone() {
		return serviceTargetPhone;
	}

	public void setServiceTargetPhone(String serviceTargetPhone) {
		this.serviceTargetPhone = serviceTargetPhone;
	}

	public String getServiceTargetEmail() {
		return serviceTargetEmail;
	}

	public void setServiceTargetEmail(String serviceTargetEmail) {
		this.serviceTargetEmail = serviceTargetEmail;
	}

	public String getApplyDate_ysl() {
		return applyDate_ysl;
	}

	public String getSerialNumber_ysl() {
		return serialNumber_ysl;
	}

	public void setSerialNumber_ysl(String serialNumber_ysl) {
		this.serialNumber_ysl = serialNumber_ysl;
	}

	public void setApplyDate_ysl(String applyDate_ysl) {
		this.applyDate_ysl = applyDate_ysl;
	}

	public void setList_blfztj(List<Map<String, Object>> list_blfztj) {
		this.list_blfztj = list_blfztj;
	}
}
