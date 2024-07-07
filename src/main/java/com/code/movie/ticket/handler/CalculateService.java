package com.code.movie.ticket.handler;

import com.code.movie.ticket.model.Customer;
import com.code.movie.ticket.model.Ticket;
import com.code.movie.ticket.model.TicketType;

import java.util.List;

public interface CalculateService {

    Ticket calculateSum(List<Customer> customers, TicketType ticketType);

}
