package com.code.movie.ticket.handler;

import com.code.movie.ticket.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.code.movie.ticket.model.TicketType.*;

@Service
public class TicketRequestService implements RequestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketRequestService.class);

    private final CalculateService calculateService;

    private final CustomerService customerService;

    public TicketRequestService(CalculateService calculateService, CustomerService customerService) {
        this.calculateService = calculateService;
        this.customerService = customerService;
    }


    @Override
    public TicketReportResponse getTicketReport(TicketReportRequest request) {
        var customers = request.customers();

        // Calculate ticket summary based on each ticket type
        var childrenTickets = getGroupedTicketSum(customers, CHILDREN);
        var adultTickets = getGroupedTicketSum(customers, ADULT);
        var teenTickets = getGroupedTicketSum(customers, TEEN);
        var seniorTickets = getGroupedTicketSum(customers, SENIOR);

        // Create a non-null ticket list
        var tickets = new ArrayList<Ticket>();
        Optional.ofNullable(childrenTickets).ifPresent(tickets::add);
        Optional.ofNullable(adultTickets).ifPresent(tickets::add);
        Optional.ofNullable(teenTickets).ifPresent(tickets::add);
        Optional.ofNullable(seniorTickets).ifPresent(tickets::add);

        // Calculate the transaction total value and sort the ticket list based on the type name
        var totalSum = (Double) tickets.stream().mapToDouble(Ticket::totalCost).sum();
        tickets.sort(Comparator.comparing(Ticket::ticketType));

        LOGGER.info("This transaction contains {}", tickets);

        return new TicketReportResponse(request.transactionId(),
                tickets,
                totalSum);

    }

    protected Ticket getGroupedTicketSum(List<Customer> customers, TicketType ticketType) {
        var groupedCustomers = customerService.getGroupedCustomers(customers, ticketType);
        return groupedCustomers.isEmpty() ? null : calculateService.calculateSum(groupedCustomers, ticketType);
    }

}
