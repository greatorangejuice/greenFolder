/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.dto;

public class AccessRestoreForm {

    private String username;
    private String newPassword;

    public AccessRestoreForm(String username, String newPassword) {
        this.username = username;
        this.newPassword = newPassword;
    }

    public AccessRestoreForm(String username) {
        this.username = username;
    }

    public AccessRestoreForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
