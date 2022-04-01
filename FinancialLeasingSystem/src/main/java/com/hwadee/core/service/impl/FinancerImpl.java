package com.hwadee.core.service.impl;

import com.hwadee.core.repository.*;
import com.hwadee.core.service.FinancerService;
import com.hwadee.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class FinancerImpl implements FinancerService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private CashFlowRepository cashFlowRepository;

    @Autowired
    private RiskRepository riskRepository;

    @Autowired
    private FinancerRepository financerRepository;

    @Autowired
    private LoanApprovalRepository loanApprovalRepository;

    @Autowired
    private ProjectAlterRepository projectAlterRepository;

    @Autowired
    private AssetsRepository assetsRepository;

    @Override
    public List<Project> projectListByState(int state, int id) {
        return projectRepository.queryProjectsByStateAndStaff(state, id);
    }

    //根据项目编号查询项目信息
    @Override
    public Project queryProjectById(int id) {
        List<Project> project = projectRepository.queryProjectsById(id);
        return project.get(0);
    }

    @Override
    public void insertQuoteEvaluation(QuoteEvaluation quoteEvaluation) {
        quoteRepository.insertQuoteEvaluation(quoteEvaluation);
    }

    @Override
    public void insertCashFlow(CashFlow cashFlow) {
        cashFlowRepository.insertCashFlow(cashFlow);
    }

    @Override
    public boolean updateStateAndStaff(int projectId, int stateId, int currentStaffId) {
        return projectRepository.updateStateAndStaff(projectId,stateId,currentStaffId);
    }

    @Override
    public RiskEvaluation queryRiskEvaluationById(int id) {
        return riskRepository.queryRiskEvaluationById(id);
    }

    @Override
    public QuoteEvaluation queryQuoteEvaluationById(Integer id) {
        return quoteRepository.queryQuoteEvaluationById(id);
    }

    @Override
    public Boolean insertLoanApproval(LoanApproval loanApproval) {
        return loanApprovalRepository.insertLoanApproval(loanApproval);
        //return financerRepository.myInsertLoanApproval(loanApproval);
    }

    @Override
    public ProjectAlterApplication queryProjectAlterApplicationById(int id) {
        return projectAlterRepository.queryProjectAlterApplicationById(id);
    }

    @Override
    public Boolean updateFinanceEvaluation(int id, int evaluatorId, String evaluation) {
        return projectAlterRepository.updateFinanceEvaluation(id,evaluatorId,evaluation);
    }

    @Override
    public List<AssetsDisposal> queryAssetsDisposalById(int id) {
        return assetsRepository.queryAssetsDisposalById(id);
    }

    @Override
    public Boolean updateAssetsDisposalFinanceEvaluation(int id, int evaluatorId, String evaluation) {
        return assetsRepository.updateFinanceOpinions(id,evaluatorId,evaluation);
        //return financerRepository.updateAssetsDisposalFinanceEvaluation(id,evaluatorId,evaluation);
    }
}
