package com.hwadee.entity;

public class TemCrew {

    private String staffName;  //姓名
    private int authorityId; //职位id
    private String password; //密码

    public TemCrew(){

    }

    public String setTemCrew( String staffName, int authorityId, String password) {

        this.staffName = staffName;
        this.authorityId = authorityId;
        this.password = password;

        return this.toString();

    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "TemCrew{" +
                "staffName='" + staffName + '\'' +
                ", authorityId=" + authorityId +
                ", password='" + password + '\'' +
                '}';
    }
}
