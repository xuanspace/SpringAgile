package com.agile.framework.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;

@Component
public class TemplateService {

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@Autowired
	private Configuration freeMarkerConfiguration;

	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.freeMarkerConfigurer = freeMarkerConfigurer;
	}

	public void setFreeMarkerConfiguration(Configuration freeMarkerConfiguration) {
		this.freeMarkerConfiguration = freeMarkerConfiguration;
	}

	public String getContent(String templateName, Map<String, Object> model) {
		return null;
	}
}