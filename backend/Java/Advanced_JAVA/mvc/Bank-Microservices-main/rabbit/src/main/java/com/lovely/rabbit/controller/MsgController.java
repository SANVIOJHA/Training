package com.lovely.rabbit.controller;

import com.lovely.rabbit.msg.producer.RabbitMqProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/msg")
@RequiredArgsConstructor
public class MsgController {

    private final RabbitMqProducer producer;

    @PostMapping
    public String sendMsg(@RequestBody String msg) {

        log.info("Message received from API: {}", msg);

        producer.sendMsg(msg);

        return "Message sent to RabbitMQ: " + msg;
    }
}

