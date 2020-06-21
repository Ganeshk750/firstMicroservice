package com.ganesh.api.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ganesh.api.entity.User;

public interface UserService extends UserDetailsService {
    User addUser(UserDto userDto);
    User getUserById(Long id);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(Long id);
    void activateEmail(String activationHash);
    void sendConfirmationEmailToUser(User user, String baseUrl);

}
