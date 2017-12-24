package com.agile.framework.validate;

import com.agile.framework.annotation.Money;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MoneyValidator implements ConstraintValidator<Money, Double> {

    private String moneyReg = "^\\d+(\\.\\d{1,2})?$";//表示金额的正则表达式
    private Pattern moneyPattern = Pattern.compile(moneyReg);

    public void initialize(Money money) {
    }

    public boolean isValid(Double value, ConstraintValidatorContext arg1) {
        if (value == null)
            return true;
        return moneyPattern.matcher(value.toString()).matches();
    }

}
