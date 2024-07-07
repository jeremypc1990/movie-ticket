package com.code.movie.ticket.handler;

import com.code.movie.ticket.model.Ticket;
import com.code.movie.ticket.model.TicketReportRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.code.movie.ticket.model.TicketType.*;
import static com.code.movie.ticket.utils.TestDataUtil.TEST_CUSTOMERS;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TicketRequestServiceTest {

    @Autowired
    private TicketRequestService service;

    private final Long transactionId = 123L;

    @Test
    void shouldReturnCorrectResponse() {
        var request = new TicketReportRequest(transactionId, TEST_CUSTOMERS);

        var response = service.getTicketReport(request);

        assertThat(response.transactionId()).isEqualTo(123L);
        assertThat(response.tickets().size()).isEqualTo(4);
        assertThat(response.tickets().get(0).ticketType()).isEqualTo(ADULT.getValue());
        assertThat(response.tickets().get(1).ticketType()).isEqualTo(CHILDREN.getValue());
        assertThat(response.tickets().get(2).ticketType()).isEqualTo(SENIOR.getValue());
        assertThat(response.tickets().get(3).ticketType()).isEqualTo(TEEN.getValue());
        assertThat(response.totalCost()).isEqualTo(response.tickets().stream().mapToDouble(Ticket::totalCost).sum());

    }

}
