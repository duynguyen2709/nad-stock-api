package com.nad.util.annotation;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author duynguyen
 */
@Aspect
@Component
@Log4j2
public class TryCatchWrapAspect {

    @Around("@annotation(com.nad.util.annotation.TryCatchWrap)")
    public Object wrap(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Throwable ex) {
            log.error("[{}] Exception occurred: {}", joinPoint.getSignature(), ex.getMessage(), ex);
            throw ex;
        }
    }
}
