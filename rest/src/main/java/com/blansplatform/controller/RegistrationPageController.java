package com.blansplatform.controller;

import com.blansplatform.dto.RegistrationPage;
import com.blansplatform.entity.User;
import com.blansplatform.service.RegistrationPageService;
import com.blansplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationPageController {

    final private RegistrationPageService registrationPageService;
    final private UserService userService;

    @Autowired
    public RegistrationPageController(RegistrationPageService registrationPageService, UserService userService) {
        this.registrationPageService = registrationPageService;
        this.userService = userService;
    }

    @GetMapping
    public @ResponseBody RegistrationPage getRegistrationPage() {
        return registrationPageService.getRegistrationPage();
    }

    @PostMapping
    public @ResponseBody ResponseEntity newUserRegistration (@RequestBody User user) {
        return userService.addUser(user);
    }

}
