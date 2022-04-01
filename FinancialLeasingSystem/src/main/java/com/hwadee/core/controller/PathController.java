package com.hwadee.core.controller;

import com.hwadee.core.service.UserService;
import com.hwadee.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PathController {
    @Autowired
    private UserService userService;

    //工作人员初始登录界面
    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    //工作人员后台登录
    @RequestMapping("/crewLogin")
    @ResponseBody
    public Integer crewLogin(@RequestBody Map<String,Object> map)
    {
        //解析数据
        int crewId=Integer.parseInt(String.valueOf(map.get("crewId")));
        String crewPassword=(String)map.get("crewPassword");
        //根据crewId查询password，1.如果crewId不在范围，报错；→查询表  2.判断password是否匹配   3.跳转至对应工作人员的界面
        if (crewId<0||crewId>5)
            return 0;//账号错误
        String password=userService.queryPassword(crewId);

        if (crewPassword.equals(password))
        {
            return 1;//密码正确
        }
        return 2;//密码错误

    }

    //用户登录初始界面
    @RequestMapping("/userLogin")
    public String userLogin()
    {
        return "userLogin";//  user/userIndex
    }

    //用户登录：用身份证作为账号
    @RequestMapping("/acceptUserLogin")
    @ResponseBody
    public Map<String,String> acceptCrewLogin(@RequestBody Map<String,Object> map)
    {
        //解析数据
        System.out.println("=========传值是否成功"+map);

        //构造响应体
        Map<String, String> resultmap = new HashMap<>();

        String idCard=(String)map.get("idCard") ;//身份证
        String userPassword=(String)map.get("userPassword");//输入的密码
        //根据idCard查询用户所有信息，pwd用于校验，id用于跳转
        User user=userService.queryUserByIdCard(idCard);

        if (user!=null)
        {
            String password=user.getPwd();//验证密码
            String userId= String.valueOf(user.getId());//获取用户id
            System.out.println(userId+"=======password====="+password);
            if (userPassword.equals(password)){
                resultmap.put("userId", userId);//传userId
                resultmap.put("message","1");//1代表用户密码正确;
                System.out.println("=======parameters====="+resultmap);
                return resultmap;
            }
            else {
                resultmap.put("userId", "0");//0:无用户
                resultmap.put("message","2");//2代表用户密码错误;
                return resultmap;
            }
        }
       else {
            resultmap.put("userId", "0");//0:无用户
            resultmap.put("message","0");//0代表用户账号错误;
            return resultmap;
        }


    }

    @GetMapping("/index")
    public String index(Model model){
        User user = new User();
        user.setName("zzy");
        user.setId(1);
        user.setPwd("123");
        model.addAttribute("user",user);
        return "index";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register/submit")
    //用户提交注册表单 没有向前端返回数据库更新结果
    public String registerSubmit(@RequestParam("name")String name,@RequestParam("idCard")String idCard, @RequestParam("pwd")String pwd,
                                 @RequestParam("pwd2")String pwd2,@RequestParam("career")String career, @RequestParam("salary")int salary,
                                 @RequestParam("assets")long assets, @RequestParam(value="recentBill",required = false) MultipartFile file,
                                 HttpServletRequest request){
        System.out.println("in PathController");
        System.out.println("***************************************");
        System.out.println(file == null);
        //获取原文件名
        String fname=file.getOriginalFilename();
        //获取文件后缀
        String subffix=fname.substring(fname.lastIndexOf(".")+1,fname.length());
        //校验格式
        if(subffix.equals("")||(!subffix.equals("pdf")))
        {
            System.out.println("in PathController--registerSubmit: 上传的文件格式不对");
        }
        //新的文件名以日期命名
        String fileName=System.currentTimeMillis()+"."+subffix;
        //获取项目根路径并转到static/
        //存到target目录下
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/file/bill/";
        //存到src目录下
//        String path=appConfig.getFilepath()+"/bill/";
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
            file.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //修改数据库
        String url="file/bill/"+fileName;
        User user=new User(name,pwd,career,salary,assets,url,idCard);
        if(userService.registerUser(user) != 1){
            System.out.println("in PathController--registerSubmit: 修改数据库失败");
            return "error";
        }else{
            return "redirect:/login";
        }
    }

    @RequestMapping("/submitBill")
    public String submitBill(@RequestParam(name="bill")MultipartFile bill){
        //处理图片上传
        byte[] bytes=new byte[0];
        System.out.println("***************************************");
        System.out.println(bill == null);
        try{
            bytes=bill.getBytes();
        }catch(Exception e){
            e.printStackTrace();
        }
//        //更新数据库
//        if(userService.updateBill(bytes) != 1){
//            System.out.println("in PathController--submitBill error: 数据库更新失败！");
//        }
        return "login";
    }
}
