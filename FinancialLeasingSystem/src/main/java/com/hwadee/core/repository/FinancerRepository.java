package com.hwadee.core.repository;

import com.hwadee.entity.LoanApproval;
import com.hwadee.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancerRepository {
    List<Project> projectListByState(@Param("state")int state, @Param("id") int id);

    Boolean myInsertLoanApproval(LoanApproval loanApproval);

    Boolean updateAssetsDisposalFinanceEvaluation(@Param("id") int id,@Param("evaluatorId") int evaluatorId,@Param("evaluation") String evaluation);
}
