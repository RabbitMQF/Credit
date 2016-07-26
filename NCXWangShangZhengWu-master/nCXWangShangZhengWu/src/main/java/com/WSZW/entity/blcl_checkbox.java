package com.WSZW.entity;

import java.util.List;
import java.util.Map;

/**
 * 在线申办受理事项中的提交材料可能有几个checkbox，此为checkbox的实体类
 * 
 * @author Administrator
 * 
 */

public class blcl_checkbox {

	public String blcl_first_name; // 办理材料的一级分类名称
	public String blcl_first_id; // 办理材料的一级分类ID
	public List<Map<String, Object>> blcl_second_name; // 办理材料的二级分类名称列表

	public String getBlcl_first_name() {
		return blcl_first_name;
	}

	public void setBlcl_first_name(String blcl_first_name) {
		this.blcl_first_name = blcl_first_name;
	}

	public List<Map<String, Object>> getBlcl_second_name() {
		return blcl_second_name;
	}

	public void setBlcl_second_name(List<Map<String, Object>> blcl_second_name) {
		this.blcl_second_name = blcl_second_name;
	}

	public String getBlcl_first_id() {
		return blcl_first_id;
	}

	public void setBlcl_first_id(String blcl_first_id) {
		this.blcl_first_id = blcl_first_id;
	}
	
	
}
