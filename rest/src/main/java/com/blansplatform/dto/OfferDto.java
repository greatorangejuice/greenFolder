/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.dto;

import com.blansplatform.enumeration.OfferStatus;

public class OfferDto {

    private Long id;
    private String secretId;
    private int bid;
    private OfferStatus offerStatus;
    private String executor;
    private String customer;
    private String comment;

    public OfferDto(Long id, String secretId, int bid, OfferStatus offerStatus, String executor, String customer, String comment) {
        this.id = id;
        this.secretId = secretId;
        this.bid = bid;
        this.offerStatus = offerStatus;
        this.executor = executor;
        this.customer = customer;
        this.comment = comment;
    }

    public OfferDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
