package com.WSZW.entity;

import java.util.List;
import java.util.Map;

/**
 * 收件登记中办理条件和提交材料的实体类
 * 
 * @author Administrator
 * 
 */
public class Sjdj_shouli_detail_bean {

	public List<Map<String, Object>> list_bltj; // 存放办理条件的列表，map包括条件的name和id
	public List<blcl_checkbox> list_tjcl; // 存放提交材料的列表
	public boolean ifHaveCheck;
	public List<Map<String, Object>> list_tjcl_no_checkbox; // 存放提交条件的列表，map包括条件的name和id

	public boolean isIfHaveCheck() {
		return ifHaveCheck;
	}

	public void setIfHaveCheck(boolean ifHaveCheck) {
		this.ifHaveCheck = ifHaveCheck;
	}

	public List<Map<String, Object>> getList_bltj() {
		return list_bltj;
	}

	public void setList_bltj(List<Map<String, Object>> list_bltj) {
		this.list_bltj = list_bltj;
	}

	public List<blcl_checkbox> getList_tjcl() {
		return list_tjcl;
	}

	public void setList_tjcl(List<blcl_checkbox> list_tjcl) {
		this.list_tjcl = list_tjcl;
	}

	public List<Map<String, Object>> getList_tjcl_no_checkbox() {
		return list_tjcl_no_checkbox;
	}

	public void setList_tjcl_no_checkbox(
			List<Map<String, Object>> list_tjcl_no_checkbox) {
		this.list_tjcl_no_checkbox = list_tjcl_no_checkbox;
	}

}
