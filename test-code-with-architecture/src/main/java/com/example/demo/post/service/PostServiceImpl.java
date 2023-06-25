package com.example.demo.post.service;

import org.springframework.stereotype.Service;

import com.example.demo.common.domain.exception.ResourceNotFoundException;
import com.example.demo.common.service.port.ClockHolder;
import com.example.demo.post.controller.port.PostService;
import com.example.demo.post.domain.Post;
import com.example.demo.post.domain.PostCreate;
import com.example.demo.post.domain.PostUpdate;
import com.example.demo.post.service.port.PostRepository;
import com.example.demo.user.domain.User;
import com.example.demo.user.service.port.UserRepository;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final ClockHolder clockHolder;

	@Override
	public Post getPostById(long id) {
		return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posts", id));
	}

	@Override
	public Post create(PostCreate postCreate) {
		final User writer = userRepository.getById(postCreate.getWriterId());
		final Post post = Post.from(postCreate, writer, clockHolder);
		return postRepository.save(post);
	}

	@Override
	public Post update(long id, PostUpdate postUpdate) {
		final Post post = getPostById(id);
		final Post updatedPost = post.update(postUpdate, clockHolder);
		return postRepository.save(updatedPost);
	}
}