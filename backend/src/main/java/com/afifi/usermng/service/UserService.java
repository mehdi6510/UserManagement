package com.afifi.usermng.service;

import com.afifi.usermng.exception.ResourceNotFoundException;
import com.afifi.usermng.model.User;

import java.util.List;

public interface UserService {

    User getUserById(Long userId) throws ResourceNotFoundException;

    List<User> getAllUsers();

    User createUser(User user);

    User updateUser(Long userId, User userDetails) throws ResourceNotFoundException;

    void deleteUser(Long userId) throws ResourceNotFoundException;

}
