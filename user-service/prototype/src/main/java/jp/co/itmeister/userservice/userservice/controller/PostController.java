package jp.co.itmeister.userservice.userservice.controller;

import jp.co.itmeister.userservice.userservice.dto.PostDto;
import jp.co.itmeister.userservice.userservice.responseBuilder.ResponseBuilder;
import jp.co.itmeister.userservice.userservice.service.PostService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    
    private final PostService postService;
    private final ResponseBuilder responseBuilder;

    @Autowired
    public PostController(PostService postService , ResponseBuilder responseBuilder){
        this.postService = postService;
        this.responseBuilder = responseBuilder;
    }

    @GetMapping("/home")
    public ResponseEntity<Map<String , Object>> indexPost(@RequestParam(value = "prefecture", required = false) Short prefecture) {
        List<PostDto> posts;
        if (prefecture != null) {
            // 都道府県指定あり
            posts = postService.findByPrefecture(prefecture);
        } else {
            // 都道府県指定なし
            posts = postService.findRecentPosts();
        }
        return responseBuilder.buildSuccessResponse(posts);
    }

    @GetMapping("/{post_id}")
    public ResponseEntity<Map<String, Object>> showPost(@PathVariable("post_id") Long id) {
            Optional<PostDto> post = postService.showPost(id);

            if(post.isPresent()){
                return responseBuilder.buildSuccessResponse(post.get());
            } else {
                return responseBuilder.buildErrorResponse("Post was not found.", HttpStatus.NOT_FOUND);
            }
    }

    @PostMapping
    public ResponseEntity<Map<String , Object>> createPost (@Valid @RequestBody PostDto requestPost) {
        try {
            PostDto createdPost = postService.createPost(requestPost);
            return responseBuilder.buildSuccessResponse(createdPost);
        } catch (EntityNotFoundException e) {
            return responseBuilder.buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return responseBuilder.buildErrorResponse("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/map")
    public ResponseEntity<Map<String , Object>> getNearbyPost (@RequestParam double NElatitude , @RequestParam double NElongitude , @RequestParam double SWlatitude , @RequestParam double SWlongitude , @RequestParam double zoom) {
        try {
            List<PostDto> posts = postService.getNearbyPost(NElatitude , NElongitude , NElongitude , SWlongitude , zoom);
            return responseBuilder.buildSuccessResponse(posts);
        } catch (Exception e) {
            return responseBuilder.buildErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}