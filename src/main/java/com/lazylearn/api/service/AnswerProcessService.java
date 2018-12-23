package com.lazylearn.api.service;

import com.lazylearn.api.config.exception.AppException;
import com.lazylearn.api.entity.Card;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.learnprogram.LearnProgram;
import com.lazylearn.api.repo.DeckRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnswerProcessService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private DeckRepo deckRepo;

    private LearnProgram getLearnProgram(Card card){
        Deck deck = deckRepo.findOne(card.getDeckId());
        LearnProgram program = (LearnProgram) context.getBean(deck.getProgramId());
        if (program == null){
            throw new AppException("Learn program not found: " + deck.getProgramId());
        }
        return program;
    }

    @Transactional
    public void setCorrect(Card card) {
        getLearnProgram(card).setCorrect(card);
    }

    @Transactional
    public void setIncorrect(Card card) {
        getLearnProgram(card).setIncorrect(card);
    }

    @Transactional
    public void setQuality(Card card, Integer quality) {
        getLearnProgram(card).setQuality(card, quality);
    }
}
