package com.example.controller;

import com.example.model.UserModel;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping
    public List<UserModel> getAllUsers () {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id) {
        return userService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserModel createUser(@Valid @RequestBody UserModel user) {
        return userService.edit(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser (@PathVariable Long id , @Valid @RequestBody UserModel userDetails) {
        return userService.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            return ResponseEntity.ok(userService.edit(user));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userService.findById(id)
                .map(user -> {
                    userService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping ("/login")
    public ResponseEntity<String> authenticateUser(@RequestParam String email , @RequestParam String password) {
        boolean isAuthenticated = userService.authenticate(email, password);

        if(isAuthenticated) {
            return ResponseEntity.ok("Authentication successful.");
        } else {
            return ResponseEntity.status(401).body("Authentication failed");
        }
    }
}