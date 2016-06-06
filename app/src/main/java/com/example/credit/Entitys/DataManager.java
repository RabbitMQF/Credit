package com.example.credit.Entitys;

import android.widget.TextView;

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
        public String PRIPID;//主体身份代码
        public String entname;//企业(机构)名称
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

    public static List<citys> citysList=new ArrayList<>();
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


    public static List<industryData> industryDataList=new ArrayList<>();

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
    public static List<mortgageMP> mortgageMP_List = new ArrayList<mortgageMP>();

    public static class mortgageMP {
        public String no;//登记编号
        public String register_date;//登记日期
        public String public_date;//公示时间
        public String debts;//被担保债权数额
        public String office;//登记机关
        public String detail;//详情

    }

    /**
     * 抵押信息不动产实体类
     */
    public static List<mortgageRE> mortgageRE_List = new ArrayList<mortgageRE>();

    public static class mortgageRE {
        public String no;//登记编号
        public String register_date;//登记日期
        public String public_date;//公示时间
        public String debts;//被担保债权数额
        public String office;//登记机关
        public String collateral;//抵押物
        public String valuation;//抵押物估值
    }

    /**
     * 行政许可实体类
     */
    public static List<administraton> ad_List = new ArrayList<administraton>();

    public static class administraton {
        public String aname;//许可文件名称
        public String no;//许可证号
        public String dates;//有效期
        public String office;//许可机关
        public String times;//发证日期
        public String contents;//许可内容

    }


    /**
     * 荣誉信息实体类
     */

    public static List<honorInfo> honorInfoList;

    public static class honorInfo {
        public String No;
        public String name;
        public String type;
        public String organs;
        public String detail;


    }

    /**
     * 企业扶持信息实体类
     */

    public static List<supportInfo> supportInfoList;

    public static class supportInfo {
        public String No;
        public String supportNum;
        public String content;
        public String department;
        public String supportDate;


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
        public String No;
        public String registerNum;
        public String pledgor;
        public String pledgorCertificateNum;
        public String pledgedEquityNum;
        public String pledgee;
        public String pledgeeCertificateNum;
        public String equityPledge;
        public String setRegisterDate;
        public String status;
        public String registerAndChangeTime;
        public String registerAndChangeContent;


    }

    /**
     * 经营异常信息实体类
     */

    public static List<abnormalInfo> abnormalInfoList;

    public static class abnormalInfo {
        public String No;
        public String why;
        public String date;
        public String remove;
        public String removeDate;
        public String decision;
    }

    /**
     * 专利信息实体类
     */

    public static List<patentInfo> patentInfoList;

    public static class patentInfo {
        public String No;
        public String patentName;
        public String applyNum;
        public String applyDate;
        public String applyPublishNum;
        public String applyPublishDate;
        public String inventor;
        public String type;
        public String patentAgency;
        public String LegalStatus;
        public String abstracts;
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
        public String No;
        public String type;
        public String centent;
        public String documentNumber;
        public String organs;
        public String time;
        public String detail;
    }

    /**
     * 商标信息实体类
     */
    public static List<trademarkInfo> trademarkInfoList = new ArrayList<>();

    public static class trademarkInfo {
        public String No;
        public String registeredName;
        public String iconUrl;
        public String applyNum;
        public String applyDate;
        public String RecognizedAuthority;
        public String applyPublishDate;
        public String identifiedDate;

    }

    /**
     * 著作信息实体类
     */
    public class Root12 {
        public String status;
        public String message;
        public List<copyrightInfo> data;
    }

    public static List<copyrightInfo> copyrightInfoList;

    public static class copyrightInfo {
        public String No;
        public String literatureName;
        public String registerDate;
        public String registerNum;
        public String category;
        public String literatureCompleteData;
        public String firstPublishDate;

    }


    public static List<report> reportList = new ArrayList<>();

    /**
     * 企业年报实体类
     */
    public static class report {
        public String NO;//编号
        public String name;//标题
        public String time;//时间
        public String url;//h5地址

    }


    public static List<funded> fundedList = new ArrayList<>();

    /**
     * 股东出资
     */
    public static class funded {
        public String shareholdersName;//股东
        public String subscribedNums;//认缴额
        public String actuallyPaidNums;//实缴额
        public String subscribedWay;//认缴出资方式
        public String subscribedNum;//认缴出资额
        public String subscribedDate;//认缴出资日期
        public String actuallyPaidWay;//实缴出资方式
        public String actuallyPaidNum;//实缴出资额
        public String actuallyPaidDate;//实缴出资日期
        public String publishDate;//公示日期
    }


    public static List<stock> stockList = new ArrayList<>();

    /**
     * 股权变更
     */
    public static class stock {
        public String changeDate;//时间
        public String afterChange;//变更后
        public String beforeChange;//变更前
    }


    public static List<permit> permitList = new ArrayList<>();

    /**
     * 行政许可信息实体类
     */
    public static class permit {
        public String licenseName;//许可文件名称
        public String licenseNum;//许可证号
        public String invalidDate;//有效期
        public String licenseDepart;//许可机关
        public String sendDate;//发证日期
        public String licensedContent;//许可内容
    }

    /**
     * 证照到期
     */
    public static List<paperwork_expire> paperwork_expireList = new ArrayList<>();

    public static class paperwork_expire {
        public String No;//编号
        public String certificateName;//证件名
        public String deadline;//证照期间
        public String daysRemaining;//剩余天数

    }

    /**
     * 证照过期
     */
    public static List<paperwork_Expired> paperwork_ExpiredList = new ArrayList<>();

    public static class paperwork_Expired {
        public String No;//编号
        public String certificateName;//证件名
        public String deadline;//证照期间
        public String state;//状态（是否过期）
    }

    /**
     * 责令改正
     */
    public static List<correction> correctionList = new ArrayList<>();

    public static class correction {
        public String No;//编号
        public String project;//整改项目名
        public String centent;//整改内容

    }

    /**
     * 欠贷信息
     */
    public static List<loan> loanList = new ArrayList<>();

    public static class loan {
        public String No;//编号
        public String time;//欠贷时间
        public String money;//欠贷金额
        public String bank;//欠贷银行
        public String status;//欠贷状态（还清、未还清）
    }

    /**
     * 欠税信息
     */
    public static List<Taxes> taxesList = new ArrayList<>();

    public static class Taxes {
        public String No;//编号
        public String time;//欠税日期
        public String money;//欠税金额
        public String organ;//征缴机关
        public String status;//完税状态（完税,未缴）
    }

    /**
     * 欠薪信息
     */
    public static List<Wages> wagesList = new ArrayList<>();

    public static class Wages {
        public String No;//编号
        public String time;//欠薪日期
        public String money;//欠薪金额
        public String status;//给薪状态
        public String source;//信息来源
    }

    /**
     * 司法信息实体类
     */

    public static List<JudicialDocuments> JudicialDocumentsList;

    public static class JudicialDocuments {//司法文书信息
        public String No;
        public String centent;
        public String number;
        public String unit;
        public String decisiontime;
        public String detail;
    }

    public static List<CrackCredit> CrackCreditList;

    public static class CrackCredit {//失信被执行人信息
        public String No;
        public String court;
        public String jurisdictionTime;
        public String caseNumber;
        public String referenceNumber;
        public String performCondition;
        public String peopleCondition;
        public String detail;
    }

    public static List<ShareholderInformationChange> ShareholderInformationChangeList;

    public static class ShareholderInformationChange {//股东变更信息
        public String No;
        public String executedPerson;
        public String equityAmount;
        public String acceptPerson;
        public String courtOfExecution;
        public String detail;
    }

    public static List<FrozenInformation> FrozenInformationList;

    public static class FrozenInformation {//股权冻结信息
        public String No;
        public String time;
        public String term;
        public String company;
        public String shareholderName;
        public String money;
        public String detail;

    }
    /**
     * 广告信息实体类
     */
    public static List<advertisementInfo> advertisementInfoList;

    public static class advertisementInfo {
        public String No;
        public String level;
        public String type;
        public String validity;
        public String identifiedDate;
        public String RecognizedAuthority;

    }

    /**
     * 守合同重信用信息实体类
     */
    public static List<obeyedInfo> obeyedInfoList;

    public static class obeyedInfo {
        public String No;
        public String publicName;
        public String publicDate;

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


}
