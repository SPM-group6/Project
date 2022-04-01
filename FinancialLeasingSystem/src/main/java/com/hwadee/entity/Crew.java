package com.hwadee.entity;

public class Crew {

    private int staffId;  //账号
    private String staffName;  //姓名
    private int authorityId; //职位id
    private String password; //密码
    private String contact;  //联系方式
    private String yearlyWorkHours; //工龄

    public Crew(int staffId, String staffName, int authorityId, String password, String contact, String yearlyWorkHours) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.authorityId = authorityId;
        this.password = password;
        this.contact = contact;
        this.yearlyWorkHours = yearlyWorkHours;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getYearlyWorkHours() {
        return yearlyWorkHours;
    }

    public void setYearlyWorkHours(String yearlyWorkHours) {
        this.yearlyWorkHours = yearlyWorkHours;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "staffId=" + staffId +
                ", staffName='" + staffName + '\'' +
                ", authorityId=" + authorityId +
                ", password='" + password + '\'' +
                ", contact='" + contact + '\'' +
                ", yearlyWorkHours='" + yearlyWorkHours + '\'' +
                '}';
    }
}
