package com.hwadee.entity;

/**
 * @Author LiuZihan
 * @CreateTime 2022/3/6 20:13
 * @Version 1.0.0
 */
//项目资产处置最终决定
public class ProjectDisposal {
    private int projectId;
    private String projectName;
    private String leasedAsset;
    private String pawnName;
    private String pawnDisposal;  //抵押物处理方式
    private String leaseDisposal;  //租赁物处理方式
    private int appraisedValue;  //估值

    public ProjectDisposal(int projectId, String projectName,String pawnName,String pawnDisposal, String leaseDisposal,String leasedAsset,int appraisedValue){
        this.appraisedValue=appraisedValue;
        this.leasedAsset=leasedAsset;
        this.leaseDisposal=leaseDisposal;
        this.pawnDisposal=pawnDisposal;
        this.pawnName=pawnName;
        this.projectId=projectId;
        this.projectName=projectName;
    }


    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLeasedAsset() {
        return leasedAsset;
    }

    public void setLeasedAsset(String leasedAsset) {
        this.leasedAsset = leasedAsset;
    }

    public String getPawnName() {
        return pawnName;
    }

    public void setPawnName(String pawnName) {
        this.pawnName = pawnName;
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

    public int getAppraisedValue() {
        return appraisedValue;
    }

    public void setAppraisedValue(int appraisedValue) {
        this.appraisedValue = appraisedValue;
    }

    @Override
    public String toString() {
        return "ProjectDisposal{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", leasedAsset='" + leasedAsset + '\'' +
                ", pawnName='" + pawnName + '\'' +
                ", pawnDisposal='" + pawnDisposal + '\'' +
                ", leaseDisposal='" + leaseDisposal + '\'' +
                ", appraisedValue=" + appraisedValue +
                '}';
    }
}
