package com.blansplatform.repository;

import com.blansplatform.entity.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findAll();
    Message findMessageById(Long id);
    @Query(value = "select * from message where sender_user_id = ?1 or recipient_user_id = ?1 order by id asc ", nativeQuery = true)
    List<Message> findAllMessagesForUser(Long id);
    @Query(value = "select * from message where sender_user_id = ?1 and recipient_user_id = ?2 or sender_user_id = ?2 and recipient_user_id = ?1 order by id asc", nativeQuery = true)
    List<Message> findAllMessagesBetweenTwoUsers(Long firstUserId, Long secondUserId);
}
