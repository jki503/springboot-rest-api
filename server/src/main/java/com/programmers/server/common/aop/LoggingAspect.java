package com.programmers.server.common.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Aspect
@Component
public class LoggingAspect {
    @Around("execution(* com.programmers.server..*Controller.*(..))")
    public Object printControllerLog(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("{} method called", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    @Around("execution(* com.programmers.server..*Service.*(..))")
    public Object printServiceLog(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("{} method called", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    @Around("execution(* com.programmers.server..*Repository.*(..))")
    public Object printRepositoryLog(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("{} method called", joinPoint.getSignature());
        return joinPoint.proceed();
    }
}
