package com.agile.framework.application;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import com.agile.framework.filter.CORSFilter;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.Log4jConfigListener;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * SpringWeb App启动初始化
 *
 * AppInitializer自动加载无需配置
 *   1.如果存在web.xml,通过web.xml配置文件初始化
 *   2.如果不存在web.xml,通过代码方式实现无web.xml配置
 *
 */
public class AppInitializer implements WebApplicationInitializer {

    private static final String SERVLET_NAME = "springMvc";
    private static final String SPRING_MVC_CONFIG = "classpath*:/spring-mvc.xml";
    private static final String SPRING_HIBERNATE_CONFIG = "classpath*:/spring-hibernate.xml";
    private static final String LOG4J_CONFIG = "classpath:log4j.properties";
    private static final String LOG4J2_CONFIG = "classpath:log4j2.properties";

    private static final long MAX_FILE_UPLOAD_SIZE = 1024 * 1024 * 5;  //5 Mb
    private static final int FILE_SIZE_THRESHOLD = 1024 * 1024; // After 1Mb
    private static final long MAX_REQUEST_SIZE = -1L;  // No request size limit

    private ServletContext servletContext;
    private WebApplicationContext appContext;

    /**
     * 服务器启动调用onStartup()初始化系统
     *
     * @param servletContext
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        this.servletContext = servletContext;

        // 没有web.xml文件，则代码方式实现web.xml中的配置
        if (!isExistWebXmlConfigFile()) {
            // Spring, hibernate使用xml配置
            addSpringConfig();
            addHibernateConfig();
            addSitemeshConfig();
            addCorsFilter();
            addLog4JConfig();
        }
        // 存在web.xml文件, 系统自动加载web.xml中的配置
        else{
            printRegisterFilters();
        }
    }

    /**
     * 通过Xml来配置应用
     * @param locations
     * @return
     */
    private XmlWebApplicationContext createAppContext(String... locations) {
        // Spring context 配文件参数（等同appContext.setConfigLocations）
        //servletContext.setInitParameter("contextConfigLocation", "classpath:");

        // 设置Spring 配置文件
        XmlWebApplicationContext xmlContext = new XmlWebApplicationContext();
        xmlContext.setConfigLocations(locations);
        xmlContext.setServletContext(servletContext);
        return xmlContext;
    }

    /**
     * 通过注解来配置应用
     *
     * @param annotatedClasses
     * @return
     */
    private AnnotationConfigWebApplicationContext createAppContext(Class<?>... annotatedClasses) {
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(annotatedClasses);
        return appContext;
    }

    /**
     * 是否存在WEB_INF/web.xml配置文件
     *
     */
    private boolean isExistWebXmlConfigFile() {
        String path = servletContext.getRealPath("/WEB-INF/web.xml");
        File file = new File(path);
        if(file.exists() && !file.isDirectory()) {
            return true;
        }
        return false;
    }

    /**
     * 添加Spring配置
     *
     */
    private void addSpringConfig() {
        // 创建Spring context
        WebApplicationContext appContext = createAppContext(SPRING_MVC_CONFIG, SPRING_HIBERNATE_CONFIG);

        // 设置Spring listener
        servletContext.addListener(new ContextLoaderListener(appContext));

        // 设置Spring servlet
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(SERVLET_NAME, new DispatcherServlet(appContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        // 设置Spring字符集
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
        encodingFilter.setInitParameter("encoding", String.valueOf(StandardCharsets.UTF_8));
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, false, "/*");

        // 设置Spring PUT与DELETE请求
        FilterRegistration.Dynamic filter = servletContext.addFilter("hiddenHttpMethodFilter", HiddenHttpMethodFilter.class);
        filter.addMappingForServletNames(null, false,"springMVC");
    }

    /**
     * 添加Hibernate配置
     *
     */
    private void addHibernateConfig() {
        FilterRegistration.Dynamic filter = servletContext.addFilter("hibernateFilter", OpenSessionInViewFilter.class);
        filter.addMappingForUrlPatterns(null, false, "/custom/*");
        filter.setInitParameter("sessionFactoryBeanName", "sessionFactory");
        filter.setInitParameter("flushMode", "AUTO");
        filter.addMappingForUrlPatterns(null, false, "/*");
    }

    /**
     * 添加Sitemesh配置
     *
     */
    private void addSitemeshConfig() {
        FilterRegistration.Dynamic filter = servletContext.addFilter("sitemesh", ConfigurableSiteMeshFilter.class);
        filter.addMappingForUrlPatterns(null, false, "/*");
    }

    /**
     * 添加跨域访问配置
     *
     */
    private void addCorsFilter() {
        // Spring PUT与DELETE请求配置
        FilterRegistration.Dynamic filter = servletContext.addFilter("CorsFilter", CORSFilter.class);
        filter.addMappingForUrlPatterns(null, false, "/*");
    }

    /**
     * 添加Log4J配置
     *
     */
    private void addLog4JConfig() {
        servletContext.setInitParameter("log4jConfigLocation", LOG4J_CONFIG);
        servletContext.addListener(Log4jConfigListener.class);
    }

    /**
     * 添加Log4J2配置
     *
     */
    private void addLog4J2Config(ServletContext servletContext) {
        servletContext.setInitParameter("log4j2ConfigLocation", LOG4J2_CONFIG);
        //servletContext.addListener(Log4jServletFilter.class);
    }

    /**
     * 打印已经注册的Filter
     *
     */
    private void printRegisterFilters() {
        Map<String, ? extends FilterRegistration> map = servletContext.getFilterRegistrations();
        for (Map.Entry<String, ? extends FilterRegistration> item : map.entrySet()) {
            String filterName = item.getKey();
            Class<?> clazz = item.getValue().getClass();
            String className = clazz.getClass().getName();
            System.out.println("Filter name:" + filterName + ","+ className);
        }
    }

}
