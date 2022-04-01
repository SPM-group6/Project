package com.hwadee.core.service.impl;

import com.hwadee.core.repository.*;
import com.hwadee.core.service.salesService;
import com.hwadee.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SalesServiceImpl implements salesService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private CashFlowRepository cashFlowRepository;
    @Autowired
    private ProjectAuditRepository projectAuditRepository;
    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private RiskRepository riskRepository;
    @Autowired
    private AssetsRepository assetsRepository ;
    @Autowired
    private LoanApprovalRepository loanApprovalRepository;
    @Autowired
    private ProjectAlterRepository projectAlterRepository;

    @Override
    public List<Project> queryAllProjects() {
        return projectRepository.queryAllProjects();
    }

    @Override
    public List<Project> queryProjectsById(int id) {
        return projectRepository.queryProjectsById(id);
    }

    @Override
    public List<Project> queryProjectsByState(int state) {
        return projectRepository.queryProjectsByState(state);
    }

    @Override
    public boolean delProject(Integer id){return true;}

    @Override
    public List<Project> cashFlowNormal() {
        return cashFlowRepository.cashFlowNormal();
    }

    @Override
    public List<Project> cashFlowAbnormal() {
        return cashFlowRepository.cashFlowAbnormal();
    }

    @Override
    public List<Project> assetsDisposal() {
        return projectRepository.queryAssetsDisposal();
    }

    @Override
    public List<Project> disposalAudit() { return projectRepository.queryDisposalAudit(); }

    @Override
    public ProjectAudit queryProjectAuditById(Integer id) {
        return projectAuditRepository.queryProjectAuditById(id);
    }

    @Override
    public QuoteEvaluation queryQuoteEvaluationById(Integer id) {
        return quoteRepository.queryQuoteEvaluationById(id);
    }

    @Override
    public RiskEvaluation queryRiskEvaluationById(Integer id) {
        return riskRepository.queryRiskEvaluationById(id);
    }

    @Override
    public boolean executeById(Integer id) {
        return projectRepository.executeById(id);
    }

    @Override
    public void deleteProjectsById(int id) {
        projectRepository.deleteProjectsById(id);
    }

    @Override
    public List<AssetsCheck> queryAssetsCheckById(Integer id){
        return assetsRepository.queryAssetsCheckById(id);
    }

    @Override
    public List<AssetsDisposal> queryAssetsDisposal(){return assetsRepository.queryProcessedItems();};

    @Override
    public List<ProjectDisposal>queryProjectDisposal(){return assetsRepository.queryProjectDisposal();}

    @Override
    public List<LoanApproval> queryLoanApprovalById(Integer id) {
        return loanApprovalRepository.queryLoanApprovalById(id);
    }

    @Override
    public List<CashFlow> queryCashFlowById(Integer id) {
        return cashFlowRepository.queryCashFlowById(id);
    }

    @Override
    public ProjectAlterApplication queryProjectAlterApplicationById(Integer id) {
        return projectAlterRepository.queryProjectAlterApplicationById(id);
    }

    @Override
    public List<AssetsDisposal> queryAssetsDisposalById(Integer id) {
        return assetsRepository.queryAssetsDisposalById(id);
    }

    @Override
    public List<ProjectDisposal> queryProjectDisposalById(Integer id) {
        return assetsRepository.queryProjectDisposalById(id);
    }

    @Override
    public  Integer insertAssetsApplication(salesAssets application){
        return assetsRepository.insertAssetsApplication(application);
    }

    @Override
    public boolean setProjectState(int pId,int newState,int newStaff){
        return projectRepository.updateStateAndStaff(pId,newState,newStaff);
    }

    @Override
    public  Integer insertAlterApplication(ProjectAlterForSales application){
        return projectAlterRepository.insertAlterApplication(application);
    }

    @Override
    public Integer insertCheck(AssetsCheck assetsCheck) {
        return assetsRepository.insertCheck(assetsCheck);
    }

    @Override
    public  Integer insertProApplication(TemProject temProject){
        return projectRepository.insertProApplication(temProject);
    }

    @Override
    public String queryCertificateByPId(int pId){
        return projectRepository.queryCertificateByPId(pId);
    }

}
