package com.hwadee.entity;

import java.util.Arrays;

//合同签约表
public class SignContract {

    private int projectId;
    private String contractFile;
    private int legalStaffId;

    public SignContract(int projectId, String contractFile, int legalStaffId) {
        this.projectId = projectId;
        this.contractFile = contractFile;
        this.legalStaffId = legalStaffId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getContractFile() {
        return contractFile;
    }

    public void setContractFile(String contractFile) {
        this.contractFile = contractFile;
    }

    public int getLegalStaffId() {
        return legalStaffId;
    }

    public void setLegalStaffId(int legalStaffId) {
        this.legalStaffId = legalStaffId;
    }

    @Override
    public String toString() {
        return "SignContract{" +
                "projectId=" + projectId +
                ", contractFile=" + contractFile +
                ", legalStaffId=" + legalStaffId +
                '}';
    }
}
