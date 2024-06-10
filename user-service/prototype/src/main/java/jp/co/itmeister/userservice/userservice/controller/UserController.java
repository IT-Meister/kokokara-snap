package jp.co.itmeister.userservice.userservice.controller;

import jakarta.validation.Valid;
import jp.co.itmeister.userservice.userservice.model.UserModel;
import jp.co.itmeister.userservice.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public UserModel createUser (@Valid @RequestBody UserModel user) {
        return userService.createUser(user);
    }

}