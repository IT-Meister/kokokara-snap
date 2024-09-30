package jp.co.itmeister.userservice.userservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jp.co.itmeister.userservice.userservice.entity.PostEntity;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findByCityIdIn(List<Integer> citiIds);

    List<PostEntity> findByIdNotIn(List<Long> ids , Pageable pageable);

@Query(value = "SELECT p FROM PostEntity p " +
               "WHERE function('ST_Within', " +
               "function('ST_GeomFromWKB', p.latlng), " +
               "function('ST_SetSRID', function('ST_MakeEnvelope', :westLon, :southLat, :eastLon, :northLat), 4326)) = true " +
               "ORDER BY function('ST_Distance', " +
               "function('ST_GeomFromWKB', p.latlng), " +
               "function('ST_SetSRID', function('ST_MakePoint', :centerLon, :centerLat), 4326)) " +
               "LIMIT :limit")
        List<PostEntity> findNearbyPosts(        
        @Param("northLat") double northLat,
        @Param("southLat") double southLat,
        @Param("eastLon") double eastLon,
        @Param("westLon") double westLon,
        @Param("centerLat") double centerLat,
        @Param("centerLon") double centerLon,
        @Param("limit") int limit);
}