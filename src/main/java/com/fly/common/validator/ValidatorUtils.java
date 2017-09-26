package com.fly.common.validator;

import com.fly.common.exception.RRException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 * <p>
 * Created by xinshidai on 17/9/18.
 */
public class ValidatorUtils {

    public static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 检验对象
     *
     * @param o      待校验对象
     * @param groups 待校验的组
     */
    public static void validateEntity(Object o, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(o, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (ConstraintViolation<Object> object : constraintViolations) {
                builder.append(object.getMessage()).append("</br>");
            }
            throw new RRException(builder.toString());
        }
    }
}
