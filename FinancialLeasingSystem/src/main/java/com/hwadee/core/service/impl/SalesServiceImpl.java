package com.hwadee.core.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.hwadee.core.repository.*;
import com.hwadee.core.service.salesService;
import com.hwadee.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SalesServiceImpl implements salesService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private CashFlowRepository cashFlowRepository;
    @Autowired
    private ProjectAuditRepository projectAuditRepository;
    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private RiskRepository riskRepository;
    @Autowired
    private AssetsRepository assetsRepository ;
    @Autowired
    private LoanApprovalRepository loanApprovalRepository;
    @Autowired
    private ProjectAlterRepository projectAlterRepository;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public List<Project> queryAllProjects() {
        String prefix = "*project-u*"; // aproject-u1-p12-s2
        Set<String> keys = redisTemplate.keys(prefix);
        List<Project> projectList;
        if(keys.size() != 0){
            System.out.println("查询所有项目使用缓存成功");
            List<String> projectsRedis = redisTemplate.opsForValue().multiGet(keys);
            projectList = JSONObject.parseArray(projectsRedis.toString(), Project.class);
        }else{
            projectList = projectRepository.queryAllProjects();
            // 将所有项目放入到缓存中
            StringBuilder key = new StringBuilder();
            for(Project project : projectList){
                key.delete(0,key.length());
                key.append("project-u");
                key.append(project.getApplicantId());
                key.append("-p");
                key.append(project.getProjectId());
                key.append("-s");
                key.append(project.getStateId());
                redisTemplate.opsForValue().set(key.toString(), JSONUtil.toJsonStr(project),43200);
            }
        }
        return projectList;
    }

    @Override
    public List<Project> queryProjectsById(int id) {
        StringBuilder prefix = new StringBuilder("*-p");
        prefix.append(id);
        prefix.append("*");
        Set<String> keys = redisTemplate.keys(prefix.toString());
        List<Project> projectList;
        if(keys.size() ==1){
            List<String> projectsRedis = redisTemplate.opsForValue().multiGet(keys);
            projectList = JSONObject.parseArray(projectsRedis.toString(), Project.class);
        }else{
            projectList = projectRepository.queryProjectsById(id);
            // 将项目信息放到缓存中
            prefix.delete(0,prefix.length());
            prefix.append("project-u");
            prefix.append(projectList.get(0).getApplicantId());
            prefix.append("-p");
            prefix.append(projectList.get(0).getProjectId());
            prefix.append("-s");
            prefix.append(projectList.get(0).getStateId());
            redisTemplate.opsForValue().set(prefix.toString(), JSONUtil.toJsonStr(projectList.get(0)));
        }
        return projectList;
    }

    @Override
    public List<Project> queryProjectsByState(int state) {
        StringBuilder prefix = new StringBuilder("*s");
        prefix.append(state);
        Set<String> keys = redisTemplate.keys(prefix.toString());
        List<Project> projectList;
        if(keys.size() != 0){
            List<String> projectsRedis = redisTemplate.opsForValue().multiGet(keys);
            projectList = JSONObject.parseArray(projectsRedis.toString(), Project.class);
        }else{
            projectList = projectRepository.queryProjectsByState(state);
        }
        return projectList;
    }

    @Override
    public  Integer insertProApplication(TemProject temProject){
        StringBuilder key = new StringBuilder("project-u");
        key.append(temProject.getApplicantId());
        key.append("-p");
        int res = projectRepository.insertProApplication(temProject);
        Project newProject = projectRepository.queryProjectByUIdTime(temProject.getApplicantId(), temProject.getProjectTime());
        key.append(newProject.getProjectId());
        key.append("-s");
        key.append(newProject.getSalesId());
        redisTemplate.opsForValue().set(key.toString(), JSONUtil.toJsonStr(newProject));
        System.out.println("新建项目立项申请成功！");
//        System.out.println(temProject.getPro());
        return res;
    }

    @Override
    public Project queryProjectByUIdTime(int applicantId, String date){
        return projectRepository.queryProjectByUIdTime(applicantId,date);
    }

    @Override
    public boolean delProject(Integer id){
        return true;
    }

    @Override
    public List<Project> cashFlowNormal() {
        return cashFlowRepository.cashFlowNormal();
    }

    @Override
    public List<Project> cashFlowAbnormal() {
        return cashFlowRepository.cashFlowAbnormal();
    }

    @Override
    public List<Project> assetsDisposal() {
        return projectRepository.queryAssetsDisposal();
    }

    @Override
    public List<Project> disposalAudit() { return projectRepository.queryDisposalAudit(); }

    @Override
    public ProjectAudit queryProjectAuditById(Integer id) {
        return projectAuditRepository.queryProjectAuditById(id);
    }

    @Override
    public QuoteEvaluation queryQuoteEvaluationById(Integer id) {
        return quoteRepository.queryQuoteEvaluationById(id);
    }

    @Override
    public RiskEvaluation queryRiskEvaluationById(Integer id) {
        return riskRepository.queryRiskEvaluationById(id);
    }

    @Override
    public boolean executeById(Integer id) {
        return projectRepository.executeById(id);
    }

    @Override
    public void deleteProjectsById(int id) {
        StringBuilder prefix = new StringBuilder("*-p");
        prefix.append(id);
        prefix.append("*");
        Set<String> keys = redisTemplate.keys(prefix.toString());
        if(keys.size() != 0){
            for(String key: keys){
                redisTemplate.delete(key);
            }
        }
        projectRepository.deleteProjectsById(id);
    }

    @Override
    public List<AssetsCheck> queryAssetsCheckById(Integer id){
        return assetsRepository.queryAssetsCheckById(id);
    }

    @Override
    public List<AssetsDisposal> queryAssetsDisposal(){return assetsRepository.queryProcessedItems();};

    @Override
    public List<ProjectDisposal>queryProjectDisposal(){return assetsRepository.queryProjectDisposal();}

    @Override
    public List<LoanApproval> queryLoanApprovalById(Integer id) {
        return loanApprovalRepository.queryLoanApprovalById(id);
    }

    @Override
    public List<CashFlow> queryCashFlowById(Integer id) {
        return cashFlowRepository.queryCashFlowById(id);
    }

    @Override
    public ProjectAlterApplication queryProjectAlterApplicationById(Integer id) {
        return projectAlterRepository.queryProjectAlterApplicationById(id);
    }

    @Override
    public List<AssetsDisposal> queryAssetsDisposalById(Integer id) {
        return assetsRepository.queryAssetsDisposalById(id);
    }

    @Override
    public List<ProjectDisposal> queryProjectDisposalById(Integer id) {
        return assetsRepository.queryProjectDisposalById(id);
    }

    @Override
    public  Integer insertAssetsApplication(salesAssets application){
        return assetsRepository.insertAssetsApplication(application);
    }

    @Override
    public boolean setProjectState(int pId,int newState,int newStaff){
        return projectRepository.updateStateAndStaff(pId,newState,newStaff);
    }

    @Override
    public  Integer insertAlterApplication(ProjectAlterForSales application){
        return projectAlterRepository.insertAlterApplication(application);
    }

    @Override
    public Integer insertCheck(AssetsCheck assetsCheck) {
        return assetsRepository.insertCheck(assetsCheck);
    }

    @Override
    public String queryCertificateByPId(int pId){
        return projectRepository.queryCertificateByPId(pId);
    }

}
