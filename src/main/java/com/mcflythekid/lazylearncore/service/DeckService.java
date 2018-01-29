package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.exception.AppNotFoundException;
import com.mcflythekid.lazylearncore.indto.DeckCreateInDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.repo.DeckRepo;
import com.mcflythekid.lazylearncore.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author McFly the Kid
 */
@Service
public class DeckService {

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
}
