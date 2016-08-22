package com.example.credit.Entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class DataManager {

    public static String StringZero="";//公司
    public static String String0="";//工商
    public static String String1="";//
    public static String String2="";//
    public static String String3="";//
    public static String String4="";//
    public static String String5="";//
    public static String String6="";//
    public static String String7="";//
    public static String String8="";//
    public static String String9="";//
    public static String String10="";//
    public static String String11="";//
    public static String String12="";//
    public static String String13="";//
    public static String String14="";//
    public static String String15="";//
    public static String String16="";//
    public static String String17="";//
    public static String String18="";//
    public static String String19="";//
    public static String String20="";//
    public static String String21="";//
    public static String String22="";//
    public static String String23="";//


    public static MyComm MyComms=new MyComm();

    /**
     *我的评价
     */
    public static class MyComm {
        public String message;
        public String status;
        public DataBean data;
        public static class DataBean {
            public List<CommentListBean> commentList;

            public static class CommentListBean {
                public String FAILEDQTY;
                public String ENTERID;//附加表的企业ID
                public String REGNORE;
                public String CONTENT;//点评内容
                public String ENTNAME;
                public String CREATETIME;//评论时间
                public String PRIPID;
                public String COMMENTID;//评论ID
                public String ISSUCCESS;
                public String SUCCESSQTY;
                public String ENTTYPE;
                public String ISFAILED;
            }
        }
    }

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
    public static List<search> searchListMore = new ArrayList<>();//搜索更多列表集合
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
        public Object D_ADDTIME;//更新时间
        public String C_STATE;//经营状态  1 存续（在营，开业，在册）  2 吊销，未注销    3吊销，已注销  4，注销  5，撤销  6，迁出   9，其他
        public String REGCAP;//注册资本(金)  万元
        public String ENTTYPE_CN;//市场主体类型
        public String DOM;//住所
        public String INDUSTRYPHY;//行业代码
        public String INDUSTRYPHY_NAME;//行业代码中文名字
        public String OPSCOPE;//经营范围

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


    public static GSXX gsxx = new GSXX();

    /**
     * 工商信息类
     */
    public static class GSXX {
        public String message;
        public String status;
        public DataBean data;

        public static class DataBean {
            public AreaBean Area;
            public CompanyBean Company;
            public BaseInfoBean BaseInfo;
            public List<AnnualReports> AnnualReports;
            public List<PartnersBean> Partners;
            public List<ChangeRecordsBean> ChangeRecords;
            public List<EmployeesBean> Employees;

            /**
             * 公司所在省市区
             */
            public static class AreaBean {
                public String C_COUNTY;//县/区
                public String C_CITY;//市
                public String C_PROVINCE;//省
            }

            public static class CompanyBean {
                public CountInfoBean CountInfo;

                public static class CountInfoBean {
                    public int sbxx;
                    public int xzsp;
                    public int xyxx;
                    public int xzcf;
                    public int zlxx;
                    public int dyxx;
                    public int ggzz;
                    public int ryxx;
                    public int czxx;
                    public int zzq;
                    public int fcxx;
                    public int jyyc;
                }
            }

            public static class BaseInfoBean {
                public String D_ADDTIME;//更新时间
                public String REGCAP;//注册资本
                public String NAME;//法定代表人
                public String ESTDATE;//成立日期
                public String REGSTATE_CN;//登记状态（中文名称）
                public String OPTO;//经营(驻在)期限至
                public String ENTTYPE;//9999=个体工商户
                public String INDUSTRYPHY;//行业门类
                public String REGNO;//注册号
                public String UNISCID;//统一社会信用代码
                public String DOM;//住所
                public String REGORG_CN;//登记机关（中文名称）
                public String APPRDATE;
                public String ENTTYPE_CN;//市场主体类型（中文名称）
                public String ENTNAME;//企业(机构)名称
                public String OPSCOPE;//经营范围
                public String PRIPID;//主体身份代码
                public String C_STATE;//经营状态 （1 存续（在营，开业，在册）  2 吊销，未注销    3 吊销，已注销  4，注销  5，撤销  6，迁出   9，其他 ）
                public String OPFROM;//经营(驻在)期限自   起
                public String C_PROVINCE;//省
            }

            /**
             * 自然人信息
             */
            public static class PartnersBean {
                public String INV;//姓名
                public String PRIPID;//主体身份代码
                public String INVTYPE_CN;//股东类型
            }

            /**
             * 工商变更
             */
            public static class ChangeRecordsBean {
                public String ALTITEM_CN;
                public String ALTAF;
                public String ALTDATE;
                public String ALTBE;
            }

            /**
             * 主要人员信息
             */
            public static class EmployeesBean {
                public String NAME;//姓名
                public String CERNO;//件号码/代表证编号（DB320）
                public String POSITION_CN;//职务（中文名称）
                public String PERSONID;//主要人员id
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
        }
    }


    /**
     * 工商变更临时
     */

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


    public static Mychattel MychattelS=new Mychattel();
    /**
     * 抵押信息动产实体类mortgageMP_List
     */
    public static class Mychattel {
        public DataBean data;
        public static class DataBean {
            public PagingBean Paging;
            /**
             * REGIDATE : 2016-08-11
             * REGORG_CN :
             * PRICLASECAM :
             * PUBLICDATE :
             * MORREG_ID : 123456
             * MORREGCNO :
             */
            public List<ChattelBean> chattel;
            public static class PagingBean {
                public int TotalPage;
                public int ShowCount;
                public int TotalResult;
                public int CurrentResult;
                public int CurrentPage;
            }
            public static class ChattelBean {
                public String MORREG_ID;//抵押登记ID
                public String MORREGCNO;//登记编号
                public String REGIDATE;//登记日期
                public String PUBLICDATE;//公示日期
                public String REGORG_CN;//登记机关
                public String PRICLASECAM;//抵押数额 万元
            }
        }
    }
    public static MyrealEstate MyrealEstateS=new MyrealEstate();
    /**
     * 抵押信息不动产实体类mortgageRE_List
     */
    public static class MyrealEstate {
        public DataBean data;
        public static class DataBean {
            public PagingBean Paging;
            public List<realEstateBean> realEstate;
            public static class PagingBean {
                public int TotalPage;
                public int ShowCount;
                public int TotalResult;
                public int CurrentResult;
                public int CurrentPage;
            }
            public static class realEstateBean {
                public String C_INFOID;//登记id
                public String C_DYDJZH;//登记编号
                public String D_DJRQ;//登记日期
                public String D_SQRQ;//公示时间
                public String C_DJJG;//登记机关
                public String C_DBFW;//详情
                // public String valuation;//抵押物估值
            }
        }
    }





    public static List<administraton> ad_List = new ArrayList<>();
    public static List<admin_other> admin_other_List = new ArrayList<>();
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
    public static class admin_other {
        public String LICANTH;//许可机关
        public String REGNO;//注册号
        public String VALFROM;//有效期自
        public String LICNAME_CN;//许可文件名称（中文名称）
        public String LICID;//其他部门行政许可信息
        public String ENTNAME;//名称
        public String LICNO;//许可文件编号
        public String VALTO;//有效期至
        public String PRIPID;//主体身份代码
        public String TYPE;//状态(  1有效   2无效)
        public String LICITEM;//许可内容

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




    public static supportInfo supportInfoS=new supportInfo();

    public static class supportInfo {
        public String message;
        public String status;
        public DataBean data;
        public String version;
        public static class DataBean {
            /**
             * IMPSPDEPART : 工商局
             * ENJSPCONTENT : 新开企业补贴
             * ENJSPAMOUNT : 100
             * IMPSPDATE : 2015-06-08
             * PRIPID : 3606222010112300036792
             */
            public List<AdvertisingBean> advertising;
            public static class AdvertisingBean {
                public String PRIPID;//主体身份代码
                public Double ENJSPAMOUNT;//享受扶持政策的数额
                public String ENJSPCONTENT;//享受扶持政策内容
                public String IMPSPDEPART;//享受扶持政策的的部门
                public String IMPSPDATE;//实施扶持政策日期

            }
        }

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

    public static abnormalInfo abnormalInfoS=new abnormalInfo();

    public static class abnormalInfo {
        public String message;
        public String status;
        public DataBean data;
        public static class DataBean {
            public  List<AbNormalBean> abNormal;
            public  class AbNormalBean {
                public String BUSEXCLIST;//经营异常名录ID
                public String SPECAUSE_CN;//列入经营异常名录原因类型（中文名称）
                public String ABNTIME;//列入日期
                public String DECORG_CN;//列入决定机关（中文名称）
                public String REMEXCPRES_CN;//移出经营异常名录原因（中文名称）
                public String REMDATE;//移出日期
                public String REDECORG_CN;//移出决定机关（中文名称）
            }
        }
    }




    /**
     * 专利信息实体类
     */

    public static PatentInfo PatentInfoS=new PatentInfo();
    public static class PatentInfo {
        public String message;
        public String status;
        public DataBean data;
        public String version;
        public static class DataBean {
            public PagingBean Paging;
            public List<PatentInfoBean> patentInfo;
            public static class PagingBean {
                public int TotalPage;
                public int ShowCount;
                public int TotalResult;
                public int CurrentResult;
                public int CurrentPage;
            }
            public static class PatentInfoBean {
                public String ID;//专利ID   后加
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
                public String ABSTRACTGRAPH;//图片地址
                public String ENTNAME;//公司名称 后加

            }
        }
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

    public static trademarkModel trademarkModelS=new trademarkModel();
    /**
     * 商标信息实体类
     */
    public static class trademarkModel{
        public String message;
        public String status;
        public DataBean data;
        public String version;
        public static class DataBean {
            public PagingBean Paging;
            public List<TrademarkBean> trademark;
            public static class PagingBean {
                public int TotalPage;
                public int ShowCount;
                public int TotalResult;
                public int CurrentResult;
                public int CurrentPage;
            }

            public static class TrademarkBean {
                public String BRANDLIST;
                public String PROCEDURE;
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
        }

    }


    /**
     * 著作信息实体类
     */

    public static List<copyrightInfo> copyrightInfoeList = new ArrayList<>();

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

  /*  *//**
     * 新自主公示
     *//*
    public static class autonomy{

    }*/
    public static List<report> reportList = new ArrayList<>();

    /**
     * 自主公示
     * 企业年报实体类
     */
    public static class report {
        /**
         * ANCHEDATE : 2016-06-15
         * ANCHEYEAR : 2015
         * ANCHEID : 7dde62bb16ba427c99f19f127e67163a
         */
        public String ANCHEDATE;//年报时间
        public String ANCHEYEAR;//年度报告
        public String ANCHEID;//年报id

    }


    public static List<funded> fundedList = new ArrayList<>();

    /**
     * 自主公示
     * 股东出资
     */
    public static class funded {
        /**
         * ACCONFORM_CN : 股权
         * ACCONDATE : 2016-06-28 00:00:00
         * ACCONFORM : 5
         * CONDATE : 2016-06-28 00:00:00
         * CONFORM : 3
         * SUBCONAM : 300
         * ACCONAM : 200
         * CONFORM_CN : 知识产权
         * INV : 他们
         * PUBLICDATE : 2016-06-28 00:00:00
         * ACPUBLICDATE : 2016-06-28 00:00:00
         */

        public String ACCONFORM_CN;//实缴出资方式CN
        public String ACCONDATE;//实缴出资日期
        public String ACCONFORM;//实缴出资方式
        public String CONDATE;
        public String CONFORM;//认缴出资方式
        public double SUBCONAM;//认缴额=认缴出资额
        public double ACCONAM;//实缴额=实缴出资额
        public String CONFORM_CN;//认缴出资方式CN
        public String INV;//股东 发起人名字
        public String PUBLICDATE;//认缴出资日期=公示日期
        public String ACPUBLICDATE;//实缴公示日期


//        public String INV;//股东 发起人名字
//        public String SUBCONAM;//认缴额=认缴出资额
//        public String ACCONAM;//实缴额=实缴出资额
//        public String CONFORM_CN;//认缴出资方式
//        //public String subscribedNum;//认缴出资额
//        public String PUBLICDATE;//认缴出资日期=公示日期
//        public String ACCONFORM_CN;//实缴出资方式
//        //public String actuallyPaidNum;//实缴出资额
//        public String ACCONDATE;//实缴出资日期
//        //public String publishDate;//公示日期
    }


    public static List<stock> stockList = new ArrayList<>();

    /**
     * 自主公示
     * 股权变更
     */
    public static class stock {
        public String REGNO;//注册号
        public String ALTAF;//变更后
        public String ENTNAME;//企业(机构)名称
        public String INVUID;//出资信息修改ID
        public String UNISCID;//统一社会信用代码
        public String PRIPID;//主体身份代码
        public String ALITEM;//变更事项
        public String ALTDATE;//变更日期
        public String ALTBE;//变更前
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
         * TYPENAME : 有效
         * TMNAME :
         * INVALIDDATE :
         * PLEREGPERFROM : 2016-06-14
         * EQUPLECANREA :
         * CANDATE :
         * UNISCID :
         * KINDS : 3
         * PLEREGPERTO : 2016-07-04
         * REGNO : 360103210025958
         * TMREGNO :
         * PLEDGOR : lkjl
         * PLEID : f4584e3689ad43ef8a016cd40f0bd935
         * ENTNAME : 江西智容科技有限公司
         * INVALIDREA :
         * PRIPID : 3601032011041300098564
         * PUBLICDATE : 2016-06-22
         * IMPORG : mjhk
         * TYPE : 1
         */

        public String TYPENAME;      //状态名称
        public String TMNAME;        //名称
        public String INVALIDDATE;     //其他无效日期
        public String PLEREGPERFROM;      //知识产权质权登记期限自
        public String EQUPLECANREA;      //注销原因
        public String CANDATE;       //注销日期
        public String UNISCID;      //统一社会信用代码
        public String KINDS;         //种类代码
        public String PLEREGPERTO;    //知识产权质权登记期限止
        public String REGNO;          //注册号
        public String TMREGNO;        //商品注册号
        public String PLEDGOR;       //知识产权出质人名称
        public String PLEID;           //出质ID
        public String ENTNAME;          //企业机构名称
        public String INVALIDREA;       //其他无效原因
        public String PRIPID;          //主体身份证代码
        public String PUBLICDATE;          //公示日期
        public String IMPORG;          //知识产权质权人名称
        public String TYPE;            //状态

//        public String PLEID;//出质ID
//        public String PRIPID;//主体身份证代码
//        public String ENTNAME;//企业机构名称
//        public String REGNO;//注册号
//        public String UNISCID;//统一社会信用代码
//        public String TMREGNO;//商品注册号
//        public String TMNAME;//名称
//        public String KINDS;//种类代码
//        public String PLEDGOR;//知识产权出质人名称
//        public String IMPORG ;//知识产权质权人名称
//        public String PLEREGPERFROM;//知识产权质权登记期限自
//        public String PLEREGPERTO;//知识产权质权登记期限止
//        public String TYPE;//状态
//        public String TYPENAME;//状态名称
//        public String CANDATE;//注销日期
//        public String EQUPLECANREA;//注销原因
//        public String INVALIDDATE;//其他无效日期
//        public String INVALIDREA;//其他无效原因
//        public String PUBLICDATE;//公示日期




    }


    public static List<punish> punishList = new ArrayList<>();

    /**
     * 自主公示
     * 行政处罚信息
     */
    public static class punish {
        /**
         * PENTYPE_CN : 责令停业整顿
         * REMARK : 啊实打实的
         * UNISCID :
         * PENDECISSDATE : 2016-06-14
         * PENAM :
         * CASEID : 83e5c4d5236049b9a693aae7015cb9f2
         * REGNO : 360103210025958
         * JUDAUTH : 省工商局
         * ENTNAME : 江西智容科技有限公司
         * PENDECNO : 呵呵呵
         * PENTYPE : 4
         * FORFAM :
         * ILLEGACTTYPE : 呃呃呃
         * PENCONTENT :
         * PRIPID : 3601032011041300098564
         * PUBLICDATE : 2016-06-22
         */

        public String PENTYPE_CN;       //处罚种类（中文名称）
        public String REMARK;            //备注
        public String UNISCID;          //统一社会信用代码
        public String PENDECISSDATE;    //作出处罚决定书日期
        public Double PENAM;            //罚款金额
        public String CASEID;         //行政处罚ID
        public String REGNO;           //注册号
        public String JUDAUTH;        //作出行政处罚决定机关名称
        public String ENTNAME;        //企业(机构)名称
        public String PENDECNO;         //处罚决定书文号
        public String PENTYPE;        //处罚种类
        public Double FORFAM;         //没收金额
        public String ILLEGACTTYPE;    //违法行为类型
        public String PENCONTENT;       //处罚内容
        public String PRIPID;          //主体身份代码
        public String PUBLICDATE;      //公示日期

//        public String CASEID;//行政处罚ID
//        public String PRIPID;//主体身份代码
//        public String ENTNAME;//企业(机构)名称
//        public String REGNO;//注册号
//        public String UNISCID;//统一社会信用代码
//        public String PENDECNO;//处罚决定书文号
//        public String ILLEGACTTYPE;//违法行为类型
//        public String PENTYPE;//处罚种类
//        public String PENTYPE_CN;//处罚种类（中文名称）
//        public String PENAM;//罚款金额
//        public String FORFAM;//没收金额
//        public String PENCONTENT;//处罚内容
//        public String JUDAUTH;//作出行政处罚决定机关名称
//        public String PENDECISSDATE;//作出处罚决定书日期
//        public String PUBLICDATE;//公示日期
//        public String REMARK;//备注
    }


    public static AlertInfo AlertInfoS=new AlertInfo();
    public static class AlertInfo {
        public String message;
        public String status;
        public String version;
        public List<DataBean> data;

        public static class DataBean {
            public String type;
            public List<date> data;
            /**
             * Date[]： 6个type返回字段一样
             */
            public static class date {
                public String D_AD;

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
        }


    }

    /**
     * 司法信息实体类
     */

    public static List<JudicialDocuments> JudicialDocumentsList=new ArrayList<>();

    public static class JudicialDocuments {//司法文书信息
        public String CASENUM;//判决书文号
        public String SENTENCECONMENT;//判决内容
        public String SUPDEPARTMENT;//作出判决机关
        public String SENTENCEDATE;//作出判决书日期
        public String REDECORG_CN;//详情
    }

    public static List<CrackCredit> CrackCreditList=new ArrayList<>();

    public static class CrackCredit {//失信被执行人信息
        public String COURT_NAME;//执行法院
        public String REG_DATE;//立案时间
        public String COURTCASEID;//法院案件表ID
        public String GIST_CID;//执行依据文号
        public String PERFORMANCE;//被执行人的履行情况
        public String DISREPUT_TYPE_NAME;//类型名称（APP增加此项）

        public String DATARESOURCE;
        public String STATE;
        public String CASE_CODE;
        public String INAME;
        public String PUBLISH_DATE;
        public String GIST_UNIT;
        public String REGNORE;
        public String AREA_NAME;
        public String BUESINESSENTITY;
        public String DELETEMARK;
        public String DUTY;//具体详情
        public String CARDTYPE;
    }

    public static List<ShareholderInformationChange> ShareholderInformationChangeList=new ArrayList<>();

    public static class ShareholderInformationChange {//股东变更信息
        public String ALIEN;//受让人
        public String REGNO;//注册号
        public String FROAUTH;//执行法院
        public Double FROAM;//股权数额
        public String INV;//被执行人
    }

    public static List<FrozenInformation> FrozenInformationList=new ArrayList<>();

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
     * {"message":"true","status":"1","data":{"advertising":[{"ADVERTID":"1","CATEGORY":"1","C_LEVEL":"a","IDENTIFYDATE":"2013-05-30",
     * "Validity":"null-2013-05-01","VALFORM":"2013-03-12","SOURCENAME":"豆腐干","IDENTIFYORGANS":"工商局","VALTO":"2013-05-01"}]}}
     */
    public static List<advertisementInfo> advertisementInfoList=new ArrayList<>();

    public static class advertisementInfo {
        public String Validity;
        public String SOURCENAME;

        public String ADVERTID;//ID
        public String C_LEVEL;// 广告资质级别
        public String CATEGORY;//类别
        public String IDENTIFYDATE;//认定时间
        public String VALFORM;//有效期自
        public String VALTO;//有效期至
        public String IDENTIFYORGANS;//认定机关
    }

    /**
     * 守合同重信用信息实体类
     */
    public static List<obeyedInfo> obeyedInfoList=new ArrayList<>();

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


    public static MyNews MyNewsS=new MyNews();//初始新闻信息
    public static MyNews MyNewsSMore=new MyNews();//更多新闻列表

    /**
     * 新闻测试实体类
     */
    public static class MyNews {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            public PagingBean Paging;
            /**
             * AUTHOR :
             * MODEL_ID : 1
             * RELEASE_DATE : 2016-7-21 16:20
             * DESCRIPTION : “最开始知道有这么个‘一张网’的时候是在去年5月，当时真是有种又爱又恨的感觉。用上了以后，现在感觉离不开它了。”江西智容科技总经理李志，一个现在拥有近百名员工的80后老板，激动的对人民财经讲述他第一时间触“一张网”时候的经历。
             * ID : 4
             * TXT : <p style="margin: 15px 0px; padding: 0px; font-size: 16px; line-height: 2em; color: rgb(0, 0, 0); font-family: 'Microsoft YaHei', u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53;">&ldquo;我为什么恨它？自己公司的基本信息在江西企业信用监管警示系统上全部对外公开，甚至很多隐私信息，比如违法次数、违法记录等等，这些我们不愿意让别人知道的东西，不管是我的竞争对手，还是除了工商以外的其他政府部门，都能轻松查到。我有一种透明感，有一种担心和害怕。&rdquo;李志说道，&ldquo;不过，这张网对于每家企业都是公平的，我对它的爱也来源于此。因为业务需要，过去经常出现在合作里提前垫资的情况，如果对方没有经济能力继续下去，往往垫资就全部打了水漂。现在我会通过系统查询提前了解到一些，比如对方是否有欠贷的历史记录等负面信息，之后再决定是否要合作，我的心里踏实了许多。&rdquo;</p>
             * <p style="margin: 15px 0px; padding: 0px; font-size: 16px; line-height: 2em; color: rgb(0, 0, 0); font-family: 'Microsoft YaHei', u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53;">　　那么，李志口中的这张&ldquo;网&rdquo;究竟是什么？</p>
             * <p style="margin: 15px 0px; padding: 0px; font-size: 16px; line-height: 2em; color: rgb(0, 0, 0); font-family: 'Microsoft YaHei', u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53;">　　2015年3月20日，李克强总理视察工商总局时强调，要组织形成全国统一的企业信息公示大数据平台，完善企业信用信息共享机制，探索新的监管模式，把市场监管体系这张&ldquo;网&rdquo;织好织密。</p>
             * <p style="margin: 15px 0px; padding: 0px; font-size: 16px; line-height: 2em; color: rgb(0, 0, 0); font-family: 'Microsoft YaHei', u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53;">　　紧接着，去年底国务院印发的《关于&ldquo;先照后证&rdquo;改革后加强事中事后监管的意见》明确提出，各地区各部门要完善协同监管机制，做好信息公示工作，大力建设企业信用信息公示&ldquo;全国一张网&rdquo;。工商总局局长张茅不久前也表示，要在2016年底前，国家企业信用信息公示系统（即&ldquo;一张网&rdquo;）基本建成使用。</p>
             * <p style="margin: 15px 0px; padding: 0px; font-size: 16px; line-height: 2em; color: rgb(0, 0, 0); font-family: 'Microsoft YaHei', u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53;">　　为了加快推进&ldquo;一张网&rdquo;工程的建设，工商总局选择了北京、上海、天津、江西、福建、重庆、贵州等七个信息化建设基础较好的省市工商、市场监管部门作为先行建设单位，江西就于去年率先建设了企业信用监管警示系统。人民财经了解到，该系统会根据企业有无违法记录、违法次数的多少等，分成四种监管类别，正常企业标记为绿色，警示企业视违法失信程度由轻到重，分别标记为黄色、橙色和红色。企业信用监管警示系统相当于为每个企业建立一个实时动态&ldquo;经济户口&rdquo;，与企业相关的信息全部向社会公示，接受社会监督，使企业违法行为无所遁形。</p>
             * <p style="margin: 15px 0px; padding: 0px; font-size: 16px; line-height: 2em; color: rgb(0, 0, 0); font-family: 'Microsoft YaHei', u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53;">　　系统究竟好不好用？江西银行业务经理肖列深有感触地告诉人民财经，&ldquo;以前没有可靠的企业信息来源，贷款授信审批只能到各个监管部门去逐个核实，甚至出现过1个企业客户需要1周的时间去调查；现在只需打开这个网站，企业信息就一目了然，审核效率大大提高。客户开心，我们也更省时省力。&rdquo;</p>
             * <p style="margin: 15px 0px; padding: 0px; font-size: 16px; line-height: 2em; color: rgb(0, 0, 0); font-family: 'Microsoft YaHei', u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53;">　　据统计，江西省南昌市已有20余家企业因违法失信被取消投标资格；去年市里某园区两家企业因在企业信用监管警示系统中有违法记录被取消新三板上市资格；在南昌市政府对相关企业予以奖励时，6家企业又因有违法记录被取消奖励。南昌市市场和质量监督管理局局长徐云辉告诉人民财经，&ldquo;很多受到处罚的企业表示，希望不要公示违法经营处罚和监管等级等信息，担心经营受影响，宁愿接受比以前更重的经济处罚。&rdquo;</p>
             * <p/>
             * CONTENT_IMG : /zhirong.creditqrcode/u/cms/www/201607/21180540mw1b.jpg
             * TITLE_IMG : /zhirong.creditqrcode/u/cms/www/201607/21180534v7us.jpg
             * ORIGIN : 中国政府网
             * TITLE : “全国一张网”是紧箍咒？企业主：有助于遏制违法失信
             */
            public  List<NewslistBean> Newslist;

            public static class PagingBean {
                public int TotalPage;
                public int ShowCount;
                public int TotalResult;
                public int CurrentResult;
                public int CurrentPage;

            }

            public static class NewslistBean {
                public String AUTHOR;
                public String MODEL_ID;
                public String RELEASE_DATE;
                public String DESCRIPTION;
                public String ID;
                public String TXT;
                public String CONTENT_IMG;
                public String TITLE_IMG;//
                public String ORIGIN;//
                public String TITLE;//标题 * TITLE : “全国一张网”是紧箍咒？企业主：有助于遏制违法失信

            }
        }
    }
    /**
     * 搜索接口返回参数实体类
     */
    public static class baging {
        public int TotalPage;
        public int TotalResult;
        public int ShowCount;
        public int CurrentPage;
    }

    /**
     * 参数数据
     */
    public static class Baseinfo {
        public String REGSTATE;//登记状态
        public String REGNO;//企业注册号
        public String NAME;//法人
        public String REGCAP;//注册资金
        public String ESTDATE;//成立日期
        public String ENTTYPE_CN;//公司类型（市场主体类型中文）
        public String ENTNAME;//公司名字
        public String REGSTATE_CN;//经营状态（中文）
        public String UNISCID;//统一社会信用代码
        public String PRIPID;//主体身份代码
        public String ENTTYPE;//市场主体类型
    }

    public static List<Baseinfo> BaseinfoList = new ArrayList<>();

    /**
     * 16个item条数实体类
     */
    public static class allcount {
        public String EnterAddtionID;//企业附加表ID
        public String IsFavorite;//关注信息

        public String BiddingCount;//招标总数
        public String JobCount;//企业招聘总数
        public String EntNewCount;//企业新闻总数

        public String HonorCount;//荣誉信息
        public String JudiciaryCount;//司法信息
        public String PledgeCount;//出质
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
        public String MortgagorCount;//抵押
        public String PatentCount;//专利
        public String EntShowCount;//企业展示
        public String PageView;//浏览量    +++
        public String IsClaim;//是否认领   +++

    }

    public static List<allcount> allcountsList = new ArrayList<>();

    public static List<Userreview> UserreviewList = new ArrayList<>();

    /**
     * 我的点评列表
     */
    public static MyCommentlistr MyCommentlistrS=new MyCommentlistr();
    public static class MyCommentlistr {
        public String message;
        public String status;
        public DataBean data;
        public String version;
        public static class DataBean {
            public PagingBean Paging;
            public List<UserreviewBean> userreview;
            public static class PagingBean {
                public int TotalPage;
                public int ShowCount;
                public int TotalResult;
                public int CurrentResult;
                public int CurrentPage;
            }
            public static class UserreviewBean {
                public String ENTERID;//附加表的企业ID
                public String ICONPATH;//头像
                public String FAILEDQTY;//吐槽数量
                public String replay2reviewCount;
                public String MEMBERID;//本评论用户ID
                public String CREATETIME;//评论时间
                public String COMMENTID;//评论ID
                public String CONTENT;//点评内容
                public String SUCCESSQTY;//点赞数量123123.
                public String ISSUCCESS;//是否点赞 0为否，1为是
                public List<Replay2review> replay2review;
                public String MEMBERNAME;//本评论用户名称
                public String ISFAILED;//是否吐槽 0为否，1为是
                /**
                 * 回复评论（楼中楼）
                 */
                public static class Replay2review {
                    public String CHILDMEMBERID;//用户ID
                    public String REPLAYCOMMENT;//回复评论内容
                    public String CHILDMEMBERNAME;//用户名称
                    public String REPLAYTIME;//回复评论时间
                }
            }
        }
    }
    public static class Userreview {
        public String ENTERID;//附加表的企业ID
        public String ICONPATH;//头像
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
    public static class Replay2review {
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
     * 点赞 and 差评  and 发表 and 回复
     */
    public class Root202 {
        public String message;
        public int status;
        public Data202 data;
        public String version;
    }

    public class Data202 {
        public String result;
    }

    public static String Result = "";


    public static User user = new User();

    /**
     * 用户信息 + 修改个人资料  实体类
     */
    public static class User {

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
                public String USERTYPEID;//用户类型ID86D9D7F53FCA45DD93E2D83DFCA0CB43
            }
        }
    }

    public static MyComplaint myComplaint = new MyComplaint();

    /**
     * 我的投诉列表实体类
     */
    public static class MyComplaint {


        /**
         * message : true
         * status : 1
         * data : 数据源
         * version : v1.0
         */

        public String message;
        public String status;
        /**
         * Paging : 页信息
         * commentList :投诉列表
         */
        public DataBean data;
        public String version;

        public static class DataBean {
            /**
             * TotalPage : 总页数
             * ShowCount : 每页显示总页数
             * TotalResult : 总记录数
             * CurrentResult : 当前记录启示索引
             * CurrentPage : 当前页
             */

            public PagingBean Paging;
            /**
             * COMPLAINTIME : 2016-06-28 15:58:47  *投诉时间
             * COMPLAINTID : 7a710d65c0c54571bb3903d4a9227b4c  *投诉ID
             * ENTERNAME : 南昌市青山湖区小美小百货店    *投诉企业
             * AttachmentList : []   附件集合
             * COMPLAINTITLE : 哀伤的风格 *投诉标题
             * COMPLAINSTATUS : 0 投诉状态
             */

            public List<CommentListBean> commentList;

            public static class PagingBean {
                public int TotalPage;
                public int ShowCount;
                public int TotalResult;
                public int CurrentResult;
                public int CurrentPage;
            }

            public static class CommentListBean {
                public String COMPLAINTIME;
                public String COMPLAINTID;
                public String ENTERNAME;
                public String COMPLAINTITLE;
                public String COMPLAINSTATUS;
                public List<?> AttachmentList;
            }
        }
    }

    /**
     * 关注 and 取消关注
     */
    public static Favotite FavotiteS=new Favotite();
    public static class Favotite {
        public String message;
        public String status;
        /**
         * result : success
         */
        public DataBean data;
        public static class DataBean {
            public String result;//结果
        }
    }
    /**
     * 我的关注列表
     */
    public static FavotiteList FavotiteListS=new FavotiteList();
    public static class FavotiteList {
        public String message;
        public String status;
        public DataBean data;

        public static class DataBean {
            /**
             * ENTERID : 29cf69863e6a11e6b90f00163e160363
             * REGNORE : 360103210025958
             * ATTENTIONTIME : 2016-07-01 19:41:26
             * NAME : 万杏娥
             * ENTTYPE_CN : 有限责任公司(自然人投资或控股)
             * ENTERNAME : 江西智容科技有限公司
             * ATTENTIONID : 63ddaceb1f7d439eb10b6a9002f327f7
             * ATTENTIONTYPE : 南昌
             * PRIPID : 3601032011041300098564
             * ENTTYPE : 1130
             */
            public List<AttentionListBean> AttentionList=new ArrayList<>();
            public static class AttentionListBean {
                public String ENTERID;//附加表的企业ID
                public String REGNORE;//注册号
                public String ATTENTIONTIME;//关注时间
                public String NAME;//法人名字
                public String ENTTYPE_CN;//公司类型
                public String ENTERNAME;//企业名称
                public String ATTENTIONID;//关注ID
                public String ATTENTIONTYPE;//关注类型
                public String PRIPID;//企业主体ID
                public String ENTTYPE;//市场主体类型代码

            }
        }
    }


    public static ComplaintDetail complaintDetail = new ComplaintDetail();

    /**
     * 投诉详情实体类
     */
    public static class ComplaintDetail {

        /**
         * message : true
         * status : 1
         * data : {"AttachmentCount":"1","AttachmentList":[{"ATTACHMENTDESC":"my_icon","ATTACHMENTNAME":"73287324ae494a5799e842e8c5f6df901467366492379.jpg","ATTACHMENTID":"15126d98fb9a450fb0e9c149ee4b3e62","ATTACHMENTPATH":"/9j/4AAQSkZJRgABAQEBLAEsAAD/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcU\r\nFhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/2wBDAQcHBwoIChMKChMoGhYaKCgoKCgo\r\nKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCj/wAARCAC0ALQDASIA\r\nAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA\r\nAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3\r\nODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm\r\np6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA\r\nAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx\r\nBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK\r\nU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3\r\nuLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwBrM25v\r\nmP50m5vU/nQ/3m+tJX6KfjVxdzep/Ojc3qfzpKKYXF3N6n86Nzep/OkooC4u5vU/nRub1P50lFAX\r\nF3N6n86Nzep/OkooC4u5vU/nRub1P50lFAXF3N6n86Nzep/OkooC4u5vU/nRub1P50lFAXF3N6n8\r\n6Nzep/OkooC4u5vU/nRub1P50lFAXF3N6n86Nzep/OkooC5NESV+8fzop1v9w/WikMgf7zfWkpX+\r\n831pKYjR0PRr7XLt7XTY1kmRN5DMF4yB3+tb3/CufEv/AD5Rf9/1q/8ABX/karn/AK9W/wDQ1r22\r\nvn8wzOthqzpwSt/XmfW5RkmHxuGVWo3e72/4Y8D/AOFc+Jf+fKL/AL/rWPr/AId1LQPIGqwrEZs7\r\n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","ATTACHMENTTYPE":"投诉类型","ATTACHMENTTIME":"2016-07-01 17:48:12"}],"UserComplain":{"COMPLAINTYPE":"","COMPLAINTIME":"2016-07-01 17:48:11","COMPLAINCOMMENT":"是的风格和健康巨化股份但是法国红酒看11111111111111111","ENTERCOMMENT":"","REFUSEREASON":"","GOVCOMMENT":"","COMPLAINTTITLE":"啊当事人同意就很快乐","MEMBERID":"b2d794b453664657af61b373c1d00e7c","ENTERNAME":"江西智容科技有限公司","COMPLAINSTATUS":"0","MEMBERNAME":"林恒56"}}
         * version : v1.0
         */

        public String message;
        public String status;
        /**
         * AttachmentCount : 1 附件条数
         * AttachmentList :用户投诉时附件
         * UserComplain : 用户对企业的投诉列表
         */

        public DataBean data;
        public String version;

        public static class DataBean {
            public String AttachmentCount;
            /**
             * COMPLAINTYPE :投诉类型
             * COMPLAINTIME : 2016-07-01 17:48:11：投诉时间
             * COMPLAINCOMMENT : 是的风格和健康巨化股份：投诉内容
             * ENTERCOMMENT :  企业处理回复
             * REFUSEREASON :拒绝原因
             * GOVCOMMENT : 政府回复
             * COMPLAINTTITLE : 啊当事人同意就很快乐：投诉标题
             * MEMBERID : b2d794b453664657af61b373c1d00e7c ：用户ID
             * ENTERNAME : 江西智容科技有限公司：企业名称
             * COMPLAINSTATUS : 0 ：投诉处理状态 0未处理 1已处理 2已拒绝
             * MEMBERNAME : 林恒56：用户名称
             */

            public UserComplainBean UserComplain;
            /**
             * ATTACHMENTDESC : my_icon：附件描述
             * ATTACHMENTNAME : 73287324ae494a5799e842e8c5f6df901467366492379.jpg：文件名？
             * ATTACHMENTID : 15126d98fb9a450fb0e9c149ee4b3e62 ：附件ID
             * ATTACHMENTPATH : Base64图片
             * ATTACHMENTTYPE : 附件类型，如身份证等
             * ATTACHMENTTIME : 2016-07-01 17:48:12
             */

            public List<AttachmentListBean> AttachmentList;

            public static class UserComplainBean {
                public String COMPLAINTYPE;
                public String COMPLAINTIME;
                public String COMPLAINCOMMENT;
                public String ENTERCOMMENT;
                public String REFUSEREASON;
                public String GOVCOMMENT;
                public String COMPLAINTTITLE;
                public String MEMBERID;
                public String ENTERNAME;
                public String COMPLAINSTATUS;
                public String MEMBERNAME;
            }

            public static class AttachmentListBean {
                public String ATTACHMENTDESC;
                public String ATTACHMENTNAME;
                public String ATTACHMENTID;
                public String ATTACHMENTPATH;
                public String ATTACHMENTTYPE;
                public String ATTACHMENTTIME;
            }
        }
    }
    public static ClaimUtils ClaimUtilsModel = new ClaimUtils();
    /**
     * 提交认领实体类 + 提交认领附件实体类
     */
    public static class ClaimUtils {
        public String message;
        public String status;
        /**
         * result : success
         * CLAIMID : 0ba157faf37d47a1adba808b95e00ab0
         */
        public DataBean data;
        public static class DataBean {
            public String result;
            public String CLAIMID;
        }
    }

    public static MyClaimUtils MyClaimUtilsModel = new MyClaimUtils();
    /**
     * 我的认领列表实体类
     */
    public static class MyClaimUtils {
        public String message;
        public String status;
        public DataBean data;
        public String version;//版本号

        public static class DataBean {

            public PagingBean Paging;
            public List<ClaimlistBean> Claimlist=new ArrayList<>();

            public static class PagingBean {
                public int TotalPage;
                public int ShowCount;
                public int TotalResult;
                public int CurrentResult;
                public int CurrentPage;

            }

            public static class ClaimlistBean {
                public String MEMBERID;//"MEMBERID":"f9aca831e7444509b4b3dcbc6a0a9318"
                public String ENTTYPE;//"ENTTYPE":"1130"
                public String USERNAME;//登录名
                private String ALIASNAME;//认领人名字

                public String ENTERID;//附加表的企业ID
                public String CLAIMID;//认领ID
                public String DESCRIPTION;//认领申明
                public String CLAIMTIME;//认领时间
                public String STATUS;//认领状态
                public String TELPHONE;//电话号码
                public String STATUSNAME;//认领状态中文
                public String REGNORE;//注册号
                public String REFUSEREASON;//拒审原因
                public String ENTTYPE_CN;//市场主体类型代码
                public String ENTERNAME;//企业名称
                public String EMAIL;//邮件地址
                public String PRIPID;//企业主体ID

                public List<AttachmentListBean> AttachmentList;

                public static class AttachmentListBean {
                    public String ATTACHMENTDESC;//图片备注内容
                    public String ATTACHMENTNAME;//图片名字
                    public String ATTACHMENTID;//图片ID
                    public String ATTACHMENTPATH;//图片base64位码
                    public String ATTACHMENTTYPE;//图片类型
                    public String ATTACHMENTTIME;//图片时间
                }
            }
        }
    }


    public  static ToComplain toComplain=new ToComplain();
    /**
     * 提交投诉成功后返回数据实体类
     */
    public static class ToComplain{
        /**
         * message : true
         * status : 1
         * data : {"result":"success","COMPLAINID":"0fb01a8e74fc4338aea42197dcb8fd5f"}
         * version : v1.0
         */
        public String message;
        public String status;
        /**
         * result : success
         * COMPLAINID : 0fb01a8e74fc4338aea42197dcb8fd5f： 事件ID
         */
        public DataBean data;
        public String version;

        public static class DataBean {
            public String result;
            public String COMPLAINID;
        }
    }

    public  static TwoDim TwoDimSli=new TwoDim();

    public static class TwoDim {
        public String message;
        public String status;
        public DataBean data;
        public String version;
        public static class DataBean {
            public String result;
        }
    }

    public  static Zdian ZdianS=new Zdian();
    /**
     * d字典表
     */
    public static class Zdian {
        public String message;
        public String status;
        public DataBean data;
        public String version;

        public static class DataBean {
            /**
             * NAME : 金融
             * ORDY_BY : 1
             * PNAME : 行业
             * P_BM : HY_JR
             * JB : 2
             * ZD_ID : a5819ad67e1e4cb1a863188602bbb861
             * BIANMA : JR
             * PARENT_ID : 43271e594e824d0f874a422a4b9933fa
             */
            public List<DictionarieBean> dictionarie;
            public static class DictionarieBean {
                public String NAME;
                public String ORDY_BY;
                public String PNAME;
                public String P_BM;
                public String JB;
                public String ZD_ID;
                public String BIANMA;
                public String PARENT_ID;
            }
        }
    }
    /**
     * 首页商标查询实体类
     */
    public static sb_search sb_searchS=new sb_search();
    public static class sb_search{
        public String message;
        public String status;
        public DataBean data;
        public String version;
        public static class DataBean {
            public PagingBean Paging;
            /**
             * APPLICANT : 江西智容科技有限公司
             * UNISCID :
             * CLASSIFYID : 14
             * BRANDNAME : 玉嫦娥
             * REGNO : 360103210025958
             * BRANDIMG : http://tm-image.qichacha.com/cbee38bf6a7fef77fcf80a1b06664ebb.jpg@100h_160w_1l_50q
             * REGCORE : 18555429
             * LIFESPAN : -
             * ENTNAME : 江西智容科技有限公司
             * BRANDSTAUTS : 商标注册申请等待受理中
             * ID : 44E748BD2E584011B55904E79D53D6CC
             * APPLICATIONDATE : 2015/12/9
             * PRIPID : 3601032011041300098564
             * AGENCY : 江西帝一国际知识产权代理有限公司
             */
            public List<TrademarkBean> trademark;
            public static class PagingBean {
                public int TotalPage;
                public int ShowCount;
                public int TotalResult;
                public int CurrentResult;
                public int CurrentPage;
            }
            public static class TrademarkBean {
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
        }
    }

    /**
     * 首页专利查询实体类
     */
    public static zl_search zl_searchS=new zl_search();
    public static class zl_search{
        public String message;
        public String status;
        public DataBean data;
        public String version;
        public static class DataBean {
            public PagingBean Paging;
            /**
             * RDATE : 1991-10-25
             * INVENTOR : 熊小康
             * ADATE : 1992-04-08
             * DETAILINFO : 简要说明： 1.省略其它视图；
             * PATENTTYPE : 外观设计
             * LEGALSTATUS :
             * ACODE : CN3013130
             * PATENTNAME : 药物包装盒片材
             * PRIPID : 3600002000000057
             * AGENCY : 江西省专利事务所
             * RCODE : 91302869X
             */
            public List<PatentInfoBean> patentInfo;
            public static class PagingBean {
                public int TotalPage;
                public int ShowCount;
                public int TotalResult;
                public int CurrentResult;
                public int CurrentPage;

            }
            public static class PatentInfoBean {
                public String ID;//后加的专利IDpatentId
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
                public String ABSTRACTGRAPH;//后加图片路径
                public String ENTNAME;//公司名称 后加
            }
        }
    }

    public static MyHot MyHotS=new MyHot();

    /**
     * 热点
     */
    public static class MyHot{
        public String message;
        public String status;
        public DataBean data;
        public String version;
        public static class DataBean {
            /**
             * KEYWORDS : 智容
             * COUNT : 1397
             * LOGID : 0019de20d4354cf1bd5c3c5666ed8ccd
             * LOGTIME : 2016-07-22 08:44:10
             * REMARK :
             */
            public List<HotspotAnalysisBean> HotspotAnalysis;
            public static class HotspotAnalysisBean {
                public String KEYWORDS;
                public String COUNT;
                public String LOGID;
                public String LOGTIME;
                public String REMARK;
            }
        }
    }
    /**
     * 失信
     */
    public static MyDishonesty MyDishonestyS=new MyDishonesty();
    public static class MyDishonesty{
        public String message;
        public String status;
        public DataBean data;
        public String version;
        public static class DataBean {
            public PagingBean Paging;
            /**
             * CARDNUM : 693725177
             * COURT_NAME : 江西省吉安市中级人民法院
             * CASE_CODE : (2015)吉中执字第00046号
             * INAME : 吉安市青年信息咨询有限公司
             * COURTCASEID : 100911345
             * PRIPID :
             * CARDTYPE : 1
             * PUBLISH_DATE : 2016-03-10 00:00:00
             */
            public List<CourtcaseinfoBean> Courtcaseinfo;
            public static class PagingBean {
                public int TotalPage;
                public int ShowCount;
                public int TotalResult;
                public int CurrentResult;
                public int CurrentPage;
            }

            public static class CourtcaseinfoBean {
                public String CARDNUM;
                public String COURT_NAME;
                public String CASE_CODE;
                public String INAME;
                public String COURTCASEID;
                public String PRIPID;
                public String CARDTYPE;
                public String PUBLISH_DATE;
            }
        }
    }
    public static String ReportText="";


    public static MyNewApp MyNewAppS=new MyNewApp();
    /**
     * 最新APP版本
     */
    public static class MyNewApp {
        public String message;
        public String status;
        public List<DataBean> data;
        public static class DataBean {
            public String TIME;
            public String NAME;
            public String VERSION;
            public String PATH;
            public String DESCS;
        }
    }
}

