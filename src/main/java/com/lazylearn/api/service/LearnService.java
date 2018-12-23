package com.lazylearn.api.service;

import com.lazylearn.api.entity.Card;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.outdto.LearnOut;
import com.lazylearn.api.repo.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author McFly the Kid
 */
@Service
public class LearnService {

    @Autowired
    private CardRepo cardRepo;

    private List<Card> findAllCardForReview(String deckId){
        return cardRepo.findAllByDeckId(deckId);
    }

    private List<Card> findAllCardForLearn(String deckId){
        return cardRepo.findAllByDeckIdAndWakeupOnBefore(deckId, new Date());
    }

    public LearnOut getByLearn(Deck deck){
        LearnOut learnOut = new LearnOut();
        learnOut.setDeck(deck);
        learnOut.setCards(this.findAllCardForLearn(deck.getId()));
        return learnOut;
    }

    public LearnOut getByReview(Deck deck){
        LearnOut learnOut = new LearnOut();
        learnOut.setDeck(deck);
        learnOut.setCards(this.findAllCardForReview(deck.getId()));
        return learnOut;
    }
}