package com.blansplatform.repository;

import com.blansplatform.entity.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findAll();
    Message findMessageById(Long id);
}
