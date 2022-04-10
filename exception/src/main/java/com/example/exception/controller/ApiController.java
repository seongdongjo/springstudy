package com.example.exception.controller;

import com.example.exception.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class ApiController {
    @GetMapping("")
    public User get(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age) { //Integer로 한이유는 @NotNull하기위해 (userclass 변수에)
        User user = new User();
        user.setAge(age); //age를 입력받지 않으면 null pointer에러가난다. set은 int를 받아야되는데 null을 받았기떄문에
        user.setName(name);

        int a = 10+age; 

        return user;
    }

    @PostMapping("")
    public User post(@Valid @RequestBody User user) { //@RequestBody는 알아서 User 에 setter해준다.
        System.out.println(user); //빈값을 보내면 서버에서는 에러가 default message [크기가 1에서 10 사이여야 합니다]] ] 이런식으로 에러가뜬다.
        //근데 클라이언트 입장에서는 아래처럼 400에러 라고 뜨면서 불친절하다. 그래서 직접만들자. -> 내가만든 GlobalControllerAdvice class참조
//        {
//            "timestamp": "2022-04-10T12:26:45.517+00:00",
//                "status": 400,
//                "error": "Bad Request",
//                "path": "/api/user"
//        }
        return user;
    }

    //GlobalControllerAdvice에서 예외 설정은 했지만 우선순위는 컨트롤러에서 지정한 ExceptionHandler를 먼저 탄다.
        @ExceptionHandler(value = MethodArgumentNotValidException.class)
        public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e) {
            //기존에 뜨던 방식
            //        {
//            "timestamp": "2022-04-10T12:26:45.517+00:00",
//                "status": 400,
//                "error": "Bad Request",
//                "path": "/api/user"
//        }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); //불친절한 메시지가 응답으로 간다. (그래도 어디서 예외났는지는 알수있다)
        }
}
