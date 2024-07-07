package com.code.movie.ticket.api;

import com.code.movie.ticket.handler.TicketRequestService;
import com.code.movie.ticket.model.TicketReportRequest;
import com.code.movie.ticket.model.TicketReportResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;

import static com.code.movie.ticket.utils.TestDataUtil.TEST_CUSTOMERS;
import static java.lang.String.format;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TicketReportApi.class)
public class TicketReportApiTest {

    private static final String PATH = "/ticket-report";

    private static final String EXPECTED_RESPONSE_FILENAME = "src/test/resources/expected-response.json";

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketRequestService service;

    @Test
    public void testTicketReportApi() throws Exception {
        var request = new TicketReportRequest(123L, TEST_CUSTOMERS);
        var response = createTicketReportResponse();
        when(service.getTicketReport(any())).thenReturn(response);

        mockMvc.perform(post(PATH)
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", is(123)))
                .andExpect(jsonPath("$.tickets[0].ticketType", is("Adult")))
                .andExpect(jsonPath("$.tickets[0].quantity", is(4)))
                .andExpect(jsonPath("$.tickets[0].totalCost", is(100.0)))
                .andExpect(jsonPath("$.totalCost", is(139.5)))
                .andExpect(content().contentType(APPLICATION_JSON_VALUE));

        var captor = ArgumentCaptor.forClass(TicketReportRequest.class);
        verify(service).getTicketReport(captor.capture());

    }

    @Test
    public void shouldReturnBadRequestWhenNullTransactionId() throws Exception {
        var request = new TicketReportRequest(null, TEST_CUSTOMERS);
        var response = createTicketReportResponse();
        when(service.getTicketReport(any())).thenReturn(response);

        mockMvc.perform(post(PATH)
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        var captor = ArgumentCaptor.forClass(TicketReportRequest.class);
        verify(service, never()).getTicketReport(captor.capture());
    }

    @Test
    public void shouldReturnBadRequestWhenNullCustomers() throws Exception {
        var request = new TicketReportRequest(123L, null);
        var response = createTicketReportResponse();
        when(service.getTicketReport(any())).thenReturn(response);

        mockMvc.perform(post(PATH)
                        .content(mapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        var captor = ArgumentCaptor.forClass(TicketReportRequest.class);
        verify(service, never()).getTicketReport(captor.capture());
    }

    private TicketReportResponse createTicketReportResponse() {
        try {
            return mapper.readValue(new File(EXPECTED_RESPONSE_FILENAME), TicketReportResponse.class);
        } catch (Exception e) {
            throw new IllegalStateException(
                    format("Could not load response from json file: %s", EXPECTED_RESPONSE_FILENAME), e);
        }
    }

}
