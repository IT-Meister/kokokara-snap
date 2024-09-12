package jp.co.itmeister.userservice.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jp.co.itmeister.userservice.userservice.entity.UserEntity;
import jp.co.itmeister.userservice.userservice.responseBuilder.ResponseBuilder;
import jp.co.itmeister.userservice.userservice.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final ResponseBuilder responseBuilder;

    @Autowired
    public UserController(UserService userService , ResponseBuilder responseBuilder) {
        this.userService = userService;
        this.responseBuilder = responseBuilder;
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> showUser(@PathVariable("user_id") Long id) {
        Optional<UserEntity> userOptional = userService.showUser(id);

        if(userOptional.isPresent()) {
            return responseBuilder.buildSuccessResponse(userOptional.get());
        } else {
            return responseBuilder.buildErrorResponse("User was not found.", HttpStatus.NOT_FOUND);
        }
    }


}