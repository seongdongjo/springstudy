package com.example.aop.controller;

import com.example.aop.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {
    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name) {
        System.out.println("get mehotd: " + id);
        System.out.println("get mehotd: " + name);
        return id + " " + name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user) { //크롬에 body에 넣어서
        //http://localhost:8080/api/post
//        {
//            "id" : "steve",
//            "pw" : "1234",
//            "email": "steve@gmail.com"
//        }
        System.out.println("post method: " + user); //3.  User{id='steve', pw='1234', email='steve@gmail.com'}
        return user; //afterReturning으로
    }
}
