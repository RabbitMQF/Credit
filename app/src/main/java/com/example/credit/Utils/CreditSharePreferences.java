package com.example.credit.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

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


    //保存历史记录
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

}
