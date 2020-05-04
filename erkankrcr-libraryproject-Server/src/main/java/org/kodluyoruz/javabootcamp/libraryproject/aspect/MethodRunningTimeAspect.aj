package org.kodluyoruz.javabootcamp.libraryproject.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.kodluyoruz.javabootcamp.libraryproject.annotion.MethodRunningTime;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MethodRunningTimeAspect {

    @Around("@annotation(methodRunningTimeAnnotion)")
    public Object execute(ProceedingJoinPoint point, MethodRunningTime methodRunningTimeAnnotion) throws Throwable {
        if(methodRunningTimeAnnotion.status()) {
            return point.proceed();
        }

        String className = point.getSignature().getDeclaringType().getSimpleName();
        String methodName = point.getSignature().getName();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = point.proceed();
        stopWatch.stop();
        System.out.println(className + "#" + methodName + " runned in  " + stopWatch.getTotalTimeMillis() + " ms");
        return result;
    }

}
