package com.blansplatform.repository;

import com.blansplatform.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {

    List<Role> findAll();
    Role findRoleById(Long id);

}
