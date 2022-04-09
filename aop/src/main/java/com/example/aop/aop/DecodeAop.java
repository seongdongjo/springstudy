package com.example.aop.aop;

import com.example.aop.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut() {}

    @Pointcut("@annotation(com.example.aop.annotation.Decode)")
    private void enableDecode() {}

    //전은 디코드를 해서보낼꺼고
    @Before(("cut() && enableDecode()"))
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Object[] args = joinPoint.getArgs();

        for(Object arg : args) {
            if(arg instanceof User) { //arg중에 User가 있으면
                User user = User.class.cast(arg); //현재는 Object이기 떄문에 User로 형변환을 시킨다.
                String base64Email = user.getEmail(); // 인코딩되있는 이메일을
                String email = new String(Base64.getDecoder().decode(base64Email),"UTF-8"); //bytes로 반환 -> String으로

                user.setEmail(email); //디코드된거(이렇게되면 컨트롤러에서는 유저라는 객체를 디코드 할 필요가없어진다)
            }
        }
    }
    //후는 인코드를 해서 보낼거다
    @AfterReturning(value = "cut() && enableDecode()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        if(returnObj instanceof User) {
            User user = User.class.cast(returnObj); //평문이메일을
            String email = user.getEmail();
            String base64Email = Base64.getEncoder().encodeToString(email.getBytes()); //인코딩
            user.setEmail(base64Email);
        }
    }
}
