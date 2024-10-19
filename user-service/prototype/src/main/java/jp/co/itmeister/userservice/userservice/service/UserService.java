package jp.co.itmeister.userservice.userservice.service;

import jp.co.itmeister.userservice.userservice.dto.SignupRequestDto;
import jp.co.itmeister.userservice.userservice.dto.UserResponseDto;
import jp.co.itmeister.userservice.userservice.dto.UserUpdateRequestDto;
import jp.co.itmeister.userservice.userservice.entity.UserEntity;
import jp.co.itmeister.userservice.userservice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final S3Service s3Service;

    @Autowired
    public UserService(UserRepository userRepository , PasswordEncoder passwordEncoder , S3Service s3Service) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.s3Service = s3Service;
    }


    public Optional<UserResponseDto> showUser(Long id)  {

       return  userRepository.findById(id).map(this::convertToDto);
        
    }

    public UserResponseDto createUser(UserEntity user) {
        UserEntity savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

   public UserResponseDto authenticateUser(String email, String password) {

        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException());

            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new IllegalArgumentException();
            }

            return convertToDto(user);

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

        return convertToDto(signupedUser);
    }

    @Transactional
    public UserResponseDto updateUser (Long id ,UserUpdateRequestDto requestUser , MultipartFile file) throws Exception {

        UserEntity user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found."));

        if(requestUser.getDisplayName() != null) {
            user.setDisplayName(requestUser.getDisplayName());;
        }

        if((requestUser.getUserName() != null) && !user.getUserName().equals(requestUser.getUserName())) {

            if(userRepository.findByUserName(requestUser.getUserName()).isPresent()) {
                throw new IllegalArgumentException("This user name is already exists.");
            }
            user.setUserName(requestUser.getUserName());
        }

        if((requestUser.getEmail() != null) && !user.getEmail().equals(requestUser.getEmail())) {

            if(userRepository.findByEmail(requestUser.getEmail()).isPresent()) {
                throw new IllegalArgumentException("This email is already exists.");
            }
            user.setEmail(requestUser.getEmail());
        }

        if(requestUser.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(requestUser.getPassword()));
        }

        if(requestUser.getPrefecture() != null) {
            user.setPrefecture(requestUser.getPrefecture());
        }

        if(file != null && !file.isEmpty()) {
            String iconUrl = s3Service.uploadFile(file, UUID.randomUUID(), "profile");
            user.setIconUrl(iconUrl);
        }

        UserEntity updatedUser = userRepository.save(user);
        return convertToDto(updatedUser);
    }

    private UserResponseDto convertToDto (UserEntity user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setDisplayName(user.getDisplayName());
        dto.setEmail(user.getEmail());
        dto.setPrefecture(user.getPrefecture());
        //cloudfrontのフルパスにする
        dto.setIconUrl(s3Service.getFullFileUrl(user.getIconUrl()));

        return dto;
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