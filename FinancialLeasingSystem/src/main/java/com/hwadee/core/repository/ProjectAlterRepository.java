package com.hwadee.core.repository;

import com.hwadee.entity.ProjectAlterApplication;
import com.hwadee.entity.ProjectAlterForSales;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectAlterRepository {
    /**
     * 查看已处理的项目变更申请书
     */
    List<ProjectAlterApplication> queryProcessedApplication();

    ProjectAlterApplication queryProcessedApplicationById(int id);

    void updateAlterApproval(int projectId,boolean ifPassed,String managerEvaluation,int managerId);

    /**
     * 根据项目ID查看项目变更申请书
     */
    ProjectAlterApplication queryProjectAlterApplicationById(@Param("id") int id);

    /**
     * 更新财务评估信息
     */
    Boolean updateFinanceEvaluation(@Param("id") int id,@Param("evaluatorId") int evaluatorId,@Param("evaluation") String evaluation);

    /**
     * 修改风险评估信息
     */
    Boolean updateRiskEvaluation(@Param("projectId") int projectId,@Param("riskEvaluatorId") int riskEvaluatorId,@Param("riskEvaluation") String riskEvaluation);

    /**
     * 新增项目变更申请书
     */
    Integer insertAlterApplication(@Param("application")ProjectAlterForSales application);
}