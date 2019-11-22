/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service.serviceImpl;

import com.blansplatform.dto.*;
import com.blansplatform.entity.Message;
import com.blansplatform.repository.MessageRepository;
import com.blansplatform.service.MessageService;
import com.blansplatform.service.UserService;
import com.blansplatform.utils.converters.MessageDtoFromMessage;
import com.blansplatform.utils.converters.MessageFromMessageDto;
import com.blansplatform.utils.sorter.DialoguesSorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageFromMessageDto messageFromMessageDto;
    private final UserService userService;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, MessageFromMessageDto messageFromMessageDto, UserService userService) {
        this.messageRepository = messageRepository;
        this.messageFromMessageDto = messageFromMessageDto;
        this.userService = userService;
    }

    @Override
    public List<MessageDto> findAll() {
        List<Message> messages = messageRepository.findAll();
        return messages.stream()
                .map(MessageDtoFromMessage::convert)
                .collect(Collectors.toList());
    }

    @Override
    public MessageDto findMessageById(Long id) {
        Message message = messageRepository.findMessageById(id);
        if (message == null){
            throw new EntityNotFoundException("Message not found");
        }
        return MessageDtoFromMessage.convert(message);
    }

    @Override
    public void addMessage(MessageDto messageDto) {
        Message message = messageFromMessageDto.convert(messageDto);
        message.setDate(Timestamp.valueOf(LocalDateTime.now()));
        message.setViewed(false);
        messageRepository.save(message);
    }

    @Override
    public void deleteMessage(MessageDto messageDto) {
        Message message = messageFromMessageDto.convert(messageDto);
        messageRepository.delete(message);
    }

    @Override
    public void updateMessage(MessageDto messageDto) {
        Message message = messageFromMessageDto.convert(messageDto);
        messageRepository.save(message);
    }

    @Override
    public void setMessageViewStatusTrue(LinkedList<MessageDto> messagesDto) {
        List<Message> messages = messagesDto.stream()
                .map(messageFromMessageDto::convert)
                .collect(Collectors.toList());
        for (Message m: messages) {
            m.setViewed(true);
        }
        messageRepository.saveAll(messages);
    }

    @Override
    public UserAllDialoguesDto getAllDialoguesForUser(String username) {
        Long userId = userService.findUserByUsername(username).getId();
        List<Message> messagesFromDb = messageRepository.findAllMessagesForUser(userId);
        if (messagesFromDb == null) {
            throw new EntityNotFoundException("No messages for this user");
        }
        List<MessageDto> messagesDto = messagesFromDb.stream()
                .map(MessageDtoFromMessage::convert)
                .collect(Collectors.toList());
        return new UserAllDialoguesDto(DialoguesSorter.messagesSortForGroups(messagesDto));
    }

    @Override
    public DistinctDialogueDto getDialogueWithDistinctUser(DistinctDialogueDto distinctDialogueDto) {
        Long firstUserId = userService.findUserByUsername(distinctDialogueDto.getFirstUsername()).getId();
        Long secondUserId = userService.findUserByUsername(distinctDialogueDto.getSecondUsername()).getId();
        List<Message> messagesFromDb = messageRepository.findAllMessagesBetweenTwoUsers(firstUserId, secondUserId);
        List<MessageDto> messagesDto = messagesFromDb.stream()
                .map(MessageDtoFromMessage::convert)
                .collect(Collectors.toList());
        distinctDialogueDto.setMessages(messagesDto);
        return distinctDialogueDto;
    }

    @Override
    public NewMessagesCount getNewMessagesCount(String username) {
        Long id = userService.findUserByUsername(username).getId();
        Long count = messageRepository.countOfNewMessagesForUser(id);
        return new NewMessagesCount(count);
    }

    @Override
    public NewMessagesCount getNewMessagesCountForDialogue(SenderRecipientDto senderRecipientDto) {
        Long senderUserId = userService.findUserByUsername(senderRecipientDto.getSender()).getId();
        Long recipientUserId = userService.findUserByUsername(senderRecipientDto.getRecipient()).getId();
        Long count = messageRepository.newMessagesCountForDialogue(senderUserId, recipientUserId);
        return new NewMessagesCount(count);
    }

    @Override
    public void setDialogueAsViewed(SenderRecipientDto senderRecipientDto) {
        Long senderId = userService.findUserByUsername(senderRecipientDto.getSender()).getId();
        Long recipientId = userService.findUserByUsername(senderRecipientDto.getRecipient()).getId();
        List<Message> messagesFromDb = messageRepository.newMessagesFromSenderToRecipient(senderId, recipientId);
        for (Message m: messagesFromDb) {
            m.setViewed(true);
        }
        messageRepository.saveAll(messagesFromDb);
    }
}
