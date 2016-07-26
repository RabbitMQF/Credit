package com.WSZW.entity;

/**
 * 
 * 政务服务事项列表的每项item实体类
 * 
 * @author Administrator
 * 
 */
public class Zwfw_Shixiang_List_Item {
	private String name; // 服务事项名称
	private String id; // 服务事项id
	private String code; // 服务事项编码
	private String leibie; // 服务事项类别
	private String bumen; // 主管部门

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLeibie() {
		return leibie;
	}

	public void setLeibie(String leibie) {
		this.leibie = leibie;
	}

	public String getBumen() {
		return bumen;
	}

	public void setBumen(String bumen) {
		this.bumen = bumen;
	}
}
