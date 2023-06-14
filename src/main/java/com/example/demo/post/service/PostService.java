package com.example.demo.post.service;

import com.example.demo.common.domain.exception.ResourceNotFoundException;
import com.example.demo.common.service.port.ClockHolder;
import com.example.demo.post.domain.Post;
import com.example.demo.post.domain.PostCreate;
import com.example.demo.post.domain.PostUpdate;
import com.example.demo.post.infrastructure.PostEntity;
import com.example.demo.post.service.port.PostRepository;
import com.example.demo.user.domain.User;
import com.example.demo.user.infrastructure.UserEntity;
import com.example.demo.user.service.UserService;
import com.example.demo.user.service.port.UserRepository;

import java.time.Clock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ClockHolder clockHolder;

    public Post getPostById(long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posts", id));
    }

    public Post create(PostCreate postCreate) {
        final User writer = userRepository.getById(postCreate.getWriterId());
        final Post post = Post.from(postCreate, writer, clockHolder);
        return postRepository.save(post);
    }

    public Post update(long id, PostUpdate postUpdate) {
        final Post post = getPostById(id);
        final Post updatedPost = post.update(postUpdate, clockHolder);
        return postRepository.save(updatedPost);
    }
}