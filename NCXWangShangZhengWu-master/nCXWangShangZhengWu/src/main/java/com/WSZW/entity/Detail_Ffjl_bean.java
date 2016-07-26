package com.WSZW.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Detail_Ffjl_bean implements Serializable {
	private String bjlsh; // 办件流水号
	private String sqrxm; // 申请人姓名
	private String ffr; // 发放人
	private String ffsj; // 发放时间
	private String ffjg; // 发放机构
	private String qsr; // 签收人
	private String jsr; // 接收人
	private String jshm; // 接收号码
	private String fsr; // 发送人
	private String fssj; // 发送时间
	private String dxnr; // 短信内容

	public String getBjlsh() {
		return bjlsh;
	}

	public void setBjlsh(String bjlsh) {
		this.bjlsh = bjlsh;
	}

	public String getSqrxm() {
		return sqrxm;
	}

	public void setSqrxm(String sqrxm) {
		this.sqrxm = sqrxm;
	}

	public String getFfr() {
		return ffr;
	}

	public void setFfr(String ffr) {
		this.ffr = ffr;
	}

	public String getFfsj() {
		return ffsj;
	}

	public void setFfsj(String ffsj) {
		if (ffsj.contains(".0")) {
			ffsj = ffsj.replace(".0", "");
		}
		this.ffsj = ffsj;
	}

	public String getFfjg() {
		return ffjg;
	}

	public void setFfjg(String ffjg) {
		this.ffjg = ffjg;
	}

	public String getJsr() {
		return jsr;
	}

	public void setJsr(String jsr) {
		this.jsr = jsr;
	}

	public String getJshm() {
		return jshm;
	}

	public void setJshm(String jshm) {
		this.jshm = jshm;
	}

	public String getFsr() {
		return fsr;
	}

	public void setFsr(String fsr) {
		this.fsr = fsr;
	}

	public String getFssj() {
		return fssj;
	}

	public void setFssj(String fssj) {
		this.fssj = fssj;
	}

	public String getDxnr() {
		return dxnr;
	}

	public void setDxnr(String dxnr) {
		this.dxnr = dxnr;
	}

	public String getQsr() {
		return qsr;
	}

	public void setQsr(String qsr) {
		this.qsr = qsr;
	}
}
