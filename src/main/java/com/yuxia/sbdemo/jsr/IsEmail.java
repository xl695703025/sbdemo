package com.yuxia.sbdemo.jsr;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 *
 * @author : xiaolei
 * @date : 2019/08/04
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IsEmailValidator.class })
public @interface IsEmail {

    String message() default "邮箱格式错误";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}