package com.cap.cards.mapper;

import com.cap.cards.dto.CardDto;
import com.cap.cards.entity.Card;

public class CardMapper {

    public static CardDto mapToCardDto(Card card, CardDto dto) {

        dto.setMobileNumber(card.getMobileNumber());
        dto.setCardNumber(card.getCardNumber());
        dto.setCardType(card.getCardType());
        dto.setTotalLimit(card.getTotalLimit());
        dto.setAmountUsed(card.getAmountUsed());
        dto.setAvailableAmount(card.getAvailableAmount());

        return dto;
    }

    public static Card mapToCard(CardDto dto, Card card) {

        card.setMobileNumber(dto.getMobileNumber());
        card.setCardNumber(dto.getCardNumber());
        card.setCardType(dto.getCardType());
        card.setTotalLimit(dto.getTotalLimit());
        card.setAmountUsed(dto.getAmountUsed());
        card.setAvailableAmount(dto.getAvailableAmount());

        return card;
    }
}