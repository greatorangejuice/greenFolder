/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service.serviceImpl;

import com.blansplatform.entity.Role;
import com.blansplatform.repository.RoleRepository;
import com.blansplatform.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleById(Long id) {
        Role role = roleRepository.findRoleById(id);
        if (role == null){
            throw new EntityNotFoundException("Role not found");
        }
        return role;
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void updateRole(Role role) {
        roleRepository.save(role);
    }
}
