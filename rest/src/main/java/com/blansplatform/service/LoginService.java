/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service;

import com.blansplatform.dto.AuthenticationRequestDto;
import com.blansplatform.dto.TokenDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface LoginService {

    TokenDto loginDataCheck(@RequestBody AuthenticationRequestDto requestDto);

}
