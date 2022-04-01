package com.hwadee.entity;

//放款审批表对应实体类
public class LoanApproval {
    private int projectId;  //项目id
    private boolean auditOpinionBoolean;  //放款评估结果
    private String auditOpinions;  //放款评估意见
    private int auditorId;  //评估人id

    public LoanApproval(int projectId, boolean auditOpinionBoolean, String auditOpinions, int auditorId) {
        this.projectId = projectId;
        this.auditOpinionBoolean = auditOpinionBoolean;
        this.auditOpinions = auditOpinions;
        this.auditorId = auditorId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public boolean isAuditOpinionBoolean() {
        return auditOpinionBoolean;
    }

    public void setAuditOpinionBoolean(boolean auditOpinionBoolean) {
        this.auditOpinionBoolean = auditOpinionBoolean;
    }

    public String getAuditOpinions() {
        return auditOpinions;
    }

    public void setAuditOpinions(String auditOpinions) {
        this.auditOpinions = auditOpinions;
    }

    public int getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(int auditorId) {
        this.auditorId = auditorId;
    }

    @Override
    public String toString() {
        return "LoanApproval{" +
                "projectId=" + projectId +
                ", auditOpinionBoolean=" + auditOpinionBoolean +
                ", auditOpinions='" + auditOpinions + '\'' +
                ", auditorId=" + auditorId +
                '}';
    }
}
