package com.example.put;

import com.example.put.dto.PostRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {
    @PutMapping("/put")
    public PostRequestDto put(@RequestBody PostRequestDto requestDto) {
        //Json 디자인
//        {
//            "name" : "steve",
//            "age" : 10,
//            "car_list" : [
//                {
//                    "name" : "BMW",
//                    "car_number" : "11가 1234"
//                },
//                {
//                    "name" : "Audi",
//                    "car_number" : "11가 2222"
//                }
//            ]
//        }
        System.out.println(requestDto); //크롬에서 put으로 해서 http://localhost:8080/api/put, body에 json디자인한걸 넣으면
        //하지만 carList=null이라고 뜬다. 왜냐하면 dto에는 catList로 했지만 body에는 car_list로 스네이크케이스로 했기떄문에
        //클래스에 대해 전체적으로 같은 룰을 적용시킬때는 (물론, jsonproperty해도 되지만) @JsonNaming을 쓴다.(PostRequestDto, CarDto)

        return requestDto; //받은 json body를 그대로 넘겨준다.
        //즉, "name" :requestDto.getName() 이렇게 할필요없이 스프링부트에서는 dto에 매핑해서 json형태로 response해준다.

        //PathVariable 테스트
//        @PutMapping("/put/{userId}")
//        public PostRequestDto put(@RequestBody PostRequestDto requestDto, @PathVariable Long userId) {
//            System.out.println(userId);
//            http://localhost:8080/api/put/100, body에 json넣고 send하면 콘솔에 찍힌다.
//        }
    }
}
