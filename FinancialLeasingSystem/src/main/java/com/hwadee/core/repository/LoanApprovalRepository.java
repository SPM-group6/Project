package com.hwadee.core.repository;

import com.hwadee.entity.LoanApproval;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApprovalRepository {
    /**
     * 新增放款审批
     */
    Boolean insertLoanApproval(LoanApproval loanApproval);

    /**
     * 根据id查询该项目放款审批
     */
    List<LoanApproval> queryLoanApprovalById(@Param("id") Integer id);//根据id查询该项目放款审批
}
