package jp.co.itmeister.userservice.userservice.repository;

import jp.co.itmeister.userservice.userservice.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

}