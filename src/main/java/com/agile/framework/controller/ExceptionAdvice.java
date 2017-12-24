package com.agile.framework.controller;

import javax.servlet.http.HttpServletRequest;

import com.agile.framework.controller.ControllerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.agile.framework.entity.AjaxResult;

/**
 *  控制器统一异常处理
 *
 *  ExceptionAdvice   提供控制器的异常处理
 *  ExceptionResolver 提供统一异常处理, 优先级高于ExceptionAdvice
 *  @ControllerAdvice 组合@Component注解,自动注册为Bean
 *
 *  配置文件添加自动扫描
 *    <context:component-scan base-package="xxx.controller" use-default-filters="false">
 *       <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller" />
 *       <context:include-filter type="annotation" expression="org.springframework.web.bind.annotation.RestController" />
 *       <context:include-filter type="annotation" expression="org.springframework.web.bind.annotation.ControllerAdvice" />
 *    </context:component-scan>
 *
 */

@ControllerAdvice
public class ExceptionAdvice {

	public static final Logger logger = LoggerFactory.getLogger(ControllerException.class);

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private AjaxResult handlerException(Exception e) {
        AjaxResult response = new AjaxResult(e);
        return response;
    }


}
