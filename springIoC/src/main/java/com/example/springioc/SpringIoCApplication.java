package com.example.springioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication //옆에 돋보기를 누르면 객체를 볼수있다.(스프링 컨테이너에서 관리되는 객체를 빈이라고 부른다)
public class SpringIoCApplication {

    public static void main(String[] args) {
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";
        SpringApplication.run(SpringIoCApplication.class, args);

        //위에 SpringAppliction이 실행되고 나면
        ApplicationContext context = ApplicationContextProvider.getContext();

        //주입을 위해 (객체를 받음)
        //Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
        //UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);
        Encoder encoder = context.getBean("base74Encoder", Encoder.class); //base64가 2개겹치니까(@Bean부분과) 명시적으로

        String result = encoder.encode(url); //Encoder에서 내가 @Qualifier("urlEncoder")로 했으니 url인코딩으로 된다.
        System.out.println(result);

    }

}

//직접 빈을 등록해보자
@Configuration //한개~여러개 빈을 등록할 것이다.
class AppConfig {

    //여기서 문제가 encoder라는 이름이 중복이된다. 그래서 @Bean("base74Encoder")라고 명시 -> 빈에 이미 base64Encoder가 있으니 74로
    @Bean("base74Encoder")
    public Encoder encoder(Base64Encoder base64Encoder) { //스프링으로 부터 주입받고
        return new Encoder(base64Encoder);
    }
    @Bean("urlEncode") //여기도 빈에 이미 urlEncoder가 있어서 urlEncode로 이름 설정
    public Encoder encoder(UrlEncoder urlEncoder) {
        return new Encoder(urlEncoder);
    }
}

