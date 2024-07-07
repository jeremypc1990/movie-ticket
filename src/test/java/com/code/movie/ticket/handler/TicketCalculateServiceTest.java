package com.code.movie.ticket.handler;

import com.code.movie.ticket.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.code.movie.ticket.model.TicketType.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TicketCalculateServiceTest {

    @Autowired
    private TicketCalculateService service;

    @Test
    void shouldReturnRightTicketSummaryForSenior() {
        var seniors = List.of(new Customer("test", 68), new Customer("test", 70));

        var ticket = service.calculateSum(seniors, SENIOR);

        assertThat(ticket.quantity()).isEqualTo(2);
        assertThat(ticket.totalCost()).isEqualTo(SENIOR.getUnitPrice() * 2);
        assertThat(ticket.ticketType()).isEqualTo(SENIOR.getValue());
    }

    @Test
    void shouldReturnRightTicketSummaryForChildrenLessThanThree() {
        var children = List.of(new Customer("test", 5), new Customer("test", 8));

        var ticket = service.calculateSum(children, CHILDREN);

        assertThat(ticket.quantity()).isEqualTo(2);
        assertThat(ticket.totalCost()).isEqualTo(CHILDREN.getUnitPrice() * 2);
        assertThat(ticket.ticketType()).isEqualTo(CHILDREN.getValue());
    }

    @Test
    void shouldReturnRightTicketSummaryForChildrenWithDiscount() {
        var children = List.of(new Customer("test", 5), new Customer("test", 8), new Customer("test", 2));

        var ticket = service.calculateSum(children, CHILDREN);

        assertThat(ticket.quantity()).isEqualTo(3);
        assertThat(ticket.totalCost()).isEqualTo(CHILDREN.getUnitPrice() * 3 * 0.75);
        assertThat(ticket.ticketType()).isEqualTo(CHILDREN.getValue());
    }

    @Test
    void shouldReturnRightTicketSummaryForAdult() {
        var adults = List.of(new Customer("test", 40), new Customer("test", 30));

        var ticket = service.calculateSum(adults, ADULT);

        assertThat(ticket.quantity()).isEqualTo(2);
        assertThat(ticket.totalCost()).isEqualTo(ADULT.getUnitPrice() * 2);
        assertThat(ticket.ticketType()).isEqualTo(ADULT.getValue());
    }

    @Test
    void shouldReturnRightTicketSummaryForTeen() {
        var teens = List.of(new Customer("test", 15), new Customer("test", 14));

        var ticket = service.calculateSum(teens, TEEN);

        assertThat(ticket.quantity()).isEqualTo(2);
        assertThat(ticket.totalCost()).isEqualTo(TEEN.getUnitPrice() * 2);
        assertThat(ticket.ticketType()).isEqualTo(TEEN.getValue());
    }

}
