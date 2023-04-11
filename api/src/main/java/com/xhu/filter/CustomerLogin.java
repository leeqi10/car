package com.xhu.filter;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xhu.ResponseStatusEnum;
import com.xhu.Result;
import com.xhu.annotation.FilterLogin;
import com.xhu.entity.Passenger;
import com.xhu.mapper.PassengerMapper;
import com.xhu.utils.JwtUtil;
import com.xhu.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Objects;

@Component
public class CustomerLogin implements HandlerInterceptor {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private PassengerMapper passengerMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            FilterLogin loginRequired = method.getAnnotation(FilterLogin.class);
            if (loginRequired!=null){
                String token = request.getHeader("token");
                //解析token
                String userId;
                try {
                    Claims claims = JwtUtil.parseJWT(token);
                    userId = claims.getSubject();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("token非法");
                }
                //从redis获取用户信息
                String redisKey = "login:" + userId;
                Passenger loginUser = redisCache.getCacheObject(redisKey);
                //为空的情况下
                if (Objects.isNull(loginUser)){
                    LambdaQueryWrapper<Passenger> queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.eq(Passenger::getUser, loginUser.getUser());
                    Passenger user = passengerMapper.selectOne(queryWrapper);
                    if (user==null){
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json");
                        PrintWriter out = response.getWriter();
                        Result result = new Result(ResponseStatusEnum.FAILED);
                        out.write(new ObjectMapper().writeValueAsString(result));
                        out.flush();
                        out.close();
                        return false;
                    }
                }

            }
            return true;
        }
            //若有LoginRequired注解进行拦截
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
