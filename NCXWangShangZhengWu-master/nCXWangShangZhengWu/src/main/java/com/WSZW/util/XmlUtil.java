/**
 * 
 */
package com.WSZW.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.WSZW.entity.Detail_Ffjl_bean;
import com.WSZW.entity.Detail_Sjdj_bean;
import com.WSZW.entity.Detail_Wxts_bean;
import com.WSZW.entity.GWLM_DpwD_bean;
import com.WSZW.entity.GWLM_ZwDt_title;
import com.WSZW.entity.blcl_checkbox;
import com.WSZW.entity.ZMHD_zx_bean;
import com.WSZW.entity.ZWFW_ShiXiang_Detai_xzcf_bean;
import com.WSZW.entity.ZWFW_ShiXiang_Detail_bean;
import com.WSZW.entity.ZWFW_zxsb_detail_bean;
import com.WSZW.entity.ZWGK_FirstColumn_bean;
import com.WSZW.entity.ZWGK_formcontent_bean;
import com.WSZW.entity.ZwFw_CX_BizInfo;
import com.WSZW.entity.ZwFw_CX_item_bean;
import com.WSZW.entity.ZwFw_Fwpy_BizInfo;
import com.WSZW.entity.Zwfw_Shixiang_List_Item;



/**
 * 解析xml格式数据的工具类
 * 
 * @author rjh
 * 
 */
public class XmlUtil {
	/**
	 * 
	 * @param xml
	 *            需要解析的xml数据
	 * @return list 解析得到的Zwfw_Shixiang_List_Item类型列表
	 * @throws XmlPullParserException
	 * @throws IOException
	 */

	public static List<Zwfw_Shixiang_List_Item> get_Zwfw_Shixiang_List(
			String xml) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Zwfw_Shixiang_List_Item> list = new ArrayList<Zwfw_Shixiang_List_Item>();
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("ServiceSubject")) {
					Zwfw_Shixiang_List_Item item = new Zwfw_Shixiang_List_Item();
					item.setName(parser.getAttributeValue(0));
					item.setId(parser.getAttributeValue(1));
					item.setCode(parser.getAttributeValue(2));
					item.setLeibie(parser.getAttributeValue(3));
					item.setBumen(parser.getAttributeValue(4));
					list.add(item);
				}

				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return list;
	}
	
	public static ZWFW_ShiXiang_Detail_bean get_Zwfw_Shixiang_Detail(
			String xml) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		ZWFW_ShiXiang_Detail_bean bean = new ZWFW_ShiXiang_Detail_bean();
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("ServiceSubject")) {
					bean.setName(parser.getAttributeValue(0));
					bean.setId(parser.getAttributeValue(1));
					bean.setServiceCode(parser.getAttributeValue(2));
					bean.setAdminOrgName(parser.getAttributeValue(3));
					bean.setMainUnitName(parser.getAttributeValue(4));
					bean.setServiceObject(parser.getAttributeValue(5));
					String temp=parser.getAttributeValue(6);
					temp=temp.replace(";", ";\r\n");
					
					bean.setProcessingConditions(temp);
					bean.setPolicyBasis(parser.getAttributeValue(7));
					bean.setTerm(parser.getAttributeValue(8));
					bean.setBlsj(parser.getAttributeValue(9));
					String temp1=parser.getAttributeValue(10);
					temp1=temp1.replace(";", ";\r\n");
					bean.setFaq(temp1);
					bean.setTel(parser.getAttributeValue(11));
					bean.setComplaintsHotline(parser.getAttributeValue(12));
					String temp2=parser.getAttributeValue(13);
					temp2=temp2.replace(";", ";\r\n");
					temp2=temp2.replace("1、", "\n 1、");
					bean.setSubmittedMaterials(temp2);
					bean.setWindowWorkflow(parser.getAttributeValue(14));
					bean.setPowerWorkflow(parser.getAttributeValue(15));
					String temp3=parser.getAttributeValue(16);
					temp3=temp3.replace(";", ";\r\n");
					bean.setServiceSubjectPost(temp3);
					bean.setChargeBasisAndStandard(parser.getAttributeValue(17));
					bean.setServiceWindow(parser.getAttributeValue(18));
					bean.setForm(parser.getAttributeValue(19));
					bean.setStarLevel(parser.getAttributeValue(20));
				}

				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return bean;
	}
	
	public static ZWFW_ShiXiang_Detai_xzcf_bean get_Zwfw_Shixiang_xzcf_Detail(
			String xml) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		ZWFW_ShiXiang_Detai_xzcf_bean bean = new ZWFW_ShiXiang_Detai_xzcf_bean();
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("ServiceSubject")) {
					bean.setName(parser.getAttributeValue(0));
					bean.setId(parser.getAttributeValue(1));
					bean.setServiceCode(parser.getAttributeValue(2));
					bean.setAdminOrgName(parser.getAttributeValue(3));
					bean.setPolicyBasis(parser.getAttributeValue(4));
					bean.setXzcfType(parser.getAttributeValue(5));
					bean.setProcessingConditions(parser.getAttributeValue(6));
					bean.setChargeBasisAndStandard(parser.getAttributeValue(7));
					bean.setLegalTerm(parser.getAttributeValue(8));
					bean.setComplaintsHotline(parser.getAttributeValue(9));
				}

				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return bean;
	}
	public static ZwFw_CX_BizInfo get_Zwfw_CX_BizInfo(String result) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(result.getBytes());
		ZwFw_CX_BizInfo cx= new ZwFw_CX_BizInfo();
		List<ZwFw_CX_item_bean> list = new ArrayList<ZwFw_CX_item_bean>();
		ZwFw_CX_item_bean item = null ;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("item")) {
					if(parser.getAttributeValue(0).toString().equals("受理流水号")){
						cx.setTrafficName(parser.getAttributeValue(1));
					}
					if(parser.getAttributeValue(0).toString().equals("事项名称")){
						cx.setObjectName(parser.getAttributeValue(1));
					}
					if(parser.getAttributeValue(0).toString().equals("办事人")){
						cx.setWorkName(parser.getAttributeValue(1));
					}
					if(parser.getAttributeValue(0).toString().equals("申办日期")){
						cx.setBidDateName(parser.getAttributeValue(1));
					}
					if(parser.getAttributeValue(0).toString().equals("受理机构")){
						cx.setInstitutionName(parser.getAttributeValue(1));
					}
					if(parser.getAttributeValue(0).toString().equals("办结日期")){
						cx.setEndDateName(parser.getAttributeValue(1));
					}
					if(parser.getAttributeValue(0).toString().equals("主管部门")){
						cx.setDepartmentName(parser.getAttributeValue(1));
					}
					if(parser.getAttributeValue(0).toString().equals("办理地点")){
						cx.setSiteName(parser.getAttributeValue(1));
					}
					if(parser.getAttributeValue(0).toString().equals("业务时间")){
						item.setDate(parser.getAttributeValue(1));
					}
					if(parser.getAttributeValue(0).toString().equals("经办机构")){
						item.setDepatement(parser.getAttributeValue(1));
					}
					if(parser.getAttributeValue(0).toString().equals("业务环节")){
						item.setHj(parser.getAttributeValue(1));
					}
					if(parser.getAttributeValue(0).toString().equals("业务状态")){
						item.setZt(parser.getAttributeValue(1));
					}
				}
				if(parser.getName().equals("BizRecordInfo")){
					item = new ZwFw_CX_item_bean();
				}
				break;
			case XmlPullParser.END_TAG:
				if(parser.getName().equals("BizRecordInfo")){
					list.add(item);
				}
				if(parser.getName().equals("BizRecordList")){
					cx.setList(list);
				}
				break;
			}
			eventType = parser.next();
		}
		return cx;
	}
	
	public static ZwFw_Fwpy_BizInfo get_Zwfw_fwpy_Detail(
			String xml) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		ZwFw_Fwpy_BizInfo bean = new ZwFw_Fwpy_BizInfo();
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("item")) {
					if(parser.getAttributeValue(0).toString().trim().equals("服务事项办结状态")){
						bean.setFwsxbjzt(parser.getAttributeValue(1));
					}if(parser.getAttributeValue(0).toString().trim().equals("受理流水号")){
						bean.setSllsh(parser.getAttributeValue(1));
					}if(parser.getAttributeValue(0).toString().trim().equals("事项名称")){
						bean.setShixiang_name(parser.getAttributeValue(1));
					}if(parser.getAttributeValue(0).toString().trim().equals("办事人")){
						bean.setBanshiren(parser.getAttributeValue(1));
					}if(parser.getAttributeValue(0).toString().trim().equals("申办日期")){
						bean.setShenban_date(parser.getAttributeValue(1));
					}if(parser.getAttributeValue(0).toString().trim().equals("受理机构")){
						bean.setBanlijigou(parser.getAttributeValue(1));
					}if(parser.getAttributeValue(0).toString().trim().equals("办结日期")){
						bean.setBanjie_date(parser.getAttributeValue(1));
					}if(parser.getAttributeValue(0).toString().trim().equals("主管部门")){
						bean.setZhuguanbumen(parser.getAttributeValue(1));
					}if(parser.getAttributeValue(0).toString().trim().equals("办理地点")){
						bean.setBanlididiab(parser.getAttributeValue(1));
					}
				}

				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return bean;
	}
	/**
	 * 文档列表
	 */
	public  static List<GWLM_ZwDt_title> getTitle(String str) throws XmlPullParserException, IOException{
		List<GWLM_ZwDt_title> list = new ArrayList<GWLM_ZwDt_title>();
		 GWLM_ZwDt_title bean = null;
		 InputStream is = new ByteArrayInputStream(str.getBytes());
		XmlPullParser parser=Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int event=parser.getEventType();
		
		while (event!=XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				if("documentVOs".equals(parser.getName())){
					bean=new GWLM_ZwDt_title();
				}
				if ("title".equals(parser.getName())) {
					bean.setTitle(parser.nextText());
				}
				if ("url".equals(parser.getName())) {
					bean.setUrl(parser.nextText());
				}
				if ("writeTime".equals(parser.getName())) {
					bean.setWriteTime(parser.nextText());
				}
				if ("docId".equals(parser.getName())) {
					bean.setDocId(parser.nextText());
				}
				if ("id".equals(parser.getName())) {
					bean.setId(parser.nextText());
				}
				if ("picUrl".equals(parser.getName())) {
					bean.setPicUrl(parser.nextText());
				}
				break;

			case XmlPullParser.END_TAG:
				if(parser.getName().equals("documentVOs")){
					list.add(bean);
				}
				break;
			}
			event = parser.next();
		}
		return list;
	
	}
	/**
	 * 单篇文档
	 */
	public  static GWLM_DpwD_bean getDpWzContent(String str) throws XmlPullParserException, IOException{
		GWLM_DpwD_bean bean = null;
		InputStream is = new ByteArrayInputStream(str.getBytes());
		XmlPullParser parser=Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int event=parser.getEventType();
		
		while (event!=XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				if("documentVO".equals(parser.getName())){
					bean=new GWLM_DpwD_bean();
				}
				if ("docContent".equals(parser.getName())) {
					bean.setDocContent(parser.nextText());
				}
				if ("summary".equals(parser.getName())) {
					bean.setSummary(parser.nextText());
				}
				if ("title".equals(parser.getName())) {
					bean.setTitle(parser.nextText());
				}
			
				if ("picUrl".equals(parser.getName())) {
					bean.setPicUrl(parser.nextText());
				}
				if ("author".equals(parser.getName())) {
					bean.setAuthor(parser.nextText());
				}
				
				if ("topNum".equals(parser.getName())) {
					bean.setTopNum(parser.nextText());
				}
				
				break;
				
			case XmlPullParser.END_TAG:
				break;
			}
			event = parser.next();
		}
		return bean;
		
	}
	/**
	 * 一级栏目列表
	 */
	public  static List<ZWGK_FirstColumn_bean> getFirstColumn(String str) throws XmlPullParserException, IOException{
		ZWGK_FirstColumn_bean bean = null;
		List<ZWGK_FirstColumn_bean> list = new ArrayList<ZWGK_FirstColumn_bean>();
		InputStream is = new ByteArrayInputStream(str.getBytes());
		XmlPullParser parser=Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int event=parser.getEventType();
		
		while (event!=XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				if("return".equals(parser.getName())){
					bean=new ZWGK_FirstColumn_bean();
				}
				if ("id".equals(parser.getName())) {
					bean.setId(parser.nextText());
				}
				if ("name".equals(parser.getName())) {
					bean.setName(parser.nextText());
				}
				if ("parentId".equals(parser.getName())) {
					bean.setParentId(parser.nextText());
				}
				if ("typeCode".equals(parser.getName())) {
					bean.setTypeCode(parser.nextText());
				}
				if ("TypeKind".equals(parser.getName())) {
					bean.setTypeKind(parser.nextText());
				}
				break;
				
			case XmlPullParser.END_TAG:
				if("return".equals(parser.getName())){
					list.add(bean);
				}
				break;
			}
			event = parser.next();
		}
		return list;
		
	}
	public  static String getResult(String str) throws XmlPullParserException, IOException{
		InputStream is = new ByteArrayInputStream(str.getBytes());
		XmlPullParser parser=Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int event=parser.getEventType();
		String str1 = null;
		
		while (event!=XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				if("return".equals(parser.getName())){
					str1 = parser.nextText();
				}
				break;
				
			case XmlPullParser.END_TAG:
				if("return".equals(parser.getName())){
					
				}
				break;
			}
			event = parser.next();
		}
		return str1;
		
	}
	/**
	 * 信息公开文档列表
	 */
	public  static List<ZWGK_formcontent_bean> getGovInfo(String str) throws XmlPullParserException, IOException{
		ZWGK_formcontent_bean bean = null;
		List<ZWGK_formcontent_bean> list = new ArrayList<ZWGK_formcontent_bean>();
		InputStream is = new ByteArrayInputStream(str.getBytes());
		XmlPullParser parser=Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int event=parser.getEventType();
		
		while (event!=XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				if("govInfoDocumentVOs".equals(parser.getName())){
					bean=new ZWGK_formcontent_bean();
				}
				if ("docId".equals(parser.getName())) {
					bean.setDocId(parser.nextText());
				}
				if ("genreTypes".equals(parser.getName())) {
					bean.setGenreTypes(parser.nextText());
				}
				if ("groupTypes".equals(parser.getName())) {
					bean.setGroupTypes(parser.nextText());
				}
				if ("id".equals(parser.getName())) {
					bean.setId(parser.nextText());
				}
				if ("indexId".equals(parser.getName())) {
					bean.setIndexId(parser.nextText());
				}
				if ("pubDocTime".equals(parser.getName())) {
					bean.setPubDocTime(parser.nextText());
				}
				if ("pubOrgan".equals(parser.getName())) {
					bean.setPubOrgan(parser.nextText());
				}
				if ("startTime".equals(parser.getName())) {
					bean.setStratTime(parser.nextText());
				}
				if ("themeTypes".equals(parser.getName())) {
					bean.setThemeTypes(parser.nextText());
				}
				if ("title".equals(parser.getName())) {
					bean.setTitle(parser.nextText());
				}
				if ("url".equals(parser.getName())) {
					bean.setUrl(parser.nextText());
				}
				if ("writeTime".equals(parser.getName())) {
					bean.setWriteTime(parser.nextText());
				}
				
				break;
				
			case XmlPullParser.END_TAG:
				if("govInfoDocumentVOs".equals(parser.getName())){
					list.add(bean);
				}
				break;
			}
			event = parser.next();
		}
		return list;
		
	}
	
	public  static String getQueryLetter(String str) throws XmlPullParserException, IOException{
		String string = null;
		InputStream is = new ByteArrayInputStream(str.getBytes());
		XmlPullParser parser=Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int event=parser.getEventType();
		
		while (event!=XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				if("return".equals(parser.getName())){
					string = parser.nextText();
				}
				break;
				
			case XmlPullParser.END_TAG:
				break;
			}
			event = parser.next();
		}
		return string;
		
	}
	
	/**
	 * 在线申办后添加的方法，需要修改
	 */
	public static List<Map<String, Object>> getAcceptQtslXmlParse(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> list = null;
		Map<String, Object> map = null;
		// String str = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<Map<String, Object>>();
				map = new HashMap<String, Object>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("ServiceSubjectSl")) {
					map.put("booleanAcceptQtsl", parser.getAttributeValue(0));
				} else if (parser.getName().equals("UserAccount")) {

					map.put("account", parser.getAttributeValue(0));
					map.put("accountId", parser.getAttributeValue(1));
					map.put("name", parser.getAttributeValue(2));
					map.put("org", parser.getAttributeValue(3));
					map.put("orgId", parser.getAttributeValue(4));
					map.put("divisionCode", parser.getAttributeValue(5));
					map.put("nextBizNode", parser.getAttributeValue(6));
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("ZsoftInfo")) {
					list.add(map);
				}
				break;
			}

			eventType = parser.next();
		}
		return list;
	}
	/**
	 * 在线申办后添加的方法，需要修改
	 */
	public static List<Map<String, Object>> getAddressCsddXmlParse(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> list_chushen_address = null;
		List<Map<String, Object>> list_chushen_address_sure = null;
		List<Map<String, Object>> list_second = null;
		Map<String, Object> map_first = null;
		Map<String, Object> map_second = null;
		// String str = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list_chushen_address = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("FirstMenu")) {
					list_second = new ArrayList<Map<String, Object>>();
					map_first = new HashMap<String, Object>();
					map_first.put("firstname", parser.getAttributeValue(0));
					map_first.put("code", parser.getAttributeValue(1));
				} else if (parser.getName().equals("SecondMenu")) {
					map_second = new HashMap<String, Object>();
					map_second.put("code", parser.getAttributeValue(1));
					map_second.put("secondname", parser.getAttributeValue(0));
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("FirstMenu")) {
					map_first.put("my_second_list", list_second);
					list_chushen_address.add(map_first);
				} else if (parser.getName().equals("SecondMenu")) {
					list_second.add(map_second);
				}
				break;
			}

			eventType = parser.next();
		}
		list_chushen_address_sure = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list_chushen_address) {
			if (((List<Map<String, Object>>) map.get("my_second_list")).size() != 0) {
				list_chushen_address_sure.add(map);
			}
		}
		return list_chushen_address_sure;
	}
	/**
	 * 在线申办后添加的方法，需要修改
	 */
	public static List<Map<String, Object>> getAddressFfddXmlParse(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> list_fafang_address = null;
		List<Map<String, Object>> list_fafang_address_sure = null;
		List<Map<String, Object>> list_second = null;
		Map<String, Object> map_first = null;
		Map<String, Object> map_second = null;
		// String str = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list_fafang_address = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("FirstMenu")) {
					list_second = new ArrayList<Map<String, Object>>();
					map_first = new HashMap<String, Object>();
					map_first.put("firstname", parser.getAttributeValue(0));
					map_first.put("code", parser.getAttributeValue(1));
					map_first.put("ifdefaultfirstvalue",
							parser.getAttributeValue(2));
				} else if (parser.getName().equals("SecondMenu")) {
					map_second = new HashMap<String, Object>();
					map_second.put("ifdefaultsecondvalue",
							parser.getAttributeValue(2));
					map_second.put("code", parser.getAttributeValue(1));
					map_second.put("secondname", parser.getAttributeValue(0));
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("FirstMenu")) {
					map_first.put("my_second_list", list_second);
					list_fafang_address.add(map_first);
				} else if (parser.getName().equals("SecondMenu")) {
					list_second.add(map_second);
				}
				break;
			}

			eventType = parser.next();
		}
		list_fafang_address_sure = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list_fafang_address) {
			if (((List<Map<String, Object>>) map.get("my_second_list")).size() != 0) {
				list_fafang_address_sure.add(map);
			}
		}
		return list_fafang_address_sure;
	}
	
	/**
	 * 在线申办后添加的方法，需要修改
	 */
	public static ZWFW_zxsb_detail_bean getSjdj_detailXmlParse(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());

		ZWFW_zxsb_detail_bean zxsb = new ZWFW_zxsb_detail_bean(); // 需要返回的收件登记关于办理条件和提交材料的实体类
		List<Map<String, Object>> list_bltj = null; // 用来存放办理条件的list
		Map<String, Object> list_bltj_map = null; // 办理条件的map

		List<Map<String, Object>> list_bltj_no_checkbox = null; // 用来存放办理条件的list
		List<blcl_checkbox> list_tjcl = null; // 存放待提交材料的实体类列表
		blcl_checkbox blcl = null;
		List<Map<String, Object>> blcl_second_name = null;
		Map<String, Object> second_name_map = null;

		List<Map<String, Object>> list_blcl_three = null; // 存放办理材料的列表
		Map<String, Object> list_blcl_map = null; // 提交材料的map

		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list_bltj = new ArrayList<Map<String, Object>>();
				list_tjcl = new ArrayList<blcl_checkbox>();
				list_bltj_no_checkbox = new ArrayList<Map<String, Object>>();
				list_blcl_three = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("ServiceSubjectBltjInfo")) {

					list_bltj_map = new HashMap<String, Object>();
					list_bltj_map.put("name", parser.getAttributeValue(0));
					list_bltj_map.put("id", parser.getAttributeValue(1));
					list_bltj.add(list_bltj_map);
				} else if (parser.getName().equals("ServiceSubjectBlclFirst")) {
					blcl = new blcl_checkbox();
					zxsb.setIfHaveCheck(true);
					blcl_second_name = new ArrayList<Map<String, Object>>();
					blcl.setBlcl_first_name(parser.getAttributeValue(0));
					blcl.setBlcl_first_id(parser.getAttributeValue(1));
				} else if (parser.getName().equals("ServiceSubjectBlclSecond")) {
					second_name_map = new HashMap<String, Object>();
					second_name_map.put("second_name",
							parser.getAttributeValue(0));
					blcl_second_name.add(second_name_map);
				} else if (parser.getName().equals("ServiceSubjectBlclThree")) {
					zxsb.setIfHaveCheck(false);
					list_blcl_map = new HashMap<String, Object>();
					list_blcl_map.put("name", parser.getAttributeValue(0));
					list_blcl_map.put("id", parser.getAttributeValue(1));
					list_blcl_map.put("yscl", parser.getAttributeValue(2));
					list_blcl_map.put("identityType",
							parser.getAttributeValue(3));
					// if(blcl == null){
					// sjdj.setIfHaveCheck(false);
					list_bltj_no_checkbox.add(list_blcl_map);
					// }else{
					// sjdj.setIfHaveCheck(true);
					// list_blcl_three.add(list_blcl_map);
					// }

				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("ServiceSubjectBltj")) {
					zxsb.setList_bltj(list_bltj);
				} else if (parser.getName().equals("ServiceSubjectBlclFirst")) {
					blcl.setBlcl_second_name(blcl_second_name);
					list_tjcl.add(blcl);

				} else if (parser.getName().equals("ServiceSubjectBlcl")) {
					if (zxsb.isIfHaveCheck()) {
						zxsb.setList_tjcl(list_tjcl);
					} else {
						zxsb.setList_tjcl_no_checkbox(list_bltj_no_checkbox);
					}

				}
				break;
			}

			eventType = parser.next();
		}
		return zxsb;
	}
	/**
	 * 在线申办后添加的方法，需要修改
	 */
	public static List<Map<String, Object>> getSjdj_detail_tjcl_XmlParse(
			String xml) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());

		List<Map<String, Object>> list_blcl_three = null; // 存放办理材料的列表
		Map<String, Object> list_blcl_map = null; // 提交材料的map

		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list_blcl_three = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("ServiceSubjectBlclThree")) {
					list_blcl_map = new HashMap<String, Object>();
					list_blcl_map.put("name", parser.getAttributeValue(0));
					list_blcl_map.put("id", parser.getAttributeValue(1));
					list_blcl_map.put("yscl", parser.getAttributeValue(2));
					list_blcl_map.put("identityType",
							parser.getAttributeValue(3));
					list_blcl_three.add(list_blcl_map);
				}
				break;
			case XmlPullParser.END_TAG:

				break;
			}
			eventType = parser.next();
		}
		return list_blcl_three;
	}
	
	
	
	/**
	 * 在线申办后添加
	 * 解析事项业务详情基本信息
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Map<String, Object>> getSubject_Detail_JBXX(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> oList = null;
		List<Map<String, Object>> list = null;
		Map<String, Object> result = null;
		Map<String, Object> oresult = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				oList = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("FlowSummary")) {
					result = new HashMap<String, Object>();
					result.put("title", parser.getAttributeValue(0));
					result.put("actived", parser.getAttributeValue(1));
					list = new ArrayList<Map<String, Object>>();
				} else if (parser.getName().equals("Item")) {
					oresult = new HashMap<String, Object>();
					oresult.put("name", parser.getAttributeValue(0));
					String value = "";
					if (parser.getAttributeValue(1).contains(".0")) {
						value = parser.getAttributeValue(1).replace(".0", "");
						oresult.put("value", value);
					} else {
						oresult.put("value", parser.getAttributeValue(1));
					}
					list.add(oresult);
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("FlowSummary")) {
					if (result != null && list != null) {
						result.put("item_list", list);
					}
					oList.add(result);
				}
				break;
			}
			eventType = parser.next();
		}
		return oList;
	}
	
	
	/**
	 * 在线申请
	 * 解析事项业务详情收件登记和电子材料
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static Detail_Sjdj_bean getSubject_Detail_SJDJ_DZCL(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		Detail_Sjdj_bean detail_Sjdj_bean = new Detail_Sjdj_bean();
		List<String> list_bltj = new ArrayList<String>();
		List<Map<String, Object>> List_blfztj = null;
		List<String> List_blfztj_2 = null;
		Map<String, Object> map_blfztj = null;

		List<Map<String, Object>> List_blcl = null;
		Map<String, Object> map_blcl = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				// oList=new ArrayList<Map<String,Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizReceiptInfo")) {
					detail_Sjdj_bean.setSerialNumber(parser
							.getAttributeValue(0));
					detail_Sjdj_bean.setServiceTargetName(parser
							.getAttributeValue(2));
					detail_Sjdj_bean.setApplyDate(parser.getAttributeValue(3));
					detail_Sjdj_bean.setServiceTargetPhone(parser
							.getAttributeValue(1));
					detail_Sjdj_bean.setApplyDate_ysl(parser
							.getAttributeValue(3));
					detail_Sjdj_bean.setServiceOrgName(parser
							.getAttributeValue(4));
					detail_Sjdj_bean.setHandleDate(parser.getAttributeValue(5));
					detail_Sjdj_bean.setOperatorName(parser
							.getAttributeValue(6));
				} else if (parser.getName().equals("ServiceSubjectBltjInfo")) {
					list_bltj.add(parser.getAttributeValue(0));
				} else if (parser.getName().equals("ServiceSubjectBlfztj")) {
					List_blfztj = new ArrayList<Map<String, Object>>();
				} else if (parser.getName().equals("ServiceSubjectBlfztjFirst")) {
					map_blfztj = new HashMap<String, Object>();
					map_blfztj.put("name", parser.getAttributeValue(0));
					map_blfztj.put("checked", parser.getAttributeValue(2));
					List_blfztj_2 = new ArrayList<String>();
				} else if (parser.getName()
						.equals("ServiceSubjectBlfztjSecond")) {
					List_blfztj_2.add(parser.getAttributeValue(0));
				} else if (parser.getName().equals("ServiceSubjectBlcl")) {
					List_blcl = new ArrayList<Map<String, Object>>();
				} else if (parser.getName().equals("ServiceSubjectBlclFirst")) {
					map_blcl = new HashMap<String, Object>();
					map_blcl.put("name", parser.getAttributeValue(0));
					map_blcl.put("id", parser.getAttributeValue(1));
					map_blcl.put("yscl", parser.getAttributeValue(2));
					map_blcl.put("identityType", parser.getAttributeValue(3));
					map_blcl.put("imgUrl", parser.getAttributeValue(4));

				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("ServiceSubjectBltj")) {
					detail_Sjdj_bean.setList_bltj(list_bltj);
				}
				if (parser.getName().equals("ServiceSubjectBlfztjFirst")) {
					map_blfztj.put("list_second", List_blfztj_2);
					List_blfztj.add(map_blfztj);
				}
				if (parser.getName().equals("ServiceSubjectBlfztj")) {
					detail_Sjdj_bean.setList_blfztj(List_blfztj);
				}
				if (parser.getName().equals("ServiceSubjectBlclFirst")) {
					List_blcl.add(map_blcl);
				}
				if (parser.getName().equals("ServiceSubjectBlcl")) {
					detail_Sjdj_bean.setList_blcl(List_blcl);
				}
				break;
			}
			eventType = parser.next();
		}
		return detail_Sjdj_bean;
	}
	
	
	/**
	 * 在线申办
	 * 解析事项业务详情暂停信息
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Map<String, Object>> getSubject_Detail_ZTXX(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> oList = null;
		List<Map<String, Object>> oList_1 = null;
		Map<String, Object> result = null;
		Map<String, Object> result_1 = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				oList = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("PauseInfo")) {
					oList_1 = new ArrayList<Map<String, Object>>();
				} else if (parser.getName().equals("Item")) {
					result_1 = new HashMap<String, Object>();
					result_1.put("name", parser.getAttributeValue(0));
					String value = "";
					if (parser.getAttributeValue(1).contains(".0")) {
						value = parser.getAttributeValue(1).replace(".0", "");
						result_1.put("value", value);
					} else {
						result_1.put("value", parser.getAttributeValue(1));
					}
					// list.add(oresult);
					// result_1.put("value", parser.getAttributeValue(1));
					oList_1.add(result_1);
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("PauseInfo")) {
					result = new HashMap<String, Object>();
					result.put("list_item_list", oList_1);
					oList.add(result);
				}
				break;
			}
			eventType = parser.next();
		}
		return oList;
	}
	
	/**
	 * 在线申请
	 * 解析事项业务详情补正信息
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Map<String, Object>> getSubject_Detail_BZXX(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> oList = null;
		List<Map<String, Object>> oList_1 = null;
		Map<String, Object> result = null;
		Map<String, Object> result_1 = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				oList = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("CorrectInfo")) {
					oList_1 = new ArrayList<Map<String, Object>>();
				} else if (parser.getName().equals("Item")) {
					result_1 = new HashMap<String, Object>();
					result_1.put("name", parser.getAttributeValue(0));
					String value = "";
					if (parser.getAttributeValue(1).contains(".0")) {
						value = parser.getAttributeValue(1).replace(".0", "");
						result_1.put("value", value);
					} else {
						result_1.put("value", parser.getAttributeValue(1));
					}
					// result_1.put("value", parser.getAttributeValue(1));
					oList_1.add(result_1);
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("CorrectInfo")) {
					result = new HashMap<String, Object>();
					result.put("list_item_list", oList_1);
					oList.add(result);
				}
				break;
			}
			eventType = parser.next();
		}
		return oList;
	}

	/**
	 * 在线申请
	 * 解析事项业务详情温馨提示
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static Detail_Ffjl_bean getSubject_Detail_FFJL(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		Detail_Ffjl_bean detail_Ffjl_bean = new Detail_Ffjl_bean();
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:

				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("TopMsg")) {
					if (parser.getAttributeValue(0).equals("业务流水号")) {
						detail_Ffjl_bean.setBjlsh(parser.getAttributeValue(1));
					} else if (parser.getAttributeValue(0).equals("申请人姓名")) {
						detail_Ffjl_bean.setSqrxm(parser.getAttributeValue(1));
					}
				} else if (parser.getName().equals("Item")) {
					if (parser.getAttributeValue(0).equals("发放人")) {
						detail_Ffjl_bean.setFfr(parser.getAttributeValue(1));
					} else if (parser.getAttributeValue(0).equals("发放时间")) {
						detail_Ffjl_bean.setFfsj(parser.getAttributeValue(1));
					} else if (parser.getAttributeValue(0).equals("发放机构")) {
						detail_Ffjl_bean.setFfjg(parser.getAttributeValue(1));
					} else if (parser.getAttributeValue(0).equals("签收人")) {
						detail_Ffjl_bean.setQsr(parser.getAttributeValue(1));
					} else if (parser.getAttributeValue(0).equals("接收人")) {
						detail_Ffjl_bean.setJsr(parser.getAttributeValue(1));
					} else if (parser.getAttributeValue(0).equals("接收号码")) {
						detail_Ffjl_bean.setJshm(parser.getAttributeValue(1));
					} else if (parser.getAttributeValue(0).equals("发送人")) {
						detail_Ffjl_bean.setFsr(parser.getAttributeValue(1));
					} else if (parser.getAttributeValue(0).equals("发送时间")) {
						detail_Ffjl_bean.setFssj(parser.getAttributeValue(1));
					} else if (parser.getAttributeValue(0).equals("短信内容")) {
						detail_Ffjl_bean.setDxnr(parser.getAttributeValue(1));
					}
				}
				break;
			}
			eventType = parser.next();
		}
		return detail_Ffjl_bean;
	}
	
	/**
	 * 
	 * 在线申办
	 * 解析事项业务详情温馨提示
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static Detail_Wxts_bean getSubject_Detail_WXTS(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> ConditionDataList = null;
		List<Map<String, Object>> MaterialDataList = null;
		Map<String, Object> ConditionData = null;
		Map<String, Object> MaterialData = null;

		Detail_Wxts_bean detail_Wxts_bean = new Detail_Wxts_bean();

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				ConditionDataList = new ArrayList<Map<String, Object>>();
				MaterialDataList = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("TipMsg")) {
					detail_Wxts_bean.setServiceTargetName(parser
							.getAttributeValue(0));
					detail_Wxts_bean.setApplyDate(parser.getAttributeValue(1));
					detail_Wxts_bean.setServiceSubjectName(parser
							.getAttributeValue(2));
				} else if (parser.getName().equals("ConditionDataInfo")) {
					ConditionData = new HashMap<String, Object>();
					ConditionData.put("name", parser.getAttributeValue(0));
					ConditionData.put("value", parser.getAttributeValue(1));
					ConditionDataList.add(ConditionData);
				} else if (parser.getName().equals("MaterialDataInfo")) {
					MaterialData = new HashMap<String, Object>();
					MaterialData.put("name", parser.getAttributeValue(0));
					MaterialData.put("value", parser.getAttributeValue(1));
					MaterialDataList.add(MaterialData);
				} else if (parser.getName().equals("AdviceMsg")) {
					detail_Wxts_bean.setServiceOrgPhone(parser
							.getAttributeValue(0));
					detail_Wxts_bean.setServiceOrgName(parser
							.getAttributeValue(1));
					detail_Wxts_bean.setServiceOrgAddress(parser
							.getAttributeValue(2));
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("ZsoftInfo")) {
					detail_Wxts_bean.setConditionDataList(ConditionDataList);
					detail_Wxts_bean.setMaterialDataList(MaterialDataList);
				}
				break;
			}
			eventType = parser.next();
		}
		return detail_Wxts_bean;
	}
	
	/*
	 * 
	 * 在线申办*/
	public static List<Map<String, Object>> getSubject_Detail_YWBD(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> list = null;
		Map<String, Object> map = null;
		// String str = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("ServiceSubjectColumn")) {
					map = new HashMap<String, Object>();
					map.put("columnName", parser.getAttributeValue(0));
					map.put("name", parser.getAttributeValue(2));
					String value = "";
					if (parser.getAttributeValue(1).contains(".0")) {
						value = parser.getAttributeValue(1).replace(".0", "");
						map.put("value", value);
					} else {
						map.put("value", parser.getAttributeValue(1));
					}
					map.put("dataType", parser.getAttributeValue(3));
					map.put("length", parser.getAttributeValue(4));
					map.put("nonEmpty", parser.getAttributeValue(5));
					map.put("controlType", parser.getAttributeValue(6));
					map.put("index", parser.getAttributeValue(7));
					map.put("groupName", parser.getAttributeValue(8));
					map.put("groupIndex", parser.getAttributeValue(9));
					map.put("isShowGroupTitle", parser.getAttributeValue(10));
					list.add(map);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return list;
	}
	
	
	/*
	 * 在线申办*/
	public static List<Map<String, Object>> getSubject_Detail_FJCK(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> list = null;
		Map<String, Object> map = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizAttachment")) {
					map = new HashMap<String, Object>();
					map.put("name", parser.getAttributeValue(0));
					map.put("url", parser.getAttributeValue(1));
					list.add(map);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return list;
	}
	
	
	/**
	 * 在线申办
	 * 解析预受理基本信息
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static Detail_Sjdj_bean getSubject_Detail_JBXX_YSL(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		Detail_Sjdj_bean detail_Sjdj_bean = new Detail_Sjdj_bean();
		List<String> list_bltj = new ArrayList<String>();
		List<Map<String, Object>> List_blfztj = null;
		List<String> List_blfztj_2 = new ArrayList<String>();
		Map<String, Object> map_blfztj = null;

		List<Map<String, Object>> List_blcl = null;
		Map<String, Object> map_blcl = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				// oList=new ArrayList<Map<String,Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizReceiptInfo")) {
					detail_Sjdj_bean.setSerialNumber_ysl(parser
							.getAttributeValue(0));
					detail_Sjdj_bean.setServiceTargetName(parser
							.getAttributeValue(1));
					detail_Sjdj_bean.setServiceTargetPhone(parser
							.getAttributeValue(2));
					detail_Sjdj_bean.setServiceTargetEmail(parser
							.getAttributeValue(3));
					String value = "";
					if (parser.getAttributeValue(4).contains(".0")) {
						value = parser.getAttributeValue(4).replace(".0", "");
						detail_Sjdj_bean.setApplyDate_ysl(value);
					} else {
						detail_Sjdj_bean.setApplyDate_ysl(parser
								.getAttributeValue(4));
					}

				} else if (parser.getName().equals("ServiceSubjectBltjInfo")) {
					list_bltj.add(parser.getAttributeValue(0));
				} else if (parser.getName().equals("ServiceSubjectBlfztj")) {
					List_blfztj = new ArrayList<Map<String, Object>>();
				} else if (parser.getName().equals("ServiceSubjectBlfztjFirst")) {
					map_blfztj = new HashMap<String, Object>();
					map_blfztj.put("name", parser.getAttributeValue(0));
					map_blfztj.put("checked", parser.getAttributeValue(2));
					// List_blfztj_2 = new ArrayList<String>();
				} else if (parser.getName()
						.equals("ServiceSubjectBlfztjSecond")) {
					List_blfztj_2.add(parser.getAttributeValue(0));
				} else if (parser.getName().equals("ServiceSubjectBlcl")) {
					List_blcl = new ArrayList<Map<String, Object>>();
				} else if (parser.getName().equals("ServiceSubjectBlclFirst")) {
					map_blcl = new HashMap<String, Object>();
					map_blcl.put("name", parser.getAttributeValue(0));
					map_blcl.put("id", parser.getAttributeValue(1));
					map_blcl.put("yscl", parser.getAttributeValue(2));
					map_blcl.put("identityType", parser.getAttributeValue(3));
					map_blcl.put("imgUrl", parser.getAttributeValue(4));

				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("ServiceSubjectBltj")) {
					detail_Sjdj_bean.setList_bltj(list_bltj);
				}
				if (parser.getName().equals("ServiceSubjectBlfztjFirst")) {
					map_blfztj.put("list_second", List_blfztj_2);
					List_blfztj.add(map_blfztj);
				}
				if (parser.getName().equals("ServiceSubjectBlfztj")) {
					detail_Sjdj_bean.setList_blfztj(List_blfztj);
				}
				if (parser.getName().equals("ServiceSubjectBlclFirst")) {
					List_blcl.add(map_blcl);
				}
				if (parser.getName().equals("ServiceSubjectBlcl")) {
					detail_Sjdj_bean.setList_blcl(List_blcl);
				}
				break;
			}
			eventType = parser.next();
		}
		return detail_Sjdj_bean;
	}
	
	
	/*在线申办*/
	public static List<Map<String, Object>> getDetail_WSBD_ServiceSubjecrFormXmlParse(
			String xml) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> list = null;
		Map<String, Object> map = null;
		// String str = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("ServiceSubjectColumn")) {
					map = new HashMap<String, Object>();
					map.put("columnName", parser.getAttributeValue(0));
					map.put("name", parser.getAttributeValue(2));
					map.put("value", parser.getAttributeValue(1));
					map.put("dataType", parser.getAttributeValue(3));
					map.put("length", parser.getAttributeValue(4));
					map.put("nonEmpty", parser.getAttributeValue(5));
					map.put("controlType", parser.getAttributeValue(6));
					map.put("index", parser.getAttributeValue(7));
					map.put("groupName", parser.getAttributeValue(8));
					map.put("groupIndex", parser.getAttributeValue(9));
					map.put("isShowGroupTitle", parser.getAttributeValue(10));
					list.add(map);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return list;
	}
	
	
	/**
	 * 在线申办
	 * 解析事项业务详情TAB跟按钮显示
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Map<String, Object>> getSubject_Detail_TAB(String xml)
			throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> oList = null;
		Map<String, Object> result = null;

		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "UTF-8");
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				oList = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("BizArchivesInfo")) {
					result = new HashMap<String, Object>();
					result.put("name", parser.getAttributeValue(0));
					result.put("type", parser.getAttributeValue(1));
					result.put("isShow", parser.getAttributeValue(2));
					oList.add(result);
				}
				break;
			}
			eventType = parser.next();
		}
		return oList;
	}
	
	/**
	 * 在线申办
	 * 获取表单
	 * 
	 * @param xml
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Map<String, Object>> getServiceSubjecrFormXmlParse(
			String xml) throws XmlPullParserException, IOException {
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		List<Map<String, Object>> list = null;
		Map<String, Object> map = null;
		// String str = null;
		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<Map<String, Object>>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("ServiceSubjectColumn")) {
					map = new HashMap<String, Object>();
					map.put("columnName", parser.getAttributeValue(0));
					map.put("name", parser.getAttributeValue(1));
					map.put("value", parser.getAttributeValue(2));
					map.put("dataType", parser.getAttributeValue(3));
					map.put("length", parser.getAttributeValue(4));
					map.put("nonEmpty", parser.getAttributeValue(5));
					map.put("controlType", parser.getAttributeValue(6));
					map.put("index", parser.getAttributeValue(7));
					map.put("groupName", parser.getAttributeValue(8));
					map.put("groupIndex", parser.getAttributeValue(9));
					map.put("isShowGroupTitle", parser.getAttributeValue(10));
					map.put("inputControlValueScope",
							parser.getAttributeValue(11));
					list.add(map);
				}
				break;
			case XmlPullParser.END_TAG:
				break;
			}
			eventType = parser.next();
		}
		return list;
	}
	
	
	
}
