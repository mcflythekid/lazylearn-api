package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.outdto.LearnOutDto;
import com.mcflythekid.lazylearncore.repo.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author McFly the Kid
 */
@Service
@Transactional
public class LearnService {

    @Autowired
    private CardRepo cardRepo;

    private List<Card> findAllCardForReview(String deckId){
        return cardRepo.findAllByDeckId(deckId);
    }

    private List<Card> findAllCardForLearn(String deckId){
        return cardRepo.findAllByDeckIdAndWakeupOnBefore(deckId, new Date());
    }

    public LearnOutDto getByLearn(Deck deck){
        LearnOutDto learnOutDto = new LearnOutDto();
        learnOutDto.setDeck(deck);
        learnOutDto.setCards(this.findAllCardForLearn(deck.getId()));
        return learnOutDto;
    }

    public LearnOutDto getByReview(Deck deck){
        LearnOutDto learnOutDto = new LearnOutDto();
        learnOutDto.setDeck(deck);
        learnOutDto.setCards(this.findAllCardForReview(deck.getId()));
        return learnOutDto;
    }
}
