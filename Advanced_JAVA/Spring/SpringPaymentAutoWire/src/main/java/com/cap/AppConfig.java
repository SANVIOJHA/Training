package com.cap;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
 * @Configuration marks this as configuration class.
 *
 * @ComponentScan tells Spring:
 * "Scan this package and create beans for all @Component classes"
 */
@Configuration
@ComponentScan("com.cap")
public class AppConfig {

}