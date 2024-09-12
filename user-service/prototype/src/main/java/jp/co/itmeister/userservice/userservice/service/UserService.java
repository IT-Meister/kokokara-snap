package jp.co.itmeister.userservice.userservice.service;

import jp.co.itmeister.userservice.userservice.dto.UserResponseDto;
import jp.co.itmeister.userservice.userservice.entity.UserEntity;
import jp.co.itmeister.userservice.userservice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

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

    public UserResponseDto signupUser (UserEntity signupRequestUser) throws Exception {

        //すでに登録済み user_name
        if(userRepository.findByUserName(signupRequestUser.getUserName()).isPresent()) {
            throw new IllegalArgumentException("This user name is already exists.");
        }

        //すでに登録済み メアド
        if(userRepository.findByEmail(signupRequestUser.getEmail()).isPresent()) {
            throw new IllegalArgumentException("This email is already exists.");
        }

        //パスワードをハッシュ化
        signupRequestUser.setPassword(passwordEncoder.encode(signupRequestUser.getPassword()));
        UserEntity signupedUser = userRepository.save(signupRequestUser);
        UserResponseDto response = new UserResponseDto(signupedUser);

        return response;
    }

}