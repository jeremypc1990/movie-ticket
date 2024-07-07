package com.code.movie.ticket.handler;

import com.code.movie.ticket.model.TicketReportRequest;
import com.code.movie.ticket.model.TicketReportResponse;

public interface RequestService {

    TicketReportResponse getTicketReport(TicketReportRequest request);

}
