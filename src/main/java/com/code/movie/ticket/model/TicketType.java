package com.code.movie.ticket.model;

public enum TicketType {

    CHILDREN("Children", 5.0, "For customers less than 11 years of age"),
    ADULT("Adult", 25.0, "For customers 18 years and older but less than 65 years old"),
    SENIOR("Senior", ADULT.unitPrice * 0.7, "For customers 65 years and older"),
    TEEN("Teen", 12.0, "For customers 11 years and older but less than 18 years old");

    private final String value;
    private final Double unitPrice;
    private final String description;

    TicketType(String value, Double unitPrice, String description) {
        this.value = value;
        this.unitPrice = unitPrice;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return value;
    }

}
