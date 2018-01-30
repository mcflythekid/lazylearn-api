package com.mcflythekid.lazylearncore.service;
import java.util.Date;

import com.mcflythekid.lazylearncore.entity.Card;
import com.mcflythekid.lazylearncore.entity.Deck;
import com.mcflythekid.lazylearncore.indto.CardCreateInDto;
import com.mcflythekid.lazylearncore.indto.CardSearchInDto;
import com.mcflythekid.lazylearncore.outdto.BootstrapTableOutDto;
import com.mcflythekid.lazylearncore.outdto.JSON;
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


    @Autowired
    private CardRepo cardRepo;

    public BootstrapTableOutDto listByDeckAndSearch(Deck deck, CardSearchInDto cardSearchInDto) {
        List<Card> rows = cardRepo.findAllByDeckIdAndFrontContaining(deck.getId(), cardSearchInDto.getSearch(), cardSearchInDto.getPageable());
        Long total = cardRepo.countByDeckIdAndFrontContaining(deck.getId(), cardSearchInDto.getSearch());
        return new BootstrapTableOutDto(rows, total);
    }

    public JSON createCard(CardCreateInDto cardCreateInDto, Deck deck) {
        Card card = new Card();
        card.setId(authService.getRamdomId());
        card.setFront(cardCreateInDto.getFront());
        card.setBack(cardCreateInDto.getBack());
        card.setCreatedOn(new Date());
        card.setDeckId(deck.getId());
        card.setUserId(deck.getUserId());
        cardRepo.save(card);
        return JSON.ok();
    }

    @Autowired
    private AuthService authService;
}
