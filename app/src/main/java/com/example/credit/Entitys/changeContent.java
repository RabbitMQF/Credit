package com.example.credit.Entitys;

/**
 * Created by alucard on 2016-05-19.
 */
public class changeContent {
    public String ProjectName;//变更标题
    public String AfterContent;//变更后
    public String BeforeContent;//变更前

    public changeContent( String ProjectName, String AfterContent, String BeforeContent) {
        this.ProjectName = ProjectName;
        this.AfterContent = AfterContent;
        this.BeforeContent = BeforeContent;
    }
}
