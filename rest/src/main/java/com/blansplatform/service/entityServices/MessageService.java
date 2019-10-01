package com.blansplatform.service.entityServices;

import com.blansplatform.entity.Message;
import com.blansplatform.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MessageService {

    final private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public Message findMessageById(Long id) {
        Message message = messageRepository.findMessageById(id);
        if (message == null){
            throw new EntityNotFoundException("Message not found");
        }
        return message;
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
