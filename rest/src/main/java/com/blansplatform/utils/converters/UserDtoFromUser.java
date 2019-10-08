/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.utils.converters;

import com.blansplatform.dto.UserDto;
import com.blansplatform.entity.User;

public class UserDtoFromUser {
    public static UserDto userDtoConverter(User user){
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.getUserStatus(),
                user.getCity(),
                user.getFaculty(),
                user.getWebMoneyAccount(),
                user.getCourse(),
                user.getUniversity(),
                user.getRoles(),
                user.getEmail()
        );
    }
}
