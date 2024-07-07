package com.code.movie.ticket.model;

import java.time.Instant;

public record ErrorResponse(Instant timestamp, String message) {

    public static ErrorResponse create(String message) {
        return new ErrorResponse(Instant.now(), message);
    }

}
