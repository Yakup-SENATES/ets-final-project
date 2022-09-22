package com.etstur.finalproject.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = FieldMatchValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldMatch {

    // Custom annotation for finding matches between two string fields
    String message() default "Fields don't match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    // make annotationList to match each one is in.
    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        FieldMatch[] value();
    }

    String first();
    String second();
}
