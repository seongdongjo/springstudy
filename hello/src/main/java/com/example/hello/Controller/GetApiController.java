package com.example.hello.Controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    //변수가 뭐가 들어올지 모를때
    @GetMapping("/query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam) { //queryParam: size = 3

        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey()+"="+entry.getValue()+"\n");
        });
        return sb.toString();
    }
    //또다른 방법(명시적으로)
    @GetMapping("query-param02") //단, age에다가 String을 넣으면 에러가 난다.400에러(클라이언트 실수)
    public String queryParam02(@RequestParam String name, @RequestParam String email, @RequestParam int age) {
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name + " " +email+" "+age;
    }
    
    //하지만, 변수가 이렇게 많아지면... DTO이용
    //?user=steve&email=steve@gmail.com&age=30 이렇게 요청이 오면 알아서 DTO에 매핑을 해준다.
    public String queryParam03(UserRequest userRequest) {
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }
    
}
