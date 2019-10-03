package com.blansplatform.controller;

import com.blansplatform.dto.AuthenticationRequestDto;
import com.blansplatform.dto.TokenDto;
import com.blansplatform.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public TokenDto userAuthentication (@RequestBody AuthenticationRequestDto requestDto) {
        return loginService.loginDataCheck(requestDto);
    }

}
