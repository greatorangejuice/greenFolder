package com.blansplatform.controller;

import com.blansplatform.dto.RegistrationPage;
import com.blansplatform.entity.User;
import com.blansplatform.service.RegistrationPageService;
import com.blansplatform.service.entityServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;

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
    public @ResponseBody Response newUserRegistration (@RequestBody User user) {
        userService.addUser(user);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

}
