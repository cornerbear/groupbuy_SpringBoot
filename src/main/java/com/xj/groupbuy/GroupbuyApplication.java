package com.xj.groupbuy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
@MapperScan("com.xj.groupbuy.mapper")
public class GroupbuyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(GroupbuyApplication.class, args);
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }

}
