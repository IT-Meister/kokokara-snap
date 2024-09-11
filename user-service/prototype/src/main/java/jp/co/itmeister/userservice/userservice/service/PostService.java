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
import org.springframework.stereotype.Service;


@Service
public class PostService {
    
    private final PostRepository postRepository;
    private final CityRepository cityRepository;

    @Autowired
    public PostService(PostRepository postRepository, CityRepository cityRepository) {
        this.postRepository = postRepository;
        this.cityRepository = cityRepository;
    }

    //全件取得
    public List<PostEntity> findAllPosts() {
        return postRepository.findAll();
    }

    public List<PostEntity> findByPrefecture(Integer prefectureId) {
        List<CityEntity> cities = cityRepository.findByPrefectureId(prefectureId);
        if(cities.isEmpty()) {
            return new ArrayList<>();
        }
       List<Integer> cityIds = cities.stream().map(CityEntity::getId).collect(Collectors.toList());
        return postRepository.findByCityIdIn(cityIds);
    }

    public PostEntity showPost (Long id) {
        Optional<PostEntity> post =  postRepository.findById(id);
         return post.orElseThrow();
    }    
}
