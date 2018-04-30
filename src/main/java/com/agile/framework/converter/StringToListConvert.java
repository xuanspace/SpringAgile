package com.agile.framework.converter;


import org.springframework.core.convert.converter.Converter;

import java.util.Arrays;
import java.util.List;

public class StringToListConvert implements Converter<String, List<String>> {
    @Override
    public List<String> convert(String source) {
        if (source == null) {
            return Arrays.asList();
        } else {
            String[] split = source.split(",");
            return Arrays.asList(split);
        }
    }
}
