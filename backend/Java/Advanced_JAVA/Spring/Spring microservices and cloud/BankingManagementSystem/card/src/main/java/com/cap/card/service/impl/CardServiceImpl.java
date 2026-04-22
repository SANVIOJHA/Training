package com.cap.card.service.impl;

import com.cap.card.dto.CardDto;
import com.cap.card.entity.Cards;
import com.cap.card.exception.ResourceNotFoundException;
import com.cap.card.mapper.CardMapper;
import com.cap.card.repository.CardRepository;
import com.cap.card.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements ICardService {

    @Autowired
    private CardRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {

        Cards card = new Cards();

        card.setMobileNumber(mobileNumber);
        card.setCardNumber("123456789012");
        card.setCardType("CREDIT");
        card.setTotalLimit(100000);
        card.setAmountUsed(0);
        card.setAvailableAmount(100000);

        cardsRepository.save(card);
    }

    @Override
    public CardDto fetchCard(String mobileNumber) {

        Cards cards = cardsRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Card not found"));

        return CardMapper.mapToCardsDto(cards,new CardDto());
    }
}