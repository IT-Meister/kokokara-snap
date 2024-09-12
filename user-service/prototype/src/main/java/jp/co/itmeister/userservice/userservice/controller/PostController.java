package jp.co.itmeister.userservice.userservice.controller;

import jp.co.itmeister.userservice.userservice.entity.PostEntity;
import jp.co.itmeister.userservice.userservice.responseBuilder.ResponseBuilder;
import jp.co.itmeister.userservice.userservice.service.PostService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public ResponseEntity<List<PostEntity>> indexPost(@RequestParam(value = "prefecture", required = false) Integer prefecture) {
        List<PostEntity> posts;
        if (prefecture != null) {
            // 都道府県指定あり
            posts = postService.findByPrefecture(prefecture);
        } else {
            // 都道府県指定なし
            posts = postService.findAllPosts();
        }
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{post_id}")
    public ResponseEntity<Map<String, Object>> showPost(@PathVariable("post_id") Long id) {
            Optional<PostEntity> post = postService.showPost(id);

            if(post.isPresent()){
                return responseBuilder.buildSuccessResponse(post.get());
            } else {
                return responseBuilder.buildErrorResponse("Post not found.", HttpStatus.NOT_FOUND);
            }
    }
}