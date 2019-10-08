/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.controller;

import com.blansplatform.dto.UserDto;
import com.blansplatform.entity.User;
import com.blansplatform.service.UserService;
import com.blansplatform.utils.converters.UserDtoFromUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    final private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserDto> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "/{id}")
    public UserDto findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public @ResponseBody Response addNewUser(@RequestBody User user) {
        userService.addUser(user);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DeleteMapping
    public @ResponseBody Response deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PutMapping
    public @ResponseBody Response updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

}
