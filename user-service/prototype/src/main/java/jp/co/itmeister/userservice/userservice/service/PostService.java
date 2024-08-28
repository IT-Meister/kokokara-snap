package jp.co.itmeister.userservice.userservice.service;

import jp.co.itmeister.userservice.userservice.entity.PostEntity;
import jp.co.itmeister.userservice.userservice.repository.PostRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostService {
    
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Optional<PostEntity> showPost (Long id) {
        return postRepository.findById(id);
    }
}
