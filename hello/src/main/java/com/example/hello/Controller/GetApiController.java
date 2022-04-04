package com.example.hello.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/get")
public class GetApiController {
    @GetMapping(path = "/hello") //http://localhost:8080/api/get/hello
    public String hello() {
        return "get Hello";
    }

    //또다른 방법(예전방식)
    //@RequestMapping("/hi") //이렇게하면 get, post, put, delete 모든 메서드가 동작하게된다.
    @RequestMapping(path = "/hi", method = RequestMethod.GET) //그래서 특정메서드로 지정 //http://localhost:8080/api/get/hi
    public String hi() {
        return "hi";
    }

    //변화하는 값을 받기 위한 pathvariable
    //http://localhost:8080/api/get/path-variable/{name}  -> 변화하는 name을 받음
    @GetMapping("/path-variable/{name}") //주소에는 소문자만 권장하니 - 으로 구분
    public String pathVariable(@PathVariable String name) {
        System.out.println("PathVariable: " +name);
        return name;
    }

    //pathVariable하면서 받는 변수에는 다른 이름으로 설정하고 싶을 때는 지정해주면된다.
    @GetMapping("/path-variable1/{name}")
    public String pathVariable1(@PathVariable(name = "name") String pathName) { //실제로 사용하는 변수는 pathName
        System.out.println("PathVariable: " +pathName);
        return pathName;
    }

    //queryParameter란
    //처음 붙을 떄는 ?로 연결하고 그 후에는 &로 연결한다.
    //search?q=intre
    //&rlz=12r3
    //http://localhost:8080/api/get/query-param?user=steve&email=steve@gmail.com&age=30
    
}
