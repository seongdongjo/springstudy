package com.example.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestContollerAdvice(basePackages = "com.example.exception.controller") 이 패키지에 발생하는 예외를 처리

@RestControllerAdvice //@RestContoller에 할거니까
public class GlobalControllerAdvice {

    @ExceptionHandler(value = Exception.class) //이 프로젝트에서 일어나는 모든 예외를 잡을거다
    public ResponseEntity exception(Exception e) { //즉, 예외가 터지면 다 이쪽을 탈것이다. 터진예외메시지를 e 를 통해 받는다.
        System.out.println(e.getClass().getName()); //무슨에러인지 보기위해 -> MethodArgumentNotValidException 이라고 뜬다. -> @ExceptionHandler 처리
        System.out.println(e.getLocalizedMessage()); //이거는 불친절한 에러

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

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
