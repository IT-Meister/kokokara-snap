package jp.co.itmeister.userservice.userservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserUpdateRequestDto {
    private String displayName;
    private String userName;
    private String email;
    private String password;
    private Short prefecture;
    private String iconUrl;

    public String getDisplayName() {
        return this.displayName;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public Short getPrefecture() {
        return this.prefecture;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }
}
