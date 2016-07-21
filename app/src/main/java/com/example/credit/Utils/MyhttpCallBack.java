package com.example.credit.Utils;

import com.example.credit.Activitys.CommentListActivity;
import com.example.credit.Activitys.CommentListDetailsActivity;
import com.example.credit.Activitys.CompanyDetailsActivity;
import com.example.credit.Activitys.LoginActivity;
import com.example.credit.Activitys.MainActivity;
import com.example.credit.Activitys.MyClaimActivity;
import com.example.credit.Activitys.MycomplaintsListActivity;
import com.example.credit.Activitys.MyconcernActivity;
import com.example.credit.Activitys.PassWordActivity;
import com.example.credit.Activitys.RegisterActivity;
import com.example.credit.Activitys.SearchFirmActivty;
import com.example.credit.Activitys.ToClaimActivity;
import com.example.credit.Activitys.ToCommentActivity;
import com.example.credit.Activitys.ToComplaintActivity;
import com.example.credit.Activitys.UserSetActivity;
import com.example.credit.Entitys.DataManager;
import com.example.credit.Entitys.DataManager.Replay2review;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by alucard on 2016-05-18.
 */
public class MyhttpCallBack implements HttpCallBack {
    Gson gson;
    static Map<String, Object> map;
    String jsonString;
    public static DataManager.baging baging = new DataManager.baging();

    private static MyhttpCallBack instance;
    CreditSharePreferences csp = CreditSharePreferences.getLifeSharedPreferences();

    public static MyhttpCallBack getInstance() {
        if (instance == null) {
            instance = new MyhttpCallBack();
        }
        return instance;
    }


    @Override
    public void onSucceed(int what, Response response) {
        //gson = new Gson();
        gson = new Gson();
        switch (what) {
            case 0x021://获取城市

                //企查查城市数据解法
//                String jstring = (String) response.get();
//                map = gson.fromJson(jstring, new TypeToken<Map<String, Object>>() {
//                }.getType());
//                List<DataManager.citys> list = gson.fromJson(((Map<String, Object>) map.get("data")).get("Result").toString(), new TypeToken<List<DataManager.citys>>() {
//                }.getType());
//                DataManager.citysList = list;

                //真实城市数据解法————zlh手解json
                String jstring = (String) response.get();
                map = gson.fromJson(jstring, new TypeToken<Map<String, Object>>() {
                }.getType());
                if (((LinkedTreeMap) map.get("data")).size() != 0) {
                    List<LinkedTreeMap> list = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("city");
                    for (int i = 0; i < list.size(); i++) {
                        DataManager.citys temp1 = new DataManager.citys();
                        temp1.c_name = (String) list.get(i).get("c_name");
                        temp1.c_code = (String) list.get(i).get("c_code");
                        temp1.citycode = new ArrayList<>();
                        List<LinkedTreeMap> templist = (List<LinkedTreeMap>) list.get(i).get("citycode");
                        for (int j = 0; j < templist.size(); j++) {
                            DataManager.citycode temp2 = new DataManager.citycode();
                            temp2.c_code = (String) templist.get(j).get("c_code");
                            temp2.c_name = (String) templist.get(j).get("c_name");
                            temp1.citycode.add(temp2);
                        }
                        DataManager.citysList.add(temp1);
                    }
                }
                break;
            case 0x111://获取新闻
                String jstring111 = (String) response.get();
                DataManager.Root11 jsonRoot11 = gson.fromJson(jstring111, new TypeToken<DataManager.Root11>() {
                }.getType());
                DataManager.NewssList = jsonRoot11.result;
                break;
            case 0x112://获取新闻内容
                String jstring112 = (String) response.get();
                DataManager.Root11 jsonRoot12 = gson.fromJson(jstring112, new TypeToken<DataManager.Root11>() {
                }.getType());
                DataManager.NewssList = jsonRoot12.result;
                if (DataManager.NewssList != null && DataManager.NewssList.size() > 0) {
                    MainActivity.handler.sendEmptyMessage(0);
                }
                break;
            case 0x113://获取最新认领
                jsonString = (String) response.get();
                DataManager.MyClaimUtilsModel = gson.fromJson(jsonString, DataManager.MyClaimUtils.class);
                break;

            case 0x022://搜索结果
                String searchstr = (String) response.get();
                //test
                //searchstr="{\"message\":\"true\",\"status\":1,\"data\":{\"Result\":[{\"PRIPID\":\"3601032011041300098564\",\"entname\":\"江西智容科技有限公司\",\"REGNO\":\"360103210025958\",\"REGORG_CN\":\"南昌高新技术产业开发区\",\"NAME\":\"万杏娥\",\"OPFROM\":\"2011-04-28\",\"OPTO\":\"2031-04-27\",\"REGSTATE_CN\":\"存续（在营、开业、在册）\",\"C_PROVINCE\":\"36\",\"D_ADDTIME\":\"2016-03-17\",\"C_STATE\":\"1\",\"UNISCID\":\"null\",\"REGCAP\":\"5000.0\",\"ENTTYPE_CN\":\"有限责任公司(自然人投资或控股)\",\"DOM\":\"江西省南昌市高新技术产业开发区高新区高新二路建昌工业园金庐软件园海外大厦北楼306室\",\"INDUSTRYPHY\":\"I\",\"INDUSTRYPHY_NAME\":\"信息传输、软件和信息技术服务业\",\"OPSCOPE\":\"计算机软件系统开发；办公自动化设备销售；计算机系统集成；国内广告的设计、制作、发布、代理；会展服务（以上项目国家有专项规定的除外）\"},{\"PRIPID\":\"20160127091814206993\",\"entname\":\"江西智容科技有限公司南昌分公司\",\"REGNO\":\"360105220000025\",\"REGORG_CN\":\"null\",\"NAME\":\"罗川\",\"OPFROM\":\"2016-02-04\",\"OPTO\":\"null\",\"REGSTATE_CN\":\"存续（在营、开业、在册）\",\"C_PROVINCE\":\"36\",\"D_ADDTIME\":\"2016-03-17\",\"C_STATE\":\"null\",\"UNISCID\":\"91360105MA35GGBY60\",\"REGCAP\":\"null\",\"ENTTYPE_CN\":\"有限责任公司分公司(自然人投资或控股)\",\"DOM\":\"红星村原红星乡财政所\",\"INDUSTRYPHY\":\"L\",\"INDUSTRYPHY_NAME\":\"租赁和商业服务业\",\"OPSCOPE\":\"餐饮企业管理服务；食堂（主食、热菜、早点）（卫生许可证有效期至2011年5月13日止）（以上项目国家有专项规定的除外）\"}],\"Paging\":{\"PageSize\":40,\"PageIndex\":0,\"TotalRecords\":2}}}";
                map = gson.fromJson(searchstr, new TypeToken<Map<String, Object>>() {
                }.getType());
//                List<DataManager.search> searchstrlist2 = gson.fromJson(((Map<String, Object>) map.get("data")).get("Result").toString(), new TypeToken<List<DataManager.search>>() {
//                }.getType());
//                DataManager.searchList = searchstrlist2;
                baging = gson.fromJson(((Map<String, Object>) map.get("data")).get("Pageing").toString(), DataManager.baging.class);
                List<LinkedTreeMap> searchstrlist2 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("Result");
                if (DataManager.searchList.size() != 0) {
                    DataManager.searchList.clear();
                }
                for (LinkedTreeMap temp : searchstrlist2) {
                    DataManager.search serchtemp = new DataManager.search();

                    serchtemp.ENTTYPE = (String) temp.get("ENTTYPE");

                    serchtemp.PRIPID = (String) temp.get("PRIPID");
                    serchtemp.ENTNAME = (String) temp.get("ENTNAME");
                    serchtemp.REGNO = (String) temp.get("REGNO");
                    serchtemp.REGORG_CN = (String) temp.get("REGORG_CN");
                    serchtemp.NAME = (String) temp.get("NAME");
                    serchtemp.OPFROM = (String) temp.get("OPFROM");
                    serchtemp.OPTO = (String) temp.get("OPTO");
                    serchtemp.REGSTATE_CN = (String) temp.get("REGSTATE_CN");
                    serchtemp.C_PROVINCE = (String) temp.get("C_PROVINCE");
                    serchtemp.D_ADDTIME = (String) temp.get("D_ADDTIME");
                    serchtemp.C_STATE = (String) temp.get("C_STATE");
                    serchtemp.REGCAP = temp.get("REGCAP").toString();
                    serchtemp.ENTTYPE_CN = (String) temp.get("ENTTYPE_CN");
                    serchtemp.DOM = (String) temp.get("DOM");
                    serchtemp.INDUSTRYPHY = (String) temp.get("INDUSTRYPHY");
                    serchtemp.INDUSTRYPHY_NAME = (String) temp.get("INDUSTRYPHY_NAME");
                    serchtemp.OPSCOPE = (String) temp.get("OPSCOPE");
                    DataManager.searchList.add(serchtemp);
                }
                if (DataManager.searchList != null && DataManager.searchList.size() > 0) {
                    SearchFirmActivty.handler.sendEmptyMessage(0);
                } else {
                    SearchFirmActivty.handler.sendEmptyMessage(500);
                }
                break;
            case 0x023://预留获取行业
//                gson = new Gson();
//                String str3 = (String) response.get();
//                map = gson.fromJson(str3, new TypeToken<Map<String, Object>>() {
//                }.getType());
//                List<LinkedTreeMap> listtemp = (List<LinkedTreeMap>) map.get("data");
//                String jsonstradd = gson.toJson(listtemp);
//                List<DataManager.industryData> listtemps = gson.fromJson(jsonstradd, new TypeToken<List<DataManager.Data>>() {
//                }.getType());
//                DataManager.dataList = listtemps;
//                for (DataManager.industryData dt : DataManager.dataList) {
//                    DataManager.SubyList = dt.SubIndustryList;
//                }
                /*DataManager.industry industry1 = null;
                String industry = (String) response.get();*/

                /*     try {
                    JSONObject a=new JSONObject(industry);
                    JSONArray array=a.getJSONArray("data");
                    for(int i=0;i<array.length();i++){
                        JSONObject b=array.getJSONObject(i);
                        DataManager.industry.Name= (String) b.get("Name");
                        DataManager.industry.Code=(String) b.get("Code");
                        DataManager.industry.Desc=(String) b.get("Desc");
                        JSONArray array1=b.getJSONArray("SubIndustryList");
                        for(int j=0;j<array1.length();j++){
                            JSONObject c =(JSONObject) array1.get(j);
                            DataManager.industry.SubIndustryList.add((String) c.get("Name"));
                        }
                        DataManager.industryList.add(industry1);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                String str3 = (String) response.get();
                map = gson.fromJson(str3, new TypeToken<Map<String, Object>>() {
                }.getType());
                if (((LinkedTreeMap) map.get("data")).size() != 0) {
                    List<LinkedTreeMap> list4 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("industry");
                    if (DataManager.industryDataList.size() != 0) {
                        DataManager.industryDataList.clear();
                    }
                    for (LinkedTreeMap tempTree : list4) {
                        DataManager.industryData industryData = new DataManager.industryData();
                        industryData.EC_VALUE = (String) tempTree.get("EC_VALUE");
                        industryData.EC_NAME = (String) tempTree.get("EC_NAME");
                        DataManager.industryDataList.add(industryData);
                    }
                }

                break;
            case 0x024://获取企业详情16宫格等
                jsonString = (String) response.get();
                try {
                    map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                    }.getType());
                    List<LinkedTreeMap> lists2 = null;
                    if (map.get("data")!=null&&!map.get("data").equals(null)&&((Map<String, Object>) map.get("data")).get("allcount") != null && !((Map<String, Object>) map.get("data")).get("allcount").equals(null)) {
                        lists2 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("allcount");
                    }
                    List<LinkedTreeMap> lists3 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("baseinfo");
                    if (DataManager.allcountsList != null) {
                        DataManager.allcountsList.clear();
                    }
                    if (DataManager.BaseinfoList != null) {
                        DataManager.BaseinfoList.clear();
                    }

                    if (lists2 != null && lists2.size() > 0) {
                        for (LinkedTreeMap temp : lists2) {
                            DataManager.allcount cfo = new DataManager.allcount();
                            cfo.IsFavorite = temp.get("IsFavorite").toString();
                            cfo.HonorCount = temp.get("HonorCount").toString();
                            cfo.JudiciaryCount = temp.get("JudiciaryCount").toString();
                            cfo.PledgeCount = temp.get("PledgeCount").toString();
                            cfo.CopyrightCount = temp.get("CopyrightCount").toString();
                            cfo.AnnualCount = temp.get("AnnualCount").toString();
                            cfo.AdvertisementCount = temp.get("AdvertisementCount").toString();
                            cfo.BaseInfoCount = temp.get("BaseInfoCount").toString();
                            cfo.ApprovalCount = temp.get("ApprovalCount").toString();
                            cfo.PunishCount = temp.get("PunishCount").toString();
                            cfo.WarningCount = temp.get("WarningCount").toString();
                            cfo.TrademarkCount = temp.get("TrademarkCount").toString();
                            cfo.AbnormityCount = temp.get("AbnormityCount").toString();
                            cfo.CreditCount = temp.get("CreditCount").toString();
                            cfo.SupportCount = temp.get("SupportCount").toString();
                            cfo.MortgagorCount = temp.get("MortgagorCount").toString();
                            cfo.PatentCount = temp.get("PatentCount").toString();
                            cfo.PageView=temp.get("PageView").toString();
                            cfo.IsClaim=temp.get("IsClaim").toString();
                            DataManager.allcountsList.add(cfo);
                        }
                    }
                    if (lists3 != null && lists3.size() > 0) {
                        for (LinkedTreeMap temp : lists3) {
                            DataManager.Baseinfo cfo = new DataManager.Baseinfo();
                            cfo.EnterAddtionID = temp.get("EnterAddtionID").toString();
                            cfo.REGSTATE = temp.get("REGSTATE").toString();
                            cfo.REGNO = temp.get("REGNO").toString();
                            cfo.IsFavorite = temp.get("IsFavorite").toString();
                            cfo.NAME = temp.get("NAME").toString();
                            cfo.REGCAP = temp.get("REGCAP").toString();
                            cfo.ESTDATE = temp.get("ESTDATE").toString();
                            cfo.ENTTYPE_CN = temp.get("ENTTYPE_CN").toString();
                            cfo.ENTNAME = temp.get("ENTNAME").toString();
                            cfo.REGSTATE_CN = temp.get("REGSTATE_CN").toString();
                            cfo.UNISCID = temp.get("UNISCID").toString();
                            cfo.PRIPID = temp.get("PRIPID").toString();
                            cfo.ENTTYPE = temp.get("ENTTYPE").toString();
                            DataManager.BaseinfoList.add(cfo);
                        }
                    }
                    if (DataManager.allcountsList != null && DataManager.allcountsList.size() > 0) {
                        SearchFirmActivty.handler.sendEmptyMessage(5);
                    } else {
                        SearchFirmActivty.handler.sendEmptyMessage(500);
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    Toast.show("该企业暂无数据");
                    SearchFirmActivty.pd.dismiss();
                }
                break;
            case 0x025://我的关注跳公司详情界面的请求
                jsonString = (String) response.get();
                map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<LinkedTreeMap> lists25 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("allcount");
                List<LinkedTreeMap> lists35 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("baseinfo");
                if (DataManager.allcountsList != null) {
                    DataManager.allcountsList.clear();
                }
                if (DataManager.BaseinfoList != null) {
                    DataManager.BaseinfoList.clear();
                }
                if (lists25 != null && lists25.size() > 0) {
                    for (LinkedTreeMap temp : lists25) {
                        DataManager.allcount cfo = new DataManager.allcount();
                        cfo.IsFavorite = temp.get("IsFavorite").toString();
                        cfo.HonorCount = temp.get("HonorCount").toString();
                        cfo.JudiciaryCount = temp.get("JudiciaryCount").toString();
                        cfo.PledgeCount = temp.get("PledgeCount").toString();
                        cfo.CopyrightCount = temp.get("CopyrightCount").toString();
                        cfo.AnnualCount = temp.get("AnnualCount").toString();
                        cfo.AdvertisementCount = temp.get("AdvertisementCount").toString();
                        cfo.BaseInfoCount = temp.get("BaseInfoCount").toString();
                        cfo.ApprovalCount = temp.get("ApprovalCount").toString();
                        cfo.PunishCount = temp.get("PunishCount").toString();
                        cfo.WarningCount = temp.get("WarningCount").toString();
                        cfo.TrademarkCount = temp.get("TrademarkCount").toString();
                        cfo.AbnormityCount = temp.get("AbnormityCount").toString();
                        cfo.CreditCount = temp.get("CreditCount").toString();
                        cfo.SupportCount = temp.get("SupportCount").toString();
                        cfo.MortgagorCount = temp.get("MortgagorCount").toString();
                        cfo.PatentCount = temp.get("PatentCount").toString();
                        cfo.PageView=temp.get("PageView").toString();
                        cfo.IsClaim=temp.get("IsClaim").toString();
                        DataManager.allcountsList.add(cfo);
                    }
                }
                if (lists35 != null && lists35.size() > 0) {
                    for (LinkedTreeMap temp : lists35) {
                        DataManager.Baseinfo cfo = new DataManager.Baseinfo();
                        cfo.EnterAddtionID = temp.get("EnterAddtionID").toString();
                        cfo.REGSTATE = temp.get("REGSTATE").toString();
                        cfo.REGNO = temp.get("REGNO").toString();
                        cfo.IsFavorite = temp.get("IsFavorite").toString();
                        cfo.NAME = temp.get("NAME").toString();
                        cfo.REGCAP = temp.get("REGCAP").toString();
                        cfo.ESTDATE = temp.get("ESTDATE").toString();
                        cfo.ENTTYPE_CN = temp.get("ENTTYPE_CN").toString();
                        cfo.ENTNAME = temp.get("ENTNAME").toString();
                        cfo.REGSTATE_CN = temp.get("REGSTATE_CN").toString();
                        cfo.UNISCID = temp.get("UNISCID").toString();
                        cfo.PRIPID = temp.get("PRIPID").toString();
                        cfo.ENTTYPE = temp.get("ENTTYPE").toString();
                        DataManager.BaseinfoList.add(cfo);
                    }
                }
                if (DataManager.allcountsList != null && DataManager.allcountsList.size() > 0) {
                    MyconcernActivity.handler.sendEmptyMessage(1);
                } else {
                    SearchFirmActivty.handler.sendEmptyMessage(500);
                }
                break;
            case 0x026://我的关注跳公司详情界面的请求
                jsonString = (String) response.get();
                map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<LinkedTreeMap> lists26 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("allcount");
                List<LinkedTreeMap> lists261 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("baseinfo");
                if (DataManager.allcountsList != null) {
                    DataManager.allcountsList.clear();
                }
                if (DataManager.BaseinfoList != null) {
                    DataManager.BaseinfoList.clear();
                }
                if (lists26 != null && lists26.size() > 0) {
                    for (LinkedTreeMap temp : lists26) {
                        DataManager.allcount cfo = new DataManager.allcount();
                        cfo.IsFavorite = temp.get("IsFavorite").toString();
                        cfo.HonorCount = temp.get("HonorCount").toString();
                        cfo.JudiciaryCount = temp.get("JudiciaryCount").toString();
                        cfo.PledgeCount = temp.get("PledgeCount").toString();
                        cfo.CopyrightCount = temp.get("CopyrightCount").toString();
                        cfo.AnnualCount = temp.get("AnnualCount").toString();
                        cfo.AdvertisementCount = temp.get("AdvertisementCount").toString();
                        cfo.BaseInfoCount = temp.get("BaseInfoCount").toString();
                        cfo.ApprovalCount = temp.get("ApprovalCount").toString();
                        cfo.PunishCount = temp.get("PunishCount").toString();
                        cfo.WarningCount = temp.get("WarningCount").toString();
                        cfo.TrademarkCount = temp.get("TrademarkCount").toString();
                        cfo.AbnormityCount = temp.get("AbnormityCount").toString();
                        cfo.CreditCount = temp.get("CreditCount").toString();
                        cfo.SupportCount = temp.get("SupportCount").toString();
                        cfo.MortgagorCount = temp.get("MortgagorCount").toString();
                        cfo.PatentCount = temp.get("PatentCount").toString();
                        cfo.PageView=temp.get("PageView").toString();
                        cfo.IsClaim=temp.get("IsClaim").toString();
                        DataManager.allcountsList.add(cfo);
                    }
                }
                if (lists261 != null && lists261.size() > 0) {
                    for (LinkedTreeMap temp : lists261) {
                        DataManager.Baseinfo cfo = new DataManager.Baseinfo();
                        cfo.EnterAddtionID = temp.get("EnterAddtionID").toString();
                        cfo.REGSTATE = temp.get("REGSTATE").toString();
                        cfo.REGNO = temp.get("REGNO").toString();
                        cfo.IsFavorite = temp.get("IsFavorite").toString();
                        cfo.NAME = temp.get("NAME").toString();
                        cfo.REGCAP = temp.get("REGCAP").toString();
                        cfo.ESTDATE = temp.get("ESTDATE").toString();
                        cfo.ENTTYPE_CN = temp.get("ENTTYPE_CN").toString();
                        cfo.ENTNAME = temp.get("ENTNAME").toString();
                        cfo.REGSTATE_CN = temp.get("REGSTATE_CN").toString();
                        cfo.UNISCID = temp.get("UNISCID").toString();
                        cfo.PRIPID = temp.get("PRIPID").toString();
                        cfo.ENTTYPE = temp.get("ENTTYPE").toString();
                        DataManager.BaseinfoList.add(cfo);
                    }
                }
                if (DataManager.allcountsList != null && DataManager.allcountsList.size() > 0) {
                    MainActivity.handler.sendEmptyMessage(8);
                }
                break;
            case 0x000://工商信息
                /*DataManager.Data0List.clear();
                String jstring0 = (String) response.get();
                DataManager.Root0 jsonRoot0 = gson.fromJson(jstring0, new TypeToken<DataManager.Root0>() {
                }.getType());
                DataManager.Data0 dt = jsonRoot0.data;
                DataManager.Data0List.add(dt);
                if (DataManager.Data0List != null && DataManager.Data0List.size() > 0) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(0);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }*/

                jsonString = (String) response.get();
                DataManager.gsxx = gson.fromJson(jsonString, DataManager.GSXX.class);
                if (DataManager.gsxx != null) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(0);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x001://行政信息
                String jstring1 = (String) response.get();
                map = gson.fromJson(jstring1, new TypeToken<Map<String, Object>>() {
                }.getType());

                List<LinkedTreeMap> list1 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("administrative");
                List<LinkedTreeMap> list221 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("other");
                if (DataManager.ad_List != null) {
                    DataManager.ad_List.clear();
                }
                if (DataManager.admin_other_List != null) {
                    DataManager.admin_other_List.clear();
                }
                if (list1 != null && list1.size() > 0) {
                    for (LinkedTreeMap temp : list1) {
                        DataManager.administraton cfo = new DataManager.administraton();
                        cfo.PRIPID = (String) temp.get("PRIPID");
                        cfo.LICNAME = (String) temp.get("LICNAME");
                        cfo.LICNO = (String) temp.get("LICNO");
                        cfo.VALFROM = (String) temp.get("VALFROM");
                        cfo.LICANTH = (String) temp.get("LICANTH");
                        cfo.VALTO = (String) temp.get("VALTO");
                        DataManager.ad_List.add(cfo);
                    }
                }
                if (list221 != null && list221.size() > 0) {
                    for (LinkedTreeMap temp : list221) {
                        DataManager.admin_other cfo = new DataManager.admin_other();
                        cfo.LICANTH = (String) temp.get("LICANTH");
                        cfo.REGNO = (String) temp.get("REGNO");
                        cfo.VALFROM = (String) temp.get("VALFROM");
                        cfo.LICNAME_CN = (String) temp.get("LICNAME_CN");
                        cfo.LICID = (String) temp.get("LICID");
                        cfo.ENTNAME = (String) temp.get("ENTNAME");
                        cfo.LICNO = (String) temp.get("LICNO");
                        cfo.VALTO = (String) temp.get("VALTO");
                        cfo.PRIPID = (String) temp.get("PRIPID");
                        cfo.TYPE = (String) temp.get("TYPE");
                        cfo.LICITEM = (String) temp.get("LICITEM");
                        DataManager.admin_other_List.add(cfo);
                    }
                }
                if (DataManager.ad_List != null || DataManager.admin_other_List != null) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(1);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x002://荣誉信息
                String jstring2 = (String) response.get();
//                map = gson.fromJson(jstring2, new TypeToken<Map<String, Object>>() {
//                }.getType());
//                List<DataManager.honorInfo> list2 = gson.fromJson(((Map<String, Object>) map.get("data")).get("chattel").toString().trim(), new TypeToken<List<DataManager.honorInfo>>() {
//                }.getType());
//                DataManager.honorInfoList = list2;
//                以下代码解决空格问题c
                map = gson.fromJson(jstring2, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<LinkedTreeMap> list2 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("chattel");

                if (DataManager.honorInfoList != null) {
                    DataManager.honorInfoList.clear();
                }
                if (list2 != null && list2.size() > 0) {
                    for (LinkedTreeMap temp : list2) {
                        DataManager.honorInfo cfo = new DataManager.honorInfo();
                        cfo.HONORID = (String) temp.get("HONORID");
                        cfo.HONORNAME = (String) temp.get("HONORNAME");
                        cfo.HONORCONTENT = (String) temp.get("HONORCONTENT");
                        cfo.ORGAN = (String) temp.get("ORGAN");
                        cfo.C_UNIQUE_CODE = (String) temp.get("C_UNIQUE_CODE");

                        DataManager.honorInfoList.add(cfo);
                    }
                }

                if (DataManager.honorInfoList != null && DataManager.honorInfoList.size() > 0) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(2);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x003://企业扶持信息
                String jstring3 = (String) response.get();
                DataManager.supportInfoS = gson.fromJson(jstring3, DataManager.supportInfo.class);
                if (DataManager.supportInfoS != null ) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(3);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x004://抵押信息/动产
                jsonString = (String) response.get();
//                map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
//                }.getType());
//                DataManager.mortgageMP_List = gson.fromJson(((Map<String, Object>) map.get("data")).get("chattel").toString().trim(), new TypeToken<List<DataManager.mortgageMP>>() {
//                }.getType());
                map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<LinkedTreeMap> list41 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("chattel");

                if (DataManager.mortgageMP_List != null) {
                    DataManager.mortgageMP_List.clear();
                }
                if (list41 != null && list41.size() > 0) {
                    for (LinkedTreeMap temp : list41) {
                        DataManager.mortgageMP cfo = new DataManager.mortgageMP();
                        cfo.MORREG_ID = (String) temp.get("MORREG_ID");
                        cfo.MORREGCNO = (String) temp.get("MORREGCNO");
                        cfo.REGIDATE = (String) temp.get("REGIDATE");
                        cfo.PUBLICDATE = (String) temp.get("PUBLICDATE");
                        cfo.REGORG_CN = (String) temp.get("REGORG_CN");
                        cfo.PRICLASECAM = (String) temp.get("PRICLASECAM");
                        DataManager.mortgageMP_List.add(cfo);
                    }
                }

                break;
            case 0x0041://抵押信息/不动产
                jsonString = (String) response.get();
//                map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
//                }.getType());
//                DataManager.mortgageRE_List = gson.fromJson(((Map<String, Object>) map.get("data")).get("realEstate").toString().trim(), new TypeToken<List<DataManager.mortgageRE>>() {
//                }.getType());

                map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<LinkedTreeMap> list412 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("realEstate");

                if (DataManager.mortgageRE_List != null) {
                    DataManager.mortgageRE_List.clear();
                }
                if (list412 != null && list412.size() > 0) {
                    for (LinkedTreeMap temp : list412) {
                        DataManager.mortgageRE cfo = new DataManager.mortgageRE();
                        cfo.C_INFOID = (String) temp.get("C_INFOID");
                        cfo.C_DYDJZH = (String) temp.get("C_DYDJZH");
                        cfo.D_DJRQ = (String) temp.get("D_DJRQ");
                        cfo.D_SQRQ = (String) temp.get("D_SQRQ");
                        cfo.C_DJJG = (String) temp.get("C_DJJG");
                        cfo.C_DBFW = (String) temp.get("C_DBFW");
                        DataManager.mortgageRE_List.add(cfo);
                    }
                }

                if (DataManager.mortgageRE_List != null && DataManager.mortgageRE_List.size() > 0) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(4);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x005://出质信息
                String jstring5 = (String) response.get();
                DataManager.Root5 jsonRoot5 = gson.fromJson(jstring5, new TypeToken<DataManager.Root5>() {
                }.getType());
                DataManager.pledgeInfoList = jsonRoot5.data;
                if (DataManager.pledgeInfoList != null && DataManager.pledgeInfoList.size() > 0) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(5);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x006://司法信息
                String jstring6 = (String) response.get();
                map = gson.fromJson(jstring6, new TypeToken<Map<String, Object>>() {
                }.getType());
                /**
                 * json报错解析方法
                 */
//                List<DataManager.JudicialDocuments> list61 = gson.fromJson(((Map<String, Object>) map.get("data")).get("judicialDocuments").toString(), new TypeToken<List<DataManager.JudicialDocuments>>() {
//                }.getType());
//                List<DataManager.CrackCredit> list62 = gson.fromJson(((Map<String, Object>) map.get("data")).get("crackCredit").toString(), new TypeToken<List<DataManager.CrackCredit>>() {
//                }.getType());
//                List<DataManager.ShareholderInformationChange> list63 = gson.fromJson(((Map<String, Object>) map.get("data")).get("shareholderInformationChange").toString(), new TypeToken<List<DataManager.ShareholderInformationChange>>() {
//                }.getType());
//                List<DataManager.FrozenInformation> list64 = gson.fromJson(((Map<String, Object>) map.get("data")).get("frozenInformation").toString(), new TypeToken<List<DataManager.FrozenInformation>>() {
//                }.getType());
                List<LinkedTreeMap> list61=(List<LinkedTreeMap>)((Map<String, Object>) map.get("data")).get("judicialDocuments");
                List<LinkedTreeMap> list62=(List<LinkedTreeMap>)((Map<String, Object>) map.get("data")).get("crackCredit");
                List<LinkedTreeMap> list63=(List<LinkedTreeMap>)((Map<String, Object>) map.get("data")).get("shareholderInformationChange");
                List<LinkedTreeMap> list64=(List<LinkedTreeMap>)((Map<String, Object>) map.get("data")).get("frozenInformation");
                /**
                 * 司法文书信息
                 */
                if(DataManager.JudicialDocumentsList!=null){
                    DataManager.JudicialDocumentsList.clear();
                }
                if (list61 != null && list61.size() > 0) {
                    for (LinkedTreeMap temp : list61) {
                        DataManager.JudicialDocuments jud = new DataManager.JudicialDocuments();
                        jud.CASENUM= (String) temp.get("CASENUM");
                        jud.REDECORG_CN= (String) temp.get("REDECORG_CN");
                        jud.SENTENCECONMENT= (String) temp.get("SENTENCECONMENT");
                        jud.SENTENCEDATE= (String) temp.get("SENTENCEDATE");
                        jud.SUPDEPARTMENT= (String) temp.get("SUPDEPARTMENT");
                        DataManager.JudicialDocumentsList.add(jud);
                    }
                }
                /**
                 * 失信被执行人信息
                 */
                if(DataManager.CrackCreditList!=null){
                    DataManager.CrackCreditList.clear();
                }
                if (list62 != null && list62.size() > 0) {
                    for (LinkedTreeMap temp : list62) {
                        DataManager.CrackCredit cra = new DataManager.CrackCredit();
                        cra.COURT_NAME= (String) temp.get("COURT_NAME");
                        cra.COURTCASEID= (String) temp.get("COURTCASEID");
                        cra.DISREPUT_TYPE_NAME= (String) temp.get("DISREPUT_TYPE_NAME");
                        cra.GIST_CID= (String) temp.get("GIST_CID");
                        cra.PERFORMANCE= (String) temp.get("PERFORMANCE");
                        cra.REG_DATE= (String) temp.get("REG_DATE");
                        DataManager.CrackCreditList.add(cra);
                    }
                }
                /**
                 * 股东变更信息
                 */
                if(DataManager.ShareholderInformationChangeList!=null){
                    DataManager.ShareholderInformationChangeList.clear();
                }
                if (list63 != null && list63.size() > 0) {
                    for (LinkedTreeMap temp : list63) {
                        DataManager.ShareholderInformationChange sha = new DataManager.ShareholderInformationChange();
                        sha.ALIEN= (String) temp.get("ALIEN");
                        sha.FROAM= (Double) temp.get("FROAM");
                        sha.FROAUTH= (String) temp.get("FROAUTH");
                        sha.INV= (String) temp.get("INV");
                        sha.REGNO= (String) temp.get("REGNO");
                        DataManager.ShareholderInformationChangeList.add(sha);
                    }
                }
                /**
                 * 股权冻结信息
                 */
                if(DataManager.FrozenInformationList!=null){
                    DataManager.FrozenInformationList.clear();
                }
                if (list64 != null && list64.size() > 0) {
                    for (LinkedTreeMap temp : list64) {
                        DataManager.FrozenInformation fro =new DataManager.FrozenInformation();
                        fro.FROAM= (String) temp.get("FROAM");
                        fro.FROAUTH= (String) temp.get("FROAUTH");
                        fro.FROFROM= (String) temp.get("FROFROM");
                        fro.FROID= (String) temp.get("FROID");
                        fro.FROZDEADLINE= (String) temp.get("FROZDEADLINE");
                        fro.INVTYPE_CN= (String) temp.get("INVTYPE_CN");
                        DataManager.FrozenInformationList.add(fro);
                    }
                }

//                DataManager.JudicialDocumentsList = list61;//司法文书信息
//                DataManager.CrackCreditList = list62;//失信被执行人信息
//                DataManager.ShareholderInformationChangeList = list63;//股东变更信息
//                DataManager.FrozenInformationList = list64;//股权冻结信息

                CompanyDetailsActivity.handler.sendEmptyMessage(6);
                break;
            case 0x007://预警信息zlh
                String jsonstring = (String) response.get();
                DataManager.AlertInfoS = gson.fromJson(jsonstring, DataManager.AlertInfo.class);
                if (DataManager.AlertInfoS.data.size() > 0 && DataManager.AlertInfoS != null) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(7);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x008://行政处罚
                String jstring8 = (String) response.get();
                DataManager.Root8 jsonRoot8 = gson.fromJson(jstring8, new TypeToken<DataManager.Root8>() {
                }.getType());
                DataManager.punishInfoList = jsonRoot8.data;
                if (DataManager.punishInfoList.size() > 0 && DataManager.punishInfoList != null) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(8);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x009://经营异常信息
                jsonString = (String) response.get();
//                map = gson.fromJson(jstring9, new TypeToken<Map<String, Object>>() {
//                }.getType());
//                List<DataManager.abnormalInfo> list9 = gson.fromJson(((Map<String, Object>) map.get("data")).get("abNormal").toString(), new TypeToken<List<DataManager.abnormalInfo>>() {
//                }.getType());
//                map = gson.fromJson(jstring9, new TypeToken<Map<String, Object>>() {
//                }.getType());
//                List<LinkedTreeMap> list9 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("abNormal");
//
//                if (DataManager.abnormalInfoList != null) {
//                    DataManager.abnormalInfoList.clear();
//                }
//                if (list9 != null && list9.size() > 0) {
//                    for (LinkedTreeMap temp : list9) {
//                        DataManager.abnormalInfo cfo = new DataManager.abnormalInfo();
//                        cfo.BUSEXCLIST = (String) temp.get("BUSEXCLIST");
//                        cfo.SPECAUSE_CN = (String) temp.get("SPECAUSE_CN");
//                        cfo.ABNTIME = (String) temp.get("ABNTIME");
//                        cfo.DECORG_CN = (String) temp.get("DECORG_CN");
//                        cfo.REMEXCPRES_CN = (String) temp.get("REMEXCPRES_CN");
//                        cfo.REMDATE = (String) temp.get("REMDATE");
//                        cfo.REDECORG_CN = (String) temp.get("REDECORG_CN");
//                        DataManager.abnormalInfoList.add(cfo);
//                    }

                DataManager.abnormalInfoS = gson.fromJson(jsonString, DataManager.abnormalInfo.class);
                CompanyDetailsActivity.handler.sendEmptyMessage(9);

                break;
            case 0x010://专利信息
                jsonString = (String) response.get();
                DataManager.PatentInfoS = gson.fromJson(jsonString, DataManager.PatentInfo.class);

                if (DataManager.PatentInfoS.patentInfo != null) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(10);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x011://商标信息
                String jstring11 = (String) response.get();
                map = gson.fromJson(jstring11, new TypeToken<Map<String, Object>>() {
                }.getType());
//                List<DataManager.trademarkInfo> list11 = gson.fromJson(((Map<String, Object>) map.get("data")).get("trademark").toString(), new TypeToken<List<DataManager.trademarkInfo>>() {
//                }.getType());
                map = (Map<String, Object>) map.get("data");
                List<LinkedTreeMap> list11 = (List<LinkedTreeMap>) map.get("trademark");
                if (DataManager.trademarkInfoList.size() != 0) {
                    DataManager.trademarkInfoList.clear();
                }
                if (list11 != null && list11.size() > 0) {
                    for (LinkedTreeMap temp : list11) {
                        DataManager.trademarkInfo trademarkInfo = new DataManager.trademarkInfo();
                        trademarkInfo.ID = (String) temp.get("ID");
                        trademarkInfo.REGNO = (String) temp.get("REGNO");
                        trademarkInfo.PRIPID = (String) temp.get("PRIPID");
                        trademarkInfo.APPLICATIONDATE = (String) temp.get("APPLICATIONDATE");
                        trademarkInfo.APPLICANT = (String) temp.get("APPLICANT");
                        trademarkInfo.BRANDSTAUTS = (String) temp.get("BRANDSTAUTS");
                        trademarkInfo.CLASSIFYID = (String) temp.get("CLASSIFYID");
                        trademarkInfo.BRANDIMG = (String) temp.get("BRANDIMG");
                        trademarkInfo.AGENCY = (String) temp.get("AGENCY");
                        trademarkInfo.LIFESPAN = (String) temp.get("LIFESPAN");
                        trademarkInfo.REGCORE = (String) temp.get("REGCORE");
                        trademarkInfo.BRANDNAME = (String) temp.get("BRANDNAME");
                        trademarkInfo.ENTNAME = (String) temp.get("ENTNAME");
                        trademarkInfo.UNISCID = (String) temp.get("UNISCID");
                        DataManager.trademarkInfoList.add(trademarkInfo);
                    }
                }

                if (DataManager.trademarkInfoList.size() > 0 && DataManager.trademarkInfoList != null) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(11);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }
                break;
            case 0x012://著作信息
                String jstrin12 = (String) response.get();
                map = gson.fromJson(jstrin12, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<LinkedTreeMap> list112 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("patentInfo");
                List<LinkedTreeMap> list112_1 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("patentInfoSoftwore");
                ;


                if (DataManager.copyrightInfoeList != null) {
                    DataManager.copyrightInfoeList.clear();
                }

                if (list112 != null && list112.size() > 0) {
                    for (LinkedTreeMap temp : list112) {
                        DataManager.copyrightInfo cfo = new DataManager.copyrightInfo();
                        cfo.ID = (String) temp.get("ID");
                        cfo.REGISTERDATA = (String) temp.get("REGISTERDATA");
                        cfo.REGISTERID = (String) temp.get("REGISTERID");

                        cfo.WORKNAME = (String) temp.get("WORKNAME");
                        cfo.WORKCLASS = (String) temp.get("WORKCLASS");
                        cfo.FINISHDATE = (String) temp.get("FINISHDATE");
                        cfo.FIRSTDATE = (String) temp.get("FIRSTDATE");
                        DataManager.copyrightInfoeList.add(cfo);
                    }
                }
                if (list112_1 != null && list112_1.size() > 0) {
                    for (LinkedTreeMap temp : list112_1) {
                        DataManager.copyrightInfo cfo = new DataManager.copyrightInfo();
                        cfo.ID = (String) temp.get("ID");
                        cfo.REGISTERDATA = (String) temp.get("REGISTERDATA");
                        cfo.REGISTERID = (String) temp.get("REGISTERID");

                        cfo.SOFTWARENAME = "【软件】" + (String) temp.get("SOFTWARENAME");
                        cfo.SOFTWARESHORT = (String) temp.get("SOFTWARESHORT");
                        cfo.STARTINGDATE = (String) temp.get("STARTINGDATE");
                        DataManager.copyrightInfoeList.add(cfo);
                    }
                }

                if (DataManager.copyrightInfoeList != null && DataManager.copyrightInfoeList.size() > 0) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(12);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x013://广告资质
                String jstring13 = (String) response.get();
//                map = gson.fromJson(jstring13, new TypeToken<Map<String, Object>>() {
//                }.getType());
//                List<DataManager.advertisementInfo> list13 = gson.fromJson(((Map<String, Object>) map.get("data")).get("advertising").toString(), new TypeToken<List<DataManager.advertisementInfo>>() {
//                }.getType());
//                DataManager.advertisementInfoList = list13;

                map = gson.fromJson(jstring13, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<LinkedTreeMap> list13 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("advertising");

                if (DataManager.advertisementInfoList != null) {
                    DataManager.advertisementInfoList.clear();
                }
                if (list13 != null && list13.size() > 0) {
                    for (LinkedTreeMap temp : list13) {
                        DataManager.advertisementInfo cfo = new DataManager.advertisementInfo();
                        cfo.ADVERTID = (String) temp.get("ADVERTID");
                        cfo.C_LEVEL = (String) temp.get("C_LEVEL");
                        cfo.CATEGORY = (String) temp.get("CATEGORY");
                        cfo.IDENTIFYDATE = (String) temp.get("IDENTIFYDATE");
                        cfo.VALFORM = (String) temp.get("VALFORM");
                        cfo.VALTO = (String) temp.get("VALTO");
                        cfo.IDENTIFYORGANS = (String) temp.get("IDENTIFYORGANS");
                        DataManager.advertisementInfoList.add(cfo);
                    }
                }

                if (DataManager.advertisementInfoList != null && DataManager.advertisementInfoList.size() > 0) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(13);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x014://守合同重信用信息
                String jstring14 = (String) response.get();
//                map = gson.fromJson(jstring14, new TypeToken<Map<String, Object>>() {
//                }.getType());
//                List<DataManager.obeyedInfo> list14 = gson.fromJson(((Map<String, Object>) map.get("data")).get("contractInfo").toString(), new TypeToken<List<DataManager.obeyedInfo>>() {
//                }.getType());
//                DataManager.obeyedInfoList = list14;
                map = gson.fromJson(jstring14, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<LinkedTreeMap> list14 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("contractInfo");

                if (DataManager.obeyedInfoList != null) {
                    DataManager.obeyedInfoList.clear();
                }
                if (list14 != null && list14.size() > 0) {
                    for (LinkedTreeMap temp : list14) {
                        DataManager.obeyedInfo cfo = new DataManager.obeyedInfo();
                        cfo.PRIPID = (String) temp.get("PRIPID");
                        cfo.ENTNAME = (String) temp.get("ENTNAME");
                        cfo.REGNO = (String) temp.get("REGNO");
                        cfo.UNISCID = (String) temp.get("UNISCID");
                        cfo.CONTENT = (String) temp.get("CONTENT");
                        cfo.IDENTIFYDATE = (String) temp.get("IDENTIFYDATE");
                        cfo.IDENTIFYORGANS = (String) temp.get("IDENTIFYORGANS");
                        cfo.STATE = (String) temp.get("STATE");
                        DataManager.obeyedInfoList.add(cfo);
                    }
                }
                if (DataManager.obeyedInfoList != null && DataManager.obeyedInfoList.size() > 0) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(14);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x015://自主公示zlh
                jsonString = (String) response.get();
                map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                }.getType());
                if ((List<LinkedTreeMap>) map.get("data") != null) {
                    List<LinkedTreeMap> listtemp = (List<LinkedTreeMap>) map.get("data");

                    for (int i = 0; i < listtemp.size(); i++) {
                        switch (listtemp.get(i).get("type").toString()) {
                            case "企业年报":
                                if (listtemp.get(i).get("data") != null) {
                                    /*DataManager.reportList = gson.fromJson(listtemp.get(i).get("data").toString(), new TypeToken<List<DataManager.report>>() {
                                    }.getType());*/
                                    if(DataManager.reportList.size()>0||DataManager.reportList!=null){
                                        DataManager.reportList.clear();
                                    }
                                    for(LinkedTreeMap r:(List<LinkedTreeMap>)listtemp.get(i).get("data")){
                                        DataManager.report report=new DataManager.report();
                                        report.ANCHEDATE= (String) r.get("ANCHEDATE");
                                        report.ANCHEID= (String) r.get("ANCHEID");
                                        report.ANCHEYEAR= (String) r.get("ANCHEYEAR");
                                        DataManager.reportList.add(report);
                                    }

                                }
                                break;
                            case "股东及出资信息":
                                if (listtemp.get(i).get("data") != null) {
                                    /*DataManager.fundedList = gson.fromJson(listtemp.get(i).get("data").toString(), new TypeToken<List<DataManager.funded>>() {
                                    }.getType());*/
                                    if(DataManager.fundedList.size()>0||DataManager.fundedList!=null){
                                        DataManager.fundedList.clear();
                                    }
                                    for (LinkedTreeMap r:(List<LinkedTreeMap>)listtemp.get(i).get("data")){
                                        DataManager.funded funded=new DataManager.funded();
                                        funded.ACCONAM= (double) r.get("ACCONAM");
                                        funded.ACCONDATE= (String) r.get("ACCONDATE");
                                        funded.ACCONFORM= (String) r.get("ACCONFORM");
                                        funded.ACCONFORM_CN= (String) r.get("ACCONFORM_CN");
                                        funded.CONDATE= (String) r.get("CONDATE");
                                        funded.CONFORM= (String) r.get("CONFORM");
                                        funded.CONFORM_CN= (String) r.get("CONFORM_CN");
                                        funded.INV= (String) r.get("INV");
                                        funded.PUBLICDATE= (String) r.get("PUBLICDATE");
                                        funded.SUBCONAM= (double) r.get("SUBCONAM");
                                        funded.ACPUBLICDATE= (String) r.get("ACPUBLICDATE");
                                        DataManager.fundedList.add(funded);

                                    }
                                }
                                break;
                            case "股权变更信息":
                                if (listtemp.get(i).get("data") != null) {
                                    /*DataManager.stockList = gson.fromJson(listtemp.get(i).get("data").toString(), new TypeToken<List<DataManager.stock>>() {
                                    }.getType());*/
                                    if(DataManager.stockList.size()>0||DataManager.stockList!=null){
                                        DataManager.stockList.clear();
                                    }
                                    for(LinkedTreeMap r:(List<LinkedTreeMap>)listtemp.get(i).get("data")){
                                        DataManager.stock stock=new DataManager.stock();
                                        stock.REGNO= (String) r.get("REGNO");
                                        stock.ALTAF= (String) r.get("ALTAF");
                                        stock.ENTNAME= (String) r.get("ENTNAME");
                                        stock.INVUID= (String) r.get("INVUID");
                                        stock.UNISCID= (String) r.get("UNISCID");
                                        stock.PRIPID= (String) r.get("PRIPID");
                                        stock.ALITEM= (String) r.get("ALITEM");
                                        stock.ALTDATE= (String) r.get("ALTDATE");
                                        stock.ALTBE= (String) r.get("ALTBE");
                                        DataManager.stockList.add(stock);
                                    }
                                }
                                break;
                            case "行政许可信息":
                                if (listtemp.get(i).get("data") != null) {
                                    /*DataManager.permitList = gson.fromJson(listtemp.get(i).get("data").toString(), new TypeToken<List<DataManager.permit>>() {
                                    }.getType());*/
                                    if(DataManager.permitList.size()>0||DataManager.permitList!=null){
                                        DataManager.permitList.clear();
                                    }
                                    for(LinkedTreeMap r:(List<LinkedTreeMap>)listtemp.get(i).get("data")){
                                        DataManager.permit permit=new DataManager.permit();
                                        permit.invalidDate= (String) r.get("invalidDate");
                                        permit.LICANTH= (String) r.get("LICANTH");
                                        permit.LICITEM= (String) r.get("LICITEM");
                                        permit.LICNAME_CN= (String) r.get("LICNAME_CN");
                                        permit.LICNO= (String) r.get("LICNO");
                                        permit.PUBLICDATE= (String) r.get("PUBLICDATE");
                                        permit.VALFROM= (String) r.get("VALFROM");
                                        permit.VALTO= (String) r.get("VALTO");
                                        DataManager.permitList.add(permit);
                                    }
                                }
                                break;
                            case "知识产权登记信息":
                                if (listtemp.get(i).get("data") != null) {
                                    /*DataManager.loreList = gson.fromJson(listtemp.get(i).get("data").toString(), new TypeToken<List<DataManager.lore>>() {
                                    }.getType());*/
                                    if(DataManager.loreList.size()>0||DataManager.loreList!=null){
                                        DataManager.loreList.clear();
                                    }
                                    for(LinkedTreeMap r:(List<LinkedTreeMap>)listtemp.get(i).get("data")){
                                        DataManager.lore lore=new DataManager.lore();
                                        lore.TYPENAME=(String) r.get("TYPENAME");
                                        lore.TMNAME=(String) r.get("TMNAME");
                                        lore.INVALIDDATE=(String) r.get("INVALIDDATE");
                                        lore.PLEREGPERFROM=(String) r.get("PLEREGPERFROM");
                                        lore.EQUPLECANREA=(String) r.get("EQUPLECANREA");
                                        lore.CANDATE=(String) r.get("CANDATE");
                                        lore.UNISCID=(String) r.get("UNISCID");
                                        lore.KINDS=(String) r.get("KINDS");
                                        lore.PLEREGPERTO=(String) r.get("PLEREGPERTO");
                                        lore.REGNO=(String) r.get("REGNO");
                                        lore.TMREGNO=(String) r.get("TMREGNO");
                                        lore.PLEDGOR=(String) r.get("PLEDGOR");
                                        lore.PLEID=(String) r.get("PLEID");
                                        lore.ENTNAME=(String) r.get("ENTNAME");
                                        lore.INVALIDREA=(String) r.get("INVALIDREA");
                                        lore.PRIPID=(String) r.get("PRIPID");
                                        lore.PUBLICDATE=(String) r.get("PUBLICDATE");
                                        lore.IMPORG=(String) r.get("IMPORG");
                                        lore.TYPE=(String) r.get("TYPE");
                                        DataManager.loreList.add(lore);
                                    }

                                }
                                break;
                            case "行政处罚信息":
                                if (listtemp.get(i).get("data") != null) {
                                    /*DataManager.punishList = gson.fromJson(listtemp.get(i).get("data").toString(), new TypeToken<List<DataManager.punish>>() {
                                    }.getType());*/
                                    if(DataManager.punishList.size()>0||DataManager.punishList!=null){
                                        DataManager.punishList.clear();
                                    }
                                    for(LinkedTreeMap r:(List<LinkedTreeMap>)listtemp.get(i).get("data")){
                                        DataManager.punish punish=new DataManager.punish();
                                        punish.PENTYPE_CN=(String) r.get("PENTYPE_CN");
                                        punish.REMARK=(String) r.get("REMARK");
                                        punish.UNISCID=(String) r.get("UNISCID");
                                        punish.PENDECISSDATE=(String) r.get("PENDECISSDATE");
                                        punish.PENAM=(Double) r.get("PENAM");
                                        punish.CASEID=(String) r.get("CASEID");
                                        punish.REGNO=(String) r.get("REGNO");
                                        punish.JUDAUTH=(String) r.get("JUDAUTH");
                                        punish.ENTNAME=(String) r.get("ENTNAME");
                                        punish.PENDECNO=(String) r.get("PENDECNO");
                                        punish.PENTYPE=(String) r.get("PENTYPE");
                                        punish.FORFAM=(Double) r.get("FORFAM");
                                        punish.ILLEGACTTYPE=(String) r.get("ILLEGACTTYPE");
                                        punish.PENCONTENT=(String) r.get("PENCONTENT");
                                        punish.PRIPID=(String) r.get("PRIPID");
                                        punish.PUBLICDATE=(String) r.get("PUBLICDATE");
                                        DataManager.punishList.add(punish);
                                    }
                                }
                                break;
                            default:
                                break;
                        }

                    }
                    CompanyDetailsActivity.handler.sendEmptyMessage(15);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }
                break;
            case 0x101://关注企业
                jsonString = (String) response.get();
                DataManager.FavotiteS = gson.fromJson(jsonString, DataManager.Favotite.class);
                CompanyDetailsActivity.handler.sendEmptyMessage(22);
                break;
            case 0x102://取消关注企业
                jsonString = (String) response.get();
                DataManager.FavotiteS = gson.fromJson(jsonString, DataManager.Favotite.class);
                CompanyDetailsActivity.handler.sendEmptyMessage(23);
                break;
            case 0x103://我的关注列表
                jsonString = (String) response.get();
                DataManager.FavotiteListS = gson.fromJson(jsonString, DataManager.FavotiteList.class);
                MainActivity.handler.sendEmptyMessage(5);
                break;
            case 0x201://评论
                jsonString = (String) response.get();
                DataManager.MyCommentlistrS = gson.fromJson(jsonString, DataManager.MyCommentlistr.class);
                if(DataManager.MyCommentlistrS.data.userreview!=null && DataManager.MyCommentlistrS.data.userreview.size()>0) {
                    CommentListActivity.handler.sendEmptyMessage(0);
                }else{
                    CommentListActivity.handler.sendEmptyMessage(500);
                }
                break;
            case 0x202://点赞
                String jstring202 = (String) response.get();
                DataManager.Root202 jsonRoot202 = gson.fromJson(jstring202, new TypeToken<DataManager.Root202>() {
                }.getType());
                DataManager.Data202 d202 = jsonRoot202.data;
                DataManager.Result = d202.result;
//                if (DataManager.Result.equals("1")) {
//                    CommentListDetailsActivity.handler.sendEmptyMessage(2);
//                }
                break;
            case 0x203://差评
                String jstring203 = (String) response.get();
                DataManager.Root202 jsonRoot203 = gson.fromJson(jstring203, new TypeToken<DataManager.Root202>() {
                }.getType());
                DataManager.Data202 d203 = jsonRoot203.data;
                DataManager.Result = d203.result;
//                if (DataManager.Result.equals("1")) {
//                    CommentListDetailsActivity.handler.sendEmptyMessage(2);
//                }
                break;
            case 0x204://发表评论
                String jstring204 = (String) response.get();
                DataManager.Root202 jsonRoot204 = gson.fromJson(jstring204, new TypeToken<DataManager.Root202>() {
                }.getType());
                DataManager.Data202 d204 = jsonRoot204.data;
                DataManager.Result = d204.result;
                if (DataManager.Result.equals("success")) {
                    ToCommentActivity.handler.sendEmptyMessage(1);
                } else {
                    ToCommentActivity.handler.sendEmptyMessage(2);
                }
                break;
            case 0x205://回复评论
                String jstring205 = (String) response.get();
                DataManager.Root202 jsonRoot205 = gson.fromJson(jstring205, new TypeToken<DataManager.Root202>() {
                }.getType());
                DataManager.Data202 d205 = jsonRoot205.data;
                DataManager.Result = d205.result;
                if (DataManager.Result.equals("success")) {
                    CommentListDetailsActivity.handler.sendEmptyMessage(1);
                } else {
                    CommentListDetailsActivity.handler.sendEmptyMessage(2);
                }
                break;
            case 0x206://我的评价
                jsonString = (String) response.get();
                DataManager.MyComms = gson.fromJson(jsonString, DataManager.MyComm.class);
                MainActivity.handler.sendEmptyMessage(1);
                break;
            case 0x301://提交认领s
                jsonString = (String) response.get();
                DataManager.ClaimUtilsModel = gson.fromJson(jsonString, DataManager.ClaimUtils.class);
                if (DataManager.ClaimUtilsModel.data.result.equals("success")) {
                    ToClaimActivity.handler.sendEmptyMessage(1);
                } else {
                    ToClaimActivity.handler.sendEmptyMessage(500);
                }
                break;
            case 0x302://提交认领附件
                jsonString = (String) response.get();
                DataManager.ClaimUtilsModel = gson.fromJson(jsonString, DataManager.ClaimUtils.class);
                if (DataManager.ClaimUtilsModel.data.result.equals("success")) {
                    ToClaimActivity.handler.sendEmptyMessage(2);
                } else {
                    ToClaimActivity.handler.sendEmptyMessage(500);
                }
                break;
            case 0x303://我的认领列表
                jsonString = (String) response.get();
                DataManager.MyClaimUtilsModel = gson.fromJson(jsonString, DataManager.MyClaimUtils.class);
                MainActivity.handler.sendEmptyMessage(6);
                break;
            case 0x3031://我的认领列表{副}
                jsonString = (String) response.get();
                DataManager.MyClaimUtilsModel = gson.fromJson(jsonString, DataManager.MyClaimUtils.class);
                MyClaimActivity.handler.sendEmptyMessage(2);
                break;
            case 0x304://我的认领详情
                jsonString = (String) response.get();
                DataManager.MyClaimUtilsModel = gson.fromJson(jsonString, DataManager.MyClaimUtils.class);
                MainActivity.handler.sendEmptyMessage(6);
                break;
            case 0x305://取消认领
                jsonString = (String) response.get();
                DataManager.ClaimUtilsModel = gson.fromJson(jsonString, DataManager.ClaimUtils.class);
                if (DataManager.ClaimUtilsModel.data.result.equals("success") || DataManager.ClaimUtilsModel.data.result.equals("fail")) {
                    MyClaimActivity.handler.sendEmptyMessage(1);
                } else {
                    MyClaimActivity.handler.sendEmptyMessage(500);
                }
                break;
            case 0x401://修改个人资料{"message":"Success","status":"1","version":"v1.0"}
                jsonString = (String) response.get();
                DataManager.user = gson.fromJson(jsonString, DataManager.User.class);
                if(DataManager.user.message.equals("successs")){
                    csp.putUser(DataManager.user);
                    UserSetActivity.handler.sendEmptyMessage(1);
                    MainActivity.loginImg(csp.getICONSTEAM());
                }else{
                    UserSetActivity.handler.sendEmptyMessage(2);
                }
                break;
            case 0x501://修改密码
                jsonString = (String) response.get();
                DataManager.user = gson.fromJson(jsonString, DataManager.User.class);
                if(DataManager.user.message.equals("Success")){
                    PassWordActivity.handler.sendEmptyMessage(1);
                }else if(DataManager.user.message.equals("原始密码错误")){
                    PassWordActivity.handler.sendEmptyMessage(3);
                }else{
                    PassWordActivity.handler.sendEmptyMessage(2);
                }

                break;
            case 0x601://二维码名片
                jsonString = (String) response.get();
                DataManager.TwoDimSli = gson.fromJson(jsonString, DataManager.TwoDim.class);
                if(DataManager.TwoDimSli.message.equals("successs")){
                    CompanyDetailsActivity.waitDialog.dismiss();
                    CompanyDetailsActivity.handler.sendEmptyMessage(25);
                }else{
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x999://登入
                jsonString = (String) response.get();
                map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                }.getType());

                if (!map.get("status").equals("1")) {//登入失败谁动了我的账号，给我站出来
                    Toast.show(map.get("message").toString());
                    LoginActivity.wd.dismiss();
                } else {//登入成功
                    DataManager.user = gson.fromJson(jsonString, DataManager.User.class);
                    csp.putUser(DataManager.user);
                    csp.putLoginStatus(true);
                    Toast.show("登录成功");
                    if(!csp.getALIASNAME().equals("")){
                        MainActivity.UserSz.setText(csp.getALIASNAME());
                    }else{
                        MainActivity.UserSz.setText(csp.getUSERNAME());
                    }
                    if(!csp.getICONSTEAM().equals("")){
                        MainActivity.loginImg(csp.getICONSTEAM());
                    }
                    LoginActivity.handler.sendEmptyMessage(0);
                    LoginActivity.wd.dismiss();
                }
                break;
            case 0x998://注册
                jsonString = (String) response.get();
                map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                }.getType());
                if (!map.get("status").equals("1")) {//注册失败
                    RegisterActivity.pd.dismiss();
                    Toast.show("注册失败" + map.get("message").toString());
                } else {//注册成功

                    RegisterActivity.handler.sendEmptyMessage(0);
                }
                break;
            case 0x997://个人中心获取投诉列表
                jsonString = (String) response.get();
                DataManager.myComplaint = gson.fromJson(jsonString, DataManager.MyComplaint.class);
                MainActivity.handler.sendEmptyMessage(2);

                break;
            case 0x996://个人中心取消投诉请求
                jsonString = (String) response.get();
                map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                }.getType());
                if (!map.get("status").equals("1")) {//注册失败
                    MycomplaintsListActivity.pd.dismiss();
                    Toast.show("取消失败" + map.get("message").toString());
                } else {//取消成功
                    MycomplaintsListActivity.handler.sendEmptyMessage(1);
                }
                break;
            case 0x995://获取投诉详情
                jsonString = (String) response.get();
                DataManager.complaintDetail = gson.fromJson(jsonString, DataManager.ComplaintDetail.class);
                MycomplaintsListActivity.handler.sendEmptyMessage(3);
                break;
            case 0x994://获取企业投诉列表
                jsonString = (String) response.get();
                DataManager.myComplaint = gson.fromJson(jsonString, DataManager.MyComplaint.class);
                CompanyDetailsActivity.pd.dismiss();
                CompanyDetailsActivity.handler.sendEmptyMessage(24);
                break;
            case 0x993://提交企业投诉
                jsonString = (String) response.get();
                DataManager.toComplain = gson.fromJson(jsonString, DataManager.ToComplain.class);
                /*map=gson.fromJson(jsonString,new TypeToken<Map<String, Object>>() {
                }.getType());*/
                if (!DataManager.toComplain.status.equals("1")) {//返回提交投诉失败
                    Toast.show("提交投诉失败" + map.get("message"));
                } else {//成功
                    ToComplaintActivity.handler.sendEmptyMessage(1);

                }
                break;
            case 0x992://提交投诉附件
                jsonString = (String) response.get();
                map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                }.getType());
                if (!map.get("status").equals("1")) {//返回提交投诉失败
                    Toast.show("提交投诉图片失败" + map.get("message"));
                } else {//成功
                    ToComplaintActivity.handler.sendEmptyMessage(0);

                }
                break;
            case 0x991://提交投诉后刷新企业投诉
                jsonString = (String) response.get();
                DataManager.myComplaint = gson.fromJson(jsonString, DataManager.MyComplaint.class);
                MycomplaintsListActivity.handler.sendEmptyMessage(5);
                break;
            default:
                break;
        }
    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
        switch (what) {
            case 0x022://搜索接口
                SearchFirmActivty.pd.dismiss();
                break;
            case 0x024://获取企业详情16宫格
                SearchFirmActivty.pd.dismiss();
                break;
            case 0x000://工商信息
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x001://行政审批
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x002://荣誉信息
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x003://扶持信息
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x004://抵押信息
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x005://出质信息
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x006://司法信息
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x007://预警信息
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x008://行政处罚
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x009://经营异常
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x010://专利信息
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x011://商标信息
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x012://著作权
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x013://广告资质
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x014://守合同重信用
                CompanyDetailsActivity.waitDialog.dismiss();
            case 0x015://企业自主公示
                CompanyDetailsActivity.waitDialog.dismiss();
                break;
            case 0x996://个人中心取消投诉请求
                MycomplaintsListActivity.pd.dismiss();
                break;
            case 0x997://个人中心获取投诉列表
                MainActivity.pd.dismiss();
                break;
            case 0x201://公司我的评价链表
                CompanyDetailsActivity.pd.dismiss();
                break;
            case 0x994://获取企业投诉列表
            case 0x993://提交企业投诉
            case 0x992://提交投诉附件
            case 0x991://提交投诉后刷新企业投诉
                ToComplaintActivity.pd.dismiss();
                MycomplaintsListActivity.pd.dismiss();
                break;
            case 0x301://提交认领
                MainActivity.ad.dismiss();
                MainActivity.pd.dismiss();
                break;
            case 0x20111://查询评论列表
                CommentListDetailsActivity.wd.dismiss();
                break;

            default:
                break;

        }
    }
}
