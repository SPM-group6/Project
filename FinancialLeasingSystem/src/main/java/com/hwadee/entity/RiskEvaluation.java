package com.hwadee.entity;

//风险评估实体类
public class RiskEvaluation {
    private int projectId;
    private int riskGradeId;
    private String riskEvaluationOpinions;
    private int evaluatorId;



    @Override
    public String toString() {
        return "RiskEvaluation{" +
                "projectId=" + projectId +
                ", riskGradeId=" + riskGradeId +
                ", riskEvaluationOpinions='" + riskEvaluationOpinions + '\'' +
                ", evaluatorId=" + evaluatorId +
                '}';
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getRiskGradeId() {
        return riskGradeId;
    }

    public void setRiskGradeId(int riskGradeId) {
        this.riskGradeId = riskGradeId;
    }

    public String getRiskEvaluationOpinions() {
        return riskEvaluationOpinions;
    }

    public void setRiskEvaluationOpinions(String riskEvaluationOpinions) {
        this.riskEvaluationOpinions = riskEvaluationOpinions;
    }

    public int getEvaluatorId() {
        return evaluatorId;
    }

    public void setEvaluatorId(int evaluatorId) {
        this.evaluatorId = evaluatorId;
    }

    public RiskEvaluation(int projectId, int riskGradeId, String riskEvaluationOpinions, int evaluatorId) {
        this.projectId = projectId;
        this.riskGradeId = riskGradeId;
        this.riskEvaluationOpinions = riskEvaluationOpinions;
        this.evaluatorId = evaluatorId;
    }
}
