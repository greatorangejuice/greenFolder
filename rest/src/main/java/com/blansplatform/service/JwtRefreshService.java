package com.blansplatform.service;

import com.blansplatform.dto.TokenDto;
import com.blansplatform.entity.User;
import com.blansplatform.security.jwt.JwtTokenProvider;
import com.blansplatform.service.entityServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class JwtRefreshService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public JwtRefreshService(JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    public TokenDto refreshTokenFromUser(TokenDto tokenDto) {
        User user = userService.findUserByUsername(tokenDto.getUsername());
        String token = tokenDto.getToken();
        if (token != null && user != null && jwtTokenProvider.validateToken(token)) {
            String refreshedToken = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
            return new TokenDto(refreshedToken, user.getName());
        }
        throw new BadCredentialsException("Invalid username or token");
    }

}
