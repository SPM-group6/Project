package com.hwadee.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

/**
 * @Author LiuZihan
 * @CreateTime 2022/3/9 17:18
 * @Version 1.0.0
 */
public class TemProject {
    private int applicantId;
    private String projectName;
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String projectTime;
    private String leasedAsset;
    private String supplier;
    private int price;
    private int leaseScheduleUnitPrice;
    private String leaseScheduleUnitTime;
    private int leaseScheduleDuration;
    private String pawnName;
    private String pawnType;
    private int pawnValue;
    private MultipartFile certificate;
    private String url;
    private Timestamp time;

    public TemProject(int applicantId, String projectName, String projectTime, String leasedAsset, String supplier, int price, int leaseScheduleUnitPrice, String leaseScheduleUnitTime, int leaseScheduleDuration, String pawnName, String pawnType, int pawnValue, MultipartFile certificate) {
        this.applicantId = applicantId;
        this.projectName = projectName;
        this.projectTime = projectTime;
        this.leasedAsset = leasedAsset;
        this.supplier = supplier;
        this.price = price;
        this.leaseScheduleUnitPrice = leaseScheduleUnitPrice;
        this.leaseScheduleUnitTime = leaseScheduleUnitTime;
        this.leaseScheduleDuration = leaseScheduleDuration;
        this.pawnName = pawnName;
        this.pawnType = pawnType;
        this.pawnValue = pawnValue;
        this.certificate = certificate;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectTime() {
        return projectTime;
    }

    public void setProjectTime(String projectTime) {
        this.projectTime = projectTime;
    }

    public String getLeasedAsset() {
        return leasedAsset;
    }

    public void setLeasedAsset(String leasedAsset) {
        this.leasedAsset = leasedAsset;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getLeaseScheduleUnitPrice() {
        return leaseScheduleUnitPrice;
    }

    public void setLeaseScheduleUnitPrice(int leaseScheduleUnitPrice) {
        this.leaseScheduleUnitPrice = leaseScheduleUnitPrice;
    }

    public String getLeaseScheduleUnitTime() {
        return leaseScheduleUnitTime;
    }

    public void setLeaseScheduleUnitTime(String leaseScheduleUnitTime) {
        this.leaseScheduleUnitTime = leaseScheduleUnitTime;
    }

    public int getLeaseScheduleDuration() {
        return leaseScheduleDuration;
    }

    public void setLeaseScheduleDuration(int leaseScheduleDuration) {
        this.leaseScheduleDuration = leaseScheduleDuration;
    }

    public String getPawnName() {
        return pawnName;
    }

    public void setPawnName(String pawnName) {
        this.pawnName = pawnName;
    }

    public String getPawnType() {
        return pawnType;
    }

    public void setPawnType(String pawnType) {
        this.pawnType = pawnType;
    }

    public int getPawnValue() {
        return pawnValue;
    }

    public void setPawnValue(int pawnValue) {
        this.pawnValue = pawnValue;
    }

    public MultipartFile getCertificate() {
        return certificate;
    }

    public void setCertificate(MultipartFile certificate) {
        this.certificate = certificate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
