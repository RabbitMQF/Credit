package com.WSZW.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.WSZW.entity.Bitmap_bean;
import com.WSZW.entity.GWLM_DpwD_bean;
import com.WSZW.entity.GWLM_ZwDt_title;
import com.WSZW.entity.GzLm_secondColumn_bean;
import com.WSZW.entity.Login_AppInfo;
import com.WSZW.entity.ZWGK_FirstColumn_bean;
import com.WSZW.entity.ZWGK_formcontent_bean;
import com.WSZW.entity.ZMHD_zx_bean;
import com.WSZW.entity.ZwFw_CX_BizInfo;
import com.WSZW.entity.ZwFw_Fwpy_BizInfo;
import com.WSZW.entity.Zwfw_bmfw_listView_item;
import com.WSZW.entity.Zwfw_Shixiang_List_Item;
import com.WSZW.entity.Zwfw_main_listview_item;


public abstract class Constants {
	// 当前登录账号
		public static String account = "";
	
	
	
	// 当前版本号
	// public static String LOCAL_VERSION_CODE = "";
	public static String title = "main";
	// public static boolean SHOWSEARCH_BUTTON = false;

	// 当前左侧导航栏菜单list
	// public static List<String> list_leftmenu = new ArrayList<String>();

	// 下载的文件保存路径
	public static final String LOCAL_DOWNLOAD_DIR = "downLoad/WSZW/";

	// 分页大小
	public static final int PAGE_SIZE_LARGE = 10, PAGE_SIZE_MEDIUM = 8,
			PAGE_SIZE_SMALL = 6;

	// 服务器类型，0电信，1移动，2联通
	public static final int SERVER_NULL = 0, SERVER_DIANXIN = 1,
			SERVER_YIDONG = 2, SERVER_LIANTONG = 3;
	public static int SERVER_TYPE = SERVER_NULL;

	// 政民互动左侧导航栏菜单list
		public static List<String> list_leftmenu = new ArrayList<String>();

	// 
	public static int TYPE_IF_XZCF = 0;
	public static int COUNT_OF_LIST_MEETING_BEAN = 0;
	public static List<Zwfw_main_listview_item> list_zwfw_zhuti_gr_ztfl = new ArrayList<Zwfw_main_listview_item>(); // 个人办事主题分类主题列表
	public static List<Zwfw_main_listview_item> list_zwfw_zhuti_gr_rssj = new ArrayList<Zwfw_main_listview_item>(); // 个人办事人生事件主题列表
	public static List<Zwfw_main_listview_item> list_zwfw_zhuti_gr_tdqt = new ArrayList<Zwfw_main_listview_item>(); // 个人办事特定群体主题列表
	public static List<Zwfw_main_listview_item> list_zwfw_zhuti_qy_ztfl = new ArrayList<Zwfw_main_listview_item>(); // 企业办事主题分类主题列表
	public static List<Zwfw_main_listview_item> list_zwfw_zhuti_qy_jyhd = new ArrayList<Zwfw_main_listview_item>(); // 企业办事经营活动主题列表
	public static List<Zwfw_main_listview_item> list_zwfw_zhuti_qy_tddx = new ArrayList<Zwfw_main_listview_item>(); // 企业办事特定对象主题列表

	public static List<Zwfw_bmfw_listView_item> Zwfw_bmfw_listview_item= new ArrayList<Zwfw_bmfw_listView_item>();	//部门服务查询主题列表
	public static List<Zwfw_bmfw_listView_item> Zwfw_xzcf_listview_item= new ArrayList<Zwfw_bmfw_listView_item>();	//部门服务查询主题列表

	public static List<Map<String, Object>> list_zwfw_bmfw_xzcf_name = new ArrayList<Map<String, Object>>(); // 政务服务和行政处罚显示列表

	public static List<Zwfw_Shixiang_List_Item> list_zwfw_shixiang = new ArrayList<Zwfw_Shixiang_List_Item>(); // 政务服务事项列表
	public static GWLM_DpwD_bean gzlm_dpwd_bean= new GWLM_DpwD_bean(); // 单篇文档
	public static List<GzLm_secondColumn_bean> gzlm_second_bean= new ArrayList<GzLm_secondColumn_bean>(); // 二级子栏目
	public static Bitmap_bean bitmap_bean= new Bitmap_bean(); // 图片
		
	public static List<Map<String, Object>> list_flash_contents = new ArrayList<Map<String, Object>>(); // 首页flash图片内容
	public static List<ZWGK_FirstColumn_bean> list_firstcolumn_bean = new ArrayList<ZWGK_FirstColumn_bean>(); // 政务公开一级栏目
	public static List<ZWGK_FirstColumn_bean> list_twocolumn_bean = new ArrayList<ZWGK_FirstColumn_bean>(); // 政务公开二级栏目
	
	public static List<ZWGK_formcontent_bean>  list_formcontent_bean = new ArrayList<ZWGK_formcontent_bean>(); // 政务公开 表单
	
	public static int COUNT_OF_LIST_ZWFW_SHIXIANG = 0;
	public static int COUNT_OF_LIST_ZMHD = 0;
	
	public static ZwFw_Fwpy_BizInfo bean_fwpy = new ZwFw_Fwpy_BizInfo();

	public static List<GWLM_ZwDt_title> gzlm_item= new ArrayList<GWLM_ZwDt_title>();
	public static List<ZMHD_zx_bean> zx_item= new ArrayList<ZMHD_zx_bean>();
	
	public static boolean fromYsl = false;// 受理业务是否从预受理跳入受理
	public static List<Map<String, Object>> list_tjcl_for_save_uploaded_pictures = new ArrayList<Map<String, Object>>();
	
	// 收件登记中提交材料列表
	public static List<Map<String, Object>> list_tjcl = new ArrayList<Map<String, Object>>();
	    // 收件登记中提交材料列表
		public static List<Map<String, Object>> list_middle = new ArrayList<Map<String, Object>>();
		public static List<Login_AppInfo> list_appinfo = new ArrayList<Login_AppInfo>();
		//判定上传图片接口是否正常
		public static boolean imgbool;
	
	
	
}
