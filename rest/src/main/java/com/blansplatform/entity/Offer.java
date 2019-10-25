package com.blansplatform.entity;

import com.blansplatform.enumeration.OfferStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Task.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "taskId")
    private Task task;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "executorId")
    private User executor;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private User customer;
    private int bid;
    private OfferStatus offerStatus;
    private String comment;

    public Offer(Task task, User executor, User customer, int bid, OfferStatus offerStatus, String comment) {
        this.task = task;
        this.executor = executor;
        this.customer = customer;
        this.bid = bid;
        this.offerStatus = offerStatus;
        this.comment = comment;
    }

    public Offer(Long id, Task task, User executor, User customer, int bid, OfferStatus offerStatus, String comment) {
        this.id = id;
        this.task = task;
        this.executor = executor;
        this.customer = customer;
        this.bid = bid;
        this.offerStatus = offerStatus;
        this.comment = comment;
    }

    public Offer(int bid, String comment) {
        this.bid = bid;
        this.comment = comment;
    }

    public Offer() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Objects.equals(id, offer.id) &&
                Objects.equals(task, offer.task) &&
                Objects.equals(executor, offer.executor) &&
                Objects.equals(customer, offer.customer) &&
                Objects.equals(bid, offer.bid) &&
                offerStatus == offer.offerStatus &&
                Objects.equals(comment, offer.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, task, executor, customer, bid, offerStatus, comment);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", task=" + task +
                ", executor=" + executor +
                ", customer=" + customer +
                ", bid='" + bid + '\'' +
                ", offerStatus=" + offerStatus +
                ", comment='" + comment + '\'' +
                '}';
    }
}
