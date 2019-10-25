package com.blansplatform.service;

import com.blansplatform.dto.TokenDto;
import com.blansplatform.entity.User;
import com.blansplatform.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class JwtRefreshService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    @Value("${jwt.token.expired}")
    private String tokenTimeForExpired;

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
            return new TokenDto(refreshedToken, user.getName(), tokenTimeForExpired);
        }
        throw new BadCredentialsException("Invalid username or token");
    }

}
