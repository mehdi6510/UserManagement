package com.afifi.usermng.service;

import com.afifi.usermng.exception.ResourceNotFoundException;
import com.afifi.usermng.model.User;

import javax.validation.Valid;
import java.util.List;


public interface UserService {

    User findById(Long userId) throws ResourceNotFoundException;

    List<User> findAll();

    User save(@Valid User user);

    User update(Long userId, @Valid User userDetails) throws ResourceNotFoundException;

    void deleteById(Long userId) throws ResourceNotFoundException;

}
