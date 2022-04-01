package com.hwadee.core.service.impl;

import com.hwadee.core.repository.*;
import com.hwadee.core.service.ManagerService;
import com.hwadee.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {


    @Autowired
    private CashFlowRepository cashFlowRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectAuditRepository projectAuditRepository;

    @Autowired
    private ProjectAlterRepository projectAlterRepository;

    @Autowired
    private AssetsRepository assetsRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private RiskRepository riskRepository;

    @Override
    public List<Project> queryProjectsByState(int state){
        return projectRepository.queryProjectsByState(state);
    }

    @Override
    public List<ProjectAudit> queryAuditedItems() {
        return projectAuditRepository.queryAuditedItems();
    }

    @Override
    public List<ProjectAlterApplication> queryProcessedApplication() {
        return projectAlterRepository.queryProcessedApplication();
    }

    @Override
    public List<AssetsDisposal> queryProcessedItems(){
        return assetsRepository.queryProcessedItems();
    }

    @Override
    public List<Project> queryProjectsById(int id) {
        return projectRepository.queryProjectsById(id);
    }

    @Override
    public QuoteEvaluation queryQuoteEvaluationItems(int id) {
        return quoteRepository.queryQuoteEvaluationById(id);
    }

    @Override
    public RiskEvaluation queryRiskEvaluationById(int id) {
        return riskRepository.queryRiskEvaluationById(id);
    }

    @Override
    public void insertProjectAudit(int projectId, boolean auditOpinionBoolean, String auditOpinions, int auditorId) {
        projectAuditRepository.insertProjectAudit(projectId,auditOpinionBoolean,auditOpinions,auditorId);
    }

    @Override
    public void updateStateAndStaff(int projectId, int stateId, int currentStaffId) {
        projectRepository.updateStateAndStaff(projectId,stateId,currentStaffId);
    }

    @Override
    public ProjectAlterApplication queryProcessedApplicationById(int id) {
        return projectAlterRepository.queryProcessedApplicationById(id);
    }

    @Override
    public void updateAlterApproval(int projectId, boolean ifPassed, String managerEvaluation, int managerId) {
        projectAlterRepository.updateAlterApproval(projectId,ifPassed,managerEvaluation,managerId);
    }

    @Override
    public boolean insertCashFlow(CashFlow cashFlow) {
        return cashFlowRepository.insertCashFlow(cashFlow);
    }

    @Override
    public void deleteCashFlow(int id) {
        cashFlowRepository.deleteCashFlow(id);
    }

    @Override
    public List<AssetsDisposal> queryAssetsDisposalById(int id) {
        return assetsRepository.queryAssetsDisposalById(id);
    }

    @Override
    public void updateAssetsDisposal(int id, boolean ifPassed, String managerEvaluation, int managerId) {
        assetsRepository.updateAssetsDisposal(id,ifPassed,managerEvaluation,managerId);
    }
}
