package com.blansplatform.service.entityServices;

import com.blansplatform.entity.User;
import com.blansplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    private final String IF_EMAIL_EXIST = "exist";
    private final String IF_EMAIL_NOT_EXIST = "not exist";

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
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public String checkUserByEmail(String email){
        User user = userRepository.findUserByEmail(email);
        if(user == null){
            return IF_EMAIL_NOT_EXIST;
        }
        return IF_EMAIL_EXIST;
    }
}
