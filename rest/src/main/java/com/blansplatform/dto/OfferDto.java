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

    public OfferDto(Long id, String bid, OfferStatus offerStatus) {
        this.id = id;

        this.bid = bid;
        this.offerStatus = offerStatus;
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

    @Override
    public String toString() {
        return "OfferDto{" +
                "id=" + id +
                ", bid='" + bid + '\'' +
                ", offerStatus=" + offerStatus +
                '}';
    }
}
