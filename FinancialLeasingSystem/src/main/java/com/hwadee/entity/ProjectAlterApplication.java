package com.hwadee.entity;

import java.sql.Timestamp;

//变更申请书内容
public class ProjectAlterApplication {

    private Integer projectId; //项目ID
    private Integer leaseScheduleUnitPrice;  //建议租赁单位价格
    private String leaseScheduleUnitTime;  //建议租赁单位时间
    private Integer leaseScheduleDuration;  //建议租赁单位时间次数
    private Timestamp retakeEffortTime;  //再生效时间
    private Integer riskEvaluatorId;  //风险评估员id
    private String riskEvaluation;  //风险评估内容
    private Integer financeEvaluatorId; //财务评估id
    private String financeEvaluation;  //财务评估
    private Integer managerId; // 业务经理id
    private String managerEvaluation; //业务经理意见
    private Boolean ifPassed; // 是否通过

    public ProjectAlterApplication(Integer projectId, Integer leaseScheduleUnitPrice, String leaseScheduleUnitTime, Integer leaseScheduleDuration, Timestamp retakeEffortTime, Integer riskEvaluatorId, String riskEvaluation, Integer financeEvaluatorId, String financeEvaluation, Integer managerId, String managerEvaluation, Boolean ifPassed) {
        this.projectId = projectId;
        this.leaseScheduleUnitPrice = leaseScheduleUnitPrice;
        this.leaseScheduleUnitTime = leaseScheduleUnitTime;
        this.leaseScheduleDuration = leaseScheduleDuration;
        this.retakeEffortTime = retakeEffortTime;
        this.riskEvaluatorId = riskEvaluatorId;
        this.riskEvaluation = riskEvaluation;
        this.financeEvaluatorId = financeEvaluatorId;
        this.financeEvaluation = financeEvaluation;
        this.managerId = managerId;
        this.managerEvaluation = managerEvaluation;
        this.ifPassed = ifPassed;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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

    public Timestamp getRetakeEffortTime() {
        return retakeEffortTime;
    }

    public void setRetakeEffortTime(Timestamp retakeEffortTime) {
        this.retakeEffortTime = retakeEffortTime;
    }

    public int getRiskEvaluatorId() {
        return riskEvaluatorId;
    }

    public void setRiskEvaluatorId(int riskEvaluatorId) {
        this.riskEvaluatorId = riskEvaluatorId;
    }

    public String getRiskEvaluation() {
        return riskEvaluation;
    }

    public void setRiskEvaluation(String riskEvaluation) {
        this.riskEvaluation = riskEvaluation;
    }

    public int getFinanceEvaluatorId() {
        return financeEvaluatorId;
    }

    public void setFinanceEvaluatorId(int financeEvaluatorId) {
        this.financeEvaluatorId = financeEvaluatorId;
    }

    public String getFinanceEvaluation() {
        return financeEvaluation;
    }

    public void setFinanceEvaluation(String financeEvaluation) {
        this.financeEvaluation = financeEvaluation;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getManagerEvaluation() {
        return managerEvaluation;
    }

    public void setManagerEvaluation(String managerEvaluation) {
        this.managerEvaluation = managerEvaluation;
    }

    public boolean isIfPassed() {
        return ifPassed;
    }

    public void setIfPassed(boolean ifPassed) {
        this.ifPassed = ifPassed;
    }

    @Override
    public String toString() {
        return "ProjectAlterApplication{" +
                "projectId=" + projectId +
                ", leaseScheduleUnitPrice=" + leaseScheduleUnitPrice +
                ", leaseScheduleUnitTime='" + leaseScheduleUnitTime + '\'' +
                ", leaseScheduleDuration=" + leaseScheduleDuration +
                ", retakeEffortTime=" + retakeEffortTime +
                ", riskEvaluatorId=" + riskEvaluatorId +
                ", riskEvaluation='" + riskEvaluation + '\'' +
                ", financeEvaluatorId=" + financeEvaluatorId +
                ", financeEvaluation='" + financeEvaluation + '\'' +
                ", managerId=" + managerId +
                ", managerEvaluation='" + managerEvaluation + '\'' +
                ", ifPassed=" + ifPassed +
                '}';
    }
}

