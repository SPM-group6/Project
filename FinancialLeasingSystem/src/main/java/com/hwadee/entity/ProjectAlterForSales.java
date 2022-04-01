package com.hwadee.entity;

import java.sql.Timestamp;

public class ProjectAlterForSales {
    private Integer projectId; //项目ID
    private Integer leaseScheduleUnitPrice;  //建议租赁单位价格
    private String leaseScheduleUnitTime;  //建议租赁单位时间
    private Integer leaseScheduleDuration;  //建议租赁单位时间次数
    private Timestamp retakeEffortTime;  //再生效时间

    public ProjectAlterForSales(Integer projectId, Integer leaseScheduleUnitPrice, String leaseScheduleUnitTime, Integer leaseScheduleDuration, Timestamp retakeEffortTime) {
        this.projectId = projectId;
        this.leaseScheduleUnitPrice = leaseScheduleUnitPrice;
        this.leaseScheduleUnitTime = leaseScheduleUnitTime;
        this.leaseScheduleDuration = leaseScheduleDuration;
        this.retakeEffortTime = retakeEffortTime;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getLeaseScheduleUnitPrice() {
        return leaseScheduleUnitPrice;
    }

    public void setLeaseScheduleUnitPrice(Integer leaseScheduleUnitPrice) {
        this.leaseScheduleUnitPrice = leaseScheduleUnitPrice;
    }

    public String getLeaseScheduleUnitTime() {
        return leaseScheduleUnitTime;
    }

    public void setLeaseScheduleUnitTime(String leaseScheduleUnitTime) {
        this.leaseScheduleUnitTime = leaseScheduleUnitTime;
    }

    public Integer getLeaseScheduleDuration() {
        return leaseScheduleDuration;
    }

    public void setLeaseScheduleDuration(Integer leaseScheduleDuration) {
        this.leaseScheduleDuration = leaseScheduleDuration;
    }

    public Timestamp getRetakeEffortTime() {
        return retakeEffortTime;
    }

    public void setRetakeEffortTime(Timestamp retakeEffortTime) {
        this.retakeEffortTime = retakeEffortTime;
    }

    @Override
    public String toString() {
        return "ProjectAlterForSales{" +
                "projectId=" + projectId +
                ", leaseScheduleUnitPrice=" + leaseScheduleUnitPrice +
                ", leaseScheduleUnitTime='" + leaseScheduleUnitTime + '\'' +
                ", leaseScheduleDuration=" + leaseScheduleDuration +
                ", retakeEffortTime=" + retakeEffortTime +
                '}';
    }
}
