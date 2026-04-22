package com.cap.cards.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cap.cards.dto.CardDto;
import com.cap.cards.entity.Card;
import com.cap.cards.mapper.CardMapper;
import com.cap.cards.repository.CardRepository;

@Service
public class CardServiceImpl implements ICardService {

    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void createCard(String mobileNumber) {

        Card card = new Card();

        card.setMobileNumber(mobileNumber);
        card.setCardNumber("123456789012");
        card.setCardType("CREDIT");
        card.setTotalLimit(100000);
        card.setAmountUsed(0);
        card.setAvailableAmount(100000);

        cardRepository.save(card);
    }

    @Override
    public CardDto fetchCard(String mobileNumber) {

        Optional<Card> card = cardRepository.findByMobileNumber(mobileNumber);

        return CardMapper.mapToCardDto(card.get(), new CardDto());
    }

}