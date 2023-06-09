package com.xhu.config;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Configuration
@Aspect
public class LogAop {
    //日志注入
    private Logger logger = LoggerFactory.getLogger(LogAop.class);
    //获取请求头
    @Autowired
    private HttpServletRequest httpServletRequest;
    //切入点方法
    @Pointcut("execution( * com.xhu.controller.*.*(..))")
    public void LogAspect(){}

    //日常日志记录
    @AfterReturning(value = "LogAspect()",returning="returnValue")
    public void doAfterReturning(JoinPoint joinPoint,Object returnValue){
        logger.info("=====请求开始=====");
        logger.info("远程地址（可能非真实地址）"+httpServletRequest.getRemoteAddr());
        logger.info(getIp3(httpServletRequest));
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

    /**
     *
     * @param request
     * @return 返回的客户的真实地址
     */
    public static String getIp3(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return "Client real IP address: " + ipAddress.split(",")[0];
    }
}