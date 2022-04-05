package com.example.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectMapperApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("hello");

        //오브젝트 매퍼는
        //Text형태의 Json을 -> Object
        //Object -> Text Json

        //controller request로 json(text)이 오면 -> object
        //response에서 object -> json(text)

        var objectMapper = new ObjectMapper();

        //object를 text로 바꿔보자
        var user = new User("steve", 10, "010-1111-1111");
        var text = objectMapper.writeValueAsString(user);//예외처리도 해주기
        System.out.println(text);
        //여기까지 하면 에러가나는데 ObjectMapper는 getter를 참조한다. 따라서 getter를 만들어줘야한다.(User)
        //즉, object -> text로 바뀔 떄는 Object mapper가 getter method를 참조한다.
        //출력: {"name":"steve","age":10,"phone_number":"010-1111-1111"} 알아서 snake case로 변환


        //text->object로 바꿔보자
        var objectUser = objectMapper.readValue(text, User.class);//text에는 json형태의 text, User라는 클래스로 바꾼다
        System.out.println(objectUser);
        //이렇게 하면 에러가난다.
        //text->object로 할때는 디폴트생성자가 있어야한다(User)
        //출력: User{name='steve', age=10, phoneNumber='010-1111-1111'}
    }

}
