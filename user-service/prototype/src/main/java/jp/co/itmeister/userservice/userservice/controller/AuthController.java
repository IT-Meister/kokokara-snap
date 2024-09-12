package jp.co.itmeister.userservice.userservice.controller;

import java.util.Optional;
import java.util.Map;
import java.util.LinkedHashMap;

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
}