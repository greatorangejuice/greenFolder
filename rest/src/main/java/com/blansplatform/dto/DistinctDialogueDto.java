/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.dto;

import java.util.List;

public class DistinctDialogueDto {

    String firstUsername;
    String secondUsername;
    List<MessageDto> messages;

    public DistinctDialogueDto(String firstUsername, String secondUsername, List<MessageDto> messages) {
        this.firstUsername = firstUsername;
        this.secondUsername = secondUsername;
        this.messages = messages;
    }

    public DistinctDialogueDto() {
    }

    public String getFirstUsername() {
        return firstUsername;
    }

    public void setFirstUsername(String firstUsername) {
        this.firstUsername = firstUsername;
    }

    public String getSecondUsername() {
        return secondUsername;
    }

    public void setSecondUsername(String secondUsername) {
        this.secondUsername = secondUsername;
    }

    public List<MessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDto> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "DistinctDialogueDto{" +
                "firstUsername='" + firstUsername + '\'' +
                ", secondUsername='" + secondUsername + '\'' +
                ", messages=" + messages +
                '}';
    }
}
