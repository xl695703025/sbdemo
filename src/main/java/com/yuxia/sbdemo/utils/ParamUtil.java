package com.yuxia.sbdemo.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

/**
 * 参数校验util
 *
 * @author :  xiaolei
 * @date :  2019/3/14
 */
public class ParamUtil {
    private ParamUtil() {
    }

    /**
     * 校验参数
     *
     * @param param 待校验的参数
     */
    public static void validateParam(Object param) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(param);
        Iterator<ConstraintViolation<Object>> iter = violations.iterator();
        if (iter.hasNext()) {
            String errMessage = iter.next().getMessage();
            throw new RuntimeException(errMessage);
        }
    }
}
