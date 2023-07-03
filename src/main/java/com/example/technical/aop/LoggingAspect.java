package com.example.technical.aop;

/* FILE LoggingAspect
AUTHOR Guillaume
PROJECT technical
DATE 02/07/2023 */

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void springRestControllerBeanPointCut() {
    }

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void springServiceBeanPointCut() {
    }

    @Before("springRestControllerBeanPointCut()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        if (log.isInfoEnabled()) {
            log.info("Calling {}.{}() with parameters: {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()));
        }
    }

    @AfterReturning(pointcut = "springRestControllerBeanPointCut()", returning = "value")
    public void logAfterMethod(JoinPoint joinPoint, Object value) {
        if (log.isInfoEnabled()) {
            log.info("Returning {}.{}() with object: {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    value.toString());
        }
    }

    @Around("springRestControllerBeanPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        if (log.isInfoEnabled()) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

            String className = methodSignature.getDeclaringTypeName();
            String methodName = methodSignature.getName();

            StopWatch stopWatch = new StopWatch(className + methodName);
            stopWatch.start(methodName);
            result = joinPoint.proceed();
            stopWatch.stop();
            log.info("Running time for {}.{}(): {} ms", className, methodName, stopWatch.getTotalTimeMillis());
        }
        return result;
    }

    @AfterThrowing(pointcut = "springServiceBeanPointCut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Exception e) {
        if (log.isErrorEnabled()) {
            log.error("Exception in {}.{}() with cause = {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    e.getMessage());
        }
    }
}
