package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.config.exception.AppException;
import com.mcflythekid.lazylearncore.repo.CardRepo;
import com.mcflythekid.lazylearncore.repo.DeckRepo;
import com.mcflythekid.lazylearncore.repo.UserRepo;
import com.mcflythekid.lazylearncore.service.AuthService;
import com.mcflythekid.lazylearncore.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

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
    private HttpServletRequest request;

    protected String getIpAddress(){
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null || ipAddress.isEmpty()){
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    protected User authorizeUser(String userId){
        User user = userRepo.findOne(userId);
        if (user == null || !user.getId().equals(SecurityUtils.getCurrentUserLogin().orElse(""))){
            throw new AppException("It's not you");
        }
        return user;
    }

    protected Deck authorizeDeck(String deckId){
        Deck deck = deckRepo.findOne(deckId);
        if (deck == null || !deck.getUserId().equals(SecurityUtils.getCurrentUserLogin().orElse(""))){
            throw new AppException("It's not you");
        }
        return deck;
    }

    protected Card authorizeCard(String cardId) {
        Card card = cardRepo.findOne(cardId);
        if (card == null || !card.getUserId().equals(SecurityUtils.getCurrentUserLogin().orElse(""))){
            throw new AppException("It's not you");
        }
        return card;
    }
}