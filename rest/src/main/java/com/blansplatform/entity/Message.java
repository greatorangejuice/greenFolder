package com.blansplatform.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "senderUserId")
    @JsonBackReference(value = "outgoingMessage_userFrom")
    private User userFrom;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "recipientUserId")
    @JsonBackReference(value = "inboxMessages_userTo")
    private User userTo;
    private String messageHead;
    private String messageBody;

    public Message(Long id, User userFrom, User userTo, String messageHead, String messageBody) {
        this.id = id;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.messageHead = messageHead;
        this.messageBody = messageBody;
    }

    public Message(User userFrom, User userTo, String messageHead, String messageBody) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.messageHead = messageHead;
        this.messageBody = messageBody;
    }

    public Message() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id.equals(message.id) &&
                userFrom.equals(message.userFrom) &&
                userTo.equals(message.userTo) &&
                messageHead.equals(message.messageHead) &&
                messageBody.equals(message.messageBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", userFrom=" + userFrom +
                ", userTo=" + userTo +
                ", messageHead='" + messageHead + '\'' +
                ", messageBody='" + messageBody + '\'' +
                '}';
    }
}
