package jp.co.itmeister.userservice.userservice.controller;

import jp.co.itmeister.userservice.userservice.entity.PostEntity;
import jp.co.itmeister.userservice.userservice.service.PostService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    
    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
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
    public ResponseEntity<PostEntity> showPost(@PathVariable("post_id") Long id) {
            PostEntity post = postService.showPost(id);
            return ResponseEntity.ok(post);
    }
}