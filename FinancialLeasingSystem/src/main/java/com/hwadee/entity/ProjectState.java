package com.hwadee.entity;

//项目状态描述表
public class ProjectState {
    private int id;  //状态号
    private String describe;  //状态描述
    private int authorityId; //操作权限（对应的权限号）

    public ProjectState(int id, String describe, int authorityId) {
        this.id = id;
        this.describe = describe;
        this.authorityId = authorityId;
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

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    public String toString() {
        return "ProjectState{" +
                "id=" + id +
                ", describe='" + describe + '\'' +
                ", authorityId=" + authorityId +
                '}';
    }
}
