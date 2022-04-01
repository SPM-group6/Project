package com.hwadee.core.service.impl;

import com.hwadee.core.repository.ProjectRepository;
import com.hwadee.core.repository.SignContractRepository;
import com.hwadee.core.service.LegalService;
import com.hwadee.entity.Project;
import com.hwadee.entity.SignContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegalServiceImpl implements LegalService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    SignContractRepository signContractRepository;

    @Override
    public List<Project> queryProjectsByState(int state) {
        return projectRepository.queryProjectsByState(state);
    }

    @Override
    public List<SignContract> queryContract() {
        return signContractRepository.queryContract();
    }

    @Override
    public Integer uploadContract(SignContract signContract) {
        return signContractRepository.uploadContract(signContract);
    }

    @Override
    public void updateStateAndStaff(int projectId, int stateId, int currentStaffId) {
        projectRepository.updateStateAndStaff(projectId,stateId,currentStaffId);
    }
}
