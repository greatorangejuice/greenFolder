/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.dto;

public class AcceptOrDeclineOfferDto {

    private Long offerId;
    private String taskSecretId;
    private String customerResponse;
    private String executor;

    public AcceptOrDeclineOfferDto(Long offerId, String taskSecretId, String customerResponse, String executor) {
        this.offerId = offerId;
        this.taskSecretId = taskSecretId;
        this.customerResponse = customerResponse;
        this.executor = executor;
    }

    public AcceptOrDeclineOfferDto() {
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getTaskSecretId() {
        return taskSecretId;
    }

    public void setTaskSecretId(String taskSecretId) {
        this.taskSecretId = taskSecretId;
    }

    public String getCustomerResponse() {
        return customerResponse;
    }

    public void setCustomerResponse(String customerResponse) {
        this.customerResponse = customerResponse;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }
}
