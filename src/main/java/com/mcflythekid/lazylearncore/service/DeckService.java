package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.indto.DeckCreateInDto;
import com.mcflythekid.lazylearncore.indto.DeckEditInDto;
import com.mcflythekid.lazylearncore.indto.DeckSearchInDto;
import com.mcflythekid.lazylearncore.outdto.BootstrapTableOutDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.repo.DeckRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author McFly the Kid
 */
@Service
public class DeckService {

    public Deck findOne(String deckId) {
        return deckRepo.findOne(deckId);
    }

    public JSON editDeck(Deck deck, DeckEditInDto deckEditInDto) {
        deck.setUpdatedOn(new Date());
        deck.setName(deckEditInDto.getName());
        deckRepo.save(deck);
        return JSON.ok();
    }

    public JSON deleteDeck(Deck deck) {
        deckRepo.delete(deck);
        return JSON.ok();
    }

    public BootstrapTableOutDto listByUserAndSearch(User user, DeckSearchInDto deckSearchInDto){
        List<Deck> rows = deckRepo.findAllByUserIdAndNameContaining(user.getId(), deckSearchInDto.getSearch(), deckSearchInDto.getPageable());
        Long total = deckRepo.countByUserIdAndNameContaining(user.getId(), deckSearchInDto.getSearch());
        return new BootstrapTableOutDto(rows, total);
    }

    public JSON create(DeckCreateInDto deckCreateInDto, User user){
        Deck deck = new Deck();
        deck.setCreatedOn(new Date());
        deck.setId(authService.getRamdomId());
        deck.setName(deckCreateInDto.getName());
        deck.setUserId(user.getId());
        deckRepo.save(deck);
        return JSON.ok();
    }

    @Autowired
    private DeckRepo deckRepo;

    @Autowired
    private AuthService authService;

    @Autowired
    private CardService cardService;
}
