package com.xj.groupbuy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@SpringBootApplication
@MapperScan("com.xj.groupbuy.mapper")
public class GroupbuyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupbuyApplication.class, args);
//        ConfigurableApplicationContext run =
//        String[] names = run.getBeanDefinitionNames();
//        for (String name : names) {
//            System.out.println(name);
//        }
    }
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
            factory.addErrorPages(error404Page);
        };
    }

}
