/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service;

import com.blansplatform.dto.MailDto;
import com.blansplatform.dto.UserDto;
import com.blansplatform.entity.User;
import com.blansplatform.enumeration.UserStatus;
import com.blansplatform.repository.UserRepository;
import com.blansplatform.utils.converters.UserDtoFromUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final String IF_EMAIL_EXIST = "yes";
    private final String IF_EMAIL_NOT_EXIST = "no";
    private final String IF_EMAIL_ALREADY_EXIST = " email already exist";
    private final String IF_USERNAME_ALREADY_EXIST = " username already exist";

    final private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findAll(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDtoFromUser::userDtoConverter)
                .collect(Collectors.toList());
    }


    public UserDto findUserById(Long id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new EntityNotFoundException("user not found");
        }
       return UserDtoFromUser.userDtoConverter(user);
    }

    public ResponseEntity<HashMap> addUser(User user) {
        HashMap response = newUserDataValidation(user);
        if (!response.isEmpty()) {
            return new ResponseEntity<HashMap>(response, HttpStatus.CONFLICT);
        }
        user.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(user);
        return new ResponseEntity<HashMap>(HttpStatus.CREATED);
    }

    public HashMap newUserDataValidation(User user) {
        Map<String, String> dataValidation = new HashMap<>();
        if (userRepository.findFirstUserByEmail(user.getEmail()) != null) {
            dataValidation.put("email", user.getEmail() + IF_EMAIL_ALREADY_EXIST);
        }
        if (userRepository.findFirstUserByUsername(user.getUsername()) != null) {
            dataValidation.put("username", user.getUsername() + IF_USERNAME_ALREADY_EXIST);
        }
        return (HashMap) dataValidation;
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public MailDto checkUserByEmail(MailDto mailDto) {
        User user = userRepository.findFirstUserByEmail(mailDto.getEmail());
        if(user == null){
            mailDto.setStatus(IF_EMAIL_NOT_EXIST);
            return mailDto;
        }
        mailDto.setStatus(IF_EMAIL_EXIST);
        return mailDto;
    }

    public User findUserByEmail (String email) {
        return userRepository.findFirstUserByEmail(email);
    }

    public User findUserByUsername(String username) {
       return userRepository.findFirstUserByUsername(username);
    }
}
