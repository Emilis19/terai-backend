package com.academy.terai.Controller;

import com.academy.terai.Model.Role;
import com.academy.terai.service.RoleService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleResource {
    private final RoleService roleService;

    @Autowired
    public RoleResource(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public Optional<Role> getRoleById(@PathVariable String id) throws NotFoundException {
        return roleService.findById(id);
    }

    @PostMapping(value = "/")
    ResponseEntity<Role> createRole(@RequestBody Role role) throws NotFoundException {
        roleService.addRole(role);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Role> updateRole(@RequestBody Role role, @PathVariable String id) throws NotFoundException {
        roleService.updateRole(role, id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> deleteRole(@PathVariable String id) throws NotFoundException {
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
