package jp.co.itmeister.userservice.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jp.co.itmeister.userservice.userservice.dto.UserResponseDto;
import jp.co.itmeister.userservice.userservice.dto.UserUpdateRequestDto;
import jp.co.itmeister.userservice.userservice.entity.UserEntity;
import jp.co.itmeister.userservice.userservice.responseBuilder.ResponseBuilder;
import jp.co.itmeister.userservice.userservice.service.UserService;

import java.util.Optional;
import java.util.Map;

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
    public ResponseEntity<Map<String , Object>> showUser(@PathVariable("user_id") Long id) {
        Optional<UserEntity> userOptional = userService.showUser(id);

        if(userOptional.isPresent()) {
            return responseBuilder.buildSuccessResponse(userOptional.get());
        } else {
            return responseBuilder.buildErrorResponse("User was not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<Map<String , Object>> updateUser (@PathVariable("user_id") Long id , @Valid @RequestBody UserUpdateRequestDto editUser) {
        try {
            UserResponseDto editedUser = userService.updateUser(id, editUser);
            return responseBuilder.buildSuccessResponse(editedUser);
        } catch (EntityNotFoundException e) {
            return responseBuilder.buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return responseBuilder.buildErrorResponse("User udpate falied.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}