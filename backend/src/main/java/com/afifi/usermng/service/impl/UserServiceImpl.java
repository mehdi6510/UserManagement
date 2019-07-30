package com.afifi.usermng.service.impl;

import com.afifi.usermng.exception.ResourceNotFoundException;
import com.afifi.usermng.model.User;
import com.afifi.usermng.repository.UserRepository;
import com.afifi.usermng.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
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
    public User findById(Long userId) throws ResourceNotFoundException {
        logger.info("Try to find user with this id : {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        logger.info("User loaded. User id : {}", userId);
        logger.debug("Loaded user detail: {}", user);
        return user;
    }

    @Override
    public List<User> findAll() {
        logger.info("Try to load all users.");
        List<User> users = new ArrayList<>(userRepository.findAll());
        logger.info("Users has been loaded. Size of result : {}", users.size());
        logger.debug("Loaded users detail: {}", users);
        return users;
    }

    @Override
    public User save(@Valid User user) {
        logger.info("Try to save user : {}", user);
        userRepository.save(user);
        logger.info("User saved : {}", user);
        return user;
    }

    @Override
    public User update(Long userId, @Valid User userDetails) throws ResourceNotFoundException {
        logger.info("Try to update user with this user id: {} and new detail: {}", userId, userDetails);
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User not found for this id :: " + userId));

        logger.info("Existing user data id db has been loaded with this details : {}", user);

        // TODO : using mapper and check usrname and password
        user.setTitle(userDetails.getTitle());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setCellPhone(userDetails.getCellPhone());
        user.setEmail(userDetails.getEmail());
        user.setIsAdmin(userDetails.getIsAdmin());

        final User updatedUser = userRepository.save(user);
        logger.info("User updated with this details : {}", updatedUser);
        return updatedUser;
    }

    @Override
    public void deleteById(Long userId) throws ResourceNotFoundException {
        logger.info("Try to delete user with this user id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        logger.info("Existing user has been loaded with this details : {}", user);

        userRepository.delete(user);
        logger.info("User deleted. Removing Date : {}", new Date());
    }

}
