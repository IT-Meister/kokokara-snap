package jp.co.itmeister.userservice.userservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.validation.constraints.NotEmpty;

import java.time.ZonedDateTime;

@Entity
@Table(name="users")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Display name is required")
    @Column(name = "display_name", nullable = false, length = 64)
    private String displayName;

    @NotEmpty(message = "User name is required")
    @Column(name = "user_name", nullable = false, length = 128, unique = true)
    private String userName;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email is required")
    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @NotEmpty(message = "Password is required")
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @ManyToOne
    @JoinColumn(name = "prefecture", referencedColumnName = "id")
    private PrefectureEntity prefecture;

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

    public PrefectureEntity getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(PrefectureEntity prefecture) {
        this.prefecture = prefecture;
    }
}