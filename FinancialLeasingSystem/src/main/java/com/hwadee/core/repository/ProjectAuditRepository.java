package com.hwadee.core.repository;

import com.hwadee.entity.ProjectAlterApplication;
import com.hwadee.entity.ProjectAudit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ProjectAuditRepository {
    /**
     * 查看已审核的项目方案
     */
    List<ProjectAudit> queryAuditedItems();

    /**
     * 根据项目Id查看审核意见
     */
    ProjectAudit queryProjectAuditById(@Param("id") Integer id);

    void insertProjectAudit(@Param("projectId") Integer projectId, @Param("auditOpinionBoolean") Boolean auditOpinionBoolean, @Param("auditOpinions") String auditOpinions, @Param("auditorId") Integer auditorId);
}
