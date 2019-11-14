/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.utils.converters;

import com.blansplatform.dto.MessageDto;
import com.blansplatform.entity.Message;

public class MessageDtoFromMessage {

    public static MessageDto convert(Message message) {
        return new MessageDto(
                message.getId(),
                message.isViewed(),
                message.getUserFrom().getUsername(),
                message.getUserTo().getUsername(),
                message.getMessageHead(),
                message.getMessageBody()
        );
    }
}
