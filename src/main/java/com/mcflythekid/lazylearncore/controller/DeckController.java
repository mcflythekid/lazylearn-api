package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.indto.DeckCreateInDto;
import com.mcflythekid.lazylearncore.indto.DeckEditInDto;
import com.mcflythekid.lazylearncore.indto.DeckSearchInDto;
import com.mcflythekid.lazylearncore.outdto.BootstrapTableOutDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.service.AuthService;
import com.mcflythekid.lazylearncore.service.DeckService;
import com.mcflythekid.lazylearncore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
public class DeckController {

    @PostMapping("/user/{userId}/deck")
    public JSON createDeck(@Valid @RequestBody DeckCreateInDto deckCreateInDto,
                           @PathVariable("userId") String userId){
        User user = userService.findOne(userId);
        authService.checkOwner(user);
        return deckService.create(deckCreateInDto, user);
    }

    @GetMapping("/user/{userId}/deck/by-search")
    public BootstrapTableOutDto listDeck(@PathVariable("userId") String userId, @RequestParam(name = "search", defaultValue = "") String search,
                                         @RequestParam("sort") String sort, @RequestParam("order") String order,
                                         @RequestParam("limit") Integer limit,  @RequestParam("offset") Integer offset){
        User user = userService.findOne(userId);
        authService.checkOwner(user);
        DeckSearchInDto deckSearchInDto = new DeckSearchInDto(order, sort, limit, offset);
        deckSearchInDto.setSearch(search);
        return deckService.listByUserAndSearch(user, deckSearchInDto);
    }

    @PutMapping("/user/{userId}/deck/{deckId}")
    public JSON editDeck(@PathVariable("userId") String userId, @PathVariable("deckId") String deckId,
                                         @RequestBody DeckEditInDto deckEditInDto){
        User user = userService.findOne(userId);
        Deck deck = deckService.findOne(deckId);
        authService.checkOwner(user);
        authService.checkOwner(deck);
        return deckService.editDeck(deck, deckEditInDto);
    }

    @DeleteMapping("/user/{userId}/deck/{deckId}")
    public JSON deleteDeck(@PathVariable("userId") String userId, @PathVariable("deckId") String deckId){
        User user = userService.findOne(userId);
        Deck deck = deckService.findOne(deckId);
        authService.checkOwner(user);
        authService.checkOwner(deck);
        return deckService.deleteDeck(deck);
    }

    @GetMapping("/user/{userId}/deck/{deckId}")
    public Deck getDeck(@PathVariable("userId") String userId, @PathVariable("deckId") String deckId){
        User user = userService.findOne(userId);
        Deck deck = deckService.findOne(deckId);
        authService.checkOwner(user);
        authService.checkOwner(deck);
        return deck;
    }

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private DeckService deckService;
}
