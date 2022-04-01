package com.hwadee.entity;

/**
 * @Author LiuZihan
 * @CreateTime 2022/3/8 17:47
 * @Version 1.0.0
 */
//用于业务员新建项目资产处理申请的表单提交
public class salesAssets {
    private Integer projectId; //项目id
    private String pawnDisposal;  //抵押物处理方式
    private String leaseDisposal;  //租赁物处理方式
    private Integer appraisedValue;  //估值

    public salesAssets(Integer projectId, String pawnDisposal, String leaseDisposal, Integer appraisedValue) {
        this.projectId = projectId;
        this.pawnDisposal = pawnDisposal;
        this.leaseDisposal = leaseDisposal;
        this.appraisedValue = appraisedValue;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getPawnDisposal() {
        return pawnDisposal;
    }

    public void setPawnDisposal(String pawnDisposal) {
        this.pawnDisposal = pawnDisposal;
    }

    public String getLeaseDisposal() {
        return leaseDisposal;
    }

    public void setLeaseDisposal(String leaseDisposal) {
        this.leaseDisposal = leaseDisposal;
    }

    public Integer getAppraisedValue() {
        return appraisedValue;
    }

    public void setAppraisedValue(Integer appraisedValue) {
        this.appraisedValue = appraisedValue;
    }
}
