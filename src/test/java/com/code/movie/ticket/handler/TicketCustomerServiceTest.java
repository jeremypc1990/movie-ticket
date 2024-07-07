package com.code.movie.ticket.handler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.code.movie.ticket.model.TicketType.*;
import static com.code.movie.ticket.utils.TestDataUtil.TEST_CUSTOMERS;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TicketCustomerServiceTest {

    @Autowired
    private TicketCustomerService service;

    @Test
    void shouldFilterCustomersSuccessfully() {
        var teens = service.getGroupedCustomers(TEST_CUSTOMERS, TEEN);
        var adults = service.getGroupedCustomers(TEST_CUSTOMERS, ADULT);
        var children = service.getGroupedCustomers(TEST_CUSTOMERS, CHILDREN);
        var seniors = service.getGroupedCustomers(TEST_CUSTOMERS, SENIOR);

        assertThat(teens.size()).isEqualTo(1);
        assertThat(adults.size()).isEqualTo(4);
        assertThat(children.size()).isEqualTo(2);
        assertThat(seniors.size()).isEqualTo(1);

    }
    
}
