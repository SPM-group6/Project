package com.hwadee.entity;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author LiuHong
 * @CreateTime 2022/3/6 17:08
 * @Version 1.0.0
 * 一个临时用户类，用于修改个人信息
 */
public class TemUser {
    private int id;
    private String name;
    private String career;
    private int salary;
    private long assets;
    private MultipartFile recentBill;
    private String idCard;
    private String url;//近期银行流水的访问路径

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TemUser(int id, String name, String career, int salary, long assets, MultipartFile recentBill, String idCard) {
        this.id = id;
        this.name = name;
        this.career = career;
        this.salary = salary;
        this.assets = assets;
        this.recentBill = recentBill;
        this.idCard = idCard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
