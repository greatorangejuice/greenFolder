package com.blansplatform.entity;

import com.blansplatform.enumeration.OfferStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Task.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "taskId")
    @JsonBackReference(value = "offers_task")
    private Task task;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "executorId")
    @JsonBackReference(value = "offers_executor")
    private User executor;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    @JsonBackReference(value = "offers_customer")
    private User customer;
    private String bid;
    private OfferStatus offerStatus;

    public Offer(Task task, User executor, User customer, String bid, OfferStatus offerStatus) {
        this.task = task;
        this.executor = executor;
        this.customer = customer;
        this.bid = bid;
        this.offerStatus = offerStatus;
    }

    public Offer(Long id, Task task, User executor, User customer, String bid, OfferStatus offerStatus) {
        this.id = id;
        this.task = task;
        this.executor = executor;
        this.customer = customer;
        this.bid = bid;
        this.offerStatus = offerStatus;
    }

    public Offer() {
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

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
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
        return id.equals(offer.id) &&
                task.equals(offer.task) &&
                executor.equals(offer.executor) &&
                customer.equals(offer.customer) &&
                bid.equals(offer.bid) &&
                offerStatus == offer.offerStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
                '}';
    }
}
