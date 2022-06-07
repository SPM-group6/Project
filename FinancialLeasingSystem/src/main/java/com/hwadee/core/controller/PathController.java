package com.hwadee.core.controller;

import com.alibaba.fastjson.JSONObject;
import com.hwadee.core.service.UserService;
import com.hwadee.entity.User;
import com.hwadee.tools.FaceUtils;
import com.hwadee.tools.FileUpLoad;
import com.hwadee.tools.ImageBase64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class PathController {
    @Autowired
    private UserService userService;


    /**
     * 工作人员初始登录界面
     * @return
     */
    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }


    /**
     * 工作人员后台登录
     * @param map
     * @return
     */
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


    /**
     * 用户登录初始界面
     * @return
     */
    @RequestMapping("/userLogin")
    public String userLogin()
    {
        return "userLogin";//  user/userIndex
    }

    /**
     * 用户注册-填写完身份信息后跳转到人脸录入界面
     * @return
     */
    @RequestMapping("/faceReg")
    public String faceReg(Model model, @RequestParam(name="uId")int uId)
    {
        model.addAttribute("uId", uId);
        System.out.println("faceReg: uId: "+uId);
        return "faceReg";
    }

    /**
     * 用户人脸登录测试界面
     * @return
     */
    @RequestMapping("/newUserLogin")
    public String newUserLogin()
    {
        return "newUserLogin";
    }

    /**
     * 用户登录：用身份证作为账号
     * @param map
     * @return
     */
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


    /**
     * 用户登录：人脸登录
     * @param images
     * @return
     * @author lsj
     */
    @RequestMapping("/faceLogin")
    @ResponseBody
    public Map<String,String> faceLogin(@RequestParam("data") String images){
        //构造响应体
        Map<String, String> resultmap = new HashMap<>();

        System.out.println("in faceLogin controller");
        try {
            // 获取所有承租人
            List<User> userList = userService.queryUsers();

            FaceUtils faceUtils1 = new FaceUtils();
            FaceUtils faceUtils2 = new FaceUtils();


//            image = image.split("base64,")[1];
            byte[] bytesImage = ImageBase64Utils.base64ToImage(images);
            String fileName = FileUpLoad.upload(bytesImage,"C:/Users/lsj/Desktop/faceTest/", new Date().getTime()+".png");
            System.out.println("人脸文件保存成功");
//            String fileName = FileUpLoad.upload(bytesImage,"static/temporary/", new Date().getTime()+".png");
            for(User user : userList){
                if(user.getFacePath().equals(null) || user.getFacePath().equals("")){

                   continue;
                }
                faceUtils1.setImageInfo(fileName);
                faceUtils2.setImageInfo(user.getFacePath());
                float score = faceUtils1.compareTo(faceUtils1.getFaceFeature(), faceUtils2.getFaceFeature());
                System.out.println("与"+user.getName()+"对比分数score："+score);
                if(score>=0.82){
                    File file = new File(fileName);
                    file.delete();
                    faceUtils1.unInit();
                    faceUtils2.unInit();

                    System.out.println("人脸登录成功！");
                    resultmap.put("userId", Integer.toString(user.getId()));//传userId
                    resultmap.put("message","3");//3代表承租人人脸登录成功;
                    return resultmap;
                }
            }
            File file = new File(fileName);
            file.delete();

            faceUtils1.unInit();
            faceUtils2.unInit();

            System.out.println("人脸登录失败");
            resultmap.put("userId", "0");//传userId
            resultmap.put("message","4");//4代表承租人人脸验证失败，数据库内没有找到匹配的照片;
            return resultmap;
        }catch (Exception e){
            e.printStackTrace();
            resultmap.put("userId", "0");//传userId
            resultmap.put("message","5");//5代表承租人人脸登录出现异常;
            return resultmap;
        }
    }

    //承租人注册
    @RequestMapping("/register")
    public String register(){
        return "register";
    }


    /**
     * 用户提交注册表单 没有向前端返回数据库更新结果
     * @param name
     * @return
     */
    @PostMapping("/register/submit")
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
        //以当前时间重命名文件
        StringBuilder prefix = new StringBuilder();
        prefix.append(new Date().getTime());
        prefix.append(".");
        prefix.append(subffix);
        String fileName=prefix.toString();
        //存储到服务器
        String path = FileUpLoad.uploadFile(file,"static/file/bill/", fileName);
        //修改数据库
        //拼接文件访问路径
        prefix.delete(0,prefix.length());
        prefix.append("http://175.178.147.20:8082/static/file/bill/");
        prefix.append(fileName);
        //存储到数据库
        User user=new User(name,pwd,career,salary,assets,prefix.toString(),idCard);
        if(userService.registerUser(user) != 1){
            System.out.println("in PathController--registerSubmit: 修改数据库失败");
            return "error";
        }else{
            //查询用户id
            user=userService.queryUserByIdCard(idCard);
            //跳转到登录用户页面
//            return "redirect:/userLogin";
            //跳转到人脸录入页面
            return "redirect:/faceReg?uId="+user.getId();
        }

        //新的文件名以日期命名
//        String fileName=System.currentTimeMillis()+"."+subffix;
        //获取项目根路径并转到static/
        //存到target目录下
//        String path = FileUpLoad.uploadFile(file,"static/file/bill", new Date().getTime()+"."+subffix);
//        //存到src目录下
////        String path=appConfig.getFilepath()+"/bill/";
//        //检查路径
//        File newfile=new File(path);
//        if(!newfile.exists())//文件夹不存在就创建
//        {
//            newfile.mkdirs();
//        }
//        //保存文件
//        //完整路径
//        path=path+fileName;
//        try {
//            file.transferTo(new File(path));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }


    /**
     * 人脸注册
     *
     * @return
     */
    @RequestMapping(value = "/faceRegister", method = RequestMethod.POST)
    @ResponseBody
    // @RequestParam("image") String data, @RequestParam(name="id") String id
    public Map<String, String> faceRegister(@RequestBody Map<String,Object> map){
        //解析数据
        int id=Integer.parseInt(String.valueOf(map.get("id")));
        String data= (String)map.get("image");
        // 构造响应体
        Map<String, String> resultmap = new HashMap<>();
        System.out.println("in faceRegister controller");
        // 将图片保存到文件夹中

        try{

            User user = userService.queryUserById(id);
            String faceUrl=null;
            String facePath=null;
            FaceUtils faceUtils=new FaceUtils();

            Date date=new Date();
            byte[] byteImages=ImageBase64Utils.base64ToImage(data);
//            String fileName=FileUpLoad.upload(byteImages,"static/temporary/",date.getTime()+".png");
            String fileName=FileUpLoad.upload(byteImages,"C:/Users/lsj/Desktop/faceTest/temporary/",date.getTime()+".png");
            faceUtils.setImageInfo(fileName);
            System.out.println("开始检测");
            if(faceUtils.isLive()){
//                facePath = FileUpLoad.upload(byteImages,"static/faceImages/",date.getTime()+".png");
                facePath = FileUpLoad.upload(byteImages,"C:/Users/lsj/Desktop/faceTest/faceImages/",date.getTime()+".png");
                faceUrl = "http://175.178.147.20:8082/"+facePath;
                user.setFacePath(facePath);
                user.setFaceUrl(faceUrl);

                userService.updateUserFaceInfo(user);

                File file  = new File(fileName);
                file.delete();
                faceUtils.unInit();

                resultmap.put("userId", Integer.toString(user.getId()));
                resultmap.put("message", "1"); // 1 人脸注册成功
                return resultmap;
            }

            System.out.println("检测不通过");
            File file = new File(fileName);
            file.delete();

            faceUtils.unInit();
            resultmap.put("userId", Integer.toString(user.getId()));
            resultmap.put("message", "2"); // 2 活体检测未通过 人脸注册失败
            return  resultmap;
        }catch (Exception e){
            e.printStackTrace();
            resultmap.put("userId", "0"); //不存在该用户
            resultmap.put("message", "3"); // 3 人脸注册失败
            return resultmap;
        }
    }


    /**
     * 人脸注销
     * @param userId
     * @return
     */
    @RequestMapping(value = "./faceDelete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> faceDelte(@RequestBody int userId){
        // 构造响应体
        Map<String, String> resultmap = new HashMap<>();

        try{
            User user = userService.queryUserById(userId);
            user.setFaceUrl(null);
            user.setFacePath(null);
            userService.updateUserFaceInfo(user);

            resultmap.put("message","1");//1 人脸注销成功
            return  resultmap;
        }catch (Exception e){
            resultmap.put("message","0");// 0 人脸注销失败
            return  resultmap;
        }
    }

//    @RequestMapping("/submitBill")
//    public String submitBill(@RequestParam(name="bill")MultipartFile bill){
//        //处理图片上传
//        byte[] bytes=new byte[0];
//        System.out.println("***************************************");
//        System.out.println(bill == null);
//        try{
//            bytes=bill.getBytes();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
////        //更新数据库
////        if(userService.updateBill(bytes) != 1){
////            System.out.println("in PathController--submitBill error: 数据库更新失败！");
////        }
//        return "login";
//    }
}
