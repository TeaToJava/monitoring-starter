package ru.clevertec.monitoringstarter.aop;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.clevertec.monitoringstarter.config.PerformanceMonitorProperties;

@Slf4j
@Aspect
@AllArgsConstructor
public class PerformanceMonitorAspect {

    private final PerformanceMonitorProperties properties;

    @Pointcut("@annotation(MonitorPerformance)")
    public void loggableMethods() {

    }

    @Around("loggableMethods()")
    public Object doLoggingAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        try {
            long millis = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - millis;
            if (executionTime > properties.getMinLoggingInterval()) {
                log.info("Method [{}] executed in [{}] ms.", methodName, executionTime);
            }
            return result;
        } catch (Throwable e) {
            throw e;
        }
    }
}
