package com.agile.framework.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringToPhoneNumberConverter implements Converter<String, String> {
    Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");
    @Override
    public String convert(String source) {
        if(!StringUtils.hasLength(source)) {
            //如果source为空,返回null
            return null;
        }
        Matcher matcher = pattern.matcher(source);
        if(matcher.matches()) {
            //如果匹配,进行转换
            return source;
        } else {
            // 如果不匹配,转换失败
            throw new IllegalArgumentException(String.format("类型转换失败，需要格式[010-12345678]，但格式是[%s]", source));
        }
    }
}