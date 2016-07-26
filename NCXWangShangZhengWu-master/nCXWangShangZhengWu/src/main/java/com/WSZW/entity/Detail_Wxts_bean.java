package com.WSZW.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Detail_Wxts_bean implements Serializable {
	private String serviceTargetName;
	private String applyDate;
	private String serviceSubjectName;
	private String serviceOrgName;
	private String serviceOrgPhone;
	private String serviceOrgAddress;

	List<Map<String, Object>> ConditionDataList = new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> MaterialDataList = new ArrayList<Map<String, Object>>();

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

	public String getServiceSubjectName() {
		return serviceSubjectName;
	}

	public void setServiceSubjectName(String serviceSubjectName) {
		this.serviceSubjectName = serviceSubjectName;
	}

	public String getServiceOrgName() {
		return serviceOrgName;
	}

	public void setServiceOrgName(String serviceOrgName) {
		this.serviceOrgName = serviceOrgName;
	}

	public String getServiceOrgPhone() {
		return serviceOrgPhone;
	}

	public void setServiceOrgPhone(String serviceOrgPhone) {
		this.serviceOrgPhone = serviceOrgPhone;
	}

	public String getServiceOrgAddress() {
		return serviceOrgAddress;
	}

	public void setServiceOrgAddress(String serviceOrgAddress) {
		this.serviceOrgAddress = serviceOrgAddress;
	}

	public List<Map<String, Object>> getConditionDataList() {
		return ConditionDataList;
	}

	public void setConditionDataList(List<Map<String, Object>> conditionDataList) {
		ConditionDataList = conditionDataList;
	}

	public List<Map<String, Object>> getMaterialDataList() {
		return MaterialDataList;
	}

	public void setMaterialDataList(List<Map<String, Object>> materialDataList) {
		MaterialDataList = materialDataList;
	}
}
