package org.example.carservice.service;

import org.example.carservice.model.User;

public interface UserService {
    User add(User user);

    User get(Long id);

    User findByEmail(String email);
}
