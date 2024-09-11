package jp.co.itmeister.userservice.userservice.service;

import jp.co.itmeister.userservice.userservice.entity.UserEntity;
import jp.co.itmeister.userservice.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


public UserEntity showUser(Long id) {
    return userRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
}
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }
}