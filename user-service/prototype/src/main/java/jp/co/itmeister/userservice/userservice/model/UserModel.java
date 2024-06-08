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
    private Long user_id;

    @NotBlank
    @Column(unique = true)
    @Size(min = 5 , max = 25)
    private String user_name;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Email
    @NotBlank
    @Size(max = 255)
    private String email;

    private Date last_login_time;

    @CreatedDate
    private Date created_at;

    @LastModifiedDate
    private Date updated_at;

}