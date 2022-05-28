package com.hwadee.entity;

public class User {
    private int id;
    private String name;
    private String pwd;
    private String career;
    private int creditGradeId;
    private int salary;
    private long assets;
    private String recentBill;
    private String idCard;
    private String creditEvaluation;
    private String facePath;
    private String faceUrl;

    public User(String name, String pwd, String career, int salary, long assets, String recentBill, String idCard) {
        this.name = name;
        this.pwd = pwd;
        this.career = career;
        this.salary = salary;
        this.assets = assets;
        this.recentBill = recentBill;
        this.idCard = idCard;
    }

    public User(){}

    public User(int id, String name, String pwd, String career, int creditGradeId, int salary, long assets, String recentBill, String idCard, String creditEvaluation) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.career = career;
        this.creditGradeId = creditGradeId;
        this.salary = salary;
        this.assets = assets;
        this.recentBill = recentBill;
        this.idCard = idCard;
        this.creditEvaluation = creditEvaluation;
    }

    public User(int id, String name, String pwd, String career, int creditGradeId, int salary, long assets, String recentBill, String idCard, String creditEvaluation, String facePath, String faceUrl) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.career = career;
        this.creditGradeId = creditGradeId;
        this.salary = salary;
        this.assets = assets;
        this.recentBill = recentBill;
        this.idCard = idCard;
        this.creditEvaluation = creditEvaluation;
        this.facePath = facePath;
        this.faceUrl = faceUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public int getCreditGradeId() {
        return creditGradeId;
    }

    public void setCreditGradeId(int creditGradeId) {
        this.creditGradeId = creditGradeId;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public long getAssets() {
        return assets;
    }

    public void setAssets(long assets) {
        this.assets = assets;
    }

    public String getRecentBill() {
        return recentBill;
    }

    public void setRecentBill(String recentBill) {
        this.recentBill = recentBill;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCreditEvaluation() {
        return creditEvaluation;
    }

    public void setCreditEvaluation(String creditEvaluation) {
        this.creditEvaluation = creditEvaluation;
    }

    public String getFacePath() {
        return facePath;
    }

    public void setFacePath(String facePath) {
        this.facePath = facePath;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", career='" + career + '\'' +
                ", creditGradeId=" + creditGradeId +
                ", salary=" + salary +
                ", assets=" + assets +
                ", recentBill='" + recentBill + '\'' +
                ", id_Card='" + idCard + '\'' +
                ", creditEvaluation='" + creditEvaluation + '\'' +
                '}';
    }
}
