package com.hwadee.entity;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author LiuHong
 * @CreateTime 2022/3/9 0:33
 * @Version 1.0.0
 */
public class TemContract {
    private int projectId;
    private MultipartFile contractFile;
    private int legalStaffId;

    public TemContract(int projectId, MultipartFile contractFile, int legalStaffId) {
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

    public MultipartFile getContractFile() {
        return contractFile;
    }

    public void setContractFile(MultipartFile contractFile) {
        this.contractFile = contractFile;
    }

    public int getLegalStaffId() {
        return legalStaffId;
    }

    public void setLegalStaffId(int legalStaffId) {
        this.legalStaffId = legalStaffId;
    }
}
