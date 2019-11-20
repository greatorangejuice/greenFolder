/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.dto;

import java.util.List;
import java.util.Map;

public class UserAllDialoguesDto {

    Map<String, List<MessageDto>> dialogues;

    public UserAllDialoguesDto(Map<String, List<MessageDto>> dialogues) {
        this.dialogues = dialogues;
    }

    public UserAllDialoguesDto() {
    }

    public Map<String, List<MessageDto>> getDialogues() {
        return dialogues;
    }

    public void setDialogues(Map<String, List<MessageDto>> dialogues) {
        this.dialogues = dialogues;
    }

    @Override
    public String toString() {
        return "UserAllDialoguesDto{" +
                "dialogues=" + dialogues +
                '}';
    }
}
