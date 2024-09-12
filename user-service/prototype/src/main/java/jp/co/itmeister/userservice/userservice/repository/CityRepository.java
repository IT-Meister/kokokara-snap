package jp.co.itmeister.userservice.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jp.co.itmeister.userservice.userservice.entity.CityEntity;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {
    // 都道府県IDに一致するCityを取得
    List<CityEntity> findByPrefectureId(Integer prefectureId);
}