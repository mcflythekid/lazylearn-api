package com.lazylearn.api.learnprogram;

import com.lazylearn.api.entity.Card;

public interface LearnProgram {
    void setCorrect(Card card);
    void setQuality(Card card, Integer quality);
    void setIncorrect(Card card);
}