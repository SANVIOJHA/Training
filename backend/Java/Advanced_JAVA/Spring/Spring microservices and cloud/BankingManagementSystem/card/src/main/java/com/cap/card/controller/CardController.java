package com.cap.card.controller;

import com.cap.card.dto.CardDto;
import com.cap.card.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private ICardService cardsService;

    @PostMapping("/create")
    public String createCard(@RequestParam String mobileNumber){

        cardsService.createCard(mobileNumber);

        return "Card Created Successfully";
    }

    @GetMapping("/fetch")
    public CardDto fetchCard(@RequestParam String mobileNumber){

        return cardsService.fetchCard(mobileNumber);
    }
}