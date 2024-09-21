package com.javamrzin.community.controller;

import com.javamrzin.community.entity.Post;
import com.javamrzin.community.repository.PostRepository;
import com.javamrzin.community.service.PostService;
import com.javamrzin.community.validator.PostValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
@Slf4j
public class PostWebController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final PostValidator postValidator;

    @GetMapping({"", "/"})
    public String list(Model model
            , @PageableDefault(size = 10) Pageable pageable
            , @RequestParam(required = false, defaultValue = "") String searchText) {
        Page<Post> posts = postRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, posts.getPageable().getPageNumber() - 4);
        int endPage = Math.min(posts.getTotalPages() == 0 ? 1 : posts.getTotalPages(), posts.getPageable().getPageNumber() + 4);

        model.addAttribute("posts", posts);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

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
    public String postsForm(@Valid Post post, BindingResult bindingResult, Authentication authentication) {
        postValidator.validate(post, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/post/form";
        }

        String username = authentication.getName();
        postService.save(username, post);
        return "redirect:/posts";
    }

}
