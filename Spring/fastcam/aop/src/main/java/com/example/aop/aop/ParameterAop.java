package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParameterAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut() {

    }

    /**
     * method 가 실행되기 전에 넘어가는 argument 확인
     */
    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("method = " + method.getName());

        Object[] args = joinPoint.getArgs();

        for (Object obj : args) {
            System.out.println("obj type = " + obj.getClass().getSimpleName());
            System.out.println("obj value = " + obj);
        }
    }

    /**
     * return 이 되었을 때
     */
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        System.out.println("======return obj======");
        System.out.println("joinPoint = " + joinPoint + ", returnObj = " + returnObj);
    }
}
