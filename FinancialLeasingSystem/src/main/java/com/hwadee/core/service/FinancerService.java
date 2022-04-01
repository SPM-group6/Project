package com.hwadee.core.service;

import com.hwadee.core.repository.RiskRepository;
import com.hwadee.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

public interface FinancerService {

    /**
     * 获取待审批的项目
     */
    List<Project> projectListByState(int state, int id);
    /**
     * 查看某个项目的具体信息
     */
    Project queryProjectById(int id);
    /**
     * 进行报价评估
     */
    void insertQuoteEvaluation(QuoteEvaluation quoteEvaluation);
    /**
     * 放款审批--添加资金流
     */
    void insertCashFlow(CashFlow cashFlow);
    /**
     * 改变项目状态
     */
    boolean updateStateAndStaff(int projectId,int stateId,int currentStaffId);
    /**
     * 查看项目风险评估
     */
    RiskEvaluation queryRiskEvaluationById(int id);
    /**
     * 查看项目报价评估
     */
    QuoteEvaluation queryQuoteEvaluationById(Integer id);
    /**
     * 进行放款审批
     */
    Boolean insertLoanApproval(LoanApproval loanApproval);
    /**
     * 根据项目ID查看项目变更申请书
     */
    ProjectAlterApplication queryProjectAlterApplicationById(int id);
    /**
     * 更新财务评估信息--变更申请
     */
    Boolean updateFinanceEvaluation(int id,int evaluatorId,String evaluation);

    /**
     * 查看指定项目的资产处置报告
     */
    List<AssetsDisposal> queryAssetsDisposalById(@Param("id") int id);

    /**
     * 对资产处置进行财务评估
     */
    Boolean updateAssetsDisposalFinanceEvaluation(int id,int evaluatorId,String evaluation);

}
