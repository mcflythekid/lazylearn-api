package com.lazylearn.api.service;

import com.lazylearn.api.entity.Card;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.indto.card.CardChangeDeckIn;
import com.lazylearn.api.indto.card.CardCreateIn;
import com.lazylearn.api.indto.card.CardEditIn;
import com.lazylearn.api.indto.card.CardSearchIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.repo.CardRepo;
import com.lazylearn.api.repo.DeckRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author McFly the Kid
 */
@Service
public class CardService {

    @Autowired
    private CardRepo cardRepo;

    @Autowired
    private DeckRepo deckRepo;

    public BootstraptableOut search(CardSearchIn in) {
        List<Card> rows = cardRepo.findAllByDeckIdAndSearch(in.getDeckId(),  in.getSearch(), in.getPageable());
        Long total = cardRepo.countByDeckIdAndSearch(in.getDeckId(), in.getSearch());
        return new BootstraptableOut(rows, total);
    }

    @Transactional(rollbackFor = Exception.class)
    public Card create(CardCreateIn in, String userId) {
        return create(in.getFront(), in.getBack(), in.getDeckId(), userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String cardId) {
        cardRepo.delete(cardId);
    }

    @Transactional(rollbackFor = Exception.class)
    public Card edit(CardEditIn in) {
        Card card = cardRepo.findOne(in.getCardId());
        card.setFront(in.getFront());
        card.setBack(in.getBack());
        return cardRepo.save(card);
    }

    @Transactional(rollbackFor = Exception.class)
    public Card changeDeck(CardChangeDeckIn in) {
        Card card = cardRepo.findOne(in.getCardId());
        Deck newDeck = deckRepo.findOne(in.getDeckId());
        Deck oldDeck = deckRepo.findOne(card.getDeckId());
        Date updatedDate = new Date();

        card.setDeckId(newDeck.getId());
        card.setUpdatedDate(updatedDate);

        oldDeck.setUpdatedDate(updatedDate);
        newDeck.setUpdatedDate(updatedDate);
        deckRepo.save(oldDeck);
        deckRepo.save(newDeck);

        return cardRepo.save(card);
    }

    @Transactional(rollbackFor = Exception.class)
    public void importCards(List<String> cardLines, Deck deck){
        for (String cardLine: cardLines){
            if(StringUtils.isBlank(cardLine)){
                continue;
            }
            String[] sides = cardLine.split("\\t");
            if (sides.length < 2){
                continue;
            }
            create(sides[0], sides[1], deck.getId(), deck.getUserId());
        }
    }

    public Card create(String front, String back, String deckId, String userId){
        Card card = new Card();
        card.setFront(front);
        card.setBack(back);
        card.setDeckId(deckId);
        card.setUserId(userId);
        return cardRepo.save(card);
    }
}