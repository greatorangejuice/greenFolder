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
import com.blansplatform.utils.converters.mailUtil.MailSenderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private static final String IF_EMAIL_EXIST = "yes";
    private static final String IF_EMAIL_NOT_EXIST = "no";
    private static final String IF_EMAIL_ALREADY_EXIST = " email already exist";
    private static final String IF_USERNAME_ALREADY_EXIST = " username already exist";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailSenderUtil mailSenderUtil;


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
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserStatus(UserStatus.ACTIVE);
        mailSenderUtil.send(user.getEmail(), "Activation link", mailSenderUtil.activationEmailBuilder(user.getActivationCode()) );
        userRepository.save(user);
        return new ResponseEntity<HashMap>(HttpStatus.CREATED);
    }

    private HashMap newUserDataValidation(User user) {
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFromDb = userRepository.findFirstUserByUsername(username);
        if (userFromDb == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return (UserDetails) userFromDb;
    }

    public Response activateUserByCode(String code) {
        User userFromDB = userRepository.findUserByActivationCode(code);
        if (userFromDB == null) {
            return Response.status(Response.Status.CONFLICT.getStatusCode()).build();
        }
        userFromDB.setActivationCode(null);
        userRepository.save(userFromDB);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
}
