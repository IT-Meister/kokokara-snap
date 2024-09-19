package jp.co.itmeister.userservice.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import jp.co.itmeister.userservice.userservice.entity.CameraEntity;

@Repository
public interface CameraRepository extends JpaRepository<CameraEntity , Long >  {
        //brand と nameで重複チェック
        Optional<CameraEntity> findByBrandAndName(String brand , String name);
}