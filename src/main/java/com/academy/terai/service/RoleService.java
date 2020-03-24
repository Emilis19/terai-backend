package com.academy.terai.service;


import com.academy.terai.Model.Account;
import com.academy.terai.Model.Role;
import com.academy.terai.Repository.RoleRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> findById(final String id) {
        return roleRepository.findById(id);
    }

    public Role findByName(final String name) {
        return roleRepository.findByName(name);
    }
    public void updateRole(final Role role, final String id) throws NotFoundException {
        if (!roleRepository.findById(id).isPresent()){
            throw new NotFoundException(id);
        }

        roleRepository.save(role);
    }

    public void deleteRole(final String id) throws NotFoundException {
        if (!roleRepository.findById(id).isPresent()){
            throw new NotFoundException(id);
        }
        roleRepository.deleteById(id);
    }

    public Role addRole(final Role role)  throws NotFoundException {
        if (roleRepository.findByName(role.getName()) != null){
            throw new NotFoundException(role.getName());
        }
        return roleRepository.save(role);
    }

}
