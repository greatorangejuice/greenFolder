/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.dto;

public class MessageDto {

    private Long id;
    boolean isViewed;
    private String messageHead;
    private String messageBody;
    private String userFrom;
    private String userTo;

    public MessageDto(Long id, boolean isViewed, String userFrom, String userTo, String messageHead, String messageBody) {
        this.id = id;
        this.isViewed = isViewed;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.messageHead = messageHead;
        this.messageBody = messageBody;
    }

    public MessageDto() {
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

    public String getMessageHead() {
        return messageHead;
    }

    public void setMessageHead(String messageHead) {
        this.messageHead = messageHead;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "id=" + id +
                ", isViewed=" + isViewed +
                ", messageHead='" + messageHead + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", userFrom='" + userFrom + '\'' +
                ", userTo='" + userTo + '\'' +
                '}';
    }
}
