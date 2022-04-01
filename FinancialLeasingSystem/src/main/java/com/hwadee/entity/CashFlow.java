package com.hwadee.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

//资金流表对应实体类
public class CashFlow {
    private int projectId; //项目id
    private Timestamp flowTime;  //资金流动时间
    private int amount;     //资金流动金额
    private int payAccountId;       //支付账号id
    private int payTypeId;      //支付类型id
    private boolean ifPaid;     //是否已经缴费

    public CashFlow(int projectId, Timestamp flowTime, int amount, int payAccountId, int payTypeId, boolean ifPaid) {
        this.projectId = projectId;
        this.flowTime = flowTime;
        this.amount = amount;
        this.payAccountId = payAccountId;
        this.payTypeId = payTypeId;
        this.ifPaid = ifPaid;
    }

//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Timestamp getFlowTime() {
        return flowTime;
    }

    public void setFlowTime(Timestamp flowTime) {
        this.flowTime = flowTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPayAccountId() {
        return payAccountId;
    }

    public void setPayAccountId(int payAccountId) {
        this.payAccountId = payAccountId;
    }

    public int getPayTypeId() {
        return payTypeId;
    }

    public void setPayTypeId(int payTypeId) {
        this.payTypeId = payTypeId;
    }

    public boolean isIfPaid() {
        return ifPaid;
    }

    public void setIfPaid(boolean ifPaid) {
        this.ifPaid = ifPaid;
    }

    @Override
    public String toString() {
        return "CashFlow{" +
                "projectId=" + projectId +
                ", flowTime=" + flowTime +
                ", amount=" + amount +
                ", payAccountId=" + payAccountId +
                ", payTypeId=" + payTypeId +
                ", ifPaid=" + ifPaid +
                '}';
    }
}
