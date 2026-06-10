package com.smartcourier.tracking.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private static final Logger log = LoggerFactory.getLogger(SmsService.class);

    @Value("${spring.twilio.account-sid:mock_sid}")
    private String accountSid;

    @Value("${spring.twilio.auth-token:mock_token}")
    private String authToken;

    @Value("${spring.twilio.phone-number:+1234567890}")
    private String fromPhoneNumber;

    private boolean isMockMode = false;

    @PostConstruct
    public void init() {
        if ("mock_sid".equals(accountSid) || "mock_token".equals(authToken)) {
            log.warn("Twilio credentials not fully configured. SMS Service will run in MOCK mode.");
            isMockMode = true;
        } else {
            try {
                Twilio.init(accountSid, authToken);
                log.info("Twilio initialized successfully.");
            } catch (Exception e) {
                log.error("Failed to initialize Twilio, falling back to MOCK mode.", e);
                isMockMode = true;
            }
        }
    }

    public void sendSms(String toPhoneNumber, String textMessage) {
        if (toPhoneNumber == null || toPhoneNumber.isBlank()) {
            log.warn("Cannot send SMS: target phone number is missing.");
            return;
        }

        if (isMockMode) {
            log.info("\n========== MOCK SMS DISPATCH ==========\nTO: {}\nFROM: {}\nMESSAGE: {}\n=======================================",
                    toPhoneNumber, fromPhoneNumber, textMessage);
            return;
        }

        try {
            Message message = Message.creator(
                    new PhoneNumber(toPhoneNumber),
                    new PhoneNumber(fromPhoneNumber),
                    textMessage
            ).create();

            log.info("SMS dispatched successfully. SID: {}", message.getSid());
        } catch (Exception e) {
            log.error("Failed to send Twilio SMS to {}. Error: {}", toPhoneNumber, e.getMessage());
            // System does not crash if SMS fails, just logged.
        }
    }
}
