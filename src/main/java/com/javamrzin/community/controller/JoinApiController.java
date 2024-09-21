package com.javamrzin.community.controller;

import com.javamrzin.community.dto.JoinDto;
import com.javamrzin.community.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/join")
@RequiredArgsConstructor
public class JoinApiController {

    private final JoinService joinService;

    @PostMapping({"", "/"})
    public String postJoin(JoinDto joinDto) {

        joinService.joinUser(joinDto);

        return "Join OK";
    }

}
