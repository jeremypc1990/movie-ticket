package com.code.movie.ticket.handler;

import com.code.movie.ticket.model.Customer;
import com.code.movie.ticket.model.TicketType;

import java.util.List;

public interface CustomerService {

    List<Customer> getGroupedCustomers(List<Customer> customers, TicketType type);

}
