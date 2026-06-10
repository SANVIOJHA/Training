package com.smartcourier.tracking.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String TRACKING_QUEUE = "tracking.delivery.status.queue";

    @Bean
    public Queue trackingQueue() {
        return new Queue(TRACKING_QUEUE, true);
    }

    @Bean
    public Queue userRegisteredQueue() {
        return new Queue("user_registered_queue", true);
    }

    @Bean
    public org.springframework.amqp.core.DirectExchange authExchange() {
        return new org.springframework.amqp.core.DirectExchange("auth_exchange");
    }

    @Bean
    public org.springframework.amqp.core.Binding userRegisteredBinding(Queue userRegisteredQueue, org.springframework.amqp.core.DirectExchange authExchange) {
        return org.springframework.amqp.core.BindingBuilder.bind(userRegisteredQueue).to(authExchange).with("user.registered");
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
