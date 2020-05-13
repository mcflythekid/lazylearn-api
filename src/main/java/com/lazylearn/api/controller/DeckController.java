package com.lazylearn.api.controller;

import com.lazylearn.api.config.Consts;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.indto.SearchIn;
import com.lazylearn.api.indto.deck.DeckCreateIn;
import com.lazylearn.api.indto.deck.DeckRenameIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author McFly the Kid
 */
@RestController
@RequestMapping("/deck")
public class DeckController extends BaseController{

    @Autowired
    private DeckService deckService;

    @PostMapping("/edit")
    public JSON rename(@Valid @RequestBody DeckRenameIn in) throws Exception {
        protectGeneratedDeck(authorizeDeck(in.getDeckId()));
        deckService.rename(in);
        return JSON.ok("Rename success");
    }

    @PostMapping("/archive/{deckId}")
    public JSON archive(@PathVariable("deckId") String deckId) throws Exception {
        protectGeneratedDeck(authorizeDeck(deckId));
        deckService.archive(deckId);
        return JSON.ok("Archived");
    }

    @PostMapping("/unarchive/{deckId}")
    public JSON unarchive(@PathVariable("deckId") String deckId) throws Exception {
        protectGeneratedDeck(authorizeDeck(deckId));
        deckService.unarchive(deckId);
        return JSON.ok("Unarchived");
    }

    @PostMapping("/delete/{deckId}")
    public JSON delete(@PathVariable("deckId") String deckId) throws Exception {
        protectGeneratedDeck(authorizeDeck(deckId));
        deckService.delete(deckId);
        return JSON.ok("Delete success");
    }

    @PostMapping("/create")
    public Deck create(@Valid @RequestBody DeckCreateIn in) throws Exception {
        return deckService.create(in, getUserId());
    }

    @GetMapping("/get/{deckId}")
    public Deck get(@PathVariable("deckId") String deckId) throws Exception {
        if (deckId.startsWith(Consts.Deck.LEARN_ALL_DECK_ID_PREFIX)){
            return deckService.createOneForAllDeck();
        }

        return authorizeDeck(deckId);
    }

    @PostMapping("/search")
    public BootstraptableOut search(@RequestBody SearchIn in) throws Exception {
        return deckService.search(in, getUserId());
    }
}