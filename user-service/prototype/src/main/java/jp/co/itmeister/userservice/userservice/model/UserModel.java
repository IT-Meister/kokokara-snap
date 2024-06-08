package jp.co.itmeister.userservice.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name="users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    @Column(name = "user_name" , unique = true)
    @Size(min = 5 , max = 25)
    private String userName;

    @NotBlank
    private String password;

    @Email
    @NotBlank
    @Size(max = 255)
    private String email;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @CreatedDate
    @Column(name = "created_at" , updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;

    // getter & setter
    public Long getUserId () {
        return this.userId = this.userId;
    }

    public void setUserId (Long userId) {
        this.userId = userId;
    }

    public String getUserName () {
        return this.userName;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }

    public String getPassword () {
        return this.password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getEmail () {
        return this.email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

}