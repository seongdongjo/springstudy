package com.example.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component //@Component는 클래스단위로, @Bean은 클래스에 붙일 수 없다. @Bean은 메서드에서 사용
public class TimerAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut() {}

    @Pointcut("@annotation(com.example.aop.annotation.Timer)") //어노테이션이 @Timer로 설정된 메서드만 로깅을 할거야. 근데 시간을 잴거기 때문에
    //전,후메서드가 필요한데 before,after로 timer를 공유할수없다. 이래서 @Around
    private void enableTimer() {}

    @Around("cut() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        System.out.println("전");
        Object result = joinPoint.proceed(); //이 메서드의 실행 전 후로 나눈다.(위 아래) -> deletemapping에 있는 타이머 실행
        System.out.println("후");
        
        stopWatch.stop();
        System.out.println("total time: " + stopWatch.getTotalTimeSeconds()); //총 얼마나 걸렸는지 //이렇게 aop를 통해서 시간다 찍어서 모니터링가능
    }
}
