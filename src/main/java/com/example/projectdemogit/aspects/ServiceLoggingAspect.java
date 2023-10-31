/*
package com.example.projectdemogit.aspects;


import com.example.projectdemogit.entity.Customer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class ServiceLoggingAspect {
    @Pointcut("within(com.example.projectdemogit.service.impl.*)")
    public void serviceMethods() {}

    @Around("serviceMethods()")
    public Object logAroundServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        log.info("Request URL: " + request.getRequestURL());
        log.info("Request Method: " + request.getMethod());

        if (joinPoint.getArgs().length > 0) {
            log.info("Call Around Before method: " + joinPoint.getArgs()[0]);
        }

        Object result = joinPoint.proceed();

        if (joinPoint.getArgs().length > 0 && !(result instanceof Customer)){
            log.info("Call Around After method: " + joinPoint.getArgs()[0]);
        }

        log.info("Response Status: " + response.getStatus());
        log.info("Response Body: " + result.toString());

        return result;
    }
}*/
