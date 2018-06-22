package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.indto.SearchIn;
import com.mcflythekid.lazylearncore.indto.deck.DeckCreateIn;
import com.mcflythekid.lazylearncore.indto.deck.DeckRenameIn;
import com.mcflythekid.lazylearncore.outdto.BootstraptableOut;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.service.DeckService;
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
        return authorizeDeck(deckId);
    }

    @PostMapping("/search")
    public BootstraptableOut search(@RequestBody SearchIn in) throws Exception {
        return deckService.search(in, getUserId());
    }
}