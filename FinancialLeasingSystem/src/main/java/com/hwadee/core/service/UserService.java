package com.hwadee.core.service;

import com.hwadee.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.sql.Timestamp;

/**
 * @Author LiuHong
 * @CreateTime 2022/3/2 14:18
 * @Version 1.0.0
 */
public interface UserService {
//    Integer registerUser(RegisterUser registerUser,byte[] bytes);//注册新用户--字节流
    Integer registerUser(User user);//注册新用户--文件路径
    List<Project> queryRunningProjects(int userId);//查询该承租人的所有进行中项目(项目applicant_id为userId，项目状态为7-11)
    List<Project> queryApplyingProjects(int userId);//查询该承租人的所有立项中项目(项目applicant_id为userId，项目状态为1-4)
    List<Project> queryFinishedProjects(int userId);//查询该承租人的所有已结束项目(项目applicant_id为userId，项目状态为15)
    User queryUserById(int userId);//根据id查询user
    List<Project> queryProjectById(int pId);//根据id查询project
    Integer updateUserPwd(int userId,String newPwd);//修改用户密码
    Integer updateUserInfo(TemUser temUser);//修改用户个人信息
    List<ProjectState> queryAllProjectStates();//查询所有项目状态
    List<Crew> queryAllSales();//查询所有业务员
    List<Crew> querySalesByUserId(int userId);//查询与指定用户合作过的业务员
    List<Crew> queryOtherSalesByUserId(int userId);//查询其他业务员（没有与指定用户合作过的业务员）
    List<CashFlow> queryCashsByPId(int pId);//查询指定项目的资金流
    Integer pay(int userId,int pId,Timestamp time);//缴租
    String queryContractByPId(int pId);//查询指定项目的合同
    Integer uploadContract(SignContract signContract);//上传指定项目的合同
    List<User> queryUsers(); // 查询所有承租人
    Integer updateUserFaceInfo(User user); //更新承租人人脸信息

    /**
     * 查询职员密码
     */
    String queryPassword(int staffId);


    /**
     * 查询指定idCard的承租人的信息
     */
    User queryUserByIdCard(String idCard);
}