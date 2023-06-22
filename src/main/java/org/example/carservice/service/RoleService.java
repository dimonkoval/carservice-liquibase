package org.example.carservice.service;

import org.example.carservice.model.Role;

public interface RoleService {
    Role add(Role role);

    Role getByName(String roleName);
}
