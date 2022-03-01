package com.javamrzin.community.repository;

import com.javamrzin.community.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitle(String title);
    List<Post> findByTitleOrContent(String title, String content);

}
