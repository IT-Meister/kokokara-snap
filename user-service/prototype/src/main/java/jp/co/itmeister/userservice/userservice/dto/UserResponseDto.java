package jp.co.itmeister.userservice.userservice.dto;

import jp.co.itmeister.userservice.userservice.entity.UserEntity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)public class UserResponseDto {
    
    private Long id;
    private String userName;
    private String displayName;
    private String email;
    private Short prefecture;
    private String iconUrl;

    public UserResponseDto (UserEntity user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.displayName = user.getDisplayName();
        this.email = user.getEmail();
        this.prefecture = user.getPrefecture();
        this.iconUrl = user.getIconUrl();
    }

        // Getters
    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public String getDisplayName() { return displayName; }
    public String getEmail() { return email; }
    public Short getPrefecture() { return prefecture; }
    public String getIconUrl() {return iconUrl;}
}
