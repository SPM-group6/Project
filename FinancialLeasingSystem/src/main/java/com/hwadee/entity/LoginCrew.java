package com.hwadee.entity;

/**
 * @Author LiuZihan
 * @CreateTime 2022/6/7 20:29
 * @Version 1.0.0
 */
public class LoginCrew {
    private Integer id;
    private String password;

    public LoginCrew(){

    }

    public LoginCrew(Integer id) {
        this.id = id;
    }

    public LoginCrew(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
