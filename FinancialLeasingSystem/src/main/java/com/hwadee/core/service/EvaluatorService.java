package com.hwadee.core.service;

import com.hwadee.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluatorService {

    //查询客户信息列表，用于客户信用评定
    List<User> queryUserList();

    //根据客户id查询客户信息，用于项目风险评定
    User queryUserById(int id);

    //查询状态为2、9的项目，用于展示立项风险评估项目列表
    List<Project> queryProjectsByState(int state);

    //根据项目id查项目
    List<Project> queryProjectById(int id);

    //查询指定承租人的所有已结束项目，用户客户信用评定
    List<Project> queryFinishedProjects(int userId);

    //修改用户信用等级和信用等级评估
    Boolean updateCreditById(int id,int creditGradeId,String creditEvaluation);

    //更新风险评估表
    Boolean updateRiskEvaluationById(RiskEvaluation riskEvaluation);

    //根据项目ID查看项目变更申请书
    ProjectAlterApplication queryProjectAlterApplicationById(int id);

    //查询指定承租人的全部资金流
    List<CashFlow> queryCashFlowByPayAccountId(int id);

    //查询指定项目的全部资金流
    List<CashFlow> queryCashFlowById(int pId);

    //修改风险评估信息
    Boolean updateRiskEvaluation(int projectId,int riskEvaluatorId,String riskEvaluation);

    //插入风险评估信息
    Boolean insertEvaluationItem(RiskEvaluation riskEvaluation);

    List<Project> queryProjectsById(int id);//根据项目id查询该项目所有信息（立项申请书）
    RiskEvaluation queryRiskEvaluationById(Integer id);//根据项目di查询项目风险评估
    QuoteEvaluation queryQuoteEvaluationById(Integer id);//根据项目id查询项目报价评估
    ProjectAudit queryProjectAuditById(Integer id);//根据项目id查询项目终审意见报告
    List<LoanApproval> queryLoanApprovalById(Integer id);//根据id查询该项目放款审批
    List<AssetsCheck> queryAssetsCheckById(Integer id);//根据项目di查询项目资产检查记录
    List<AssetsDisposal> queryAssetsDisposalById(Integer id);//根据id查询该项目资产处置申请

    //修改项目状态和分配员工
    boolean updateStateAndStaff(int projectId, int stateId, int currentStaffId);

    //查询全部风险评估信息
    List<RiskEvaluation> queryRiskEvaluationItems();

    //查询所有用户信息
    List<User> queryUsers();

}
