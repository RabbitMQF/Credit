package com.example.credit.Entitys;

import java.util.List;

/**
 * Created by alucard on 2016-05-19.
 */
public class changeResult {
    public String ChangeDate;
    public List<changeContent> changeContent;

    public changeResult(String ChangeDate, List<changeContent> changeContent) {
        this.ChangeDate = ChangeDate;
        this.changeContent = changeContent;
    }
}
