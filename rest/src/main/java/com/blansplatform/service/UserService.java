/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service;

import com.blansplatform.dto.AccessRestoreForm;
import com.blansplatform.dto.MailDto;
import com.blansplatform.dto.UserDto;
import com.blansplatform.entity.User;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;

public interface UserService {


    List<UserDto> findAll();

    UserDto findUserById(Long id);

    ResponseEntity<HashMap> addUser(User user);

    HashMap newUserDataValidation(User user);

    void deleteUser(User user);

    void updateUser(UserDto userDto);

    MailDto checkUserByEmail(MailDto mailDto);

    User findUserByUsername(String username);

    Response activateUserByCode(String code);

    Response restoreAccess(MailDto mailDto);

    AccessRestoreForm userPasswordRefresh(String link);

    Response userNewPasswordAccept(String link, AccessRestoreForm accessRestoreForm);

    void banUser(String username);

    void unbanUser(String username);

    void disableUser(String username);

    void restoreUser(String username);

    void setUserRoles(String username, List<String> roles);

    void deleteUserRoles(String username, List<String> roles);


}
