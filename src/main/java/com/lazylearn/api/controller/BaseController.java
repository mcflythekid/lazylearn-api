package com.lazylearn.api.controller;

import com.lazylearn.api.config.Consts;
import com.lazylearn.api.config.exception.AppException;
import com.lazylearn.api.entity.*;
import com.lazylearn.api.indto.ClientData;
import com.lazylearn.api.repo.*;
import com.lazylearn.api.util.HttpServletUtils;
import com.lazylearn.api.util.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
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
    private ArticleRepo articleRepo;

    @Autowired
    private VocabRepo vocabRepo;

    @Autowired
    private VocabdeckRepo vocabdeckRepo;

    @Autowired
    private MinpairFileRepo minpairFileRepo;

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

    protected String getUserId() throws Exception{
        return SecurityUtils.getCurrentUserLogin();
    }

    protected User authorizeUser(String userId) throws Exception {
        User data = userRepo.findOne(userId);
        authorizeObject(data);
        return data;
    }

    protected Deck authorizeDeck(String deckId) throws Exception {
        Deck data = deckRepo.findOne(deckId);
        authorizeObject(data);
        return data;
    }

    protected Card authorizeCard(String cardId) throws Exception {
        Card data = cardRepo.findOne(cardId);
        authorizeObject(data);
        return data;
    }

    protected Minpair authorizeMinpair(String minpairId) throws Exception {
        Minpair data = minpairRepo.findOne(minpairId);
        authorizeObject(data);
        return data;
    }

    protected Article authorizeArticle(String articleId) throws Exception {
        Article data = articleRepo.findOne(articleId);
        authorizeObject(data);
        return data;
    }

    protected Vocab authorizeVocab(String vocabId) throws Exception {
        Vocab data = vocabRepo.findOne(vocabId);
        authorizeObject(data);
        return data;
    }

    protected Vocabdeck authorizeVocabdeck(String vocabdeckId) throws Exception {
        Vocabdeck data = vocabdeckRepo.findOne(vocabdeckId);
        authorizeObject(data);
        return data;
    }

    protected MinpairFile authorizeMinpairFile(String minpairFileId) throws Exception {
        MinpairFile data = minpairFileRepo.findOne(minpairFileId);
        authorizeObject(data);
        return data;
    }

    private void authorizeObject(HasUserId hasUserId) throws Exception {
        if (Consts.Admin.isAdmin()){
            // HIJACK admin
            return;
        }

        if (hasUserId == null){
            throw Consts.Exception.NOT_FOUND;
        }
        if (!hasUserId.getUserId().equals(this.getUserId())){
            throw Consts.Exception.INVALID_OWNERSHIP;
        }
    }

    protected void protectGeneratedDeck(Deck deck) throws Exception{
        if (StringUtils.isNotBlank(deck.getVocabdeckId())){
            throw Consts.Exception.PROTECTED_VOCABDECK;
        }
        if (StringUtils.isNotBlank(deck.getMinpairLanguage())){
            throw Consts.Exception.PROTECTED_MINPAIR;
        }
        if (deck.getType() != null && deck.getType().equalsIgnoreCase(Consts.DECKTYPE__TOPIC)){
            throw Consts.Exception.PROTECTED_TOPIC;
        }
    }

    protected void protectGeneratedCard(Card card) throws Exception{
        if (StringUtils.isNotBlank(card.getVocabId())){
            throw Consts.Exception.PROTECTED_VOCABDECK;
        }
        if (StringUtils.isNotBlank(card.getMinpairLanguage())){
            throw Consts.Exception.PROTECTED_MINPAIR;
        }
    }
}