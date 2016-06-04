package com.example.credit.Entitys;

/**
 * Created by alucard on 2016-05-19.
 */
public class changeContent {
    public String ALTITEM_CN;//变更事项
    public String ALTBE;//变更前
    public String ALTAF;//变更后
    public String ALTDATE;//变更日期

    public changeContent() {
    }

    public changeContent(String ALTITEM_CN, String ALTBE, String ALTAF, String ALTDATE) {
        this.ALTITEM_CN = ALTITEM_CN;
        this.ALTBE = ALTBE;
        this.ALTAF = ALTAF;
        this.ALTDATE = ALTDATE;
    }

    public String getALTITEM_CN() {
        return ALTITEM_CN;
    }

    public void setALTITEM_CN(String ALTITEM_CN) {
        this.ALTITEM_CN = ALTITEM_CN;
    }

    public String getALTBE() {
        return ALTBE;
    }

    public void setALTBE(String ALTBE) {
        this.ALTBE = ALTBE;
    }

    public String getALTAF() {
        return ALTAF;
    }

    public void setALTAF(String ALTAF) {
        this.ALTAF = ALTAF;
    }

    public String getALTDATE() {
        return ALTDATE;
    }

    public void setALTDATE(String ALTDATE) {
        this.ALTDATE = ALTDATE;
    }
}
