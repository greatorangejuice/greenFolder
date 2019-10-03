package com.blansplatform.service.entityServices;

import com.blansplatform.dto.MailDto;
import com.blansplatform.entity.User;
import com.blansplatform.enumeration.UserStatus;
import com.blansplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    private final String IF_EMAIL_EXIST = "yes";
    private final String IF_EMAIL_NOT_EXIST = "no";

    final private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new EntityNotFoundException("user not found");
        }
       return user;
    }

    public void addUser(User user) {
        user.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
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

    public User findUserByEmail (String email) {
        return userRepository.findUserByEmail(email);
    }

    public User findUserByUsername(String username) {
       return userRepository.findUserByUsername(username);
    }
}
