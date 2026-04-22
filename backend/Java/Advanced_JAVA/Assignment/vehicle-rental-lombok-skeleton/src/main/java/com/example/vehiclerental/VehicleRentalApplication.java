package com.example.vehiclerental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VehicleRentalApplication {
    public static void main(String[] args) {
        SpringApplication.run(VehicleRentalApplication.class, args);
    }
}
//My project is a Spring Boot based REST API that manages vehicle rental plans.
// It allows users to add rental plans, retrieve them by vehicle type, filter them by mileage and duration,
// and also perform analytics such as counting plans by rental category.

//In my application the request follows a layered architecture.
// The client sends a request to the controller, which acts as the entry point for REST APIs.
// The controller forwards the request to the service layer where business logic is implemented.
// The service then calls the repository layer, which interacts with the database using Spring Data JPA.
// After the database operation is completed, the response flows back through the repository, service,
// and controller,and is finally returned to the client.


