package jp.co.itmeister.userservice.userservice.controller;

import jakarta.validation.Valid;
import jp.co.itmeister.userservice.userservice.entity.UserEntity;
import jp.co.itmeister.userservice.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    public final UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserEntity> showUser (@PathVariable("user_id") Long id) {
        UserEntity user = userService.showUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public UserEntity createUser (@Valid @RequestBody UserEntity user) {
        return userService.createUser(user);
    }

}