package com.hwadee.entity;

//支付类型
public class PayType {
    private int id; //状态号
    private String typeDescribe; //对状态的描述

    public PayType(int id, String typeDescribe) {
        this.id = id;
        this.typeDescribe = typeDescribe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeDescribe() {
        return typeDescribe;
    }

    public void setTypeDescribe(String typeDescribe) {
        this.typeDescribe = typeDescribe;
    }

    @Override
    public String toString() {
        return "PayType{" +
                "id=" + id +
                ", typeDescribe='" + typeDescribe + '\'' +
                '}';
    }
}
