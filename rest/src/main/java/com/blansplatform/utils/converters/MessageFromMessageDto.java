/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.utils.converters;

import com.blansplatform.dto.MessageDto;
import com.blansplatform.entity.Message;
import com.blansplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageFromMessageDto {

    private final UserRepository userRepository;

    @Autowired
    public MessageFromMessageDto(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Message convert(MessageDto messageDto) {
        return new Message(
                messageDto.getId(),
                messageDto.isViewed(),
                userRepository.findFirstUserByUsername(messageDto.getUserFrom()),
                userRepository.findFirstUserByUsername(messageDto.getUserTo()),
                messageDto.getMessage(),
                messageDto.getDate()
        );
    }
}
