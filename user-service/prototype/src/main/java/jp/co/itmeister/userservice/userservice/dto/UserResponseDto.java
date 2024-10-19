package jp.co.itmeister.userservice.userservice.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponseDto {
    
    private Long id;
    private String userName;
    private String displayName;
    private String email;
    private Short prefecture;
    private String iconUrl;

    public UserResponseDto(){};

        // Getters
    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public String getDisplayName() { return displayName; }
    public String getEmail() { return email; }
    public Short getPrefecture() { return prefecture; }
    public String getIconUrl() {return iconUrl;}

    public void setId(Long id) { this.id = id; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public void setEmail(String email) { this.email = email; }
    public void setPrefecture(Short prefecture) { this.prefecture = prefecture; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }
}
