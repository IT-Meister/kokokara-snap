package jp.co.itmeister.userservice.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.itmeister.userservice.userservice.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
         Optional<UserEntity> findByEmail(String email);
         Optional<UserEntity> findByUserName(String userName);
}