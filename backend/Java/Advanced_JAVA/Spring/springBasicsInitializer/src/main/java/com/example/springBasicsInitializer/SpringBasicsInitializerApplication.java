package com.example.springBasicsInitializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@SpringBootApplication
//instead of this @SpringBootApplication we can use below three configuration also
// as all these three are stored in @SpringBootApplication
//
//@Component
//@EnableAutoConfiguration
//@Configuration
public class SpringBasicsInitializerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBasicsInitializerApplication.class, args);
	}

}
