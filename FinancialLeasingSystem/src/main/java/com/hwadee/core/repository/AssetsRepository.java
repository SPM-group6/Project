package com.hwadee.core.repository;

import com.hwadee.entity.AssetsCheck;
import com.hwadee.entity.AssetsDisposal;
import com.hwadee.entity.ProjectDisposal;
import com.hwadee.entity.salesAssets;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Repository
public interface AssetsRepository {
    /**
     * 查看已处理的资产处置报告
     */
    List<AssetsDisposal> queryProcessedItems();

    /**
     * 新增资产检查记录
     */
    Integer insertCheck(@Param("check") AssetsCheck assetsCheck);

    /**
     * 查看指定项目的资产检查情况
     */
    List<AssetsCheck> queryAssetsCheckById(@Param("id") Integer id);

    /**
     * 查看所有项目的资产处置决定
     */
    List<ProjectDisposal> queryProjectDisposal();

    /**
     * 查看指定项目的资产处置报告
     */
    List<AssetsDisposal> queryAssetsDisposalById(@Param("id") int id);

    /**
     * 根据id查询该项目资产处置决定（两表联合查询，新建的实体类）
     */
    List<ProjectDisposal> queryProjectDisposalById(Integer id);


    /**
     * 修改财务人员ID和财务部意见
     */
    Boolean updateFinanceOpinions(@Param("id") int id, @Param("financeEvaluatorId") int financeEvaluatorId, @Param("financeOpinions") String financeOpinions);

    void updateAssetsDisposal(@Param("id") int id,@Param("ifPassed") Boolean ifPassed,@Param("managerOpinions") String managerOpinions,@Param("managerId") int managerId);

    /**
     * 新增资产处置申请
      */
    Integer insertAssetsApplication(@Param("application") salesAssets application);
}
