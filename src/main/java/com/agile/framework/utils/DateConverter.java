package com.agile.framework.utils;

import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.commons.beanutils.Converter;

@SuppressWarnings("unchecked")
public class DateConverter implements Converter {

    /**
     * 将日期字符串转Date类型
     * @param type 转换类型
     * @param value 日期字符串
     * @return Date类型
     */	
	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Class type, Object value) {
		if (type != Date.class)
			return null;
		if (!(value instanceof String))
			return value;
		if (value == null || "".equals(value.toString().trim()))
			return null;
		return StringUtils.toDate((String)value);
	}
}