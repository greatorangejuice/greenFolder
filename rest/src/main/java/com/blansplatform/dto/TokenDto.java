package com.blansplatform.dto;

import com.blansplatform.entity.Role;

import java.util.List;

public class TokenDto {

    private String token;
    private String username;
    private String tokenLifeTime;
    private List<Role> userRoles;

    public TokenDto(String token, String username, String tokenLifeTime, List<Role> userRoles) {
        this.token = token;
        this.username = username;
        this.tokenLifeTime = tokenLifeTime;
        this.userRoles = userRoles;
    }



    public TokenDto() {
    }

    public List<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<Role> userRoles) {
        this.userRoles = userRoles;
    }

    public String getTokenLifeTime() {
        return tokenLifeTime;
    }

    public void setTokenLifeTime(String tokenLifeTime) {
        this.tokenLifeTime = tokenLifeTime;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
