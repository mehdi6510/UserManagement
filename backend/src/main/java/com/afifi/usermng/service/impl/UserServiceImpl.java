package com.afifi.usermng.service.impl;

import com.afifi.usermng.model.User;
import com.afifi.usermng.repository.UserRepository;
import com.afifi.usermng.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(String id) {
        User user = userRepository.findById(id).get();
        System.err.println("find : " + user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        System.err.println("Save : " + user);
        return userRepository.save(user);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
