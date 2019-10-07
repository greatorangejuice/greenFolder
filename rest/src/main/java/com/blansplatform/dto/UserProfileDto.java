/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.dto;

import java.util.List;

public class UserProfileDto {

    private UserDto user;
    private List<TaskDto> tasksLikeCustomer;
    private List<TaskDto> tasksLikeExecutor;
    private List<MessageDto> inboxMessages;
    private List<MessageDto> outgoingMessages;
    private List<OfferDto> inboxOffers;
    private List<OfferDto> outgoingOffers;

    public UserProfileDto(UserDto user, List<TaskDto> tasksLikeCustomer,
                          List<TaskDto> tasksLikeExecutor, List<MessageDto> inboxMessages,
                          List<MessageDto> outgoingMessages, List<OfferDto> inboxOffers,
                          List<OfferDto> outgoingOffers) {
        this.user = user;
        this.tasksLikeCustomer = tasksLikeCustomer;
        this.tasksLikeExecutor = tasksLikeExecutor;
        this.inboxMessages = inboxMessages;
        this.outgoingMessages = outgoingMessages;
        this.inboxOffers = inboxOffers;
        this.outgoingOffers = outgoingOffers;
    }

    public UserProfileDto() {
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<TaskDto> getTasksLikeCustomer() {
        return tasksLikeCustomer;
    }

    public void setTasksLikeCustomer(List<TaskDto> tasksLikeCustomer) {
        this.tasksLikeCustomer = tasksLikeCustomer;
    }

    public List<TaskDto> getTasksLikeExecutor() {
        return tasksLikeExecutor;
    }

    public void setTasksLikeExecutor(List<TaskDto> tasksLikeExecutor) {
        this.tasksLikeExecutor = tasksLikeExecutor;
    }

    public List<MessageDto> getInboxMessages() {
        return inboxMessages;
    }

    public void setInboxMessages(List<MessageDto> inboxMessages) {
        this.inboxMessages = inboxMessages;
    }

    public List<MessageDto> getOutgoingMessages() {
        return outgoingMessages;
    }

    public void setOutgoingMessages(List<MessageDto> outgoingMessages) {
        this.outgoingMessages = outgoingMessages;
    }

    public List<OfferDto> getInboxOffers() {
        return inboxOffers;
    }

    public void setInboxOffers(List<OfferDto> inboxOffers) {
        this.inboxOffers = inboxOffers;
    }

    public List<OfferDto> getOutgoingOffers() {
        return outgoingOffers;
    }

    public void setOutgoingOffers(List<OfferDto> outgoingOffers) {
        this.outgoingOffers = outgoingOffers;
    }

    @Override
    public String toString() {
        return "UserProfileDto{" +
                "user=" + user +
                ", tasksLikeCustomer=" + tasksLikeCustomer +
                ", tasksLikeExecutor=" + tasksLikeExecutor +
                ", inboxMessages=" + inboxMessages +
                ", outgoingMessages=" + outgoingMessages +
                ", inboxOffers=" + inboxOffers +
                ", outgoingOffers=" + outgoingOffers +
                '}';
    }
}
