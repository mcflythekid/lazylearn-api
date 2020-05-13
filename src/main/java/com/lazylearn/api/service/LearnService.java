package com.lazylearn.api.service;

import com.lazylearn.api.config.Consts;
import com.lazylearn.api.entity.Card;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.entity.User;
import com.lazylearn.api.outdto.LearnOut;
import com.lazylearn.api.repo.CardRepo;
import com.lazylearn.api.repo.UserRepo;
import com.lazylearn.api.util.DateUtils2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author McFly the Kid
 */
@Service
public class LearnService {

    @Autowired
    private CardRepo cardRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DeckService deckService;

    private List<Card> findAllCardForReview(String deckId){
        return cardRepo.findAllByDeckIdAndArchived(deckId, Consts.CARDDECK_UNARCHIVED);
    }

    private List<Card> findAllCardForLearn(String deckId){
        return cardRepo.findAllByDeckIdAndArchivedAndWakeupOnBefore(deckId, Consts.CARDDECK_UNARCHIVED, new Date());
    }

    private List<Card> findAllCardForLearnByUserId(String userId){
        return cardRepo.findAllByUserIdAndWakeupOnBefore(userId, new Date());
    }

    private List<Card> findAllCardForLearnByUserIdOnetimeToday(String userId) throws ParseException {
        User user = userRepo.findOne(userId);
        return cardRepo.findAllByUserIdAndWakeupOnBeforeAndLearnedOnBefore(userId, new Date(), DateUtils2.getRemoteTimezoneMidnightDate(user.getTimezone()));
    }

    public LearnOut getByLearnOneUserId(String userId){
        LearnOut learnOut = new LearnOut();
        learnOut.setDeck(deckService.createOneForAllDeck());
        learnOut.setCards(this.findAllCardForLearnByUserId(userId));
        return learnOut;
    }

    public LearnOut getByLearnOneUserIdOnetimeToday(String userId) throws ParseException {
        LearnOut learnOut = new LearnOut();
        learnOut.setDeck(deckService.createOneForAllDeck());
        learnOut.setCards(this.findAllCardForLearnByUserIdOnetimeToday(userId));
        return learnOut;
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