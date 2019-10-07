package com.blansplatform.controller;

import com.blansplatform.dto.MailDto;
import com.blansplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mail")
public class MailValidationController {

    final private UserService userService;

    @Autowired
    public MailValidationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/check")
    public @ResponseBody MailDto mailAlreadyExistCheck(@RequestBody MailDto mailDto){
        return userService.checkUserByEmail(mailDto);
    }
}
