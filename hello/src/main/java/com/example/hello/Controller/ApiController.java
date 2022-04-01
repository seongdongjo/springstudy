package com.example.hello.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //해당 Class는 Rest API를 처리하는 Controller로 등록이된다.
@RequestMapping("/api") //RequestMapping은 URI를 지정해주는 어노테이션이다.
public class ApiController {

    @GetMapping("/hello") //http://localhost:8080/api/hello 이렇게 요청이오면 return보내라
    public String hello() {
        return "hello spring boot";
    }
}
