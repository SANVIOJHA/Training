package com.cap.cards.controller;

import org.springframework.web.bind.annotation.*;

import com.cap.cards.dto.CardDto;
import com.cap.cards.service.ICardService;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final ICardService cardService;

    public CardController(ICardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/create")
    public String createCard(@RequestParam String mobileNumber) {

        cardService.createCard(mobileNumber);
        return "Card created successfully";
    }

    @GetMapping("/fetch")
    public CardDto fetchCard(@RequestParam String mobileNumber) {

        return cardService.fetchCard(mobileNumber);
    }

}