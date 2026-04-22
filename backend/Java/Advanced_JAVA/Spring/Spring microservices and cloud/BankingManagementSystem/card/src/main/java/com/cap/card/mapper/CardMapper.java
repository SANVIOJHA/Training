package com.cap.card.mapper;

import com.cap.card.dto.CardDto;
import com.cap.card.entity.Cards;

public class CardMapper {

    public static CardDto mapToCardsDto(Cards cards, CardDto dto){

        dto.setMobileNumber(cards.getMobileNumber());
        dto.setCardNumber(cards.getCardNumber());
        dto.setCardType(cards.getCardType());
        dto.setTotalLimit(cards.getTotalLimit());
        dto.setAmountUsed(cards.getAmountUsed());
        dto.setAvailableAmount(cards.getAvailableAmount());

        return dto;
    }

    public static Cards mapToCards(CardDto dto, Cards cards){

        cards.setMobileNumber(dto.getMobileNumber());
        cards.setCardNumber(dto.getCardNumber());
        cards.setCardType(dto.getCardType());
        cards.setTotalLimit(dto.getTotalLimit());
        cards.setAmountUsed(dto.getAmountUsed());
        cards.setAvailableAmount(dto.getAvailableAmount());

        return cards;
    }
}