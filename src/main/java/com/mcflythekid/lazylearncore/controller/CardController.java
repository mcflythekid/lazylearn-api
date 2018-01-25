package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author McFly the Kid
 */
@RestController
@RequestMapping("/card")
public class CardController {

    @PostMapping
    public Card create(@RequestBody Card card){
        return cardService.createCard(card);
    }

    @Autowired
    private CardService cardService;
}
