/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service;

import com.blansplatform.dto.*;

import java.util.LinkedList;
import java.util.List;

public interface MessageService {

    List<MessageDto> findAll();

    MessageDto findMessageById(Long id);

    void addMessage(MessageDto messageDto);

    void deleteMessage(MessageDto messageDto);

    void updateMessage(MessageDto messageDto);

    void setMessageViewStatusTrue(LinkedList<MessageDto> messagesDto);

    UserAllDialoguesDto getAllDialoguesForUser(String username);

    DistinctDialogueDto getDialogueWithDistinctUser(DistinctDialogueDto distinctDialogueDto);

    NewMessagesCount getNewMessagesCount(String username);

    NewMessagesCount getNewMessagesCountForDialogue(SenderRecipientDto senderRecipientDto);

    void setDialogueAsViewed(SenderRecipientDto senderRecipientDto);
}
