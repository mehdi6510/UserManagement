package com.afifi.usermng.service;

import com.afifi.usermng.exception.ResourceNotFoundException;
import com.afifi.usermng.model.User;

import java.util.List;


public interface UserService {

    User findById(Long userId) throws ResourceNotFoundException;

    List<User> findAll();

    User save(User user);

    User update(Long userId, User userDetails) throws ResourceNotFoundException;

    void deleteById(Long userId) throws ResourceNotFoundException;

}
