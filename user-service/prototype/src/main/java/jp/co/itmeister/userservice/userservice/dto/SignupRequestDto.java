package jp.co.itmeister.userservice.userservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SignupRequestDto {
    private String displayName;
    private String email;
    private String password;
    private Short prefecture;

    // デフォルトコンストラクタ
    public SignupRequestDto() {}

    // 全フィールドを引数に取るコンストラクタ
    public SignupRequestDto(String displayName, String email, String password, Short prefecture) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.prefecture = prefecture;
    }

    // Getters and setters
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Short getPrefecture() { return prefecture; }
    public void setPrefecture(Short prefecture) { this.prefecture = prefecture; }
}