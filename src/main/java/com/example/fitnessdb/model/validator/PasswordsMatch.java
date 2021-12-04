package com.example.fitnessdb.model.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchImpl.class)
public @interface PasswordsMatch {

    String message() default "{javax.validation.constraints.PasswordMatch.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String firstF();

    String secondF();
}
