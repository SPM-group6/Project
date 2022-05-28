package com.hwadee.core.service.impl;

import com.hwadee.core.repository.*;
import com.hwadee.core.service.UserService;
import com.hwadee.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * @Author LiuHong
 * @CreateTime 2022/3/2 14:18
 * @Version 1.0.0
 */
@Service
public class UserServiceImpl implements UserService, updateUserFaceInfo {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private CrewRepository crewRepository;
    @Autowired
    private CashFlowRepository cashFlowRepository;
    @Autowired
    private SignContractRepository signContractRepository;

//    @Override
//    //注册新用户--字节流
//    public Integer registerUser(RegisterUser registerUser, byte[] bytes) {
//        return userRepository.registerUser(registerUser, bytes);
//    }

    @Override
    //注册新用户
    public Integer registerUser(User user){
        return userRepository.registerUser(user);
    }

    @Override
    //查询指定承租人的所有立项中项目(项目状态为1-4)
    public List<Project> queryApplyingProjects(int userId) {
        return projectRepository.queryApplyingProjects(userId);
    }

    @Override
    //查询该承租人的所有进行中项目(项目applicant_id为userId，项目状态为7-11)
    public List<Project> queryRunningProjects(int userId) {
        return projectRepository.queryRunningProjects(userId);
    }

    @Override
    //查询指定承租人的所有已结束项目(项目状态为15)
    public List<Project> queryFinishedProjects(int userId) {
        return projectRepository.queryFinishedProjects(userId);
    }

    @Override
    //根据id查询user
    public User queryUserById(int userId) {
        return userRepository.queryUserById(userId);
    }

    @Override
    //修改用户密码
    public Integer updateUserPwd(int userId, String newPwd) {
        return userRepository.updateUserPwd(userId, newPwd);
    }

    //修改用户个人信息-备份(无抵押物证件上传) 勿删！！！
//    public Integer updateUserInfo(TemUser temUser){return userRepository.updateUserInfo(temUser);};

    @Override
    //修改用户个人信息
    public Integer updateUserInfo(TemUser temUser) {
        return userRepository.updateUserInfo(temUser);
    }

    @Override
    //查询所有项目状态
    public List<ProjectState> queryAllProjectStates() {
        return projectRepository.queryAllProjectStates();
    }

    @Override
    //查询所有业务员
    public List<Crew> queryAllSales() {
        return crewRepository.queryAllSales();
    }

    @Override
    // 查询与指定用户合作过的业务员
    public List<Crew> querySalesByUserId(int userId) {
        return crewRepository.querySalesByUserId(userId);
    }

    @Override
    // 查询与指定用户合作过的业务员
    public List<Crew> queryOtherSalesByUserId(int userId) {
        return crewRepository.queryOtherSalesByUserId(userId);
    }

    @Override
    //查询指定项目的资金流
    public List<CashFlow> queryCashsByPId(int pId) {
        return cashFlowRepository.queryCashsByPId(pId);
    }

    @Override
    //根据项目id查询项目
    public List<Project> queryProjectById(int pId) {
        return projectRepository.queryProjectsById(pId);
    }

    @Override
    //缴租
    public Integer pay(int userId, int pId, Timestamp time) {
        //将当前时间和预缴租时间转换成Calendar类型
        Calendar ctime = Calendar.getInstance();
        ctime.setTime(time);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Calendar cnow = Calendar.getInstance();
        cnow.setTime(now);
        System.out.println("time: " + time + "ctime: " + ctime + "\nnow: " + now + "cnow: " + cnow);
        System.out.println("ctime.date: " + ctime.get(Calendar.DATE) + "\ncnow.date: " + cnow.get(Calendar.DATE));
        System.out.println("ctime.year: " + ctime.get(Calendar.YEAR) + "\ncnow.year: " + cnow.get(Calendar.YEAR));
        //判断缴租类型
        int payTypeId = 0;
        if (ctime.get(Calendar.YEAR) == cnow.get(Calendar.YEAR)) {
            if (ctime.get(Calendar.DATE) == cnow.get(Calendar.DATE)) {
                payTypeId = 1;//正常缴租
            } else if (cnow.get(Calendar.DATE) > (ctime.get(Calendar.DATE))) {
                payTypeId = 3;//补缴
            } else if (cnow.get(Calendar.DATE) < (ctime.get(Calendar.DATE))) {
                payTypeId = 2;//提前缴租
            }
        } else {
            System.out.println("交租年份与预缴年份不同！");
        }
        return cashFlowRepository.pay(userId, pId, time, payTypeId);
    }

    @Override
    //查询指定项目的合同
    public String queryContractByPId(int pId) {
       return signContractRepository.queryContractByPId(pId);
    }

    @Override
    //上传指定项目的合同
    public  Integer uploadContract(SignContract signContract){return signContractRepository.uploadContract(signContract);}

    @Override
    // 查询所有承租人
    public List<User> queryUsers() {
        return userRepository.queryUsers();
    }

    @Override
    public Integer updateUserFaceInfo(User user){
        return userRepository.updateUserFaceInfo(user);
    }

    /**
     * 查询职员密码
     */
    @Override
    public String queryPassword(int staffId){
        return crewRepository.queryPassword(staffId);
    }


    /**
     * 查询指定idCard的承租人的信息
     */
    @Override
    public User queryUserByIdCard(String idCard){
        return userRepository.queryUserByIdCard(idCard);
    }
}
