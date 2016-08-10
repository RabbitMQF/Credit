package com.example.credit.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.credit.Entitys.DataManager;

public class CreditSharePreferences {
    private static final String SPNAME = "credit";

    private CreditSharePreferences() {
    }//私有构造方法

    private static CreditSharePreferences esp;
    public static SharedPreferences sp;

    //初始化本地xml文件
    public static void init(Context ctx) {
        if (sp == null) {
            sp = ctx.getSharedPreferences(SPNAME, ctx.MODE_PRIVATE);
        }
    }

    //单例模式
    public static CreditSharePreferences getLifeSharedPreferences() {
        if (esp == null) {
            esp = new CreditSharePreferences();
        }
        return esp;
    }

    /**
     * //保存历史记录
     * @param listory
     */

    public void putHistory(String listory) {
        if(sp!=null){
            Editor editor = sp.edit();
            editor.putString("listory", listory);
            editor.commit();}
    }

    public String getHistory() {
        if(sp!=null) {
            return sp.getString("listory", null);
        }else{
            return "";
        }
    }

    //保存历史记录(临时)
    public void putmainHistory(String mainlistory) {
        if(sp!=null){
            Editor editor = sp.edit();
            editor.putString("mainlistory", mainlistory);
            editor.commit();}
    }

    public String getmainHistory() {
        if(sp!=null) {
            return sp.getString("mainlistory", null);
        }else{
            return "";
        }
    }
    /**
     * 保存是否登录状态
     * @param b
     */
    public void putLoginStatus(Boolean b){
        if(sp!=null){
            Editor editor = sp.edit();
            editor.putBoolean("LoginStatus", b);
            editor.commit();}
    }
    public Boolean getLoginStatus(){
        if(sp!=null) {
            return sp.getBoolean("LoginStatus", false);
        }
        return false;
    }

    /**
     * 保存用户信息
     * @param user 用户实体类
     */
    public void putUser(DataManager.User user) {
        if(sp!=null){
            if(user==null){
                Editor editor =sp.edit();
                editor.clear();
                editor.commit();
                return;
            }
            Editor editor = sp.edit();
            editor.putString("STATUS", user.data.memberList.get(0).STATUS);
            editor.putString("AMOUNT", user.data.memberList.get(0).AMOUNT);
            editor.putString("INDUSTRY", user.data.memberList.get(0).INDUSTRY);
            editor.putString("MOBILE", user.data.memberList.get(0).MOBILE);
            editor.putString("INDUSTRYID", user.data.memberList.get(0).INDUSTRYID);
            editor.putString("USERTYPE", user.data.memberList.get(0).USERTYPE);
            editor.putString("SEX", user.data.memberList.get(0).SEX);
            editor.putString("EDUCATIONID", user.data.memberList.get(0).EDUCATIONID);
            editor.putString("ISDELETE", user.data.memberList.get(0).ISDELETE);
            editor.putString("ID", user.data.memberList.get(0).ID);
            editor.putString("EDUCATION", user.data.memberList.get(0).EDUCATION);
            editor.putString("ICONSTEAM", user.data.memberList.get(0).ICONSTEAM);
            editor.putString("USERNAME", user.data.memberList.get(0).USERNAME);
            editor.putString("EMAIL", user.data.memberList.get(0).EMAIL);
            editor.putString("ALIASNAME", user.data.memberList.get(0).ALIASNAME);
            editor.putString("PASSWORD", user.data.memberList.get(0).PASSWORD);
            editor.putString("USERTYPEID", user.data.memberList.get(0).USERTYPEID);
            editor.commit();
        }
    }

    public void putUserNull(DataManager.User user){
        if(sp!=null){
            Editor editor=sp.edit();

        }
    }


    /**
     * 获取状态：激活1、未激活0 对应数字默认0
     * @return
     */
    public String getSTATUS() {
        if(sp!=null) {
            return sp.getString("STATUS", null);
        }else{
            return "";
        }
    }

    /**
     * 获取余额
     * @return
     */
    public String getAMOUNT() {
        if(sp!=null) {
            return sp.getString("AMOUNT", null);
        }else{
            return "";
        }
    }

    /**
     * 获取行业
     * @return
     */
    public String getINDUSTRY() {
        if(sp!=null) {
            return sp.getString("INDUSTRY", null);
        }else{
            return "";
        }
    }


    /**
     * 获取手机
     * @return
     */
    public String getMOBILE() {
        if(sp!=null) {
            return sp.getString("MOBILE", null);
        }else{
            return "";
        }
    }


    /**
     * 获取行业ID
     * @return
     */
    public String getINDUSTRYID() {
        if(sp!=null) {
            return sp.getString("INDUSTRYID", null);
        }else{
            return "";
        }
    }
    /**
     * 获取用户类型
     * @return
     */
    public String getUSERTYPE() {
        if(sp!=null) {
            return sp.getString("USERTYPE", null);
        }else{
            return "";
        }
    }
    /**
     * 获取性别
     * @return
     */
    public String getSEX() {
        if(sp!=null) {
            return sp.getString("SEX", null);
        }else{
            return "";
        }
    }
    /**
     * 获取教育ID
     * @return
     */
    public String getEDUCATIONID() {
        if(sp!=null) {
            return sp.getString("EDUCATIONID", null);
        }else{
            return "";
        }
    }
    /**
     * 获取账户是否删除 默认0未删除，1已删除
     * @return
     */
    public String getISDELETE() {
        if(sp!=null) {
            return sp.getString("ISDELETE", null);
        }else{
            return "";
        }
    }
    /**
     * 获取用户ID
     * @return
     */
    public String getID() {
        if(sp!=null) {
            return sp.getString("ID", null);
        }else{
            return "";
        }
    }
    /**
     * 教育
     * @return
     */
    public String getEDUCATION() {
        if(sp!=null) {
            return sp.getString("EDUCATION", null);
        }else{
            return "";
        }
    }
    /**
     * 获取头像base64位图
     * @return
     */
    public String getICONSTEAM() {
        if(sp!=null) {
            return sp.getString("ICONSTEAM", null);
        }else{
            return "";
        }
    }
    public void putICONSTEAM(String ICONSTEAM) {
        Editor editor = sp.edit();
        editor.putString("ICONSTEAM", ICONSTEAM);
        editor.commit();
    }
    /**
     * 获取用户名
     * @return
     */
    public String getUSERNAME() {
        if(sp!=null) {
            return sp.getString("USERNAME", null);
        }else{
            return "";
        }
    }
    public void putUSERNAME(String USERNAME) {
        Editor editor = sp.edit();
        editor.putString("USERNAME", USERNAME);
        editor.commit();
    }
    /**
     * 获取EMAIL
     * @return
     */
    public String getEMAIL() {
        if(sp!=null) {
            return sp.getString("EMAIL", null);
        }else{
            return "";
        }
    }
    /**
     * 获取别名
     * @return
     */
    public String getALIASNAME() {
        if(sp!=null) {
            return sp.getString("ALIASNAME", null);
        }else{
            return "";
        }
    }
    public void putALIASNAME(String ALIASNAME) {
        Editor editor = sp.edit();
        editor.putString("ALIASNAME", ALIASNAME);
        editor.commit();
    }
    /**
     * 获取密码
     * @return
     */
    public String getPASSWORD() {
        if(sp!=null) {
            return sp.getString("PASSWORD", null);
        }else{
            return "";
        }
    }
    /**
     * 获取用户类型ID
     * @return
     */
    public String getUSERTYPEID() {
        if(sp!=null) {
            return sp.getString("USERTYPEID", null);
        }else{
            return "";
        }
    }


}
