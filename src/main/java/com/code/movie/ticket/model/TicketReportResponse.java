package com.code.movie.ticket.model;

import java.util.List;

public record TicketReportResponse(Long transactionId,
                                   List<Ticket> tickets,
                                   Double totalCost) {
}
