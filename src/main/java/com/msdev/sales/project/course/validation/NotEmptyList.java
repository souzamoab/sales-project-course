package com.msdev.sales.project.course.validation;

import com.msdev.sales.project.course.validation.constraint.NotEmptyListValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyListValidator.class)
public @interface NotEmptyList {

    String message() default "List cannot be empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
