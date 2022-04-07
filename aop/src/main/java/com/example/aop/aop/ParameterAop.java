package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect //aop로 동작
@Component //스프링에서 관리
public class ParameterAop {
    //controller하위 메서드가 실행되기전에 before부분이 실행될거고 그 후에 controller 메서드 실행 후 정상리턴되면 afterReturning에서 해당 object를 볼수가있다.
    @Pointcut("execution(* com.example.aop.controller..*.*(..))") //어디에 적용시킬건지(controller패키지밑에 모든 메서드를 aop로 보겠다)
    private void cut() {}

    @Before("cut()") //cut이 실행되는 지점에 before메서드를 실핼
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());

        Object[] args = joinPoint.getArgs();
        //크롬에서 body에 넣어서 보내게 되면
        for(Object obj : args) {
            System.out.println("type: " + obj.getClass().getSimpleName()); //1(출력순서). type: User
            System.out.println("value: " + obj); //2. User{id='steve', pw='1234', email='steve@gmail.com'}
            //그 후에 RestApiController로 가서 post실행
        }
    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        System.out.println("return obj:" + returnObj); //4. User{id='steve', pw='1234', email='steve@gmail.com'}
    }
}
