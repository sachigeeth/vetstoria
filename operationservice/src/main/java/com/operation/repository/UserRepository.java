package com.operation.repository;

import com.operation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByUsernameContainsIgnoreCaseAndStatusNot(String username, Integer status);

    User findByEmailContainsIgnoreCaseAndStatusNot(String email, Integer status);

    User findByUsernameIgnoreCase(String userName);

    User findByUsernameIgnoreCaseAndEnabled(String username, boolean b);
}
