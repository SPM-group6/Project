package com.hwadee.entity;

import java.sql.Timestamp;

public class ProjectAlterForRisk {
    private Integer projectId; //项目ID
    private Integer leaseScheduleUnitPrice;  //建议租赁单位价格
    private String leaseScheduleUnitTime;  //建议租赁单位时间
    private Integer leaseScheduleDuration;  //建议租赁单位时间次数
    private Timestamp retakeEffortTime;  //再生效时间
    private Integer riskEvaluatorId;  //风险评估员id
    private String riskEvaluation;  //风险评估内容

    public ProjectAlterForRisk(Integer projectId, Integer leaseScheduleUnitPrice, String leaseScheduleUnitTime, Integer leaseScheduleDuration, Timestamp retakeEffortTime, Integer riskEvaluatorId, String riskEvaluation) {
        this.projectId = projectId;
        this.leaseScheduleUnitPrice = leaseScheduleUnitPrice;
        this.leaseScheduleUnitTime = leaseScheduleUnitTime;
        this.leaseScheduleDuration = leaseScheduleDuration;
        this.retakeEffortTime = retakeEffortTime;
        this.riskEvaluatorId = riskEvaluatorId;
        this.riskEvaluation = riskEvaluation;
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

    public Integer getRiskEvaluatorId() {
        return riskEvaluatorId;
    }

    public void setRiskEvaluatorId(Integer riskEvaluatorId) {
        this.riskEvaluatorId = riskEvaluatorId;
    }

    public String getRiskEvaluation() {
        return riskEvaluation;
    }

    public void setRiskEvaluation(String riskEvaluation) {
        this.riskEvaluation = riskEvaluation;
    }

    @Override
    public String toString() {
        return "ProjectAlterForRisk{" +
                "projectId=" + projectId +
                ", leaseScheduleUnitPrice=" + leaseScheduleUnitPrice +
                ", leaseScheduleUnitTime='" + leaseScheduleUnitTime + '\'' +
                ", leaseScheduleDuration=" + leaseScheduleDuration +
                ", retakeEffortTime=" + retakeEffortTime +
                ", riskEvaluatorId=" + riskEvaluatorId +
                ", riskEvaluation='" + riskEvaluation + '\'' +
                '}';
    }
}
