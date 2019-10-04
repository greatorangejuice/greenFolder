package com.blansplatform.service.entityServices;

import com.blansplatform.entity.Role;
import com.blansplatform.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findRoleById(Long id) {
        Role role = roleRepository.findRoleById(id);
        if (role == null){
            throw new EntityNotFoundException("Role not found");
        }
        return role;
    }

    public void addRole(Role role) {
        roleRepository.save(role);
    }

    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    public void updateRole(Role role) {
        roleRepository.save(role);
    }
}
