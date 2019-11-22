/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.controller;

import com.blansplatform.dto.*;
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
    public @ResponseBody Response addNewMessage(@RequestBody MessageDto messageDto) {
        messageService.addMessage(messageDto);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DeleteMapping
    public @ResponseBody Response deleteMessage(@RequestBody MessageDto messageDto) {
        messageService.deleteMessage(messageDto);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PutMapping
    public @ResponseBody Response updateMessage(@RequestBody MessageDto messageDto) {
        messageService.updateMessage(messageDto);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PutMapping(path = "/set-as-viewed")
    public @ResponseBody Response setMessagesAsViewed(@RequestBody LinkedList<MessageDto> messagesDto) {
        messageService.setMessageViewStatusTrue(messagesDto);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @GetMapping(path = "/all-dialogs/{username}")
    public @ResponseBody
    UserAllDialoguesDto getAllDialoguesForUser(@PathVariable String username) {
        return messageService.getAllDialoguesForUser(username);
    }

    @PostMapping(path = "/dialog")
    public @ResponseBody DistinctDialogueDto getDialogueWithDistinctUser(@RequestBody DistinctDialogueDto distinctDialogueDto) {
        return messageService.getDialogueWithDistinctUser(distinctDialogueDto);
    }

    @GetMapping(path = "/{username}/new-messages-count")
    public @ResponseBody NewMessagesCount getNewMessagesCount(@PathVariable String username) {
        return messageService.getNewMessagesCount(username);
    }

    @PostMapping(path = "/dialog-new-messages-count")
    public @ResponseBody NewMessagesCount getNewMessagesCountForDialogue(@RequestBody SenderRecipientDto senderRecipientDto) {
        return messageService.getNewMessagesCountForDialogue(senderRecipientDto);
    }

    @PostMapping(path = "/set-dialog-as-viewed")
    public @ResponseBody Response setDialogueAsViewed(@RequestBody SenderRecipientDto senderRecipientDto) {
        messageService.setDialogueAsViewed(senderRecipientDto);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }
}
