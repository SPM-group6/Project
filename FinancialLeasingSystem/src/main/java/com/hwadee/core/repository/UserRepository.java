package com.hwadee.core.repository;

import com.hwadee.entity.Project;
import com.hwadee.entity.RegisterUser;
import com.hwadee.entity.TemUser;
import com.hwadee.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    /**
     * 注册新用户
     */
//    Integer registerUser(@Param("newUser") RegisterUser registerUser,@Param("blob")byte[] bytes);//字节流
    Integer registerUser(@Param("newUser")User user);

    /**
     * 查询所有用户信息
     */
    List<User> queryUsers();

    /**
     * 查询指定ID的用户信息
     *
     * @param id
     */
    User queryUserById(@Param("id") int id);

    /**
     * 修改用户密码
     */
    Integer updateUserPwd(@Param("id") int id, @Param("newPwd") String newPwd);

    /**
     * 修改用户个人信息
     */
    Integer updateUserInfo(@Param("temUser") TemUser temUser);

    /**
     * 修改用户信用等级和信用等级评估
     */
    Boolean updateCreditById(@Param("id") int id, @Param("creditGradeId") int creditGradeId, @Param("creditEvaluation") String creditEvaluation);

    /**
     * 查询指定承租人的银行流水
     */
    String queryBill(@Param("id")int id);

    /**
     * 查询指定idCard的承租人的信息
     */
    User queryUserByIdCard(@Param("idCard")String idCard);

    /**
     * 修改用户人脸信息
     */
    Integer updateUserFaceInfo(@Param("user") User user);


}
