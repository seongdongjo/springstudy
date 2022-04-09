package com.example.aop.controller;

import com.example.aop.annotation.Decode;
import com.example.aop.annotation.Timer;
import com.example.aop.dto.User;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {
    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name) {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        //Logic이 들어가는 곳
//        stopWatch.stop();
//        System.out.println("total time: " + stopWatch.getTotalTimeSeconds()); //총 얼마나 걸렸는지
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
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        //Logic이 들어가는 곳.
//        stopWatch.stop();
//        System.out.println("total time: " + stopWatch.getTotalTimeSeconds()); //총 얼마나 걸렸는지
        return user; //afterReturning으로
    }

    @Timer //우리가 직접만든 어노테이션
    @DeleteMapping("delete")
    public void delete() throws InterruptedException{
        //delete하는데 2초 소요된다고 가정

        //aop를 쓰지않으면 이렇게 중복된코드가 들어가는데 get, post에도 마찬가지로 실제적으로 Logic이 들어가는곳에 반복적으로 들어갈수있다.
        //모든 메서드에 같은 기능이 들어갔다(횡단). 비즈니스로직과 전혀상관없는 이러한것들을 aop로 뺴는 것이다. 너네는 서비스로직만 신경써
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
        System.out.println("타이머실행");
        Thread.sleep(1000 * 2);
        System.out.println("타이머종료");
//        stopWatch.stop();
//        System.out.println("total time: " + stopWatch.getTotalTimeSeconds()); //총 얼마나 걸렸는지
    }

    @Decode //값을 바꿔줄거기 때문에
    @PutMapping("/put")
    public User put(@RequestBody User user) { // 크롬에 http://localhost:8080/api/put
//        {
//            "id" : "steve",
//            "pw" : "1234",
//            "email" : "c3RldmVAZ21haWwuY29t" //인코딩된 이메일(main에서 출력해서 받아옴)
//        }
        System.out.println("put");
        System.out.println(user); //실제 서비스로직에서는 평문
        return user;
    }
    //출력결과 (처음에 인코딩된 email을 받아서 before에서 디코딩처리하고 setemail -> putmapping api실행 -> user를 after에 전달 -> after에서 인코딩 후 setemail
//    여기니? :put
//    여기는 실행안되네??
//    type: User
//    value: User{id='steve', pw='1234', email='steve@gmail.com'}
//    put
//    User{id='steve', pw='1234', email='steve@gmail.com'}
//    return obj:User{id='steve', pw='1234', email='steve@gmail.com'}

    //크롬에 응답온걸 보면
//    {
//        "id": "steve",
//        "pw": "1234",
//        "email": "c3RldmVAZ21haWwuY29t"
//    }

}
