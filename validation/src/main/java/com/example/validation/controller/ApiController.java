package com.example.validation.controller;

import com.example.validation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

//{
//        "name" : "홍길동",
//        "age" : 10,
//        "email" : "abced",
//        "phoneNumber" : "01011112222"
//}

@RestController
@RequestMapping("/api")
public class ApiController {
    @PostMapping("/user") // http://localhost:8080/api/user
    public User user(@RequestBody User user) {
        //근데 이거는 email이나 phoneNumber가 정상적인 요청이아니다. 이메일은 @가 들어가야하는데 요청이 잘못됌, 나이도 100살이하로 받아야됀다.
        System.out.println(user); //User{name='홍길동', age=120, email='abced', phoneNumber='01011112222'}
        return user;
    }

    //그래서 기존에서는 이렇게 하나씩 if문써서 했다.
    @PostMapping("/user1")
    public ResponseEntity user1(@RequestBody User user) {
        System.out.println(user);
        if(user.getAge() >= 100) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        }
        return ResponseEntity.ok(user);
    }
    //그래서 User class를 참조해보면 validation을 붙힌다.
    @PostMapping("/user2") // http://localhost:8080/api/user
    public User user2(@Valid @RequestBody User user) { //@Valid를 붙인다.
        System.out.println(user);
        return user;
    }

    //이렇게 전송 시
//    {
//            "name" : "홍길동",
//            "age" : 101,
//            "email" : "abced",
//            "phoneNumber" : "01011112222"
//    }
    //근데 클라이언트가 잘못입력 시 지금은 예외가 바로 터지는데 Validation의 결과를 BindingResult으로 들어오게만든후 메시지를 출력해보자
    @PostMapping("/user3") // http://localhost:8080/api/user
    public ResponseEntity user3(@Valid @RequestBody User user, BindingResult bindingResult) { //@Valid를 붙인다.
        if(bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                System.out.println("field :" +field.getField());
                System.out.println(message);
            });
        }
        System.out.println(user);
        return ResponseEntity.ok(user); //client body에 user 객체를 보낸다. (보여주기위해)
        //출력결과
//        field :email
//        올바른 형식의 이메일 주소여야 합니다
//        field :phoneNumber
//        "^\d{2,3}-\d{3,4}-\d{4}$"와 일치해야 합니다
//        User{name='홍길동', age=101, email='abced', phoneNumber='01011112222'}
    }
    //근데 에러 메시지가 예쁘지가 않다. (전화번호) 그래서 User class에서 메시지를 지정해줄도 있다
    @PostMapping("/user4") // http://localhost:8080/api/user
    public ResponseEntity user4(@Valid @RequestBody User user, BindingResult bindingResult) { //@Valid를 붙인다.
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                System.out.println("field :" + field.getField());
                System.out.println(message);

                sb.append("filed: " + field.getField());
                sb.append("message: " + message);

            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
            //출력결과: filed: phoneNumbermessage: 핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxxfiled: emailmessage: 올바른 형식의 이메일 주소여야 합니다filed: agemessage: 90 이하여야 합니다
        }
        //여기는 logic부분 이제 쓰면됌

        return ResponseEntity.ok(user); //if문을 돌지않으면 여기로
    }

}
