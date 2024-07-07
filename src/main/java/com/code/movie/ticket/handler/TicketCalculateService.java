package com.code.movie.ticket.handler;

import com.code.movie.ticket.model.Customer;
import com.code.movie.ticket.model.Ticket;
import com.code.movie.ticket.model.TicketType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketCalculateService implements CalculateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketCalculateService.class);
    private static final int MIN_CHILDREN_FOR_DISCOUNT = 3;

    @Value("${discount.rate.children:0.25}")
    private Double discountRate;

    @Override
    public Ticket calculateSum(List<Customer> customers, TicketType ticketType) {
        var sum = customers.size() * ticketType.getUnitPrice();
        var quantity = customers.size();

        if (ticketType == TicketType.CHILDREN && quantity >= MIN_CHILDREN_FOR_DISCOUNT) {
            sum = sum * (1 - discountRate);
            LOGGER.info("Transaction has {} children in total that is eligible for discount", quantity);
        }

        return new Ticket(ticketType.getValue(), quantity, sum);
    }

}
