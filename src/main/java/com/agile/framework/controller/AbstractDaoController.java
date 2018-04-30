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

public abstract class AbstractDaoController<T> extends AbstractController {

	public static final Logger logger = LoggerFactory.getLogger(AbstractDaoController.class.getName()); 
	
	/**
	 * 服务基类实例
	 */
	private IDaoService<T> daoService;

    /**
     * 校验基类实例
     */
    private AbstractValidator<T> daoValidator;

	/**
	 * 泛型参数T的类型
	 */
    protected Class<T> entityClass;

    /**
     * 泛型参数T的ID的类型
     */
    private Class<?> entityIdClass;

    /**
     * 缺省构造函数
     */	
    public AbstractDaoController() {
    	this.getEntityIdClass();
    }

    /**
     * 构造函数注入服务
     */
    public AbstractDaoController(IDaoService<T> service) {
        this.daoService = service;
        this.daoValidator = null;
        this.getEntityIdClass();
    }

    /**
     * 构造函数注入服务和校验
     */
    public AbstractDaoController(IDaoService<T> service, AbstractValidator<T> validator) {
    	this.daoService = service;
    	this.daoValidator = validator;
    	this.getEntityIdClass();
    }

    /**
     * 注册校验器
     * @return binder
     */
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        //binder.replaceValidators(new XxxValidator());
    }

    /**
     * 获取控制器服务
     * @return 返回服务实例
     */	
	public IDaoService<T> getService() {
		return daoService;
	}
	
    /**
     * 返回参数T的类型
     * @return the entity class
     */    
    @SuppressWarnings("unchecked")
	protected Class<?> getEntityClass() {
        if (entityClass == null) {
            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return entityClass;
    }

    /**
     * 返回参数T的主键ID类型
     * @return the entity class
     */        
	public Class<?> getEntityIdClass() {
		if (entityIdClass == null) {
			entityIdClass = EntityUtils.getIdClass(getEntityClass());
		}
		return entityIdClass;
	}

    /**
     * 获取校验器
     * @return validator
     */
    public AbstractValidator<T> getValidtor() {
        return daoValidator;
    }
	
    /**
     * 将请求参数转成对象T
     * 当对实体对象进行校验，有错误抛出异常，在ExceptionResolver中统一处理
     * @param request
     * @return 对象T实例
     *
     */
    @SuppressWarnings({"unchecked","rawtypes"})
	public T getRequestEntity(HttpServletRequest request) throws Exception {
    	T entity = null;
        Map map = request.getParameterMap();
        if (map.size() > 0) {
            // 是否对实体类动态注解
            //Class<?> clazz = getValidtor().getEntityAssist().getTypeClass();
            Class<?> clazz = getEntityClass();
            entity = (T) EntityUtils.toBean(map, clazz);
            daoValidator.validate(entity);
        }
        return entity;
    }

    //
    // Restful API
	//

    /**
     * GET(SELECT)：从服务器查询
     * http://localhost/xxx/{id}
     *
     * @return T 实体对象
     */    
	@ResponseBody
    @RequestMapping(value="{id:[0-9]+}", method=RequestMethod.GET,produces={"application/json;charset=UTF-8"})
    public AjaxResult get(HttpServletRequest request) throws Exception {
		AjaxResult result = new AjaxResult();
        RestParameter params = new RestParameter(request);
        Serializable id = params.getId(entityIdClass);
        if (id != null) {
            Object entity = daoService.get(id);
            result.setData(entity);
        }else {
        	result.setError("参数错误");
        }
        return result;
    }

    /**
     * 添加对象
     * POST（CREATE）：在服务器新建一个资源
     * @param request
     * @return String
     * @throws Exception  
     */
    @ResponseBody
    @RequestMapping(method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
    public AjaxResult post(HttpServletRequest request, HttpSession session) throws Exception {
    	AjaxResult result = new AjaxResult();
		T entity = getRequestEntity(request);
		if (entity != null) {
			daoService.save(entity);
		}else {
			result.setError("参数错误");
		}
        return result;
    }
    
    /**
     * 添加对象
     * PUT(UPDATE)：在服务器更新资源 
     * @param request
     * @return String
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(method=RequestMethod.PUT,produces={"application/json;charset=UTF-8"})
    public AjaxResult put(HttpServletRequest request, HttpSession session) throws Exception{
    	AjaxResult result = new AjaxResult();
		T entity = getRequestEntity(request);
		if (entity != null) {
			daoService.update(entity);
		}else{
			result.setError("参数错误");
		}
    	return result;
    }

    /**
     * 删除对象
     * DELETE(DELETE)：从服务器删除资源
     *  http://localhost/xxx/{id}
     * @param request
     * @return String 
     */
    @ResponseBody
    @RequestMapping(value="{id}", method=RequestMethod.DELETE,produces={"application/json;charset=UTF-8"})
    public AjaxResult delete(HttpServletRequest request) throws Exception {
    	AjaxResult result = new AjaxResult();    	
		RestParameter params = new RestParameter(request);
		Serializable id = params.getId(entityIdClass);
		if(id != null) {
			daoService.delete(id);
		}else {
			result.setError("参数错误");
		}
    	return result;
    }


    /**
     * 获取列表数据请求
     * http://localhost/xxx?page=x&size=x
     * @return list
     */    
	@ResponseBody 
    @RequestMapping(method=RequestMethod.GET, produces={"application/json;charset=UTF-8"})
    public AjaxResult list(HttpServletRequest request) throws Exception {
		AjaxResult result = new AjaxResult();
        RestParameter params = new RestParameter(request);
        Integer page = params.getPage();
        Integer size = params.getSize();
        if (page != null && size != null) {
            Builder builder = daoService.queryBuilder();
            builder.offset(page*size).limit(size);
            result.setData(builder.list());
        }else {
        	result.setError("参数错误");
        }
		return result;
    }
	
	
    // Restful页面 API

    /**
     * 对象列表页面
     * http://localhost/xxx/index?page=x&size=x
     * @return ModelAndView
     */
    @RequestMapping(value="/index", method= RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model){
    	List<?> data = null;
    	RestParameter params = new RestParameter(request);
    	
		try {
	        Integer page = params.getPage();
	        Integer size = params.getSize();
	        if (page != null && size != null) {
	            Builder builder = daoService.queryBuilder();
	            builder.offset(page*size).limit(size);
	            data = builder.list();
	        }
	        else{
	        	data = daoService.getList();
	        }
        } catch (Exception e) {
        	e.printStackTrace();
        }

        //String name = entityClass.getSimpleName().toLowerCase() + "List";
        model.addAttribute("list", data);
        return "user/index";
    }

    /**
     * 添加对象页面
     * http://localhost/xxx/create
     * @return ModelAndView
     */
    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap model) throws Exception {
		T entity = (T)getEntityClass().newInstance();
        String name = entityClass.getSimpleName().toLowerCase();
        model.addAttribute(name, entity);
        model.addAttribute("status", "create");
        return "/user/create";
    }

    /**
     * 保存对象页面
     * http://localhost/xxx/save
     * @return url
     */
    @RequestMapping(value = {"/save"}, method = RequestMethod.POST)
    public String save(@Valid T entity, BindingResult result, ModelMap model) throws Exception {
        if (result.hasErrors()) {
            return "/user/create";
        }
        model.addAttribute("status", "success");
        return "/user/index";
    }

    /**
     * 显示对象页面
     * http://localhost/xxx/show/{id}
     *
     * @param id
     * @return url
     */
    @RequestMapping(value="/show/{id}", method=RequestMethod.GET)
    public String show(@PathVariable String id, HttpServletRequest request, ModelMap model) throws Exception {
        T entity = daoService.get(id);
        String name = entityClass.getSimpleName().toLowerCase();
        model.addAttribute(name, model);
        return "user/show";
    }
    
    /**
     * 编辑对象页面
     * @param id
     * @return url
     */
    @RequestMapping(value="/edit/{id}")
    public String edit(@PathVariable String id, HttpServletRequest request, ModelMap model) {
    	T entity = daoService.get(id);
        String name = entityClass.getSimpleName().toLowerCase();
        model.addAttribute(name, model);
        model.addAttribute("status", "edit");
        return "user/edit";
    }
    
    /**
     * 更新对象页面
     * @param entity
     * @return url
     */
    @RequestMapping(value="/update", method = RequestMethod.PUT)
    public String update(@Valid T entity, BindingResult result, ModelMap model) throws Exception {
        if (result.hasErrors()) {
            return "/user/edit";
        }
        daoService.update(entity);
        model.addAttribute("status", "success");
        return "user/index";
    }
    
    /**
     * 删除对象页面
     * @param id
     */
    @ResponseBody
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public String remove(@PathVariable String id, HttpServletRequest request, ModelMap model) {
        daoService.delete(id);
        model.addAttribute("id", id);
        model.addAttribute("status", "success");
        return "user/index";
    }

}
