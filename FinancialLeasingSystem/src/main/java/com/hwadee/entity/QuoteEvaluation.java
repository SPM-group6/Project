package com.hwadee.entity;

import java.sql.Timestamp;

public class QuoteEvaluation {

    private int projectId;  //项目ID
    private String leaseValue;  //租赁市场售卖价格
    private int leaseRentCost;  //租赁市场租赁价格
    private int leaseScheduleUnitPrice;  //建议方案 单位价格
    private String leaseScheduleUnitTime;  //建议方案 单位时间
    private String leaseScheduleDuration;  //建议方案  共计多少单位时间
    private Timestamp loanTime; //建议放款时间
    private int evaluatorId;  //评估员id

    public QuoteEvaluation(int projectId, String leaseValue, int leaseRentCost, int leaseScheduleUnitPrice, String leaseScheduleUnitTime, String leaseScheduleDuration, Timestamp loanTime, int evaluatorId) {
        this.projectId = projectId;
        this.leaseValue = leaseValue;
        this.leaseRentCost = leaseRentCost;
        this.leaseScheduleUnitPrice = leaseScheduleUnitPrice;
        this.leaseScheduleUnitTime = leaseScheduleUnitTime;
        this.leaseScheduleDuration = leaseScheduleDuration;
        this.loanTime = loanTime;
        this.evaluatorId = evaluatorId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getLeaseValue() {
        return leaseValue;
    }

    public void setLeaseValue(String leaseValue) {
        this.leaseValue = leaseValue;
    }

    public int getLeaseRentCost() {
        return leaseRentCost;
    }

    public void setLeaseRentCost(int leaseRentCost) {
        this.leaseRentCost = leaseRentCost;
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

    public String getLeaseScheduleDuration() {
        return leaseScheduleDuration;
    }

    public void setLeaseScheduleDuration(String leaseScheduleDuration) {
        this.leaseScheduleDuration = leaseScheduleDuration;
    }

    public Timestamp getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(Timestamp loanTime) {
        this.loanTime = loanTime;
    }

    public int getEvaluatorId() {
        return evaluatorId;
    }

    public void setEvaluatorId(int evaluatorId) {
        this.evaluatorId = evaluatorId;
    }

    @Override
    public String toString() {
        return "QuoteEvaluation{" +
                "projectId=" + projectId +
                ", leaseValue='" + leaseValue + '\'' +
                ", leaseRentCost=" + leaseRentCost +
                ", leaseScheduleUnitPrice=" + leaseScheduleUnitPrice +
                ", leaseScheduleUnitTime='" + leaseScheduleUnitTime + '\'' +
                ", leaseScheduleDuration='" + leaseScheduleDuration + '\'' +
                ", loanTime=" + loanTime +
                ", evaluatorId=" + evaluatorId +
                '}';
    }
}
