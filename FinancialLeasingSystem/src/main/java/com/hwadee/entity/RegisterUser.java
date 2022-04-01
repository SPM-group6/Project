package com.hwadee.entity;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author LiuHong
 * @CreateTime 2022/3/8 0:07
 * @Version 1.0.0
 */
public class RegisterUser {
    private String name;
    private String idCard;
    private String pwd;
    private String pwd2;
    private String career;
    private int salary;
    private long assets;
    private MultipartFile recentBill;

    public RegisterUser(String name, String idCard, String pwd, String pwd2, String career, int salary, long assets, MultipartFile recentBill) {
        this.name = name;
        this.idCard = idCard;
        this.pwd = pwd;
        this.pwd2 = pwd2;
        this.career = career;
        this.salary = salary;
        this.assets = assets;
        this.recentBill = recentBill;
    }

    public String getPwd2() {
        return pwd2;
    }

    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public long getAssets() {
        return assets;
    }

    public void setAssets(long assets) {
        this.assets = assets;
    }

    public MultipartFile getRecentBill() {
        return recentBill;
    }

    public void setRecentBill(MultipartFile recentBill) {
        this.recentBill = recentBill;
    }
}
