package com.ganesh.api.service;

import java.util.List;

import com.ganesh.api.entity.Role;

public interface RoleService {
    Role addRole(Role role);
    List<Role> getAllRoles();
    Role updateRole(Role role);
    void deleteRole(Long id);
    Role createOrGetRoleUser();
}

