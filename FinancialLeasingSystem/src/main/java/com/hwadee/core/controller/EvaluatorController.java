package com.hwadee.core.controller;
import com.hwadee.core.service.EvaluatorService;
import com.hwadee.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.StyledEditorKit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EvaluatorController {

    @Autowired
    public EvaluatorService evaluatorService;


    //立项风险评估界面:查询项目状态为2的项目列表——立项风险评估
    @RequestMapping("/evaluatorCenter")
    public String evaluatorCenter(Model model){

        List<Project> projectList=new ArrayList<>();
        List<String> userList=new ArrayList<>();
        Integer applicant_id=0;
        projectList= evaluatorService.queryProjectsByState(2);
        //根据每个项目承租人的id展示他的名字，通过user表
        for(Project p:projectList)
        {
            applicant_id=p.getApplicantId();//获取该项目承租人id
            User user=evaluatorService.queryUserById(applicant_id);
            userList.add(user.getName());
        }

        model.addAttribute("projectList",projectList);
        model.addAttribute("userList",userList);

        return "evaluator/evaluatorCenter";
    }

    //立项风险评估界面:具体项目的立项风险评估——承租人的信息、项目信息、承租人过往支付情况、评估模块
    @RequestMapping(value="/projectRiskEvaluate",method = RequestMethod.GET)
    public String toProjectRiskEvaluate(Model model, @RequestParam("id") int projectId){
        //根据projectId查询项目详细信息
        List<Project> projects=evaluatorService.queryProjectById(projectId);
        Project project=projects.get(0);//只有一个项目
        Integer applicant_id=project.getApplicantId();//获取该项目承租人id
        User user=evaluatorService.queryUserById(applicant_id);
        System.out.println("===承租人id："+applicant_id);

        //获取该承租人的资金流
        List<CashFlow> userCashFlow=evaluatorService.queryCashFlowByPayAccountId(applicant_id);
        System.out.println("===承租人资金流："+userCashFlow);

        model.addAttribute("user",user);
        model.addAttribute("project",project);
        model.addAttribute("userCashFlow",userCashFlow);

        return "evaluator/projectRiskEvaluate";
    }

    //立项风险评估界面：填写具体项目的风险评估等级和意见。评估成功后将项目指定给下一个工作人员
    @RequestMapping("/modifyProjectRiskEvaluation")
    @ResponseBody
    public Boolean modifyProjectRiskEvaluation(@RequestBody Map<String,Object> map) {
        //解析数据
        int projectId=Integer.parseInt(String.valueOf(map.get("projectId")));//获取项目id
        int evaluatorId=2;//风险评估员id
        int riskGradeId=Integer.parseInt((String)map.get("riskGradeId"));//获取风险等级
        String newEvaluation=(String)map.get("riskEvaluationOpinions");//获取风险评估意见

        //修改risk_evaluation表——其实是新增
        RiskEvaluation riskEvaluation=new RiskEvaluation(projectId,riskGradeId,newEvaluation,evaluatorId);
        Boolean flag=evaluatorService.insertEvaluationItem(riskEvaluation);

        //指定下一个成员
        Boolean flag2=evaluatorService.updateStateAndStaff(projectId,3,3);
        if(flag2)
        {
            System.out.println("==========指定成功");
        }

        return flag;
    }


    //风险变更评估界面:查询项目状态为9的项目列表
    @RequestMapping("/riskVariationEvaluation")
    public String toVariationEvaluation(Model model){
        //拼接project_info表与risk_evaluation表
        List<Project> projectList=new ArrayList<>();
        projectList= evaluatorService.queryProjectsByState(9);

        //需要根据项目id查询risk_evaluation表
        model.addAttribute("projectList",projectList);

        return "evaluator/riskVariationEvaluation";
    }

    //风险变更评估界面:根据项目变更申请书、立项申请书、资金流、个人信息综合展示，填写评估意见
    @RequestMapping(value="/riskVariationEvaluationDetail",method = RequestMethod.GET)
    public String toRiskVariationEvaluationDetail(Model model, @RequestParam("id") int projectId){
        //根据projectId查询立项申请书详细信息
        List<Project> projects=evaluatorService.queryProjectById(projectId);
        Project project1=projects.get(0);//只有一个项目:立项申请书
        Integer applicant_id=project1.getApplicantId();//获取该项目承租人id
        //获取该承租人的信用信息
        User user=evaluatorService.queryUserById(applicant_id);
        System.out.println("===承租人id："+user.getId()+"     ===项目id："+project1.getProjectId());
        //获取项目变更申请书
        ProjectAlterApplication project2=evaluatorService.queryProjectAlterApplicationById(projectId);
        System.out.println("===项目变更申请书："+project2);
        //获取该承租人的资金流
        List<CashFlow> userCashFlow=evaluatorService.queryCashFlowByPayAccountId(applicant_id);
        System.out.println("===承租人资金流："+userCashFlow);
        //获取该项目的资金流
        List<CashFlow> projectCashFlow=evaluatorService.queryCashFlowById(projectId);
        System.out.println("===项目资金流："+projectCashFlow);

        model.addAttribute("user",user);
        model.addAttribute("project1",project1);
        model.addAttribute("project2",project2);
        model.addAttribute("userCashFlow",userCashFlow);
        model.addAttribute("projectCashFlow",projectCashFlow);

        return "evaluator/riskVariationEvaluationDetail";
    }

    //风险变更评估界面：填写具体项目的风险评估等级和意见，修改：变更申请书表两个字段，风险评估表所有字段,指定给下一个工作人员
    @RequestMapping("/modifyProjectVariationRiskEvaluation")
    @ResponseBody
    public Boolean modifyProjectVariationRiskEvaluation(@RequestBody Map<String,Object> map) {
        //解析数据
        int projectId=Integer.parseInt(String.valueOf(map.get("projectId")));//获取项目id
        int evaluatorId=2;//风险评估员id
        int riskGradeId=Integer.parseInt((String)map.get("riskGradeId"));//获取风险等级
        String newEvaluation=(String)map.get("riskEvaluationOpinions");//获取风险评估意见

        //修改risk_evaluation表
        RiskEvaluation riskEvaluation=new RiskEvaluation(projectId,riskGradeId,newEvaluation,evaluatorId);
        Boolean flag1=evaluatorService.updateRiskEvaluationById(riskEvaluation);

        //修改变更申请书
        Boolean flag2=evaluatorService.updateRiskEvaluation(projectId,riskGradeId,newEvaluation);

        //指定给下一个成员
        Boolean flag3=evaluatorService.updateStateAndStaff(projectId,10,3);
        if(flag2)
        {
            System.out.println("==========指定成功");
        }

        return flag1&&flag2;
    }


    //修改风险评估意见
    @RequestMapping("/submitNewCredit")
    @ResponseBody
    public Boolean submitNewCredit(@RequestBody Map<String,Object> map) {
        //解析数据
        System.out.println("传值是否成功");
        int userId=Integer.parseInt(String.valueOf(map.get("userId")));
        Integer newCredit=Integer.parseInt((String)map.get("newCredit"));
        String newEvaluation=(String)map.get("newEvaluation");
        Boolean flag=evaluatorService.updateCreditById(userId,newCredit,newEvaluation);

        return flag;
    }

    //去客户信用评定界面，传客户列表
    @RequestMapping("/creditEvaluate")
    public String toCreditEvaluate(Model model){

        //查询所有user信息
        List<User> userList=new ArrayList<>();
        userList= evaluatorService.queryUserList();
        model.addAttribute("userList",userList);
        return "evaluator/creditEvaluate";
    }

    //去客户信用评定详情界面，传用户id，便于下一个界面查询用户基本信息和project
    @RequestMapping(value = "/creditEvaluateDetail",method = RequestMethod.GET)
    public String toCreditEvaluateDetail(Model model, @RequestParam("id") int userId){
        //用户基本信息
        User user=evaluatorService.queryUserById(userId);
        model.addAttribute("user",user);
        //用户已结束的项目列表
        List<Project> projects=evaluatorService.queryFinishedProjects(userId);
        model.addAttribute("projects",projects);

        return "evaluator/creditEvaluateDetail";
    }

    //查看项目方案
    @RequestMapping(value = "/projectScheme",method = RequestMethod.GET)
    public String projectAllContent(Model model,@RequestParam(name="id") Integer id) {
        List<Project> projects = evaluatorService.queryProjectsById(id);
        Project project=projects.get(0);
        model.addAttribute("project", project);

        int state=project.getStateId();
        RiskEvaluation risk=evaluatorService.queryRiskEvaluationById(id);
        model.addAttribute("risk", risk);

        QuoteEvaluation quote= evaluatorService.queryQuoteEvaluationById(id);
        model.addAttribute("quote", quote);

        ProjectAudit audit= evaluatorService.queryProjectAuditById(id);
        model.addAttribute("audit", audit);

        if(state>5){//合同

        }
        if(state>6){
            //放款审批
            List<LoanApproval> loan=evaluatorService.queryLoanApprovalById(id);
            model.addAttribute("loan",loan.get(0));
            //资金流
            List<CashFlow> cashFlows =evaluatorService.queryCashFlowById(id);
            model.addAttribute("cashFlows",cashFlows);
            //资产检查
            List<AssetsCheck> assetsChecks = evaluatorService.queryAssetsCheckById(id);
            model.addAttribute("assetsChecks", assetsChecks);
            //项目变更申请报告
            ProjectAlterApplication projectAlterApplication=evaluatorService. queryProjectAlterApplicationById(id);
            model.addAttribute("alter", projectAlterApplication);
        }
        if(state>11){
            //资产处置申请
            List<AssetsDisposal> assetsDisposals=evaluatorService.queryAssetsDisposalById(id);
            if(assetsDisposals!=null&&assetsDisposals.size()>0)
                model.addAttribute("assetsDisposal", assetsDisposals.get(0));
        }
        return "evaluator/projectScheme";
    }


    //查看项目资金流
    @RequestMapping(value = "/projectCashFlow",method = RequestMethod.GET)
    public String toProjectCashFlow(Model model, @RequestParam("id") int projectId){

        //获取该项目的资金流
        List<CashFlow> projectCashFlow=evaluatorService.queryCashFlowById(projectId);
        System.out.println("===项目资金流："+projectCashFlow);

        model.addAttribute("projectCashFlow",projectCashFlow);
        return "evaluator/projectCashFlow";
    }

    //所有已评估的项目
    @RequestMapping("/evaluatedProjects")
    public String toEvaluatedProjects(Model model){

        //查询所有user信息
        List<User>  userList= evaluatorService.queryUsers();
        model.addAttribute("userList",userList);
        //查询所有risk_evaluation
        List<RiskEvaluation> riskEvaluationList=evaluatorService.queryRiskEvaluationItems();
        model.addAttribute("riskEvaluationList",riskEvaluationList);

        return "evaluator/evaluatedProjects";
    }

}
