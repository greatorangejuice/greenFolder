/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.controller;

import com.blansplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.core.Response;

@Controller
@RequestMapping("/activation")
public class AccountActivateController {

    @Autowired
    UserService userService;

    @PostMapping("/{code}")
    public @ResponseBody Response userActivation(@PathVariable String code) {
        return userService.activateUserByCode(code);
    }
}
