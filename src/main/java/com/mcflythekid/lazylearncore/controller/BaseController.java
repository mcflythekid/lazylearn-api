package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.entity.Minpair;
import com.mcflythekid.lazylearncore.entity.User;
import com.mcflythekid.lazylearncore.config.exception.AppException;
import com.mcflythekid.lazylearncore.indto.ClientData;
import com.mcflythekid.lazylearncore.repo.CardRepo;
import com.mcflythekid.lazylearncore.repo.DeckRepo;
import com.mcflythekid.lazylearncore.repo.MinpairRepo;
import com.mcflythekid.lazylearncore.repo.UserRepo;
import com.mcflythekid.lazylearncore.service.AuthService;
import com.mcflythekid.lazylearncore.util.HttpServletUtils;
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
    private MinpairRepo minpairRepo;
    @Autowired
    private HttpServletRequest request;

    protected ClientData getClientData(){
        ClientData clientData = new ClientData();

        String os = HttpServletUtils.getClientOS(request);
        String browser = HttpServletUtils.getClientBrowser(request);
        String ipAddress = HttpServletUtils.getClientIpAddr(request);

        clientData.setIpAddress(ipAddress);
        clientData.setData(String.format("IP address: %s - Device: %s %s", ipAddress, os, browser));

        return clientData;
    }

    private String getIpAddress(){
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null || ipAddress.isEmpty()){
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    protected String getUserId() throws Exception{
        return SecurityUtils.getCurrentUserLogin();
    }

    protected User authorizeUser(String userId) throws Exception {
        User user = userRepo.findOne(userId);
        if (user == null || !user.getId().equals(SecurityUtils.getCurrentUserLogin())){
            throw new AppException("It's not you");
        }
        return user;
    }

    protected Deck authorizeDeck(String deckId) throws Exception {
        Deck deck = deckRepo.findOne(deckId);
        if (deck == null || !deck.getUserId().equals(SecurityUtils.getCurrentUserLogin())){
            throw new AppException("It's not you");
        }
        return deck;
    }

    protected Card authorizeCard(String cardId) throws Exception {
        Card card = cardRepo.findOne(cardId);
        if (card == null || !card.getUserId().equals(SecurityUtils.getCurrentUserLogin())){
            throw new AppException("It's not you");
        }
        return card;
    }

    protected Minpair authorizeMinpair(String minpairId) throws Exception {
        Minpair minpair = minpairRepo.findOne(minpairId);
        if (minpair == null || !minpair.getUserId().equals(SecurityUtils.getCurrentUserLogin())){
            throw new AppException("It's not you");
        }
        return minpair;
    }
}