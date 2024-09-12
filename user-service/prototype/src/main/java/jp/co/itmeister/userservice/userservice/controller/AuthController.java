package jp.co.itmeister.userservice.userservice.controller;

import java.util.Optional;
import java.util.Map;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jp.co.itmeister.userservice.userservice.dto.UserResponseDto;
import jp.co.itmeister.userservice.userservice.entity.UserEntity;
import jp.co.itmeister.userservice.userservice.responseBuilder.ResponseBuilder;
import jp.co.itmeister.userservice.userservice.service.UserService;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService  userService;
    private final ResponseBuilder responseBuilder;

    @Autowired
    public AuthController (UserService userService , ResponseBuilder responseBuilder) {
        this.userService = userService;
        this.responseBuilder = responseBuilder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authLogin(@Valid @RequestBody UserEntity user) {
       Optional<UserEntity> authenticatedUser = userService.authenticateUser(user.getEmail(), user.getPassword());

        if (authenticatedUser.isPresent()) {
            UserEntity userEntity = authenticatedUser.get();
            // ログイン成功時のレスポンス
            Map<String, Object> responseData = new LinkedHashMap<>();
            Map<String, Object> userData = new LinkedHashMap<>();
            userData.put("id", userEntity.getId());
            userData.put("displayName", userEntity.getDisplayName());
            userData.put("userName", userEntity.getUserName());
            userData.put("email", userEntity.getEmail());
            userData.put("prefecture", userEntity.getPrefecture());
            responseData.put("status", "success");
            responseData.put("data", userData);

            return ResponseEntity.ok(responseData);
        } else {
            // ログイン失敗時のレスポンス
            Map<String, Object> responseData = new LinkedHashMap<>();
            responseData.put("status", "error");
            responseData.put("data", "Invalid email or password");
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(@Valid @RequestBody UserEntity signupRequest) {
        try {
            UserResponseDto signedUpUser = userService.signupUser(signupRequest);
            
            return responseBuilder.buildSuccessResponse(signedUpUser);
        } catch (IllegalArgumentException e) {
            return responseBuilder.buildErrorResponse(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return responseBuilder.buildErrorResponse("ユーザー登録に失敗しました", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}