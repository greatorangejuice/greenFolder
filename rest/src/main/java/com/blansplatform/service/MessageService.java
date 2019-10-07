/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service;

import com.blansplatform.dto.MessageDto;
import com.blansplatform.entity.Message;
import com.blansplatform.repository.MessageRepository;
import com.blansplatform.utils.converters.MessageDtoFromMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    final private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<MessageDto> findAll() {
        List<Message> messages = messageRepository.findAll();
        return messages.stream()
                .map(MessageDtoFromMessage::MessageConverter)
                .collect(Collectors.toList());
    }

    public MessageDto findMessageById(Long id) {
        Message message = messageRepository.findMessageById(id);
        if (message == null){
            throw new EntityNotFoundException("Message not found");
        }
        return MessageDtoFromMessage.MessageConverter(message);
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
}
