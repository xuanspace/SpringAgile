package com.agile.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *  App SpringWeb配置
 *
 *  WebAppConfig基以WebMvcConfigurationSupport
 *  WebMvcConfig基以WebMvcConfigurerAdapter
 *  实现无spring*.xml配置(两者等同)
 *
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.agile.framework.config")
class WebAppConfig extends WebMvcConfigurationSupport {

    @Bean
    public DomainClassConverter<?> domainClassConverter() {
        return new DomainClassConverter<FormattingConversionService>(mvcConversionService());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    /**
     * 配置JSP视图解析器
     *
     * 	<bean id="jspViewResolver"
     *     class="org.springframework.web.servlet.view.InternalResourceViewResolver">
     * 		<property name="viewClass" value="org.springframework.web.servlet.view.JstlView" />
     * 		<property name="prefix" value="/WEB-INF/jsp/"/>
     * 		<property name="suffix" value=".jsp"/>
     *         <property name="order" value="1" />
     * </bean>
     */
    @Bean
    public ViewResolver addJspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setOrder(1);
        return viewResolver;
    }
}