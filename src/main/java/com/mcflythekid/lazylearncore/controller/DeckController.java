package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.indto.CreateDeckInDto;
import com.mcflythekid.lazylearncore.indto.UpdateDeckInDto;
import com.mcflythekid.lazylearncore.indto.SearchDeckInDto;
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
public class DeckController extends BaseController{

    @Autowired
    private DeckService deckService;

    @PostMapping("/user/{userId}/deck")
    public JSON create(@PathVariable("userId") String userId, @Valid @RequestBody CreateDeckInDto createDeckInDto){
        authorizeUser(userId);

        createDeckInDto.setUserId(userId);
        return deckService.create(createDeckInDto);
    }

    @PutMapping("/deck/{deckId}")
    public JSON update(@PathVariable("deckId") String deckId, @RequestBody UpdateDeckInDto updateDeckInDto){
        authorizeDeck(deckId);

        updateDeckInDto.setDeckId(deckId);
        return deckService.update(updateDeckInDto);
    }

    @DeleteMapping("/deck/{deckId}")
    public JSON delete(@PathVariable("deckId") String deckId){
        authorizeDeck(deckId);

        return deckService.delete(deckId);
    }

    @GetMapping("/deck/{deckId}")
    public Deck get(@PathVariable("deckId") String deckId){
        Deck deck = authorizeDeck(deckId);

        return deck;
    }

    @GetMapping("/user/{userId}/deck/by-search")
    public BootstrapTableOutDto search(@PathVariable("userId") String userId,
                                       @RequestParam(name = "search", defaultValue = "") String search,
                                       @RequestParam("sort") String sort, @RequestParam("order") String order,
                                       @RequestParam("limit") Integer limit,  @RequestParam("offset") Integer offset){
        authorizeUser(userId);
        
        SearchDeckInDto searchDeckInDto = new SearchDeckInDto(order, sort, limit, offset);
        searchDeckInDto.setSearch(search);
        searchDeckInDto.setUserId(userId);
        return deckService.search(searchDeckInDto);
    }
}
