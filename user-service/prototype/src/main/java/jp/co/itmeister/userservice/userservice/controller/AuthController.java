package jp.co.itmeister.userservice.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jp.co.itmeister.userservice.userservice.entity.UserEntity;
import jp.co.itmeister.userservice.userservice.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService  userService;

    @Autowired
    public AuthController (UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authLogin(@Valid @RequestBody UserEntity user) {
        boolean isAuthenticated = userService.authenticateUser(user.getUserName(), user.getPassword());

        if(isAuthenticated) {
            return ResponseEntity.ok().body("Login successful.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed.");
        }
    }
}
