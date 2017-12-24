package com.agile.framework.exception;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@JsonAutoDetect(fieldVisibility=Visibility.NONE, getterVisibility=Visibility.NONE, isGetterVisibility=Visibility.NONE)
public class ValidationException extends Exception{

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private boolean success = false;

    @JsonProperty
    private List<String> errors = Lists.newArrayList();

    @JsonProperty
    private Map<String,String> fieldErrors = Maps.newHashMap();


    public ValidationException(){}

    public ValidationException(BindingResult bindingResult){
        for(FieldError error : bindingResult.getFieldErrors()){
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }
    }

    public boolean getSuccess(){
        return success;
    }

    public List<String> getErrors(){
        return errors;
    }

    public Map<String, String> getFieldErrors(){
        return fieldErrors;
    }
}
