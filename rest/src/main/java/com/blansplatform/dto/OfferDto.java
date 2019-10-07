/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.dto;

import com.blansplatform.entity.Task;
import com.blansplatform.entity.User;
import com.blansplatform.enumeration.OfferStatus;

public class OfferDto {

    private Long id;
    private String bid;
    private OfferStatus offerStatus;
    private String executor;
    private String customer;


    public OfferDto(Long id, String bid, OfferStatus offerStatus, String executor, String customer) {
        this.id = id;
        this.bid = bid;
        this.offerStatus = offerStatus;
        this.executor = executor;
        this.customer = customer;
    }

    public OfferDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "OfferDto{" +
                "id=" + id +
                ", bid='" + bid + '\'' +
                ", offerStatus=" + offerStatus +
                ", executor='" + executor + '\'' +
                ", customer='" + customer + '\'' +
                '}';
    }
}
