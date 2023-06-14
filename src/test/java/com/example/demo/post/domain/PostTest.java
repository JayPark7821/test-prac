package com.example.demo.post.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Clock;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.demo.mock.TestClockHolder;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;

class PostTest {

	@Test
	void PostCreate으로_게시물을_만들_수_있다() throws Exception{
	    //given
		PostCreate postCreate = PostCreate.builder()
			.writerId(1L)
			.content("Hello world")
			.build();

		final User writer = User.builder()
			.id(1L)
			.email("test@mail.com")
			.nickname("test")
			.address("suwon")
			.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
			.status(UserStatus.ACTIVE)
			.build();

		//when
		Post post = Post.from(postCreate, writer, new TestClockHolder(100L));

	    //then
		assertThat(post.getContent()).isEqualTo("Hello world");
		assertThat(post.getWriter().getId()).isEqualTo(1L);
		assertThat(post.getWriter().getEmail()).isEqualTo("test@mail.com");
		assertThat(post.getWriter().getAddress()).isEqualTo("suwon");
		assertThat(post.getWriter().getStatus()).isEqualTo(UserStatus.ACTIVE);
		assertThat(post.getCreatedAt()).isEqualTo(100L);
		assertThat(post.getWriter().getCertificationCode()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab");

	}
}