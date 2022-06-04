package com.hwadee.core.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.hwadee.core.repository.*;
import com.hwadee.core.service.UserService;
import com.hwadee.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

//    @Override
//    //注册新用户--字节流
//    public Integer registerUser(RegisterUser registerUser, byte[] bytes) {
//        return userRepository.registerUser(registerUser, bytes);
//    }

    @Override
    //注册新用户
    public Integer registerUser(User user){
        // 将新注册的用户保存到数据库中
        StringBuilder key = new StringBuilder("userInfo");
        key.append(user.getId());
        redisTemplate.opsForValue().set(key.toString(), JSONUtil.toJsonStr(user));
        return userRepository.registerUser(user);
    }

    @Override
    //查询指定承租人的所有立项中项目(项目状态为1-4)
    public List<Project> queryApplyingProjects(int userId) {
        StringBuilder prefix = new StringBuilder("*-u");
        prefix.append(userId);
        prefix.append("*-s[1234]");
        // 获取所有的key
        Set<String> keys = redisTemplate.keys(prefix.toString());
        List<Project> aprojectList;
        if(keys.size()!=0){
            System.out.println("承租人的所有立项中项目缓存查询成功");
            List<String> aprojectListRedis = redisTemplate.opsForValue().multiGet(keys);
            aprojectList = JSONObject.parseArray(aprojectListRedis.toString(), Project.class);
        }else{
            aprojectList= projectRepository.queryApplyingProjects(userId);
            StringBuilder key = new StringBuilder();
            for(Project aproject: aprojectList){
                key.delete(0,key.length());
                key.append("project-u");
                key.append(userId);
                key.append("-p");
                key.append(aproject.getProjectId());
                key.append("-s");
                key.append(aproject.getStateId());
                redisTemplate.opsForValue().set(key.toString(), JSONUtil.toJsonStr(aproject),43200);
            }
        }
        return aprojectList;
    }

    @Override
    //查询该承租人的所有进行中项目(项目applicant_id为userId，项目状态为7-11)
    public List<Project> queryRunningProjects(int userId) {
        StringBuilder prefix = new StringBuilder("*-u");
        prefix.append(userId);
        prefix.append("*-s[789]");
        // 获取所有的key
        Set<String> keys = redisTemplate.keys(prefix.toString());
        prefix.delete(0,prefix.length());
        prefix.append("*-u");
        prefix.append(userId);
        prefix.append("*-s10");
        if(redisTemplate.keys(prefix.toString()).size() != 0){
            keys.addAll(redisTemplate.keys(prefix.toString()));
        }
        prefix.delete(0,prefix.length());
        prefix.append("*-u");
        prefix.append(userId);
        prefix.append("*-s11");
        if(redisTemplate.keys(prefix.toString()).size() != 0){
            keys.addAll(redisTemplate.keys(prefix.toString()));
        }
        List<Project> rprojectList;

        if(keys.size()!=0){
            System.out.println("承租人的所有进行中项目缓存查询成功");
            List<String> rprojectListRedis = redisTemplate.opsForValue().multiGet(keys);
            rprojectList = JSONObject.parseArray(rprojectListRedis.toString(), Project.class);
        }else{
            rprojectList= projectRepository.queryRunningProjects(userId);
            StringBuilder key = new StringBuilder();
            for(Project rproject: rprojectList){
                key.delete(0,key.length());
                key.append("project-u");
                key.append(userId);
                key.append("-p");
                key.append(rproject.getProjectId());
                key.append("-s");
                key.append(rproject.getStateId());
                redisTemplate.opsForValue().set(key.toString(), JSONUtil.toJsonStr(rproject),43200);
            }
        }
        return rprojectList;
    }

    @Override
    //查询指定承租人的所有已结束项目(项目状态为15)
    public List<Project> queryFinishedProjects(int userId) {
        StringBuilder prefix = new StringBuilder("*-u");
        prefix.append(userId);
        prefix.append("*-s15");
        // 获取所有的key
        Set<String> keys = redisTemplate.keys(prefix.toString());
        List<Project> fprojectList;
        if(keys.size()!=0){
            System.out.println("承租人的所有已结束项目缓存查询成功");
            List<String> fprojectListRedis = redisTemplate.opsForValue().multiGet(keys);
            fprojectList = JSONObject.parseArray(fprojectListRedis.toString(), Project.class);
        }else{
            fprojectList= projectRepository.queryFinishedProjects(userId);
            StringBuilder key = new StringBuilder();
            for(Project fproject: fprojectList){key.delete(0,key.length());
                key.append("project-u");
                key.append(userId);
                key.append("-p");
                key.append(fproject.getProjectId());
                key.append("-s");
                key.append(fproject.getStateId());
                redisTemplate.opsForValue().set(key.toString(), JSONUtil.toJsonStr(fproject),43200);
            }
        }
        return fprojectList;
    }

    @Override
    //根据id查询user
    public User queryUserById(int userId) {
//        String key = "userInfo"+userId;
        StringBuilder key = new StringBuilder("userInfo");
        key.append(userId);
        Boolean if_exists = redisTemplate.hasKey(key.toString());
        String userInfo = redisTemplate.opsForValue().get(key.toString());
        User user;
        if(if_exists && userInfo != null){
            System.out.println("用户使用缓存成功！");
            user = JSONObject.parseObject(userInfo, User.class);
        }else {
            user = userRepository.queryUserById(userId);
            redisTemplate.opsForValue().set(key.toString(), JSONUtil.toJsonStr(user));
        }
        return user;
    }

    @Override
    //修改用户密码
    public Integer updateUserPwd(int userId, String newPwd) {
        StringBuilder key = new StringBuilder("userInfo");
        key.append(userId);
        Boolean if_exists = redisTemplate.hasKey(key.toString());
        String userInfo = redisTemplate.opsForValue().get(key.toString());
        if(if_exists && userInfo != null){
            User user = JSONObject.parseObject(userInfo, User.class);
            user.setPwd(newPwd);
            redisTemplate.opsForValue().set(key.toString(), JSONUtil.toJsonStr(user));
            System.out.println("更新缓存中的用户密码信息成功");
        }
        return userRepository.updateUserPwd(userId, newPwd);
    }

    //修改用户个人信息-备份(无抵押物证件上传) 勿删！！！
//    public Integer updateUserInfo(TemUser temUser){return userRepository.updateUserInfo(temUser);};

    @Override
    //修改用户个人信息
    public Integer updateUserInfo(TemUser temUser) {
        int res = userRepository.updateUserInfo(temUser);
//        String key = "userInfo"+temUser.getId();
        StringBuilder key = new StringBuilder("userInfo");
        key.append(temUser.getId());
        redisTemplate.delete(key.toString());
        User user = userRepository.queryUserById(temUser.getId());
        redisTemplate.opsForValue().set(key.toString(), JSONUtil.toJsonStr(user));
        System.out.println("修改用户个人信息成功·，并且更新缓存中的用户信息");
        return res;
    }

    @Override
    //查询所有项目状态
    public List<ProjectState> queryAllProjectStates() {
        String key = "projectStates";
        Boolean if_exists = redisTemplate.hasKey(key);
        String states = redisTemplate.opsForValue().get(key);
        List<ProjectState> projectStateList;
        if(if_exists && states != null){
            System.out.println("所有项目状态缓存成功");
            projectStateList = JSONObject.parseArray(states,ProjectState.class);
        }else{
            projectStateList = projectRepository.queryAllProjectStates();
            redisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(projectStateList),43200);
        }
        return projectStateList;
    }

    @Override
    //查询所有业务员
    public List<Crew> queryAllSales() {
        String key = "allSales";
        Boolean if_exists = redisTemplate.hasKey(key);
        String sales = redisTemplate.opsForValue().get(key);
        List<Crew> crewList;
        if(if_exists && sales != null){
            crewList = JSONObject.parseArray(sales, Crew.class);
        }else{
            crewList = crewRepository.queryAllSales();
            redisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(crewList),43200);
        }
        return crewList;
    }

    @Override
    // 查询与指定用户合作过的业务员
    public List<Crew> querySalesByUserId(int userId) {
//        String key = "salesHave"+userId;
        StringBuilder key = new StringBuilder("salesHave");
        key.append(userId);
        Boolean if_exists = redisTemplate.hasKey(key.toString());
        String sales = redisTemplate.opsForValue().get(key.toString());
        List<Crew> crewList;
        if(if_exists && sales != null){
            System.out.println("查询用户合作过的业务员缓存成功");
            crewList = JSONObject.parseArray(sales, Crew.class);
        }else{
            crewList = crewRepository.querySalesByUserId(userId);
            redisTemplate.opsForValue().set(key.toString(), JSONUtil.toJsonStr(crewList));
        }
        return crewList;
    }

    @Override
    // 查询没有与指定用户合作过的业务员
    public List<Crew> queryOtherSalesByUserId(int userId) {
//        String key = "salesNotHave"+userId;
        StringBuilder key = new StringBuilder("salesNotHave");
        key.append(userId);
        Boolean if_exists = redisTemplate.hasKey(key.toString());
        String sales = redisTemplate.opsForValue().get(key.toString());
        List<Crew> crewList;
        if(if_exists && sales != null){
            System.out.println("查询用户其他业务员缓存成功");
            crewList = JSONObject.parseArray(sales, Crew.class);
        }else{
            crewList = crewRepository.queryOtherSalesByUserId(userId);
            redisTemplate.opsForValue().set(key.toString(), JSONUtil.toJsonStr(crewList));
        }
        return crewList;
    }

    @Override
    //查询指定项目的资金流
    public List<CashFlow> queryCashsByPId(int pId) {
        return cashFlowRepository.queryCashsByPId(pId);
    }

    @Override
    //根据项目id查询项目
    public List<Project> queryProjectById(int pId) {
        System.out.println("查询项目的基本信息");
        Set<String> keys = redisTemplate.keys("*"+pId);
//        System.out.println(keys);
        List<String> projectListRedis = redisTemplate.opsForValue().multiGet(keys);
        List<Project> projectList;
        if(projectListRedis.size() != 0){
            System.out.println("使用缓存查询项目数据成功");
            projectList = JSONObject.parseArray(projectListRedis.toString(), Project.class);
        }else{
            System.out.println("未使用缓存查询项目数据");
            projectList = projectRepository.queryProjectsById(pId);
        }
        return projectList;
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
        Set<String> keys = redisTemplate.keys("userInfo*");
        List<String> userListRedis = redisTemplate.opsForValue().multiGet(keys);
        List<User> userList;
        if(userListRedis.size() != 0){
            userList = JSONObject.parseArray(userListRedis.toString(), User.class);
        }else{
            userList = userRepository.queryUsers();
        }
        return userList;
    }

    @Override
    // 修改用户人脸数据
    public Integer updateUserFaceInfo(User user){
//        String key = "userInfo" + user.getId();
        StringBuilder key = new StringBuilder("userInfo");
        key.append(user.getId());
        redisTemplate.opsForValue().set(key.toString(), JSONUtil.toJsonStr(user));
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
