//业务员controller
package com.hwadee.core.controller;

import com.hwadee.core.service.salesService;
import com.hwadee.entity.*;
import com.hwadee.tools.FileUpLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

//import com.hwadee.core.service.salesService;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller

public class SalesContorller {

    @Autowired
    private salesService SalesService;

    @RequestMapping("/test")
    public String test(Model model) {
        List<Project> projects = SalesService.queryProjectsByState(3);
        model.addAttribute("projects", projects);
        return "sales/plain_page";
    }

    /**
     * 业务员--所有项目概览
     * @param model
     * @return
     */
    @RequestMapping("/allProjects")
    public String allProjects(Model model) {
        List<Project> projects = SalesService.queryAllProjects();
        model.addAttribute("projects", projects);
        return "sales/allProjects";
    }

    /**
     * 业务员--查询立项申请中的项目
     * @param model
     * @return
     */
    @RequestMapping("/application")
    public String application(Model model) {
        List<Project> applications = SalesService.queryProjectsByState(1);
        model.addAttribute("applications", applications);
        return "sales/application";
    }

    /**
     * 业务员--查询风险评估中的项目
     * @param model
     * @return
     */
    @RequestMapping("/riskEvaluate")
    public String riskEvaluate(Model model) {
        List<Project> projects = SalesService.queryProjectsByState(2);
        model.addAttribute("projects", projects);
        return "sales/riskEvaluate";
    }

    /**
     * 业务员--查询报价评估中的项目
     * @param model
     * @return
     */
    @RequestMapping("/quoteEvaluate")
    public String quoteEvaluate(Model model) {
        List<Project> projects = SalesService.queryProjectsByState(3);
        model.addAttribute("projects", projects);
        return "sales/quoteEvaluate";
    }

    /**
     * 业务员--查询项目终审中的项目
     * @param model
     * @return
     */
    @RequestMapping("/finalAudit")
    public String finalAudit(Model model) {
        List<Project> projects = SalesService.queryProjectsByState(4);
        model.addAttribute("projects", projects);
        return "sales/finalAudit";
    }

    /**
     * 业务员--查询合同签约中的项目
     * @param model
     * @return
     */
    @RequestMapping("/contract")
    public String contract(Model model) {
        List<Project> projects = SalesService.queryProjectsByState(5);
        model.addAttribute("projects", projects);
        return "sales/contract";
    }

    /**
     * 业务员--查询放款审批中的项目
     * @param model
     * @return
     */
    @RequestMapping("/loan")
    public String loan(Model model) {
        List<Project> projects = SalesService.queryProjectsByState(6);
        model.addAttribute("projects", projects);
        return "sales/loan";
    }

    /**
     * 业务员--查询正常履约和逾期未交中的项目
     * @param model
     * @return
     */
    @RequestMapping("/assetsCheck")
    public String assetsCheck(Model model) {
        List<Project> projects7 = SalesService.queryProjectsByState(7);
        List<Project> projects8 = SalesService.queryProjectsByState(8);
        List<Project> projects=new ArrayList<>();
        projects.addAll(projects7);
        projects.addAll(projects8);
        model.addAttribute("projects", projects);
        return "sales/assetsCheck";
    }

    /**
     * 业务员--查询正常履约和预期未交中的项目
     * @param model
     * @return
     */
    @RequestMapping("/mCheck")
    public String mCheck(Model model) {
        List<Project> projects7 = SalesService.queryProjectsByState(7);
        List<Project> projects8 = SalesService.queryProjectsByState(8);
        model.addAttribute("projects7", projects7);
        model.addAttribute("projects8", projects8);
        return "sales/mCheck";
    }

    /**
     * 业务员--查询已结束的项目
     * @param model
     * @return
     */
    @RequestMapping("/alterApplication")
    public String alterApplication(Model model) {
        List<Project> projects7 = SalesService.queryProjectsByState(7);
        List<Project> projects8 = SalesService.queryProjectsByState(8);
        List<Project> projects=new ArrayList<>();
        projects.addAll(projects7);
        projects.addAll(projects8);
        model.addAttribute("projectsON", projects);
        List<Project> projects9 = SalesService.queryProjectsByState(9);
        List<Project> projects10 = SalesService.queryProjectsByState(10);
        List<Project> projectss=new ArrayList<>();
        projectss.addAll(projects9);
        projectss.addAll(projects10);
        model.addAttribute("projectsWAIT", projectss);
        return "sales/alterApplication";
    }

    @RequestMapping("/assetsApplication")
    public String assetsApplication(Model model) {
        List<Project> projectsON = SalesService.assetsDisposal();
        model.addAttribute("projectsON", projectsON);
        List<Project> projectsWAIT = SalesService.disposalAudit();
        model.addAttribute("projectsWAIT", projectsWAIT);
        return "sales/assetsApplication";
    }

    @RequestMapping("/execute")
    public String execute(Model model) {
        List<ProjectDisposal> pro_disposals = SalesService.queryProjectDisposal();
        model.addAttribute("disposals", pro_disposals);
        return "sales/execute";
    }

    /**
     * 业务员--查询已结束的项目
     * @param model
     * @return
     */
    @RequestMapping("/end")
    public String end(Model model) {
        List<Project> projects = SalesService.queryProjectsByState(15);
        model.addAttribute("projects", projects);
        return "sales/end";
    }

    @RequestMapping("/projectDetail")
    public String projectDetail(Model model,@RequestParam(name="id") Integer id) {
        List<Project> projects = SalesService.queryProjectsById(id);
        Project project=projects.get(0);
        model.addAttribute("project", project);
        int state=project.getStateId();

        if(state>2){
            RiskEvaluation risk=SalesService.queryRiskEvaluationById(id);
            model.addAttribute("risk", risk);
        }
        if(state>3){
            QuoteEvaluation quote= SalesService.queryQuoteEvaluationById(id);
            model.addAttribute("quote", quote);
        }
        if(state>4){
            ProjectAudit audit= SalesService.queryProjectAuditById(id);
            model.addAttribute("audit", audit);
        }

        return "sales/projectDetail";
    }

    @RequestMapping("/check")
    public String check(Model model,@RequestParam(name="id") Integer id) {
        List<Project> projects = SalesService.queryProjectsById(id);
        model.addAttribute("project", projects.get(0));
        List<AssetsCheck> assetsChecks = SalesService.queryAssetsCheckById(id);
        model.addAttribute("assetsChecks", assetsChecks);
        return "sales/check";
    }

    @RequestMapping("/checkSubmit")
    @ResponseBody
    public Map<String, String> checkSubmit(@RequestBody AssetsCheck assetsCheck){
        //构造响应体
        Map<String,String> resultmap=new HashMap<>();
        //修改数据库
        if (SalesService.insertCheck(assetsCheck) != 1){
            resultmap.put("change","0");
        }
        else{
            resultmap.put("change","1");
        }
        return resultmap;
    }


    @RequestMapping("/delProject")
    public String delProject(@RequestParam Integer id) {
        SalesService.deleteProjectsById(id);
        //检查是否正确删除，暂未
        return "redirect:/application";
    }

    @RequestMapping("/executeById")
    public String executeById(@RequestParam Integer id) {
        SalesService.executeById(id);
        //检查是否正确，暂未
        return "redirect:/execute";
    }

    @RequestMapping("/projectAllContent")
    public String projectAllContent(Model model,@RequestParam(name="id") Integer id) {
        List<Project> projects = SalesService.queryProjectsById(id);
        Project project=projects.get(0);
        model.addAttribute("project", project);

        int state=project.getStateId();
            RiskEvaluation risk=SalesService.queryRiskEvaluationById(id);
            model.addAttribute("risk", risk);

            QuoteEvaluation quote= SalesService.queryQuoteEvaluationById(id);
            model.addAttribute("quote", quote);

            ProjectAudit audit= SalesService.queryProjectAuditById(id);
            model.addAttribute("audit", audit);

        if(state>5){//合同

        }
        if(state>6){
            //放款审批
            List<LoanApproval> loan=SalesService.queryLoanApprovalById(id);
            model.addAttribute("loan",loan.get(0));
            //资金流
            List<CashFlow> cashFlows =SalesService.queryCashFlowById(id);
            model.addAttribute("cashFlows",cashFlows);
            //资产检查
            List<AssetsCheck> assetsChecks = SalesService.queryAssetsCheckById(id);
            model.addAttribute("assetsChecks", assetsChecks);
            //项目变更申请报告
            ProjectAlterApplication projectAlterApplication=SalesService. queryProjectAlterApplicationById(id);
            model.addAttribute("alter", projectAlterApplication);
        }
        if(state>11){
            //资产处置申请
            List<AssetsDisposal> assetsDisposals=SalesService.queryAssetsDisposalById(id);
            if(assetsDisposals!=null&&assetsDisposals.size()>0)
                model.addAttribute("assetsDisposal", assetsDisposals.get(0));
        }
        return "sales/projectAllContent";
    }

    @RequestMapping("/assetsApply")
    @ResponseBody
    public Map<String,String> assetsApplicationSubmit(@RequestBody salesAssets application){
        //构造响应体
        Map<String,String> resultmap=new HashMap<>();
        //修改数据库
        if (SalesService.insertAssetsApplication(application) != 1){
            resultmap.put("change","0");
        }
        else{
            resultmap.put("change","1");
            //修改项目状态和当前职工id
            if(SalesService.setProjectState(application.getProjectId(),12,3)){
                resultmap.put("changeState","1");
            }
            else
                resultmap.put("changeState","0");
        }
        return resultmap;
    }

    @RequestMapping("/alterApply")
    @ResponseBody
    public Map<String,String> alterApplicationSubmit(@RequestBody ProjectAlterForSales application){
        //构造响应体
        Map<String,String> resultmap=new HashMap<>();
        //修改数据库
        if (SalesService.insertAlterApplication(application) != 1){
            resultmap.put("change","0");
        }
        else{
            resultmap.put("change","1");
            //修改项目状态和当前职工id
            if(SalesService.setProjectState(application.getProjectId(),9,2)){
                resultmap.put("changeState","1");
            }
            else
                resultmap.put("changeState","0");

        }
        return resultmap;
    }

//    @RequestMapping("/proApplication")
//    @ResponseBody
//    //新建立项申请--字节流
//    public Map<String,String> proApplicationSubmit(@RequestBody TemProject temProject){
//        //构造响应体
//        Map<String, String> resultmap = new HashMap<>();
//        //处理文件上传
//        byte[] bytes=new byte[0];
//        try{
//            bytes=(temProject.getCertificate()).getBytes();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("in SalesController--proApplicationSubmit");
//        System.out.println(temProject.getCertificate() == null);
//        System.out.println(bytes == null);
//        //执行数据库更新
//        if(SalesService.insertProApplication(temProject,bytes) != 1){//返回1代表更新成功
//            resultmap.put("change","0");
//        }
//        else
//            resultmap.put("change","1");
//        return resultmap;
//    }

    @PostMapping("/applicationSubmit")
    //新建立项申请--表单提交
    public String applicationSubmit(TemProject temProject) throws ParseException {
        System.out.println("string time: "+temProject.getProjectTime());
//        Map<String,Object>
        //将String转换成Timestamp数据格式
        String[] temStr=temProject.getProjectTime().split("T");
        String temTime= temStr[0]+" "+temStr[1]+":00";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp time=new Timestamp(sf.parse(temTime).getTime());
        temProject.setTime(time);
        System.out.println("string time: "+temProject.getProjectTime()+" timestamp time: "+time);

//        //映射为实体类
//        TemProject temProject=new TemProject(Integer.parseInt(String.valueOf(map.get("applicantId"))),(String)map.get("projectName"),time,(String)map.get("leasedAsset"),
//                (String)map.get("supplier"),Integer.parseInt(String.valueOf(map.get("price"))),Integer.parseInt(String.valueOf(map.get("leaseScheduleUnitPrice"))),
//                (String)map.get("leaseScheduleUnitTime"),Integer.parseInt(String.valueOf(map.get("leaseScheduleDuration"))),(String)map.get("pawnName"),
//                (String)map.get("pawnType"),Integer.parseInt(String.valueOf(map.get("pawnValue"))),)
        //处理抵押物证明
        //获取原文件名
        String fname=temProject.getCertificate().getOriginalFilename();
        System.out.println("获取原文件名");
        //获取文件后缀
        String subffix=fname.substring(fname.lastIndexOf(".")+1,fname.length());
        //校验格式--必须是pdf
        if(subffix.equals("")||(!subffix.equals("pdf")))
        {
            System.out.println("in SalesController--applicationSubmit: 上传的文件格式不对");
        }
        //以当前时间重命名文件
        StringBuilder prefix = new StringBuilder();
        prefix.append(new Date().getTime());
        prefix.append(".");
        prefix.append(subffix);
        String fileName=prefix.toString();
        //存储到服务器
        String path = FileUpLoad.uploadFile(temProject.getCertificate(),"static/file/certificate/", fileName);
        //修改数据库
        //拼接文件访问路径
        prefix.delete(0,prefix.length());
        prefix.append("http://175.178.147.20:8082/static/file/certificate/");
        prefix.append(fileName);
        //修改数据库
        temProject.setUrl(prefix.toString());
        if(SalesService.insertProApplication(temProject) != 1) {
            System.out.println("in SalesController--applicationSubmit: 数据库修改失败");
        }
//        修改项目状态和当前员工id
//        SalesService.setProjectState()
        return "redirect:/application";
    }

    @RequestMapping("/viewPawnProof")
    //查看项目合同
    public String viewContract(@RequestParam(name="pId")Integer pId,Model model)throws IOException{
        //查询指定项目的合同
        String url=SalesService.queryCertificateByPId(pId);
        model.addAttribute("certificate",url);
        return "redirect:/"+url;
    }



}
