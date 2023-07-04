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

/**
 * <h1>This class will log the project activity</h1>
 *
 * Taking advantage of Spring AOP this class will bring
 * additional behavior to existing code without modification of the code itself.
 *
 * @author Guillaume
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /**
     * This method is used to create a poincut for RestControllers
     * The within PCD will match every bean with the @RestController annotation
     * The method declaration is used as the pointcut signature
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void springRestControllerBeanPointCut() {
    }

    /**
     * This method is used to create a poincut for Services
     * The within PCD will match every bean with the @Service annotation
     * The method declaration is used as the pointcut signature
     */
    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void springServiceBeanPointCut() {
    }

    /**
     * This advice will log args before the joinPoint will be executed
     * within the rest
     *
     * @param joinPoint for the Before advice
     */
    @Before("springRestControllerBeanPointCut()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        if (log.isInfoEnabled()) {
            log.info("Calling {}.{}() with parameters: {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()));
        }
    }

    /**
     * This advice will log objects after the JoinPoint will return
     * within the rest layer
     *
     * @param joinPoint for the AfterReturning advice
     * @param value is the Object returned by our joinPoint
     */
    @AfterReturning(pointcut = "springRestControllerBeanPointCut()", returning = "value")
    public void logAfterMethod(JoinPoint joinPoint, Object value) {
        if (log.isInfoEnabled()) {
            log.info("Returning {}.{}() with object: {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    value.toString());
        }
    }

    /**
     * This advice will log execution time of the joinPoint method
     * within the rest layer
     *
     * @param joinPoint for the Around advice
     */
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

    /**
     * This advice will log Exceptions within the service layer
     *
     * @param joinPoint for the AfterThrowing advice
     * @param e that are thrown by the joinPoint
     */
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
