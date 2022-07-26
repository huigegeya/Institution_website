package com.huige.Institution.validation;

import cn.hutool.core.util.ArrayUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {

    private String[] strValues;
    private int[] intValues;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        strValues = constraintAnnotation.strValues();
        intValues = constraintAnnotation.intValues();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null)
            return true;
        if (value instanceof String) {
            return ArrayUtil.contains(strValues, (String) value);
        } else if (value instanceof Integer) {
            return ArrayUtil.contains(intValues, (int) value);
        }
        return false;
    }
}

