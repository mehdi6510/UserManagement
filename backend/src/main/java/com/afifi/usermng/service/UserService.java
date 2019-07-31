package com.afifi.usermng.service;

import com.afifi.usermng.entity.model.User;

import java.util.List;


public interface UserService {

    User findById(Long userId);

    List<User> findAll();

    User save(User user);

    User update(Long userId, User userDetails);

    void deleteById(Long userId);

}
