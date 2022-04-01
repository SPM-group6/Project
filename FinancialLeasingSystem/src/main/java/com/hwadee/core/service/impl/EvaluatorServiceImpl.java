package com.hwadee.core.service.impl;

import com.hwadee.core.repository.*;
import com.hwadee.core.service.EvaluatorService;
import com.hwadee.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluatorServiceImpl implements EvaluatorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RiskRepository riskRepository;

    @Autowired
    private ProjectAlterRepository projectAlterRepository;

    @Autowired
    private CashFlowRepository cashFlowRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private ProjectAuditRepository projectAuditRepository;

    @Autowired
    private LoanApprovalRepository loanApprovalRepository;

    @Autowired
    private AssetsRepository assetsRepository;

    //查询客户信息列表，用于客户信用评定
    @Override
    public List<User> queryUserList(){
        return userRepository.queryUsers();
    }

    //根据客户id查询客户信息，用于项目风险评定
    @Override
    public User queryUserById(int id){ return userRepository.queryUserById(id);}

    //查询某个状态的项目，用于展示立项风险评估项目列表
    @Override
    public List<Project> queryProjectsByState(int state){
        return projectRepository.queryProjectsByState(state);
    }

    //根据项目id查项目
    @Override
    public List<Project> queryProjectById(int id){
        return projectRepository.queryProjectsById(id);
    }

    //查询指定承租人的所有已结束项目
    @Override
    public List<Project> queryFinishedProjects(int userId){ return projectRepository.queryFinishedProjects(userId);}


    //修改用户信用等级和信用等级评估
    @Override
    public Boolean updateCreditById(int id,int creditGradeId,String creditEvaluation){
        return userRepository.updateCreditById(id,creditGradeId,creditEvaluation);
    }

    //更新风险评估表
    @Override
    public Boolean updateRiskEvaluationById(RiskEvaluation riskEvaluation){
        return riskRepository.updateRiskEvaluationById(riskEvaluation);
    }

    //根据项目ID查看项目变更申请书
    @Override
    public ProjectAlterApplication queryProjectAlterApplicationById(int id){
        return projectAlterRepository.queryProjectAlterApplicationById(id);
    }

    //查询指定人的全部资金流
    @Override
    public List<CashFlow> queryCashFlowByPayAccountId(int id){
        return cashFlowRepository.queryCashFlowByPayAccountId(id);
    }

    //查询指定项目的全部资金流
    @Override
    public List<CashFlow> queryCashFlowById(int pId){
        return cashFlowRepository.queryCashFlowById(pId);
    }

    //修改风险评估信息
    @Override
    public Boolean updateRiskEvaluation(int projectId,int riskEvaluatorId,String riskEvaluation){
        return projectAlterRepository.updateRiskEvaluation(projectId,riskEvaluatorId,riskEvaluation);
    }

    //插入风险评估信息
    @Override
    public Boolean insertEvaluationItem(RiskEvaluation riskEvaluation){
        return riskRepository.insertEvaluationItem(riskEvaluation);
    }

    //修改项目状态和分配员工
    @Override
    public boolean updateStateAndStaff(int projectId, int stateId, int currentStaffId){
        return projectRepository.updateStateAndStaff(projectId,stateId,currentStaffId);
    }

    //查询全部风险评估信息
    @Override
    public List<RiskEvaluation> queryRiskEvaluationItems(){
        return riskRepository.queryRiskEvaluationItems();
    }

    //查询所有用户信息
    @Override
    public List<User> queryUsers(){
        return userRepository.queryUsers();
    }

    @Override
    public List<Project> queryProjectsById(int id) {
        return projectRepository.queryProjectsById(id);
    }
    @Override
    public RiskEvaluation queryRiskEvaluationById(Integer id) {
        return riskRepository.queryRiskEvaluationById(id);
    }
    @Override
    public QuoteEvaluation queryQuoteEvaluationById(Integer id) {
        return quoteRepository.queryQuoteEvaluationById(id);
    }
    @Override
    public ProjectAudit queryProjectAuditById(Integer id) {
        return projectAuditRepository.queryProjectAuditById(id);
    }

    @Override
    public List<LoanApproval> queryLoanApprovalById(Integer id) {
        return loanApprovalRepository.queryLoanApprovalById(id);
    }

    @Override
    public List<AssetsCheck> queryAssetsCheckById(Integer id){
        return assetsRepository.queryAssetsCheckById(id);
    }
    @Override
    public List<AssetsDisposal> queryAssetsDisposalById(Integer id) {
        return assetsRepository.queryAssetsDisposalById(id);
    }



}
