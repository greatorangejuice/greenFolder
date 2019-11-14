/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service;

import com.blansplatform.dto.AccessRestoreForm;
import com.blansplatform.dto.MailDto;
import com.blansplatform.dto.UserDto;
import com.blansplatform.entity.User;
import com.blansplatform.enumeration.UserStatus;
import com.blansplatform.repository.RoleRepository;
import com.blansplatform.repository.UserRepository;
import com.blansplatform.utils.converters.UserDtoFromUser;
import com.blansplatform.utils.mailUtil.MailSenderUtil;
import org.modelmapper.ModelMapper;
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
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSenderUtil mailSenderUtil;
    private final RoleRepository roleRepository;

    private static final String IF_EMAIL_EXIST = "yes";
    private static final String IF_EMAIL_NOT_EXIST = "no";
    private static final String IF_EMAIL_ALREADY_EXIST = " email already exist";
    private static final String IF_USERNAME_ALREADY_EXIST = " username already exist";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       MailSenderUtil mailSenderUtil, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSenderUtil = mailSenderUtil;
        this.roleRepository = roleRepository;
    }

    public List<UserDto> findAll(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDtoFromUser::convert)
                .collect(Collectors.toList());
    }

    public UserDto findUserById(Long id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new EntityNotFoundException("user not found");
        }
        return UserDtoFromUser.convert(user);
    }

    public ResponseEntity<HashMap> addUser(User user) {
        HashMap response = newUserDataValidation(user);
        if (!response.isEmpty()) {
            return new ResponseEntity<HashMap>(response, HttpStatus.CONFLICT);
        }
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserStatus(UserStatus.ACTIVE);
        user.setRoles(new LinkedList<>());
        user.getRoles().add(roleRepository.findRoleByName("USER"));
        mailSenderUtil.send(user.getEmail(), "Activation link", mailSenderUtil.activationEmailBuilder(user.getActivationCode()) );
        userRepository.save(user);
        return new ResponseEntity<HashMap>(HttpStatus.CREATED);
    }

    public HashMap newUserDataValidation(User user) {
        Map<String, String> dataValidation = new HashMap<>();
        if (userRepository.findUserByEmail(user.getEmail()) != null) {
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

    public void updateUser(UserDto userDto) {
        User userFromDb = userRepository.findUserById(userDto.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(userDto, userFromDb);
        userRepository.save(userFromDb);
    }

    public MailDto checkUserByEmail(MailDto mailDto) {
        User user = userRepository.findUserByEmail(mailDto.getEmail());
        if(user == null){
            mailDto.setStatus(IF_EMAIL_NOT_EXIST);
            return mailDto;
        }
        mailDto.setStatus(IF_EMAIL_EXIST);
        return mailDto;
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

    public Response restoreAccess(MailDto mailDto) {
       User userFromDb = userRepository.findUserByEmail(mailDto.getEmail());
       if (userFromDb == null) {
           return Response.status(Response.Status.CONFLICT).build();
       }
       userFromDb.setPasswordRestoreLink(UUID.randomUUID().toString());
       mailSenderUtil.send(userFromDb.getEmail(), "Password restore",
               mailSenderUtil.passwordRestoreEmailBuilder(userFromDb.getPasswordRestoreLink()));
       userRepository.save(userFromDb);
       return Response.status(Response.Status.OK).build();//if ok, message from user
    }

    public AccessRestoreForm userPasswordRefresh(String link) {
        User userFromDB = userRepository.findUserByPasswordRestoreLink(link);
        if (userFromDB == null) {
            throw new EntityNotFoundException("User not found");
        }
        return new AccessRestoreForm(userFromDB.getUsername());
    }

    public Response userNewPasswordAccept(String link, AccessRestoreForm accessRestoreForm) {
        User userFromDb = userRepository.findUserByPasswordRestoreLink(link);
        if (userFromDb == null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        userFromDb.setPassword(passwordEncoder.encode(accessRestoreForm.getNewPassword()));
        userFromDb.setPasswordRestoreLink(null);
        userRepository.save(userFromDb);
        return Response.status(Response.Status.OK).build();
    }

    public void banUser(String username) {
        User userFromDb = userRepository.findFirstUserByUsername(username);
        if (userFromDb == null) {
            throw new EntityNotFoundException("user not found");
        }
        userFromDb.setUserStatus(UserStatus.BANNED);
        userRepository.save(userFromDb);
    }

    public void unbanUser(String username) {
        User userFromDb = userRepository.findFirstUserByUsername(username);
        if (userFromDb == null) {
            throw new EntityNotFoundException("user not found");
        }
        userFromDb.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(userFromDb);
    }

    public void disableUser(String username) {
        User userFromDb = userRepository.findFirstUserByUsername(username);
        if (userFromDb == null) {
            throw new EntityNotFoundException("user not found");
        }
        userFromDb.setUserStatus(UserStatus.INACTIVE);
        userRepository.save(userFromDb);
    }

    public void restoreUser(String username) {
        unbanUser(username);
    }

    public void setUserRoles(String username, List<String> roles) {
        User userFromDb = userRepository.findFirstUserByUsername(username);
        if (userFromDb == null) {
            throw new EntityNotFoundException("user not found");
        }
        for (String role: roles) {
            userFromDb.getRoles().add(roleRepository.findRoleByName(role));
        }
        userRepository.save(userFromDb);
    }

    public void deleteUserRoles(String username, List<String> roles) {
        User userFromDb = userRepository.findFirstUserByUsername(username);
        if (userFromDb == null) {
            throw new EntityNotFoundException("user not found");
        }
        for (String role: roles) {
            userFromDb.getRoles().remove(roleRepository.findRoleByName(role));
        }
        userRepository.save(userFromDb);
    }
}
