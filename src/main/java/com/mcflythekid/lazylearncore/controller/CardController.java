package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.indto.*;
import com.mcflythekid.lazylearncore.indto.card.CardCreateIn;
import com.mcflythekid.lazylearncore.indto.card.CardEditIn;
import com.mcflythekid.lazylearncore.indto.card.CardSearchIn;
import com.mcflythekid.lazylearncore.outdto.BootstraptableOut;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
@RequestMapping("/card")
public class CardController extends BaseController{

    @Autowired
    private CardService cardService;

    @PostMapping("/search")
    public BootstraptableOut searchDeck(@Valid @RequestBody CardSearchIn in) throws Exception {
        authorizeDeck(in.getDeckId());
        return cardService.search(in);
    }

    @PostMapping("/create")
    public Card create(@Valid @RequestBody CardCreateIn in) throws Exception {
        authorizeDeck(in.getDeckId());
        return cardService.create(in, getUserId());
    }

    @PostMapping("/delete/{cardId}")
    public JSON deleteCard(@PathVariable("cardId") String cardId) throws Exception {
        authorizeCard(cardId);
        cardService.delete(cardId);
        return JSON.ok("Delete success");
    }

    @PostMapping("/edit")
    public JSON edit(@Valid @RequestBody CardEditIn in) throws Exception {
        authorizeCard(in.getCardId());
        cardService.edit(in);
        return JSON.ok("Edit success");
    }

    @GetMapping("/get/{cardId}")
    public Card get(@PathVariable("cardId") String cardId) throws Exception {
        return authorizeCard(cardId);
    }
}