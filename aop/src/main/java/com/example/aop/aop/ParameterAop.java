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
        System.out.println("여기니? :" + method.getName());

        //deletemapping은 여기 실행안됌. 받은 인자가 없어서 -> @AfterReturning으로
        Object[] args = joinPoint.getArgs();
        for(Object obj : args) {
            System.out.println("여기는 실행안되네??");
            System.out.println("type: " + obj.getClass().getSimpleName());
            System.out.println("value: " + obj);

        }
    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        System.out.println("return obj:" + returnObj); //return obj: null (void라서)
    }
}
