package jp.co.itmeister.userservice.userservice.service;

import jp.co.itmeister.userservice.userservice.entity.PostEntity;
import jp.co.itmeister.userservice.userservice.entity.PrefectureEntity;
import jp.co.itmeister.userservice.userservice.dto.PostDto;
import jp.co.itmeister.userservice.userservice.entity.CameraEntity;
import jp.co.itmeister.userservice.userservice.entity.CityEntity;
import jp.co.itmeister.userservice.userservice.repository.PostRepository;
import jp.co.itmeister.userservice.userservice.repository.PrefectureRepository;
import jp.co.itmeister.userservice.userservice.repository.CameraRepository;
import jp.co.itmeister.userservice.userservice.repository.CityRepository;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.UUID;
import java.math.BigDecimal;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;


@Service
public class PostService {
    
    private final PostRepository postRepository;
    private final CityRepository cityRepository;
    private final CameraRepository cameraRepository;
    private final PrefectureRepository prefectureRepository;
    private final GeometryFactory geometryFactory;

    private static final int MAX_POSTS = 60;

    @Autowired
    public PostService(PostRepository postRepository, CityRepository cityRepository, CameraRepository cameraRepository, PrefectureRepository prefectureRepository) {
        this.postRepository = postRepository;
        this.cityRepository = cityRepository;
        this.cameraRepository = cameraRepository;
        this.prefectureRepository = prefectureRepository;
        this.geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
    }

    //全件取得 (Max MAX_POSTS件)
    public List<PostDto> findRecentPosts() {
        List<PostEntity> recentPosts =  findPostsWithLimit(MAX_POSTS);
        return recentPosts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //都道府県でフィルター、MAX_POSTS件未満のときはid小さいものを補完する
    public List<PostDto> findByPrefecture(Short prefectureId) {
        List<CityEntity> cities = cityRepository.findByPrefectureId(prefectureId);
        List<PostEntity> posts = new ArrayList<>();

        //都道府県で不一致
        if(cities.isEmpty()) {
            List<PostEntity> recentPosts =  findPostsWithLimit(MAX_POSTS);
            return recentPosts.stream().map(this::convertToDto).collect(Collectors.toList());
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

        return posts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<PostDto> showPost (Long id) {
        return postRepository.findById(id).map(this::convertToDto);
    }    

    //post投稿
    @Transactional
    public PostDto createPost (PostDto post) {
        //prefecture取得
        PrefectureEntity prefecture = prefectureRepository.findById(post.getPrefecture())
            .orElseThrow(() -> new EntityNotFoundException("Prefecture not found with id: " + post.getPrefecture()));

        //city重複チェック
        CityEntity city = cityRepository.findByPrefectureIdAndName(post.getPrefecture() , post.getCityName())
                .orElseGet(() -> {
                    CityEntity newCity = new CityEntity();
                    newCity.setPrefecture(prefecture);
                    newCity.setName(post.getCityName());
                    return cityRepository.save(newCity);
                });

        //camera重複チェック
        CameraEntity camera = cameraRepository.findByBrandAndName(post.getBrand(), post.getCameraName())
                .orElseGet(() -> {
                    CameraEntity newCamera = new CameraEntity();
                    newCamera.setBrand(post.getBrand());
                    newCamera.setName(post.getCameraName());
                    return cameraRepository.save(newCamera);
                });

        //latlng
        Point point = geometryFactory.createPoint(
            new Coordinate(post.getLongitude(), post.getLatitude())
        );

        PostEntity newPost = convertToEntity(post, city, camera, point);
        PostEntity createdPost =  postRepository.save(newPost);

        PostDto response = convertToDto(createdPost);

        return response;
    }

    public List<PostDto> getNearbyPost (double latitude , double longitude , double zoom) {

        //mapのアップ度応じて件数を変えるため
        Integer limit = calculatePostLimit(zoom);
        //mapのアップ度応じて検索範囲を変えるため
        double searchRadius = calculateSearchRadius(zoom);

        List<PostEntity> nearbyPosts = postRepository.findNearbyPosts(latitude, longitude, searchRadius, limit);

        List<PostDto> response = nearbyPosts.stream().map(this::convertToDto).collect(Collectors.toList());

        return response;
    }


    //件数制限をかける取得メソッド
    private List<PostEntity> findPostsWithLimit(int limit ) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by("id").descending());
        return postRepository.findAll(pageable).getContent();
    }

    //dto変換関数
    private PostDto convertToDto (PostEntity entity) {

        PostDto dto = new PostDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setUrl(entity.getUrl());
        dto.setTitle(entity.getTitle());
        dto.setPrefecture(entity.getCity().getPrefecture().getId());
        dto.setCityName(entity.getCity().getName());
        dto.setDescription(entity.getDescription());
        dto.setBrand(entity.getCamera().getBrand());
        dto.setCameraName(entity.getCamera().getName());
        dto.setLatitude((entity.getLatlng().getY()));
        dto.setLongitude((entity.getLatlng().getX()));
        dto.setSnapTime(entity.getSnapTime());
        dto.setAngle(entity.getAngle());
        dto.setIso(entity.getIso());
        dto.setFValue(entity.getFValue());
        dto.setShutterSpeed(entity.getShutterSpeed());

        return dto;
    }

    //Entity変換関数
    private PostEntity convertToEntity(PostDto post, CityEntity city, CameraEntity camera, Point point) {
        PostEntity newPost = new PostEntity();
        newPost.setUid(UUID.randomUUID());
        newPost.setUserId(post.getUserId());
        newPost.setUrl(post.getUrl());
        newPost.setTitle(post.getTitle());
        newPost.setCity(city);
        newPost.setDescription(post.getDescription());
        newPost.setCamera(camera);
        newPost.setLatlng(point);
        newPost.setSnapTime(post.getSnapTime());
        newPost.setAngle(post.getAngle());
        newPost.setIso(post.getIso());
        newPost.setFValue(post.getFValue());
        newPost.setShutterSpeed(post.getShutterSpeed());
        
        return newPost;
    }

    private double calculateSearchRadius (double zoom) {
        return 10000000.00;
    }

    private Integer calculatePostLimit (double zoom) {
        if(zoom <= 5) return 10;
        if(zoom <= 10) return 20;
        if(zoom <= 15) return 50;
        else return 100;
    }
}
