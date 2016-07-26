package com.WSZW.data;

import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref;
import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref.Scope;

@SharedPref(value = Scope.APPLICATION_DEFAULT)
public interface InfoFile {
	String urlCenterWs(); // 服务器地址

	String zhutiID(); // 用于获取事项列表 的主题ID

	String zhutiName(); // 用户获取事项列表的主题name
	
	String shixiang_name();	//事项列表中点击的事项名称
	
	String shixiang_id();	//用于获取事项详情的事项ID
	
	String xzzq_num();		//行政职权点击的图标编号
	
	String edittext_1_zwfw_search();
	
	String edittext_2_zwfw_search();

	String gzlm_url(); //公众栏目URL
	String zwgk_url(); //政务共公开栏目URL
	String chanIds();  //chanIds
	String chanDocId();  //chanDocId
	String gzlm_title(); //公众栏目标题
	String writeTime(); //发布时间
	String summary();  //
	String title();  //
	String id();  //
	String organId();  //政务公开Id
	String prefixURL();//政务公开前缀地址
	String searchNo();
	String lettertypeName();
	String replyTimeString();
	String content();
	String replyContent();
	String deliverTime();
	String score();
	String result();
	String infoCookie();
 boolean isAutoLogin();//是否自动登录
	 String infoUsername();//用户名
	String infoUserId();//用户ID
	// String infoPassword();//密码
	// String numOfYslzs(); //预受理总数
	// String numOfDspzs(); //待审批总数
	// String numOfDffzs(); //待发放总数
	// String numOfDqwbjzs(); //到期未办结总数
	String serviceSubjectId();//事项id
	String serviceSubjectName();//事项名称
	String postinfoname();//提交材料名
	String postinfoid();//提交材料id
	String postinfotype();//提交材料类型
	 String shengqingrenname();//申请人姓名
	String shengqingrenCardId();//申请人身份证号
	 String yslyw_shengqingrenCardId();//预受理业务申请人身份证号
	 String shengqingriqi();//申请日期
	 String banjieriqi();//办结日期
	 String wssq_wsyslh();//网上预受理号
	 String baselist_yewuliushuihao();//业务流水号
	 String jgff_yewuliushuihao();//业务流水号
	 String dxtz_beizhu();//短信通知里面的备注
	 String dxtz_shibaiyuanyin();//短信通知里面的失败原因
	 String ZTYWkaishishijian();//暂停业务开始时间
	 String ZTYWyuanyin();//暂停业务原因
	 String bdlr_ywlsh(); //表单录入需要的业务流水号
	 String bizArchivesId();//各项信息详情id
	 String bz_sqr_phone();//补正申请人电话
	 String bz_msg();//补正短信
	 String bz_slczy();//补正受理操作员
	 String bz_yy();//补正原因
	 String dfflb_bjzt();//待发放业务办结状态
}
