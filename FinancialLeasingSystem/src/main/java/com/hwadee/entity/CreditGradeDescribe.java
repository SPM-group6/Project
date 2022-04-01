package com.hwadee.entity;

//信用等级描述表对应实体类
public class CreditGradeDescribe {
    private int id;  //信用等级号
    private String describe;  //信用等级描述

    public CreditGradeDescribe(int id, String describe) {
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
        return "CreditGradeDescribe{" +
                "id=" + id +
                ", describe='" + describe + '\'' +
                '}';
    }
}
