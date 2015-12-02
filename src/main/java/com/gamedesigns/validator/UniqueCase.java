package com.gamedesigns.validator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueCaseValidator.class)
@Documented
public @interface UniqueCase {

	String message() default "Value already exist in database";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
	
    public abstract String className();
    
    public abstract String columnName();
}
