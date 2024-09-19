package jp.co.itmeister.userservice.userservice.service;

import jp.co.itmeister.userservice.userservice.dto.SignupRequestDto;
import jp.co.itmeister.userservice.userservice.dto.UserResponseDto;
import jp.co.itmeister.userservice.userservice.dto.UserUpdateRequestDto;
import jp.co.itmeister.userservice.userservice.entity.UserEntity;
import jp.co.itmeister.userservice.userservice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository , PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Optional<UserEntity> showUser(Long id)  {

        return userRepository.findById(id);
    }

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

   public UserResponseDto authenticateUser(String email, String password) {
        // ユーザーをメールアドレスで検索
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException());

            // パスワードを比較（ハッシュ化されたパスワードと入力されたパスワード）
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new IllegalArgumentException();
            }

            return new UserResponseDto(user);

    }

    public UserResponseDto signupUser (SignupRequestDto requestUser)  {
        
        //すでに登録済み メアド
        if(userRepository.findByEmail(requestUser.getEmail()).isPresent()) {
            throw new IllegalArgumentException("This email is already exists.");
        }

        //ユーザネームをランダム生成
        Short userNameLength = 12;
        String userName = generateRandomString(userNameLength);
        //重複チェック
        while(userRepository.findByUserName(userName).isPresent()) {
            userName = generateRandomString(userNameLength);
        }

        UserEntity newUser = new UserEntity();
        newUser.setDisplayName(requestUser.getDisplayName());
        newUser.setEmail(requestUser.getEmail());
        newUser.setUserName(userName);
        newUser.setPassword(passwordEncoder.encode(requestUser.getPassword()));
        newUser.setPrefecture(requestUser.getPrefecture());

        UserEntity signupedUser = userRepository.save(newUser);
        UserResponseDto response = new UserResponseDto(signupedUser);

        return response;
    }

    public UserResponseDto updateUser (Long id ,UserUpdateRequestDto requestUser) {
        //idチェック
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found."));

        //ボディにあったらset
        if(requestUser.getDisplayName() != null) {
            user.setDisplayName(requestUser.getDisplayName());;
        }

        if(requestUser.getUserName() != null) {
            user.setUserName(requestUser.getUserName());
        }

        if(requestUser.getEmail() != null) {
            user.setEmail(requestUser.getEmail());
        }

        if(requestUser.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(requestUser.getPassword()));
        }

        if(requestUser.getPrefecture() != null) {
            user.setPrefecture(requestUser.getPrefecture());
        }

        if(requestUser.getIconUrl() != null) {
            user.setIconUrl(requestUser.getIconUrl());
        }

        UserEntity updatedUser = userRepository.save(user);
        UserResponseDto response = new UserResponseDto(updatedUser);
        return response;
    }

     private static String generateRandomString(final int length) {
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder sb = new StringBuilder();
            Random random = new Random();

            for (int i = 0; i < length; i++) {
                sb.append(chars.charAt(random.nextInt(chars.length())));
            }
            return sb.toString();
        };
}