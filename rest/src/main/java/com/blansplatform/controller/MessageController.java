/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.controller;

import com.blansplatform.dto.MessageDto;
import com.blansplatform.entity.Message;
import com.blansplatform.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    final private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/all")
    public List<MessageDto> findAllMessages() {
        return messageService.findAll();
    }

    @GetMapping(path = "/{id}")
    public MessageDto findMessageById(@PathVariable Long id) {
        return messageService.findMessageById(id);
    }

    @PostMapping
    public @ResponseBody Response addNewMessage(@RequestBody Message message) {
        messageService.addMessage(message);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DeleteMapping
    public @ResponseBody Response deleteMessage(@RequestBody Message message) {
        messageService.deleteMessage(message);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PutMapping
    public @ResponseBody Response updateMessage(@RequestBody Message message) {
        messageService.updateMessage(message);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PutMapping(path = "/set-as-viewed")
    public @ResponseBody Response setMessagesAsViewed(@RequestBody LinkedList<MessageDto> messagesDto) {
        messageService.setMessageViewStatusTrue(messagesDto);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }
}
