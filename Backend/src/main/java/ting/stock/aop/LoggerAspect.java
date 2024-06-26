package ting.stock.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggerAspect {


    @Pointcut("@annotation(LogAOP)")
    public void saveLog(){}

    // save logs into log file synchronized
    @AfterThrowing(pointcut = "saveLog()",throwing = "e")
    public synchronized void  saveErrorLogAdvice(JoinPoint joinPoint, Throwable e){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String message = String.format ("Exception in %s.%s() with cause = %s",
                signature.getDeclaringTypeName(),
                signature.getName(),
                e.getCause() != null ? e.getCause().toString() : "NULL");

        log.error(message);

//        String session = Arrays.stream(joinPoint.getArgs()).filter(arg -> arg)

    }
}
