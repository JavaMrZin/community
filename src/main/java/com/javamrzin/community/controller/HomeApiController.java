package com.javamrzin.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeApiController {

    @GetMapping({"", "/"})
    public String index() {
        return "Home API Controller";
    }

}
