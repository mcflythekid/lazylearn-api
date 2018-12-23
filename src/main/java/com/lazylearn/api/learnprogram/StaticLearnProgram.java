package com.lazylearn.api.learnprogram;

import com.lazylearn.api.config.exception.AppException;
import com.lazylearn.api.entity.Card;
import com.lazylearn.api.entity.Deck;
import com.lazylearn.api.repo.CardRepo;
import com.lazylearn.api.repo.DeckRepo;
import com.lazylearn.api.service.ProgramService;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public abstract class StaticLearnProgram implements LearnProgram{

    @Autowired
    private ProgramService programService;

    @Autowired
    private CardRepo cardRepo;

    @Autowired
    private DeckRepo deckRepo;

    @Transactional
    @Override
    public void setCorrect(Card card) {
        Deck deck = deckRepo.findOne(card.getDeckId());
        card.increaseStep();
        card.setLearnedOn(new Date());

        if (card.getStep() >= programService.getCardStepEnd(deck.getProgramId())){
            card.setWakeupOn(null);
        } else {
            card.setWakeupOn(getWakeupOn(card.getStep(), deck.getProgramId()));
        }
        cardRepo.save(card);
    }

    @Transactional
    @Override
    public void setIncorrect(Card card) {
        Date now = new Date();
        card.setLearnedOn(now);
        card.setWakeupOn(now);
        card.resetStep();
        cardRepo.save(card);
    }

    @Override
    public void setQuality(Card card, Integer quality) {
        throw new AppException(401, "Not supported quality in basic program");
    }

    private Date getWakeupOn(Integer step, String programId){
        return DateUtils.addDays(new Date(), programService.getDays(programId, step));
    }
}
