package com.hwadee.core.repository;

import com.hwadee.entity.Project;
import com.hwadee.entity.ProjectState;
import com.hwadee.entity.TemProject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository {
    /**
     * 查询所有项目
     */
    List<Project> queryAllProjects();

    /**
     * 根据state查询项目
     */
    List<Project> queryProjectsByState(@Param("state") int state);

    /**
     * 根据id查询项目
     * @return
     */
    List<Project> queryProjectsById(@Param("id") int id);

    /**
     * 根据state和stuffId查询项目
     */
    List<Project> queryProjectsByStateAndStaff(@Param("state") int state,@Param("currentStaffId") int staffId);

    /**
     * 查询指定承租人的所有进行中项目
     */
    List<Project> queryRunningProjects(@Param("userId") int userId);

    /**
     * 查询指定承租人的所有立项中项目
     */
    List<Project> queryApplyingProjects(@Param("userId") int userId);

    /**
     * 查询指定承租人的所有已结束项目
     */
    List<Project> queryFinishedProjects(@Param("userId") int userId);

    /**
     * 按照id删除项目
     */
    void deleteProjectsById(@Param("id") int id);

    /**
     * 查询所有项目状态
     */
    List<ProjectState> queryAllProjectStates();

    /**
     * 查询到达项目结束时间的进行中项目
     */
    List<Project> queryAssetsDisposal();

    /**
     * 查询已提交资产处置申请的项目
     */
    List<Project> queryDisposalAudit();

    /**
     * 处置资产
     */
    boolean executeById(@Param("id") Integer id);

    /**
     * 修改项目状态和分配员工
     */
    boolean updateStateAndStaff(@Param("projectId") int projectId, @Param("stateId") int stateId, @Param("currentStaffId") int currentStaffId);

    /**
     * 新增立项申请
     */
    Integer insertProApplication(@Param("project")TemProject project);

    /**
     * 查询指定项目的抵押物证明
     */
    public String queryCertificateByPId(@Param("pId") int pId);
}
