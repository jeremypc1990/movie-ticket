package com.code.movie.ticket.validation;

import com.code.movie.ticket.model.Customer;
import com.code.movie.ticket.model.TicketReportRequest;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TicketRequestValidatorTest {

    @Mock
    private ConstraintValidatorContext context;

    private TicketRequestValidator validator;

    private final Long transactionId = 123L;
    private final List<Customer> customers = List.of(new Customer("test1", 10), new Customer("test2", 20));

    @BeforeEach
    public void setup() {
        validator = new TicketRequestValidator();
    }

    @Nested
    class IsValid {

        @Test
        public void validRequest() {
            var request = new TicketReportRequest(transactionId, customers);

            assertTrue(validator.isValid(request, context));
        }

    }

    @Nested
    class IsNotValid {

        @Test
        public void nullRequest() {
            assertFalse(validator.isValid(null, context));
        }

        @Test
        public void nullTransactionIdRequest() {
            var request = new TicketReportRequest(null, customers);

            assertFalse(validator.isValid(request, context));
        }

        @Test
        public void nullCustomersRequest() {
            var request = new TicketReportRequest(transactionId, null);

            assertFalse(validator.isValid(request, context));
        }

        @Test
        public void nullCustomerNameRequest() {
            var request = new TicketReportRequest(transactionId, List.of(new Customer(null, 10)));

            assertFalse(validator.isValid(request, context));
        }

        @Test
        public void emptyCustomerNameRequest() {
            var request = new TicketReportRequest(transactionId, List.of(new Customer("", 10)));

            assertFalse(validator.isValid(request, context));
        }

        @Test
        public void lessThanOneCustomerAgeRequest() {
            var request = new TicketReportRequest(transactionId, List.of(new Customer("test", -2)));

            assertFalse(validator.isValid(request, context));
        }

    }

}
