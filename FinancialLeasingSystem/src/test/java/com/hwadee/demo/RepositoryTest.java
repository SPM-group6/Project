package com.hwadee.demo;

import com.hwadee.core.repository.*;
import com.hwadee.core.service.FinancerService;
import com.hwadee.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.Times;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {
    @Resource
    private UserRepository userRepository;
    @Resource
    private FinancerRepository financerRepository;
    @Resource
    private ProjectRepository projectRepository;
    @Resource
    private AssetsRepository assetsRepository;
    @Resource
    private ProjectAlterRepository projectAlterRepository;
    @Resource
    private ProjectAuditRepository projectAuditRepository;
    @Resource
    private RiskRepository riskRepository;
    @Resource
    private QuoteRepository quoteRepository;
    @Resource
    private CrewRepository crewRepository;
    @Resource
    private CashFlowRepository cashFlowRepository;
    @Resource
    private LoanApprovalRepository loanApprovalRepository;

    @Test
    public void testMapper() {
        // 查出指定用户
        System.out.println("查出指定用户");
        User user = userRepository.queryUserById(1);
        System.out.println(user);
        // 查出全部用户
        System.out.println("查出全部用户");
        List<User> list = userRepository.queryUsers();
        for (User user1 : list) {
            System.out.println(user1);
        }
    }

    @Test
    public void testProjectMapper() {
        System.out.println("******************************");
        System.out.println("******************************");
        System.out.println("******************************");
        List<Project> projects = projectRepository.queryProjectsByState(4);
        System.out.println(projects);
    }

    /*    @Test
        public  void Test1(){
            List<Project> list = financerRepository.projectListByState(3,3);
            for(Project project : list){
                System.out.println("******************************");
                System.out.println("******************************");
                System.out.println(project.getProjectName());
                System.out.println(project.getSupplier());
                System.out.println(project.getCurrentStaffId());
                System.out.println(project.getLeasedAsset());
            }
        }*/
    //针对int类型转化为Timestamp的测试
    @Test
    public void weTest1() {
        QuoteEvaluation quoteEvaluation = new QuoteEvaluation(0, "123", 12, 12, "月",
                "23", new Timestamp(122, 2, 6, 1, 1, 1, 1), 3);
        quoteRepository.insertQuoteEvaluation(quoteEvaluation);
        Timestamp projectTime = new Timestamp(122, 2, 6, 1, 1, 1, 1);
        System.out.println(projectTime);
        //新建一个Calendar对象
        Calendar c = Calendar.getInstance();
        //设置时间
        c.setTime(projectTime);
        c.add(Calendar.YEAR, 1);
        Timestamp t = new Timestamp(c.getTimeInMillis());
        System.out.println(t);
        System.out.println("********************************");
        System.out.println("********************************");

    }
    //测试需求--使用addQuoteEvaluation项数据库quote_evaluation中添加一项数据
    //pId,市场价格，市租价，市租单价，市租间期，市租总期数，放款时间2022/3/6/0：0：0，评估人
    // 0, "10000", 10000,  1000, "月","10", new Timestamp(122, 2, 6, 0, 0, 0, 0), 3
    @Test
    public void weTest2(){
        //新建一个java对应的timestamp
        Timestamp timestamp = new Timestamp(122, 2, 6, 0, 0, 0, 0);
        Date date = new Date(2022,3,6);
        //新建一个风险评估表对象
        QuoteEvaluation quoteEvaluation = new QuoteEvaluation( 0, "10000", 10000,  1000, "月","10", timestamp, 3);
        //数据库操作
        quoteRepository.insertQuoteEvaluation(quoteEvaluation);
        //存储目标：2022/3/6/0：0：0
        //Timestamp构造参数：（122，2，6，0，0，0，0）
        //数据库存储结果：2022-3-6 0：0：0
        //前端查询显示结果：2022-3-6 0：0：0
    }

    //对于calendar和timestamp转换的测试
    @Test
    public void weTest3(){
        System.out.println("********************************");
        System.out.println("********************************");
        Timestamp ts = new Timestamp(122,2,6,0,0,0,0);
        System.out.println("ts="+ts);
        Calendar c = Calendar.getInstance();
        c.setTime(ts);
        System.out.println("c="+c);
        Timestamp ts2 =new Timestamp(c.getTimeInMillis());
        System.out.println("ts2="+ts2);
    }
}
