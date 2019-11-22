/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.dto;

public class SenderRecipientDto {

    private String sender;
    private String recipient;

    public SenderRecipientDto(String sender, String recipient) {
        this.sender = sender;
        this.recipient = recipient;
    }

    public SenderRecipientDto() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "SenderRecipientDto{" +
                "sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                '}';
    }
}
