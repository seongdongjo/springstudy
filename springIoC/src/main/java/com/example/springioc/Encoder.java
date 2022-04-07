package com.example.springioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component //에러가뜨는데 빈에서 스프링이 선택할 때 하나만있으면 매칭되는데 지금은 우리가 base64, urlEncoder를 컴포넌트로 등록했기떄문에
//@Qualifier로 지정해줘야된다.
public class Encoder {
    private IEncoder iEncoder;
    
    //외부에서 iEncoder를 주입받음(클래스의 앞글자는 소문자)
    public Encoder(@Qualifier("urlEncoder") IEncoder iEncoder) {
//        this.iEncoder = new Base64Encoder();
//        this.iEncoder = new UrlEncoder();
        this.iEncoder = iEncoder;
    }

    //bean을 주입받을수있는 장소가 변수,생성자, setter가 있다.
    public void setIEncoder(IEncoder iEncoder) {
        this.iEncoder =iEncoder;
    }

    public String encode(String message)
    {
        return iEncoder.encode(message);
    }
}
