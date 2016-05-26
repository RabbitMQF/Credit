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

    public static List<search> searchList;//搜索列表集合

    public class search {//搜索列表信息 Result
        public String KeyNo;
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
        public String EnglishName;
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


    public static List<citys> citysList = new ArrayList<citys>();//城市集合

    /**
     * 城市实体类
     */
    public class citys {
        public String Province;
        public String City;
        public String ProvinceCode;
        public String CityCode;
    }

    /**
     * 行业实体类
     */
    public static List<industryData> dataList;

    public class industryData {
        public String Code;
        public String Name;
        public String Desc;
        public List<SubIndustryList> SubIndustryList;
    }

    /**
     * 行业子类
     */
    public static List<SubIndustryList> SubyList;

    public class SubIndustryList {
        public String Code;
        public String Name;
        public String Desc;
    }

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

    /**
     * 工商变更类
     */
   /* public class changeContent {
        public String ChangeDate;//变更时间
        public String ProjectName;//变更标题
        public String AfterContent;//变更后
        public String BeforeContent;//变更前

        public changeContent(String ChangeDate, String ProjectName, String AfterContent, String BeforeContent) {
            this.ChangeDate = ChangeDate;
            this.ProjectName = ProjectName;
            this.AfterContent = AfterContent;
            this.BeforeContent = BeforeContent;
        }
    }*/


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
     * 专利信息实体类
     */
    public static List<patentInfo> patentInfoList;

    public static class patentInfo {
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
     * 广告资质实体类
     */
    public static List<advertisement> advertisementList=new ArrayList<>();
    public static class advertisement{
        public String level;//许可文件名称
        public String type;//许可证号
        public String dates;//有效期
        public String times;//认定日期
        public String office;//认定机关
    }



}
