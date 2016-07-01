package com.example.credit.Utils;

import com.example.credit.Activitys.CommentListDetailsActivity;
import com.example.credit.Activitys.CompanyDetailsActivity;
import com.example.credit.Activitys.LoginActivity;
import com.example.credit.Activitys.MainActivity;
import com.example.credit.Activitys.RegisterActivity;
import com.example.credit.Activitys.SearchFirmActivty;
import com.example.credit.Activitys.ToCommentActivity;
import com.example.credit.Entitys.DataManager;
import com.example.credit.Entitys.DataManager.Replay2review;
import com.google.gson.Gson;
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
    public static DataManager.allcount allcount = new DataManager.allcount();
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
                map = gson.fromJson(jstring, new TypeToken<Map<String, Object>>() { }.getType());
                //JsonReader reader = new JsonReader(new StringReader(((Map<String, Object>) map.get("data")).get("city").toString()));
                //reader.setLenient(true);
                //List<DataManager.citys> list =gson.fromJson(((Map<String, Object>) map.get("data")).get("city").toString(),new TypeToken<List<DataManager.citys>>(){}.getType());//((Map<String, Object>) map.get("data")).get("city").toString()
                if (((LinkedTreeMap)map.get("data")).size()!=0  ) {
                    List<LinkedTreeMap> list = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("city");
                    //DataManager.citysList=gson.fromJson(list.toString(),new TypeToken<List<DataManager.citys>>(){}.getType());
                    for (int i = 0; i < list.size(); i++) {
                        DataManager.citys temp1 = new DataManager.citys();
                        temp1.c_name = (String) list.get(i).get("c_name");
                        temp1.c_code = (String) list.get(i).get("c_code");
                        temp1.citycode = new ArrayList<>();
                        //temp1.citycode=gson.fromJson(list.get(i).get("citycode").toString(),new TypeToken<List<DataManager.citycode>>(){}.getType());
                        //List<DataManager.citycode> templist=new ArrayList<>();
                        //templist= (List<DataManager.citycode>) list.get(i).get("citycode");
                        List<LinkedTreeMap> templist = (List<LinkedTreeMap>) list.get(i).get("citycode");
                        for (int j = 0; j < templist.size(); j++) {
                            DataManager.citycode temp2 = new DataManager.citycode();
                            //String a= (String) templist.get(j).get("c_code");
                            temp2.c_code = (String) templist.get(j).get("c_code");
                            temp2.c_name = (String) templist.get(j).get("c_name");
                            temp1.citycode.add(temp2);
                        }
                        DataManager.citysList.add(temp1);

                        //DataManager.citysList=gson.fromJson(list.get(i).toString(),new TypeToken<List<DataManager.citys>>(){}.getType());
                        // DataManager.citysList.get(i).citycode= (List<DataManager.citycode>) list.get(i).get("citycode");
                        // for(int j=0;j<list.get(i).)
                    }
                }
                break;
            case 0x111://获取新闻
                String jstring111 = (String) response.get();
//                map = gson.fromJson(jstring111, new TypeToken<Map<String, Object>>() {
//                }.getType());
//                List<DataManager.Newss> list1s11 = gson.fromJson( map.get("result").toString(), new TypeToken<LinkedTreeMap>() {
//                }.getType());
//                DataManager.NewssList = list1s11;
                DataManager.Root11 jsonRoot11 = gson.fromJson(jstring111, new TypeToken<DataManager.Root11>() {
                }.getType());
                DataManager.NewssList = jsonRoot11.result;
                /*if (DataManager.NewssList != null && DataManager.NewssList.size() > 0) {
                    MainActivity.handler.sendEmptyMessage(0);
                }*/
                break;
            case 0x112://获取新闻内容
                String jstring112 = (String) response.get();
//                map = gson.fromJson(jstring111, new TypeToken<Map<String, Object>>() {
//                }.getType());
//                List<DataManager.Newss> list1s11 = gson.fromJson( map.get("result").toString(), new TypeToken<LinkedTreeMap>() {
//                }.getType());
//                DataManager.NewssList = list1s11;
                DataManager.Root11 jsonRoot12 = gson.fromJson(jstring112, new TypeToken<DataManager.Root11>() {
                }.getType());
                DataManager.NewssList = jsonRoot12.result;
                if (DataManager.NewssList != null && DataManager.NewssList.size() > 0) {
                    MainActivity.handler.sendEmptyMessage(0);
                }
                break;
            case 0x022://搜索结果
//                String searchstr = (String) response.get();
//                Type type = new TypeToken<DataManager.Root>() {
//                }.getType();
//                DataManager.Root jsonRoot = gson.fromJson(searchstr, type);
//                DataManager.DataS str2 = jsonRoot.data;
//                DataManager.searchList = str2.Result;
//                if (DataManager.searchList != null && DataManager.searchList.size() > 0) {
//                    SearchFirmActivty.handler.sendEmptyMessage(0);
//                }
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
                if (((LinkedTreeMap)map.get("data")).size()!=0  ) {
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
            case 0x024:
                jsonString = (String) response.get();
                map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<LinkedTreeMap> lists2 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("allcount");
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
                        DataManager.allcountsList.add(cfo);
                    }
                }
                if (lists2 != null && lists2.size() > 0) {
                    for (LinkedTreeMap temp : lists3) {
                        DataManager.Baseinfo cfo = new DataManager.Baseinfo();
                        cfo.EnterAddtionID = temp.get("EnterAddtionID").toString();
                        DataManager.BaseinfoList.add(cfo);
                    }
                }

                if (DataManager.allcountsList != null && DataManager.allcountsList.size() > 0) {
                    SearchFirmActivty.handler.sendEmptyMessage(5);
                } else {
                    SearchFirmActivty.handler.sendEmptyMessage(500);
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
//                List<DataManager.administraton> list5 = gson.fromJson(((Map<String, Object>) map.get("data")).get("administrative").toString().trim(), new TypeToken<List<DataManager.administraton>>() {
//                }.getType());
//                DataManager.ad_List = list5;

                map = gson.fromJson(jstring1, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<LinkedTreeMap> list1 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("administrative");

                if (DataManager.ad_List != null) {
                    DataManager.ad_List.clear();
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


                if (DataManager.ad_List != null && DataManager.ad_List.size() > 0) {
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
//                map = gson.fromJson(jstring3, new TypeToken<Map<String, Object>>() {
//                }.getType());
//                List<DataManager.supportInfo> list3 = gson.fromJson(((Map<String, Object>) map.get("data")).get("advertising").toString().trim(), new TypeToken<List<DataManager.supportInfo>>() {
//                }.getType());
//                DataManager.supportInfoList = list3;

                map = gson.fromJson(jstring3, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<LinkedTreeMap> list3 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("advertising");

                if (DataManager.supportInfoList != null) {
                    DataManager.supportInfoList.clear();
                }
                if (list3 != null && list3.size() > 0) {
                    for (LinkedTreeMap temp : list3) {
                        DataManager.supportInfo cfo = new DataManager.supportInfo();
                        cfo.PRIPID = (String) temp.get("PRIPID");
                        cfo.ENJSPAMOUNT = (String) temp.get("ENJSPAMOUNT");
                        cfo.ENJSPCONTENT = (String) temp.get("ENJSPCONTENT");
                        cfo.IMPSPDEPART = (String) temp.get("IMPSPDEPART");
                        cfo.IMPSPDATE = (String) temp.get("IMPSPDATE");
                        DataManager.supportInfoList.add(cfo);
                    }
                }

                if (DataManager.supportInfoList != null && DataManager.supportInfoList.size() > 0) {
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
                List<DataManager.JudicialDocuments> list61 = gson.fromJson(((Map<String, Object>) map.get("data")).get("judicialDocuments").toString(), new TypeToken<List<DataManager.JudicialDocuments>>() {
                }.getType());
                List<DataManager.CrackCredit> list62 = gson.fromJson(((Map<String, Object>) map.get("data")).get("crackCredit").toString(), new TypeToken<List<DataManager.CrackCredit>>() {
                }.getType());
                List<DataManager.ShareholderInformationChange> list63 = gson.fromJson(((Map<String, Object>) map.get("data")).get("shareholderInformationChange").toString(), new TypeToken<List<DataManager.ShareholderInformationChange>>() {
                }.getType());
                List<DataManager.FrozenInformation> list64 = gson.fromJson(((Map<String, Object>) map.get("data")).get("frozenInformation").toString(), new TypeToken<List<DataManager.FrozenInformation>>() {
                }.getType());

                DataManager.JudicialDocumentsList = list61;//司法文书信息
                DataManager.CrackCreditList = list62;//失信被执行人信息
                DataManager.ShareholderInformationChangeList = list63;//股东变更信息
                DataManager.FrozenInformationList = list64;//股权冻结信息

                CompanyDetailsActivity.handler.sendEmptyMessage(6);
                break;
            case 0x007://预警信息zlh
                String jsonstring = (String) response.get();
                map = gson.fromJson(jsonstring, new TypeToken<Map<String, Object>>() {
                }.getType());

                List<LinkedTreeMap> listtemp2 = (List<LinkedTreeMap>) map.get("data");
                for (int i = 0; i < listtemp2.size(); i++) {
                    switch (listtemp2.get(i).get("type").toString()) {
                        case "zzdq"://zzdq(证照到期)
                            DataManager.pdateList_zzdq = gson.fromJson(listtemp2.get(i).get("date").toString(), new TypeToken<List<DataManager.date>>() {
                            }.getType());
                            break;
                        case "zzgq"://zzgq(证照过期)
                            DataManager.pdateList_zzgq = gson.fromJson(listtemp2.get(i).get("date").toString(), new TypeToken<List<DataManager.date>>() {
                            }.getType());
                            break;
                        case "zlgz"://zlgz（责令改正）
                            DataManager.pdateList_zlgz = gson.fromJson(listtemp2.get(i).get("date").toString(), new TypeToken<List<DataManager.date>>() {
                            }.getType());
                            break;
                        case "qdxx"://qdxx(欠贷信息)
                            DataManager.pdateList_qdxx = gson.fromJson(listtemp2.get(i).get("date").toString(), new TypeToken<List<DataManager.date>>() {
                            }.getType());
                            break;
                        case "qsxx"://qsxx(欠税信息)
                            DataManager.pdateList_qsxx = gson.fromJson(listtemp2.get(i).get("date").toString(), new TypeToken<List<DataManager.date>>() {
                            }.getType());
                            break;
                        case "qxxx"://qxxx(欠薪信息)
                            DataManager.pdateList_qxxx = gson.fromJson(listtemp2.get(i).get("date").toString(), new TypeToken<List<DataManager.date>>() {
                            }.getType());
                            break;
                        default:
                            break;
                    }
                }
                if (listtemp2.size() > 0 && listtemp2 != null) {
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
                String jstring9 = (String) response.get();
//                map = gson.fromJson(jstring9, new TypeToken<Map<String, Object>>() {
//                }.getType());
//                List<DataManager.abnormalInfo> list9 = gson.fromJson(((Map<String, Object>) map.get("data")).get("abNormal").toString(), new TypeToken<List<DataManager.abnormalInfo>>() {
//                }.getType());
                map = gson.fromJson(jstring9, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<LinkedTreeMap> list9 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("abNormal");

                if (DataManager.abnormalInfoList != null) {
                    DataManager.abnormalInfoList.clear();
                }
                if (list9 != null && list9.size() > 0) {
                    for (LinkedTreeMap temp : list9) {
                        DataManager.abnormalInfo cfo = new DataManager.abnormalInfo();
                        cfo.BUSEXCLIST = (String) temp.get("BUSEXCLIST");
                        cfo.SPECAUSE_CN = (String) temp.get("SPECAUSE_CN");
                        cfo.ABNTIME = (String) temp.get("ABNTIME");
                        cfo.DECORG_CN = (String) temp.get("DECORG_CN");
                        cfo.REMEXCPRES_CN = (String) temp.get("REMEXCPRES_CN");
                        cfo.REMDATE = (String) temp.get("REMDATE");
                        cfo.REDECORG_CN = (String) temp.get("REDECORG_CN");
                        DataManager.abnormalInfoList.add(cfo);
                    }
                }
                if (DataManager.abnormalInfoList.size() > 0 && DataManager.abnormalInfoList != null) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(9);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x010://专利信息
                String jstring10 = (String) response.get();
//                map = gson.fromJson(jstring10, new TypeToken<Map<String, Object>>() {
//                }.getType());
//                List<DataManager.patentInfo> list10 = gson.fromJson(((Map<String, Object>) map.get("data")).get("patentInfo").toString().trim(), new TypeToken<List<DataManager.patentInfo>>() {
//                }.getType());
//                DataManager.patentInfoList = list10;

                //以下代码解决空格问题
                map = gson.fromJson(jstring10, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<LinkedTreeMap> list10 = (List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("patentInfo");

                if (DataManager.patentInfoList != null) {
                    DataManager.patentInfoList.clear();
                }
                if (list10 != null && list10.size() > 0) {
                    for (LinkedTreeMap temp : list10) {
                        DataManager.patentInfo cfo = new DataManager.patentInfo();
                        cfo.PRIPID = (String) temp.get("PRIPID");
                        cfo.PATENTNAME = (String) temp.get("PATENTNAME");
                        cfo.RCODE = (String) temp.get("RCODE");
                        cfo.RDATE = (String) temp.get("RDATE");
                        cfo.ACODE = (String) temp.get("ACODE");
                        cfo.ADATE = (String) temp.get("ADATE");
                        cfo.PATENTTYPE = (String) temp.get("PATENTTYPE");
                        cfo.INVENTOR = (String) temp.get("INVENTOR");
                        cfo.AGENCY = (String) temp.get("AGENCY");
                        cfo.LEGALSTATUS = (String) temp.get("LEGALSTATUS");
                        cfo.DETAILINFO = (String) temp.get("DETAILINFO");
                        DataManager.patentInfoList.add(cfo);
                    }
                }


                if (DataManager.patentInfoList.size() > 0 && DataManager.patentInfoList != null) {
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
                        cfo.VALFTO = (String) temp.get("VALFTO");
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
                                DataManager.reportList = gson.fromJson(listtemp.get(i).get("date").toString(), new TypeToken<List<DataManager.report>>() {
                                }.getType());
                                break;
                            case "股东及出资信息":
                                DataManager.fundedList = gson.fromJson(listtemp.get(i).get("date").toString(), new TypeToken<List<DataManager.funded>>() {
                                }.getType());
                                break;
                            case "股权变更信息":
                                DataManager.stockList = gson.fromJson(listtemp.get(i).get("date").toString(), new TypeToken<List<DataManager.stock>>() {
                                }.getType());
                                break;
                            case "行政许可信息":
                                DataManager.permitList = gson.fromJson(listtemp.get(i).get("date").toString(), new TypeToken<List<DataManager.permit>>() {
                                }.getType());
                                break;
                            case "知识产权登记信息":
                                DataManager.loreList = gson.fromJson(listtemp.get(i).get("date").toString(), new TypeToken<List<DataManager.lore>>() {
                                }.getType());
                                break;
                            case "行政处罚信息":
                                DataManager.punishList = gson.fromJson(listtemp.get(i).get("date").toString(), new TypeToken<List<DataManager.punish>>() {
                                }.getType());
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
            case 0x201://评论
                String jstring201 = (String) response.get();
                DataManager.Root201 jsonRoot201 = gson.fromJson(jstring201, new TypeToken<DataManager.Root201>() {
                }.getType());
                DataManager.Data201 d201 = jsonRoot201.data;
                DataManager.UserreviewList = d201.userreview;
                CompanyDetailsActivity.handler.sendEmptyMessage(21);
                break;
            case 0x2011://评论1
                String jstring2011 = (String) response.get();
                DataManager.Root201 jsonRoot2011 = gson.fromJson(jstring2011, new TypeToken<DataManager.Root201>() {
                }.getType());
                DataManager.Data201 d2011 = jsonRoot2011.data;
                DataManager.UserreviewList = d2011.userreview;
                ToCommentActivity.handler.sendEmptyMessage(21);
                break;
            case 0x20111://评论2
                String jstring20111 = (String) response.get();
                DataManager.Root201 jsonRoot20111 = gson.fromJson(jstring20111, new TypeToken<DataManager.Root201>() {
                }.getType());
                DataManager.Data201 d20111 = jsonRoot20111.data;
                DataManager.UserreviewList = d20111.userreview;
                CommentListDetailsActivity.handler.sendEmptyMessage(21);
                break;
            case 0x202://点赞
                String jstring202 = (String) response.get();
                DataManager.Root202 jsonRoot202 = gson.fromJson(jstring202, new TypeToken<DataManager.Root202>() {
                }.getType());
                DataManager.Data202 d202 = jsonRoot202.data;
                DataManager.Result = d202.result;
                if (DataManager.Result.equals("1")) {
//                    CommentListDetailsActivity.handler.sendEmptyMessage(1);
                } else {
                    CommentListDetailsActivity.handler.sendEmptyMessage(3);
                }
                break;
            case 0x203://差评
                String jstring203 = (String) response.get();
                DataManager.Root202 jsonRoot203 = gson.fromJson(jstring203, new TypeToken<DataManager.Root202>() {
                }.getType());
                DataManager.Data202 d203 = jsonRoot203.data;
                DataManager.Result = d203.result;
                if (DataManager.Result.equals("1")) {
//                    CommentListDetailsActivity.handler.sendEmptyMessage(2);
                } else {
                    CommentListDetailsActivity.handler.sendEmptyMessage(4);
                }
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
                jsonString= (String) response.get();
                DataManager.MyComms=gson.fromJson(jsonString,DataManager.MyComm.class);
                MainActivity.handler.sendEmptyMessage(1);
                break;

            case 0x999://登入
                jsonString = (String) response.get();
                map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                }.getType());

                if (!String.valueOf(map.get("status")).equals("1.0")) {//登入失败
                    Toast.show(map.get("message").toString());
                } else {//登入成功
                    DataManager.user = gson.fromJson(jsonString, DataManager.User.class);
                    csp.putUser(DataManager.user);
                    csp.putLoginStatus(true);
                    Toast.show("登录成功");
                    LoginActivity.handler.sendEmptyMessage(0);
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
            case 0x997://获取投诉列表
                jsonString = (String) response.get();
                DataManager.myComplaint = gson.fromJson(jsonString, DataManager.MyComplaint.class);
                MainActivity.handler.sendEmptyMessage(2);
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
            default:
                break;

        }
    }
}
