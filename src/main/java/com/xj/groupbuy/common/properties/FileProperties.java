package com.xj.groupbuy.common.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Author : zhangxiaojian
 * Date : 2021/4/7
 */
@Data
@Component
@ConfigurationProperties(prefix = "upload")
public class FileProperties {

    private String path;

}