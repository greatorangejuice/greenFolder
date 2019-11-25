/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service.serviceImpl;

import com.blansplatform.dto.AuthenticationRequestDto;
import com.blansplatform.dto.TokenDto;
import com.blansplatform.entity.User;
import com.blansplatform.security.jwt.JwtTokenProvider;
import com.blansplatform.service.LoginService;
import com.blansplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    @Value("${jwt.token.expired}")
    private String tokenTimeForExpired;

    @Autowired
    public LoginServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Override
    public TokenDto loginDataCheck(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findUserByUsername(username);
            if (user == null || !checkIsAccountActivated(user)) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }
            String token = jwtTokenProvider.createToken(username, user.getRoles());
            return new TokenDto(token, username, tokenTimeForExpired);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(e.getLocalizedMessage());
        }
    }

    private boolean checkIsAccountActivated(User user){
        return user.getActivationCode() == null;
    }
}
