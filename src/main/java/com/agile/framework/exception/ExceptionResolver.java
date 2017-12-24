package com.agile.framework.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agile.framework.entity.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 *  全局异常处理类ExceptionResolver
 *
 *   HandlerExceptionResolver优先级高于@ControllerAdvice
 *
 *   配置文件添加@Component自动扫描
 *   <context:component-scan base-package="com.agile.framework.exception" />
 */

@Component
public class ExceptionResolver implements HandlerExceptionResolver {

    public static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

    /**
     * 通过ModelAndView返回
     * @param request
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
    	ModelAndView modelAndView = null;
    	exception.printStackTrace();
    	
        // 判断异常函数返回类型
        HandlerMethod methodHandler = (HandlerMethod)handler;
        Method method = methodHandler.getMethod();
        Type type= method.getGenericReturnType();  
        if (type.getClass().equals(ModelAndView.class)) {
        	modelAndView = handleViewException(request, response, handler, exception);        	
        }else {
        	modelAndView = handleRestException(request, response, handler, exception);
        }
        return modelAndView;
    }

    /**
     * 处理ModelAndView异常
     * @param request
     * @return
     */
    public ModelAndView handleViewException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ex.printStackTrace();
        ModelAndView modelAndView = new ModelAndView();
        
        /*
        //如果抛出的是系统自定义的异常则直接转换
        CustomException customException = null;
        if(ex instanceof CustomException) {
            customException = (CustomException) ex;
        } else {
            //如果抛出的不是系统自定义的异常则重新构造一个未知错误异常
            customException = new CustomException("系统未知错误");
        }
        
		FastJsonJsonView view = new FastJsonJsonView();  
        Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("code", "1000001");  
        attributes.put("msg", ex.getMessage());  
        view.setAttributesMap(attributes);  
        modelAndView.setView(view);   
        */
        
        modelAndView.addObject("message", "what");
        modelAndView.setViewName("/WEB-INF/jsp/error.jsp");
        return modelAndView;
    }
    
    /**
     * 处理Restfull异常
     * @param request
     * @return
     */
    public ModelAndView handleRestException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        exception.printStackTrace();

        if(exception instanceof BindException) {
            handleValidateException(request, response, handler, exception);
        }
        else{
            AjaxResult result = new AjaxResult();
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Cache-Control", "no-cache, must-revalidate");

            try {
                result.setException(exception);
                response.getWriter().write(result.toJson());
            } catch (IOException e) {
                logger.error("与客户端通讯异常:"+ e.getMessage(), e);
            }
        }
        return new ModelAndView();
    }

    /**
     * 处理校验异常
     * @param request
     * @return
     */
    public ModelAndView handleValidateException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        exception.printStackTrace();
        BindException bindException = (BindException)exception;
        BindingResult bindingResult = bindException.getBindingResult();

        String messsage = "Invalid Request:";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            messsage += fieldError.getDefaultMessage() + ", ";
        }

        AjaxResult result = new AjaxResult();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");

        try {
            result.setError(messsage);
            response.getWriter().write(result.toJson());
        } catch (IOException e) {
            logger.error("与客户端通讯异常:"+ e.getMessage(), e);
        }

        return new ModelAndView();
    }

}
