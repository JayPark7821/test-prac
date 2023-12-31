package com.example.demo.post.domain;

import java.time.Clock;

import com.example.demo.common.service.port.ClockHolder;
import com.example.demo.user.domain.User;
import com.example.demo.user.infrastructure.UserEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Post {
	private final Long id;
	private final String content;
	private final Long createdAt;
	private final Long modifiedAt;
	private final User writer;

	@Builder
	public Post(final Long id, final String content, final Long createdAt, final Long modifiedAt,
		final User writer) {
		this.id = id;
		this.content = content;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.writer = writer;
	}

	public static Post from(PostCreate postCreate, User writer, ClockHolder clockHolder){
		return Post.builder()
			.content(postCreate.getContent())
			.createdAt(clockHolder.millis())
			.writer(writer)
			.build();
	}

	public Post update(PostUpdate postUpdate, ClockHolder clockHolder){
		return Post.builder()
			.id(this.id)
			.content(postUpdate.getContent())
			.createdAt(this.createdAt)
			.modifiedAt(clockHolder.millis())
			.writer(this.writer)
			.build();

	}
}
