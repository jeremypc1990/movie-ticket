package com.code.movie.ticket.validation;

import ch.qos.logback.core.util.StringUtil;
import com.code.movie.ticket.model.TicketReportRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TicketRequestValidator implements ConstraintValidator<ValidTicketRequest, TicketReportRequest> {


    @Override
    public void initialize(ValidTicketRequest constraintAnnotation) {
    }

    @Override
    public boolean isValid(TicketReportRequest request, ConstraintValidatorContext context) {

        if (request == null) {
            return false;
        }

        var customers = request.customers();
        var transactionId = request.transactionId();

        if (transactionId == null || customers == null || customers.isEmpty()) {
            return false;
        }

        return customers.stream().filter(customer -> customer.age() <= 0).toList().isEmpty() &&
                customers.stream().filter(customer -> StringUtil.isNullOrEmpty(customer.name())).toList().isEmpty();
    }

}
