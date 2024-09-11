package jp.co.itmeister.userservice.userservice.controller;

import jp.co.itmeister.userservice.userservice.entity.PostEntity;
import jp.co.itmeister.userservice.userservice.responseBuilder.ResponseBuilder;
import jp.co.itmeister.userservice.userservice.service.PostService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/{post_id}")
    public ResponseEntity<Map<String, Object>> showPost(@PathVariable("post_id") Long id) {
            PostEntity post = postService.showPost(id);
            return responseBuilder.buildSuccessResponse(post);
    }
}