package com.operation.serviceimpl;

import com.operation.entity.User;
import com.operation.repository.UserRepository;
import com.operation.security.jwt.JwtUtils;
import com.operation.security.payload.request.LoginRequest;
import com.operation.security.payload.response.JwtResponse;
import com.operation.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtServiceImpl implements JwtService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    private final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

    @Autowired
    public JwtServiceImpl(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseEntity<?> authenticate(LoginRequest loginRequest) {
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        String jwt = jwtUtils.generateJwtToken(loginRequest.getUsername());
        User user = this.userRepository.findByUsernameIgnoreCaseAndEnabled(loginRequest.getUsername(), true);
        List<String> roles = user.getRoles().stream()
                .map(role -> role.getRoleName())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                user.getUsername(),
                user.getPassword(),
                roles));
    }
}
