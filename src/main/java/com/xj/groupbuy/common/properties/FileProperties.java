package com.xj.groupbuy.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Author : zhangxiaojian
 * Date : 2021/4/7
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "upload")
@PropertySource("application.yml")
public class FileProperties {

    private String path;

}