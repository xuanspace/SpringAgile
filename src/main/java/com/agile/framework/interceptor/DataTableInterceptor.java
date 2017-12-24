package com.agile.framework.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.agile.framework.controller.AbstractDaoController;
import com.agile.framework.entity.DataTableParameter;
import com.agile.framework.entity.DataTableResponse;
import com.agile.framework.entity.SearchCondition;
import com.agile.framework.persistence.IBaseDao;
import com.agile.framework.query.Builder;
import com.agile.framework.utils.CriteriaUtils;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* 
 * DataTable Ajax请求拦截器
 *   https://datatables.net
 * 
 * 配置文件添加拦截器
 * <mvc:interceptors> 
 *	<bean class="com.agile.framework.interceptor.DataTableInterceptor" />
 * </mvc:interceptors>
 *     
 */

public class DataTableInterceptor implements HandlerInterceptor {

	public static final Logger logger = LoggerFactory.getLogger(DataTableInterceptor.class.getName());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			printInterceptorInfo(request, method);
			
			// 拦截Controller中的list函数
			if (method.getMethod().getName().equals("list")) {
				if (request.getParameter("aoData") != null) {
					DataTableParameter params = new DataTableParameter(request);
					DataTableResponse result = intercept(method, params);
					setResponse(response, result);
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	private DataTableResponse intercept(HandlerMethod handle, DataTableParameter params) {
		AbstractDaoController<?> controller = (AbstractDaoController<?>) handle.getBean();
		if (params != null) {
			IBaseDao<?> dao = controller.getService().getDao();
			if (dao != null) {
				// 查询数据
				Builder builder = dao.queryBuilder();				
				Integer size = params.getiDisplayLength();
				Integer star = params.getiDisplayStart();
				builder.limit(size).offset(star);
				long count = dao.getCount();
				List<?> data = builder.list();
				
				// 返回数据
				DataTableResponse result = new DataTableResponse();
				result.setsEcho(params.getsEcho());
				result.setiTotalRecords(count);
				result.setiTotalDisplayRecords(count);
				result.setAaData(data);
				return result;
			}			
		}
		return null;
	}	
	
	private void setResponse(HttpServletResponse response, DataTableResponse result) throws Exception {
		PrintWriter writer = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		try {
			writer = response.getWriter();
			if (result != null) {
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(result);
				writer.print(json);
			}else {
				writer.print("fail");
			}			
		} catch (IOException e) {
			logger.error("response error", e);
		} finally {
			if (writer != null)
				writer.close();
		}
	}

	private String getParameterString(Map<String, String[]> map) {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String[]> e : map.entrySet()) {
			sb.append(e.getKey()).append("=");
			String[] value = e.getValue();
			if (value != null && value.length == 1) {
				sb.append(value[0]).append("\t");
			} else {
				sb.append(Arrays.toString(value)).append("\t");
			}
		}
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	private void printInterceptorInfo(HttpServletRequest request, HandlerMethod handler) {
		StringBuilder sb = new StringBuilder(1000);
		sb.append("Controller: ").append(handler.getBean().getClass().getName()).append("\n");
		sb.append("Method    : ").append(handler.getMethod().getName()).append("\n");
		sb.append("Params    : ").append(getParameterString(request.getParameterMap())).append("\n");
		sb.append("URI       : ").append(request.getRequestURI()).append("\n");
		System.out.println(sb.toString());
	}
}
