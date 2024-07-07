package com.code.movie.ticket.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class TicketTypeTest {

    @Test
    void shouldBeFourTicketTypes() {
        assertThat(TicketType.values().length).isEqualTo(4);
    }

    @Test
    void shouldHaveCorrectValues() {
        Arrays.stream(TicketType.values())
                .forEach(ticketType -> assertThat(ticketType.getValue()).isEqualTo(ticketType.toString()));
    }

}
