package com.xj.groupbuy.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/2
 */
//@Component
public class RedisUrlCountInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();

        redisTemplate.opsForValue().increment(uri);
        return true;
    }
}
