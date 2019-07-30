package com.afifi.usermng.controller;

import com.afifi.usermng.exception.ResourceNotFoundException;
import com.afifi.usermng.model.User;
import com.afifi.usermng.service.UserService;
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

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(userService.findById(userId));
    }

    @GetMapping(value = "/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping(value = "/users")
    public User createUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(userService.update(userId, userDetails));
    }

    @DeleteMapping(value = "/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        userService.deleteById(userId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
