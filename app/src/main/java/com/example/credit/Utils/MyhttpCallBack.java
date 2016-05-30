package com.example.credit.Utils;

import com.example.credit.Activitys.CompanyDetailsActivity;
import com.example.credit.Activitys.SearchFirmActivty;
import com.example.credit.Entitys.DataManager;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.yolanda.nohttp.Response;


import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by alucard on 2016-05-18.
 */
public class MyhttpCallBack implements HttpCallBack {
    Gson gson;
    static Map<String, Object> map;
    String jsonString;

    private static MyhttpCallBack instance;

    public static MyhttpCallBack getInstance() {
        if (instance == null) {
            instance = new MyhttpCallBack();
        }
        return instance;
    }


    @Override

    public void onSucceed(int what, Response response) {
        gson = new Gson();
        switch (what) {
            case 0x01:

                String jstring = (String) response.get();
                map = gson.fromJson(jstring, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<DataManager.citys> list = gson.fromJson(((Map<String, Object>) map.get("data")).get("Result").toString(), new TypeToken<List<DataManager.citys>>() {
                }.getType());
                DataManager.citysList = list;
                break;
            case 0x022:
                String searchstr = (String) response.get();
                Type type = new TypeToken<DataManager.Root>() {
                }.getType();
                DataManager.Root jsonRoot = gson.fromJson(searchstr, type);
                DataManager.DataS str2 = jsonRoot.data;
                DataManager.searchList = str2.Result;
                if (DataManager.searchList != null && DataManager.searchList.size() > 0) {
                    SearchFirmActivty.handler.sendEmptyMessage(0);
                }
                break;
            case 0x023:
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
                break;
            case 0x002://荣誉信息
                String jstring2 = (String) response.get();
                map = gson.fromJson(jstring2, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<DataManager.honorInfo> list2 = gson.fromJson(((Map<String, Object>) map.get("data")).get("chattel").toString(), new TypeToken<List<DataManager.honorInfo>>() {
                }.getType());
                DataManager.honorInfoList = list2;
                CompanyDetailsActivity.handler.sendEmptyMessage(2);
                break;
            case 0x003://企业扶持信息
                String jstring3 = (String) response.get();
                map = gson.fromJson(jstring3, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<DataManager.supportInfo> list3 = gson.fromJson(((Map<String, Object>) map.get("data")).get("support").toString(), new TypeToken<List<DataManager.supportInfo>>() {
                }.getType());
                DataManager.supportInfoList = list3;
                CompanyDetailsActivity.handler.sendEmptyMessage(3);
                break;
            case 0x005://出质信息
                String jstring5 = (String) response.get();
                DataManager.Root5 jsonRoot5 = gson.fromJson(jstring5, new TypeToken<DataManager.Root5>() {
                }.getType());
                DataManager.pledgeInfoList = jsonRoot5.data;
                CompanyDetailsActivity.handler.sendEmptyMessage(5);
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
            case 0x007://预警信息
                String jsonstring = (String) response.get();
                map = gson.fromJson(jsonstring, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<LinkedTreeMap> listtemp2 = (List<LinkedTreeMap>) map.get("data");
                for (int i = 0; i < listtemp2.size(); i++) {
                    switch (listtemp2.get(i).get("type").toString()) {
                        case "证照到期":
                            DataManager.paperwork_expireList = gson.fromJson(listtemp2.get(i).get("data").toString(), new TypeToken<List<DataManager.paperwork_expire>>() {
                            }.getType());
                            break;
                        case "证照过期":
                            DataManager.paperwork_ExpiredList = gson.fromJson(listtemp2.get(i).get("data").toString(), new TypeToken<List<DataManager.paperwork_Expired>>() {
                            }.getType());
                            break;
                        case "责令改正":
                            DataManager.correctionList = gson.fromJson(listtemp2.get(i).get("data").toString(), new TypeToken<List<DataManager.correction>>() {
                            }.getType());
                            break;
                        case "欠贷信息":
                            DataManager.loanList = gson.fromJson(listtemp2.get(i).get("data").toString(), new TypeToken<List<DataManager.loan>>() {
                            }.getType());
                            break;
                        case "欠税信息":
                            DataManager.taxesList = gson.fromJson(listtemp2.get(i).get("data").toString(), new TypeToken<List<DataManager.Taxes>>() {
                            }.getType());
                            break;
                        case "欠薪信息":
                            DataManager.wagesList = gson.fromJson(listtemp2.get(i).get("data").toString(), new TypeToken<List<DataManager.Wages>>() {
                            }.getType());
                            break;
                        default:
                            break;
                    }
                }


                break;
            case 0x008://行政处罚
                String jstring8 = (String) response.get();
                DataManager.Root8 jsonRoot8 = gson.fromJson(jstring8, new TypeToken<DataManager.Root8>() {
                }.getType());
                DataManager.punishInfoList = jsonRoot8.data;
                CompanyDetailsActivity.handler.sendEmptyMessage(8);
                break;
            case 0x009://经营异常信息
                String jstring9 = (String) response.get();
                map = gson.fromJson(jstring9, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<DataManager.abnormalInfo> list9 = gson.fromJson(((Map<String, Object>) map.get("data")).get("abNormal").toString(), new TypeToken<List<DataManager.abnormalInfo>>() {
                }.getType());
                DataManager.abnormalInfoList = list9;
                CompanyDetailsActivity.handler.sendEmptyMessage(9);
                break;
            case 0x010://专利信息
                String jstring10 = (String) response.get();
                map = gson.fromJson(jstring10, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<DataManager.patentInfo> list10 = gson.fromJson(((Map<String, Object>) map.get("data")).get("patentInfo").toString(), new TypeToken<List<DataManager.patentInfo>>() {
                }.getType());
                DataManager.patentInfoList = list10;
                CompanyDetailsActivity.handler.sendEmptyMessage(10);
                break;
            case 0x011://商标信息
                String jstring11 = (String) response.get();
                map = gson.fromJson(jstring11, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<DataManager.trademarkInfo> list11 = gson.fromJson(((Map<String, Object>) map.get("data")).get("trademark").toString(), new TypeToken<List<DataManager.trademarkInfo>>() {
                }.getType());
                DataManager.trademarkInfoList = list11;
                CompanyDetailsActivity.handler.sendEmptyMessage(11);
                break;
            case 0x012://著作信息
                String jstring12 = (String) response.get();
                DataManager.Root12 jsonRoot12 = gson.fromJson(jstring12, new TypeToken<DataManager.Root12>() {
                }.getType());
                DataManager.copyrightInfoList = jsonRoot12.data;
                CompanyDetailsActivity.handler.sendEmptyMessage(12);
                break;
            case 0x013://广告信息
                String jstring13 = (String) response.get();
                map = gson.fromJson(jstring13, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<DataManager.advertisementInfo> list13 = gson.fromJson(((Map<String, Object>) map.get("data")).get("advertising").toString(), new TypeToken<List<DataManager.advertisementInfo>>() {
                }.getType());
                DataManager.advertisementInfoList = list13;
                CompanyDetailsActivity.handler.sendEmptyMessage(13);
                break;
            case 0x014://守合同重信用信息
                String jstring14 = (String) response.get();
                map = gson.fromJson(jstring14, new TypeToken<Map<String, Object>>() {
                }.getType());
                List<DataManager.obeyedInfo> list14 = gson.fromJson(((Map<String, Object>) map.get("data")).get("contractInfo").toString(), new TypeToken<List<DataManager.obeyedInfo>>() {
                }.getType());
                DataManager.obeyedInfoList = list14;
                CompanyDetailsActivity.handler.sendEmptyMessage(14);
                break;
            case 0x015://自主公示
                jsonString = (String) response.get();
                map = gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
                }.getType());
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
                        default:
                            break;
                    }

                }


            default:
                break;
        }

    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {

    }
}
