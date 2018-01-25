package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.repo.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Service
public class CardService {

    public List<Card> findAllCardByDeckId(String deckId){
        return cardRepo.findAllByDeckId(deckId);
    }

    public Card createCard(Card card){
        return cardRepo.save(card);
    }

    @Autowired
    private CardRepo cardRepo;
}
