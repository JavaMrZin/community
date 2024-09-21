package com.javamrzin.community.service;

import com.javamrzin.community.entity.Post;
import com.javamrzin.community.entity.User;
import com.javamrzin.community.repository.PostRepository;
import com.javamrzin.community.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public Post save(String username, Post post) {
        User user = userRepository.findByUsername(username);
        post.setUser(user);
        return postRepository.save(post);
    }


}
