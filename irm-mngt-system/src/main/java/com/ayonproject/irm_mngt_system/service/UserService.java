package com.ayonproject.irm_mngt_system.service;

import com.ayonproject.irm_mngt_system.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    boolean deleteUser(Long id);

    User updateUser(Long id, User user);

    long countUsers();
}
