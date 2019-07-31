package com.afifi.usermng.controller;

import com.afifi.usermng.entity.model.User;
import com.afifi.usermng.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/usermanagement/api")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) {
        logger.info("Receive request to get user by id : {}", userId);
        return ResponseEntity.ok().body(userService.findById(userId));
    }

    @GetMapping(value = "/users")
    public List<User> getAllUsers() {
        logger.info("Receive request to get all users");
        return userService.findAll();
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        logger.info("Receive request to save user : {}", user);
        return ResponseEntity.ok().body(userService.save(user));
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails) {
        logger.info("Receive request to update user with this user id: {} and new detail: {}", userId, userDetails);
        return ResponseEntity.ok(userService.update(userId, userDetails));
    }

    @DeleteMapping(value = "/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) {
        logger.info("Receive request to delete user by id : {}", userId);
        userService.deleteById(userId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
