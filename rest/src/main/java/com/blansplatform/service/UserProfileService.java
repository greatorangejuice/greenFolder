/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service;

import com.blansplatform.dto.UserProfileDto;
import com.blansplatform.entity.User;

public interface UserProfileService {

    UserProfileDto userProfileById(Long id);

    UserProfileDto userProfileByUsername(String username);

    UserProfileDto userProfileDtoBuilder(User user);

}
