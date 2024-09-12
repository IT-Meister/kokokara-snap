package jp.co.itmeister.userservice.userservice.service;

import jp.co.itmeister.userservice.userservice.entity.UserEntity;
import jp.co.itmeister.userservice.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository , PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


public UserEntity showUser(Long id) {
    return userRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
}
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

   public Optional<UserEntity> authenticateUser(String email, String password) {
        // ユーザーをメールアドレスで検索
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            // パスワードを比較（ハッシュ化されたパスワードと入力されたパスワード）
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);  // 認証成功
            }
        }
        return Optional.empty();  // 認証失敗
    }

}