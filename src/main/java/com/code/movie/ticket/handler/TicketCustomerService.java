package com.code.movie.ticket.handler;

import com.code.movie.ticket.model.Customer;
import com.code.movie.ticket.model.TicketType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketCustomerService implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketCustomerService.class);

    @Override
    public List<Customer> getGroupedCustomers(List<Customer> customers, TicketType type) {

        var groupedCustomers = switch (type) {
            case CHILDREN -> customers.stream().filter(customer -> customer.age() < 11).toList();
            case TEEN -> customers.stream().filter(customer -> customer.age() >= 11 && customer.age() < 18).toList();
            case ADULT -> customers.stream().filter(customer -> customer.age() >= 18 && customer.age() < 65).toList();
            case SENIOR -> customers.stream().filter(customer -> customer.age() >= 65).toList();
        };

        LOGGER.info("Filter all customers and found {} {}", groupedCustomers.size(), type.getValue());

        return groupedCustomers;
    }

}
