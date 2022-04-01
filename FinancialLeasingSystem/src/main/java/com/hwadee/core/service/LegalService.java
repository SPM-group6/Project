package com.hwadee.core.service;
import com.hwadee.entity.Project;
import com.hwadee.entity.SignContract;

import java.util.List;


public interface LegalService {
    List<Project> queryProjectsByState(int state);
    List<SignContract> queryContract();
    Integer uploadContract(SignContract signContract);//上传指定项目的合同
    void updateStateAndStaff(int projectId, int stateId, int currentStaffId);
}
