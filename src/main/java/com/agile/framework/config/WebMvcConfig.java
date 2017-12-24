package com.agile.framework.config;

import com.agile.framework.interceptor.DataTableInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;

/**
 *  App SpringWeb配置
 *
 *  WebMvcConfig基以WebMvcConfigurerAdapter
 *  WebAppConfig基以WebMvcConfigurationSupport
 *  实现无spring*.xml配置(两者等同)
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.agile.framework.config")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 缺省Servlet配置
     *  Spring遇到没有mapping的url地址，就会转发到默认的Servlet处理
     *
     *  <mvc:default-servlet-handler/>
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 静态资源映射配置
     *
     *   <mvc:resources mapping="/images/**" location="/WEB-INF/images/" />
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/public-resources/");
    }

    /**
     * 格式化和转换器配置
     *
     * <bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
     *   <property name="converters">
     *   <set>
     *     <bean class="xxx.convert.StringToListConvert"/>
     *   </set>
     *   </property>
     * </bean>
     *
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
        //registry.addFormatter(new StringDateFormatter());
        //registry.addConverter(new StringToListConvert());
    }

    /**
     * 添加消息转换器
     *  HttpMessageConverter
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // equivalent to <mvc:argument-resolvers>
    }


    /**
     * 消息转换器配置
     *  HttpMessageConverter
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // equivalent to <mvc:message-converters>
    }

    /**
     * 拦截器配置
     *
     *	<mvc:interceptors>
     *	   <bean class="com.agile.framework.interceptor.DataTableInterceptor" />
     *	</mvc:interceptors>
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new DataTableInterceptor());
    }

    /**
     * 视图控制器配置
     *
     *   如：访问http://localhost:8080/error，即访问到error.jsp
     *   <mvc:view-controller path="/error" view-name="error"/>
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error")
                .setViewName("error");
    }

    /**
     * 视图控制器媒体类型配置
     *
     *   <mvc:view-controller path="/" view-name="home"/>
     *	    <bean id="contentNegotiationManager" class="org.springframework.web.accept.ContentNegotiationManagerFactoryBean">
     *	    <property name="mediaTypes">
     *	    <value>
     *	        json=application/json
     *	        xml=application/xml
     *	    </value>
     *	    </property>
     *	</bean>
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.mediaType("json", MediaType.APPLICATION_JSON);
    }


    /**
     * 视图解析配置
     *
     *   <mvc:view-resolvers>
     *       <mvc:content-negotiation>
     *           <mvc:default-views>
     *               <bean class="org.springframework.web.servlet.view.json.MappingJackson2JsonView"/>
     *           </mvc:default-views>
     *       </mvc:content-negotiation>
     *       <mvc:jsp/>
     *   </mvc:view-resolvers>
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //registry.enableContentNegotiation(new MappingJackson2JsonView());
        //registry.jsp();
    }

}
