package jp.co.itmeister.userservice.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.persistence.EntityNotFoundException;
import jp.co.itmeister.userservice.userservice.dto.UserResponseDto;
import jp.co.itmeister.userservice.userservice.dto.UserUpdateRequestDto;
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
        Optional<UserResponseDto> userOptional = userService.showUser(id);

        if(userOptional.isPresent()) {
            return responseBuilder.buildSuccessResponse(userOptional.get());
        } else {
            return responseBuilder.buildErrorResponse("User was not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{user_id}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String , Object>> updateUser (
        @PathVariable("user_id") Long id ,
        @RequestPart(value = "user_data") String editUserJson,
        @RequestPart(value = "file" , required = false) MultipartFile file
        ) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            UserUpdateRequestDto editUser = objectMapper.readValue(editUserJson, UserUpdateRequestDto.class);

            UserResponseDto editedUser = userService.updateUser(id, editUser , file);
            return responseBuilder.buildSuccessResponse(editedUser);
        } catch (EntityNotFoundException e) {
            return responseBuilder.buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return responseBuilder.buildErrorResponse(e.getMessage(), HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return responseBuilder.buildErrorResponse("User udpate falied." + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}