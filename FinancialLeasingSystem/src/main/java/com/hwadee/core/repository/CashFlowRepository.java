package com.hwadee.core.repository;

import com.hwadee.entity.CashFlow;
import com.hwadee.entity.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CashFlowRepository {
    /**
     * 查询资金流全部正常的进行中项目
     */
    List<Project> cashFlowNormal();

    /**
     * 查询资金流有逾期的进行中项目
     */
    List<Project> cashFlowAbnormal();

    /**
     * 新增资金流记录
     */
    boolean insertCashFlow(CashFlow cashFlow);

    /**
     * 查询指定项目的全部资金流
     */
    List<CashFlow> queryCashsByPId(@Param("pId") int pId);

    /**
     * 缴租
     */
    Integer pay(@Param("userId")int userId, @Param("pId")int pId, @Param("time")Timestamp time,@Param("payTypeId")int payTypeId);

    /**
     * 根据ID查询现金流
     */
    List<CashFlow> queryCashFlowById(@Param("id") Integer id);


    void deleteCashFlow(@Param("id") Integer id);

    /**
     * 根据支付ID查现金流
     */
    List<CashFlow> queryCashFlowByPayAccountId(@Param("id") Integer id);
}
