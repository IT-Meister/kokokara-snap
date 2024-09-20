package jp.co.itmeister.userservice.userservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jp.co.itmeister.userservice.userservice.entity.PostEntity;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findByCityIdIn(List<Integer> citiIds);

    List<PostEntity> findByIdNotIn(List<Long> ids , Pageable pageable);

   @Query(value = "SELECT p FROM PostEntity p " +
                   "WHERE function('ST_DWithin', p.latlng, " +
                   "function('ST_SetSRID', function('ST_MakePoint', :longitude, :latitude), 4326), :radius) = true " +
                   "ORDER BY function('ST_Distance', p.latlng, " +
                   "function('ST_SetSRID', function('ST_MakePoint', :longitude, :latitude), 4326))" +
                   "LIMIT :limit")
    List<PostEntity> findNearbyPosts(@Param("latitude") BigDecimal latitude,
                                     @Param("longitude") BigDecimal longitude,
                                     @Param("radius") double searchRadius,
                                     @Param("limit") Integer limit);
}