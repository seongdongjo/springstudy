package com.example.post.controller;

import com.example.post.dto.PostRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {
    @PostMapping("/post")
    public void post(@RequestBody Map<String, Object> requestData) {
        requestData.entrySet().forEach(soentry -> {
            System.out.println(("key: " +soentry.getKey()));
            System.out.println(("value: " +soentry.getValue()));
        });
    }

    @PostMapping("/post1")
    public void post1(@RequestBody PostRequestDto requestData) {
       System.out.println(requestData);
    }
}