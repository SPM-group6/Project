package com.hwadee.core.service.impl;

import com.hwadee.core.repository.*;
import com.hwadee.core.service.CommonService;
import com.hwadee.entity.Crew;
import com.hwadee.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {
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
    private CrewRepository crewRepository;

    @Override
    public Crew assignCrew(int authorityId)
    {
        int chosenCrewId=0;
        int minProjectNum=0;
        List<Crew> crewList=crewRepository.queryCrewByAuthorityId(authorityId);
        for(int i=0;i<crewList.size();i++){
            List<Project> projectList=projectRepository.queryProjectsByStaffId(crewList.get(i).getStaffId());
            if(minProjectNum==0) {
                minProjectNum = projectList.size();
                chosenCrewId=i;
            }
            else if(projectList.size()<minProjectNum){
                minProjectNum=projectList.size();
                chosenCrewId=i;
            }
        }
        return crewList.get(chosenCrewId);
    }

}
