/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service;

import com.blansplatform.dto.UserAllDialoguesDto;
import com.blansplatform.dto.DistinctDialogueDto;
import com.blansplatform.dto.MessageDto;
import com.blansplatform.entity.Message;
import com.blansplatform.repository.MessageRepository;
import com.blansplatform.utils.converters.MessageDtoFromMessage;
import com.blansplatform.utils.converters.MessageFromMessageDto;
import com.blansplatform.utils.sorter.DialoguesSorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageFromMessageDto messageFromMessageDto;
    private final UserService userService;

    @Autowired
    public MessageService(MessageRepository messageRepository, MessageFromMessageDto messageFromMessageDto, UserService userService) {
        this.messageRepository = messageRepository;
        this.messageFromMessageDto = messageFromMessageDto;
        this.userService = userService;
    }

    public List<MessageDto> findAll() {
        List<Message> messages = messageRepository.findAll();
        return messages.stream()
                .map(MessageDtoFromMessage::convert)
                .collect(Collectors.toList());
    }

    public MessageDto findMessageById(Long id) {
        Message message = messageRepository.findMessageById(id);
        if (message == null){
            throw new EntityNotFoundException("Message not found");
        }
        return MessageDtoFromMessage.convert(message);
    }

    public void addMessage(Message message) {
        messageRepository.save(message);
    }

    public void deleteMessage(Message message) {
        messageRepository.delete(message);
    }

    public void updateMessage(Message message) {
        messageRepository.save(message);
    }

    public void setMessageViewStatusTrue(LinkedList<MessageDto> messagesDto) {
        List<Message> messages = messagesDto.stream()
                .map(messageFromMessageDto::convert)
                .collect(Collectors.toList());
        for (Message m: messages) {
            m.setViewed(true);
        }
        messageRepository.saveAll(messages);
    }

    public UserAllDialoguesDto getAllDialoguesForUser(String username) {
        Long userId = userService.findUserByUsername(username).getId();
        List<Message> messagesFromDb = messageRepository.findAllMessagesForUser(userId);
        if (messagesFromDb == null) {
            throw new EntityNotFoundException("No messages for this user");
        }
        List<MessageDto> messagesDto = messagesFromDb.stream()
                .map(MessageDtoFromMessage::convert)
                .collect(Collectors.toList());
        return new UserAllDialoguesDto(DialoguesSorter.sortAndGroup(messagesDto));
    }

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
}
