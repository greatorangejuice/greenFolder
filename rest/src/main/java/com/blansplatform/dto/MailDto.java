package com.blansplatform.dto;

public class MailDto {

    private String email;
    private String status;

    public MailDto(String email, String status) {
        this.email = email;
        this.status = status;
    }

    public MailDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
