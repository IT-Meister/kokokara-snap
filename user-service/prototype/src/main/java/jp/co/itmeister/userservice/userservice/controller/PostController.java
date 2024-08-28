package jp.co.itmeister.userservice.userservice.controller;

import jakarta.validation.Valid;
import jp.co.itmeister.userservice.userservice.entity.PostEntity;
import jp.co.itmeister.userservice.userservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    
    public final PostService postService;

    @Autowired
    public PostController (PostService postService){
        this.postService = postService;
    }

    // @GetMapping
}
