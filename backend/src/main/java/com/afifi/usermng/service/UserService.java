package com.afifi.usermng.service;

import com.afifi.usermng.exception.ResourceNotFoundException;
import com.afifi.usermng.model.User;

import java.util.List;


public interface UserService {

    User findById(String userId) throws ResourceNotFoundException;

    List<User> findAll();

    User save(User user);

    User update(String userId, User userDetails) throws ResourceNotFoundException;

    void deleteById(String userId) throws ResourceNotFoundException;

}
