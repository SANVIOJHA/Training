package com.cap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
 * @Configuration → Marks this class as Spring configuration class.
 *
 * @ComponentScan → Tells Spring to scan the specified package
 * and automatically detect classes annotated with:
 *     @Component
 *     @Service
 *     @Repository
 *     @Controller
 *
 * All detected classes will be registered as beans.
 */

@Configuration
@ComponentScan(basePackages = "com.cap")

public class DemoConfig {


}