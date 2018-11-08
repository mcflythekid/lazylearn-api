package com.lazylearn.api.controller;

import com.lazylearn.api.entity.Card;
import com.lazylearn.api.indto.card.CardCreateIn;
import com.lazylearn.api.indto.card.CardEditIn;
import com.lazylearn.api.indto.card.CardSearchIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.service.CardService;
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
        protectGeneratedDeck(authorizeDeck(in.getDeckId()));
        return cardService.create(in, getUserId());
    }

    @PostMapping("/delete/{cardId}")
    public JSON deleteCard(@PathVariable("cardId") String cardId) throws Exception {
        protectGeneratedCard(authorizeCard(cardId));
        cardService.delete(cardId);
        return JSON.ok("Delete success");
    }

    @PostMapping("/edit")
    public Card edit(@Valid @RequestBody CardEditIn in) throws Exception {
        protectGeneratedCard(authorizeCard(in.getCardId()));
        return cardService.edit(in);
    }

    @GetMapping("/get/{cardId}")
    public Card get(@PathVariable("cardId") String cardId) throws Exception {
        return authorizeCard(cardId);
    }
}