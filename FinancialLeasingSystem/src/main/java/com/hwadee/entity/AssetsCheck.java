package com.hwadee.entity;

import java.sql.Timestamp;

//资产检查表对应实体类
public class AssetsCheck {
    private int projectId;  //项目id
    private Timestamp checkTime;  //检查时间
    private String checkObject;  //检查对象（租赁物/抵押物）
    private String checkResult;  //检查结果（正常/损坏/遗失）
    private String ps;  //资产检查备注

    public AssetsCheck(int projectId, Timestamp checkTime, String checkObject, String checkResult, String ps) {
        this.projectId = projectId;
        this.checkTime = checkTime;
        this.checkObject = checkObject;
        this.checkResult = checkResult;
        this.ps = ps;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Timestamp getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Timestamp checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckObject() {
        return checkObject;
    }

    public void setCheckObject(String checkObject) {
        this.checkObject = checkObject;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    @Override
    public String toString() {
        return "AssetsCheck{" +
                "projectId=" + projectId +
                ", checkTime=" + checkTime +
                ", checkObject='" + checkObject + '\'' +
                ", checkResult='" + checkResult + '\'' +
                ", ps='" + ps + '\'' +
                '}';
    }
}
