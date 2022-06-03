package com.hwadee.tools;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwadee.core.service.UserService;
import com.hwadee.core.service.salesService;
import com.hwadee.entity.Crew;
import com.hwadee.entity.ProjectState;
import com.hwadee.entity.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class RedisHandler implements InitializingBean {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private salesService SalesService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void afterPropertiesSet() throws Exception {
        // 0. 删除所有缓存
        Set<String> keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);

        // 1.初始化缓存
        // 查询承租人信息 以及 员工信息
        List<User> userList = userService.queryUsers();

        //查询项目状态描述
        userService.queryAllProjectStates();
        //查询所有的信誉等级

        //查询业务员姓名
        userService.queryAllSales();

        // 2.放入缓存
        String key;
        for(User user: userList){
            key = "userInfo"+user.getId();
            redisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(user));
        }

        System.out.println("项目启动，预加热用户信息缓存！");
    }
}