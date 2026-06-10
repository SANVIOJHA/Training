package com.smartcourier.auth.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "auth_exchange";
    public static final String USER_REGISTERED_QUEUE = "user_registered_queue";
    public static final String USER_REGISTERED_ROUTING_KEY = "user.registered";

    @Bean
    public DirectExchange authExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue userRegisteredQueue() {
        return new Queue(USER_REGISTERED_QUEUE, true);
    }

    @Bean
    public Binding userRegisteredBinding(Queue userRegisteredQueue, DirectExchange authExchange) {
        return BindingBuilder.bind(userRegisteredQueue).to(authExchange).with(USER_REGISTERED_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
