package com.agile.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.JstlView;

@Configuration
public class ViewResolverConfig {

    @Bean(name = "jspViewResolver")
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        // for debug envirment
        viewResolver.setCache(false);
        return viewResolver;
    }

}