package com.example.credit.Utils;

/**
 * Created by alucard on 2016-05-13.
 */
public class URLconstant {
    //真数据
    public static final String URLINSER="http://101.201.211.27:8282/esws.interface/";//所有接口前缀

    public static  final  String GETCITY="baseinfo/querycitys.do";//获取城市接口
    public static final String GETINDUSTRY= "baseinfo/queryindustry.do";//获取行业门类      没参    返回  data    industry：  EC_VALUE：行业门类代码   EC_NAME：行业门类名字

    public static final String SEARCHURL ="baseinfo/querymodelbaseinfo.do";//搜索接口

    public static final String DETAILSCINFOURL ="info/queryinfomessage.do";//获取工商DetailsContent

    public static final String ADMINURL ="eilcCertificate/getEilcCertificate.do";//获取行政Admin

    public static final String HONORURL ="honorifo/getqueryhonorinfo.do";//获取荣誉

    public static final String SUPPORTURL ="esmsupport/getqueryesmsupport.do";//获取扶持

    public static final String PLEDGEURL ="espPledge/getespPledge.do";//获取出质Pledge

    public static final String JUDICIALURL = "forensic/getForensic.do";//司法信息URL

    public static final String GETALERT = "LcWarninginfo/getLcWarninginfo.do";//获取预警信息URL

    public static final String MORTINFO="mortreginfo/getquerymortinfo.do";//抵押信息  动产抵押

    public static final String MORTINFOBDC="mortreginfo/getquerymortinfobdc.do";//抵押信息   不动产抵押


    public static final String PUNISHURL ="zrvcasepubbaseinfo/getqueryzrvcasepubbaseinfo.do";//获取行政处罚Punish

    public static final String ABNORMALURL ="zrvaoopanomaly/getqueryzrvaoopanomaly.do";//获取经营异常

    public static final String PATENTURL ="tblPatent/getTblPatent.do";//获取专利

    public static final String TRADEMARKURL = "trademark/getquerytrademark.do";//商标URLtrademark
    public static final String GETAUTO="myselferinfo/getquerymyselferinfo.do";//自主公示接口

    public static final String COPYRIGHTURL = "patentInfo/getquerypatentInfo.do";//著作URLcopyright

    public static final String ADVERTISEMENTURL = " advertising/getqueryadvertising.do";//广告信息URLAdvertisement
//===========企查查

    public static final String GETCITYLIST="http://app.qichacha.com/enterprises/new/GetCityList";//获取城市接口

    //    public static final String SEARCHURL="http://168s.mobile.hc360.com/get168.cgi";//获取搜索列表
    //public static final String GETINDUSTRY = "http://app.qichacha.com/enterprises/new/getIndustryList";//获取行业接口

//    public static final String PATENTURL = "http://www.zhirong.applinzi.com/editor/attached/file/20160527/20160527092029_66150.txt";//专利URLpatent

//    public static final String PUNISHURL = "http://www.zhirong.applinzi.com/editor/attached/file/20160526/20160526112834_82136.txt";//处罚URLpunish

//    public static final String COPYRIGHTURL = "http://www.zhirong.applinzi.com/editor/attached/file/20160527/20160527092102_82399.txt";//著作URLcopyright

//    public static final String PLEDGEURL = "http://www.zhirong.applinzi.com/editor/attached/file/20160527/20160527091853_75579.txt";//出质URLpledge

//    public static final String HONORURL = "http://www.zhirong.applinzi.com/editor/attached/file/20160527/20160527091752_78425.txt";//荣誉URL

//    public static final String SUPPORTURL = "http://www.zhirong.applinzi.com/editor/attached/file/20160527/20160527091814_22025.txt";//企业扶持URL

//    public static final String ABNORMALURL = "http://www.zhirong.applinzi.com/editor/attached/file/20160527/20160527092007_15820.txt";//经营异常URLAbnormal

//    public static final String GETAUTONOMY = "http://www.zhirong.applinzi.com/editor/attached/file/20160530/20160530091322_24501.txt";//获取自主公示  test

//    public static final String GETALERT = "http://www.zhirong.applinzi.com/editor/attached/file/20160527/20160527091926_64722.txt";//获取预警信息URL

//    public static final String JUDICIALURL = "http://www.zhirong.applinzi.com/editor/attached/file/20160530/20160530101457_32579.txt";//司法信息URL

    public static final String NEWSURL = "http://op.juhe.cn/onebox/news/query?key=c0e7b7d679e557ef030efeaaa7be0b07&q=%E6%B1%9F%E8%A5%BF"/*""*/;//新闻URLObeyed



    //WebService测试专用
//    public static final String TRADEMARKURL = "http://119.29.181.170:8555/WebService.asmx/trademark";//商标URLtrademark

//    public static final String ADVERTISEMENTURL = "http://119.29.181.170:8555/WebService.asmx/AdvertisingQualifications";//广告信息URLAdvertisement

    public static final String OBEYEDURL = "http://119.29.181.170:8555/WebService.asmx/creditData";//守合同重信用信息URLObeyed

}
