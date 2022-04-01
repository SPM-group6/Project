package com.hwadee.entity;

import java.sql.Timestamp;

public class Project {
    private int projectId;
    private int applicantId;
    private String projectName;
    private Timestamp projectTime;
    private String leasedAsset;
    private String supplier;
    private int price;
    private int leaseScheduleUnitPrice;
    private String leaseScheduleUnitTime;
    private int leaseScheduleDuration;
    private String pawnName;
    private String pawnType;
    private int pawnValue;
    private String certificate;
    private int stateId;
    private int currentStaffId;
    private int salesId;

    public Project(int projectId, int applicantId, String projectName, Timestamp projectTime, String leasedAsset, String supplier, int price, int leaseScheduleUnitPrice, String leaseScheduleUnitTime, int leaseScheduleDuration, String pawnName, String pawnType, int pawnValue, String certificate, int stateId, int currentStaffId, int salesId) {
        this.projectId = projectId;
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
        this.stateId = stateId;
        this.currentStaffId = currentStaffId;
        this.salesId = salesId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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

    public Timestamp getProjectTime() {
        return projectTime;
    }

    public void setProjectTime(Timestamp projectTime) {
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

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public int getCurrentStaffId() {
        return currentStaffId;
    }

    public void setCurrentStaffId(int currentStaffId) {
        this.currentStaffId = currentStaffId;
    }

    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", applicantId=" + applicantId +
                ", projectName='" + projectName + '\'' +
                ", projectTime=" + projectTime +
                ", leasedAsset='" + leasedAsset + '\'' +
                ", supplier='" + supplier + '\'' +
                ", price=" + price +
                ", leaseScheduleUnitPrice=" + leaseScheduleUnitPrice +
                ", leaseScheduleUnitTime=" + leaseScheduleUnitTime +
                ", leaseScheduleDuration=" + leaseScheduleDuration +
                ", pawnName='" + pawnName + '\'' +
                ", pawnType='" + pawnType + '\'' +
                ", pawnValue=" + pawnValue +
                ", certificate='" + certificate + '\'' +
                ", stateId=" + stateId +
                ", currentStaffId=" + currentStaffId +
                ", salesId=" + salesId +
                '}';
    }
}
