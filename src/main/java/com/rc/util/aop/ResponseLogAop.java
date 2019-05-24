package com.rc.util.aop;

import com.rc.util.Impl.HttpResponseErrorLog;
import com.rc.util.Impl.HttpResponseLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
public class ResponseLogAop {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Around("within(*..*) && @annotation(rl)")
    public Object createResponseLog(ProceedingJoinPoint pjp, HttpResponseLog rl){
        Object object = null;
        logger.debug("错误");
        try {
            object = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return object;
    }

    @AfterThrowing(pointcut = "within(*..*) && @annotation(rl)", throwing = "e")
    public void createErrorLog(Throwable e, HttpResponseErrorLog rl){
        logger.debug("错误");
        System.out.println("111");
    }


}
