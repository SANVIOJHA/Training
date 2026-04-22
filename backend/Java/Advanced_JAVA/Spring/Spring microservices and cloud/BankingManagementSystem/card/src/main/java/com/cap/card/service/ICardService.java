package com.cap.card.service;

import com.cap.card.dto.CardDto;

public interface ICardService {

    void createCard(String mobileNumber);

    CardDto fetchCard(String mobileNumber);
}