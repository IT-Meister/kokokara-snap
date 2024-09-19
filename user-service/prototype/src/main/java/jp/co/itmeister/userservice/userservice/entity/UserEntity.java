package jp.co.itmeister.userservice.userservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name="users")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "display_name", nullable = false, length = 64)
    private String displayName;

    @Column(name = "user_name", nullable = false, length = 128, unique = true)
    private String userName;

    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @NotEmpty(message = "Password is required")
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "prefecture", nullable = false)
    private Short prefecture;

    @Column(name = "icon_url" , nullable = true)
    private String iconUrl;

    // Getter & Setter for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Short getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(Short prefecture) {
        this.prefecture = prefecture;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }


}