package com.hwadee.core.service;

import com.hwadee.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerService {

    List<Project> queryProjectsByState(int state);

    List<ProjectAudit> queryAuditedItems();

    List<ProjectAlterApplication> queryProcessedApplication();

    List<AssetsDisposal> queryProcessedItems();

    List<Project> queryProjectsById(int id);

    QuoteEvaluation queryQuoteEvaluationItems(int id);

    RiskEvaluation queryRiskEvaluationById(int id);

    void insertProjectAudit(int projectId,boolean auditOpinionBoolean,String auditOpinions,int auditorId);

    void updateStateAndStaff(int projectId, int stateId, int currentStaffId);

    ProjectAlterApplication queryProcessedApplicationById(int id);

    void updateAlterApproval(int projectId,boolean ifPassed,String managerEvaluation,int managerId);

    boolean insertCashFlow(CashFlow cashFlow);

    void deleteCashFlow(int id);

    List<AssetsDisposal> queryAssetsDisposalById(int id);

    void updateAssetsDisposal(int id,boolean ifPassed,String managerEvaluation,int managerId);
}
