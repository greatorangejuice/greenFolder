/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.controller;

import com.blansplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;

@Controller
@RequestMapping("/activation")
public class AccountActivateController {

    private final UserService userService;

    @Autowired
    public AccountActivateController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{code}")
    public @ResponseBody Response userActivation(@PathVariable String code) {
        return userService.activateUserByCode(code);
    }
}
