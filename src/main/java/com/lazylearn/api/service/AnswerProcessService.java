package com.lazylearn.api.service;

import com.lazylearn.api.config.exception.AppException;
import com.lazylearn.api.entity.Card;
import com.lazylearn.api.learnprogram.LearnProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnswerProcessService {

    @Autowired
    private ApplicationContext context;

    private LearnProgram getLearnProgram(Card card){
        LearnProgram program = (LearnProgram) context.getBean(card.getProgramId());
        if (program == null){
            throw new AppException("Learn program not found: " + card.getProgramId());
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
