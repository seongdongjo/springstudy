package com.example.springioc;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component //Base64Encoder가 빈으로 등록된다.(스프링이 관리한다)
//@Component("base74Encoder") 나는 이 이름으로 쓰겠다. @Qualifier("base74Encoder") 로 가능
public class Base64Encoder implements IEncoder{
    public String encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
