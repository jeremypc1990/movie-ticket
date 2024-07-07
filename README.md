# Movie Ticket Report API

Provides REST API to report the projected cost of movie tickets from historical customer transactions to inform
decision-making around ticket pricing

### Tech Stack
* Java 21
* Spring boot 3.3.1
* Junit & Mockito
* Maven

### Build & Deploy
#### Build and Test
```shell
mvn clean verify
```

#### Run application locally
```shell
mvn spring-boot:run
```

### Test Coverage
* Unit tests to cover all service layer classes, major models and request validator
* Integration tests to cover api functionality tests including some failure cases

### Example of use
#### POST API Endpoint
```text
http://localhost:8080/ticket-report
```
#### Sample Request Payload
```json
{
  "transactionId": 123,
  "customers": [
    {
      "name": "Michael Jordan",
      "age": 5
    },
    {
      "name": "Michael Test",
      "age": 7
    },
    {
      "name": "Allen Jordan",
      "age": 8
    },
    {
      "name": "Alan Mike",
      "age": 12
    },
    {
      "name": "Mike Bryant",
      "age": 20
    },
    {
      "name": "Chris Hehe",
      "age": 40
    },
    {
      "name": "Alia Jordan",
      "age": 65
    },
    {
      "name": "Michael Jean",
      "age": 45
    }
  ]
}
```

#### Sample Response Payload
```json
{
  "transactionId": 123,
  "tickets": [
    {
      "ticketType": "Adult",
      "quantity": 3,
      "totalCost": 75.0
    },
    {
      "ticketType": "Children",
      "quantity": 3,
      "totalCost": 11.25
    },
    {
      "ticketType": "Senior",
      "quantity": 1,
      "totalCost": 17.5
    },
    {
      "ticketType": "Teen",
      "quantity": 1,
      "totalCost": 12.0
    }
  ],
  "totalCost": 115.75
}
```

### To be improved
* Make unit price of each customer type configurable through separate endpoints by admin user
* Make discount rates and rules configurable through separate endpoints by admin user
* More exception handling with more meaningful error messages