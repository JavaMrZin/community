package com.javamrzin.community.repository;

import com.javamrzin.community.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitle(String title);

    List<Post> findByTitleOrContent(String title, String content);

    Page<Post> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
