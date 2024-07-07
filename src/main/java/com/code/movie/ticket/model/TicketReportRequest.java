package com.code.movie.ticket.model;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TicketReportRequest(@NotNull Long transactionId,
                                  @NotNull List<Customer> customers) {
}
