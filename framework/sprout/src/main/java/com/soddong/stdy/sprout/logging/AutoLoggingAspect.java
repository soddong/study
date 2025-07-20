package com.soddong.stdy.sprout.logging;

import com.soddong.stdy.sprout.annotation.AutoGet;
import com.soddong.stdy.sprout.annotation.AutoPost;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AutoLoggingAspect {

    @Around("@annotation(autoGet) || @annotation(autoPost)")
    public Object log(ProceedingJoinPoint joinPoint, AutoGet autoGet, AutoPost autoPost) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        System.out.println("[" + methodName + "] called with: " + Arrays.toString(args));
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("[" + methodName + "] returned in " + (end - start) + "ms");

        return result;
    }
}
