package com.example.springioc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component //얘도 스프링에서 빈으로 등록해서 관리해줘
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    //여기도 보면 applicationContext 라는 객체를 외부로부터 주입을 받는다.(스프링이알아서)
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
