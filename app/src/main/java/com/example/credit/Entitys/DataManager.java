package com.example.credit.Entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class DataManager {


    public class ContatUser {//联系人信息
        public Integer user_id;
        public Integer corps_id;
        public String user_name;
        public String gender;
        public String phone_num;
        public String password;
        public String qq_num;
        public String email;
        public Byte status;
        public String detail;
    }

    public class Root {
        public int status;
        public String message;
        public DataS data;
    }

    public class DataS {
        public Paging Paging;
        public List<search> Result;
    }

    public class Paging {
        public int PageSize;
        public int PageIndex;
        public int TotalRecords;
    }

    public static List<search> searchList = new ArrayList<>();//搜索列表集合

    /**
     * 搜索实体类
     */
    public static class search {//搜索列表信息 Result

        public String ENTTYPE;
        public String PRIPID;//主体身份代码
        public String ENTNAME;//企业(机构)名称
        public String REGNO;//注册号
        public String REGORG_CN;//登记机关（中文名称）
        public String NAME;//法定代表人
        public String OPFROM;//经营(驻在)期限自
        public String OPTO;//经营(驻在)期限至
        public String REGSTATE_CN;//登记状态中文名称
        public String C_PROVINCE;//省代码
        public String D_ADDTIME;//更新时间
        public String C_STATE;//经营状态  1 存续（在营，开业，在册）  2 吊销，未注销    3吊销，已注销  4，注销  5，撤销  6，迁出   9，其他
        public String REGCAP;//注册资本(金)  万元
        public String ENTTYPE_CN;//市场主体类型
        public String DOM;//住所
        public String INDUSTRYPHY;//行业代码
        public String INDUSTRYPHY_NAME;//行业代码中文名字
        public String OPSCOPE;//经营范围


       /* public String KeyNo;
        public String Name;//公司名
        public String No;
        public String BelongOrg;
        public String OperName;//法人
        public String StartDate;//注册时间
        public String EndDate;
        public String Status;//存续
        public String Province;
        public String UpdatedDate;
        public String ShortStatus;
        public String CreditCode;
        public String RegistCapi;//注册资金
        public String EconKind;
        public String Address;
        public Industry Industry;
        public List<OriginalName> OriginalName;
        public HitReason HitReason;
        public String Scope;
        public String ContactNumber;
        public String Email;
        public String WebSite;
        public String ImageUrl;
        public String OrgNo;
        public String EnglishName;*/
    }

    public class Industry {
        public String IndustryCode;
        public int SubIndustryCode;
        public String Industry;
        public String SubIndustry;
    }

    public class OriginalName {
        public String Name;
    }

    public class HitReason {
        public String Field;
        public String Value;
    }


//    public static List<citys> citysList = new ArrayList<citys>();//城市集合
//    /**
//     * 企查查城市实体类
//     */
//    public class citys {
//        public String Province;
//        public String City;
//        public String ProvinceCode;
//        public String CityCode;
//    }

    public static List<citys> citysList = new ArrayList<>();

    /**
     * 省份实体类
     */
    public static class citys {
        public String c_code;
        public String c_name;
        public List<citycode> citycode;

    }

    public static List<citycode> citycodeList = new ArrayList<>();

    /**
     * 城市实体类
     */
    public static class citycode {
        public String c_code;
        public String c_name;
    }

    public static List<String> city = new ArrayList<>();//城市数据源


    public static List<industryData> industryDataList = new ArrayList<>();

    /**
     * 行业实体类
     */
    public static class industryData {
        public String EC_VALUE;
        public String EC_NAME;
    }


//    public static List<SubIndustryList> SubyList;
//
//    /**
//     * 行业子类
//     */
//    public class SubIndustryList {
//        public String Code;
//        public String Name;
//        public String Desc;
//    }

    public static List<News> newsList;//新闻list

    /**
     * 新闻实体类
     */
    public static class News {
        public News(String img, String title, String content, String time) {
            this.img = img;
            this.title = title;
            this.content = content;
            this.time = time;
        }

        public String img;
        public String title;
        public String content;
        public String time;

    }

    public static List<Data0> Data0List = new ArrayList<>();

    public class Root0 {
        public String message;
        public int status;
        public Data0 data;
    }

    /**
     * 工商信息类
     */

    public class Data0 {
        public String APPRDATE;//发照日期
        public String ESTDATE;//成立日期
        public String D_ADDTIME;//更新时间
        public String ENTNAME;//企业(机构)名称
        public String REGNO;//注册号
        public String REGORG_CN;//登记机关（中文名称）
        public String NAME;//法定代表人
        public String OPFROM;//经营(驻在)期限自
        public String OPTO;//经营(驻在)期限到
        public String REGSTATE_CN;//登记状态（中文名称）
        public String C_PROVINCE;//省
        public String C_STATE;//经营状态 （1 存续（在营，开业，在册）  2 吊销，未注销    3吊销，已注销  4，注销  5，撤销  6，迁出   9，其他 ）
        public String UNISCID;
        public String REGCAP;//注册资本
        public String ENTTYPE_CN;//市场主体类型（中文名称）
        public String DOM;//住所
        public String INDUSTRYPHY;//行业门类
        public String OPSCOPE;//经营范围
        public String PRIPID;//主体身份代码
        public List<Partners> Partners;//自然人信息
        public List<Employees> Employees;//主要人员信息
        public List<ChangeRecords> ChangeRecords;//工商变更
        public List<AnnualReports> AnnualReports;//分支机构
        public Area Area;//公司所在省市区
        public Company Company;
    }

    /**
     * 自然人信息
     */
    public class Partners {
        public String PRIPID;//主体身份代码
        public String INV;//姓名
        public String INVTYPE_CN;//股东类型
    }

    /**
     * 主要人员信息
     */
    public class Employees {
        public String PERSONID;//主要人员id
        public String NAME;//姓名
        public String POSITION_CN;//职务（中文名称）
        public String CERNO;//件号码/代表证编号（DB320）
    }

    /**
     * 公司所在省市区
     */
    public class Area {
        private String C_PROVINCE;//省
        private String C_CITY;//市
        private String C_COUNTY;//县/区
    }

    /**
     * 工商变更
     */
    public class ChangeRecords {
        public String ALTITEM_CN;//变更事项
        public String ALTBE;//变更前
        public String ALTAF;//变更后
        public String ALTDATE;//变更日期
    }

    public static class ChangeTime {
        public String ALTDATE;//变更日期
        public List<ChangeData> changedata;//变更信息
    }

    public static class ChangeData {//临时仓库
        public String ALTDATE;//变更日期
        public String ALTITEM_CN;//变更事项
        public String ALTBE;//变更前
        public String ALTAF;//变更后

    }

    /**
     * 分支机构
     */
    public class AnnualReports {//分支机构
        public String BRID;//分支机构ID
        public String PPRIPID;//主体身份代码
        public String PRIPID;//分支机构主体身份代码
        public String BRNAME;//分支机构名称
        public String REGNO;//注册号
        public String UNISCID;//统一社会信用代码
        public String REGORG_CN;//登记机关（中文名称）
        public String REGIDATE;//登记日期
    }

    /**
     *
     */
    public class Company {
        public CountInfo CountInfo;
    }

    /**
     *
     */
    public class CountInfo {
        public int sbxx;//商标信息
        public int xzsp;//行政审批
        public int xyxx;//信用信息
        public int xzcf;//行政处罚
        public int zlxx;//专利信息
        public int dyxx;//抵押信息
        public int ggzz;//广告资质
        public int ryxx;//荣誉信息
        public int czxx;//出资信息
        public int zzq;//著作权
        public int fcxx;//扶持信息
        public int jyyc;//经营异常
    }


    /**
     * 抵押信息动产实体类
     */
    public static List<mortgageMP> mortgageMP_List = new ArrayList<>();

    public static class mortgageMP {
        public String MORREG_ID;//抵押登记ID
        public String MORREGCNO;//登记编号
        public String REGIDATE;//登记日期
        public String PUBLICDATE;//公示日期
        public String REGORG_CN;//登记机关
        public String PRICLASECAM;//抵押数额 万元

    }

    /**
     * 抵押信息不动产实体类
     */
    public static List<mortgageRE> mortgageRE_List = new ArrayList<>();

    public static class mortgageRE {
        public String C_INFOID;//登记id
        public String C_DYDJZH;//登记编号
        public String D_DJRQ;//登记日期
        public String D_SQRQ;//公示时间
        public String C_DJJG;//登记机关
        public String C_DBFW;//详情
        // public String valuation;//抵押物估值
    }


    public static List<administraton> ad_List = new ArrayList<>();
    /**
     * 行政审批
     * 行政许可实体类
     */
    public static class administraton {
        public String PRIPID;//主体身份代码
        public String LICNAME;//许可文件名称
        public String LICNO;//许可文件编号
        public String VALFROM;//有效期自
        public String LICANTH;//许可机关
        public String VALTO;//有效期止

    }

    /**
     * 行政审批
     * 其它信息实体类
     */
    public static class admin_other{
        public String OTHER;//暂无信息

    }


    /**
     * 荣誉信息实体类
     */

    public static List<honorInfo> honorInfoList = new ArrayList<>();

    public static class honorInfo {
        public String HONORID;//荣誉id
        public String HONORNAME;//荣誉名称
        public String HONORCONTENT;//荣誉内容（类型）
        public String ORGAN;//机关
        public String C_UNIQUE_CODE;//统一社会信用代码
    }

    /**
     * 企业扶持信息实体类
     */

    public static List<supportInfo> supportInfoList;

    public static class supportInfo {
        public String PRIPID;//主体身份代码
        public String ENJSPAMOUNT;//享受扶持政策的数额
        public String ENJSPCONTENT;//享受扶持政策内容
        public String IMPSPDEPART;//享受扶持政策的的部门
        public String IMPSPDATE;//实施扶持政策日期

    }

    /**
     * 出质信息实体类
     */
    public class Root5 {
        public String status;
        public String message;
        public List<pledgeInfo> data;
    }

    public static List<pledgeInfo> pledgeInfoList;

    public static class pledgeInfo {
        public String REGNO;//股权所在公司注册号
        public String EQUITYNO;//股权登记编号
        public String PLEDGOR;//出质人
        public String PLEDBLICNO;//出质人证照号
        public String PLEDBLICTYPE_CN;//出质人证件类型
        public String IMPAM;//出质股权数额
        public String IMPORG;//质权人
        public String IMPORGBLICNO;//质权人证照号
        public String ENTNAME;//股权所在公司名称
        public String EQUPLEDATE;//股权出质登记日期
        public String PUBLICDATE;//公示日期
        //public String registerAndChangeContent;


    }

    /**
     * 经营异常信息实体类
     */

    public static List<abnormalInfo> abnormalInfoList;

    public static class abnormalInfo {
        public String BUSEXCLIST;//经营异常名录ID
        public String SPECAUSE_CN;//列入经营异常名录原因类型（中文名称）
        public String ABNTIME;//列入日期
        public String DECORG_CN;//列入决定机关（中文名称）
        public String REMEXCPRES_CN;//移出经营异常名录原因（中文名称）
        public String REMDATE;//移出日期
        public String REDECORG_CN;//移出决定机关（中文名称）
    }

    /**
     * 专利信息实体类
     */

    public static List<patentInfo> patentInfoList = new ArrayList<>();

    public static class patentInfo {
        public String PRIPID;//企业ID
        public String PATENTNAME;//专利名称
        public String RCODE;//申请号
        public String RDATE;//申请日期
        public String ACODE;//授权公布号
        public String ADATE;//授权公布日期
        public String INVENTOR;//发明人
        public String PATENTTYPE;//专利类型
        public String AGENCY;//代理机构
        public String LEGALSTATUS;//法律状态
        public String DETAILINFO;//详细信息

    }

    /**
     * 处罚信息实体类
     */
    public class Root8 {
        public String status;
        public String message;
        public List<punishInfo> data;
    }

    public static List<punishInfo> punishInfoList;

    public static class punishInfo {
        public String CASEID;//行政处罚ID
        public String ILLEGACTTYPE;//违法行为类型
        public String PENCONTENT;//处罚内容
        public String PENDECNO;//处罚决定书文号
        public String JUDAUTH;//作出行政处罚决定机关名称
        public String PENDECISSDATE;//作出处罚决定书日期
        public String Remark;//备注
    }

    /**
     * 商标信息实体类
     */
    public static List<trademarkInfo> trademarkInfoList = new ArrayList<>();

    public static class trademarkInfo {
        public String ID;//商标id
        public String PRIPID;//企业id
        public String REGNO;//注册号
        public String APPLICATIONDATE;//申请日期
        public String APPLICANT;//申请人
        public String BRANDSTAUTS;//商标状态
        public String CLASSIFYID;//商标分类号
        public String BRANDIMG;//商标图标链接
        public String AGENCY;//代理机构
        public String LIFESPAN;//使用期限
        public String REGCORE;//商标注册号
        public String BRANDNAME;//商标名称
        public String ENTNAME;//企业名称
        public String UNISCID;//社会统一信用代码
    }



    /**
     * 著作信息实体类
     */

    public static List<copyrightInfo> copyrightInfoeList=new ArrayList<>();

    public static class copyrightInfo {
        public String ID;
        public String REGISTERDATA;//登记日期
        public String REGISTERID;//登记号

        public String WORKNAME;//作品名称
        public String WORKCLASS;//作品类别
        public String FINISHDATE;//创作完成日期
        public String FIRSTDATE;//首次发表日期

        public String SOFTWARENAME;//软件名称
        public String SOFTWARESHORT;//软件简称
        public String STARTINGDATE;//首发日期
    }

    public static class patentInfos {
        public String ID;
        public String WORKNAME;//作品名称
        public String REGISTERDATA;//登记日期
        public String REGISTERID;//登记号
        public String WORKCLASS;//作品类别
        public String FINISHDATE;//创作完成日期
        public String FIRSTDATE;//首次发表日期
    }
    public static class patentInfoSoftwore {
        public static String ID;
        public static String SOFTWARENAME;//软件名称
        public static String REGISTERDATA;//登记日期
        public static String REGISTERID;//登记号
        public static String SOFTWARESHORT;//软件简称
        public static String STARTINGDATE;//首发日期
    }


    public static List<report> reportList = new ArrayList<>();

    /**
     * 自主公示
     * 企业年报实体类
     */
    public static class report {
        public String ANCHEID;//年报id
        public String ANCHEYEAR;//年度报告
        public String ANCHEDATE;//年报时间
        public String url;//h5地址

    }


    public static List<funded> fundedList = new ArrayList<>();

    /**
     * 自主公示
     * 股东出资
     */
    public static class funded {
        public String INVNAME;//股东 发起人名字
        public String LISUBCONAM;//认缴额=认缴出资额
        public String LIACCONAM;//实缴额=实缴出资额
        public String SUBCONFORM;//认缴出资方式
        //public String subscribedNum;//认缴出资额
        public String SUBCONDATE;//认缴出资日期=公示日期
        public String ACCONFORM;//实缴出资方式
        //public String actuallyPaidNum;//实缴出资额
        public String ACCONDATE;//实缴出资日期
        //public String publishDate;//公示日期
    }


    public static List<stock> stockList = new ArrayList<>();

    /**
     * 自主公示
     * 股权变更
     */
    public static class stock {
        public String ALTDATE;//时间
        public String TRANSAMAFT;//变更后
        public String TRANSAMPR;//变更前
    }


    public static List<permit> permitList = new ArrayList<>();

    /**
     * 自主公示
     * 行政许可信息实体类
     */
    public static class permit {
        public String LICNAME_CN;//许可文件名称
        public String LICNO;//许可文件编号
        public String VALFROM;//有效期起
        public String VALTO;//有效期止
        public String LICANTH;//许可机关
        public String PUBLICDATE;//公示日期
        public String LICITEM;//许可内容
        public String invalidDate;//起止时间拼接
    }

    public static List<lore> loreList = new ArrayList<>();

    /**
     * 自主公示
     * 知识产权实体类
     */
    public static class lore {
        /**
         * PLEID : 1
         * PRIPID : 111111
         * ENTNAME : 大魔王
         * REGNO : 2222
         */
        public String PLEID;//出质ID
        public String PRIPID;//主体身份证代码
        public String ENTNAME;//企业机构名称
        public String REGNO;//注册号

    }


    public static List<punish> punishList = new ArrayList<>();

    /**
     * 自主公示
     * 行政处罚信息
     */
    public static class punish {
        public String CASEID;//行政处罚ID
        public String PRIPID;//主体身份代码
        public String ENTNAME;//企业(机构)名称
        public String REGNO;//注册号
        public String UNISCID;//统一社会信用代码
        public String PENDECNO;//处罚决定书文号
        public String ILLEGACTTYPE;//违法行为类型
        public String PENTYPE;//处罚种类
        public String PENTYPE_CN;//处罚种类（中文名称）
        public String PENAM;//罚款金额
        public String FORFAM;//没收金额
        public String PENCONTENT;//处罚内容
        public String JUDAUTH;//作出行政处罚决定机关名称
        public String PENDECISSDATE;//作出处罚决定书日期
        public String PUBLICDATE;//公示日期
        public String REMARK;//备注
    }


//    public static List<paperwork_expire> paperwork_expireList = new ArrayList<>();
//    /**
//     * 证照到期zzdq(证照到期)
//     */
//    public static class paperwork_expire {
//        public String No;//编号
//        public String certificateName;//证件名
//        public String deadline;//证照期间
//        public String daysRemaining;//剩余天数
//
//    }

    public static List<date> pdateList_zzdq = new ArrayList<>();
    public static List<date> pdateList_zzgq = new ArrayList<>();
    public static List<date> pdateList_zlgz = new ArrayList<>();
    public static List<date> pdateList_qdxx = new ArrayList<>();
    public static List<date> pdateList_qsxx = new ArrayList<>();
    public static List<date> pdateList_qxxx = new ArrayList<>();
    /**
     * 	Date[]： 6个type返回字段一样
     */
    public static class date {
        public String WARNID;//数据id
        public String REGNO;//注册号
        public String PRIPID;//企业id
        public String UNISCID;//社会统一信用代码
        public String ENTNAME;//企业名称
        public String WARNDATE;//预警日期
        public String WARNAMOUNT;//预警金额
        public String WARNSTATUS;//预警状态
        public String ORGAN;//预警机关
        public String WARNCONTENT;//预警内容
        public String SOURCE;//数据来源（部门）
        public String STATE;//数据状态（0有效，1无效）
        public String SOURCENAME;//部门名称
        public String SUPDEPARTMENT;//上级部门编码
        public String UPDEPARTMENTNAME;//上级部门名称
        public String BUSINESSATT;//业务属性
        public String UPDATETIME;//更新时间
        public String USERNAME;//上传用户
        public String DEPID;//部门id
        public String DATATYPE;//数据种类

    }

//    /**
//     * 证照过期zzgq(证照过期)
//     */
//    public static List<paperwork_Expired> paperwork_ExpiredList = new ArrayList<>();
//
//    public static class paperwork_Expired {
//        public String No;//编号
//        public String certificateName;//证件名
//        public String deadline;//证照期间
//        public String state;//状态（是否过期）
//    }
//
//    /**
//     * 责令改正 zlgz（责令改正）
//     */
//    public static List<correction> correctionList = new ArrayList<>();
//
//    public static class correction {
//        public String No;//编号
//        public String project;//整改项目名
//        public String centent;//整改内容
//
//    }
//
//    /**
//     * 欠贷信息 qdxx(欠贷信息)
//     */
//    public static List<loan> loanList = new ArrayList<>();
//
//    public static class loan {
//        public String No;//编号
//        public String time;//欠贷时间
//        public String money;//欠贷金额
//        public String bank;//欠贷银行
//        public String status;//欠贷状态（还清、未还清）
//    }
//
//    /**
//     * 欠税信息  qsxx(欠税信息)
//     */
//    public static List<Taxes> taxesList = new ArrayList<>();
//
//    public static class Taxes {
//        public String No;//编号
//        public String time;//欠税日期
//        public String money;//欠税金额
//        public String organ;//征缴机关
//        public String status;//完税状态（完税,未缴）
//    }
//
//    /**
//     * 欠薪信息qxxx(欠薪信息)
//     */
//    public static List<Wages> wagesList = new ArrayList<>();
//
//    public static class Wages {
//        public String No;//编号
//        public String time;//欠薪日期
//        public String money;//欠薪金额
//        public String status;//给薪状态
//        public String source;//信息来源
//    }

    /**
     * 司法信息实体类
     */

    public static List<JudicialDocuments> JudicialDocumentsList;

    public static class JudicialDocuments {//司法文书信息
        public String CASENUM;//判决书文号
        public String SENTENCECONMENT;//判决内容
        public String SUPDEPARTMENT;//作出判决机关
        public String SENTENCEDATE;//作出判决书日期
        public String REDECORG_CN;//详情
    }

    public static List<CrackCredit> CrackCreditList;

    public static class CrackCredit {//失信被执行人信息
        public String COURT_NAME;//执行法院
        public String REG_DATE;//立案时间
        public String COURTCASEID;//法院案件表ID
        public String GIST_CID;//执行依据文号
        public String PERFORMANCE;//被执行人的履行情况
        public String DISREPUT_TYPE_NAME;//类型名称（APP增加此项）
    }

    public static List<ShareholderInformationChange> ShareholderInformationChangeList;

    public static class ShareholderInformationChange {//股东变更信息
        public String ALIEN;//受让人
        public String REGNO;//注册号
        public String FROAUTH;//执行法院
        public String FROAM;//股权数额
        public String INV;//被执行人
    }

    public static List<FrozenInformation> FrozenInformationList;

    public static class FrozenInformation {//股权冻结信息
        public String FROID;//冻结ID
        public String FROFROM;//冻结期限自
        public String FROZDEADLINE;//冻结期限
        public String FROAUTH;//执行法院
        public String INVTYPE_CN;//被执行人
        public String FROAM;//股权数额
    }

    /**
     * 广告信息实体类
     */
    public static List<advertisementInfo> advertisementInfoList;

    public static class advertisementInfo {
        public String ADVERTID;//ID
        public String C_LEVEL;// 广告资质级别
        public String CATEGORY;//类别
        public String IDENTIFYDATE;//认定时间
        public String VALFORM;//有效期自
        public String VALFTO;//有效期至
        public String IDENTIFYORGANS;//认定机关
    }

    /**
     * 守合同重信用信息实体类
     */
    public static List<obeyedInfo> obeyedInfoList;

    public static class obeyedInfo {
        public String PRIPID;//企业id
        public String ENTNAME;//企业名称
        public String REGNO;//注册号
        public String UNISCID;//统一社会信用代码
        public String CONTENT;//内容
        public String IDENTIFYDATE;//认定日期
        public String IDENTIFYORGANS;//认定机关
        public String STATE;//状态，1表示有效，2表示无效

    }


    public static List<Newss> NewssList = new ArrayList<>();

    /**
     * 新闻测试实体类
     */
    public class Newss {
        public String title;
        public String content;
        public String img_width;
        public String full_title;
        public String pdate;
        public String src;
        public String img_length;
        public String img;
        public String url;
        public String pdate_src;
    }

    public class Root11 {
        public String reason;
        public List<Newss> result;
        public int error_code;
    }

    /**
     * 搜索接口返回参数实体类
     */
    public static class baging {
        public String TotalPage;
        public String PageIndex;
        public String showCount;
        public int TotalRecords;
    }

    /**
     * 16个item条数实体类
     */
    public static class allcount{

        public String HonorCount;//荣誉信息
        public String JudiciaryCount;//司法信息
        public String PledgeCount;//抵押
        public String CopyrightCount;//著作权
        public String AnnualCount;//自主公示
        public String AdvertisementCount;//广告资质
        public String BaseInfoCount;//基本信息 工商
        public String ApprovalCount;//赞同  行政审批
        public String PunishCount;//行政处罚
        public String WarningCount;//预警信息
        public String TrademarkCount;//商标
        public String AbnormityCount;//经营异常
        public String CreditCount;//守合同重信用
        public String SupportCount;//扶持
        public String MortgagorCount;//出质
        public String PatentCount;//专利

    }
    //  public static allcount allcounts=new allcount();
    public static List<allcount> allcountsList = new ArrayList<>();

    public static List<Userreview> UserreviewList = new ArrayList<>();
    /**
     * 我的点评列表
     */
    public static class Userreview {
        public String ENTERID;//附加表的企业ID
        public String FAILEDQTY;//点赞吐槽
        public String replay2reviewCount;
        public String MEMBERID;//本评论用户ID
        public String CREATETIME;//评论时间
        public String COMMENTID;//评论ID
        public String CONTENT;//点评内容
        public String SUCCESSQTY;//点赞数量
        public String ISSUCCESS;//是否点赞 0为否，1为是
        public List<Replay2review> replay2review;
        public String MEMBERNAME;//本评论用户名称
        public String ISFAILED;//是否吐槽 0为否，1为是
    }
    public static List<Replay2review> replay2reviewList = new ArrayList<>();

    /**
     * 回复评论（楼中楼）
     */
    public class Replay2review {
        public String CHILDMEMBERID;//用户ID
        public String REPLAYCOMMENT;//回复评论内容
        public String CHILDMEMBERNAME;//用户名称
        public String REPLAYTIME;//回复评论时间
    }
    public class Data201 {
        public List<Userreview> userreview;
        public Paging Paging;
    }
    public class Root201 {
        public String message;
        public int status;
        public Data201 data;
        public String version;
    }





    /**
     * 用户信息实体类
     */
    public static class User{

        /**
         * message : true
         * status : 1
         * data : {"memberList":[{"STATUS":"0","AMOUNT":"","INDUSTRY":"","MOBILE":"","INDUSTRYID":"","USERTYPE":"","SEX":"","EDUCATIONID":"","ISDELETE":"0","ID":"26235a38bad44b4190abfa64bd8b50b5","EDUCATION":"","ICONSTEAM":"","USERNAME":"gzq12321@152.com","EMAIL":"gzq12321@152.com","ALIASNAME":"","PASSWORD":"61278de2e31a5c72860f8b5ef8101c0f0d92bb92","USERTYPEID":""}]}
         * version : v1.0
         */
        public String message;
        public int status;
        public DataBean data;
        public String version;

        public static class DataBean {
            /**
             * STATUS : 状态：激活1、未激活0 对应数字默认0
             * AMOUNT :余额
             * INDUSTRY :行业
             * MOBILE :移动电话
             * INDUSTRYID :行业ID
             * USERTYPE :用户类型
             * SEX :性别
             * EDUCATIONID :教育ID
             * ISDELETE : 账户是否删除 默认0未删除，1已删除
             * ID : 26235a38bad44b4190abfa64bd8b50b5  用户ID
             * EDUCATION :教育
             * ICONSTEAM :头像base64位图
             * USERNAME : gzq12321@152.com  用户名
             * EMAIL : gzq12321@152.com
             * ALIASNAME :别名
             * PASSWORD : 61278de2e31a5c72860f8b5ef8101c0f0d92bb92 密码
             * USERTYPEID :用户类型ID
             */

            public List<MemberListBean> memberList;

            public static class MemberListBean {
                public String STATUS;//状态：激活1、未激活0 对应数字默认0
                public String AMOUNT;//余额
                public String INDUSTRY;//行业
                public String MOBILE;//移动电话
                public String INDUSTRYID;//行业ID
                public String USERTYPE;//用户类型
                public String SEX;//性别
                public String EDUCATIONID;//教育ID
                public String ISDELETE;//账户是否删除 默认0未删除，1已删除
                public String ID;//用户ID
                public String EDUCATION;//教育
                public String ICONSTEAM;//头像base64位图
                public String USERNAME;//用户名
                public String EMAIL;
                public String ALIASNAME;//别名
                public String PASSWORD;//密码
                public String USERTYPEID;//用户类型ID
            }
        }
    }
    public static User user=new User();

    }
