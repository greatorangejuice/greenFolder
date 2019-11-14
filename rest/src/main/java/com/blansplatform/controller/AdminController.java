/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.controller;

import com.blansplatform.dto.OfferDto;
import com.blansplatform.dto.TaskDto;
import com.blansplatform.dto.UserDto;
import com.blansplatform.entity.User;
import com.blansplatform.service.OfferService;
import com.blansplatform.service.TaskService;
import com.blansplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private final TaskService taskService;
    private final UserService userService;
    private final OfferService offerService;

    @Autowired
    public AdminController(TaskService taskService, UserService userService, OfferService offerService) {
        this.taskService = taskService;
        this.userService = userService;
        this.offerService = offerService;
    }

    @GetMapping(path = "/all-tasks")
    public List<TaskDto> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping(path = "/all-users")
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "/all-offers")
    public List<OfferDto> getAllOffers() {
        return offerService.findAll();
    }

    @GetMapping(path = "/user/ban/{username}")
    public Response banUserFromAdmin(@PathVariable String username) {
        userService.banUser(username);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @GetMapping(path = "/user/unban/{username}")
    public Response unbanUserFromAdmin(@PathVariable String username) {
        userService.unbanUser(username);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @GetMapping(path = "/user/disable/{username}")
    public Response disableUserFromAdmin(@PathVariable String username) {
        userService.disableUser(username);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @GetMapping(path = "/user/restore/{username}")
    public Response restoreUserFromAdmin(@PathVariable String username) {
        userService.restoreUser(username);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PostMapping(path = "/user/set-roles/{username}")
    public Response setUserRolesFromAdmin(@PathVariable String username, @RequestBody List<String> roles) {
        userService.setUserRoles(username, roles);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PostMapping(path = "/user/delete-roles/{username}")
    public Response deleteUserRolesFromAdmin(@PathVariable String username, @RequestBody List<String> roles) {
        userService.deleteUserRoles(username, roles);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PutMapping(path = "/edit-task")
    public Response editTaskFromAdmin(@RequestBody TaskDto taskDto) {
        taskService.updateTask(taskDto);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @DeleteMapping(path = "/delete-task/{secretId}")
    public Response deleteTaskFromAdmin(@PathVariable String secretId) {
        taskService.deleteTaskBySecretId(secretId);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PutMapping(path = "/edit-offer")
    public Response editTaskFromAdmin(@RequestBody OfferDto offerDto) {
        offerService.updateOffer(offerDto);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @DeleteMapping(path = "/delete-offer/{id}")
    public Response deleteTaskFromAdmin(@PathVariable Long id) {
        offerService.deleteOfferById(id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PutMapping(path = "/edit-user")
    public Response editUserFromAdmin(@RequestBody UserDto userDto) {
        userService.updateUser(userDto);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
