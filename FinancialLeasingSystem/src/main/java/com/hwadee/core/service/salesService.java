package com.hwadee.core.service;

import com.hwadee.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface salesService {

    List<Project> queryAllProjects();//所有项目的：项目编号、项目名称、租赁物、供应商、承租人、项目状态

    List<Project> queryProjectsByState(int state);// 根据状态查询项目信息

    void deleteProjectsById(int id);//根据项目id删除立项申请

    boolean delProject(Integer id);

    List<Project> cashFlowNormal();//查询资金流全部正常的进行中项目

    List<Project> cashFlowAbnormal();//查询资金流有逾期的进行中项目

    List<Project> assetsDisposal();//查询到达项目结束时间的进行中项目

    List<Project> disposalAudit();//查询已经提交资产处置申请的项目（状态码12、13、14）

    List<AssetsDisposal> queryAssetsDisposal();//查询所有已经通过审核的资产处置报告

    List<ProjectDisposal>queryProjectDisposal();//查询所有项目资产处置决定

    boolean executeById(Integer id);//处置资产，项目归档；根据项目id将该项目状态码更改为结束（15）

    ////////立项阶段
    List<Project> queryProjectsById(int id);//根据项目id查询该项目所有信息（立项申请书）
    RiskEvaluation queryRiskEvaluationById(Integer id);//根据项目di查询项目风险评估
    QuoteEvaluation queryQuoteEvaluationById(Integer id);//根据项目id查询项目报价评估
    ProjectAudit queryProjectAuditById(Integer id);//根据项目id查询项目终审意见报告

    Integer insertProApplication(TemProject temProject);//新增立项申请

    ////////启动阶段
    List<LoanApproval> queryLoanApprovalById(Integer id);//根据id查询该项目放款审批
    //合同？

    ////////进行阶段
    List<AssetsCheck> queryAssetsCheckById(Integer id);//根据项目di查询项目资产检查记录
    List<CashFlow> queryCashFlowById(Integer id);//根据id查询该项目资金流
    ProjectAlterApplication queryProjectAlterApplicationById(Integer id);//根据id查询该项目变更报告

    Integer insertAlterApplication(ProjectAlterForSales application);//新增项目变更申请
    Integer insertCheck (AssetsCheck assetsCheck);//新增资产检查记录

    ////////清算阶段
    List<AssetsDisposal> queryAssetsDisposalById(Integer id);//根据id查询该项目资产处置申请
    List<ProjectDisposal> queryProjectDisposalById(Integer id);//根据id查询该项目资产处置决定（两表联合查询，新建的实体类）

    Integer insertAssetsApplication(salesAssets application);//新增资产处置申请
    boolean setProjectState(int pId,int newState,int newStaff);//修改项目状态

    String queryCertificateByPId(int pId);//查询指定项目的抵押物证明

    // 根据项目创建时间以及创建人进行查询
    Project queryProjectByUIdTime(int applicantId, String date);
}