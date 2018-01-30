package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.indto.CardCreateInDto;
import com.mcflythekid.lazylearncore.indto.CardSearchInDto;
import com.mcflythekid.lazylearncore.indto.DeckSearchInDto;
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
public class CardController {

    @PostMapping("/user/{userId}/deck/{deckId}/card")
    public JSON createCard(@PathVariable("userId") String userId, @PathVariable("deckId") String deckId,
                           @RequestBody @Valid CardCreateInDto cardCreateInDto){
        User user = userService.findOne(userId);
        Deck deck = deckService.findOne(deckId);
        authService.checkOwner(user);
        authService.checkOwner(deck);

        return cardService.createCard(cardCreateInDto, deck);
    }

    @GetMapping("/user/{userId}/deck/{deckId}/card")
    public BootstrapTableOutDto listDeck(@PathVariable("userId") String userId, @PathVariable("deckId") String deckId,
                                         @RequestParam(name = "search", defaultValue = "") String search,
                                         @RequestParam("sort") String sort, @RequestParam("order") String order,
                                         @RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset){
        User user = userService.findOne(userId);
        Deck deck = deckService.findOne(deckId);
        authService.checkOwner(user);
        authService.checkOwner(deck);

        CardSearchInDto cardSearchInDto = new CardSearchInDto(order, sort, limit, offset);
        cardSearchInDto.setSearch(search);
        return cardService.listByDeckAndSearch(deck, cardSearchInDto);
    }

    @Autowired
    private CardService cardService;

    @Autowired
    private UserService userService;

    @Autowired
    private DeckService deckService;

    @Autowired
    private AuthService authService;
}
