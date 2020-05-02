package com.bw.security;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy=UniqueValidator.class)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {
    String message() default "Value already exists";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    String table() default "";
    String column() default "";
}
