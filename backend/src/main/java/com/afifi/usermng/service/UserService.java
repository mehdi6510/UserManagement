package com.afifi.usermng.service;

import com.afifi.usermng.model.User;

import java.util.List;


public interface UserService {

    User findById(String id);

    List<User> findAll();

    User save(User user);

    void deleteById(String id);

}
