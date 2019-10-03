package com.blansplatform.dto;

public class TokenDto {

    private String token;
    private String username;
    private String tokenLifeTime;

    public TokenDto(String token, String username, String tokenLifeTime) {
        this.token = token;
        this.username = username;
        this.tokenLifeTime = tokenLifeTime;
    }



    public TokenDto() {
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
