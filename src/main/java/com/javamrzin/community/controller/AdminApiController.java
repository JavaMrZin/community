package com.javamrzin.community.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminApiController {

    @GetMapping({"", "/"})
    public String index() {
        return "Admin API Controller";
    }

}
