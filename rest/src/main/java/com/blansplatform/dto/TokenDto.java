package com.blansplatform.dto;

public class TokenDto {

    private String token;
    private String username;

    public TokenDto(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public TokenDto() {
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
