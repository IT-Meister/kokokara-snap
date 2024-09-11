package jp.co.itmeister.userservice.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jp.co.itmeister.userservice.userservice.entity.PostEntity;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    //都道府県に一致するレコードを取得
    List<PostEntity> findByCityIdIn(List<Integer> citiIds);
}