package com.example.transport.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.example.transport.service..*(..)) || execution(* com.example.transport.app.ConsoleApp.*(..))")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        Logger logger = LoggerFactory.getLogger(pjp.getTarget().getClass());
        String method = pjp.getSignature().toShortString();
        logger.info("Enter {} args={}", method, Arrays.toString(pjp.getArgs()));
        long start = System.nanoTime();
        try {
            Object result = pjp.proceed();
            logger.info("Exit  {} result={} timeMs={}", method, safe(result), (System.nanoTime() - start) / 1_000_000.0);
            return result;
        } catch (Throwable ex) {
            logger.error("Throw {} ex={}", method, ex.toString());
            throw ex;
        }
    }

    @AfterThrowing(pointcut = "execution(* com.example.transport..*(..))", throwing = "ex")
    public void logUnhandled(Throwable ex) {
        LoggerFactory.getLogger("Unhandled").error("Unhandled exception: {}", ex.toString());
    }

    private String safe(Object obj) {
        try { return String.valueOf(obj); } catch (Exception e) { return "<toString failed>"; }
    }
}
