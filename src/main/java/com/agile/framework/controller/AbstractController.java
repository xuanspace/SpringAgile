package com.agile.framework.controller;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;

import com.agile.framework.validate.AbstractValidator;
import com.agile.framework.validate.EntityAssist;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.agile.framework.entity.AjaxResult;
import com.agile.framework.query.Builder;
import com.agile.framework.service.IDaoService;
import com.agile.framework.utils.EntityUtils;

public class AbstractController implements IController {

    public static final Logger logger = LoggerFactory.getLogger(AbstractController.class.getName());

    /**
     * 请求方法映射
     */
    @Autowired
    protected RequestMappingHandlerMapping handlerMapping;

    /**
     * 校验信息实例
     */
    @Autowired
    protected MessageSource messageSource;

    /**
     * 缺省构造函数
     */
    public AbstractController() {

    }

    /**
     * 注册日期校验器
     * @return binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 设置日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);

        //true:允许输入空值
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 获取控制器注解路径
     * @return String
     */
    public String getRequestMappingPath() {
        Class<?> clazz = this.getClass();
        for (Annotation annotation : clazz.getAnnotations()) {
            Class<? extends Annotation> type = annotation.annotationType();
            if( annotation instanceof RequestMapping ) {
                for (Method method : type.getDeclaredMethods()) {
                    try {
                        if (method.getName().equals("value")) {
                            String[] paths = (String[])method.invoke(annotation, (Object[])null);
                            if (paths != null && paths.length > 0)
                                return paths[0];
                        }
                    }catch (Exception e) {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 获取请求的映射URL集合
     * @return List 返回映射的URL
     */
    public List<String> getRequstMappingUrls(HttpServletRequest request) {
        List<String> urlMap = new ArrayList<String>();
        // 获取当前请求路径
        String path = request.getScheme()+"://"+request.getServerName()+":"
                +request.getServerPort()+request.getContextPath();
        // 获取映射的方法和URL
        Map<RequestMappingInfo, HandlerMethod> map = handlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> item : map.entrySet()) {
            RequestMappingInfo info = item.getKey();
            HandlerMethod handle = item.getValue();
            // 当前类的路径映射
            if (handle.getBeanType().equals(this.getClass())) {
                // 去掉头尾[]字符
                String patern = info.getPatternsCondition().toString().substring(1);
                patern = patern.substring(0, patern.length()-1);
                urlMap.add(path + patern);
            }
        }
        return urlMap;
    }

    /**
     * 将请求参数转成对象T
     * @param request
     * @return 对象T实例
     *
     */
    public <T> T getRequestEntity(HttpServletRequest request, Class<T> clazz) throws Exception {
        T entity = null;
        Map map = request.getParameterMap();
        if (map.size() > 0) {
            entity = (T) EntityUtils.toBean(map, clazz);
        }
        return entity;
    }

    /**
     * 获取控制器URL映射列表
     * 	http://localhost/xxx/api
     * @return List 返回映射的URL
     */
    @ResponseBody
    @RequestMapping(value="/api", method= RequestMethod.GET,produces={"application/json;charset=UTF-8"})
    public Object api(HttpServletRequest request) throws Exception {
        return getRequstMappingUrls(request);
    }
}
