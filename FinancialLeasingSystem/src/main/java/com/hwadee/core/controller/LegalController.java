package com.hwadee.core.controller;

import com.hwadee.core.service.LegalService;
import com.hwadee.entity.Project;
import com.hwadee.entity.SignContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LegalController {

    @Autowired
    private LegalService legalService;

    @RequestMapping("/legalSignContract")
    public String legalSignContract(Model model){
        List<Project> projectList = legalService.queryProjectsByState(5);
        model.addAttribute("projectList",projectList);

        return "legal/legalSignContract";
    }

    @RequestMapping("/legalSignContractDetail")
    public String legalSignContractDetail(Model model, @RequestParam("id") Integer id){
        model.addAttribute("idInfo",id);
        return "legal/legalSignContractDetail";
    }

    @RequestMapping("/submitLegalContract")
    public String submitLegalContract(@RequestParam(name="legalStaffId") int lId,@RequestParam(name="contractFile") MultipartFile contract,
                                      @RequestParam(name="projectId") int pId, Model model){

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
        if(legalService.uploadContract(signContract) != 1){
            model.addAttribute("change","0");
        }
        else
            model.addAttribute("change","1");
        legalService.updateStateAndStaff(pId,6,3);
        return "redirect:/signContractAlready";
    }


    @RequestMapping("/signContractAlready")
    public String signContractAlready(Model model){
        List<SignContract> projectList = legalService.queryContract();
        model.addAttribute("projectList",projectList);
        return "legal/signContractAlready";
    }
}
