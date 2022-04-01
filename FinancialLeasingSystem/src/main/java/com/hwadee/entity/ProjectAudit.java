package com.hwadee.entity;

//项目终审表
public class ProjectAudit {
    private int projectId;  //项目id
    private boolean auditOpinionBoolean;  //是否同意
    private String auditOpinions;  //评估意见
    private int auditorId;  //评估员id

    public ProjectAudit(int projectId, boolean auditOpinionBoolean, String auditOpinions, int auditorId) {
        this.projectId = projectId;
        this.auditOpinionBoolean = auditOpinionBoolean;
        this.auditOpinions = auditOpinions;
        this.auditorId = auditorId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectID(int projectId) {
        this.projectId = projectId;
    }

    /**public boolean isAuditOpinionBoolean() {
        return auditOpinionBoolean;
    }*/

    public boolean getAuditOpinionBoolean() {
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
        return "ProjectAudit{" +
                "projectID=" + projectId +
                ", auditOpinionBoolean=" + auditOpinionBoolean +
                ", auditOpinions='" + auditOpinions + '\'' +
                ", auditorId=" + auditorId +
                '}';
    }
}
