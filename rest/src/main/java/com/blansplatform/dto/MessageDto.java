/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.dto;

import com.blansplatform.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class MessageDto {

    private Long id;
    @JsonIgnore
    private User userFrom;
    @JsonIgnore
    private User userTo;
    private String messageHead;
    private String messageBody;

    public MessageDto(Long id, User userFrom, User userTo, String messageHead, String messageBody) {
        this.id = id;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.messageHead = messageHead;
        this.messageBody = messageBody;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
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
                ", messageHead='" + messageHead + '\'' +
                ", messageBody='" + messageBody + '\'' +
                '}';
    }
}
