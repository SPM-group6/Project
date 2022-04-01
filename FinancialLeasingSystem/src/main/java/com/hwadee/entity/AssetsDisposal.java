package com.hwadee.entity;

//资产处置报告
public class AssetsDisposal {

    private Integer projectId; //项目id
    private String pawnDisposal;  //抵押物处理方式
    private String leaseDisposal;  //租赁物处理方式
    private Integer appraisedValue;  //估值
    private Integer financeEvaluatorId;  //财务部人员id
    private String financeOpinions;  //财务部意见
    private Integer managerId;  //业务经理id
    private String managerOpinions;  //业务经理意见
    private Boolean ifPassed;  //经理是否同意通过

    public AssetsDisposal(Integer projectId, String pawnDisposal, String leaseDisposal, Integer appraisedValue, Integer financeEvaluatorId, String financeOpinions, Integer managerId, String managerOpinions, Boolean ifPassed) {
        this.projectId = projectId;
        this.pawnDisposal = pawnDisposal;
        this.leaseDisposal = leaseDisposal;
        this.appraisedValue = appraisedValue;
        this.financeEvaluatorId = financeEvaluatorId;
        this.financeOpinions = financeOpinions;
        this.managerId = managerId;
        this.managerOpinions = managerOpinions;
        this.ifPassed = ifPassed;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getPawnDisposal() {
        return pawnDisposal;
    }

    public void setPawnDisposal(String pawnDisposal) {
        this.pawnDisposal = pawnDisposal;
    }

    public String getLeaseDisposal() {
        return leaseDisposal;
    }

    public void setLeaseDisposal(String leaseDisposal) {
        this.leaseDisposal = leaseDisposal;
    }

    public Integer getAppraisedValue() {
        return appraisedValue;
    }

    public void setAppraisedValue(Integer appraisedValue) {
        this.appraisedValue = appraisedValue;
    }

    public Integer getFinanceEvaluatorId() {
        return financeEvaluatorId;
    }

    public void setFinanceEvaluatorId(Integer financeEvaluatorId) {
        this.financeEvaluatorId = financeEvaluatorId;
    }

    public String getFinanceOpinions() {
        return financeOpinions;
    }

    public void setFinanceOpinions(String financeOpinions) {
        this.financeOpinions = financeOpinions;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerOpinions() {
        return managerOpinions;
    }

    public void setManagerOpinions(String managerOpinions) {
        this.managerOpinions = managerOpinions;
    }

    public Boolean getIfPassed() {
        return ifPassed;
    }

    public void setIfPassed(Boolean ifPassed) {
        this.ifPassed = ifPassed;
    }

    @Override
    public String toString() {
        return "AssetsDisposal{" +
                "projectId=" + projectId +
                ", pawnDisposal='" + pawnDisposal + '\'' +
                ", leaseDisposal='" + leaseDisposal + '\'' +
                ", appraisedValue=" + appraisedValue +
                ", financeEvaluatorId=" + financeEvaluatorId +
                ", financeOpinions='" + financeOpinions + '\'' +
                ", managerId=" + managerId +
                ", managerOpinions='" + managerOpinions + '\'' +
                ", ifPassed=" + ifPassed +
                '}';
    }
}
