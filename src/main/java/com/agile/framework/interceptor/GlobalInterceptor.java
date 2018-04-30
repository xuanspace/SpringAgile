package com.agile.framework.interceptor;

import com.agile.framework.controller.AbstractDaoController;
import com.agile.framework.entity.DataTableParameter;
import com.agile.framework.entity.DataTableResponse;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.query.Builder;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*
 * GlobalInterceptor全局拦截器
 *
 * 配置文件添加拦截器
 * <mvc:interceptors>
 *	<bean class="com.agile.framework.interceptor.GlobalInterceptor" />
 * </mvc:interceptors>
 *
 */
public class GlobalInterceptor implements HandlerInterceptor {

    public static final Logger logger = LoggerFactory.getLogger(DataTableInterceptor.class.getName());

    /**
     * 当前页面访问基础路径
     */
    private String basePath = "";

    /**
     * 设置请求页面基础路径变量
     */
    public void setBasePathAttribute(HttpServletRequest request) {
        // 获取当前请求路径
        String path = request.getScheme()+"://"+request.getServerName()+":"
                +request.getServerPort()+request.getContextPath();
        HttpSession session = request.getSession();
        session.setAttribute("basePath", path);
    }

    /**
     * controller执行前调用此方法
     * 返回true表示继续执行，返回false中止执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 设置页面basePath变量
        setBasePathAttribute(request);
        return true;
    }

    /**
     * controller执行后但未返回视图前调用此方法
     * 可在返回用户前对模型数据进行处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    /**
     * controller执行后且视图返回后调用此方法
     * 这里可得到执行controller时的异常信息
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}
