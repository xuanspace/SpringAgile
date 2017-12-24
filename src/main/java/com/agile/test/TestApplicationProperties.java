package com.agile.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")

public class TestApplicationProperties {

    // Spring中有配置文件读取
    @Value("${uploadFieldName}") String uploadFieldName ;

}

