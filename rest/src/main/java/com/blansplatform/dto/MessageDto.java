/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class MessageDto {

    private Long id;
    boolean isViewed;
    private String message;
    private String userFrom;
    private String userTo;
    private Timestamp date;

    public MessageDto(Long id, boolean isViewed, String userFrom, String userTo, String message, Timestamp date) {
        this.id = id;
        this.isViewed = isViewed;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.message = message;
        this.date = date;
    }

    public MessageDto() {
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public boolean isViewed() {
        return isViewed;
    }

    public void setViewed(boolean viewed) {
        isViewed = viewed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "id=" + id +
                ", isViewed=" + isViewed +
                ", message='" + message + '\'' +
                ", userFrom='" + userFrom + '\'' +
                ", userTo='" + userTo + '\'' +
                ", date=" + date +
                '}';
    }
}
