package com.agile.framework.formatter;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

public class StringDateFormatter implements Formatter<List> {

    //解析接口，根据Locale信息解析字符串到T类型的对象；
    @Override
    public List parse(String text, Locale locale) throws ParseException {
        return null;
    }

    //格式化显示接口，将T类型的对象根据Locale信息以某种格式进行打印显示（即返回字符串形式）；
    @Override
    public String print(List object, Locale locale) {
        return "我是格式化的日期";
    }
}
