package com.example.credit.Utils;

import com.example.credit.Activitys.CompanyDetailsActivity;
import com.example.credit.Activitys.MainActivity;
import com.example.credit.Activitys.SearchFirmActivty;
import com.example.credit.Entitys.DataManager;
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
                //JsonReader reader = new JsonReader(new StringReader(((Map<String, Object>) map.get("data")).get("city").toString()));
                //reader.setLenient(true);
                //List<DataManager.citys> list =gson.fromJson(((Map<String, Object>) map.get("data")).get("city").toString(),new TypeToken<List<DataManager.citys>>(){}.getType());//((Map<String, Object>) map.get("data")).get("city").toString()
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
                    serchtemp.PRIPID = (String) temp.get("PRIPID");
                    serchtemp.entname = (String) temp.get("entname");
                    serchtemp.REGNO = (String) temp.get("REGNO");
                    serchtemp.REGORG_CN = (String) temp.get("REGORG_CN");
                    serchtemp.NAME = (String) temp.get("NAME");
                    serchtemp.OPFROM = (String) temp.get("OPFROM");
                    serchtemp.OPTO = (String) temp.get("OPTO");
                    serchtemp.REGSTATE_CN = (String) temp.get("REGSTATE_CN");
                    serchtemp.C_PROVINCE = (String) temp.get("C_PROVINCE");
                    serchtemp.D_ADDTIME = (String) temp.get("D_ADDTIME");
                    serchtemp.C_STATE = (String) temp.get("C_STATE");
                    serchtemp.REGCAP = (String) temp.get("REGCAP");
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
                break;
            case 0x024:
                jsonString = (String) response.get();
                map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                }.getType());
                DataManager.allcounts = gson.fromJson(((List<LinkedTreeMap>) ((Map<String, Object>) map.get("data")).get("allcount")).get(0).toString(), new TypeToken<DataManager.allcount>() {
                }.getType());
                //((List<DataManager.allcount>)((Map<String, Object>) map.get("data")).get("allcount")).get(0);
                SearchFirmActivty.handler.sendEmptyMessage(5);
                break;
            case 0x000://工商信息
                DataManager.Data0List.clear();
                String jstring0 = (String) response.get();
                DataManager.Root0 jsonRoot0 = gson.fromJson(jstring0, new TypeToken<DataManager.Root0>() {
                }.getType());
                DataManager.Data0 dt = jsonRoot0.data;
                DataManager.Data0List.add(dt);
                if (DataManager.Data0List != null && DataManager.Data0List.size() > 0) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(0);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x001://行政信息
                String jstring1 = (String) response.get();
                map = gson.fromJson(jstring1, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<DataManager.administraton> list5 = gson.fromJson(((Map<String, Object>) map.get("data")).get("administrative").toString(), new TypeToken<List<DataManager.administraton>>() {
                }.getType());
                DataManager.ad_List = list5;
                if (DataManager.ad_List != null && DataManager.ad_List.size() > 0) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(1);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x002://荣誉信息
                String jstring2 = (String) response.get();
                map = gson.fromJson(jstring2, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<DataManager.honorInfo> list2 = gson.fromJson(((Map<String, Object>) map.get("data")).get("chattel").toString(), new TypeToken<List<DataManager.honorInfo>>() {
                }.getType());
                DataManager.honorInfoList = list2;
                if (DataManager.honorInfoList != null && DataManager.honorInfoList.size() > 0) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(2);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x003://企业扶持信息
                String jstring3 = (String) response.get();
                map = gson.fromJson(jstring3, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<DataManager.supportInfo> list3 = gson.fromJson(((Map<String, Object>) map.get("data")).get("advertising").toString(), new TypeToken<List<DataManager.supportInfo>>() {
                }.getType());
                DataManager.supportInfoList = list3;
                if (DataManager.supportInfoList != null && DataManager.supportInfoList.size() > 0) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(3);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x004://抵押信息/动产
                jsonString = (String) response.get();
                map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                }.getType());
                DataManager.mortgageMP_List = gson.fromJson(((Map<String, Object>) map.get("data")).get("chattel").toString(), new TypeToken<List<DataManager.mortgageMP>>() {
                }.getType());
                break;
            case 0x0041://抵押信息/不动产
                jsonString = (String) response.get();
                map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                }.getType());
                DataManager.mortgageRE_List = gson.fromJson(((Map<String, Object>) map.get("data")).get("realEstate").toString(), new TypeToken<List<DataManager.mortgageRE>>() {
                }.getType());
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
                map = gson.fromJson(jstring9, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<DataManager.abnormalInfo> list9 = gson.fromJson(((Map<String, Object>) map.get("data")).get("abNormal").toString(), new TypeToken<List<DataManager.abnormalInfo>>() {
                }.getType());
                DataManager.abnormalInfoList = list9;
                if (DataManager.abnormalInfoList.size() > 0 && DataManager.abnormalInfoList != null) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(9);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x010://专利信息
                String jstring10 = (String) response.get();
                map = gson.fromJson(jstring10, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<DataManager.patentInfo> list10 = gson.fromJson(((Map<String, Object>) map.get("data")).get("patentInfo").toString(), new TypeToken<List<DataManager.patentInfo>>() {
                }.getType());
                DataManager.patentInfoList = list10;
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

                        cfo.WORKNAME = "【其他】" + (String) temp.get("WORKNAME");
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
                map = gson.fromJson(jstring13, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<DataManager.advertisementInfo> list13 = gson.fromJson(((Map<String, Object>) map.get("data")).get("advertising").toString(), new TypeToken<List<DataManager.advertisementInfo>>() {
                }.getType());
                DataManager.advertisementInfoList = list13;
                if (DataManager.advertisementInfoList != null && DataManager.advertisementInfoList.size() > 0) {
                    CompanyDetailsActivity.handler.sendEmptyMessage(13);
                } else {
                    CompanyDetailsActivity.handler.sendEmptyMessage(500);
                }

                break;
            case 0x014://守合同重信用信息
                String jstring14 = (String) response.get();
                map = gson.fromJson(jstring14, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<DataManager.obeyedInfo> list14 = gson.fromJson(((Map<String, Object>) map.get("data")).get("contractInfo").toString(), new TypeToken<List<DataManager.obeyedInfo>>() {
                }.getType());
                DataManager.obeyedInfoList = list14;
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
