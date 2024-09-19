package jp.co.itmeister.userservice.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.itmeister.userservice.userservice.entity.PrefectureEntity;

@Repository
public interface PrefectureRepository extends JpaRepository<PrefectureEntity , Short> {
    
}
