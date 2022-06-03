package com.hwadee.tools;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.hwadee.core.service.UserService;
import com.hwadee.core.service.salesService;
import com.hwadee.entity.Crew;
import com.hwadee.entity.Project;
import com.hwadee.entity.ProjectState;
import com.hwadee.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Set;

@Configuration
@EnableScheduling // 开启定时任务
public class StaticScheduleTask {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private salesService SalesService;

//    @Scheduled(cron = "0 0 0 */1 * ?")
//    @Scheduled(cron = "0/10 * * * * *")
    @Scheduled(cron=  "0 48 1 * * ?")
    private void redisUpdate(){
        try {
            // 在每天晚上2点更新缓存

            // 删除所有缓存
            Set<String> keys = redisTemplate.keys("*");
            redisTemplate.delete(keys);

            SalesService.queryAllProjects(); // 将所有项目放到缓存中
            List<User> userList = userService.queryUsers();
            List<Crew> salesHave;
            List<Crew> salesNotHave;
            StringBuilder key = new StringBuilder();

            System.out.println("删除所有缓存成功");
            for(User user : userList){
                // 查询用户 并设置缓存
                key.delete(0,key.length());
                key.append("userInfo");
                key.append(user.getId());
                redisTemplate.opsForValue().set(key.toString(), JSONUtil.toJsonStr(user),43200);

                // 与该用户联系过的业务员
                salesHave = userService.querySalesByUserId(user.getId());
                key.delete(0,key.length());
                key.append("salesHave");
                key.append(user.getId());
                redisTemplate.opsForValue().set(key.toString(), JSONUtil.toJsonStr(salesHave),43200);

                // 其他业务员
                salesNotHave = userService.queryOtherSalesByUserId(user.getId());
                key.delete(0,key.length());
                key.append("salesNotHave");
                key.append(user.getId());

                redisTemplate.opsForValue().set(key.toString(), JSONUtil.toJsonStr(salesNotHave),43200);
            }

            //查询项目状态描述
            userService.queryAllProjectStates();
            //查询业务员姓名
            userService.queryAllSales();
            // 查询所有信信用等级


            System.out.println("缓存更新成功！");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
