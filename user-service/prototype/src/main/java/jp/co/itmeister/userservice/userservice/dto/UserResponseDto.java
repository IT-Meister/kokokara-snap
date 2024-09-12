package jp.co.itmeister.userservice.userservice.dto;

import jp.co.itmeister.userservice.userservice.entity.UserEntity;

public class UserResponseDto {
    
    private Long id;
    private String userName;
    private String displayName;
    private String email;
    private Short prefecture;

    public UserResponseDto (UserEntity user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.displayName = user.getDisplayName();
        this.email = user.getEmail();
        this.prefecture = user.getPrefecture();
    }

        // Getters
    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public String getDisplayName() { return displayName; }
    public String getEmail() { return email; }
    public Short getPrefecture() { return prefecture; }
}
