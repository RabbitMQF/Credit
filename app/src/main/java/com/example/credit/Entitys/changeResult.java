package com.example.credit.Entitys;

import java.util.List;

/**
 * Created by alucard on 2016-05-19.
 */
public class changeResult {
    public String ALTDATE;//变更日期
    public List<changeContent> changedata;//变更信息

    public changeResult(String ALTDATE, List<changeContent> changedata) {
        this.ALTDATE = ALTDATE;
        this.changedata = changedata;
    }

    public changeResult() {
    }

    public String getALTDATE() {
        return ALTDATE;
    }

    public void setALTDATE(String ALTDATE) {
        this.ALTDATE = ALTDATE;
    }

    public List<changeContent> getChangedata() {
        return changedata;
    }

    public void setChangedata(List<changeContent> changedata) {
        this.changedata = changedata;
    }
}
