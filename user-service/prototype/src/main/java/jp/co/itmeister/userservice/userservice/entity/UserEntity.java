package jp.co.itmeister.userservice.userservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

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

    @Email
    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "prefecture", nullable = false)
    private Short prefecture;

    @Column(name = "last_login_time")
    private ZonedDateTime lastLoginTime;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;
    
    // getter & setter
    // public Long getUserId () {
    //     return this.userId;
    // }

    // public void setUserId (Long userId) {
    //     this.userId = userId;
    // }

    // public String getUserName () {
    //     return this.userName;
    // }

    // public void setUserName (String userName) {
    //     this.userName = userName;
    // }

    // public String getPassword () {
    //     return this.password;
    // }

    // public void setPassword (String password) {
    //     this.password = password;
    // }

    // public String getEmail () {
    //     return this.email;
    // }

    // public void setEmail (String email) {
    //     this.email = email;
    // }

    // public Date getLastLoginTime () {
    //     return this.lastLoginTime;
    // }

    // public void setLastLoginTime (Date lastLoginTime) {
    //     this.lastLoginTime = lastLoginTime;
    // }

    // public Date getCreatedAt () {
    //     return this.createdAt;
    // }

    // public void setCreatedAt (Date createdAt) {
    //     this.createdAt = createdAt;
    // }

    // public Date getUpdatedAt () {
    //     return this.updatedAt;
    // }

    // public void setUpdatedAt (Date updatedAt) {
    //     this.updatedAt = updatedAt;
    // }

    // @PrePersist
    // protected void onCreate() {
    //     createdAt = new Date();
    //     updatedAt = new Date();
    // }

    // @PreUpdate
    // protected void onUpdate() {
    //     updatedAt = new Date();
    // }
}