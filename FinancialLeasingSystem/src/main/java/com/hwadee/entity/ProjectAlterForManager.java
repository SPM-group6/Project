package com.hwadee.entity;

import java.sql.Timestamp;

public class ProjectAlterForManager {
    private Integer projectId; //项目ID
    private Integer leaseScheduleUnitPrice;  //建议租赁单位价格
    private String leaseScheduleUnitTime;  //建议租赁单位时间
    private Integer leaseScheduleDuration;  //建议租赁单位时间次数
    private Timestamp retakeEffortTime;  //再生效时间
    private Integer riskEvaluatorId;  //风险评估员id
    private String riskEvaluation;  //风险评估内容
    private Integer financeEvaluatorId; //财务评估id
    private String financeEvaluation;  //财务评估

    public ProjectAlterForManager(Integer projectId, Integer leaseScheduleUnitPrice, String leaseScheduleUnitTime, Integer leaseScheduleDuration, Timestamp retakeEffortTime, Integer riskEvaluatorId, String riskEvaluation, Integer financeEvaluatorId, String financeEvaluation) {
        this.projectId = projectId;
        this.leaseScheduleUnitPrice = leaseScheduleUnitPrice;
        this.leaseScheduleUnitTime = leaseScheduleUnitTime;
        this.leaseScheduleDuration = leaseScheduleDuration;
        this.retakeEffortTime = retakeEffortTime;
        this.riskEvaluatorId = riskEvaluatorId;
        this.riskEvaluation = riskEvaluation;
        this.financeEvaluatorId = financeEvaluatorId;
        this.financeEvaluation = financeEvaluation;
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

    public Integer getFinanceEvaluatorId() {
        return financeEvaluatorId;
    }

    public void setFinanceEvaluatorId(Integer financeEvaluatorId) {
        this.financeEvaluatorId = financeEvaluatorId;
    }

    public String getFinanceEvaluation() {
        return financeEvaluation;
    }

    public void setFinanceEvaluation(String financeEvaluation) {
        this.financeEvaluation = financeEvaluation;
    }

    @Override
    public String toString() {
        return "ProjectAlterForManager{" +
                "projectId=" + projectId +
                ", leaseScheduleUnitPrice=" + leaseScheduleUnitPrice +
                ", leaseScheduleUnitTime='" + leaseScheduleUnitTime + '\'' +
                ", leaseScheduleDuration=" + leaseScheduleDuration +
                ", retakeEffortTime=" + retakeEffortTime +
                ", riskEvaluatorId=" + riskEvaluatorId +
                ", riskEvaluation='" + riskEvaluation + '\'' +
                ", financeEvaluatorId=" + financeEvaluatorId +
                ", financeEvaluation='" + financeEvaluation + '\'' +
                '}';
    }
}
