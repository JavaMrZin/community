package com.javamrzin.community.service;

import com.javamrzin.community.model.Post;
import com.javamrzin.community.model.User;
import com.javamrzin.community.repository.PostRepository;
import com.javamrzin.community.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
