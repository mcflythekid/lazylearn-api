package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.indto.*;
import com.mcflythekid.lazylearncore.outdto.BootstrapTableOutDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.service.AuthService;
import com.mcflythekid.lazylearncore.service.CardService;
import com.mcflythekid.lazylearncore.service.DeckService;
import com.mcflythekid.lazylearncore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
public class CardController extends BaseController{

    @Autowired
    private CardService cardService;

    @PostMapping("/deck/{deckId}/card")
    public JSON create(@PathVariable("deckId") String deckId, @RequestBody @Valid CreateCardInDto createCardInDto){
        Deck deck = authorizeDeck(deckId);

        createCardInDto.setUserId(deck.getUserId());
        createCardInDto.setDeckId(deckId);
        return cardService.create(createCardInDto);
    }

    @PutMapping("/card/{cardId}")
    public JSON update(@PathVariable("cardId") String cardId, @RequestBody @Valid UpdateCardInDto updateCardInDto){
        authorizeCard(cardId);

        updateCardInDto.setCardId(cardId);
        return cardService.update(updateCardInDto);
    }

    @DeleteMapping("/card/{cardId}")
    public JSON deleteCard(@PathVariable("cardId") String cardId){
        authorizeCard(cardId);

        return cardService.delete(cardId);
    }

    @GetMapping("/card/{cardId}")
    public Card get(@PathVariable("cardId") String cardId){
        Card card = authorizeCard(cardId);

        return card;
    }

    @GetMapping("/deck/{deckId}/card/by-search")
    public BootstrapTableOutDto searchDeck(@PathVariable("deckId") String deckId,
                                         @RequestParam(name = "search", defaultValue = "") String search,
                                         @RequestParam("sort") String sort, @RequestParam("order") String order,
                                         @RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset){
        authorizeDeck(deckId);

        SearchCardInDto searchCardInDto = new SearchCardInDto(order, sort, limit, offset);
        searchCardInDto.setSearch(search);
        searchCardInDto.setDeckId(deckId);
        return cardService.search(searchCardInDto);
    }
}
