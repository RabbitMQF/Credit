package com.WSZW.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Sjdj_Deny_Msg_bean implements Serializable {
	String name_matter; // 不予办理的事项名
	String username; // 办理事项的人
	List<Map<String, Object>> list_bltj = new ArrayList<Map<String, Object>>(); // 跳转入不予受理界面后办理条件是否符合列表
	List<Map<String, Object>> list_tjcl = new ArrayList<Map<String, Object>>(); // 跳转入不予受理界面后办理材料是否符合列表

	public String getName_matter() {
		return name_matter;
	}

	public void setName_matter(String name_matter) {
		this.name_matter = name_matter;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Map<String, Object>> getList_bltj() {
		return list_bltj;
	}

	public void setList_bltj(List<Map<String, Object>> list_bltj) {
		this.list_bltj = list_bltj;
	}

	public List<Map<String, Object>> getList_tjcl() {
		return list_tjcl;
	}

	public void setList_tjcl(List<Map<String, Object>> list_tjcl) {
		this.list_tjcl = list_tjcl;
	}
}
