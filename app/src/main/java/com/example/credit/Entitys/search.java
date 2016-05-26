package com.example.credit.Entitys;

/**
 * Created by alucard on 2016-05-19.
 */
public class search {
    public String Gname;//公司名
    public String UserName;//法人
    public String money;//注册资金
    public String state;//存续
    public String time;//注册时间

    public search(String gname, String userName, String money, String state, String time) {
        Gname = gname;
        UserName = userName;
        this.money = money;
        this.state = state;
        this.time = time;
    }
}
