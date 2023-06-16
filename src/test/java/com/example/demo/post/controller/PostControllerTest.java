package com.example.demo.post.controller;

public class PostControllerTest {

	// @Test
	// void 사용자는_게시물을_단건_조회_할_수_있다() throws Exception {
	//     // given
	//     // when
	//     // then
	//     mockMvc.perform(get("/api/posts/1"))
	//         .andExpect(status().isOk())
	//         .andExpect(jsonPath("$.id").isNumber())
	//         .andExpect(jsonPath("$.content").value("helloworld"))
	//         .andExpect(jsonPath("$.writer.id").isNumber())
	//         .andExpect(jsonPath("$.writer.email").value("kok202@naver.com"))
	//         .andExpect(jsonPath("$.writer.nickname").value("kok202"));
	// }
	//
	// @Test
	// void 사용자가_존재하지_않는_게시물을_조회할_경우_에러가_난다() throws Exception {
	//     // given
	//     // when
	//     // then
	//     mockMvc.perform(get("/api/posts/123456789"))
	//         .andExpect(status().isNotFound())
	//         .andExpect(content().string("Posts에서 ID 123456789를 찾을 수 없습니다."));
	// }
	//
	// @Test
	// void 사용자는_게시물을_수정할_수_있다() throws Exception {
	//     // given
	//     PostUpdate postUpdate = PostUpdate.builder()
	//         .content("foobar")
	//         .build();
	//
	//     // when
	//     // then
	//     mockMvc.perform(
	//         put("/api/posts/1")
	//             .contentType(MediaType.APPLICATION_JSON)
	//             .content(objectMapper.writeValueAsString(postUpdate)))
	//         .andExpect(status().isOk())
	//         .andExpect(jsonPath("$.id").isNumber())
	//         .andExpect(jsonPath("$.content").value("foobar"))
	//         .andExpect(jsonPath("$.writer.id").isNumber())
	//         .andExpect(jsonPath("$.writer.email").value("kok202@naver.com"))
	//         .andExpect(jsonPath("$.writer.nickname").value("kok202"));
	// }
}