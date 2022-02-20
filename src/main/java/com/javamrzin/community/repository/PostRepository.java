package com.javamrzin.community.repository;

import com.javamrzin.community.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
