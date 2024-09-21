package com.javamrzin.community.controller;

import com.javamrzin.community.entity.Post;
import com.javamrzin.community.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostApiController {

    private final PostRepository postRepository;

    @GetMapping("/posts")
    List<Post> all(@RequestParam(required = false, defaultValue = "") String title
            , @RequestParam(required = false, defaultValue = "") String content) {
        if (StringUtils.hasText(title) || StringUtils.hasText(content)) {
            return postRepository.findByTitleOrContent(title, content);
        } else {
            return postRepository.findAll();
        }
    }

    @PostMapping("/posts")
    Post newPost(@RequestBody Post newPost) {
        return postRepository.save(newPost);
    }

    @GetMapping("/posts/{id}")
    Post one(@PathVariable Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @PutMapping("/posts/{id}")
    Post replacePost(@RequestBody Post newPost, @PathVariable Long id) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(newPost.getTitle());
                    post.setContent(newPost.getContent());
                    return postRepository.save(post);
                })
                .orElseGet(() -> {
                    newPost.setId(id);
                    return postRepository.save(newPost);
                });
    }

    @DeleteMapping("/posts/{id}")
    void deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
    }
}
