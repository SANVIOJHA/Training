package com.sprintsmartcourier.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class
DeliveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryServiceApplication.class, args);
	}

}


/**
 Client (Postman) ->Controller -> Service ->Mapper ->Repository -> Database-> Repository ->Mapper (Entity ->DTO)
 ->Controller -> client (response)

 */