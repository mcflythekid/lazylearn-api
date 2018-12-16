package com.lazylearn.api.service;

import com.lazylearn.api.config.Consts;
import com.lazylearn.api.entity.Card;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.outdto.LearnOut;
import com.lazylearn.api.repo.CardRepo;
import com.lazylearn.api.repo.DeckRepo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private ProgramService programService;
    @Autowired
    private DeckRepo deckRepo;

    @Transactional(rollbackFor = Exception.class)
    public void markCorrect(Card card) {
        String programId = deckRepo.findOne(card.getDeckId()).getProgramId();

        card.increaseStep();
        card.setLearnedOn(new Date());

        if (card.getStep() >= programService.getCardStepEnd(programId)){
            card.setWakeupOn(null);
        } else {
            card.setWakeupOn(getWakeupOn(card.getStep(), programId));
        }
        cardRepo.save(card);
    }

    @Transactional(rollbackFor = Exception.class)
    public void markIncorrect(Card card) {
        card.setLearnedOn(new Date());
        card.setWakeupOn(new Date());
        card.resetStep();
        cardRepo.save(card);
    }


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

    private Date getWakeupOn(Integer step, String programId){
        return DateUtils.addDays(new Date(), programService.getDays(programId, step));
    }
}