package com.blansplatform.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    boolean isViewed;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "senderUserId")
    private User userFrom;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "recipientUserId")
    private User userTo;
    private String messageHead;
    private String messageBody;

    public Message(Long id, User userFrom, User userTo, String messageHead, String messageBody, boolean isViewed) {
        this.id = id;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.messageHead = messageHead;
        this.messageBody = messageBody;
        this.isViewed = isViewed;
    }

    public Message(User userFrom, User userTo, String messageHead, String messageBody, boolean isViewed) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.messageHead = messageHead;
        this.messageBody = messageBody;
        this.isViewed = isViewed;
    }

    public Message() {
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
        return isViewed == message.isViewed &&
                Objects.equals(id, message.id) &&
                Objects.equals(userFrom, message.userFrom) &&
                Objects.equals(userTo, message.userTo) &&
                Objects.equals(messageHead, message.messageHead) &&
                Objects.equals(messageBody, message.messageBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isViewed, userFrom, userTo, messageHead, messageBody);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", isViewed=" + isViewed +
                ", userFrom=" + userFrom +
                ", userTo=" + userTo +
                ", messageHead='" + messageHead + '\'' +
                ", messageBody='" + messageBody + '\'' +
                '}';
    }
}
