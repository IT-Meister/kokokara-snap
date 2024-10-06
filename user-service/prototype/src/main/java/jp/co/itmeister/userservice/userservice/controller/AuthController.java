package jp.co.itmeister.userservice.userservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jp.co.itmeister.userservice.userservice.dto.SignupRequestDto;
import jp.co.itmeister.userservice.userservice.dto.UserResponseDto;
import jp.co.itmeister.userservice.userservice.entity.UserEntity;
import jp.co.itmeister.userservice.userservice.responseBuilder.ResponseBuilder;
import jp.co.itmeister.userservice.userservice.service.UserService;
import jp.co.itmeister.userservice.userservice.Security.JwtUtils;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService  userService;
    private final ResponseBuilder responseBuilder;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController (UserService userService , ResponseBuilder responseBuilder , JwtUtils jwtUtils) {
        this.userService = userService;
        this.responseBuilder = responseBuilder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String , Object>> authLogin(@Valid @RequestBody UserEntity user) {
        try {
            UserResponseDto userResponseDto = userService.authenticateUser(user.getEmail(), user.getPassword());

            ResponseCookie newCookie = jwtUtils.generateJwtCookie(user.getEmail());

            return responseBuilder.buildSuccessResponse(userResponseDto , newCookie);
        } catch (IllegalArgumentException e) {
            return responseBuilder.buildErrorResponse("Email or password is incorrect.", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return responseBuilder.buildErrorResponse("Authentication failed." + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(@Valid @RequestBody SignupRequestDto requestUser) {
        try {
            UserResponseDto signupedUser = userService.signupUser(requestUser);
            
            return responseBuilder.buildSuccessResponse(signupedUser , null);
        } catch (IllegalArgumentException e) {
            return responseBuilder.buildErrorResponse(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return responseBuilder.buildErrorResponse("User registration failed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}