/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service;

import com.blansplatform.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findRoleById(Long id);

    void addRole(Role role);

    void deleteRole(Role role);

    void updateRole(Role role);

}
