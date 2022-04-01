package com.hwadee.core.controller;

import com.hwadee.core.service.FinancerService;
import com.hwadee.entity.*;
import com.hwadee.tools.Tools;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class FinancerController {

    @Autowired
    private FinancerService financerService;

    //to fianacer center
    @RequestMapping("/financerCenter")
    public String toFinancerCenter(Model model){
        List<Project> quoteList = financerService.projectListByState(3,3);
        model.addAttribute("quoteList",quoteList);
        return "financer/quoteList";
    }

    @RequestMapping("/quoteList")
    public String toQuoteList(Model model){
        //查询数据库中状态为3并且authority_staff_id 为3的项目，封装到list中
        //根据状态查询project--projectListByState()
        List<Project> quoteList = financerService.projectListByState(3,3);
        model.addAttribute("quoteList",quoteList);
        return "financer/quoteList";
    }
    @RequestMapping("/loanList")
    public String toLoanList(Model model){
        List<Project> quoteList = financerService.projectListByState(6,3);
        model.addAttribute("quoteList",quoteList);
        return "financer/loanList";
    }

    @RequestMapping("/alterList")
    public String toAlterList(Model model){
        List<Project> quoteList = financerService.projectListByState(10,3);
        model.addAttribute("quoteList",quoteList);
        return "financer/alterList";
    }

    @RequestMapping("/assetsList")
    public String toAssetsList(Model model){
        List<Project> quoteList = financerService.projectListByState(12,3);
        model.addAttribute("quoteList",quoteList);
        return "financer/assetsList";
    }

    @RequestMapping(value="/quoteEvaluation", method=RequestMethod.GET)
    public String toQuoteEvaluation(Model model, @RequestParam("projectId") int projectId){
        //使用projectId查找project的信息（单表查询--projectRepository）
        Project project = financerService.queryProjectById(projectId);
        model.addAttribute("project",project);
        return "/financer/quoteEvaluation";
    }
    @RequestMapping(value="/loanEvaluation", method=RequestMethod.GET)
    public String toLoanEvaluation(Model model, @RequestParam("projectId") int projectId){
        //使用projectId查找project的信息（单表查询--projectRepository）
        Project project = financerService.queryProjectById(projectId);
        RiskEvaluation riskEvaluation = financerService.queryRiskEvaluationById(projectId);
        QuoteEvaluation quoteEvaluation = financerService.queryQuoteEvaluationById(projectId);
        Timestamp loanTime = quoteEvaluation.getLoanTime();
        model.addAttribute("project",project);
        model.addAttribute("riskEvaluation",riskEvaluation);
        model.addAttribute("quoteEvaluation",quoteEvaluation);
        model.addAttribute("loanTime",loanTime);
        return "/financer/loanEvaluation";
    }
    @RequestMapping(value="/alterEvaluation", method=RequestMethod.GET)
    public String toAlterEvaluation(Model model, @RequestParam("projectId") int projectId){
        //使用projectId查找project的信息（单表查询--projectRepository）
        ProjectAlterApplication projectAlterApplication = financerService.queryProjectAlterApplicationById(projectId);
        ProjectAlterForRisk projectAlterForRisk = Tools.transferProjectAlterForRisk(projectAlterApplication);

        Project project = financerService.queryProjectById(projectId);

        model.addAttribute("project",project);
        model.addAttribute("projectAlterApplication",projectAlterForRisk);
        return "/financer/alterEvaluation";
    }
    @RequestMapping(value="/assetsEvaluation", method=RequestMethod.GET)
    public String toAssetsEvaluation(Model model, @RequestParam("projectId") int projectId) {
        //使用projectId查找project的信息（单表查询--projectRepository）
        AssetsDisposal assetsDisposal = financerService.queryAssetsDisposalById(projectId).get(0);
        Project project = financerService.queryProjectById(projectId);

        model.addAttribute("project",project);
        model.addAttribute("assetsDisposal", assetsDisposal);
        return "/financer/assetsEvaluation";
    }
    @RequestMapping(value="/addAlterEvaluation",method = RequestMethod.GET)
    public String addAlterEvaluation(Model model,@RequestParam("projectId") int projectId,
                                    @RequestParam("financeEvaluation") String financeEvaluation){
        //projectAlterApplication 变更申请书更新
        financerService.updateFinanceEvaluation(projectId,3,financeEvaluation);

        //修改项目状态和当前操作员id
        financerService.updateStateAndStaff(projectId,11,4);

        List<Project> quoteList = financerService.projectListByState(10,3);
        model.addAttribute("quoteList",quoteList);

        return "financer/alterList";
    }

    @RequestMapping(value="/addAssetsEvaluation",method = RequestMethod.GET)
    public String addAssetsEvaluation(Model model,@RequestParam("projectId") int projectId,
                                     @RequestParam("financeOpinions") String financeEvaluation){
        //projectAlterApplication 变更申请书更新
        financerService.updateAssetsDisposalFinanceEvaluation(projectId,3,financeEvaluation);

        //修改项目状态和当前操作员id
        financerService.updateStateAndStaff(projectId,13,4);

        List<Project> quoteList = financerService.projectListByState(12,3);
        model.addAttribute("quoteList",quoteList);

        return "financer/assetsList";
    }

    @RequestMapping(value="/addQuoteEvaluation",method = RequestMethod.GET)
    public String addQuoteEvaluation(Model model,
            @RequestParam("leaseValue") String leaseValue,
                                     @RequestParam("leaseRentCost") int leaseRentCost, @RequestParam("leaseScheduleUnitPrice") int leaseScheduleUnitPrice, @RequestParam("leaseScheduleUnitTime") String newleaseScheduleUnitTime,
                                     @RequestParam("leaseScheduleDuration") String newleaseScheduleDuration, @RequestParam("loanYear")int loanYear, @RequestParam("loanMonth") int loanMonth, @RequestParam("loanDay") int loanDay, @RequestParam("pId") int projectId){
        //合成一个timestamp类
        Timestamp loanTime = new Timestamp(loanYear-1900,loanMonth-1,loanDay,0,0,0,0);

        //quoteEvaluation 报价评估新增
        QuoteEvaluation quoteEvaluation=new QuoteEvaluation(projectId,leaseValue,leaseRentCost,leaseScheduleUnitPrice,
                newleaseScheduleUnitTime,newleaseScheduleDuration,loanTime,3);
        financerService.insertQuoteEvaluation(quoteEvaluation);


        //修改项目状态和当前操作员id
        financerService.updateStateAndStaff(projectId,4,4);

        List<Project> quoteList = financerService.projectListByState(3,3);
        model.addAttribute("quoteList",quoteList);

        //返回修改成功信息
        model.addAttribute("msg","报价评估提交成功");
        return "financer/quoteList";
    }
    //放款审批
    @RequestMapping(value = "/addLoanEvaluation",method = RequestMethod.GET)
    public String addLoanEvaluation(Model model,@RequestParam("pId") int projectId,
                                    @RequestParam("auditOpinionBoolean") Boolean auditOpinionBoolean,
                                    @RequestParam("loanAuditOpinions") String auditOpinions,
                             @RequestParam("pTime") Timestamp projectTime,@RequestParam("pPrice") int price,
                             @RequestParam("pUTime") String leaseScheduleUnitTime,
                             @RequestParam("pDuration") int leaseScheduleDuration,
                             @RequestParam("pUnitPrice") int amount, @RequestParam("aId") int applicantId){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(projectTime);

        //合成一个 新建loanApproval数据项
        LoanApproval loanApproval = new LoanApproval(projectId,auditOpinionBoolean,auditOpinions,3);
        financerService.insertLoanApproval(loanApproval);

        //新建一个放款资金流
        financerService.insertCashFlow(new CashFlow(projectId,new Timestamp(Calendar.getInstance().getTimeInMillis()),price,applicantId,3,true));

        //生成一些资金流表项
        if(leaseScheduleUnitTime.equals("年")){
            for(int i = 1;i<=leaseScheduleDuration;i++){
                calendar.add(Calendar.YEAR,1);
                financerService.insertCashFlow(new CashFlow(projectId,new Timestamp(calendar.getTimeInMillis()),amount,applicantId,1,false));
            }
        }
        if(leaseScheduleUnitTime.equals("月")){
            for(int i = 1;i<=leaseScheduleDuration;i++){
                calendar.add(Calendar.MONTH,1);
                financerService.insertCashFlow(new CashFlow(projectId,new Timestamp(calendar.getTimeInMillis()),amount,applicantId,0,false));
            }
        }
        if(leaseScheduleUnitTime.equals("日")){
            for(int i = 1;i<=leaseScheduleDuration;i++){
                calendar.add(Calendar.DATE,1);
                financerService.insertCashFlow(new CashFlow(projectId,new Timestamp(calendar.getTimeInMillis()),amount,applicantId,0,false));
            }
        }
        //修改项目状态
        financerService.updateStateAndStaff(projectId,7,1);
        List<Project> quoteList = financerService.projectListByState(6,3);
        model.addAttribute("quoteList",quoteList);
        return "financer/loanList";
    }
}
