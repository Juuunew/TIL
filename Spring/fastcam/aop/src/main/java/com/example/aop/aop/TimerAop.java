package com.example.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @Componet -> class 에 등록됨
 * @Bean -> method 에 등록
 * @Configuration -> 하나의 class 에 여러가지 Bean 등록록
 */

@Aspect
@Component
public class TimerAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut() {
    }

    @Pointcut("@annotation(com.example.aop.annotation.Timer)")
    private void enableTimer() {
    }

    @Around("cut() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        // method 실행 전
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // method 실행 시점
        Object result = joinPoint.proceed();

        // method 실행 후
        stopWatch.stop();

        System.out.println("total time = " + stopWatch.getTotalTimeSeconds());
    }
}
