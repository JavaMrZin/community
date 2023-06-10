package com.javamrzin.community.controller;

import com.javamrzin.community.model.Post;
import com.javamrzin.community.repository.PostRepository;
import com.javamrzin.community.validator.PostValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostRepository postRepository;
    private final PostValidator postValidator;

    @GetMapping({"", "/"})
    public String list(Model model, @PageableDefault(size = 2) Pageable pageable
            , @RequestParam(required = false, defaultValue = "") String searchText) {
        Page<Post> posts = postRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        log.info("posts = {}", posts);
        for (Post post : posts) {
            log.info("post = {}", post);
        }
        int startPage = Math.max(posts.getTotalPages(), posts.getPageable().getPageNumber() - 4);
        int endPage = Math.min(posts.getTotalPages(), posts.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("posts", posts);
        return "post/list";
    }

    @GetMapping("/form")
    public String getForm(Model model, @RequestParam(required = false) Long id) {
        if (id == null) {
            model.addAttribute("post", new Post());
        } else {
            Post post = postRepository.findById(id).orElse(null);
            model.addAttribute("post", post);
        }
        return "post/form";
    }

    @PostMapping("/form")
    public String postsForm(@Valid Post post, BindingResult bindingResult) {
        postValidator.validate(post, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/post/form";
        }

        postRepository.save(post);
        return "redirect:/posts";
    }

}
