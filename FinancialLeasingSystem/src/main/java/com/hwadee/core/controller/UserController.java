package com.hwadee.core.controller;

import com.hwadee.core.service.UserService;
import com.hwadee.core.service.salesService;
import com.hwadee.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
/**
 * @Author LiuHong
 * @CreateTime 2022/3/2 14:17
 * @Version 1.0.0
 */
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private salesService SalesService;

    /**
     * 承租人中心--首页，即承租人项目总览
     * @param userId
     * @return
     */
    @RequestMapping("/userIndex")
    public String userIndex(Model model,@RequestParam(name="userId")Integer userId){
        //查询用户
        User user=userService.queryUserById(userId);
        model.addAttribute("user",user);
        //查询用户的立项中项目、进行中项目和已结束项目
        List<Project> aprojects=userService.queryApplyingProjects(userId);
        model.addAttribute("aprojects",aprojects);
        List<Project> rprojects=userService.queryRunningProjects(userId);
        model.addAttribute("rprojects",rprojects);
        List<Project> fprojects=userService.queryFinishedProjects(userId);
        model.addAttribute("fprojects",fprojects);
        //查询项目状态描述
        List<ProjectState> states=userService.queryAllProjectStates();
        model.addAttribute("states",states);
        //查询业务员姓名
        List<Crew> sales=userService.queryAllSales();
        model.addAttribute("sales",sales);
        return "user/allProjects";
    }

    /**
     * 承租人中心--联系业务员
     * @param userId
     * @return
     */
    @RequestMapping("/user/contactSales")
    public String contactSales(Model model,@RequestParam(name="userId")Integer userId){
        //查询用户
        User user=userService.queryUserById(userId);
        model.addAttribute("user",user);
        //查询合作过的业务员
        List<Crew> mSales=userService.querySalesByUserId(userId);
        model.addAttribute("mSales",mSales);
        //查询其他业务员
        List<Crew> oSales=userService.queryOtherSalesByUserId(userId);
        model.addAttribute("oSales",oSales);
        return "user/contactSales";
    }

    /**
     * 承租人中心--个人中心
     * @param userId
     * @return
     */
    @RequestMapping("/user/personalCenter")
    public String userPersonalCenter(Model model,@RequestParam(name="userId")Integer userId){
        //debug
        System.out.println("in userPersonalCenter");
        if(userId == null){
            System.out.println("in UserController--userPersonalCenter: error: 缺少参数！");
            return "error";
        }
        User user=userService.queryUserById(userId);
        model.addAttribute("user",user);
        //初始化参数
        model.addAttribute("cahnge","3");
        model.addAttribute("validForm","3");
        return "user/personalCenter";
    }

    /**
     * 修改个人信息
     * @param temUser
     * @param  model
     * @return
     */
    @RequestMapping("/user/changeUserInfo")
    public String changeUserInfo(TemUser temUser,Model model) {
        System.out.println("in UserController--changeUserInfo");
        System.out.println(temUser.getRecentBill() == null);
        //获取原文件名
        String fname=temUser.getRecentBill().getOriginalFilename();
        //获取文件后缀
        String subffix=fname.substring(fname.lastIndexOf(".")+1,fname.length());
        //校验格式--必须是pdf
        if(subffix.equals("")||(!subffix.equals("pdf")))
        {
            System.out.println("in PathController--registerSubmit: 上传的文件格式不对");
            model.addAttribute("validForm","0");
        }
        else{
            model.addAttribute("validForm","1");
        }
        //新的文件名以日期命名
        String fileName=System.currentTimeMillis()+"."+subffix;
        //获取项目根路径并转到static/
        //存到target目录下
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/file/contract/";
        //检查路径
        File newfile=new File(path);
        if(!newfile.exists())//文件夹不存在就创建
        {
            newfile.mkdirs();
        }
        //保存文件
        //完整路径
        path=path+fileName;
        try {
            temUser.getRecentBill().transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //修改数据库
        String url="file/bill/"+fileName;
        temUser.setUrl(url);
        if(userService.updateUserInfo(temUser) != 1){//返回1代表更新成功
            model.addAttribute("change","0");
        }
        else
           model.addAttribute("change","1");
        return "redirect:/user/personalCenter?userId="+temUser.getId();
    }

    /**
     * 修改密码页面
     * @param userId
     * @return
     */
    @RequestMapping("/user/changePwd")
    //修改密码页面
    public String userChangePwd(Model model,@RequestParam(name="userId")Integer userId){
        //debug
        System.out.println("in userChangePwd");
        if(userId == null){
            System.out.println("in UserController--userChangePwd: error: 缺少参数！");
            return "error";
        }
        User user=userService.queryUserById(userId);
        model.addAttribute("user",user);

        return "user/changePwd";
    }

    /**
     * 修改密码验证与提交
     * @param map
     * @return
     */
    @RequestMapping("/user/changePwd/submit")
    @ResponseBody
    public Map<String,String> changePwdSubmit(@RequestBody Map<String,Object> map){
        //解析数据
        int userId=Integer.parseInt(String.valueOf(map.get("userId")));
        String oldPwd= (String)map.get("oldPwd");
        String newPwd= (String)map.get("newPwd");
        //构造响应体
        Map<String, String> resultmap = new HashMap<>();
        //校验旧密码
        User user=userService.queryUserById(userId);
        System.out.println("in submit, rOldPwd: "+user.getPwd()+"\noldPwd: "+oldPwd);
        if(!user.getPwd().equals(oldPwd)){
            resultmap.put("validOldPwd", "0");
            resultmap.put("change","0");
        }
        else{
            resultmap.put("validOldPwd", "1");
            //修改数据库
            if(userService.updateUserPwd(userId,newPwd) != 1){
                //密码修改失败
                System.out.println("in UserController--changePwdSubmit: error 返回值非1");
                resultmap.put("change","0");
            }
            else{
                resultmap.put("change","1");
            }
        }
        return resultmap;
    }

    @RequestMapping("/user/projectDetail")
    //查看项目方案
    public String projectDetail(Model model,@RequestParam(name="userId")Integer userId,@RequestParam(name="id") Integer id) {
        //查询用户
        User user=userService.queryUserById(userId);
        model.addAttribute("user",user);
        //查询项目
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

        return "user/projectDetail";
    }

    @RequestMapping("/user/projectAllContent")
    //查看项目详情
    public String projectAllContent(Model model,@RequestParam(name="userId")Integer userId,@RequestParam(name="id") Integer id) {
        //查询用户
        User user=userService.queryUserById(userId);
        model.addAttribute("user",user);
        //查询项目
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
        return "user/projectAllContent";
    }


    @RequestMapping("/user/pay")
    //查看指定项目的缴租表
    public String userPay(Model model,@RequestParam(name="pId")Integer pId,@RequestParam(name="userId")Integer userId){
        //查询用户
        User user=userService.queryUserById(userId);
        model.addAttribute("user",user);
        //查询项目
        List<Project> project=userService.queryProjectById(pId);
        model.addAttribute("project", project.get(0));
        //查询资金流表
        List<CashFlow> cashFlows=userService.queryCashsByPId(pId);
        model.addAttribute("cashs",cashFlows);
        return "user/pay";
    }

    @RequestMapping("user/pay/submit")
    @ResponseBody
    //承租人缴租
    public Map<String, String> paySubmit(@RequestBody Map<String,Object> map) throws ParseException {
        //构造响应体
        Map<String, String> resultmap = new HashMap<>();
        //解析数据
        int userId=Integer.parseInt((String)(map.get("userId")));
        int pId=Integer.parseInt((String)(map.get("pId")));
        //将String转换成Timestamp数据格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp time=new Timestamp((sf.parse((String)(map.get("time")))).getTime());
        System.out.println("in UserController--paySubmit: map: "+map);
        System.out.println("userId: "+userId+"pId: "+pId+"time: "+time);
        //执行数据库更新
        if(userService.pay(userId,pId,time) != 1){
            resultmap.put("change","0");
        }
        else{
            resultmap.put("change","1");
        }
        return resultmap;
    }

    @RequestMapping("/user/uploadContract")
    //上传项目合同
    public String uploadContract(@RequestParam(name="pId")int pId,Model model){
        //添加初始值
        model.addAttribute("change","3");
        model.addAttribute("pId",pId);
        return "user/contract";
    }


    @PostMapping("/user/submitContract")
    //上传合同--表单提交
    public String submitContract(@RequestParam(name="legalStaffId") int lId,@RequestParam(name="contractFile")MultipartFile contract,
                                 @RequestParam(name="projectId") int pId, Model model){
        System.out.println("in UserController--uploadContract");
        System.out.println(contract == null);
        //构造返回体
        Map<String,String> resultmap=new HashMap<>();
        //获取原文件名
        String fname=contract.getOriginalFilename();
        //获取文件后缀
        String subffix=fname.substring(fname.lastIndexOf(".")+1,fname.length());
        //校验格式--必须是pdf
        if(subffix.equals("")||(!subffix.equals("pdf")))
        {
            System.out.println("in PathController--registerSubmit: 上传的文件格式不对");
            resultmap.put("validForm","0");
        }
        else{
            resultmap.put("validForm","1");
        }
        //新的文件名以日期命名
        String fileName=System.currentTimeMillis()+"."+subffix;
        //获取项目根路径并转到static/
        //存到target目录下
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/file/contract/";
        //检查路径
        File newfile=new File(path);
        if(!newfile.exists())//文件夹不存在就创建
        {
            newfile.mkdirs();
        }
        //保存文件
        //完整路径
        path=path+fileName;
        try {
            contract.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //修改数据库
        String url="file/contract/"+fileName;
        SignContract signContract=new SignContract(pId,url,lId);
        //执行数据库更新--返回1代表更新成功
        if(userService.uploadContract(signContract) != 1){
            model.addAttribute("change","0");
        }
        else
            model.addAttribute("change","1");
        return "redirect:/user/uploadContract?pId="+pId;
    }

    @RequestMapping("/user/viewContract")
    //查看项目合同
    public String viewContract(@RequestParam(name="pId")Integer pId,Model model)throws IOException{
        //查询指定项目的合同
        String url=userService.queryContractByPId(pId);
        model.addAttribute("contract",url);
        return "redirect:/"+url;
    }



}
