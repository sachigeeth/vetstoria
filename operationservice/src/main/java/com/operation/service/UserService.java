package com.operation.service;

import com.operation.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    ResponseEntity createUser(User user);

    ResponseEntity updateUser(User user);

    ResponseEntity<User> deleteUser(Integer userId);

    User getUserData(String userName);
}
