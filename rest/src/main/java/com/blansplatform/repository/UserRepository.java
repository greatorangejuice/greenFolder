package com.blansplatform.repository;

import com.blansplatform.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByEmail(String email);
    List<User> findAll();
    User findUserById(Long id);
}
