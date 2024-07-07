package com.code.movie.ticket.utils;

import com.code.movie.ticket.model.Customer;

import java.util.List;

public class TestDataUtil {

    public static final List<Customer> TEST_CUSTOMERS = List.of(new Customer("test", 5),
            new Customer("test", 10),
            new Customer("test", 15),
            new Customer("test", 30),
            new Customer("test", 40),
            new Customer("test", 50),
            new Customer("test", 60),
            new Customer("test", 70));

}
