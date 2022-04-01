package com.hwadee.core.repository;

import com.hwadee.entity.QuoteEvaluation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository {
    /**
     * 查询全部报价评估
     */
    List<QuoteEvaluation> queryQuoteEvaluationItems();

    /**
     * 根据项目ID查看报价评估
     */
    QuoteEvaluation queryQuoteEvaluationById(@Param("id") Integer id);

    /**
     * 增加报价评估
     */
    void insertQuoteEvaluation(QuoteEvaluation quoteEvaluation);
}
