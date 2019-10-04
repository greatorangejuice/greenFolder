package com.blansplatform.controller.entityControllers;

import com.blansplatform.entity.Role;
import com.blansplatform.service.entityServices.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public List<Role> findAllRoles() {
        return roleService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Role findRoleById(@PathVariable Long id) {
        return roleService.findRoleById(id);
    }

    @PostMapping
    public @ResponseBody Response addNewRole(@RequestBody Role role) {
        roleService.addRole(role);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DeleteMapping
    public @ResponseBody Response deleteRole(@RequestBody Role role) {
        roleService.deleteRole(role);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PutMapping
    public @ResponseBody Response updateRole(@RequestBody Role role) {
        roleService.updateRole(role);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

}
