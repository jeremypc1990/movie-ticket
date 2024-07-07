package com.code.movie.ticket.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TicketRequestValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface ValidTicketRequest {

    String message() default "The request payload is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
