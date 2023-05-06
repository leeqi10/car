package com.xhu.config;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@Aspect
public class LogAop {
    //日志注入
    private Logger logger = LoggerFactory.getLogger(LogAop.class);

    @Pointcut("execution( * com.xhu.controller.*.*(..))")
    public void LogAspect(){}

    //日常日志记录
    @AfterReturning(value = "LogAspect()",returning="returnValue")
    public void doAfterReturning(JoinPoint joinPoint,Object returnValue){
        logger.info("=====请求开始=====");
        logger.info("请求的类"+joinPoint.getSignature().getDeclaringType().toString());
        logger.info("调用的方法：" + joinPoint.getSignature().toString());
        logger.info("传入的参数：" + Arrays.toString(joinPoint.getArgs()));
        logger.info("方法返回值：" + JSONObject.toJSONString(returnValue));
        logger.info("=====请求结束=====");
    }

    //异常统一捕获
    @AfterThrowing(value = "LogAspect()",throwing = "exception")
    public void deAfterThrowing(JoinPoint joinPoint,Exception exception){
        logger.error("=====异常=====");
        logger.info("请求的类"+joinPoint.getSignature().getDeclaringType().toString());
        logger.error("调用的方法：" + joinPoint.getSignature().toString());
        logger.info("传入的参数：" + Arrays.toString(joinPoint.getArgs()));
        logger.error(exception.getMessage());
        logger.error("=====异常=====");
    }
}