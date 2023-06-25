package com.example.demo.post.infrastructure;

import com.example.demo.post.domain.Post;
import com.example.demo.user.domain.User;
import com.example.demo.user.infrastructure.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "modified_at")
    private Long modifiedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity writer;

    public static PostEntity fromModel(final Post post) {
        final PostEntity postEntity = new PostEntity();
        postEntity.id = post.getId();
        postEntity.content = post.getContent();
        postEntity.modifiedAt = post.getModifiedAt();
        postEntity.createdAt = post.getCreatedAt();
        postEntity.writer = UserEntity.fromModel(post.getWriter());
        return postEntity;
    }

    public Post toModel(){
        return Post.builder()
            .id(this.id)
            .content(this.content)
            .createdAt(this.createdAt)
            .modifiedAt(this.modifiedAt)
            .writer(this.writer.toModel())
            .build();
    }

}