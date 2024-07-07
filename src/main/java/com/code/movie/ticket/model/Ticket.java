package com.code.movie.ticket.model;

public record Ticket(String ticketType,
                     Integer quantity,
                     Double totalCost) {
}
