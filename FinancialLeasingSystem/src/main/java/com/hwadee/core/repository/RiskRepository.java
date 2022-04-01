package com.hwadee.core.repository;

import com.hwadee.entity.RiskEvaluation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiskRepository {
    /**
     * 根据项目ID查询风险评估信息
     */
    RiskEvaluation queryRiskEvaluationById(@Param("id") int id);

    /**
     * 更新风险评估表
     */
    Boolean updateRiskEvaluationById(RiskEvaluation riskEvaluation);

    /**
     * 查询全部风险评估信息
     */
    List<RiskEvaluation> queryRiskEvaluationItems();

    /**
     * 插入风险评估信息
     */
    Boolean insertEvaluationItem(RiskEvaluation riskEvaluation);
}
