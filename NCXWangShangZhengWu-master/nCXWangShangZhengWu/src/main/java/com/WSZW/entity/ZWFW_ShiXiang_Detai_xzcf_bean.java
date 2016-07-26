package com.WSZW.entity;

public class ZWFW_ShiXiang_Detai_xzcf_bean {
	private String name;						//事项名称
	private String id;							//事项id
	private String serviceCode;					//行政处罚事项编码
	private String adminOrgName;				//主管部门
	private String policyBasis;					//行使依据
	private String xzcfType;					//行政处罚种类
	private String processingConditions;		//处罚幅度、违法情节及处罚裁量标准
	private	String chargeBasisAndStandard;		//收费依据及标准
	private String legalTerm;					//法定期限
	private String complaintsHotline;			//监督电话
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getAdminOrgName() {
		return adminOrgName;
	}
	public void setAdminOrgName(String adminOrgName) {
		this.adminOrgName = adminOrgName;
	}
	public String getPolicyBasis() {
		return policyBasis;
	}
	public void setPolicyBasis(String policyBasis) {
		this.policyBasis = policyBasis;
	}
	public String getXzcfType() {
		return xzcfType;
	}
	public void setXzcfType(String xzcfType) {
		this.xzcfType = xzcfType;
	}
	public String getProcessingConditions() {
		return processingConditions;
	}
	public void setProcessingConditions(String processingConditions) {
		this.processingConditions = processingConditions;
	}
	public String getChargeBasisAndStandard() {
		return chargeBasisAndStandard;
	}
	public void setChargeBasisAndStandard(String chargeBasisAndStandard) {
		this.chargeBasisAndStandard = chargeBasisAndStandard;
	}
	public String getLegalTerm() {
		return legalTerm;
	}
	public void setLegalTerm(String legalTerm) {
		this.legalTerm = legalTerm;
	}
	public String getComplaintsHotline() {
		return complaintsHotline;
	}
	public void setComplaintsHotline(String complaintsHotline) {
		this.complaintsHotline = complaintsHotline;
	}
	
	
}
