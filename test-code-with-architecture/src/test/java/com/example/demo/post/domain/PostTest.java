package com.example.demo.post.domain;

import static org.assertj.core.api.Assertions.*;
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
	@Test
	void PostUpdate으로_게시물을_수정할_수_있다() throws Exception{
	    //given
		PostUpdate postUpdate = PostUpdate.builder()
			.content("foo")
			.build();

		final User writer = User.builder()
			.id(1L)
			.email("test@mail.com")
			.nickname("test")
			.address("suwon")
			.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
			.status(UserStatus.ACTIVE)
			.build();

		Post post = Post.builder()
			.id(1L)
			.content("Hello world")
			.writer(writer)
			.createdAt(11L)
			.modifiedAt(11L)
			.build();



		//when
		Post updatedPost = post.update(postUpdate, new TestClockHolder(100L));

	    //then
		assertThat(updatedPost.getContent()).isEqualTo("foo");
		assertThat(updatedPost.getWriter().getId()).isEqualTo(1L);
		assertThat(updatedPost.getWriter().getEmail()).isEqualTo("test@mail.com");
		assertThat(updatedPost.getWriter().getAddress()).isEqualTo("suwon");
		assertThat(updatedPost.getWriter().getStatus()).isEqualTo(UserStatus.ACTIVE);
		assertThat(updatedPost.getModifiedAt()).isEqualTo(100L);
		assertThat(updatedPost.getCreatedAt()).isEqualTo(11L);
		assertThat(updatedPost.getWriter().getCertificationCode()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab");

	}
}