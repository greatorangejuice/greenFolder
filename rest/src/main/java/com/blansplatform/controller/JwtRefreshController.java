package com.blansplatform.controller;

import com.blansplatform.dto.TokenDto;
import com.blansplatform.service.JwtRefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/token")
public class JwtRefreshController {

    private final JwtRefreshService jwtRefreshService;

    @Autowired
    public JwtRefreshController (JwtRefreshService jwtRefreshService) {
        this.jwtRefreshService = jwtRefreshService;
    }

    @PostMapping(value = "/refresh")
    public TokenDto refreshToken (@RequestBody TokenDto tokenDto) {
        return jwtRefreshService.refreshTokenFromUser(tokenDto);
    }
}
