package com.xj.groupbuy.common.config;

import com.xj.groupbuy.common.interceptor.RedisUrlCountInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Author : zhangxiaojian
 * Date : 2021/3/2
 */
//@Configuration
public class WebConfig implements WebMvcConfigurer {

    
//    @Autowired
//    RedisUrlCountInterceptor redisUrlCountInterceptor;
//    
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(redisUrlCountInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/js/**","/verifyCode");
//    }
//    @Bean
//    public MultipartResolver multipartResolver(){
//        return new CommonsMultipartResolver();
//    }

}
