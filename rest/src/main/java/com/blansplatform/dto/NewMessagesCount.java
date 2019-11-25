/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.dto;

public class NewMessagesCount {

    private Long count;

    public NewMessagesCount(Long count) {
        this.count = count;
    }

    public NewMessagesCount() {
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "NewMessagesCount{" +
                "count=" + count +
                '}';
    }
}
