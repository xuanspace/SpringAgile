package com.agile.framework.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * SpringWeb App配置
 *
 *  @Configuration
 *      表示该类包含@Bean注解的方法,由spring容器管理产生beans
 *  @ComponentScan
 *      等同于XML配置文件中的context:component-scan base-package="..."
 *  @PropertySource
 *      指定classpath路径下的properties文件
 *  @EnableWebMvc
 *      等同于XML配置文件中mvc:annotation-driven
 *  @EnableTransactionManagement
 *      等同于XML配置文件中的tx:*命名空间声明
 *
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.agile.framework.config")
public class AppConfig {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

}
