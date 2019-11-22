package com.blansplatform.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
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
    private String message;
    private Timestamp date;

    public Message(Long id, boolean isViewed, User userFrom, User userTo, String message, Timestamp date) {
        this.id = id;
        this.isViewed = isViewed;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.message = message;
        this.date = date;
    }

    public Message(User userFrom, User userTo, String message, Timestamp date) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.message = message;
        this.date = date;
    }

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isViewed() {
        return isViewed;
    }

    public void setViewed(boolean viewed) {
        isViewed = viewed;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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
                Objects.equals(this.message, message.message) &&
                Objects.equals(date, message.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isViewed, userFrom, userTo, message, date);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", isViewed=" + isViewed +
                ", userFrom=" + userFrom +
                ", userTo=" + userTo +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
