package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/text")
    public String text(@RequestParam String account) {
        return account; //일반 String으로 response
    }

    //Json으로 response
    @PostMapping("/json")
    public User json(@RequestBody User user) {
        //Json body
//        {
//            "name":"steve",
//                "age": 10,
//                "phoneNumber" : "010-1111-2222",
//                "address" : "패스트캠퍼스"
//        }
        return user; //json으로 위에 처럼 응답을 준다.
        //request가 오게되면 -> object mapper로 인해서 -> object로 바뀌고 -> postmapping -> object로 받고 -> object mapper -> json -> response
    }

    //특정 reponse로 내려주기
    //put 으로 resource가 생성됐을 때 201 response 할것이다.
    //수정됐을 떄는 200
    @PutMapping("/put")
    public ResponseEntity<User> put(@RequestBody User user) { //ResponseEntitiy를 통해 header값도 추가가능, 응답에 대해 커스텀마이징도 가능
        return ResponseEntity.status(HttpStatus.CREATED).body(user); //마찬가지로 json으로 매핑되서 response된다.
    }
    //크롬에 put , http://localhost:8080/api/put 하고 body에 위에 json body를 넣으면 201로 response가 뜬다.


}
