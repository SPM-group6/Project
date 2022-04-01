package com.hwadee.core.controller;

import com.hwadee.core.service.ManagerService;
import com.hwadee.core.service.impl.ManagerServiceImpl;
import com.hwadee.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hwadee.tools.Tools;
import java.sql.Timestamp;
import java.util.List;


@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping("/managerCenter")
    public String managerCenter(Model model){
        List<Project> projectList = managerService.queryProjectsByState(4);
        model.addAttribute("projectList",projectList);
        return "manager/managerCenter";
    }

    @RequestMapping("/managerProjectAudit")
    public String managerProjectAudit(Model model, @RequestParam Integer id){
        List<Project> project = managerService.queryProjectsById(id);
        Project projectGet = project.get(0);
        model.addAttribute("currentProject",projectGet);

        QuoteEvaluation quoteEvaluation = managerService.queryQuoteEvaluationItems(id);
        model.addAttribute("quoteEvaluation",quoteEvaluation);

        RiskEvaluation riskEvaluation = managerService.queryRiskEvaluationById(id);
        model.addAttribute("riskEvaluation",riskEvaluation);
        return "manager/managerProjectAudit";
    }

    @RequestMapping("/projectAuditSubmit")
    public String projectAuditSubmit(@RequestParam("projectId") Integer projectId,@RequestParam("auditOpinionBoolean") Boolean auditOpinionBoolean,@RequestParam("auditOpinions") String auditOpinions,@RequestParam("auditorId") Integer auditorId){
        managerService.insertProjectAudit(projectId,auditOpinionBoolean,auditOpinions,auditorId);
        if (auditOpinionBoolean){
            managerService.updateStateAndStaff(projectId,5,5);
        }else {
            managerService.updateStateAndStaff(projectId,1,1);
        }
        return "redirect:/managerCenter";
    }

    @RequestMapping("/managerAlterApplication")
    public String managerAlterApplication(Model model){
        List<Project> projectList = managerService.queryProjectsByState(11);
        model.addAttribute("alterProjectList",projectList);
        return "manager/managerAlterApplication";
    }

    @RequestMapping("/managerAlterApproval")
    public String managerAlterApproval(Model model, @RequestParam Integer id){
        ProjectAlterApplication projectAlterApplication = managerService.queryProcessedApplicationById(id);

        ProjectAlterForManager projectAlterForManager = Tools.transferAlterForManager(projectAlterApplication);
     
        model.addAttribute("currentProjectAlterApplication",projectAlterForManager);

        return "manager/managerAlterApproval";
    }

    @RequestMapping("/projectAlterApprovalSubmit")
    public String projectAlterApprovalSubmit(@RequestParam("projectId") Integer projectId,@RequestParam("auditOpinionBoolean") Boolean auditOpinionBoolean,@RequestParam("auditOpinions") String auditOpinions,@RequestParam("auditorId") Integer auditorId){
        managerService.updateAlterApproval(projectId,auditOpinionBoolean,auditOpinions,auditorId);
        managerService.updateStateAndStaff(projectId,7,1);
        List<Project> projectList = managerService.queryProjectsById(projectId);
        Project project = projectList.get(0);
        ProjectAlterApplication projectAlterApplication = managerService.queryProcessedApplicationById(projectId);
        Timestamp flowTime = projectAlterApplication.getRetakeEffortTime();
        String unit = projectAlterApplication.getLeaseScheduleUnitTime();
        long unitTime;
        switch (unit){
            case "日":
                unitTime = (long)1000*3600*24;
            case "月":
                unitTime = (long)1000*3600*24*30;
            case "年":
                unitTime = (long)1000*3600*24*365;
            default:
                unitTime = (long)1000*3600*24;
        }
        int time = projectAlterApplication.getLeaseScheduleDuration();
        int amount = projectAlterApplication.getLeaseScheduleUnitPrice();
        int payAccountId = project.getApplicantId();
        if (auditOpinionBoolean){
            managerService.deleteCashFlow(projectId);
            for (int i=0;i<time;i++){
                boolean success = managerService.insertCashFlow(new CashFlow(projectId,new Timestamp(flowTime.getTime()+i*unitTime),amount,payAccountId,0,false));
            }
        }
        return "redirect:/managerAlterApplication";
    }

    @RequestMapping("/managerAssetsDisposal")
    public String managerAssetsDisposal(Model model){
        List<Project> projectList = managerService.queryProjectsByState(13);
        model.addAttribute("assetsProjectList",projectList);
        return "manager/managerAssetsDisposal";
    }

    @RequestMapping("/managerAssetsDisposalDetail")
    public String managerAssetsDisposalDetail(Model model,@RequestParam Integer id){
        AssetsDisposal assetsDisposal = managerService.queryAssetsDisposalById(id).get(0);
        model.addAttribute("currentAssetsDisposal",assetsDisposal);
        return "manager/managerAssetsDisposalDetail";
    }

    @RequestMapping("/assetsDisposalSubmit")
    public String assetsDisposalSubmit(@RequestParam("projectId") Integer projectId,@RequestParam("auditOpinionBoolean") Boolean auditOpinionBoolean,@RequestParam("auditOpinions") String auditOpinions,@RequestParam("auditorId") Integer auditorId){
        if(auditOpinionBoolean){
            managerService.updateStateAndStaff(projectId,14,1);
        }else{
            managerService.updateStateAndStaff(projectId,7,1);
        }
        managerService.updateAssetsDisposal(projectId,auditOpinionBoolean,auditOpinions,auditorId);
        return "redirect:/managerAssetsDisposal";
    }

    @RequestMapping("/managerProjectsAlready")
    public String managerProjectsAlready(Model model){
        List<ProjectAudit> projectList1 = managerService.queryAuditedItems();
        model.addAttribute("ProjectsAlready1",projectList1);

        List<ProjectAlterApplication> projectList2 = managerService.queryProcessedApplication();
        model.addAttribute("ProjectsAlready2",projectList2);

        List<AssetsDisposal> projectList3 = managerService.queryProcessedItems();
        model.addAttribute("ProjectsAlready3",projectList3);
        return "manager/managerProjectsAlready";
    }


}
