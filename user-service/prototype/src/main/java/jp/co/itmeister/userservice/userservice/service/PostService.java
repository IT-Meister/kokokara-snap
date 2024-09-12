package jp.co.itmeister.userservice.userservice.service;

import jp.co.itmeister.userservice.userservice.entity.PostEntity;
import jp.co.itmeister.userservice.userservice.entity.CityEntity;
import jp.co.itmeister.userservice.userservice.repository.PostRepository;
import jp.co.itmeister.userservice.userservice.repository.CityRepository;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PostService {
    
    private final PostRepository postRepository;
    private final CityRepository cityRepository;

    private static final int MAX_POSTS = 60;

    @Autowired
    public PostService(PostRepository postRepository, CityRepository cityRepository) {
        this.postRepository = postRepository;
        this.cityRepository = cityRepository;
    }

    //全件取得 (Max MAX_POSTS件)
    public List<PostEntity> findRecentPosts() {
        return findPostsWithLimit(MAX_POSTS);
    }

    //都道府県でフィルター、MAX_POSTS件未満のときはid小さいものを補完する
    public List<PostEntity> findByPrefecture(Integer prefectureId) {
        List<CityEntity> cities = cityRepository.findByPrefectureId(prefectureId);
        List<PostEntity> posts = new ArrayList<>();

        //都道府県で不一致
        if(cities.isEmpty()) {
            return findPostsWithLimit(MAX_POSTS);
        }

        //取得したcity_id
       List<Integer> cityIds = cities.stream().map(CityEntity::getId).collect(Collectors.toList());
        posts = postRepository.findByCityIdIn(cityIds);

        //MAX_POSTS件未満なので補完する
        if (posts.size() < MAX_POSTS) {
            Pageable pageable = PageRequest.of(0, MAX_POSTS - posts.size(), Sort.by("id").descending());

            List<Long> currentPostIds = posts.stream().map(PostEntity::getId).collect(Collectors.toList());
            List<PostEntity> additionalPosts = postRepository.findByIdNotIn(currentPostIds, pageable);
            posts.addAll(additionalPosts);
        }

        return posts;
    }

    public Optional<PostEntity> showPost (Long id) {
        return postRepository.findById(id);
    }    


    //件数制限をかける取得メソッド
    private List<PostEntity> findPostsWithLimit(int limit ) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by("id").descending());
        return postRepository.findAll(pageable).getContent();
    }
}
