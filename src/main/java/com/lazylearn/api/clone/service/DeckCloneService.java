package com.lazylearn.api.clone.service;

import com.lazylearn.api.entity.Card;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.entity.User;
import com.lazylearn.api.indto.card.CardCreateIn;
import com.lazylearn.api.indto.deck.DeckCreateIn;
import com.lazylearn.api.repo.CardRepo;
import com.lazylearn.api.repo.DeckRepo;
import com.lazylearn.api.repo.UserRepo;
import com.lazylearn.api.service.CardService;
import com.lazylearn.api.service.DeckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author 4nha
 * Date: 2020-05-02
 */
@Service
@Slf4j
public class DeckCloneService {

    @Autowired
    private DeckRepo deckRepo;

    @Autowired
    private CardRepo cardRepo;

    @Autowired
    private DeckService deckService;

    @Autowired
    private CardService cardService;

    @Autowired
    private UserRepo userRepo;

    private DeckCreateIn createDeckDto(String oldDeckId){
        Deck deck = deckRepo.findOne(oldDeckId);

        DeckCreateIn deckCreateIn = new DeckCreateIn();
        deckCreateIn.setName(deck.getName());

        return deckCreateIn;
    }

    private List<CardCreateIn> createCardDtoList(String oldDeckId, String newDeckId){
        List<Card> cards = cardRepo.findAllByDeckId(oldDeckId);

        List<CardCreateIn> cardCreateInList = new ArrayList<>();
        for(Card card : cards){
            CardCreateIn cardCreateIn = new CardCreateIn();
            cardCreateIn.setFront(card.getFront());
            cardCreateIn.setBack(card.getBack());
            cardCreateIn.setDeckId(newDeckId);
            cardCreateInList.add(cardCreateIn);
        }

        return cardCreateInList;
    }

    @Transactional
    public void cloneDeck(String oldDeckId, String userId){
        Deck oldDeck = deckRepo.findOne(oldDeckId);
        if (isBlank(oldDeck.getCloneableid())){
            throw new RuntimeException("Cannot clone because this deck does not have cloneableid");
        }
        if (deckRepo.countByUserIdAndCloneableid(userId, oldDeck.getCloneableid()) > 0){
            log.info("Skip because already imported");
            return;
        }

        Deck newDeck = deckService.create(createDeckDto(oldDeckId), userId);
        newDeck.setCloneableid(oldDeck.getCloneableid());
        deckRepo.save(newDeck);

        for (CardCreateIn cardCreateIn : createCardDtoList(oldDeckId, newDeck.getId())){
            cardService.create(cardCreateIn, userId);
        }
    }

    @Transactional
    public void cloneDeck(String oldDeckId){
        for (User user : userRepo.findAll()){
            cloneDeck(oldDeckId, user.getUserId());
        }
    }
}
