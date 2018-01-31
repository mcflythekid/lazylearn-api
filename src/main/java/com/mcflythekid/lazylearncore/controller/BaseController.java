package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.exception.AppForbiddenException;
import com.mcflythekid.lazylearncore.repo.CardRepo;
import com.mcflythekid.lazylearncore.repo.DeckRepo;
import com.mcflythekid.lazylearncore.repo.UserRepo;
import com.mcflythekid.lazylearncore.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author McFly the Kid
 */
public abstract class BaseController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DeckRepo deckRepo;

    @Autowired
    private CardRepo cardRepo;

    @Autowired
    private AuthService authService;

    public User authorizeUser(String userId){
        User user = userRepo.findOne(userId);
        if (user == null || !user.getId().equals(authService.getCurrentUserId())){
            throw new AppForbiddenException("It's not you");
        }
        return user;
    }

    public Deck authorizeDeck(String deckId){
        Deck deck = deckRepo.findOne(deckId);
        if (deck == null || !deck.getUserId().equals(authService.getCurrentUserId())){
            throw new AppForbiddenException("It's not you");
        }
        return deck;
    }

    public Card authorizeCard(String cardId) {
        Card card = cardRepo.findOne(cardId);
        if (card == null || !card.getUserId().equals(authService.getCurrentUserId())){
            throw new AppForbiddenException("It's not you");
        }
        return card;
    }
}
