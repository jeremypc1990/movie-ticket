package com.code.movie.ticket.api;


import com.code.movie.ticket.handler.RequestService;
import com.code.movie.ticket.model.TicketReportRequest;
import com.code.movie.ticket.model.TicketReportResponse;
import com.code.movie.ticket.validation.ValidTicketRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
public class TicketReportApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketReportApi.class);

    private final RequestService requestHandler;

    public TicketReportApi(RequestService requestHandler) {
        this.requestHandler = requestHandler;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/ticket-report", produces = APPLICATION_JSON_VALUE)
    public TicketReportResponse getTicketReport(@ValidTicketRequest @RequestBody TicketReportRequest request) {
        LOGGER.info("Received ticket report request with params: {}", request);
        return requestHandler.getTicketReport(request);
    }

}
