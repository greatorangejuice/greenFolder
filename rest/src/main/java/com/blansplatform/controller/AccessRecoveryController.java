/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.controller;

import com.blansplatform.dto.AccessRestoreForm;
import com.blansplatform.dto.MailDto;
import com.blansplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;

@Controller
@RequestMapping("/accessrestore")
public class AccessRecoveryController {

    private final UserService userService;

    @Autowired
    public AccessRecoveryController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public @ResponseBody Response userAccountAccessRestore(@RequestBody MailDto mailDto){
        return userService.restoreAccess(mailDto);
    }

    @GetMapping("/{link}")
    public @ResponseBody AccessRestoreForm userAccountPasswordRefresh(@PathVariable String link){
        return userService.userPasswordRefresh(link);
    }

    @PostMapping("/{link}")
    public @ResponseBody Response userAccountNewPasswordAccept(@PathVariable String link, @RequestBody AccessRestoreForm accessRestoreForm) {
        return userService.userNewPasswordAccept(link, accessRestoreForm);
    }

}
