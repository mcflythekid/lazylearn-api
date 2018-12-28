package com.lazylearn.api.program;

import com.lazylearn.api.entity.Card;

public interface Program {
    void setCorrect(Card card);
    void setQuality(Card card, Integer quality);
    void setIncorrect(Card card);
}
