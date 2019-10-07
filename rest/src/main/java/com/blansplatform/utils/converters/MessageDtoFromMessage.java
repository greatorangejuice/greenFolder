/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.utils.converters;

import com.blansplatform.dto.MessageDto;
import com.blansplatform.entity.Message;

public class MessageDtoFromMessage {

    public static MessageDto MessageConverter(Message message) {
        return new MessageDto(
                message.getId(),
                message.getUserFrom(),
                message.getUserTo(),
                message.getMessageHead(),
                message.getMessageBody()
        );
    }
}
