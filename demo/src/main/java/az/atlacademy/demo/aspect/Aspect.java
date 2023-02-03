package az.atlacademy.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@org.aspectj.lang.annotation.Aspect
@Slf4j
@Component
public class Aspect {
    @Before("execution(* az.atlacademy.demo.service.*.*(..))")
    void beforeService(JoinPoint joinPoint) {
        log.info(joinPoint + "args: {}", joinPoint.getArgs());
    }

    @AfterReturning(value = "execution(* az.atlacademy.demo.service.*.*(..))", returning = "returnValue")
    void afterService(JoinPoint joinPoint, Object returnValue) {
        log.info(joinPoint + "return: {}", returnValue);
    }

    @Before("execution(* az.atlacademy.demo.controller.*.*(..))")
    void beforeController(JoinPoint joinPoint) {
        log.info(joinPoint + "args: {}", joinPoint.getArgs());
    }

    @AfterReturning(value = "execution(* az.atlacademy.demo.controller.*.*(..))", returning = "returnValue")
    void afterController(JoinPoint joinPoint, Object returnValue) {
        log.info(joinPoint + "return: {}", returnValue);
    }
}
