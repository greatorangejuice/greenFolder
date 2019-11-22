/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service;

import com.blansplatform.dto.TokenDto;

public interface JwtRefreshService {

    TokenDto refreshTokenFromUser(TokenDto tokenDto);

}
