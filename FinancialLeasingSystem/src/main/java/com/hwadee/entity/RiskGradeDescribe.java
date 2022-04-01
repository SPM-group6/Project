package com.hwadee.entity;

//风险描述表
public class RiskGradeDescribe {

    private int id;  //风险编号
    private String describe;  //风险描述

    public RiskGradeDescribe(int id, String describe) {
        this.id = id;
        this.describe = describe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "RiskGradeDescribe{" +
                "id=" + id +
                ", describe='" + describe + '\'' +
                '}';
    }

}
