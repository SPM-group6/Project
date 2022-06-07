package com.hwadee.entity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
/**
 * @Author LiuZihan
 * @CreateTime 2022/6/7 16:59
 * @Version 1.0.0
 */
public class JwtCrew implements UserDetails{
    private Integer id;
    private String name;
    private String password;
    private int authorityId; //职位id
    private Collection<? extends GrantedAuthority> authorities;

    public JwtCrew() {
    }

    // 写一个能直接使用user创建jwtUser的构造器
    public JwtCrew(Crew crew) {
        id = crew.getStaffId();
        name = crew.getStaffName();
        password = crew.getPassword();
        authorityId=crew.getAuthorityId();
        authorities = Collections.singleton(new SimpleGrantedAuthority(crew.getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "JwtUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", authorityId='" + authorityId + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
