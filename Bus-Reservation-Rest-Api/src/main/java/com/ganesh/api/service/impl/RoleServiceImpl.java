package com.ganesh.api.service.impl;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganesh.api.entity.Role;
import com.ganesh.api.repository.RoleRepository;
import com.ganesh.api.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role addRole(Role role) {
        role.setId(null);
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.delete(id);
    	//roleRepository.deleteById(id);
    }

    @Override
    public Role createOrGetRoleUser() {
        Role role = roleRepository.findOneByName("USER");
        if (role == null) {
            Role userRole = new Role();
            userRole.setName("USER");
            role = roleRepository.save(userRole);
        }
        return role;
    }
}

