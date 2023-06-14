package com.example.demo.post.controller.response;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.demo.post.domain.Post;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;

class PostResponseTest {

	@Test
	void post로_응답을_생성할_수_있다() throws Exception{
	    //given
		Post post = Post.builder()
			.content("Hello world")
			.writer(User.builder()
				.id(1L)
				.email("test@mail.com")
				.nickname("test")
				.address("suwon")
				.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
				.status(UserStatus.ACTIVE)
				.build())
			.build();
	    //when
		PostResponse postResponse = PostResponse.from(post);
	    //then
		assertThat(postResponse.getContent()).isEqualTo("Hello world");
		assertThat(postResponse.getWriter().getId()).isEqualTo(1L);
		assertThat(postResponse.getWriter().getEmail()).isEqualTo("test@mail.com");
		assertThat(postResponse.getWriter().getStatus()).isEqualTo(UserStatus.ACTIVE);

	}

}