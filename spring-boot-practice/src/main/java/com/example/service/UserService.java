package com.example.service;

import com.example.model.UserModel;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserModel> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserModel edit (UserModel user) {
        return userRepository.edit(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    //認証判定
    public boolean authenticate(String email , String password) {
        return userRepository.findByEmail(email).map(user -> user.getPassword().equals(password)).orElse(false);
    }
}