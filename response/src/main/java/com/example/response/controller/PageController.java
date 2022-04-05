package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //애는 html같은 리소스를 찾는다. @RestController는 API를 만들 때 , @Controller는 페이지를 리턴할 때
//그렇다면 @Controller에서는 json을 어떻게 보냄?? -> ResponseBody
public class PageController {
    @RequestMapping("/main")
    //@Controller라는 어노테이션은 String을 return하게되면 리소스를 찾지만 @ResponseBody를 붙이면 말그대로 body를 만들어서 response하겠다라는 의미이다.
    public String main() {
        return "main.html"; //http://localhost:8080/main
    }

    @ResponseBody
    @GetMapping("/user")
    public User user() {
        //기존
        //User user = new User();

        //자바 11에서의 타입추론
        var user = new User();
        user.setName("steve");
        user.setAddress("패스트 캠퍼스");
        return user; //json으로 응답
        // http://localhost:8080/user
    }

}
