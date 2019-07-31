package com.afifi.usermng.service.impl;

import com.afifi.usermng.exception.ResourceNotFoundException;
import com.afifi.usermng.model.User;
import com.afifi.usermng.repository.UserRepository;
import com.afifi.usermng.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<User> findById(Long userId) {
        logger.info("Try to find user with this id : {}", userId);
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User not found for this id :: " + userId));

        logger.info("User loaded. User id : {}", userId);
        logger.debug("Loaded user detail: {}", user);
        return Mono.just(user);
    }

    @Override
    public Flux<User> findAll() {
        logger.info("Try to load all users.");
        List<User> users = new ArrayList<>(userRepository.findAll());
        logger.info("Users has been loaded. Size of result : {}", users.size());
        logger.debug("Loaded users detail: {}", users);
        return Flux.fromIterable(users);
    }

    @Override
    public Mono<User> save(User user) {
        logger.info("Try to save user : {}", user);
        userRepository.save(user);
        logger.info("User saved : {}", user);
        return Mono.just(user);
    }

    @Override
    public Mono<User> update(User updatingUser) {
        logger.info("Try to update user with this user id: {} and new detail: {}", updatingUser.getId(), updatingUser);

        User loadedUser = userRepository.findById(updatingUser.getId()).orElseThrow(() ->
                new ResourceNotFoundException("User not found for this id :: " + updatingUser.getId()));
        logger.info("Existing user data id db has been loaded with this details : {}", loadedUser);

        // TODO : using mapper and check usrname and password
        loadedUser.setTitle(updatingUser.getTitle());
        loadedUser.setFirstName(updatingUser.getFirstName());
        loadedUser.setLastName(updatingUser.getLastName());
        loadedUser.setUsername(updatingUser.getUsername());
        loadedUser.setPassword(updatingUser.getPassword());
        loadedUser.setCellPhone(updatingUser.getCellPhone());
        loadedUser.setEmail(updatingUser.getEmail());
        loadedUser.setIsAdmin(updatingUser.getIsAdmin());

        final User updatedUser = userRepository.save(loadedUser);
        logger.info("User updated with this details : {}", updatedUser);

        return Mono.just(updatedUser);
    }

    @Override
    public Mono<Void> deleteById(Long userId) {
        logger.info("Try to delete user with this user id: {}", userId);
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User not found for this id :: " + userId));
        logger.info("Existing user has been loaded with this details : {}", user);

        userRepository.delete(user);
        logger.info("User deleted. Removing Date : {}", new Date());
        return Mono.empty();
    }

}
