package com.operation.service;

import com.operation.security.payload.request.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface JwtService {
    ResponseEntity<?> authenticate(LoginRequest loginRequest);
}
