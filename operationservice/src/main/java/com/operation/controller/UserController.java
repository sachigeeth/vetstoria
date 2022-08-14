package com.operation.controller;

import com.operation.entity.User;
import com.operation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        return this.userService.createUser(user);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateUser(@Valid @RequestBody User user) {
        return this.userService.updateUser(user);
    }

    @DeleteMapping("{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> deleteUser(@PathVariable("userId") Integer userId) {
        return this.userService.deleteUser(userId);
    }

    @GetMapping("/getUserData")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUserData(@RequestParam("username") String username) {
        return this.userService.getUserData(username);
    }
}
