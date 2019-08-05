package com.yuxia.sbdemo.jsr;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author : xiaolei
 * @date : 2019/08/04
 */
public class IsEmailValidator implements ConstraintValidator<IsEmail, String> {

    @Override
    public void initialize(IsEmail constraintAnnotation) {
        //TODO: 初始化
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //TODO: 进行校验的逻辑
        return false;
    }
}