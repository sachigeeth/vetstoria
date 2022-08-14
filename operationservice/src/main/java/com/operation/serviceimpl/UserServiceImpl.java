package com.operation.serviceimpl;

import com.operation.entity.User;
import com.operation.repository.UserRepository;
import com.operation.service.UserService;
import com.operation.util.MasterDataStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity createUser(User user) {
        ResponseEntity responseEntity;
        User dbUser = this.userRepository.findByUsernameContainsIgnoreCaseAndStatusNot(user.getUsername(), MasterDataStatus.DELETED.getStatusSeq());
        User dbUserEmail = this.userRepository.findByEmailContainsIgnoreCaseAndStatusNot(user.getEmail(), MasterDataStatus.DELETED.getStatusSeq());
        if (dbUser != null) {
            responseEntity = new ResponseEntity<>("Username already exist", HttpStatus.BAD_REQUEST);
        } else if (dbUserEmail != null) {
            responseEntity = new ResponseEntity<>("Email already exist", HttpStatus.BAD_REQUEST);
        } else {
            String originalPassword = user.getPassword().trim();
            String password = "{bcrypt}" + BCrypt.hashpw(originalPassword, BCrypt.gensalt());
            user.setPassword(password);
            user.setUsername(user.getUsername().trim());
            user.setEmail(user.getEmail().toLowerCase().trim());
            this.userRepository.save(user);
            responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @Override
    @Transactional
    public ResponseEntity updateUser(User user) {
        ResponseEntity responseEntity;
        Optional<User> dbUser = this.userRepository.findById(user.getUserId());
        if (dbUser.isPresent()) {
            if (dbUser.get().equals(user)) {
                responseEntity = new ResponseEntity<>(dbUser.get(), HttpStatus.NOT_MODIFIED);
            } else {
                this.userRepository.save(user);
                responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
            }
        } else {
            responseEntity = new ResponseEntity<>("Record not found", HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @Override
    @Transactional
    public ResponseEntity<User> deleteUser(Integer userId) {
        Optional<User> dbUser = this.userRepository.findById(userId);
        ResponseEntity<User> responseEntity;
        if (dbUser.isPresent()) {
            dbUser.get().setStatus(MasterDataStatus.DELETED.getStatusSeq());
            dbUser.get().setRoles(null);
            this.userRepository.save(dbUser.get());
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    public User getUserData(String userName) {
        return this.userRepository.findByUsernameIgnoreCase(userName);
    }
}
